package speed.storage;

import speed.exception.SpeedException;
import speed.task.Task;
import speed.task.Todo;
import speed.task.Deadline;
import speed.task.Event;
import speed.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages saving tasks to disk and loading tasks from disk.
 */
public class Storage {
    public static final String EVENT = "E";
    public static final String DEADLINE = "D";
    public static final String TODO = "T";
    private final String filePath;

    /**
     * Creates a Storage object that reads from and writes to the given file path.
     *
     * @param filePath File path of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath; //object filePath now refers to relativePath
    }

    /**
     * Loads tasks from the save file.
     * If the file does not exist, an empty list is returned.
     * If some lines are corrupted, valid tasks are still loaded and a warning is shown.
     *
     * @return List of tasks loaded from disk.
     * @throws SpeedException If the save file exists but cannot be opened.
     */
    public ArrayList<Task> load() throws SpeedException {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        boolean hasCorruption = false;

        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                try {
                    Task task = parseLine(line);
                    tasks.add(task);

                } catch (SpeedException e)  {
                    hasCorruption = true;
                }
            }
            if (hasCorruption) {
                Ui.printLine();
                System.out.println("Warning: Some tasks could not be loaded.");
                Ui.printLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new SpeedException("File not found");
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the save file.
     * Any required parent directories will be created if missing.
     *
     * @param tasks List of tasks to save.
     * @throws SpeedException If an I/O error occurs while writing the file.
     */
    public void save(ArrayList<Task> tasks) throws SpeedException {

        try {
            File file = new File(filePath);

            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath);

            for (Task task : tasks) {

                String line = serialize(task);

                fw.write(line);
                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            throw new SpeedException("Error writing file: " + e.getMessage());
        }

    }

    /**
     * Converts a Task into a single-line string for storage.
     *
     * @param task Task to serialize.
     * @return Serialized representation of the task.
     */

    private String serialize(Task task) {

        String done = task.isDone() ? "1" : "0";

        if (task instanceof Todo) {
            return "T | "  + done + " | " + task.getDescription();
         }

        if (task instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getDeadlineTime();
        }

        if (task instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getStartTime() + " | "
                    + e.getEndTime();
        }
        return "";
    }

    /**
     * Parses a saved line into a Task object.
     *
     * @param line A line from the save file.
     * @return Task represented by the line.
     * @throws SpeedException If the line format is invalid or unsupported.
     */
    private Task parseLine(String line) throws SpeedException {
        //Split by |, ignore all whitespaces and treat | as a literal character
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            throw new SpeedException("Corrupted line");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {

        case TODO:
            if (parts.length != 3) {
                throw new SpeedException("Corrupted Todo line");
            }
            task = new Todo(description);
            break;

        case DEADLINE:
            if (parts.length != 4) {
                throw new SpeedException("Corrupted Deadline line");
            }
            task = new Deadline(description, parts[3]);
            break;

        case EVENT:
            if (parts.length != 5) {
                throw new SpeedException("Corrupted Event line");
            }
            task = new Event(description, parts[3], parts[4]);
            break;

        default:
            throw new SpeedException("Corrupted line");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
        }

    }

