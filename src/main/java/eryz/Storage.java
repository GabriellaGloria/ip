package eryz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

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
