package controller.commands;

import exceptions.ServiceException;
import model.entity.User;
import service.UserService;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUsersCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        userService = new UserService();

        LanguageManager languageManager = (LanguageManager) request.getSession().getAttribute("appLocale");
        List<User> allUsers = userService.getAllUsers();
        request.getSession().setAttribute("allUser", allUsers);
        return CommandResult.forward("WEB-INF/accounts.jsp");
    }
}
