package service;

import model.entity.Answer;
import model.entity.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AnswerServiceTest {

    private AnswerService answerService;

    private static List<Question> QUESTIONS_FROM_DB = Arrays.asList(
            new Question(1, 1, "descriptionOfQuestion", "option1", "option2", "option3", "option4", "option2"),
            new Question(2, 1, "descriptionOfQuestion", "option1", "option2", "option3", "option4", "option4"),
            new Question(3, 1, "descriptionOfQuestion", "option1", "option2", "option3", "option4", "option4"),
            new Question(4, 1, "descriptionOfQuestion", "option1", "option2", "option3", "option4", "option1")
    );
    private static List<String> USER_ANSWERS_FROM_FORM = Arrays.asList("option2", "option4", "option3", "option3");
    private static int EXPECTED_NUMB_OF_CORRECT_ANSWERS = 2;

    @Before
    public void setUp() {
        answerService = ServiceFactory.getInstance().getAnswerService();
    }

    @Test
    public void getAnswers() {
        List<Answer> answers = answerService.getAnswers(QUESTIONS_FROM_DB, USER_ANSWERS_FROM_FORM);
        assertNotNull(answers);
    }

    @Test
    public void getRightAnswers() {
        List<Answer> answers = answerService.getAnswers(QUESTIONS_FROM_DB, USER_ANSWERS_FROM_FORM);

        int rightAnswers = answerService.getRightAnswers(answers);

        assertEquals(EXPECTED_NUMB_OF_CORRECT_ANSWERS, rightAnswers );
    }
}