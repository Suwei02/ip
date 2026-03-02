package speed.command;

import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.task.Event;
import speed.task.TaskList;
import speed.ui.Ui;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    private final Event task;

    /**
     * Creates an EventCommand with the specified task.
     *
     * @param task The Event task to add.
     */
    public EventCommand(Event task) {
        this.task = task;
    }

    /**
     * Executes the event command by adding the task, displaying confirmation, and saving.
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
