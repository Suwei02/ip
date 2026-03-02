package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents an executable command in the Speed application.
 * All specific command types inherit from this abstract class.
 */
public abstract class Command {
    /**
     * Executes the command with the given application components.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     * @throws SpeedException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException;

    /**
     * Indicates whether this command should terminate the application.
     * Default implementation returns false. Override in ExitCommand to return true.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}