package controller;

import exceptions.ServiceException;
import controller.commands.CommandFactory;
import controller.commands.CommandResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code TestController} is the controller in MVC pattern that controls
 * the data flow into model object and updates view whenever data changes.
 *
 * @author Alex Naumenko
 */

@WebServlet(urlPatterns = {"/test"})
public class TestController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(TestController.class);

    private static final String COMMAND = "command";

    /**
     * Used to control switching between languages.
     */
    private static final String COMMAND_FOR_SESSION = "comm";

    /**
     * Process http GET-requests. Redirect to {@link TestController#process(HttpServletRequest, HttpServletResponse)}.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Process http POST-requests. Redirect to {@link TestController#process(HttpServletRequest, HttpServletResponse)}.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Process http requests.
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing request...");
        String strCommand = req.getParameter(COMMAND);
        if (strCommand != null) req.getSession().setAttribute(COMMAND_FOR_SESSION, strCommand); //for language
        String sessionCommand = (String) req.getSession().getAttribute(COMMAND_FOR_SESSION);
        CommandResult page;
        CommandFactory commandFactory = CommandFactory.getInstance();
        try {
            page = commandFactory.getCommand(sessionCommand).execute(req, resp);
        } catch (ServiceException serviceException) {
            page = CommandResult.forward("WEB-INF/error.jsp");
            LOGGER.error(serviceException);
        }
        dispatch(req, resp, page);
    }

    /**
     * The method determines which type of redirection to use.
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     * @param page to dispatch
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();
        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + pageToDispatch);
        } else {
            req.getRequestDispatcher(pageToDispatch).forward(req, resp);
        }
    }
}
