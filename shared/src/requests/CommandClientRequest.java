package requests;

import command.logic.CommandDescription;
import requests.annotations.Authorize;

@Authorize
public class CommandClientRequest extends BaseRequest {
    private final CommandDescription command;
    private final String[] lineArgs;

    public CommandClientRequest(CommandDescription command, String[] lineArgs) {
        this.command = command;
        this.lineArgs = lineArgs;
    }

    public CommandDescription getCommandDescription() {
        return command;
    }

    public String[] getLineArgs() {
        return lineArgs;
    }
}
