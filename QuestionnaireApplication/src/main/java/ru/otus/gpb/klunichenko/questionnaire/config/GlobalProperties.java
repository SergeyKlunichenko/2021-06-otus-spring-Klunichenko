package ru.otus.gpb.klunichenko.questionnaire.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="properties")
public class GlobalProperties {
    private  String delim;
    private  String filequest;

    public String getFilequest() {
        return filequest;
    }

    public void setFilequest(String filequest) {
        this.filequest = filequest;
    }

    public String getDelim() {
        return delim;
    }

    public void setDelim(String delim) {
        this.delim = delim;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "delim='" + delim + '\'' +
                ", filequest='" + filequest + '\'' +
                '}';
    }
}
