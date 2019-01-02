package servlet.commands;

import exceptions.ServiceException;
import model.entity.User;
import org.apache.log4j.Logger;
import service.ThemeService;
import service.UserService;
import utility.LanguageManager;
import utility.PasswordSecurity;
import utility.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class LoginCheckCommand extends Command {
    final static Logger logger = Logger.getLogger(LoginCheckCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        userService = new UserService();
        LanguageManager languageManager = (LanguageManager)req.getSession().getAttribute("appLocale");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //validation
        boolean checkEmail = Validator.checkEmail(email);
        boolean checkPassword = Validator.checkPassword(password);

        if (!checkEmail)                  req.setAttribute("errEmailMessage", languageManager.getMessage("example-email"));
        if (!checkPassword)               req.setAttribute("errPassMessage", languageManager.getMessage("password-info"));
        if (!(checkEmail&&checkPassword))
            return CommandResult.forward("login.jsp");

        //if user exists
        User user = userService.getUserBy("email", req.getParameter("email"));
        if (user==null) {
            req.setAttribute("error", languageManager.getMessage("user-not-registered"));
            return CommandResult.forward("login.jsp");
        }
        //verification
        boolean verifyUserPassword = PasswordSecurity.verifyUserPassword(password, user.getPassword(), user.getSalt());
        if (verifyUserPassword){
            logger.info("user["+user.getId()+"] logged in");
            //VERIFY
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("role", user.getRole());
            themeService = new ThemeService();
            List<String> courses = themeService.getCourses();
            req.getSession().setAttribute("course_name_list", courses);
            req.getSession().setAttribute("comm", "GET_COURSES");
            return CommandResult.redirect("test");
        }else {
            //NOT VERIFY
            req.setAttribute("error", languageManager.getMessage("unknown-user")); // Set error msg for ${error}
            return CommandResult.forward("login.jsp");
        }
    }
}
