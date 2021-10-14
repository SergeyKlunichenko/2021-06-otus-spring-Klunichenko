package ru.otus.bookstore.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.otus.bookstore.springbatch.model.Autor;
import ru.otus.bookstore.springbatch.model.Book;
import ru.otus.bookstore.springbatch.model.Genre;
import ru.otus.bookstore.springbatch.service.CleanUpService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;


@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    public static final String IMPORT_USER_JOB_NAME = "importUserJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CleanUpService cleanUpService;

    @PersistenceContext
    private EntityManager entityManager;


    //*******************************  Genre  ***************************************//
    //*******************************  Genre  ***************************************//
    //*******************************  Genre  ***************************************//
    @Bean
    public Step genreStep(ItemReader<Genre> reader, JpaItemWriter<Genre> writer) {
        return stepBuilderFactory.get("genre")
                .<Genre, Genre>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Genre> readerGenre( MongoTemplate mongoTemplate){
        return new MongoItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .template(mongoTemplate)
                .collection("genre")
                .jsonQuery("{}")
                .targetType(Genre.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Genre> jpaWriterGenre(){
        JpaItemWriter<Genre> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return writer;

    }

    //*******************************  Autor  ***************************************//
    //*******************************  Autor  ***************************************//
    //*******************************  Autor  ***************************************//
    @Bean
    public Step autorStep(ItemReader<Autor> reader, JpaItemWriter<Autor> writer) {
        return stepBuilderFactory.get("autor")
                .<Autor, Autor>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Autor> readerAutor(MongoTemplate mongoTemplate){
        return new MongoItemReaderBuilder<Autor>()
                .name("autorItemReader")
                .template(mongoTemplate)
                .collection("autor")
                .jsonQuery("{}")
                .targetType(Autor.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Autor> jpaWriterAutor(){
        JpaItemWriter<Autor> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return writer;

    }

    //*******************************  Book  ***************************************//
    //*******************************  Book  ***************************************//
    //*******************************  Book  ***************************************//
    @Bean
    public Step bookStep(ItemReader<Book> reader, JpaItemWriter<Book> writer) {
        return stepBuilderFactory.get("book")
                .<Book, Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Book> readerBook( MongoTemplate mongoTemplate){
        return new MongoItemReaderBuilder<Book>()
                .name("bookItemReader")
                .template(mongoTemplate)
                .collection("book")
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Book> jpaWriterBook(){
        JpaItemWriter<Book> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return writer;

    }


    //*******************************  Job  ***************************************//
    //*******************************  Job  ***************************************//
    //*******************************  Job  ***************************************//


    @Bean
    public MethodInvokingTaskletAdapter cleanUpTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();

        adapter.setTargetObject(cleanUpService);
        adapter.setTargetMethod("cleanUp");

        return adapter;
    }


    @Bean
    public Job importUserJob(/*Step transformPersonsStep,*/ Step genreStep, Step autorStep, Step bookStep,  Step cleanUpStep) {
        return jobBuilderFactory.get(IMPORT_USER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                //.flow(transformPersonsStep)
                .flow(genreStep)
                .next(autorStep)
                .next(bookStep)
                .next(cleanUpStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

//
    @Bean
    public Step cleanUpStep() {
        return this.stepBuilderFactory.get("cleanUpStep")
                .tasklet(cleanUpTasklet())
                .build();
    }
}
