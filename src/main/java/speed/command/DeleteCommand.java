package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.Task;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param taskIndex Zero-based index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task, displaying confirmation, and saving.
     *
     * @param tasks The task list containing the task to delete.
     * @param ui The UI for displaying the confirmation message.
     * @param storage The storage for saving the updated task list.
     * @throws SpeedException If an error occurs during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
