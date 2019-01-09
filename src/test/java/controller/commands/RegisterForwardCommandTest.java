package controller.commands;

import org.junit.Before;
import org.junit.Test;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.commands.CommandPages.REGISTRATION_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegisterForwardCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void shouldReturnCorrectRedirect() throws ServletException, IOException {
        RegisterForwardCommand command = new RegisterForwardCommand();
        CommandResult execute = command.execute(request, response);
        assertEquals(execute.getPage(), REGISTRATION_PAGE);
        verify(request, never()).getSession();
    }
}