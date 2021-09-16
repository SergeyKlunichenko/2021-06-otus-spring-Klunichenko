package ru.otus.gpb.klunichenko.questionnaire.dao;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParserCsvImpl implements  Parser{

    private String delim;
    private String fileName;
    private List<String[]> rows = new ArrayList<String[]>();

    public  ParserCsvImpl(String delim){
        this.delim =  delim;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void parse() throws Exception {
        /*
        URL resource = getClass().getClassLoader().getResource(fileName);

        System.out.printf("load file %s\r\n", resource.toURI().toString());

        if(resource == null){
            throw new Exception(String.format("The file \"%s\" with the questions was not found", fileName));
        }

        File fileCsv = new File(resource.getFile());
        FileReader reader = new FileReader(fileCsv);
        BufferedReader br = new BufferedReader(reader);
         */

        InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(resource);
        BufferedReader br = new BufferedReader(reader);

        String line;
        while((line = br.readLine()) != null){
            //System.out.println(line);

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
