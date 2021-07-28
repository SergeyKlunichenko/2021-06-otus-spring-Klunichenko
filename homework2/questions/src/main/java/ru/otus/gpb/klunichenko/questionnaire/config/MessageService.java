package ru.otus.gpb.klunichenko.questionnaire.config;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.tools.Logger;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    private final Map<String, String> msgMap =  new HashMap<>();

    public MessageService(){
        msgMap.put("UserNoname", "Noname");
        msgMap.put("UserUnknow", "Unknow");
        msgMap.put("AnswerWrong", "Wrong");
        msgMap.put("AnswerCorrect", "Correct");

    }
    @Logger
    public String getMessage(String key){
        return (String)msgMap.get(key);
    }


}
