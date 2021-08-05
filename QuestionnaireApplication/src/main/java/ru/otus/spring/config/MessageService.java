package ru.otus.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
public class MessageService {
    private final Locale locale;
    private final MessageSource msg;
    public MessageService(MessageSource msg,  AppConfig config){
        System.out.println("config.getLocale()="+config.getLocale());
        this.locale = Locale.forLanguageTag(config.getLocale());
        this.msg    =   msg;
    }
    public String getMessage(String msgCode){
        return msg.getMessage(msgCode,  null, locale);
    }

    public String getMessage(String msgCode, String... strings){
        return msg.getMessage(msgCode, strings, locale);
    }

}
