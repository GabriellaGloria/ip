package eryz;

import java.util.ArrayList;

import eryz.task.Task;

/**
 * Represents a list of tasks in the EryzBot system.
 * This class provides methods to add, delete, mark, unmark tasks,
 * and retrieve the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the task list to an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     * 
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     * 
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     * The index is 1-based, so the first task is at index 1.
     * 
     * @param idx The index of the task to be deleted.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public void deleteTask(int idx) {
        tasks.remove(idx - 1);
    }

    /**
     * Marks a task as done by its index.
     * The index is 1-based, so the first task is at index 1.
     * 
     * @param idx The index of the task to be marked.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public void markTask(int idx) {
        tasks.get(idx - 1).mark();
    }

    /**
     * Unmarks a task as undone by its index.
     * The index is 1-based, so the first task is at index 1.
     * 
     * @param idx The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public void unmarkTask(int idx) {
        tasks.get(idx - 1).unmark();
    }

    /**
     * Returns the number of tasks in the task list.
     * 
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks as an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks in the task list that contain the given keyword.
     * The search is case-sensitive and matches if the task's name contains the keyword.
     * 
     * @param keyword The keyword to search for in task names.
     * @return A list of tasks that contain the given keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> retTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                retTasks.add(task);
            }
        }
        return retTasks;
    }
}
