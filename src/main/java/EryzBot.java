import java.util.Scanner;

public class EryzBot {
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
    static void echo(Scanner scanner){
        String input = scanner.nextLine();
        System.out.println("__________________________________________________________");
        if (input.equalsIgnoreCase("bye")){
            exit();
        } else {
            System.out.println(input);
            System.out.println("__________________________________________________________");
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

