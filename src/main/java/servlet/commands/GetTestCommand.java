package servlet.commands;

import exeption.ServiceExeption;
import model.entity.Question;
import model.entity.Theme;
import service.QuestionService;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetTestCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceExeption {
        LanguageManager languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        themeService = new ThemeService();
        questionService = new QuestionService();
        Integer themeId = Integer.parseInt(req.getParameter("theme_id"));
        Theme theme = themeService.getThemeByID(themeId);
        Integer time = theme.getTime();
        LanguageManager langManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        Locale locale = langManager.getLocale();
        List<Question> questionList = questionService.getQuestions(themeId, locale);
        if (questionList != null) {
            req.setAttribute("test_time", time);
            req.getSession().setAttribute("startTime", System.currentTimeMillis());
            req.getSession().setAttribute("listOfQuestion", questionList);
            req.getSession().setAttribute("theme_id_attribute", themeId);
            return CommandResult.forward("WEB-INF/taking_test.jsp");
        } else {
            req.setAttribute("error", languageManager.getMessage("no-questions"));
            return CommandResult.forward("error.jsp");
        }
    }
}
