package controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(GetThemesByCourseCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        req.getSession().invalidate();
        return CommandResult.redirect(LOGIN_PAGE);

    }
}
