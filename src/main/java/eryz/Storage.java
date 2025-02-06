package eryz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import eryz.exception.EryzBotException;
import eryz.task.Task;

/**
 * A class for handling the storage of tasks in a file.
 * This class provides methods to fetch tasks from a file and save tasks to a file.
 * It supports object serialization for storing and retrieving tasks as a list.
 */
public class Storage {
    private String filepath;

    /**
     * Constructs a Storage object with the given file path.
     * 
     * @param filepath The path to the file where tasks will be stored/retrieved.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Fetches the list of tasks from the file.
     * If the file does not exist or is empty, an empty list is returned.
     * The tasks are deserialized from the file using object serialization.
     * 
     * @return An ArrayList of tasks retrieved from the file.
     * @throws EryzBotException if an error occurs while reading the tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> fetch() throws EryzBotException {
        File file = new File(filepath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (var fin = new FileInputStream(filepath); var ois = new ObjectInputStream(fin)) {
            return (ArrayList<Task>) ois.readObject();
        } catch (Exception e) {
            throw new EryzBotException("Couldn't fetch tasks.");
        }
    }

    /**
     * Saves the list of tasks to a file using object serialization.
     * If the parent directories of the file do not exist, they are created.
     * 
     * @param tasks The list of tasks to be saved to the file.
     * @throws EryzBotException if an error occurs while saving the tasks.
     */
    public void save(ArrayList<Task> tasks) throws EryzBotException {
        File file = new File(filepath);
        file.getParentFile().mkdirs();

        try (var fout = new FileOutputStream(filepath); var oos = new ObjectOutputStream(fout)) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new EryzBotException("Couldn't save tasks.");
        }
    }
}
