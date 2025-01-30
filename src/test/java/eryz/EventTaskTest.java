package eryz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EventTaskTest {

    @Test
    public void testEventTaskCreateValidInput() {
        String input = "event meeting cs2103T /from 2025-02-01 /to 2025-02-02";
        Task task = EventTask.EventTaskCreate(input);
        assertNotNull(task);
        assertTrue(task instanceof EventTask);
    }

    @Test
    public void testEventTaskCreateInvalidInput() {
        String input = "event meeting cs2103T /from 2025-02-01";
        assertThrows(EryzBotException.class, () -> EventTask.EventTaskCreate(input));
    }

    @Test
    public void testEventTaskPrint() {
        String input = "event meeting cs2103T /from 2025-02-01 /to 2025-02-02";
        Task task = EventTask.EventTaskCreate(input);
        task.printTask();
        assertDoesNotThrow(() -> task.printTask());
    }
}
