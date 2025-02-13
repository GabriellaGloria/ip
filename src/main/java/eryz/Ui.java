package eryz;

import java.util.ArrayList;

import eryz.task.Task;

/**
 * The Ui class handles all the user interface interactions for the EryzBot application.
 * It is responsible for displaying messages, task lists, errors, and other relevant
 * information to the user in a user-friendly format.
 */
public class Ui {

    public void greet() {
        String logo = "   ____             ___       __ \n"
                    + "  / __/_____ _____ / _ )___  / /_\n"
                    + " / _// __/ // /_ // _  / _ \\/ __/\n"
                    + "/___/_/  \\_, //__/____/\\___/\\__/ \n"
                    + "        /___/                    \n";
        System.out.println("__________________________________________________________\n");
        System.out.println("Hello, I am \n" + logo);
        System.out.println("How can I assist you?");
        System.out.println("__________________________________________________________\n");
    }
    
    /**
     * Displays an exit message when the bot is closed.
     * This method is called when the user says "bye".
     */
    public void exit() {
        System.out.println("Bye! Hope to see you again!");
        System.out.println("__________________________________________________________");
    }

    /**
     * Displays an error message.
     * 
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a message indicating that a task has been added.
     * 
     * @param task The task that was added.
     * @param taskCount The total number of tasks after the task is added.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Added this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted.
     */
    public void showTaskDeleted() {
        System.out.println("Task deleted!");
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from the file.");
    }

    /**
     * Displays a message when the task list is empty.
     */
    public void showTaskListEmpty() {
        System.out.println("You have no tasks. Yay!");
    }

    /**
     * Displays a separator line for better UI readability.
     */
    public void showSeparator() {
        System.out.println("\n__________________________________________________________\n");
    }

    /**
     * Displays the list of tasks to the user.
     * 
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).printTask());
        }
    }

    /**
     * Displays the tasks that match the user's search keyword.
     * 
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here, tasks that match your keyword:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(matchingTasks.get(i).printTask());
            }
        }
    }
}