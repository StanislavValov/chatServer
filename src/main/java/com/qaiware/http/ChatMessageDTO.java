package com.qaiware.http;

/**
 * Created by Stan on 19.12.2017 г..
 */
public class ChatMessageDTO {

//    Its better to validate DTO objects with custom validator handler and annotations
//    @Size(min = 1, max = 160, message = "Message should be between 1 and 160 symbols.")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
