package com.example.Hari.NewSpringBatch.Config;

import com.example.Hari.NewSpringBatch.Model.CustomProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.Hari.NewSpringBatch.Entity.Person;
import com.example.Hari.NewSpringBatch.Model.CustomLineMapper;
import com.example.Hari.NewSpringBatch.Model.DataRecord;
import com.example.Hari.NewSpringBatch.Model.PersonProcessor;
import com.example.Hari.NewSpringBatch.Repo.PersonRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
// @EnableSpring Batch Processing - annotation is not needed  for latest version (5.0)

@Slf4j

public class SpringBatchConfig {

    @Autowired
    private PersonRepository personRepository;


    public final DataSource dataSource;

    public SpringBatchConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("new-job", jobRepository)
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
                 .resource(new ClassPathResource("people-1000.csv"))
              //  .resource(new FileSystemResource("/home/hariharasudhan/Documents/SpringBatch/people-1000.csv"))
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


//------------------------------------------------------------------------------------------------------------------------------------//

    @Bean
    public Job createJob(JobRepository jobRepository,
                         Step newSteps) {
        return new JobBuilder( LocalDateTime.now().toString(), jobRepository)
                .start(newSteps)
                .build();
    }


    @Bean
@Primary
    public Step newSteps(JobRepository jobRepository,
                        PlatformTransactionManager platformTransactionManager) throws IOException, ParseException {

        return new StepBuilder("multi-file-data-loading-step", jobRepository)
                .<DataRecord, DataRecord>chunk(10, platformTransactionManager)
                .reader(readers(null, null))
                .processor(processors(""))
                .writer(writers(null))
                .build();
    }


    @Bean
    @StepScope
    public FlatFileItemReader<DataRecord> readers(@Value("#{jobParameters['filePath']}") String filePath,
                                                  @Value("#{jobParameters['jsonPath']}") String jsonPath)
            throws IOException, ParseException {

        log.info("Inside Job Reader");

        //read file and get fields in order
        //Create reader instance
        return new FlatFileItemReaderBuilder<DataRecord>()
                .name("dataItemReader")
                .resource(new FileSystemResource(filePath))
                .recordSeparatorPolicy(new DefaultRecordSeparatorPolicy())
                .lineMapper(new CustomLineMapper(jsonColumnNames(jsonPath), ","))
                .linesToSkip(1)
                .build();
    }


    @Bean
    @StepScope
    public CustomProcessor processors(@Value("#{jobParameters['fileName']}") String filename) {
        return new CustomProcessor(filename);
    }

    @Bean
    @StepScope
    @Transactional
    public JdbcBatchItemWriter<DataRecord> writers(@Value("#{jobParameters['jsonPath']}") String jsonPath)
            throws IOException, ParseException {

        ArrayList<String> fields = new ArrayList<>(jsonColumnNames(jsonPath));
        String table = getTable(jsonPath);



// Wants to check whether the table is present or not !!!!!!!!!

        String queryColumns = String.join(", ", fields);
        log.info("QueryColumn:{}",queryColumns);
        String valueColumns = fields.stream().map(field -> ":" + field).collect(Collectors.joining(", "));
        log.info("valueColumns:{}",valueColumns);


        // Construct SQL without ON DUPLICATE KEY UPDATE
        String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                table, queryColumns, valueColumns
        );

        log.info("SQL Statement: {}", sql);

        return new JdbcBatchItemWriterBuilder<DataRecord>()
                .itemSqlParameterSourceProvider(item -> {
                    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
                    mapSqlParameterSource.addValues(item.getDataMap());
                    return mapSqlParameterSource;
                })
                .sql(sql)
                .dataSource(dataSource)
                .build();
    }



    public static ArrayList<String> jsonColumnNames(@Value("#{jobParameters['jsonPath']}") String jsonPath) throws IOException, org.json.simple.parser.ParseException {

        JSONParser jsonParser = new JSONParser();
        InputStream resource = new FileSystemResource(jsonPath).getInputStream();
        Reader reader = new InputStreamReader(resource);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray jsonArray = (JSONArray) jsonObject.get("fields");
        log.info("Columns In > {}", jsonArray);
        ArrayList<String> arr = new ArrayList<>();
        for (Object o : jsonArray)
            arr.add((String) o);
        return arr;
    }


    public static String getTable(@Value("#{jobParameters['jsonPath']}") String jsonPath) throws IOException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        InputStream resource = new FileSystemResource(jsonPath).getInputStream();
        Reader reader = new InputStreamReader(resource);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        return (String) jsonObject.get("table");
    }

    @Autowired
    JdbcTemplate jdbcTemplate ;



    public ResponseEntity<?> createTable(String columnName , String tableName){



//        List<String> tableFields = List.of("id INT PRIMARY KEY", "name VARCHAR(255)", "created_at TIMESTAMP");
//
//        String tableName = "my_table";

       // String fields = String.join(", ", tableFields);


        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s)",tableName,columnName);

        try {
            // Execute the SQL query
            jdbcTemplate.execute(sql);

            // Log the SQL for debugging purposes
            System.out.println("Generated SQL: " + sql);

            // Return a success response
            return ResponseEntity.ok("Table created successfully or already exists");
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating table: " + e.getMessage());
        }
    }


       // jdbcTemplate.execute(sql);



































































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
