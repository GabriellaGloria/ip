public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String input, String from, String to){
        super(input, "[E]");
        this.from = from;
        this.to = to;
    }

    static Task EventTaskCreate(String input){
        try {
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new EryzBotException("Oops, Please use fromat : event <name> /from <start> /to <end>");
            }
            String[] desc = input.substring(6).split(" /from ");
            String name = desc[0];
            String[] date = desc[1].split(" /to ");
            String from = date[0];
            String to = date[1];
            return new EventTask(name, from, to);
        } catch (RuntimeException e) {
            throw new EryzBotException("Invalid input format. Use: event <name> /from <start> /to <end>");
        }
    }

    @Override
    public void printTask(){
        super.printTask();
        System.out.println(" (from: " + from + " to: " + to + ")");
    }
}
