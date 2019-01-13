package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import utility.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheckCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(LoginCheckCommand.class);
    private UserService userService;
    private LanguageManager languageManager;

    public LoginCheckCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    public LoginCheckCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + "  is running");
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean validPassOrEmail = validation(req, email, password);
        if (!validPassOrEmail) {
            LOGGER.warn("Unknown user entered incorrect password or email");
            return CommandResult.forward(LOGIN_PAGE);
        }

        User user = userService.getUserBy("email", req.getParameter("email"));
        if (!existsUser(user, req)){
            LOGGER.warn("Unknown user entered invalid email - "+email);
            return CommandResult.forward(LOGIN_PAGE);
        }

        boolean verifyUserPassword = Encryption.verifyUserPassword(password, user.getHash(), user.getSalt());
        if (verifyUserPassword) {
            LOGGER.info("User ID " + user.getId() + " is logged in.");
            setAttribute(req, user);
            return CommandResult.redirect(TEST_URL);
        } else {
            LOGGER.warn("User ID " + user.getId() + " entered invalid data");
            req.setAttribute("error", languageManager.getMessage("unknown-user")); // Set error msg for ${error}
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private void setAttribute(HttpServletRequest req, User user) {
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("role", user.getRole());
        req.getSession().setAttribute("comm", "GET_COURSES");
    }

    private boolean existsUser(User user, HttpServletRequest req) {
        if (user == null) {
            req.setAttribute("error", languageManager.getMessage("user-not-registered"));
            return false;
        }
        return true;
    }

    /**
     * @param req
     * @param email
     * @param password
     * @return validPassOrEmail. This variable  is used to immediately display all error messages.
     */
    private boolean validation(HttpServletRequest req, String email, String password) {
        boolean validPassOrEmail = true;
        if (!EmailValidator.checkEmail(email)) {
            req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
            validPassOrEmail = false;
        }
        if (!PasswordValidator.checkPassword(password)) {
            req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
            validPassOrEmail = false;
        }
        return validPassOrEmail;
    }
}