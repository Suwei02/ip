package speed.ui;

/**
 *Contains methods displaying user interaction texts.
 */

public class Ui {

    public static final String ERROR_UNKNOWN_COMMAND =
            "Invalid command.\nType <help> to see available commands.";

    public static final String ERROR_EMPTY_TODO =
            "Hold up! You forgot to give a description of the todo!!";

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

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        String logo =
                "███████╗██████╗ ███████╗███████╗██████╗\n" +
                        "██╔════╝██╔══██╗██╔════╝██╔════╝██╔══██╗\n" +
                        "███████╗██████╔╝█████╗  █████╗  ██║  ██║\n" +
                        "╚════██║██╔═══╝ ██╔══╝  ██╔══╝  ██║  ██║\n" +
                        "███████║██║     ███████╗███████╗██████╔╝\n" +
                        "╚══════╝╚═╝     ╚══════╝╚══════╝╚═════╝\n";
        printLine();
        System.out.println("YO! I'M SPEED 🏃💨");
        System.out.println(logo);
        System.out.println("WHAT CAN I DO FOR YOU BROOOO?? 💥💥💥");
        System.out.println("Type <help> to view the list of available commands.");
        printLine();
    }

    private static final String BYE_MESSAGE =
            "PEACE OUT BROTHER!🫡 GREEN APPLE!!!🍏🍏";

    public static void printByeMessage() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    public static void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public static void printCommandList() {
        printLine();
        System.out.println("Available commands:");
        System.out.println("1.list");
        System.out.println("2.todo <description>");
        System.out.println("3.deadline " + DEADLINE_FORMAT);
        System.out.println("4.event " + EVENT_FORMAT);
        System.out.println("5.mark <Task number>");
        System.out.println("6.unmark <Task number>");
        System.out.println("7.bye");
        printLine();
    }
}
