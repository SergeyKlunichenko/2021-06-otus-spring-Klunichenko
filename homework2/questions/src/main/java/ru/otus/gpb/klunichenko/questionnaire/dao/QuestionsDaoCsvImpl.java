package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoCsvImpl implements QuestionsDao {

    private String delim;
    private String fileName;
    private List<Question> rows = new ArrayList<Question>();

    public QuestionsDaoCsvImpl(@Value("${questions.filequest}") String  fileName, @Value("${questions.delim}") String delim){
        this.delim =  delim;
        this.fileName = fileName;
    }

    public List<Question> getAll() throws IOException {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(delim);
                rows.add(new Question(fields[0], fields[1], fields[2]));
            }
        }
        return rows;
    }

    public int getSize(){
        return rows.size();
    }

    public Question getRow(int pos){
        return rows.get(pos);
    }

    public List<Question> getRows(){
        return rows;
    }

    public String toString() {
        return String.format("fileName=\"%s\", delim=\"%s\"", fileName, delim);
    }

}
