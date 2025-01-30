package eryz;

public class Parser {

    public static int parseIndex(String input) throws EryzBotException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EryzBotException("Invalid index.");
        }
    }

    public static Task parseTask(String input) throws EryzBotException {
        if (input.toLowerCase().startsWith("todo")) {
            return TodoTask.TodoTaskCreate(input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return DeadlineTask.DeadlineTaskCreate(input);
        } else if (input.toLowerCase().startsWith("event")) {
            return EventTask.EventTaskCreate(input);
        } else {
            throw new EryzBotException("I don't recognize that task. Please input todo/deadline/event only!");
        }
    }
}
