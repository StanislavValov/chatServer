package com.qaiware.core;

/**
 * Created by Stan on 19.12.2017 г..
 */
public interface Validator {
    boolean isValid(ChatMessage message);

    boolean isValid(Emotion emotion);
}
