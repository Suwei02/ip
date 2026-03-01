package speed.parser;

import speed.exception.SpeedException;
import speed.task.Deadline;
import speed.task.Event;
import speed.task.Task;
import speed.task.Todo;
import speed.ui.Ui;

/**
 * Parses user input and creates appropriate Task objects.
 */
public class Parser {

    /**
     * Helper method to check if a task index is valid.
     *
     * @param index The zero-based index to check.
     * @param total Total number of tasks.
     * @return true if the index is valid, false otherwise.
     */
    private static boolean isValidTaskIndex(int index, int total) {
        return index >= 0 && index < total;
    }

    /**
     * Parses the task index from a command string.
     *
     * @param input Command string containing the task number.
     * @param totalTaskCount Total number of tasks in the list.
     * @return Zero-based index of the task.
     * @throws SpeedException If the index is missing, invalid, or out of range.
     */
    public static int parseTaskIndex(String input, int totalTaskCount) throws SpeedException {
        String[] parts = input.trim().split("\\s+"); // splits string by one or more spaces
        if (parts.length < 2) {
            throw new SpeedException(Ui.ERROR_NO_TASK_NUMBER);
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SpeedException(Ui.ERROR_NOT_NUMBER);
        }
        if (!isValidTaskIndex(index, totalTaskCount)) {
            throw new SpeedException(Ui.INVALID_TASK_NUMBER);
        }
        return index;
    }

    /**
     * Parses a deadline command and creates a Deadline task.
     *
     * @param input The full deadline command string.
     * @return A new Deadline task.
     * @throws SpeedException If the format is invalid.
     */
    public static Task parseDeadline(String input) throws SpeedException {
        String deadlineContent = input.substring("deadline".length()).trim();
        String[] parts = deadlineContent.split(" /by ", 2); // split at most by 2 parts

        // check format
        if (parts.length < 2) {
            throw new SpeedException(Ui.DEADLINE_FORMAT_ERROR);
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        return new Deadline(description, deadline);
    }

    /**
     * Parses an event command and creates an Event task.
     *
     * @param input The full event command string.
     * @return A new Event task.
     * @throws SpeedException If the format is invalid.
     */
    public static Task parseEvent(String input) throws SpeedException {
        String eventContent = input.substring("event".length()).trim();
        String[] parts = eventContent.split(" /from | /to ", 3);

        // Check format
        if (parts.length < 3) {
            throw new SpeedException(Ui.EVENT_FORMAT_ERROR);
        }

        String description = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();

        return new Event(description, startTime, endTime);
    }

    /**
     * Parses a todo command and creates a Todo task.
     *
     * @param input The full todo command string.
     * @return A new Todo task.
     * @throws SpeedException If the description is empty.
     */
    public static Task parseTodo(String input) throws SpeedException {
        String[] parts = input.split(" ", 2);
        String description = parts.length < 2 ? "" : parts[1].trim();

        if (description.isEmpty()) {
            throw new SpeedException(Ui.ERROR_EMPTY_TODO);
        }
        return new Todo(description);
    }
}
