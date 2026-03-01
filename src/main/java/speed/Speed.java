package speed;

import speed.task.Task;
import speed.task.TaskList;
import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.parser.Parser;
import speed.ui.Ui;

/**
 * Main class for the Speed application.
 */
public class Speed {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Speed instance with the specified file path for storage.
     *
     * @param filePath Path to the data file.
     */
    public Speed(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SpeedException e) {
            ui.printLoadingWarning();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        ui.greet();

        while (true) {
            String input = ui.readCommand();
            try {
                if (input.equals("bye")) {
                    ui.printByeMessage();
                    break;

                } else if (input.equals("list")) {
                    ui.showTaskList(tasks);

                } else if (input.equals("mark") || input.startsWith("mark ")) {
                    int markTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    tasks.markTask(markTaskIndex);
                    ui.showTaskMarked(tasks.getTask(markTaskIndex));
                    storage.save(tasks.getTasks());

                } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                    int unmarkTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    tasks.unmarkTask(unmarkTaskIndex);
                    ui.showTaskUnmarked(tasks.getTask(unmarkTaskIndex));
                    storage.save(tasks.getTasks());

                } else if (input.equals("delete") || input.startsWith("delete ")) {
                    int deleteTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    Task removedTask = tasks.deleteTask(deleteTaskIndex);
                    ui.showTaskDeleted(removedTask, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    Task newTask = Parser.parseTodo(input);
                    tasks.addTask(newTask);
                    ui.showTaskAdded(newTask, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    Task newDeadline = Parser.parseDeadline(input);
                    tasks.addTask(newDeadline);
                    ui.showTaskAdded(newDeadline, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("event") || input.startsWith("event ")) {
                    Task newEvent = Parser.parseEvent(input);
                    tasks.addTask(newEvent);
                    ui.showTaskAdded(newEvent, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("help")) {
                    ui.printCommandList();

                } else {
                    throw new SpeedException(Ui.ERROR_UNKNOWN_COMMAND);
                }
            } catch (SpeedException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Speed("./data/speed.txt").run();
    }
}

