import java.util.ArrayList;
import java.util.Scanner;

public class EryzBot {
    private static ArrayList<Task> tasks = new ArrayList<>();

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
        for (int i = 0; i < tasks.size(); i++) {
            printTask(i + 1);
        }
    }

    public static void mark(int idx){
        tasks.get(idx - 1).mark();
    }

    public static void unmark(int idx){
        tasks.get(idx - 1).unmark();
    }

    public static void echo(Scanner scanner){
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("bye")){
            exit();
        } else if (input.equalsIgnoreCase("list")){
            list();
            System.out.println("__________________________________________________________\n");
            echo(scanner);
        } else if (input.toLowerCase().startsWith("mark")){
            int idx = Integer.parseInt(input.split(" ")[1]);
            mark(idx);
            System.out.println("__________________________________________________________\n");
            echo(scanner);
        } else if (input.toLowerCase().startsWith("unmark")){
            int idx = Integer.parseInt(input.split(" ")[1]);
            unmark(idx);
            System.out.println("__________________________________________________________\n");
            echo(scanner);
        } else if (input.toLowerCase().startsWith("todo")){
            input = input.substring(5);
            Task newTask = new Task(input, "[T]");
            tasks.add(newTask);
            System.out.println("Added this todo :");
            newTask.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("__________________________________________________________\n");
            echo(scanner); 
        } else if (input.toLowerCase().startsWith("deadline")){
            String desc[] = input.substring(9).split(" /by ");
            String name = desc[0];
            String by = desc[1];
            Task newTask = new Task(name, "[D]", by);
            tasks.add(newTask);
            System.out.println("Added this deadline :");
            newTask.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("__________________________________________________________\n");
            echo(scanner); 
        } else if (input.toLowerCase().startsWith("event")){
            String[] desc = input.substring(6).split(" /from ");
            String name = desc[0];
            String[] date = desc[1].split(" /to ");
            String from = date[0];
            String to = date[1];
            Task newTask = new Task(name, "[E]", from, to);
            tasks.add(newTask);
            System.out.println("Added this event :");
            newTask.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("__________________________________________________________\n");
            echo(scanner);  
        } else {
            // Normal tasks & echo
            System.out.println(input);
            Task newTask = new Task(input, "[ ]");
            tasks.add(newTask);
            System.out.println("Added this task :");
            newTask.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("__________________________________________________________\n");
            echo(scanner); 
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        greet();
        echo(scanner);
        scanner.close();
    }
}

