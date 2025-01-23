public class DeadlineTask extends Task {
    String by;

    public DeadlineTask(String input, String by){
        super(input, "[D]");
        this.by = by;
    }

    static Task DeadlineTaskCreate(String input){
        try {
            String[] desc = input.substring(9).split(" /by ", 2);
            String name = desc[0];
            String by = desc[1];

            if (name.isEmpty() || by.isEmpty()) {
                throw new EryzBotException("The description or deadline cannot be empty.");
            }
            return new DeadlineTask(name, by);
        } catch (RuntimeException e) {
            throw new EryzBotException("Please use this format : deadline <description> /by <date>");
        }
    }

    @Override
    public void printTask(){
        super.printTask();
        System.out.println(" (by: " + by + ")");
    }
}
