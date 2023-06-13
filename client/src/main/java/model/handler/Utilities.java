package model.handler;

import exception.StreamInterruptedException;
import exception.WrongAmountOfArgumentsException;

import java.util.Scanner;

public class Utilities {
    public static void checkArgumentsOrThrow(int provided, int expected) throws WrongAmountOfArgumentsException {
        if (provided - 1 != expected)
            throw new WrongAmountOfArgumentsException("Provided " + (provided - 1) + " arguments, expected " + expected);
    }

    @SuppressWarnings("SameReturnValue")
    public static boolean hasNextLineOrThrow(Scanner scanner) throws StreamInterruptedException {
        if (scanner.hasNextLine()) return true;
        else throw new StreamInterruptedException("Поток ввода был преждевременно остановлен.");
    }
}
