package controller.commands;

import org.junit.Before;
import org.junit.Test;
import service.UserService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.commands.CommandPages.ERROR_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UndefinedCommandTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;


    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        UndefinedCommand command = new UndefinedCommand();
        CommandResult execute = command.execute(request, response);
        assertEquals(execute.getPage(), ERROR_PAGE);
    }
}