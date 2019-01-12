package service;

public class ServiceFactory {

    private static volatile ServiceFactory serviceFactory ;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        if (serviceFactory == null){
            synchronized (ServiceFactory.class){
                if (serviceFactory == null){
                    serviceFactory = new ServiceFactory();
                }
            }
        }
        return serviceFactory;
    }

    public UserService getUserService() {
        return new UserService();
    }

    public QuestionService getQuestionService() {
        return new QuestionService();
    }

    public TestService getTestService() {
        return new TestService();
    }

    public ThemeService getThemeService() {
        return new ThemeService();
    }

    public AnswerService getAnswerService() {
        return new AnswerService();
    }
}
