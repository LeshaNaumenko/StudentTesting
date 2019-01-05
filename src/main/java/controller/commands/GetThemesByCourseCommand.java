package controller.commands;

import exceptions.ServiceException;
import model.entity.Theme;
import service.ServiceFactory;
import service.ThemeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetThemesByCourseCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {

        themeService = ServiceFactory.getThemeService();
        List<Theme> themesByCourse = themeService.getListOfEntityBy("course_name", req.getParameter("course_name"));
        req.getSession().setAttribute("themesByCourse", themesByCourse);
        return CommandResult.forward("WEB-INF/test.jsp");
    }
}
