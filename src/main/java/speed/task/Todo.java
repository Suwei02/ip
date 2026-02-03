package speed.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDisplayString() {
        return "[T] " + super.toDisplayString();
    }
}
