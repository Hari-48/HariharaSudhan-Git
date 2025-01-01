package com.example.springBootJPA.Configuration;

import com.example.springBootJPA.Entity.HumanBeing;
import com.example.springBootJPA.Repository.HumanBeingRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfiguration {





    private final HumanBeingRepo humanBeingRepo;

    @Bean
    public Job importHumanBeingsJob(JobRepository jobRepository, Step csvImportStep) {
        return new JobBuilder("importHumanBeings", jobRepository)
                .start(csvImportStep)
                .build();
    }

    @Bean
    public Step csvImportStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-import-step", jobRepository)
                .<HumanBeing, HumanBeing>chunk(10, transactionManager)
                .reader(reader())
                // Uncomment if processing logic is needed
                // .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<HumanBeing> reader() {
        return new FlatFileItemReaderBuilder<HumanBeing>()
                .name("humanBeingItemReader")
                .resource(new ClassPathResource("people-1000.csv"))
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .targetType(HumanBeing.class)
                .build();
    }

    @Bean
    public RepositoryItemWriter<HumanBeing> writer() {
        RepositoryItemWriter<HumanBeing> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(humanBeingRepo);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public HumanBeingProcessor processor() {
        return new HumanBeingProcessor();
    }

    private DefaultLineMapper<HumanBeing> lineMapper() {
        DefaultLineMapper<HumanBeing> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setNames("id", "userId", "firstName", "lastName", "gender", "email", "phone", "dateOfBirth", "jobTitle");

        BeanWrapperFieldSetMapper<HumanBeing> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(HumanBeing.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}












//
//
//
//
//
//    @Autowired
//    private HumanBeingRepo humanBeingRepo;
//
//
//    @Bean
//    public Job job(JobRepository jobRepository, Step step) {
//        return new JobBuilder("importHumanBeings", jobRepository).start(step).build();
//    }
//
//    @Bean
//    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("csv-import-step", jobRepository).
//                <HumanBeing, HumanBeing>chunk(10,platformTransactionManager)
//                .reader(reader())
//               // .processor(processor())
//                .writer(writer())
//                .build();
//    }
//
//
//    @Bean
//    public FlatFileItemReader<HumanBeing> reader() {
//
//        return new FlatFileItemReaderBuilder<HumanBeing>()
//                .name("HumanBeingItemReader")
//                .resource(new ClassPathResource("people-1000.csv"))
//                .linesToSkip(1)
//                .lineMapper(lineMapper())
//                .targetType(HumanBeing.class)
//                .build();
//    }
//
//    @Bean
//    HumanBeingProcessor processor() {
//        return new HumanBeingProcessor();
//    }
//
//    @Bean
//    public ItemWriter<HumanBeing> writer() {
//        RepositoryItemWriter<HumanBeing> itemWriter = new RepositoryItemWriter<>();
//        itemWriter.setRepository(humanBeingRepo);
//        itemWriter.setMethodName("save");
//        return itemWriter;
//    }
//
//    private LineMapper<HumanBeing> lineMapper() {
//        DefaultLineMapper<HumanBeing> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setDelimiter(",");
//        delimitedLineTokenizer.setStrict(false);
//        delimitedLineTokenizer.setNames("id", "userId", "firstName", "lastName", "gender", "email", "phone", "dateOfBirth", "jobTitle");
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//        BeanWrapperFieldSetMapper<HumanBeing> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        beanWrapperFieldSetMapper.setTargetType(HumanBeing.class);
//        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
//        return defaultLineMapper;
//    }

//}
