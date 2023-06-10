package command.logic;

import command.logic.external.recievers.ArgumentRouteCommandReceiver;
import command.logic.external.recievers.ExecuteScriptReceiver;
import command.logic.external.recievers.ExitReceiver;
import command.logic.external.recievers.NonArgumentReceiver;
import command.logic.reciever.ReceiverManager;
import command.logic.reciever.enums.ReceiverType;
import command.logic.reciever.handlers.ArgumentReceiverHandler;
import command.logic.reciever.handlers.NonArgReceiversHandler;
import exceptions.*;
import models.Route;
import models.handlers.ModuleHandler;
import models.handlers.mode.cli.RouteCLIHandler;
import models.handlers.mode.stream.RouteNonCLIHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static command.logic.CommandMode.CLI_UserMode;

/**
 * Class for executing commands. Provides different inputs for command executing.
 */
public class CommandExecutor {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");

    private final ArrayList<CommandDescription> commands;
    private final Scanner scannerInput;
    private final CommandMode mode;
    private final ReceiverManager manager;

    /**
     * Constructor :/
     *
     * @param commands array of commands
     * @param input    commands stream (File, System.in, e.t.c.)
     * @param mode     variant of command behavior (see CommandMode enum)
     */
    public CommandExecutor(ArrayList<CommandDescription> commands, InputStream input, CommandMode mode) throws CommandsNotLoadedException {
        if (commands == null) throw new CommandsNotLoadedException();

        this.commands = commands;
        this.scannerInput = new Scanner(input);
        this.mode = mode;
        manager = new ReceiverManager();

        manager.registerHandler(ReceiverType.NoArgs, new NonArgReceiversHandler());
        manager.registerHandler(ReceiverType.ArgumentRoute, new ArgumentReceiverHandler<>(Route.class));

        manager.registerReceiver(ReceiverType.NoArgs, new NonArgumentReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExecuteScriptReceiver());
        manager.registerReceiver(ReceiverType.NoArgs, new ExitReceiver());

        ModuleHandler<Route> handler = null;
        switch (mode) {
            case CLI_UserMode -> handler = new RouteCLIHandler();
            case NonUserMode -> handler = new RouteNonCLIHandler(scannerInput);
        }
        manager.registerReceiver(ReceiverType.ArgumentRoute, new ArgumentRouteCommandReceiver(handler));
    }

    /**
     * Start executing commands from InputStream.
     */
    public void startExecuting() {
        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            if (line.isEmpty()) continue;
            try {
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
            } catch (CommandInterruptedException ex) {
                if (mode.equals(CLI_UserMode))
                    logger.info("Выполнение команды было прервано. Вы можете продолжать работу. Программа возвращена в безопасное состояние.");
                else
                    logger.info("Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}