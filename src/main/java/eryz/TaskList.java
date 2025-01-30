package eryz;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the EryzBot system.
 * This class provides methods to add, delete, mark, unmark tasks,
 * and retrieve the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int idx) {
        tasks.remove(idx - 1);
    }

    public void markTask(int idx) {
        tasks.get(idx - 1).mark();
    }

    public void unmarkTask(int idx) {
        tasks.get(idx - 1).unmark();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
