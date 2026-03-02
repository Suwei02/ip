package speed.ui;

import speed.task.Task;
import speed.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interactions including input and output.
 */
public class Ui {

    public static final String ERROR_UNKNOWN_COMMAND =
            "Invalid command.\nType <help> to see available commands.";

    public static final String ERROR_EMPTY_TODO =
            "Hold up! You forgot to give a description of the todo!!";

    public static final String ERROR_MISSING_KEYWORD =
            "Give me a keyword of what you want me to find bro!";

    public static final String ERROR_NO_TASK_NUMBER =
            "Please give me the task number as well bro.";

    public static final String ERROR_NOT_NUMBER =
            "That's not a task number bro. -_-";

    private static final String EVENT_FORMAT =
            "Format: event <description> /from <start time> /to <end time>";

    public static final String EVENT_FORMAT_ERROR =
            "Follow this format bro!\n" + EVENT_FORMAT;

    private static final String DEADLINE_FORMAT =
            "Format: deadline <description> /by <when>";

    public static final String DEADLINE_FORMAT_ERROR =
            "Follow this format bro!\n" + DEADLINE_FORMAT;

    public static final String INVALID_TASK_NUMBER =
            "Invalid task number man. Type <list> to find the correct task number!";

    private static final String BYE_MESSAGE =
            "PEACE OUT BROTHER! GREEN APPLE!!!";

    private final Scanner scanner;

    /**
     * Creates an Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message and logo.
     */
    public void greet() {
        String logo =
                "███████╗██████╗ ███████╗███████╗██████╗\n" +
                        "██╔════╝██╔══██╗██╔════╝██╔════╝██╔══██╗\n" +
                        "███████╗██████╔╝█████╗  █████╗  ██║  ██║\n" +
                        "╚════██║██╔═══╝ ██╔══╝  ██╔══╝  ██║  ██║\n" +
                        "███████║██║     ███████╗███████╗██████╔╝\n" +
                        "╚══════╝╚═╝     ╚══════╝╚══════╝╚═════╝\n";
        printLine();
        System.out.println("YO! I'M SPEED!");
        System.out.println(logo);
        System.out.println("WHAT CAN I DO FOR YOU BROOOO??");
        System.out.println("Type <help> to view the list of available commands.");
        printLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void printByeMessage() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Displays the list of available commands.
     */
    public void printCommandList() {
        printLine();
        System.out.println("Available commands:");
        System.out.println("1.list");
        System.out.println("2.todo <description>");
        System.out.println("3.deadline " + DEADLINE_FORMAT);
        System.out.println("4.event " + EVENT_FORMAT);
        System.out.println("5.mark <Task number>");
        System.out.println("6.unmark <Task number>");
        System.out.println("7.delete <Task number>");
        System.out.println("8.find <keyword>");
        System.out.println("9.bye");
        printLine();
    }

    /**
     * Displays a warning when tasks cannot be loaded.
     */
    public void printLoadingWarning() {
        printLine();
        System.out.println("Warning: could not load saved tasks. Starting with an empty list.");
        printLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            printLine();
            System.out.println("NO TASKS YET! SIUUU!");
            printLine();
        } else {
            printLine();
            System.out.println("Here are the tasks in your list bro:  ");
            int taskCount = 1;
            for (Task task : tasks.getTasks()) {
                System.out.println((taskCount++) + "." + task.displayString());
            }
            printLine();
        }
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param totalTaskCount The total number of tasks after adding.
     */
    public void showTaskAdded(Task task, int totalTaskCount) {
        printLine();
        System.out.println("Gotcha, I've added this task:");
        System.out.println(task.displayString());
        System.out.println("Now you have " + totalTaskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("HELL YEAH! ANOTHER TASK DONE:");
        System.out.println(task.displayString());
        printLine();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("Ok, still waiting on this one bro:");
        System.out.println(task.displayString());
        printLine();
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param remainingTasksCount The number of tasks remaining.
     */
    public void showTaskDeleted(Task task, int remainingTasksCount) {
        printLine();
        System.out.println("Gotchu bro! Deleting this task:");
        System.out.println(task.displayString());
        System.out.println("Now you have " + remainingTasksCount + " tasks in the list.");
        printLine();
    }

    public void showFindList(ArrayList<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            printLine();
            System.out.println("No matches found!");
            printLine();
        } else {
            printLine();
            System.out.println("Here are the matching tasks in your list:");
            int taskCount = 1;
            for (Task task : matchedTasks) {
                System.out.println((taskCount++) + "." + task.displayString());
            }
            printLine();
        }

    }
}
