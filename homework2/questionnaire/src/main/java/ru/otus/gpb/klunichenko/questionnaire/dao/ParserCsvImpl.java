package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import ru.otus.gpb.klunichenko.questionnaire.config.AppConfig;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParserCsvImpl implements  Parser{

    private String delim;
    private String fileName;
    private List<String[]> rows = new ArrayList<String[]>();


    public  ParserCsvImpl(AppConfig appConfig){
        this.delim      =   appConfig.getDelim();
        this.fileName   =   appConfig.getFilequest() ;
    }

    public void parse() throws IOException {
        InputStream resource = null;
        try {
            resource = getClass().getClassLoader().getResourceAsStream(fileName);
            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(delim));
            }
        } finally {
            if(resource != null){
                resource.close();
            }
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
