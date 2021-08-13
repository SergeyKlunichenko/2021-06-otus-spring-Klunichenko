package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.QuestionaireException;
import ru.otus.spring.tools.IOService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoCsvImpl implements QuestionsDao {

    private final AppConfig config;
    public QuestionsDaoCsvImpl(AppConfig config){
        this.config = config;
    }


    public List<Question> getAll() throws QuestionaireException {
        List<Question> rows = new ArrayList<>();
        String fileName = config.getFileQuest();
        System.out.printf("A file %s with questions has been opened\n", fileName)    ;

        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            //Проверяем на наличие файла с вопросами.  если resource == null,  то файла нет.
            if(resource == null){
                throw new QuestionaireException(String.format("The file with questions %s was not found\n", fileName));
            }

            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(config.getDelim());
                rows.add(new Question(fields[0], fields[1], fields[2]));
            }
        } catch (Exception ex) {
            throw new QuestionaireException(ex);
        }

        return rows;
    }
}
