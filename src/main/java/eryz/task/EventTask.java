package eryz.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import eryz.exception.EryzBotException;

/**
 * Represents an event task in the EryzBot application.
 * This task includes a description, as well as a date range from a starting date to an ending date.
 * Event tasks are stored with a description and a date range, with the dates formatted as "yyyy-MM-dd".
 */
public class EventTask extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for creating an EventTask.
     * 
     * @param name The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public EventTask(String name, LocalDate from, LocalDate to) {
        super(name, "[E]");  // Call the parent constructor with the task description and type "[E]"
        
        assert from != null : "Start date cannot be null";
        assert to != null : "End date cannot be null";

        this.from = from;
        this.to = to;
    }

    /**
     * Static method to create an EventTask from user input.
     * The method expects the input to start with "event" followed by the task description,
     * starting date, and ending date in the format: "/from yyyy-MM-dd /to yyyy-MM-dd".
     * 
     * @param input The full user input for creating an EventTask (should start with "event").
     * @return A new EventTask instance.
     * @throws EryzBotException If the input format is invalid or any part of the input is empty.
     */
    public static Task eventTaskCreate(String input) {
        assert input != null : "Input should not be null";
        
        try {
            // Extract the task description, starting date, and ending date from the input
            String[] desc = input.substring(6).split(" /from | /to ", 3);
            String name = desc[0];
            String from = desc[1];
            String to = desc[2];

            // Ensure none of the fields are empty
            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EryzBotException("The description or event date cannot be empty.");
            }

            // Parse the start and end dates using the specified format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(from, formatter);
            LocalDate end = LocalDate.parse(to, formatter);

            return new EventTask(name, start, end);  // Return a new EventTask instance
        } catch (Exception e) {
            throw new EryzBotException("Invalid format. Use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
    }

    /**
     * Prints the details of the EventTask.
     * It calls the parent method to print the task description, followed by the formatted start and end dates.
     */
    @Override
    public void printTask() {
        super.printTask();  // Print the task using the parent class's printTask method
        System.out.println(" (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
