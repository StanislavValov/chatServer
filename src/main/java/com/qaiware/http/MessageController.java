package com.qaiware.http;

import com.qaiware.core.ChatMessage;
import com.qaiware.core.Emotion;
import com.qaiware.core.Validator;
import com.qaiware.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Stan on 19.12.2017 г..
 */
@Controller
@RequestMapping(path = "/messages")
public class MessageController {

    private Validator messageValidator;
    private MessageRepository repository;

    @Autowired
    public MessageController(Validator messageValidator, MessageRepository repository) {
        this.messageValidator = messageValidator;
        this.repository = repository;
    }

    @RequestMapping(path = "/send_text", method = POST, consumes = "application/json")
    public ModelAndView sendText(@RequestBody ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = adapt(chatMessageDTO);

        boolean isValid = messageValidator.isValid(chatMessage);

        if(!isValid){
            return new ModelAndView("", PRECONDITION_FAILED);
        }

        repository.insert(chatMessage);

        return new ModelAndView("", CREATED);
    }

    @RequestMapping(path = "/send_emotion", method = POST, consumes = "application/json")
    public ModelAndView sendEmotion(@RequestBody EmotionDTO emotionDTO) {
        Emotion emotion = adapt(emotionDTO);

        //Validation and all duplicated logic below can be extracted.
        boolean isValid = messageValidator.isValid(emotion);

        if(!isValid){
            return new ModelAndView("", PRECONDITION_FAILED);
        }

        repository.insert(emotion);

        return new ModelAndView("", CREATED);
    }

    private Emotion adapt(EmotionDTO emotionDTO) {
        return new Emotion(emotionDTO.getValue());
    }

    private ChatMessage adapt(ChatMessageDTO dto) {
        return new ChatMessage(dto.getValue());
    }
}