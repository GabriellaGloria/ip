package eryz;

import java.io.Serializable;


/**
 * Represents a task in the EryzBot system.
 * A task has a name, a type, and a status (marked or unmarked).
 * It supports marking and unmarking the task as well as printing its details.
 */
public class Task implements Serializable {
    protected String name;
    protected String type;
    protected boolean isMarked;

    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isMarked = false;
    }

    public void mark() {
        System.out.println("This task is marked as done now, yay!");
        this.isMarked = true;
        this.printTask();
    }

    public void unmark() {
        System.out.println("This task is not done now, please do it soon!");
        this.isMarked = false;
        this.printTask();
    }

    public void printTask() {
        String mark = isMarked ? "[X]" : "[ ]";
        System.out.print(type + mark + " " + name);
    }
}
