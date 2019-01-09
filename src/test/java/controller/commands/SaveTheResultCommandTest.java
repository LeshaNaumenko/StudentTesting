package controller.commands;

import exceptions.ServiceException;
import model.entity.Question;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.AnswerService;
import service.TestService;
import service.ThemeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class SaveTheResultCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private TestService testService;
    private AnswerService answerService;
    private ThemeService themeService;

    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public List<Question> questions;


    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        testService = mock(TestService.class);
        answerService = mock(AnswerService.class);
        themeService = mock(ThemeService.class);
    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, ServiceException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("listOfQuestion")).thenReturn(questions);
        when(session.getAttribute("startTime")).thenReturn(System.currentTimeMillis());
        when(session.getAttribute("user")).thenReturn(new User());
        when(testService.getTheDifferenceMinutes(5)).thenReturn(10L);
        when(testService.createTest(any(model.entity.Test.class))).thenReturn(new model.entity.Test.Builder().build());

        SaveTheResultCommand command = new SaveTheResultCommand(themeService, testService, answerService);
        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
    }


    @Parameterized.Parameters(name = " url: {0}, number of questions: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.TEST_PAGE, null},
                {CommandPages.RESULT_OF_TEST, Arrays.asList(new Question(), new Question())}
        });
    }
}