package speed.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks with operations to manage them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with existing tasks.
     *
     * @param tasks ArrayList of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index Zero-based index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index Zero-based index of the task to mark.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param index Zero-based index of the task to unmark.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list has no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the underlying ArrayList of tasks.
     * Used by Storage for saving tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
