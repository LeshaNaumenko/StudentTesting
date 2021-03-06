package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class {@code GetAllUsersCommand} is used to get all users.
 *
 * @author Alex Naumenko
 * @see Command
 * @see CommandPages
 * @see CommandFactory
 * @see CommandResult
 */
public class GetAllUsersCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetAllUsersCommand.class);
    private UserService userService;
    private LanguageManager languageManager;

    public GetAllUsersCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    public GetAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + "  is running");
        languageManager = (LanguageManager) request.getSession().getAttribute("appLocale");

        List<User> allUsers = userService.getAllUsers();
        if (allUsers == null) {
            LOGGER.warn("No users");
            return sendError(request);
        }
        request.getSession().setAttribute("allUser", allUsers);
        return CommandResult.forward(ACCOUNT_PAGE);

    }

    private CommandResult sendError(HttpServletRequest request) {
        request.getSession().setAttribute("error", languageManager.getMessage("no-user"));
        return CommandResult.forward(ERROR_PAGE);
    }

}
