package controller.commands;

import service.ThemeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCoursesNamesCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        themeService = new ThemeService();
        List<String> courses = themeService.getCourses();
        req.getSession().setAttribute("course_name_list", courses);
        return CommandResult.forward("WEB-INF/test.jsp");
    }
}
