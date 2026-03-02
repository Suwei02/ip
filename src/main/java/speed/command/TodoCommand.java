package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.TaskList;
import speed.task.Todo;
import speed.ui.Ui;

/**
 * Represents a command to add a Todo task.
 */
public class TodoCommand extends Command {
    private final Todo task;

    /**
     * Creates a TodoCommand with the specified task.
     *
     * @param task The Todo task to add.
     */
    public TodoCommand(Todo task) {
        this.task = task;
    }

    /**
     * Executes the todo command by adding the task, displaying confirmation, and saving.
     *
     * @param tasks The task list to add the task to.
     * @param ui The UI for displaying the confirmation message.
     * @param storage The storage for saving the updated task list.
     * @throws SpeedException If an error occurs during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpeedException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}