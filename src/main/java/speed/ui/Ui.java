package speed.ui;

/**
 *Contains methods displaying user interaction texts.
 */



public class Ui {

    public static final String ERROR_UNKNOWN_COMMAND =
            "Invalid command.\nType <help> to see available commands.";

    public static final String ERROR_EMPTY_TODO =
            "The description of a todo cannot be empty.";

    public static final String ERROR_MARK_NO_NUMBER =
            "Please provide a task number. Usage: mark <number>";

    public static final String ERROR_MARK_NOT_NUMBER =
            "Task number must be an integer.";

    public static final String EVENT_FORMAT_ERROR =
            "Format: event <description> /from <start time> /to <end time>";

    public static final String DEADLINE_FORMAT_ERROR =
            "Format: deadline <description> /by <when>";


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

    public static void bye() {
        printLine();
        System.out.println("PEACE OUT BROTHER!🫡 GREEN APPLE!!!🍏🍏");
        printLine();
    }

    public static void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public static void printCommandList () {
        printLine();
        System.out.println("Available commands:");
        System.out.println("1.list");
        System.out.println("2.todo <description>");
        System.out.println("3.deadline " + DEADLINE_FORMAT_ERROR);
        System.out.println("4.event " + EVENT_FORMAT_ERROR);
        System.out.println("5.mark <Task number>");
        System.out.println("6.unmark <Task number>");
        System.out.println("7.bye");
        printLine();
    }
}
