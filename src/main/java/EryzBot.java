import java.util.Scanner;

public class EryzBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    ui.exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (tasks.size() == 0) {
                        ui.showTaskListEmpty();
                    } else {
                        ui.showTaskList(tasks.getTasks());
                    }
                } else if (input.toLowerCase().startsWith("mark")) {
                    int idx = Parser.parseIndex(input);
                    tasks.markTask(idx);
                    storage.save(tasks.getTasks());
                } else if (input.toLowerCase().startsWith("delete")) {
                    int idx = Parser.parseIndex(input);
                    tasks.deleteTask(idx);
                    storage.save(tasks.getTasks());
                    ui.showTaskDeleted();
                } else if (input.toLowerCase().startsWith("unmark")) {
                    int idx = Parser.parseIndex(input);
                    tasks.unmarkTask(idx);
                    storage.save(tasks.getTasks());
                } else {
                    Task newTask = Parser.parseTask(input);
                    tasks.addTask(newTask);
                    storage.save(tasks.getTasks());
                    ui.showTaskAdded(newTask, tasks.size());
                }
                ui.showSeparator();
            } catch (EryzBotException e) {
                ui.showError(e.getMessage());
                ui.showSeparator();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new EryzBot("../data/eryz.txt").run();
    }
}
