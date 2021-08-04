package ru.otus.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.tools.Logged;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class MessageService {
    private final Map<String, String> msgMap =  new HashMap<>();
    private final Locale locale;
    private final MessageSource msg;
    public MessageService(MessageSource msg,  AppConfig config){

        this.locale = Locale.forLanguageTag(config.getLocale());
        this.msg    =   msg;

        msgMap.put("UserNoname", msg.getMessage("messages.login.UserNoname", null, locale));
        msgMap.put("UserUnknow", msg.getMessage("messages.login.UserUnknow", null, locale));
        msgMap.put("AnswerWrong", msg.getMessage("messages.login.AnswerWrong", null, locale));
        msgMap.put("AnswerCorrect", msg.getMessage("messages.login.AnswerCorrect", null, locale));
        msgMap.put("loginMessage", msg.getMessage("messages.login.loginMessage", null, locale));
        msgMap.put("label.Surname", msg.getMessage("messages.login.label.Surname", null, locale));
        msgMap.put("label.Name", msg.getMessage("messages.login.label.Name", null, locale));


    }
//    @Logged
    public String getMessage(String key){
        return msgMap.get(key);
    }

    public String getMessage(String msgCode, Object[] objects){
        return msg.getMessage(msgCode, objects, locale);
    }

}
