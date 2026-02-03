package speed.task;

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
