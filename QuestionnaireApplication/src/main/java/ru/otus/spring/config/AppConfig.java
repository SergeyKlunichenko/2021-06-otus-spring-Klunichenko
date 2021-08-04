package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix="properties")
@Component
public class AppConfig {
    String delim;
    String filequest;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    String locale;
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

    @Override
    public String toString() {
        return "AppConfig{" +
                "delim='" + delim + '\'' +
                ", filequest='" + filequest + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
