package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.QuestionaireException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoCsvImpl implements QuestionsDao {

    private final AppConfig config;
    private final List<Question> rows = new ArrayList<Question>();

    public QuestionsDaoCsvImpl(AppConfig config){
        this.config = config;
    }


    public List<Question> getAll() throws QuestionaireException {
        List<Question> rows = new ArrayList<>();
        String fileName = config.getFilequest();
        String locale = config.getLocale();
        fileName = fileName.replace(".", "_"+locale+".").replace("-", "_");

        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if(resource == null){
                throw new QuestionaireException("Не найден файл с вопросами:" + fileName);
            }

            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(config.getDelim());
                rows.add(new Question(fields[0], fields[1], fields[2]));
            }
        } catch (IOException e){
            throw new QuestionaireException("Не найден файл с вопросами: " + fileName);
        } catch (Exception ex) {
            throw new QuestionaireException(ex);
        }

        return rows;
    }
}
