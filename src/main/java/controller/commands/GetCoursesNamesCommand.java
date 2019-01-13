package controller.commands;

import exceptions.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCoursesNamesCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetCoursesNamesCommand.class);
    private ThemeService themeService;
    private LanguageManager languageManager;

    public GetCoursesNamesCommand() {
        this.themeService = ServiceFactory.getInstance().getThemeService();
    }

    public GetCoursesNamesCommand(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        List<String> courses = themeService.getCourses();
        if (courses == null) {
            LOGGER.warn("No courses");
            return sendError(req);
        }
        req.getSession().setAttribute("course_name_list", courses);
        return CommandResult.forward(TEST_PAGE);
    }

    private CommandResult sendError(HttpServletRequest req) {
        req.getSession().setAttribute("error", languageManager.getMessage("no-courses"));
        return CommandResult.forward(ERROR_PAGE);
    }
}
