package speed.task;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {

    /** Description of the task. */
    private String description;

    /** Completion status of the task. */
    private boolean isDone;

    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String displayString() {
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }

    public String getDescription() {
        return description;
    }
    /**
     * Marks this task as completed.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }


}