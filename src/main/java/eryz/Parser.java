package eryz;

public class Parser {

    public static int parseIndex(String input) throws EryzBotException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EryzBotException("Invalid index.");
        }
    }

    public static String parseFind(String input) throws EryzBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EryzBotException("Provide your keyword to find tasks.");
        }
        return parts[1].trim();
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
