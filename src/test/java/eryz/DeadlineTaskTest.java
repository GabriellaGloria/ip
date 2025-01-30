package eryz;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void testDeadlineTaskCreateValidInput() {
        String input = "deadline give report /by 2025-01-31";
        Task task = DeadlineTask.deadlineTaskCreate(input);
        assertNotNull(task);
        assertTrue(task instanceof DeadlineTask);
    }

    @Test
    public void testDeadlineTaskCreateInvalidInput() {
        String input = "deadline give report /by";
        assertThrows(EryzBotException.class, () -> DeadlineTask.deadlineTaskCreate(input));
    }
}
