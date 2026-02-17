package speed.storage;

import speed.exception.SpeedException;
import speed.task.Task;
import speed.task.Todo;
import speed.task.Deadline;
import speed.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath; //object filePath now refers to relativePath
    }

    public ArrayList<Task> load() throws SpeedException {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

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
                    // Skip corrupted line and continue loading
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new SpeedException("File not found");
        }
        return tasks;
    }

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

        case "T":
            if (parts.length != 3) {
                throw new SpeedException("Corrupted Todo line");
            }
            task = new Todo(description);
            break;

        case "D":
            if (parts.length != 4) {
                throw new SpeedException("Corrupted Deadline line");
            }
            task = new Deadline(description, parts[3]);
            break;

        case "E":
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

