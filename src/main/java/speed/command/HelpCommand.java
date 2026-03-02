package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to display the list of available commands.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by displaying all available commands.
     *
     * @param tasks The task list (not used).
     * @param ui The UI for displaying the command list.
     * @param storage The storage (not used).
     * @throws SpeedException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        ui.printCommandList();
    }
}

