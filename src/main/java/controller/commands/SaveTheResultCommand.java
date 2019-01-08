package controller.commands;

import exceptions.ServiceException;
import model.entity.Question;
import model.entity.Test;
import model.entity.Theme;
import model.entity.User;
import service.ServiceFactory;
import service.TestService;
import service.ThemeService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveTheResultCommand extends Command {
    private ThemeService themeService = ServiceFactory.getThemeService();
    private TestService testService = ServiceFactory.getTestService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {

        User current = (User) req.getSession().getAttribute("user");
        Integer size = (Integer) req.getSession().getAttribute("sizeOfListOfQuestion");
        Integer themeId = (Integer) req.getSession().getAttribute("theme_id_attribute");
        Date startTime = new Date((long) req.getSession().getAttribute("startTime"));
        Date endTime = new Date(System.currentTimeMillis());
        List<Question> questionList = (List<Question>) req.getSession().getAttribute("listOfQuestion");

        //correct answers
        int trueCounter = 0;
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getCorrect_option().equals(req.getParameter("optradio" + (i)))) {
                trueCounter++;
            }
        }
        int grade = testService.calculateTheGrade(trueCounter, size);

        //calculate date
        long duration  = endTime.getTime() - startTime.getTime();
        long diffSeconds = duration / 1000 % 60;
        long diffMinutes = duration / (60 * 1000) % 60;

        Test test = testService.createTest(current.getId(),themeId,grade, setFormatForDate(startTime), setFormatForDate(endTime),diffMinutes+":"+diffSeconds, setFormatForDate(startTime));
        Theme theme = themeService.getThemeByID(test.getTheme_id());

        setAttribute(req, diffSeconds, diffMinutes, test, theme);
        removeAttribute(req);
        return CommandResult.forward(RESULT_OF_TEST);

    }

    private void removeAttribute(HttpServletRequest req) {
        req.getSession().removeAttribute("startTime");
        req.getSession().removeAttribute("endTime");
    }

    private void setAttribute(HttpServletRequest req, long diffSeconds, long diffMinutes, Test test, Theme theme) {
        req.setAttribute("test", test);
        req.setAttribute("theme", theme);
        req.setAttribute("minutes", diffMinutes);
        req.setAttribute("seconds", diffSeconds);
    }

    private String setFormatForDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
