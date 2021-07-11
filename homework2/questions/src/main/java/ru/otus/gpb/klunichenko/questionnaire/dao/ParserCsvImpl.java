package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Repository
public class ParserCsvImpl implements  Parser{

    private String delim;
    private String fileName;
    private List<String[]> rows = new ArrayList<String[]>();


    public  ParserCsvImpl(@Value("${questions.filequest}") String fileName, @Value("${questions.delim}") String delim){
        this.delim =  delim;
        this.fileName = fileName;
    }

//    public void setFileName(String fileName){
//        this.fileName = fileName;
//    }

    public void parse() throws IOException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(resource);
        BufferedReader br = new BufferedReader(reader);

        String line;
        while((line = br.readLine()) != null){
            rows.add(line.split(delim));
        }


    }

    public int getSize(){
        return rows.size();
    }

    public String[] getRow(int pos){
        return rows.get(pos);
    }

    public List<String[]> getRows(){
        return rows;
    }

    public String toString() {
        return String.format("fileName=\"%s\", delim=\"%s\"", fileName, delim);
    }

}
