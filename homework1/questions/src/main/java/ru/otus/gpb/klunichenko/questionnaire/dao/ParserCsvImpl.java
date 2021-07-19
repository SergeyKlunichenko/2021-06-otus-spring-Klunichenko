package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ParserCsvImpl implements  Parser{

    private String delim;
    private String fileName;
    private List<String[]> rows = new ArrayList<String[]>();

    public  ParserCsvImpl(@Value("${questions.delim}") String delim){
        this.delim =  delim;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /********************************************
     * Парсер csv
     * @throws Exception
     */
    public void parse() throws IOException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(delim));
            }
        } finally {
            if(resource != null) resource.close();
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
