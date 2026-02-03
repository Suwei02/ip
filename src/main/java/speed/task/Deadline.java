package speed.task;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {
    protected  String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toDisplayString() {
        return "[D] " + super.toDisplayString() + " (by: " + deadlineTime + ")";
    }
}
