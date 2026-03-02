package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks The task list (not used).
     * @param ui The UI for displaying the goodbye message.
     * @param storage The storage (not used).
     * @throws SpeedException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        ui.printByeMessage();
    }

    /**
     * Indicates that this command terminates the application.
     *
     * @return true, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
