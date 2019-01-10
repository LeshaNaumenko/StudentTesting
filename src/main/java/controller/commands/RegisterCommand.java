package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import service.UserService;
import utility.EmailValidator;
import utility.LanguageManager;
import utility.NameSurnameValidator;
import utility.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand extends Command {
    private UserService userService;
    LanguageManager languageManager;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");

        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean validParameters = checkParameters(req, firstName, lastName, email, password);
        if (!validParameters) return CommandResult.forward(REGISTRATION_PAGE);
        User user = userService.getUserBy("email", email);
        if (existsUser(user, req)) return CommandResult.forward(REGISTRATION_PAGE);
        User newUser = userService.registerUser(new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
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
     * Returns variable is used to immediately display all error messages.
     *
     * @param req
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return validParameters.
     */
    private boolean checkParameters(HttpServletRequest req, String firstName, String lastName, String email, String password) {
        boolean validParameters = true;
        if (!EmailValidator.checkEmail(email)) {
            req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
            validParameters = false;
        }
        if (!PasswordValidator.checkPassword(password)) {
            req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
            validParameters = false;
        }
        if (!NameSurnameValidator.checkNames(firstName)) {
            req.setAttribute("errFirstMessage", languageManager.getMessage("fname-incorrect"));
            validParameters = false;
        }
        if (!NameSurnameValidator.checkNames(lastName)) {
            req.setAttribute("errLastMessage", languageManager.getMessage("lname-incorrect"));
            validParameters = false;
        }
        return validParameters;
    }
}
