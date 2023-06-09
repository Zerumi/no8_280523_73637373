package command.logic;

import command.logic.external.reciever.ArgumentRouteCommandReceiver;
import command.logic.external.reciever.ExecuteScriptReceiver;
import command.logic.external.reciever.ExitReceiver;
import command.logic.external.reciever.NonArgumentReceiver;
import command.logic.reciever.ReceiverManager;
import command.logic.reciever.enam.ReceiverType;
import command.logic.reciever.handler.ArgumentReceiverHandler;
import command.logic.reciever.handler.NonArgReceiversHandler;
import exception.*;
import model.Route;
import model.handler.ModuleHandler;
import model.handler.mode.cli.RouteCLIHandler;
import model.handler.mode.gui.GUIRouteHandler;
import model.handler.mode.stream.RouteNonCLIHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;
import response.logic.ApplicationResponseProvider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Class for executing commands. Provides different inputs for command executing.
 */
public class CommandExecutor {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    private final ArrayList<CommandDescription> commands;
    private final Scanner scannerInput;
    private final ReceiverManager manager;

    /**
     * Constructor :/
     *
     * @param commands array of commands
     * @param input    commands stream (File, System.in, e.t.c.)
     * @param mode     variant of command behavior (see CommandMode enum)
     */
    @SafeVarargs
    public CommandExecutor(ArrayList<CommandDescription> commands, InputStream input, CommandMode mode, ApplicationResponseProvider<CommandStatusResponse>... providers) throws CommandsNotLoadedException {
        if (commands == null) throw new CommandsNotLoadedException();

        this.commands = commands;
        this.scannerInput = new Scanner(Optional.ofNullable(input).orElse(new ByteArrayInputStream(new byte[0])));
        manager = new ReceiverManager();

        manager.registerHandler(ReceiverType.NoArgs, new NonArgReceiversHandler());
        manager.registerHandler(ReceiverType.ArgumentRoute, new ArgumentReceiverHandler<>(Route.class));

        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentReceiver(providers));
        manager.registerReceiver(ReceiverType.NoArgs, new ExecuteScriptReceiver(providers));
        manager.registerReceiver(ReceiverType.NoArgs, new ExitReceiver());

        ModuleHandler<Route> handler = null;
        switch (mode) {
            case CLI_UserMode -> handler = new RouteCLIHandler();
            case NonUserMode -> handler = new RouteNonCLIHandler(scannerInput);
            case GUIMode -> handler = new GUIRouteHandler();
        }
        manager.registerReceiver(ReceiverType.ArgumentRoute, new ArgumentRouteCommandReceiver(handler, providers));
    }

    /**
     * Start executing commands from InputStream.
     */
    public void startExecuting() {
        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            if (line.isEmpty()) continue;
            executeSingleCommand(line);
        }
    }

    public void executeSingleCommand(String line) throws CommandInterruptedException {
        try {
            String[] lineArgs = line.split(" ");
            CommandDescription description = Optional.ofNullable(commands).orElseThrow(CommandsNotLoadedException::new).stream().filter(x -> x.getName().equals(lineArgs[0])).findAny().orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена"));
            description.getReceiver().callReceivers(manager, description, lineArgs);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.warn("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            throw new CommandInterruptedException(e);
        } catch (BuildObjectException | UnknownCommandException e) {
            logger.error(e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (WrongAmountOfArgumentsException e) {
            logger.error("Wrong amount of arguments! " + e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (Exception e) {
            logger.error("В командном менеджере произошла непредвиденная ошибка! " + e.getMessage());
            throw new CommandInterruptedException(e);
        }
    }
}