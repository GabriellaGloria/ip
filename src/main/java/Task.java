import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private String type;
    private boolean isMarked;
    private String by;   
    private String from; 
    private String to;  

    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isMarked = false;
    }

    public Task(String name, String type, String by) {
        this(name + " (by: " + by + ")", type);
        this.by = by;
    }

    public Task(String name, String type, String from, String to) {
        this(name + "(from: " + from + " to: " + to + ")", type);
        this.from = from;
        this.to = to;
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

    public void printTask(){
        String mark = isMarked ? "[X]" : "[ ]";
        System.out.print(type + mark + " " + name);
    }
}