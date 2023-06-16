package request;

import command.logic.CommandDescription;

import java.util.Locale;

public class LocalizedCommandClientRequest extends CommandClientRequest {

    private final CommandClientRequest request;

    private final Locale locale;

    public LocalizedCommandClientRequest(CommandClientRequest request, Locale locale) {
        super(request.getCommandDescription(), request.getLineArgs());
        this.request = request;
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public CommandClientRequest getRequest() {
        return request;
    }
}
