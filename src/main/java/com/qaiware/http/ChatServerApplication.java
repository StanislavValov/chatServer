package com.qaiware.http;

import com.qaiware.core.MessageValidator;
import com.qaiware.core.Validator;
import com.qaiware.persistence.MessageRepository;
import com.qaiware.persistence.PersistentMessageRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class, args);
    }

    @Bean
    public Validator validator() {
        return new MessageValidator();
    }

    @Bean
    public MessageRepository repository() {
        return new PersistentMessageRepository();
    }
}
