package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.Task;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param taskIndex Zero-based index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the task as not done, displaying confirmation, and saving.
     *
     * @param tasks The task list containing the task to unmark.
     * @param ui The UI for displaying the confirmation message.
     * @param storage The storage for saving the updated task list.
     * @throws SpeedException If an error occurs during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        tasks.unmarkTask(taskIndex);
        Task unmarkedTask = tasks.getTask(taskIndex);
        ui.showTaskUnmarked(unmarkedTask);
        storage.save(tasks.getTasks());
    }
}
