package command.logic.external.reciever;

import command.logic.CommandDescription;
import command.logic.CommandDescriptionHolder;
import command.logic.CommandExecutor;
import command.logic.CommandMode;
import command.logic.reciever.receiver.ExternalBaseReceiver;
import exception.CommandsNotLoadedException;
import exception.ScriptRecursionException;
import exception.WrongAmountOfArgumentsException;
import model.handler.Utilities;
import response.logic.ApplicationResponseProvider;
import response.CommandStatusResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Reads and executes script from file.
 *
 * @author Zerumi
 * @since 1.0
 */
public class ExecuteScriptReceiver implements ExternalBaseReceiver {
    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    private final ApplicationResponseProvider<CommandStatusResponse>[] providers;

    @SafeVarargs
    public ExecuteScriptReceiver(ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
    }

    @Override
    public boolean receiveCommand(CommandDescription commandDescription, String[] args) throws IllegalArgumentException, WrongAmountOfArgumentsException {

        if (!Objects.equals(commandDescription.getName(), "execute_script")) return true;

        Utilities.checkArgumentsOrThrow(args.length, 1);

        try {
            CommandExecutor executor = new CommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), new FileInputStream(Path.of(args[1]).toFile()), CommandMode.NonUserMode, providers);
            if (checkRecursion(Path.of(args[1]), new ArrayDeque<>())) {
                Arrays.stream(providers).forEach(x -> x.acceptException(new ScriptRecursionException()));
                myLogger.log(Level.WARNING, "При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return false;
            }
            myLogger.log(Level.INFO, "Executing script " + args[1]);
            executor.startExecuting();
        } catch (InvalidPathException e) {
            System.out.println("Provided argument path isn't legal. Try again.");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("File not found! Try again.");
        } catch (SecurityException e) {
            System.out.println("Access denied. Try again with another file.");
        } catch (IOException e) {
            System.out.println("Something went wrong during file handling. Try again. (" + e.getMessage() + ")");
        } catch (CommandsNotLoadedException e) {
            System.out.println("We've got a really interesting situation... Commands is gone...");
        }
        return false;
    }

    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find()) {
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
            if (checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }
}
