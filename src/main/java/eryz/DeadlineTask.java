package eryz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. Inherits from the Task class and includes a deadline
 * represented by a LocalDate object.
 *
 * A DeadlineTask stores a task's name and a deadline, and provides functionality to
 * create, display, and manage tasks that have a specific due date.
 */
public class DeadlineTask extends Task {
    private LocalDate by;

    public DeadlineTask(String name, LocalDate by) {
        super(name, "[D]");
        this.by = by;
    }

    public static Task deadlineTaskCreate(String input) {
        try {
            String[] desc = input.substring(9).split(" /by ", 2);
            String name = desc[0];
            String by = desc[1];

            if (name.isEmpty() || by.isEmpty()) {
                throw new EryzBotException("The description or deadline cannot be empty.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(by, formatter);

            return new DeadlineTask(name, deadline);
        } catch (Exception e) {
            throw new EryzBotException("Invalid format. Use: deadline <description> /by <yyyy-MM-dd>");
        }
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
