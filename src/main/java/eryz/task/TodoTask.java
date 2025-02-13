package eryz.task;

import eryz.exception.EryzBotException;

/**
 * Represents a "To-Do" task in the EryzBot system.
 * This class extends the Task class and represents a task of type "To-Do",
 * denoted by the prefix "[T]".
 */
public class TodoTask extends Task {

    /**
     * Constructor for a To-Do task.
     * 
     * @param input The description of the task.
     */
    public TodoTask(String input) {
        super(input, "[T]");  // Call the parent constructor with the task description and type "[T]"
    }

    /**
     * Static method to create a To-Do task from user input.
     * The method expects the input to start with "todo" followed by the task description.
     * 
     * @param input The full user input for creating a To-Do task (should start with "todo").
     * @return A new TodoTask instance.
     * @throws EryzBotException If the input is invalid or the task description is empty.
     */
    public static Task todoTaskCreate(String input) {
        assert input != null : "Input should not be null";

        if (input.length() <= 5) {
            throw new EryzBotException("Todo task can't be empty!");  // Ensure the task description is not empty
        }
        input = input.substring(5);  // Remove the "todo" prefix from the input
        return new TodoTask(input);  // Return a new TodoTask with the task description
    }

    /**
     * Prints the details of the To-Do task.
     * It calls the parent method to print the task and then adds a line break for better readability.
     */
    @Override
    public void printTask() {
        super.printTask();  // Print the task using the parent class's printTask method
        System.out.println("");  // Print a newline for readability
    }
}
