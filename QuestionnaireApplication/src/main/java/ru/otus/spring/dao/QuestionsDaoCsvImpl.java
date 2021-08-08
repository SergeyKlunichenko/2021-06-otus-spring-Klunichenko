package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.QuestionaireException;
import ru.otus.spring.tools.IOService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoCsvImpl implements QuestionsDao {

    private final AppConfig config;
    private final MessageService messageService;
    private final IOService ioService;
    public QuestionsDaoCsvImpl(AppConfig config, MessageService messageService, IOService ioService){
        this.config = config;
        this.messageService = messageService;
        this.ioService = ioService;
    }


    public List<Question> getAll() throws QuestionaireException {
        List<Question> rows = new ArrayList<>();
        String fileName = config.getFileQuest();
        //String locale = config.getLocale().trim();

//System.out.println("Открытие файла с вопросником:"+fileName);
        ioService.println(messageService.getMessage("messages.questions.openfile", fileName));

        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if(resource == null){
                throw new QuestionaireException(messageService.getMessage("messages.questions.fileNotFound", fileName));
            }

            Reader reader = new InputStreamReader(resource);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(config.getDelim());
                rows.add(new Question(fields[0], fields[1], fields[2]));
            }
        } catch (IOException e){
            throw new QuestionaireException(e);
        } catch (Exception ex) {
            throw new QuestionaireException(ex);
        }

        return rows;
    }
}
