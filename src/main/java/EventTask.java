import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate from;
    private LocalDate to;

    public EventTask(String name, LocalDate from, LocalDate to){
        super(name, "[E]");
        this.from = from;
        this.to = to;
    }

    public static Task EventTaskCreate(String input){
        try {
            String[] desc = input.substring(6).split(" /from | /to ", 3);
            String name = desc[0];
            String from = desc[1];
            String to = desc[2];

            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EryzBotException("The description or event date cannot be empty.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(from, formatter);
            LocalDate end = LocalDate.parse(to, formatter);

            return new EventTask(name, start, end);
        } catch (Exception e) {
            throw new EryzBotException("Invalid format. Use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
    }

    @Override
    public void printTask(){
        super.printTask();
        System.out.println(" (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
