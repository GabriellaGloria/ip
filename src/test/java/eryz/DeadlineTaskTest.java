package eryz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {

    @Test
    public void testDeadlineTaskCreateValidInput() {
        String input = "deadline give report /by 2025-01-31";
        Task task = DeadlineTask.DeadlineTaskCreate(input);
        assertNotNull(task);
        assertTrue(task instanceof DeadlineTask);
    }

    @Test
    public void testDeadlineTaskCreateInvalidInput() {
        String input = "deadline give report /by";
        assertThrows(EryzBotException.class, () -> DeadlineTask.DeadlineTaskCreate(input));
    }
}
