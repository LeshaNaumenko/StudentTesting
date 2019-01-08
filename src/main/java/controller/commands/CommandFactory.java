package controller.commands;

import service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> actionMap;

    static {
        actionMap = new HashMap<>();
        actionMap.put("GET_USERS", new GetAllUsersCommand(ServiceFactory.getUserService()));
        actionMap.put("LOGIN_CHECK", new LoginCheckCommand());
        actionMap.put("GET_COURSES", new GetCoursesNamesCommand(ServiceFactory.getThemeService()));
        actionMap.put("GET_LIST_OF_THEMES_BY_COURSES_NAME", new GetThemesByCourseCommand(ServiceFactory.getThemeService()));
        actionMap.put("GET_TEST", new GetTestCommand(ServiceFactory.getThemeService(), ServiceFactory.getQuestionService() ));
        actionMap.put("SAVE_THE_RESULTS", new SaveTheResultCommand());
        actionMap.put("SHOW_RESULTS", new GetStudentResultCommand(ServiceFactory.getUserService(),ServiceFactory.getTestService()));
        actionMap.put("REGISTER", new RegisterCommand());
        actionMap.put("LOGOUT", new LogoutCommand());
        actionMap.put("REGISTER_FORWARD", new RegisterForward());
    }
    public static Command getCommand(String commandFromController) {

        if (actionMap.containsKey(commandFromController))
            return actionMap.get(commandFromController.toUpperCase());
        else
            return new UndefinedCommand();
    }
}

