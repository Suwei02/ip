package speed;

import speed.command.Command;
import speed.exception.SpeedException;
import speed.parser.Parser;
import speed.storage.Storage;
import speed.task.TaskList;
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
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input, tasks.size());
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SpeedException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Speed("./data/speed.txt").run();
    }
}

