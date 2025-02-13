package eryz.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import eryz.exception.EryzBotException;

/**
 * Represents a task with a deadline. Inherits from the Task class and includes a deadline
 * represented by a LocalDate object.
 *
 * A DeadlineTask stores a task's name and a deadline, and provides functionality to
 * create, display, and manage tasks that have a specific due date.
 */
public class DeadlineTask extends Task {
    private LocalDate by;  // The deadline date of the task

    /**
     * Constructor for creating a DeadlineTask.
     *
     * @param name The description of the task.
     * @param by The deadline date for the task.
     */
    public DeadlineTask(String name, LocalDate by) {
        super(name, "[D]");  // Call the parent constructor with the task description and type "[D]"
        this.by = by;
    }

    /**
     * Static method to create a DeadlineTask from user input.
     * The method expects the input to start with "deadline" followed by the task description
     * and a deadline in the format: "/by yyyy-MM-dd".
     *
     * @param input The full user input for creating a DeadlineTask (should start with "deadline").
     * @return A new DeadlineTask instance.
     * @throws EryzBotException If the input format is invalid or any part of the input is empty.
     */
    public static Task deadlineTaskCreate(String input) {
        try {
            // Extract the task description and deadline from the input
            String[] desc = input.substring(9).split(" /by ", 2);
            String name = desc[0];
            String by = desc[1];

            // Ensure neither the task description nor the deadline is empty
            if (name.isEmpty() || by.isEmpty()) {
                throw new EryzBotException("The description or deadline cannot be empty.");
            }

            // Parse the deadline using the specified format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(by, formatter);

            return new DeadlineTask(name, deadline);  // Return a new DeadlineTask instance
        } catch (Exception e) {
            throw new EryzBotException("Invalid format. Use: deadline <description> /by <yyyy-MM-dd>");
        }
    }

    /**
     * Prints the details of the DeadlineTask.
     * It calls the parent method to print the task description, followed by the formatted deadline.
     */
    @Override
    public String printTask() {
        return super.printTask() + (" (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
