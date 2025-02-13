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
     */
    public String getResponse(String input) {
        try {
            return handleCommand(input);
        } catch (EryzBotException e) {
            return e.getMessage();
        }
    }

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

    private String handleExit() {
        ui.exit();
        return "Goodbye!";
    }

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

    private String handleFind(String input) throws EryzBotException {
        String keyword = Parser.parseFind(input);
        return tasks.findTasks(keyword).toString();
    }

    private String handleMark(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.markTask(idx);
        saveTasks();
        return "Marked task " + idx;
    }

    private String handleDelete(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.deleteTask(idx);
        saveTasks();
        return "Deleted task " + idx;
    }

    private String handleUnmark(String input) throws EryzBotException {
        int idx = Parser.parseIndex(input);
        tasks.unmarkTask(idx);
        saveTasks();
        return "Unmarked task " + idx;
    }

    private String handleAddTask(String input) throws EryzBotException {
        Task newTask = Parser.parseTask(input);
        tasks.addTask(newTask);
        saveTasks();
        return "Added task: " + newTask.printTask();
    }

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
