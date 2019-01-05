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
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        userService = ServiceFactory.getUserService();
        LanguageManager languageManager = (LanguageManager)req.getSession().getAttribute("appLocale");

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
            System.out.println("1");
            req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
        }
        if (!checkPass) {
            System.out.println("2");
            req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
        }
        if (!checkFirst) {
            System.out.println("3");
            req.setAttribute("errFirstMessage", languageManager.getMessage("fname-incorrect"));
        }
        if (!checkLast) {
            System.out.println("4");
            req.setAttribute("errLastMessage", languageManager.getMessage("lname-incorrect"));
        }
        if (!(checkEmail && checkPass && checkFirst && checkLast)) {
            System.out.println("5   ");
            return CommandResult.forward("/WEB-INF/registration.jsp");

        }
        User user = userService.getUserBy("email", email);
        if (user != null) {
            System.out.println("6");
            req.setAttribute("errMessage", languageManager.getMessage("user-exists"));
            return CommandResult.forward( "/WEB-INF/registration.jsp");
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
        themeService = ServiceFactory.getThemeService();
        List<String> courses = themeService.getCourses();
        req.getSession().setAttribute("course_name_list", courses);
        req.getSession().setAttribute("comm", "GET_COURSES");
        return CommandResult.forward("WEB-INF/test.jsp");
    }
}
