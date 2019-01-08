package controller.commands;

import exceptions.ServiceException;
import model.entity.TestDTO;
import model.entity.User;
import service.TestService;
import service.UserService;
import utility.LanguageManager;
import utility.Pagination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetStudentResultCommand extends Command {
    private UserService userService ;
    private TestService testService ;
    private LanguageManager languageManager;
    public static final int RECORDS_ON_THE_PAGE = 5;



    public GetStudentResultCommand(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");

        User student;
        int currentPage;
        Pagination pagination;
        String student_id = req.getParameter("student_id");
        String currentPageStr = req.getParameter("currentPage");

        checkStudentIdParam(req, student_id);
        student = (User) req.getSession().getAttribute("student");

        checkCurrentPageStr(req, currentPageStr);
        currentPage = (int) req.getSession().getAttribute("currentPage");

        pagination = new Pagination(RECORDS_ON_THE_PAGE, currentPage);
        List<TestDTO> testDTOList = testService.getResultsById(student.getId(), pagination.calculateStart(), RECORDS_ON_THE_PAGE);
        if (testDTOList == null||testDTOList.isEmpty()) {
            return sendError(req);
        }
        int rows = testService.getNumberOfRows(student.getId());
        setAttribute(req, pagination, testDTOList, rows);
        return CommandResult.forward(RESULTS_PAGE);
    }

    private CommandResult sendError(HttpServletRequest req) {
        req.getSession().setAttribute("error", languageManager.getMessage("no-test"));
        return CommandResult.forward(ERROR_PAGE);
    }

    private void checkCurrentPageStr(HttpServletRequest req, String currentPageStr) {
        if (currentPageStr != null) {
            req.getSession().setAttribute("currentPage", Integer.valueOf(currentPageStr));
        }
    }

    private void checkStudentIdParam(HttpServletRequest req, String student_id) throws ServiceException {
        if (student_id != null) {
            req.getSession().setAttribute("student", userService.getUserBy("id", student_id));
        }
    }

    private void setAttribute(HttpServletRequest req, Pagination pagination, List<TestDTO> testDTOList, int rows) {
        req.setAttribute("noOfPages", pagination.calculateNumOfPages(rows));
        req.getSession().setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("recordsPerPage", pagination.getRecordsPerPage());
        req.setAttribute("testDTOList", testDTOList);
    }

    public UserService getUserService() {
        return userService;
    }

    public TestService getTestService() {
        return testService;
    }
}
