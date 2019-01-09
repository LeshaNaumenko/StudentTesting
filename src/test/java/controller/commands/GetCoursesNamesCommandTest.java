package controller.commands;

import exceptions.ServiceException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class GetCoursesNamesCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ThemeService themeService;
    private LanguageManager languageManager;

    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public List<String> courseNames;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        themeService = mock(ThemeService.class);
        languageManager = LanguageManager.INSTANCE;

    }
    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, IOException, ServiceException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);
        when(themeService.getCourses()).thenReturn(courseNames);

        GetCoursesNamesCommand command = new GetCoursesNamesCommand(themeService);
        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
        verify(request.getSession(), times(1)).setAttribute(anyString(), any());
    }

    @Parameterized.Parameters(name = " url: {0},  course names: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.ERROR_PAGE, null},
                {CommandPages.TEST_PAGE, Arrays.asList("Java", "C++", "Scala")}
        });
    }
}