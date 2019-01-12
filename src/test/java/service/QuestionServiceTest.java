package service;

import dao.IQuestionDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.entity.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceTest {
    private IQuestionDAO<Question, Integer> questionDao;
    private QuestionService questionService;

    @Before
    public void setUp() {
        questionDao = (IQuestionDAO<Question, Integer>) mock(IQuestionDAO.class);
        questionService = new QuestionService(questionDao);
    }

    @Test()
    public void getQuestionsTest() throws DAOException, ServiceException {
        when(questionDao.getListOfQuestionWithLocale(anyInt(), any(Locale.class))).thenReturn(Arrays.asList(new Question(), new Question()));
        List<Question> questions = questionService.getQuestions(1, Locale.getDefault());
        assertNotNull(questions);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionsTest() throws DAOException, ServiceException {
        when(questionDao.getListOfQuestionWithLocale(anyInt(), any(Locale.class))).thenThrow(DAOException.class);
        questionService.getQuestions(1, Locale.getDefault());
    }


}