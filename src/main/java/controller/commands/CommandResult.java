package controller.commands;

public class CommandResult {
    private final boolean redirect;
    private final String page;

    private CommandResult(String page, boolean redirect) {
        this.redirect = redirect;
        this.page = page;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
