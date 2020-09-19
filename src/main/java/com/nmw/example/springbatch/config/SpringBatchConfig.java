package com.nmw.example.springbatch.config;


import com.nmw.example.springbatch.batch.FilmWriter;
import com.nmw.example.springbatch.batch.UserWriter;
import com.nmw.example.springbatch.batch.UserProcessor;
import com.nmw.example.springbatch.model.Film;
import com.nmw.example.springbatch.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job loadCsvFileJob(){
       return  this.jobBuilderFactory.get("load-csv-file")
                                .start(userStep())
                                .next(filmStep())
                                .build();

    }

    private Step userStep() {
        return stepBuilderFactory.get("step-load-user-file")
                .<User, User>chunk(500)
                .reader(flatFileItemReaderUser())
                .processor(UserProcessor())
                .writer(userWriter())
                .build();
    }

    private Step filmStep() {
        return stepBuilderFactory.get("step-load-film-file")
                .<Film, Film>chunk(500)
                .reader(flatFileItemReaderFilm())
                .writer(filmWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

//0
//0

    @Bean
    public FlatFileItemReader<User> flatFileItemReaderUser() {
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/user.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapperUser());

        return  flatFileItemReader;
    }

    @Bean
    public FlatFileItemReader<Film> flatFileItemReaderFilm() {
        FlatFileItemReader<Film> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/film.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapperFilm());

        return  flatFileItemReader;
    }

    private LineMapper<Film> lineMapperFilm() {
        DefaultLineMapper<Film> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"year","movieLength","title","subject","actor","actress","director","popularity","award","images"});
        BeanWrapperFieldSetMapper<Film> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Film.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;

    }
    private LineMapper<User> lineMapperUser() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;

    }

    /*@StepScope
    @Bean
    public  ItemProcessor<User, User> processor() {

        final CompositeItemProcessor<User, User> processor = new CompositeItemProcessor<>();
        processor.setDelegates(Arrays.asList(UserProcessor()));
        return processor;


    }
*/
    @StepScope
    @Bean
    public UserProcessor UserProcessor() {
        return new UserProcessor();
    }

    @StepScope
    @Bean
    public ItemWriter<User> userWriter(){
        return  new UserWriter();
    }

    @StepScope
    @Bean
    public ItemWriter<Film> filmWriter() {
        return new FilmWriter();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(64);
        executor.setMaxPoolSize(64);
        executor.setQueueCapacity(64);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("MultiThreaded-");
        return executor;
    }

}
