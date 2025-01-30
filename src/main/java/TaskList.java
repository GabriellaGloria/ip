import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this(new ArrayList<>());
    }
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    void add(Task task) {
        tasks.add(task);
    }
    void mark(int index) {
        tasks.get(index).mark();
    }
    void unmark(int index) {
        tasks.get(index).unmark();
    }
    void printTask(int idx){
        tasks.get(idx - 1).printTask();
    }
    void list(){
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks. Yay!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                printTask(i + 1);
            }
        }
    }
}