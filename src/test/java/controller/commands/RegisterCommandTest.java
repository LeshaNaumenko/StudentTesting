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
public class RegisterCommandTest {
    private static final User USER = new User.Builder().setId(1).setFirstName("Alex").setLastName("Naumenko")
            .setEmail("naymenko213@gmail.com").setHash("3123532Kkl").setRole(User.Role.USER).build();
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INVALID_PASSWORD = "123";
    private static final String INVALID_EMAIL =  "naumgmail.com";
    private static final String CORRECT_EMAIL =  "naum9009@gmail.com";
    private static final String EXIST_EMAIL =  "naymenko213@gmail.com";
    private static final String INVALID_NAME =  "asasasasasasasasasasas20";
    private static final String CORRECT_NAME =  "Alex._.134";
    private static final String INVALID_SURNAME =  "dfd>";
    private static final String CORRECT_SURNAME =  "Alex._.134";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private LanguageManager languageManager;
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);
        languageManager = LanguageManager.INSTANCE;

    }

    @Parameterized.Parameter()
    public String expectedPage;
    @Parameterized.Parameter(1)
    public User usersInDB;
    @Parameterized.Parameter(2)
    public String nameFromForm;
    @Parameterized.Parameter(3)
    public String surnameFromForm;
    @Parameterized.Parameter(4)
    public String emailFromForm;
    @Parameterized.Parameter(5)
    public String passwordFromForm;



    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, ServiceException, IOException {
        when(session.getAttribute("appLocale")).thenReturn(languageManager);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn(nameFromForm,surnameFromForm,emailFromForm, passwordFromForm);
        when(userService.getUserBy(anyString(), anyString())).thenReturn(usersInDB);
        RegisterCommand command = new RegisterCommand(userService);
        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
    }

    @Parameterized.Parameters(name = " url: {0},  user: {1}, name:{2}, surname:{3}, email: {4}, password: {5}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.REGISTRATION_PAGE, null,INVALID_NAME,INVALID_SURNAME, INVALID_EMAIL, INVALID_PASSWORD},
                {CommandPages.REGISTRATION_PAGE, null,CORRECT_NAME,INVALID_SURNAME, INVALID_EMAIL, INVALID_PASSWORD},
                {CommandPages.REGISTRATION_PAGE, null,CORRECT_NAME,CORRECT_SURNAME, INVALID_EMAIL, INVALID_PASSWORD},
                {CommandPages.REGISTRATION_PAGE, null,CORRECT_NAME,CORRECT_SURNAME, CORRECT_EMAIL, INVALID_PASSWORD},
                {CommandPages.REGISTRATION_PAGE, USER,CORRECT_NAME,CORRECT_SURNAME, CORRECT_EMAIL, CORRECT_PASSWORD},
                {CommandPages.TEST_PAGE,         null,CORRECT_NAME,CORRECT_SURNAME, EXIST_EMAIL  , CORRECT_PASSWORD},
        });
    }
}