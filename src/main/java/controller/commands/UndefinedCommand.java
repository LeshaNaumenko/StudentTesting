package controller.commands;

import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UndefinedCommand extends Command {
    LanguageManager languageManager;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        req.getSession().setAttribute("error", "unknown-command");
        return CommandResult.forward(ERROR_PAGE);
    }
}
