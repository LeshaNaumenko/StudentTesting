package controller.commands;

/**
 * Class @{@code CommandResult } represents the result of executing the commands.
 *
 * @author Alex Naumenko
 */
public class CommandResult {
    private final boolean redirect;
    private final String page;

    private CommandResult(String page, boolean redirect) {
        this.redirect = redirect;
        this.page = page;
    }

    /**
     * Return command result with the forward type.
     *
     * @param page is the path to jsp.
     * @return command result with the forward type.
     */
    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    /**
     * Return command result with the redirect type.
     *
     * @param page is the path to jsp.
     * @return command result with the redirect type.
     */
    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    /**
     * Returns the path to jsp page.
     *
     * @return the path to jsp page.
     */
    public String getPage() {
        return page;
    }

    /**
     * Returns the type of redirection.
     *
     * @return true if a page has the redirect type and false if the page has the forward type.
     */
    public boolean isRedirect() {
        return redirect;
    }
}
