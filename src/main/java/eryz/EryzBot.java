package eryz;

import eryz.exception.EryzBotException;
import eryz.task.Task;

/**
 * Represents the EryzBot application that handles task management and user interactions.
 * It processes user input, manages tasks, and interacts with the storage system to persist data.
 */
public class EryzBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an EryzBot instance with a specified file path for storage.
     * It initializes the UI, loads tasks from storage, or creates an empty task list if loading fails.
     * 
     * @param filePath The file path where tasks are stored.
     */
    public EryzBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = loadTasks();
    }

    /**
     * Loads tasks from the storage system, or creates a new TaskList if loading fails.
     * 
     * @return A TaskList object containing the loaded tasks, or an empty TaskList if loading fails.
     */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.fetch());
        } catch (EryzBotException e) {
            ui.showLoadingError();
            return new TaskList();
        }
    }

    /**
     * Processes the user input and returns an appropriate response.
     * This method responds to commands such as "bye", "list", "find", "mark", "delete", "unmark",
     * and adding new tasks. It also updates the task storage after modifying the task list.
     * 
     * @param input The user input command.
     * @return A string response to the user's command.
     * @throws EryzBotException If an error occurs while processing the command.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";

        try {
            return handleCommand(input);
        } catch (EryzBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the user command by parsing the input and determining which operation to perform.
     * 
     * @param input The user input command.
     * @return The result of the command execution.
     * @throws EryzBotException If an error occurs while handling the command.
     */
    private String handleCommand(String input) throws EryzBotException {
        String command = input.trim().toLowerCase();

        return switch (command.split(" ")[0]) {
            case "bye" -> handleExit();
            case "list" -> handleList();
            case "find" -> handleFind(input);
            case "mark" -> handleMark(input);
            case "delete" -> handleDelete(input);
            case "unmark" -> handleUnmark(input);
            default -> handleAddTask(input);
        };
    }

    /**
     * Handles the "bye" command, which ends the application.
     * 
     * @return A farewell message to the user.
     */
    private String handleExit() {
        ui.exit();
        return "Goodbye!";
    }

    /**
     * Handles the "list" command by returning a list of all tasks.
     * 
     * @return A string representation of the list of tasks, or a message if the list is empty.
     */
    private String handleList() {
        if (tasks.size() == 0) {
            return ("Your task list is empty.");
        }

        String ret = "";
        for (Task task : tasks.getTasks()) {
            ret += (task.printTask() + "\n");
        }
        return ret;
    }

    /**
     * Handles the "find" command by searching for tasks containing a specified keyword.
     * 
     * @param input The user input containing the search keyword.
     * @return A string representation of the tasks containing the keyword.
     * @throws EryzBotException If there is an issue parsing the input.
     */
    private String handleFind(String input) throws EryzBotException {
        String keyword = Parser.parseFind(input);
        return tasks.findTasks(keyword).toString();
    }

    /**
     * Handles the "mark" command by marking a task as done.
     * 
     * @param input The user input containing the task index to mark.
     * @return A message indicating the task was marked.
     * @throws EryzBotException If there is an issue parsing the input or marking the task.
     */
    private String handleMark(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.markTask(idx);
        saveTasks();
        return "Marked task " + idx;
    }

    /**
     * Handles the "delete" command by deleting a specified task.
     * 
     * @param input The user input containing the task index to delete.
     * @return A message indicating the task was deleted.
     * @throws EryzBotException If there is an issue parsing the input or deleting the task.
     */
    private String handleDelete(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.deleteTask(idx);
        saveTasks();
        return "Deleted task " + idx;
    }

    /**
     * Handles the "unmark" command by unmarking a task as undone.
     * 
     * @param input The user input containing the task index to unmark.
     * @return A message indicating the task was unmarked.
     * @throws EryzBotException If there is an issue parsing the input or unmarking the task.
     */
    private String handleUnmark(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.unmarkTask(idx);
        saveTasks();
        return "Unmarked task " + idx;
    }

    /**
     * Handles adding a new task to the task list.
     * 
     * @param input The user input containing the task details.
     * @return A message indicating the new task was added.
     * @throws EryzBotException If there is an issue parsing the input or adding the task.
     */
    private String handleAddTask(String input) throws EryzBotException {
        Task newTask = Parser.parseTask(input);
        tasks.addTask(newTask);
        saveTasks();
        return "Added task: " + newTask.printTask();
    }

    /**
     * Saves the current list of tasks to the storage system.
     * 
     * @throws EryzBotException If an error occurs while saving tasks.
     */
    private void saveTasks() throws EryzBotException {
        storage.save(tasks.getTasks());
    }

    /**
     * The main method to launch the EryzBot application.
     * It creates an instance of EryzBot and processes a sample input (e.g., "list").
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        EryzBot bot = new EryzBot("./data/eryz.txt");
        System.out.println(bot.getResponse("list"));
    }
}
