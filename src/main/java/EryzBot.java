import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EryzBot {
    private static final String DATAPATH = "../data/eryz.txt";
    private static ArrayList<Task> tasks = fetchTasks(DATAPATH);

    @SuppressWarnings("unchecked")
    static ArrayList<Task> fetchTasks(String filepath) throws EryzBotException {
        File file = new File(filepath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (var fin = new FileInputStream(filepath); var ois = new ObjectInputStream(fin)) {
            return (ArrayList<Task>) ois.readObject();
        } catch (Exception e) {
            throw new EryzBotException("Couldn't fetch tasks.");
        }
    }
    
    static void saveTasks(String filepath, ArrayList<Task> newTasks) throws EryzBotException {
        File file = new File(filepath);
        file.getParentFile().mkdirs();

        try (var fout = new FileOutputStream(filepath); var oos = new ObjectOutputStream(fout)) {
            oos.writeObject(newTasks);
        } catch (IOException e) {
            throw new EryzBotException("Couldn't save tasks.");
        }
    }

    public static void greet(){
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

    public static void exit(){
        System.out.println("Bye! Hope to see you again!");
        System.out.println("__________________________________________________________");
    }

    public static void printTask(int idx){
        tasks.get(idx - 1).printTask();
    }

    public static void list(){
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks. Yay!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                printTask(i + 1);
            }
        }
    }

    public static void mark(int idx){
        tasks.get(idx - 1).mark();
        saveTasks(DATAPATH, tasks);
    }

    public static void delete(int idx){
        tasks.remove(idx - 1);
        saveTasks(DATAPATH, tasks);
    }

    public static void unmark(int idx){
        tasks.get(idx - 1).unmark();
        saveTasks(DATAPATH, tasks);
    }

    private static int parseIndex(String input) throws EryzBotException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EryzBotException("Invalid index.");
        }
    }

    public static void echo(Scanner scanner) throws EryzBotException {
        String input = scanner.nextLine();
        try {
            if (input.equalsIgnoreCase("bye")) {
                exit();
                return;
            } else if (input.equalsIgnoreCase("list")) {
                list();
            } else if (input.toLowerCase().startsWith("mark")) {
                int idx = parseIndex(input);
                mark(idx);
            } else if (input.toLowerCase().startsWith("delete")) {
                int idx = parseIndex(input);
                delete(idx);
                System.out.println("Task deleted!");
            } else if (input.toLowerCase().startsWith("unmark")) {
                int idx = parseIndex(input);
                unmark(idx);
            } else if (input.toLowerCase().startsWith("todo")) {
                Task newTask = TodoTask.TodoTaskCreate(input);
                tasks.add(newTask);
                saveTasks(DATAPATH, tasks);
                System.out.println("Added this todo :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.toLowerCase().startsWith("deadline")) {
                Task newTask = DeadlineTask.DeadlineTaskCreate(input);
                tasks.add(newTask);
                saveTasks(DATAPATH, tasks);
                System.out.println("Added this deadline :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.toLowerCase().startsWith("event")) {
                Task newTask = EventTask.EventTaskCreate(input);
                tasks.add(newTask);
                saveTasks(DATAPATH, tasks);
                System.out.println("Added this event :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new EryzBotException("I don't recognize that task. Please input todo/deadline/event only!");
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("__________________________________________________________\n");
        echo(scanner);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        greet();
        try {
            echo(scanner);
        } catch (EryzBotException e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
