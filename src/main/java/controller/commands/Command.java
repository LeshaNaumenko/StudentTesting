package controller.commands;

import exceptions.ServiceException;
import service.QuestionService;
import service.TestService;
import service.ThemeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command implements CommandPages {
    public Command() {

    }
    public abstract CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException;
}
