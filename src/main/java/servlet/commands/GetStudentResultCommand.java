package servlet.commands;

import exceptions.ServiceException;
import model.entity.TestDTO;
import model.entity.User;
import service.TestService;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetStudentResultCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {

        testService = new TestService();
        userService = new UserService();
        User student;
        String student_id = req.getParameter("student_id");
        int currentPage = Integer.valueOf(req.getParameter("currentPage"));//!!!!!!!!!!!!
        int recordsPerPage = 5;
        int start = currentPage * recordsPerPage - recordsPerPage;
        //for more info about student
        if (student_id != null) {
            student= userService.getUserBy("id",student_id );
            req.getSession().setAttribute("student", student);
        }
        student=(User)req.getSession().getAttribute("student");

        List<TestDTO> testDTOList = testService.getResultsById(student.getId(), start, recordsPerPage);
        int rows = testService.getNumberOfRows(student.getId());

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("testDTOList", testDTOList);
        return CommandResult.forward("WEB-INF/results.jsp");
    }
}
