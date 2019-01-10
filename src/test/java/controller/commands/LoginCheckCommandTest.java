package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.UserService;
import utility.LanguageManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LoginCheckCommandTest {

    private static final User USER = new User.Builder().setId(1).setFirstName("Alex").setLastName("Naumenko")
            .setEmail("naymenko213@gmail.com").setHash("3123532Kkl").setRole(User.Role.USER).build();
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INCORRECT_PASSWORD = "1234JK2a";
    private static final String INVALID_PASSWORD = "123";
    private static final String INVALID_EMAIL =  "naum9009gmail.com";
    private static final String CORRECT_EMAIL =  "naymenko213@gmail.com";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private LanguageManager languageManager;

    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public User usersInDB;
    @Parameterized.Parameter(2)
    public String emailFromForm;
    @Parameterized.Parameter(3)
    public String passwordFromForm;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);
        languageManager = LanguageManager.INSTANCE;
    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, ServiceException, IOException {
        when(session.getAttribute("appLocale")).thenReturn(languageManager);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter(anyString())).thenReturn(emailFromForm, passwordFromForm);
        when(userService.getUserBy(anyString(), anyString())).thenReturn(usersInDB);

        LoginCheckCommand command = new LoginCheckCommand(userService);

        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
    }

    @Parameterized.Parameters(name = " url: {0},  user: {1}, email: {2}, password: {3}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.LOGIN_PAGE, USER, CORRECT_EMAIL, INVALID_PASSWORD},
                {CommandPages.LOGIN_PAGE, USER, CORRECT_EMAIL, INCORRECT_PASSWORD},
                {CommandPages.LOGIN_PAGE, USER, INVALID_EMAIL, CORRECT_PASSWORD},
                {CommandPages.LOGIN_PAGE, null, CORRECT_EMAIL, CORRECT_PASSWORD},
                {CommandPages.TEST_URL, USER, CORRECT_EMAIL, CORRECT_PASSWORD},
        });
    }

}