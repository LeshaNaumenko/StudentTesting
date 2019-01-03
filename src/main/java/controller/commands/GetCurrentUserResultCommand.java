package controller.commands;

import exceptions.ServiceException;
import model.entity.TestDTO;
import model.entity.User;
import service.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCurrentUserResultCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        User currentUser = (User) req.getSession().getAttribute("user");
        testService = new TestService();
        int currentPage = Integer.valueOf(req.getParameter("currentPage"));//!!!!!!!!!!!!
        int recordsPerPage = 5;
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<TestDTO> testDTOList = testService.getResultsById(currentUser.getId(), start, recordsPerPage);
        int rows = testService.getNumberOfRows(currentUser.getId());

        req.getSession().setAttribute("student", currentUser);
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("testDTOList", testDTOList);
        req.setAttribute("command", req);

        return CommandResult.forward("WEB-INF/results.jsp");
    }
}
