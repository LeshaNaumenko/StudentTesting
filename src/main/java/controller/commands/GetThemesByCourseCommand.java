package controller.commands;

import exceptions.ServiceException;
import model.entity.Theme;
import service.ServiceFactory;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetThemesByCourseCommand extends Command {
    private ThemeService themeService;
    private LanguageManager languageManager;

    public GetThemesByCourseCommand(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");

            String course_name = req.getParameter("course_name");
        List<Theme> themesByCourse = themeService.getThemesByCourse(req.getParameter("course_name"));
        if (themesByCourse != null||themesByCourse.isEmpty())
            req.getSession().setAttribute("themesByCourse", themesByCourse);
        else
            req.setAttribute("error", languageManager.getMessage("no-tests"));

        return CommandResult.forward(TEST_PAGE);
    }
}
