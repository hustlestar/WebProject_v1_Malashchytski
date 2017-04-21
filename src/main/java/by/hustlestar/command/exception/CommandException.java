package by.hustlestar.command.exception;

/**
 * CommandException is thrown when some error happens
 * during command processing.
 */
public class CommandException extends Exception {
    public CommandException(String s) {
        super(s);
    }

    public CommandException() {
    }
}
