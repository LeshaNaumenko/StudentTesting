package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.ThemeService;
import service.UserService;
import utility.LanguageManager;
import utility.PasswordSecurity;
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
            .setEmail("naymenko213@gmail.com").setPassword("3123532Kkl").setRole(User.Role.USER).build();
    //    private static final User ADMINISTRATOR = new User(2, "Dmytro", "Naumenko", "naum9@gmail.com", "31235Kl32", User.Role.ADMIN);
    private static final User INCORRECT_EMAIL_USER = new User(3, "Yaroslav", "Naumenko", "naum9gmail.com", "312Jj3532", User.Role.USER);
    private static final User WRONG_PASSWORD_USER = new User(4, "Jack", "Naumenko", "naum9@gmail.com", "312332", User.Role.USER);
    private static final User INCORRECT_USERNAME_USER = new User(5, "Lena", "naumenko", "naum9@gmail.com", "3123Jjc532", User.Role.USER);
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INCORRECT_PASSWORD = "1234JKf2";
    private static final String INVALID_PASSWORD = "123";
    private static final String INVALID_EMAIL =  "naum9009gmail.com";
    private static final String CORRECT_EMAIL =  "naymenko213@gmail.com";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private ThemeService themeService;
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
        themeService = mock(ThemeService.class);
        languageManager = LanguageManager.INSTANCE;
    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, ServiceException, IOException {
        when(session.getAttribute("appLocale")).thenReturn(languageManager);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter(anyString())).thenReturn(emailFromForm, passwordFromForm);
        when(userService.getUserBy(anyString(), anyString())).thenReturn(usersInDB);

        LoginCheckCommand command = new LoginCheckCommand(userService, themeService);

        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
    }

    @Parameterized.Parameters(name = " url: {0},  users: {1}")
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