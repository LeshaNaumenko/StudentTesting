package controller.commands;

import org.apache.log4j.Logger;
import utility.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UndefinedCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(UndefinedCommand.class);
    LanguageManager languageManager;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        languageManager = (LanguageManager) req.getSession().getAttribute("appLocale");
        req.getSession().setAttribute("error", "unknown-command");
        return CommandResult.forward(ERROR_PAGE);
    }
}
