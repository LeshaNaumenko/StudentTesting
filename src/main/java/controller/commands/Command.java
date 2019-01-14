package controller.commands;

import exceptions.ServiceException;
import service.QuestionService;
import service.TestService;
import service.ThemeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to process a request received from {@link controller.TestController}
 *
 * @author Alex Naumenko
 */
public abstract class Command implements CommandPages {

    /**
     * Default constructor.
     */
    public Command() {
    }

    /**
     * Executes the request.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServletException if any inner exception in servlet occurs.
     * @throws IOException      if I/O error occurs.
     * @throws ServiceException if any service exception in servlet occurs.
     */
    public abstract CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException;
}
