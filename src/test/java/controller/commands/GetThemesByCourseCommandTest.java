package controller.commands;

import exceptions.ServiceException;
import model.entity.Theme;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class GetThemesByCourseCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ThemeService themeService;
    private LanguageManager languageManager;


    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public List<Theme> themesByCourse;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        themeService = mock(ThemeService.class);
        languageManager = LanguageManager.INSTANCE;
    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServiceException, ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);
        when(themeService.getThemesByCourse(anyString())).thenReturn(themesByCourse);
        GetThemesByCourseCommand command = new GetThemesByCourseCommand(themeService);
        CommandResult execute = command.execute(request, response);
        Assert.assertEquals(execute.getPage(), expectedPage);

    }
    @Parameterized.Parameters(name = " url: {0},  questions: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.TEST_PAGE, null},
                {CommandPages.TEST_PAGE, Arrays.asList(new Theme(),new Theme(), new Theme())}
        });
    }




}