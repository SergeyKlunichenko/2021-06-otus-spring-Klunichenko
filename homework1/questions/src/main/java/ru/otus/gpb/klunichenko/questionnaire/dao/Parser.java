package ru.otus.gpb.klunichenko.questionnaire.dao;

import java.io.IOException;
import java.util.List;

public interface Parser {
     void parse() throws IOException;
     void setFileName(String fileName);
     String toString();

     int getSize();

     String[] getRow(int pos);
     List<String[]> getRows();

}
