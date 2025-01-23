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

    public static void echo(Scanner scanner) throws EryzBotException {
        try {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                return;
            } else if (input.equalsIgnoreCase("list")) {
                list();
            } else if (input.toLowerCase().startsWith("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                mark(idx);
            } else if (input.toLowerCase().startsWith("unmark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                unmark(idx);
            } else if (input.toLowerCase().startsWith("todo")) {
                Task newTask = TodoTask.TodoTaskCreate(input);
                tasks.add(newTask);
                System.out.println("Added this todo :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.toLowerCase().startsWith("deadline")) {
                Task newTask = DeadlineTask.DeadlineTaskCreate(input);
                tasks.add(newTask);
                System.out.println("Added this deadline :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.toLowerCase().startsWith("event")) {
                Task newTask = EventTask.EventTaskCreate(input);
                tasks.add(newTask);
                System.out.println("Added this event :");
                newTask.printTask();
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new EryzBotException("I don't recognize that task. Please input todo/deadline/event only!");
            }
            System.out.println("__________________________________________________________\n");
            echo(scanner); 
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
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

