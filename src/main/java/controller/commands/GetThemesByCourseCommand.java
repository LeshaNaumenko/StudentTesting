package controller.commands;

import exceptions.ServiceException;
import model.entity.Theme;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetThemesByCourseCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetThemesByCourseCommand.class);
    private ThemeService themeService;
    private LanguageManager languageManager;

    public GetThemesByCourseCommand() {
        this.themeService = ServiceFactory.getInstance().getThemeService();
    }

    public GetThemesByCourseCommand(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        List<Theme> themesByCourse = themeService.getThemesByCourse(req.getParameter("course_name"));
        if (themesByCourse == null || themesByCourse.isEmpty()) {
            LOGGER.warn("No tests");
            req.setAttribute("error", languageManager.getMessage("no-tests"));
        } else
            req.getSession().setAttribute("themesByCourse", themesByCourse);

        return CommandResult.forward(TEST_PAGE);
    }
}
