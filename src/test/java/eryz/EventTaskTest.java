package eryz;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import eryz.exception.EryzBotException;
import eryz.task.EventTask;
import eryz.task.Task;
public class EventTaskTest {

    @Test
    public void testEventTaskCreateValidInput() {
        String input = "event meeting cs2103T /from 2025-02-01 /to 2025-02-02";
        Task task = EventTask.eventTaskCreate(input);
        assertNotNull(task);
        assertTrue(task instanceof EventTask);
    }

    @Test
    public void testEventTaskCreateInvalidInput() {
        String input = "event meeting cs2103T /from 2025-02-01";
        assertThrows(EryzBotException.class, () -> EventTask.eventTaskCreate(input));
    }

    @Test
    public void testEventTaskPrint() {
        String input = "event meeting cs2103T /from 2025-02-01 /to 2025-02-02";
        Task task = EventTask.eventTaskCreate(input);
        System.out.println(task.printTask());
        assertDoesNotThrow(() -> task.printTask());
    }
}
