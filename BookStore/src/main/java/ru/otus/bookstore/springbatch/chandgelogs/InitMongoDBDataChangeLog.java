package ru.otus.bookstore.springbatch.chandgelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.bookstore.springbatch.model.Autor;
import ru.otus.bookstore.springbatch.model.Book;
import ru.otus.bookstore.springbatch.model.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }


    @ChangeSet(order = "001", id = "initBookStory", author = "", runAlways = true)
    public void initBookStory(MongockTemplate template) {
        System.out.println("Load genres");
        Genre genre1 = new Genre(1l, "драма");
        Genre genre2 = new Genre(2l, "фантастика");

        template.save(genre1);
        template.save(genre2);

        System.out.println("Load autors");
        Autor autor1 = new Autor(1l, "Булгаков Михаил Афанасьевич");
        Autor autor2 = new Autor(2l, "Стругацкий Аркадий Натанович");

        template.save(autor1);
        template.save(autor2);

        System.out.println("Load books");

        template.save(new Book(1l,"Пикник на обочине", autor2, genre2 ));
        template.save(new Book(2l,"Улитка на склоне", autor2,genre2));
        template.save(new Book(3l,"Белая гвардия", autor1, genre1 ));
        template.save(new Book(4l,"Мастер и Маргарита", autor1, genre1));

    }


}
