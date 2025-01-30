package eryz;

/**
 * A utility class for parsing user input into appropriate task objects.
 * It provides methods to parse task descriptions and indices from user commands.
 */
public class Parser {

    public static int parseIndex(String input) throws EryzBotException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EryzBotException("Invalid index.");
        }
    }

    public static String parseFind(String input) throws EryzBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EryzBotException("Provide your keyword to find tasks.");
        }
        return parts[1].trim();
    }
    
    public static Task parseTask(String input) throws EryzBotException {
        if (input.toLowerCase().startsWith("todo")) {
            return TodoTask.todoTaskCreate(input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return DeadlineTask.deadlineTaskCreate(input);
        } else if (input.toLowerCase().startsWith("event")) {
            return EventTask.eventTaskCreate(input);
        } else {
            throw new EryzBotException("I don't recognize that task. Please input todo/deadline/event only!");
        }
    }
}
