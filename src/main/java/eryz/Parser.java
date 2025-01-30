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
