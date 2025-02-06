package eryz.task;

import java.io.Serializable;

/**
 * Represents a task in the EryzBot system.
 * A task has a name, a type, and a status (marked or unmarked).
 * It supports marking and unmarking the task as well as printing its details.
 */
public class Task implements Serializable {
    protected String name;    // The description of the task
    protected String type;    // The type of task (e.g., To-Do, Event, Deadline)
    protected boolean isMarked;  // The status of the task, whether it is marked as done or not

    /**
     * Constructor for creating a Task object.
     *
     * @param name The description of the task.
     * @param type The type of the task (e.g., "[T]", "[D]", "[E]").
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isMarked = false;  // By default, the task is unmarked
    }

    /**
     * Returns the name (description) of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Marks the task as done.
     * Prints a message to indicate that the task is marked as done and displays the task details.
     */
    public void mark() {
        System.out.println("This task is marked as done now, yay!");
        this.isMarked = true;
        this.printTask();  // Print the task details after marking it
    }

    /**
     * Unmarks the task, indicating that it is not done.
     * Prints a message to indicate that the task is unmarked and displays the task details.
     */
    public void unmark() {
        System.out.println("This task is not done now, please do it soon!");
        this.isMarked = false;
        this.printTask();  // Print the task details after unmarking it
    }

    /**
     * Prints the details of the task, including its type, mark status, and name.
     * It shows "[X]" if the task is marked and "[ ]" if it is unmarked.
     */
    public void printTask() {
        String mark = isMarked ? "[X]" : "[ ]";  // If the task is marked, display "[X]", otherwise "[ ]"
        System.out.print(type + mark + " " + name);
    }
}
