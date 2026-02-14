package speed.task;

/**
 * Represents a to-do task without any date or time constraints.
 */
public class Todo extends Task {

    /**
     * Creates a to-do task with the specified description.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String displayString() {
        return "[T] " + super.displayString();
    }
}
