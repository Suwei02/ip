package speed.task;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toDisplayString() {
        return "[E] " + super.toDisplayString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
