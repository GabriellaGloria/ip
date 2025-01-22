import java.util.ArrayList;
import java.util.Scanner;

public class EryzBot {
    private static ArrayList<String> inputs = new ArrayList<>();
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
    static void list(){
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(i+1 + ". " + inputs.get(i));
        }
    }
    static void echo(Scanner scanner){
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("bye")){
            exit();
        } else if (input.equalsIgnoreCase("list")){
            list();
            System.out.println("__________________________________________________________\n");
            echo(scanner);
        } else {
            System.out.println(input);
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

