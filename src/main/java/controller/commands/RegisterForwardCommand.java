package controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code RegisterForwardCommand} is used to forward to the registration page.
 *
 * @author Alex Naumenko
 * @see Command
 * @see CommandPages
 * @see CommandFactory
 * @see CommandResult
 */
public class RegisterForwardCommand extends Command {

    private final static Logger LOGGER = Logger.getLogger(RegisterForwardCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)  {
        LOGGER.info(this.getClass().getSimpleName() + " is running");
        return CommandResult.forward(REGISTRATION_PAGE);
    }
}
