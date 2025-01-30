package eryz;

import java.util.ArrayList;

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

    public void exit() {
        System.out.println("Bye! Hope to see you again!");
        System.out.println("__________________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Added this task:");
        task.printTask();
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted() {
        System.out.println("Task deleted!");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from the file.");
    }

    public void showTaskListEmpty() {
        System.out.println("You have no tasks. Yay!");
    }

    public void showSeparator() {
        System.out.println("\n__________________________________________________________\n");
    }

    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask();
        }
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here, tasks that match your keyword:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.print((i + 1) + ".");
                matchingTasks.get(i).printTask();
            }
        }
    }
    
}
