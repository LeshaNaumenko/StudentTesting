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

/**
 * Class {@code RegisterCommand} is used to register a user.
 *
 * @author Alex Naumenko
 * @see Command
 * @see CommandPages
 * @see CommandFactory
 * @see CommandResult
 */
public class RegisterCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetThemesByCourseCommand.class);
    private UserService userService;
    private LanguageManager languageManager;

    public RegisterCommand() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }
    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean validParameters = checkParameters(req, firstName, lastName, email, password);
        if (!validParameters) {
            LOGGER.warn("Unknown user entered incorrect data during registration.");
            return CommandResult.forward(REGISTRATION_PAGE);
        }
        User user = userService.getUserBy("email", email);
        if (existsUser(user, req)) {
            LOGGER.warn("Unknown user attempted to register by existing email - " + email);
            return CommandResult.forward(REGISTRATION_PAGE);
        }
        EncryptionBuilder encryptionBuilder = new EncryptionBuilder(password);
        User newUser = userService.registerUser(new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setHash(encryptionBuilder.getHash())
                .setSalt(encryptionBuilder.getSalt())
                .setRole(User.Role.USER)
                .build()
        );
        req.getSession().setAttribute("user", newUser);
        req.getSession().setAttribute("comm", "GET_COURSES");
        return CommandResult.forward(TEST_PAGE);
    }

    private boolean existsUser(User user, HttpServletRequest req) {
        if (user != null) {
            req.setAttribute("errMessage", languageManager.getMessage("user-exists"));
            return true;
        }
        return false;
    }


    /**
     * Method to check if the user data is valid.
     *
     * @param req {@link HttpServletRequest}
     * @param firstName is user first name
     * @param lastName is user last name
     * @param email is user email
     * @param password is user password
     * @return validParameters. This variable is used to immediately display all error messages.
     */
    private boolean checkParameters(HttpServletRequest req, String firstName, String lastName, String email, String password) {
        boolean validParameters = true;
        if (!EmailValidator.checkEmail(email)) {
            LOGGER.debug("Incorrect email");
            req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
            validParameters = false;
        }
        if (!PasswordValidator.checkPassword(password)) {
            LOGGER.debug("Incorrect password");
            req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
            validParameters = false;
        }
        if (!NameSurnameValidator.checkNames(firstName)) {
            LOGGER.debug("Incorrect first name");
            req.setAttribute("errFirstMessage", languageManager.getMessage("fname-incorrect"));
            validParameters = false;
        }
        if (!NameSurnameValidator.checkNames(lastName)) {
            LOGGER.debug("Incorrect last name");
            req.setAttribute("errLastMessage", languageManager.getMessage("lname-incorrect"));
            validParameters = false;
        }
        return validParameters;
    }
}
