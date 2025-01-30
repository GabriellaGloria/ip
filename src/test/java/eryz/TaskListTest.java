package eryz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task todoTask = new TodoTask("Buy book");
        taskList.addTask(todoTask);
        assertEquals(1, taskList.size(), "Size should be 1 after addition.");
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task todoTask = new TodoTask("Buy book");
        taskList.addTask(todoTask);
        taskList.deleteTask(1);
        assertEquals(0, taskList.size(), "Size should be 0 after deletion.");
    }

    @Test
    public void testSize() {
        TaskList taskList = new TaskList();
        Task todoTask1 = new TodoTask("Buy book");
        Task todoTask2 = new TodoTask("Go to lecture");
        taskList.addTask(todoTask1);
        taskList.addTask(todoTask2);
        assertEquals(2, taskList.size(), "Size should be 2 after addition.");
    }

    @Test
    public void testGetTasks() {
        TaskList taskList = new TaskList();
        Task todoTask = new TodoTask("Buy book");
        taskList.addTask(todoTask);
        assertTrue(taskList.getTasks().contains(todoTask), "List should contain current task.");
    }

    @Test
    public void testDeleteNonExistentTask() {
        TaskList taskList = new TaskList();
        Task todoTask = new TodoTask("Buy book");
        taskList.addTask(todoTask);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(2),
                "Non-existent task deletion should throw an exception.");
    }

    @Test
    public void testMarkNonExistentTask() {
        TaskList taskList = new TaskList();
        Task todoTask = new TodoTask("Buy book");
        taskList.addTask(todoTask);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTask(2),
                "Non-existent task marking should throw an exception.");
    }

}
