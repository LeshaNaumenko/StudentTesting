package controller.commands;

import exceptions.ServiceException;
import model.entity.*;
import service.AnswerService;
import service.TestService;
import service.ThemeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaveTheResultCommand extends Command {
    private ThemeService themeService;
    private TestService testService;
    private AnswerService answerService;

    public SaveTheResultCommand(ThemeService themeService, TestService testService, AnswerService answerService) {

        this.themeService = themeService;
        this.testService = testService;
        this.answerService = answerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {

        User current = (User) req.getSession().getAttribute("user");
        List<Question> questionList = (List<Question>) req.getSession().getAttribute("listOfQuestion");

        if (!Objects.nonNull(questionList)) return sendRedirectToTest(req);
        Integer themeId = (Integer) req.getSession().getAttribute("theme_id_attribute");
        Date startTime = new Date((long) req.getSession().getAttribute("startTime"));
        Date endTime = new Date(System.currentTimeMillis());

        List<String> parameters = getOptionParameters(req, questionList.size());
        List<Answer> answers = answerService.getAnswers(questionList, parameters);
        int rightAnswers = answerService.getRightAnswers(answers);
        int grade = testService.calculateTheGrade(rightAnswers, questionList.size());
        long duration = testService.getDuration(endTime.getTime(), startTime.getTime());
        long diffSeconds = testService.getTheDifferenceSeconds(duration);
        long diffMinutes = testService.getTheDifferenceMinutes(duration);
        Test.Status status = testService.calculateStatus(grade, themeId);
        Test test = testService.createTest(new Test.Builder()
                .setUserId(current.getId())
                .setThemeId(themeId)
                .setStatus(status)
                .setGrade(grade)
                .setStartTime(setFormatForDate(startTime))
                .setEndTime(setFormatForDate(endTime))
                .setTestTime(diffMinutes + ":" + diffSeconds)
                .setDate(setFormatForDate(startTime))
                .build()
        );
        Theme theme = themeService.getThemeByID(test.getTheme_id());

        setAttribute(req, diffSeconds, diffMinutes, test, theme, answers);
        removeAttribute(req);
        return CommandResult.forward(RESULT_OF_TEST);

    }

    private CommandResult sendRedirectToTest(HttpServletRequest req) {
        req.getSession().setAttribute("comm", "GET_COURSES");
        return CommandResult.forward(TEST_PAGE);
    }

    private List<String> getOptionParameters(HttpServletRequest req, int size) {
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String parameter = req.getParameter("optradio" + (i));
            parameters.add((Objects.nonNull(parameter) ? parameter : ""));
        }
        return parameters;

    }


    private void removeAttribute(HttpServletRequest req) {
        req.getSession().removeAttribute("listOfQuestion");
    }

    private void setAttribute(HttpServletRequest req, long diffSeconds, long diffMinutes, Test test, Theme theme, List<Answer> answers) {
        req.getSession().setAttribute("test", test);
        req.getSession().setAttribute("theme", theme);
        req.getSession().setAttribute("minutes", diffMinutes);
        req.getSession().setAttribute("seconds", diffSeconds);
        req.getSession().setAttribute("answers", answers);

    }

    private String setFormatForDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
