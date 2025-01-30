package eryz;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void testTodoTaskCreateValidInput() {
        String input = "todo Buy book";
        Task task = TodoTask.todoTaskCreate(input);
        assertNotNull(task);
        assertTrue(task instanceof TodoTask);
    }

    @Test
    public void testTodoTaskCreateInvalidInput() {
        String input = "todo";
        assertThrows(EryzBotException.class, () -> TodoTask.todoTaskCreate(input));
    }
}
