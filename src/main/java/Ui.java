import java.util.ArrayList;

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
}
