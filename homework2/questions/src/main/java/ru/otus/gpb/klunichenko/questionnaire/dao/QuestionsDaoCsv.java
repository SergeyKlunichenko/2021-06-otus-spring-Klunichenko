package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.exceptions.QuestionaireException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoCsv implements QuestionsDao {

    private final String delim;
    private final String fileName;

    public QuestionsDaoCsv(@Value("${questions.filequest}") String  fileName, @Value("${questions.delim}") String delim){
        this.delim =  delim;
        this.fileName = fileName;
    }

    public List<Question> getAll()  {
        List<Question> rows = new ArrayList<Question>();
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delim);
                rows.add(new Question(fields[0], fields[1], fields[2]));
            }
        } catch (IOException e){
            throw new QuestionaireException("Не найден файл с вопросами: " + fileName);
        } catch (Exception ex) {
            throw new QuestionaireException("Ошибка выполнения:"+ex.getMessage());

        }
        return rows;
    }


}
