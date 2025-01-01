package com.example.springBootJPA.config;


import com.example.springBootJPA.Entity.Person;
import com.example.springBootJPA.Model.BatchUpload;
import com.example.springBootJPA.Repository.PersonRepository;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private BatchUpload batchUpload;


    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("importPersons", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-import-step", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                // .resource(new ClassPathResource("people-1000.csv"))
                .resource(new FileSystemResource("/home/hariharasudhan/Documents/SpringBatch/people-1000.csv"))
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .targetType(Person.class)
                .build();
    }

    private LineMapper<Person> lineMapper() {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "userId", "firstName", "lastName", "gender", "email", "phone", "dateOfBirth", "jobTitle");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    RepositoryItemWriter<Person> writer() {
        RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
        writer.setRepository(personRepository);
        writer.setMethodName("save");
        return writer;
    }





   /* @Bean
    public Job createJob(String name, JobRepository jobRepository, Step newStep) {
        return new JobBuilder(name, jobRepository).start(newStep).build();
    }


    @Bean
    public Step newStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws IOException, ParseException {
        return new StepBuilder("multi-file-load", jobRepository)
                .chunk(10, platformTransactionManager)
                .reader(newReader(null, null,null))
                .writers(newWriter(null))
                .build();
    }



    @Bean
    public <T> FlatFileItemReader<T> newReader(
            @Value("#{jobParameters['filePath']}") String filePath,
            @Value("#{jobParameters['jsonPath']}") String jsonPath,
            @Value("#{jobParameters['targetType']}") Class<T> targetType
    ) throws IOException {
        return new FlatFileItemReaderBuilder<T>()
                .name("Reader")
                .resource(new ClassPathResource(filePath))
                .linesToSkip(1)
                .lineMapper(newLineMapper(targetType, jsonPath))
                .build();

    }


    @Autowired
    private Map<String, CrudRepository<?, ?>> repositories;

    @SuppressWarnings("unchecked")
    public <T> RepositoryItemWriter<T> newWriter(@Value("#{jobParameters['targetType']}") Class<T> targetType) {
        CrudRepository<T, ?> repository = (CrudRepository<T, ?>) repositories.get(targetType.getSimpleName());
        if (repository == null) {
            throw new IllegalArgumentException("No repository found for type: " + targetType.getSimpleName());
        }
        RepositoryItemWriter<T> writers = new RepositoryItemWriter<>();
        writers.setRepository(repository);
        writers.setMethodName("save");
        return writers;
    }

    @Bean
    private <T> LineMapper<T> newLineMapper(Class<T> targetType, String jsonPath) throws IOException {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        // lineTokenizer.setNames("id", "userId", "firstName", "lastName", "gender", "email", "phone", "dateOfBirth", "jobTitle");

        lineTokenizer.setNames(getColumnNames(jsonPath));

        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(targetType);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    public static String[] getColumnNames(String jsonPath) throws IOException {
        // Load the JSON file
        InputStream resource = new ClassPathResource(jsonPath).getInputStream();

        // Parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(resource);

        // Extract "fields" array
        JsonNode fieldsNode = rootNode.get("fields");

        if (fieldsNode == null || !fieldsNode.isArray()) {
            throw new IllegalArgumentException("Invalid JSON format: 'fields' array not found");
        }

        // Convert to String array
        String[] columns = new String[fieldsNode.size()];
        for (int i = 0; i < fieldsNode.size(); i++) {
            columns[i] = fieldsNode.get(i).asText();
        }
        return columns;
    }
*/
}
