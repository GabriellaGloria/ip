import java.util.ArrayList;
import java.util.Scanner;

public class EryzBot {
    private static ArrayList<String> inputs = new ArrayList<>();
    private static ArrayList<Boolean> marked = new ArrayList<>();

    static void greet(){
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
    static void exit(){
        System.out.println("Bye! Hope to see you again!");
        System.out.println("__________________________________________________________");
    }
    static void printTask(int idx){
        if (marked.get(idx-1)) {
            System.out.println("[X] " + inputs.get(idx - 1));
        } else {
            System.out.println("[ ] " + inputs.get(idx - 1));
        }
    }
    static void list(){
        for (int i = 0; i < inputs.size(); i++) {
            System.out.print(i+1 + ". ");
            printTask(i + 1);
        }
    }
    static void mark(int idx){
        System.out.println("This task is marked as done now, yay!");
        System.out.println("  [X] " + inputs.get(idx - 1));
        marked.set(idx-1, true);
    }
    static void unmark(int idx){
        System.out.println("This task is not done now, please do it soon!");
        System.out.println("  [ ] " + inputs.get(idx - 1));
        marked.set(idx-1, false);
    }
    static void echo(Scanner scanner){
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
        } else {
            System.out.println(input);
            marked.add(false);
            inputs.add(input);
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

