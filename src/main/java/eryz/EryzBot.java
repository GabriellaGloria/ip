package eryz;

import eryz.exception.EryzBotException;
import eryz.task.Task;

/**
 * Represents the EryzBot application that handles task management and user interactions.
 * It processes user input, manages tasks, and interacts with the storage system to persist data.
 */
public class EryzBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an EryzBot instance with a specified file path for storage.
     * It initializes the UI, loads tasks from storage, or creates an empty task list if loading fails.
     * 
     * @param filePath The file path where tasks are stored.
     */
    public EryzBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.fetch());
        } catch (EryzBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input and returns an appropriate response.
     * This method responds to commands such as "bye", "list", "find", "mark", "delete", "unmark", 
     * and adding new tasks. It also updates the task storage after modifying the task list.
     * 
     * @param input The user input command.
     * @return A string response to the user's command.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";

        try {
            if (input.equalsIgnoreCase("bye")) {
                ui.exit();
                return "Goodbye!";
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.size() == 0) {
                    return "Your task list is empty.";
                } else {
                    return tasks.getTasks().toString(); // Display all tasks
                }
            } else if (input.toLowerCase().startsWith("find")) {
                String keyword = Parser.parseFind(input);
                return tasks.findTasks(keyword).toString(); // Display matching tasks
            } else if (input.toLowerCase().startsWith("mark")) {
                int idx = Parser.parseIndex(input);
                tasks.markTask(idx);
                storage.save(tasks.getTasks());
                return "Marked task " + idx;
            } else if (input.toLowerCase().startsWith("delete")) {
                int idx = Parser.parseIndex(input);
                tasks.deleteTask(idx);
                storage.save(tasks.getTasks());
                return "Deleted task " + idx;
            } else if (input.toLowerCase().startsWith("unmark")) {
                int idx = Parser.parseIndex(input);
                tasks.unmarkTask(idx);
                storage.save(tasks.getTasks());
                return "Unmarked task " + idx;
            } else {
                Task newTask = Parser.parseTask(input);
                tasks.addTask(newTask);
                storage.save(tasks.getTasks());
                return "Added task: " + newTask;
            }
        } catch (EryzBotException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method to launch the EryzBot application.
     * It creates an instance of EryzBot and processes a sample input (e.g., "list").
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new EryzBot("./data/eryz.txt").getResponse("list"); 
    }
}
