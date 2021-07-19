package ru.otus.gpb.klunichenko.questionnaire.config;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class Messages {
    private Map<String, String> msgMap =  new HashMap();

    public Messages(){
        msgMap.put("UserNoname", "Noname");
        msgMap.put("UserUnknow", "Unknow");
        msgMap.put("AnswerWrong", "Wrong");
        msgMap.put("AnswerCorrect", "Correct");

    }

    public String getMessage(String key){
        return (String)msgMap.get(key);
    }


}
