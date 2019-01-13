package controller.commands;

import exceptions.ServiceException;
import model.entity.Question;
import model.entity.Theme;
import org.apache.log4j.Logger;
import service.QuestionService;
import service.ServiceFactory;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetTestCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetTestCommand.class);
    private ThemeService themeService ;
    private QuestionService questionService ;
    private LanguageManager langManager;

    public GetTestCommand() {
        this.themeService = ServiceFactory.getInstance().getThemeService();
        this.questionService = ServiceFactory.getInstance().getQuestionService();
    }

    public GetTestCommand(ThemeService themeService, QuestionService questionService) {
        this.themeService = themeService;
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + "  is running");
        langManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        Integer themeId = Integer.parseInt(req.getParameter("theme_id"));
        Theme theme = themeService.getThemeByID(themeId);
        Integer time = theme.getTime();
        Locale locale = langManager.getLocale();
        List<Question> questionList = questionService.getQuestions(themeId, locale);
        if (questionList == null||questionList.isEmpty()) {
            LOGGER.warn("No questions");
            return sendError(req, langManager);
        } else {
            setAttribute(req, themeId, time, questionList);
            return CommandResult.forward(TAKING_TEST_PAGE);
        }
    }

    private CommandResult sendError(HttpServletRequest req, LanguageManager langManager) {
        req.getSession().setAttribute("error", langManager.getMessage("no-questions"));
        return CommandResult.forward(ERROR_PAGE);
    }

    private void setAttribute(HttpServletRequest req, Integer themeId, Integer time, List<Question> questionList) {
        req.setAttribute("user_time", time);
        req.getSession().setAttribute("startTime", System.currentTimeMillis());
        req.getSession().setAttribute("listOfQuestion", questionList);
        req.getSession().setAttribute("theme_id_attribute", themeId);
    }
}
