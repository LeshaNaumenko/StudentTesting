package controller.commands;

import exceptions.ServiceException;
import model.entity.Question;
import model.entity.Theme;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.QuestionService;
import service.TestService;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class GetTestCommandTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ThemeService themeService;
    private QuestionService questionService;
    private LanguageManager languageManager;

    private static final Theme EMPTY_THEME = new Theme(1, null, null, null, null );


    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public List<Question> questionList;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        themeService = mock(ThemeService.class);
        questionService = mock(QuestionService.class);
        languageManager = LanguageManager.INSTANCE;

    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServiceException, ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);
        when(request.getParameter("theme_id")).thenReturn("1");
        when(questionService.getQuestions(anyInt(),any(Locale.class))).thenReturn(questionList);
        when(themeService.getThemeByID(anyInt())).thenReturn(EMPTY_THEME);
        GetTestCommand getTestCommand = new GetTestCommand(themeService, questionService);
        CommandResult execute = getTestCommand.execute(request, response);
        Assert.assertEquals(execute.getPage(), expectedPage);
    }
    @Parameterized.Parameters(name = " url: {0},  questions: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.ERROR_PAGE, null},
                {CommandPages.TAKING_TEST_PAGE, Arrays.asList(new Question(),new Question(), new Question())}
        });
    }
}