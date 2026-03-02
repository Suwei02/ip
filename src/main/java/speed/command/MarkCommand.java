package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.Task;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a MarkCommand with the specified task index.
     *
     * @param taskIndex Zero-based index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the task as done, displaying confirmation, and saving.
     *
     * @param tasks The task list containing the task to mark.
     * @param ui The UI for displaying the confirmation message.
     * @param storage The storage for saving the updated task list.
     * @throws SpeedException If an error occurs during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        tasks.markTask(taskIndex);
        Task markedTask = tasks.getTask(taskIndex);
        ui.showTaskMarked(markedTask);
        storage.save(tasks.getTasks());
    }
}
