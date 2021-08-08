package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;


@ConfigurationProperties(prefix="properties")
@Component
public class AppConfig {
    private  String  delim = "";
    private  String  filequest = "";

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

    public String getFileQuest() {
        String loc =     Locale.forLanguageTag(locale).getLanguage();
        return filequest.replace("{locale}", loc.equals("")?"en":loc) ;
    }

    public void setFileQuest(String filequest) {
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
