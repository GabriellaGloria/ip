package eryz.exception;

/**
 * Custom exception class for the EryzBot application.
 * This exception is thrown when there is an error specific to the bot's operations,
 * such as invalid user input, task-related issues, or any other EryzBot-specific error.
 */
public class EryzBotException extends RuntimeException {
    public EryzBotException(String message) {
        super(message);
    }
}
