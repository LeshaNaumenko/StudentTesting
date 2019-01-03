package controller;

import exceptions.ServiceException;
import controller.commands.CommandFactory;
import controller.commands.CommandResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/test"})
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strCommand = req.getParameter("command");
        if (strCommand != null) req.getSession().setAttribute("comm", strCommand); //for language
        String sessionCommand = (String) req.getSession().getAttribute("comm");
        CommandResult page = null;
        try {
            page = CommandFactory.getCommand(sessionCommand).execute(req, resp);
        } catch (ServiceException serviceException) {
            page = CommandResult.forward("WEB-INF/error.jsp");
        }
        dispatch(req, resp, page);
    }

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
