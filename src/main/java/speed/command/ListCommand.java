package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks.
     *
     * @param tasks The task list to display.
     * @param ui The UI for displaying the task list.
     * @param storage The storage (not used).
     * @throws SpeedException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        ui.showTaskList(tasks);
    }
}
