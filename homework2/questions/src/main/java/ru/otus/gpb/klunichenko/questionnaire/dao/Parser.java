package ru.otus.gpb.klunichenko.questionnaire.dao;

import java.io.IOException;
import java.util.List;

public interface Parser {
    public void parse() throws IOException;
    //public void setFileName(String fileName);
    public String toString();

    public int getSize();

    public String[] getRow(int pos);
    public List<String[]> getRows();

}
