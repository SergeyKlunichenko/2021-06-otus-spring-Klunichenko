package ru.otus.gpb.klunichenko.questionnaire.dao;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    public void getAll() throws IOException;
    public String toString();

    //public int getSize();

    public String[] getRow(int pos);
    public List<String[]> getRows();

}
