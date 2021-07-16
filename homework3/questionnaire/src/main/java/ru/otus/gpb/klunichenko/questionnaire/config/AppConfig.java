package ru.otus.gpb.klunichenko.questionnaire.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix="questions")
@Component
public class AppConfig {
    String delim;
    String filequest;
    public String getDelim() {
        return delim;
    }

    public void setDelim(String delim) {
        this.delim = delim;
    }

    public String getFilequest() {
        return filequest;
    }

    public void setFilequest(String filequest) {
        this.filequest = filequest;
    }

}
