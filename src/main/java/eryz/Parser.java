package eryz;

import eryz.exception.EryzBotException;
import eryz.task.DeadlineTask;
import eryz.task.EventTask;
import eryz.task.Task;
import eryz.task.TodoTask;

/**
 * A utility class for parsing user input into appropriate task objects.
 * It provides methods to parse task descriptions and indices from user commands.
 */
public class Parser {

    /**
     * Parses the index from the user input.
     * The index is expected to be the second part of the input after a space.
     * 
     * @param input The user input containing the task index.
     * @return The parsed index as an integer.
     * @throws EryzBotException if the input does not contain a valid index.
     */
    public static int parseIndex(String input) throws EryzBotException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EryzBotException("Invalid index.");
        }
    }

    /**
     * Parses the keyword for finding tasks from the user input.
     * The keyword should be the second part of the input.
     * 
     * @param input The user input containing the search keyword.
     * @return The parsed keyword.
     * @throws EryzBotException if no keyword is provided.
     */
    public static String parseFind(String input) throws EryzBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EryzBotException("Provide your keyword to find tasks.");
        }
        return parts[1].trim();
    }

    /**
     * Parses the user input to create a corresponding task object.
     * The input should begin with either "todo", "deadline", or "event".
     * 
     * @param input The user input containing the task description.
     * @return The created task object (TodoTask, DeadlineTask, or EventTask).
     * @throws EryzBotException if the input does not match any recognized task type.
     */
    public static Task parseTask(String input) throws EryzBotException {
        if (input.toLowerCase().startsWith("todo")) {
            return TodoTask.todoTaskCreate(input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return DeadlineTask.deadlineTaskCreate(input);
        } else if (input.toLowerCase().startsWith("event")) {
            return EventTask.eventTaskCreate(input);
        } else {
            throw new EryzBotException("I don't even know that task. Please input valid todo/deadline/event only!");
        }
    }
}
