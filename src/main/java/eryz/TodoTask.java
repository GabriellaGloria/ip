package eryz;

/**
 * Represents a "To-Do" task in the EryzBot system.
 * The task type is denoted by the prefix "[T]".
 */
public class TodoTask extends Task {

    public TodoTask(String input) {
        super(input, "[T]");
    }

    static Task todoTaskCreate(String input) {
        if (input.length() <= 5) {
            throw new EryzBotException("Todo task can't be empty!");
        }
        input = input.substring(5);
        return new TodoTask(input);
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println("");
    }
}
