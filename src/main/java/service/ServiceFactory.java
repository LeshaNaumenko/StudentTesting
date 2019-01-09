package service;

import dao.factory.DAOFactory;

public class ServiceFactory {
    private static DAOFactory instance = DAOFactory.getInstance(DAOFactory.DBName.MYSQL_DB);

    public static UserService getUserService() {
        return new UserService(instance.getUserDAO());
    }

    public static QuestionService getQuestionService() {
        return new QuestionService(instance.getQuestionDao());
    }

    public static TestService getTestService() {
        return new TestService(instance.getTestDao(), instance.getThemeDAO());
    }

    public static ThemeService getThemeService() {
        return new ThemeService(instance.getThemeDAO());
    }

    public static AnswerService getAnswerService() {
        return new AnswerService();
    }
}
