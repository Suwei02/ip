package speed.parser;

import speed.command.Command;
import speed.command.DeadlineCommand;
import speed.command.DeleteCommand;
import speed.command.EventCommand;
import speed.command.ExitCommand;
import speed.command.FindCommand;
import speed.command.HelpCommand;
import speed.command.ListCommand;
import speed.command.MarkCommand;
import speed.command.TodoCommand;
import speed.command.UnmarkCommand;
import speed.exception.SpeedException;
import speed.task.Deadline;
import speed.task.Event;
import speed.task.Todo;
import speed.ui.Ui;

/**
 * Parses user input and creates appropriate Command objects.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command object.
     *
     * @param input The full user input string.
     * @param totalTaskCount Total number of tasks (needed for index validation).
     * @return The Command object to execute.
     * @throws SpeedException If the input is invalid or cannot be parsed.
     */
    public static Command parseCommand(String input, int totalTaskCount) throws SpeedException {
        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.equals("list")) {
            return new ListCommand();

        } else if (input.equals("help")) {
            return new HelpCommand();

        } else if (input.equals("mark") || input.startsWith("mark ")) {
            int index = parseTaskIndex(input, totalTaskCount);
            return new MarkCommand(index);

        } else if (input.equals("unmark") || input.startsWith("unmark ")) {
            int index = parseTaskIndex(input, totalTaskCount);
            return new UnmarkCommand(index);

        } else if (input.equals("delete") || input.startsWith("delete ")) {
            int index = parseTaskIndex(input, totalTaskCount);
            return new DeleteCommand(index);

        } else if (input.equals("todo") || input.startsWith("todo ")) {
            Todo task = parseTodo(input);
            return new TodoCommand(task);

        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            Deadline task = parseDeadline(input);
            return new DeadlineCommand(task);

        } else if (input.equals("event") || input.startsWith("event ")) {
            Event task = parseEvent(input);
            return new EventCommand(task);

        } else if (input.equals("find") || input.startsWith("find ") ) {
            String keyword = parseFind(input);
            return new FindCommand(keyword);
        } else {
            throw new SpeedException(Ui.ERROR_UNKNOWN_COMMAND);
        }
    }

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
    private static int parseTaskIndex(String input, int totalTaskCount) throws SpeedException {
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
    private static Deadline parseDeadline(String input) throws SpeedException {
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
    private static Event parseEvent(String input) throws SpeedException {
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
    private static Todo parseTodo(String input) throws SpeedException {
        String[] parts = input.split(" ", 2);
        String description = parts.length < 2 ? "" : parts[1].trim();

        if (description.isEmpty()) {
            throw new SpeedException(Ui.ERROR_EMPTY_TODO);
        }
        return new Todo(description);
    }

    private static String parseFind(String input) throws SpeedException {
        String[] parts = input.split(" ", 2);
        String keyword = parts.length < 2 ? "" : parts[1].trim();

        if (keyword.isEmpty()) {
            throw new SpeedException(Ui.ERROR_MISSING_KEYWORD);
        }
        return keyword;
    }
}
