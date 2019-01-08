package service;

public class ServiceFactory {
    public static UserService getUserService () {
        return new UserService();
    }
    public static QuestionService getQuestionService () {
        return new QuestionService();
    }
    public static TestService getTestService () {
        return new TestService();
    }
    public static ThemeService getThemeService () {
        return new ThemeService();
    }
    public static AnswerService getAnswerService () {
        return new AnswerService();
    }
}
