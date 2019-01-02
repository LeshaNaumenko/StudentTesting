package servlet.commands;

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
        List<TestDTO> testDTOList = testService.getResultsById(currentUser.getId());

        req.getSession().setAttribute("student", currentUser);
        req.setAttribute("testDTOList", testDTOList);

        return CommandResult.forward("WEB-INF/results.jsp");
    }
}
