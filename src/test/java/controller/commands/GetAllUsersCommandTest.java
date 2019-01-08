package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.junit.Assert;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class GetAllUsersCommandTest {

    private GetAllUsersCommand command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private LanguageManager languageManager;

    @Parameterized.Parameter()
    public String expected;
    @Parameterized.Parameter(1)
    public List<User> users;

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
        command = new GetAllUsersCommand(userService);

        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("appLocale")).thenReturn(languageManager);
        when(command.getUserService().getAllUsers()).thenReturn(users);

        CommandResult execute = command.execute(request, response);

        Assert.assertEquals(execute.getPage(), expected);
        verify(request.getSession(), times(1)).setAttribute(anyString(), any());
    }

    @Parameterized.Parameters(name = " url: {0},  users: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.ERROR_PAGE, null},
                {CommandPages.ACCOUNT_PAGE, Arrays.asList(new User(), new User())}
        });
    }
}
