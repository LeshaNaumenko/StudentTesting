package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import service.ServiceFactory;
import service.ThemeService;
import service.UserService;
import utility.EmailValidator;
import utility.LanguageManager;
import utility.NameSurnameValidator;
import utility.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RegisterCommand extends Command {
    private UserService userService = ServiceFactory.getUserService();
    private ThemeService themeService = ServiceFactory.getThemeService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        LanguageManager languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");

        String firstName = req.getParameter("fname");

        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean checkEmail = EmailValidator.checkEmail(email);
        boolean checkPass = PasswordValidator.checkPassword(password);
        boolean checkFirst = NameSurnameValidator.checkNames(firstName);
        boolean checkLast = NameSurnameValidator.checkNames(lastName);

        //setMessages
        if (!checkEmail) {
            req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
        }
        if (!checkPass) {
            req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
        }
        if (!checkFirst) {
            req.setAttribute("errFirstMessage", languageManager.getMessage("fname-incorrect"));
        }
        if (!checkLast) {
            req.setAttribute("errLastMessage", languageManager.getMessage("lname-incorrect"));
        }
        if (!(checkEmail && checkPass && checkFirst && checkLast)) {
            return CommandResult.forward(REGISTRATION_PAGE);

        }
        User user = userService.getUserBy("email", email);
        if (user != null) {
            req.setAttribute("errMessage", languageManager.getMessage("user-exists"));
            return CommandResult.forward(REGISTRATION_PAGE);
        }
        User newUser = userService.registerUser(new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(User.Role.USER)
                .build()
        );
        req.getSession().setAttribute("user", newUser);
//        List<String> courses = themeService.getCourses();
//        req.getSession().setAttribute("course_name_list", courses);
        req.getSession().setAttribute("comm", "GET_COURSES");
        return CommandResult.forward(TEST_PAGE);
    }
}
