package com.qaiware.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stan on 20.12.2017 г..
 */
public class MessageValidatorTest {

    private final MessageValidator validator = new MessageValidator();

    @Test
    public void validChatMessage() throws Exception {
        ChatMessage message = new ChatMessage("test");

        boolean isValid = validator.isValid(message);

        assertTrue(isValid);
    }

    @Test
    public void chatMessageValueIsNull() throws Exception {
        ChatMessage message = new ChatMessage(null);

        boolean isValid = validator.isValid(message);

        assertFalse(isValid);
    }

    @Test
    public void chatMessageIsNull() throws Exception {
        boolean isValid = validator.isValid((ChatMessage) null);

        assertFalse(isValid);
    }

    @Test
    public void chatMessageIsEmpty() throws Exception {
        ChatMessage message = new ChatMessage("");

        boolean isValid = validator.isValid(message);

        assertFalse(isValid);
    }

    @Test
    public void chatMessageIsTooLong() throws Exception {
        String messageValue = "test";

        for (; messageValue.length() < 160; ) {
            messageValue += messageValue;
        }

        ChatMessage message = new ChatMessage(messageValue);

        boolean isValid = validator.isValid(message);

        assertFalse(isValid);
    }

    @Test
    public void validEmotion() throws Exception {
        Emotion emotion = new Emotion("test");

        boolean isValid = validator.isValid(emotion);

        assertTrue(isValid);
    }

    @Test
    public void emotionIsNull() throws Exception {
        boolean isValid = validator.isValid((Emotion) null);

        assertFalse(isValid);
    }

    @Test
    public void emotionValueIsNull() throws Exception {
        Emotion emotion = new Emotion(null);

        boolean isValid = validator.isValid(emotion);

        assertFalse(isValid);
    }

    @Test
    public void emotionValueTooShort() throws Exception {
        Emotion emotion = new Emotion("a");

        boolean isValid = validator.isValid(emotion);

        assertFalse(isValid);
    }

    @Test
    public void emotionValueTooLong() throws Exception {
        Emotion emotion = new Emotion("asadasdaasa");

        boolean isValid = validator.isValid(emotion);

        assertFalse(isValid);
    }

    @Test
    public void emotionContainsDigit() throws Exception {
        Emotion emotion = new Emotion("123");

        boolean isValid = validator.isValid(emotion);

        assertFalse(isValid);
    }
}