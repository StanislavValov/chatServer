package com.qaiware.core;

/**
 * Created by Stan on 19.12.2017 г..
 * Those validations could be done with annotations
 */
public class MessageValidator implements Validator {

    @Override
    public boolean isValid(ChatMessage message) {
        if (message == null || message.getValue() == null){
            return false;
        }

        int length = message.getValue().length();

        return !(length < 1 || length > 160);
    }

    @Override
    public boolean isValid(Emotion emotion) {
        if (emotion == null || emotion.getValue() == null){
            return false;
        }

        int length = emotion.getValue().length();

        if (length < 2 || length > 10) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (Character.isDigit(emotion.value.charAt(i))){
                return false;
            }
        }

        return true;
    }
}
