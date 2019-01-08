package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.apache.log4j.Logger;
import service.ThemeService;
import service.UserService;
import utility.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginCheckCommand extends Command {
    final static Logger logger = Logger.getLogger(LoginCheckCommand.class);
    private UserService userService;
    private ThemeService themeService;
    LanguageManager languageManager;

    public LoginCheckCommand(UserService userService, ThemeService themeService) {
        this.userService = userService;
        this.themeService = themeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean validPassOrEmail = validation(req, email, password);
        if (!validPassOrEmail) return CommandResult.forward(LOGIN_PAGE);

        User user = userService.getUserBy("email", req.getParameter("email"));
        if (!existsUser(user, req))return CommandResult.forward(LOGIN_PAGE);

        boolean verifyUserPassword = PasswordSecurity.verifyUserPassword(password, user.getPassword(), user.getSalt());
        if (verifyUserPassword) {
            logger.info("user[" + user.getId() + "] logged in");
            setAttribute(req, user);
            return CommandResult.redirect(TEST_URL);
        } else {
            req.setAttribute("error", languageManager.getMessage("unknown-user")); // Set error msg for ${error}
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private void setAttribute(HttpServletRequest req, User user) {
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("role", user.getRole());
//        List<String> courses = themeService.getCourses();
//        req.getSession().setAttribute("course_name_list", courses);
        req.getSession().setAttribute("comm", "GET_COURSES");
    }

    private boolean existsUser(User user, HttpServletRequest req) {
        if (user == null) {
            req.setAttribute("error", languageManager.getMessage("user-not-registered"));
            return false;
        }
        return true;
    }

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