package controller.commands;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static controller.commands.CommandPages.LOGIN_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogoutCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    public static String expectedPage = LOGIN_PAGE;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

    }

    @Test
    public void deleteSession() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);

        LogoutCommand command = new LogoutCommand();
        CommandResult execute = command.execute(request, response);

        assertEquals(execute.getPage(), expectedPage);
        verify(session, times(1)).invalidate();
    }
}