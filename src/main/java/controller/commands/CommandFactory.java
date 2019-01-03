package controller.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> actionMap;

    static {
        actionMap = new HashMap<>();
        actionMap.put("GET_USERS", new GetAllUsersCommand());
        actionMap.put("LOGIN_CHECK", new LoginCheckCommand());
        actionMap.put("GET_COURSES", new GetCoursesNamesCommand());
        actionMap.put("GET_LIST_OF_THEMES_BY_COURSES_NAME", new GetThemesByCourseCommand());
        actionMap.put("GET_TEST", new GetTestCommand());
        actionMap.put("SAVE_THE_RESULTS", new SaveTheResultCommand());
        actionMap.put("SHOW_RESULTS", new GetStudentResultCommand());
        actionMap.put("REGISTER", new RegisterCommand());
        actionMap.put("LOGOUT", new LogoutCommand());
        actionMap.put("CURRENT_USER_RESULT", new GetCurrentUserResultCommand());
        actionMap.put("REGISTER_FORWARD", new RegisterForward());
    }
    public static Command getCommand(String commandFromController) {

        if (actionMap.containsKey(commandFromController))
            return actionMap.get(commandFromController.toUpperCase());
        else
            return new UndefinedCommand();
    }
}

