package com.example.Hari.Batchprocessing.Config;

import com.example.Hari.Batchprocessing.Entity.Customer;
import com.example.Hari.Batchprocessing.Repository.CustomerRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration

@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
    private static final Logger log = LoggerFactory.getLogger(SpringBatchConfig.class);


    private final JobRepository jobBuilderFactory;
    private  final PlatformTransactionManager stepBuilderFactory;



    private CustomerRepository customerRepository;

    @Bean
//    to read a data from csv file we use flatfile Item reader
    public ItemReader<Customer> reader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();

        itemReader.setResource(new FileSystemResource("/home/harihara/Downloads/MOCK_DATA (2).csv"));
//      We can give any name to the reader .
        itemReader.setName("csvReader");
//        to skip header.
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
//        log.info("Item Reader:{}", itemReader);
        return itemReader;
    }


    private LineMapper<Customer> lineMapper() {
//         How to map  the data from file to obj and How to read the data in file >>>>> line apper
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

//        how to read by using ,
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
//  read by using these header
        lineTokenizer.setNames("id", "name", "job", "email", "gender");
//        feild set mapper map the data to obj customer
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
//        log.info("Line Mapper:{}", lineMapper);
        return lineMapper;
    }

    @Bean
    public ItemProcessor<Customer, Customer> processor() {
        return new CustomProcessor();
    }

    //RepositoryItemWriter used to write the data - use my customer repoitory use save method write the data in database
    @Bean
    public ItemWriter<Customer> writer() {
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();

        writer.setRepository(customerRepository);
        writer.setMethodName("save");
//        log.info("WRITER:{}", writer);
        return writer;
    }

    @Bean
    public Step step1() {
        StepBuilder stepBuilder = new StepBuilder("myStep", jobBuilderFactory);

        return stepBuilder.<Customer, Customer>chunk(10, stepBuilderFactory)
                .reader(reader())
                .processor(processor())
                .writer(writer()).
                taskExecutor(taskExecutor())
                .build();




//        return stepBuilderFactory.get("csv-step").<Customer, Customer>chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .taskExecutor(taskExecutor())
//                .build();
    }




    @Bean
    public Job runJob() {
        //return jobBuilderFactory.get("importCustomers").flow(step1()).end().build();
        JobBuilder jobBuilder = new JobBuilder("myJob", jobBuilderFactory);
        return jobBuilder.start(step1()).build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
