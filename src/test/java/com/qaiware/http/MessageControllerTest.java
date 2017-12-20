package com.qaiware.http;

import com.qaiware.core.ChatMessage;
import com.qaiware.core.Emotion;
import com.qaiware.core.Validator;
import com.qaiware.persistence.MessageRepository;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.qaiware.utils.matchers.EqualityMatchers.deepEquals;
import static com.qaiware.utils.matchers.EqualityMatchers.matching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

/**
 * Created by Stan on 20.12.2017 г..
 */
public class MessageControllerTest {

    private MessageController controller;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private Validator validator;

    @Mock
    private MessageRepository repository;

    @Before
    public void setUp() throws Exception {
        controller = new MessageController(validator, repository);
    }

    @Test
    public void sendTextMessage() throws Exception {
        ChatMessageDTO messageDTO = new ChatMessageDTO();
        ChatMessage message = adapt(messageDTO);

        context.checking(new Expectations() {{
                oneOf(validator).isValid(with(matching(message)));
                will(returnValue(true));

                oneOf(repository).insert(with(matching(message)));
            }
        });

        ModelAndView modelAndView = controller.sendText(messageDTO);
        ModelAndView expected = new ModelAndView("", CREATED);

        assertThat(modelAndView, is(deepEquals(expected)));
    }

    @Test
    public void messageValidationFailed() throws Exception {
        ChatMessageDTO messageDTO = new ChatMessageDTO();
        ChatMessage message = adapt(messageDTO);

        context.checking(new Expectations() {{
            oneOf(validator).isValid(with(matching(message)));
            will(returnValue(false));
        }
        });

        ModelAndView modelAndView = controller.sendText(messageDTO);
        ModelAndView expected = new ModelAndView("", PRECONDITION_FAILED);

        assertThat(modelAndView, is(deepEquals(expected)));
    }

    @Test
    public void sendEmotion() throws Exception {
        EmotionDTO emotionDTO = new EmotionDTO();
        Emotion emotion = adapt(emotionDTO);

        context.checking(new Expectations() {{
            oneOf(validator).isValid(with(matching(emotion)));
            will(returnValue(true));

            oneOf(repository).insert(with(matching(emotion)));
        }
        });

        ModelAndView modelAndView = controller.sendEmotion(emotionDTO);
        ModelAndView expected = new ModelAndView("", CREATED);

        assertThat(modelAndView, is(deepEquals(expected)));
    }

    @Test
    public void emotionValidationFailed() throws Exception {
        EmotionDTO emotionDTO = new EmotionDTO();
        Emotion emotion = adapt(emotionDTO);

        context.checking(new Expectations() {{
            oneOf(validator).isValid(with(matching(emotion)));
            will(returnValue(false));
        }
        });

        ModelAndView modelAndView = controller.sendEmotion(emotionDTO);
        ModelAndView expected = new ModelAndView("", PRECONDITION_FAILED);

        assertThat(modelAndView, is(deepEquals(expected)));
    }

    private Emotion adapt(EmotionDTO emotionDTO) {
        return new Emotion(emotionDTO.getValue());
    }

    private ChatMessage adapt(ChatMessageDTO dto) {
        return new ChatMessage(dto.getValue());
    }
}