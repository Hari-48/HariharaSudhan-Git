package com.example.springBootJPA.Model;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class BatchUpload {

    public final DataSource dataSource;
    private Long jobId;

    public BatchUpload(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setId(Long id) {
        this.jobId = id;
    }


//     Job > Step > reader > processor > write


    @Bean
    public Job createJob(@Value("#{jobParameters['fileName']}") String fileName,
                         JobRepository jobRepository,
                         Step newStep) {
        return new JobBuilder(fileName + "_" + LocalDateTime.now().toString(), jobRepository)
                .start(newStep)
                .build();
    }


    @Bean
    public Step newStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws IOException, ParseException {
        return new StepBuilder("importStep", jobRepository)
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

        log.info("Table Name > :{}", table);


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


}
