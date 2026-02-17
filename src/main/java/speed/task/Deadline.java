package speed.task;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {
     private final String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String displayString() {
        return "[D] " + super.displayString() + " (by: " + deadlineTime + ")";
    }
}

