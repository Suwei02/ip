package speed.ui;

/**
 *Contains methods displaying user interaction texts.
 */



public class Ui {

    private static final String EVENT_FORMAT_ERROR =
            "Format: event <description> /from <start time> /to <end time>";
    private static final String DEADLINE_FORMAT_ERROR =
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

    public static void printEventFormatError() {
        printLine();
        System.out.println(EVENT_FORMAT_ERROR);
        printLine();
    }
    public static void printDeadlineFormatError() {
        printLine();
        System.out.println(DEADLINE_FORMAT_ERROR);
        printLine();
    }

    public static void printErrorMsg() {
        printLine();
        System.out.println("Invalid command.\nType <help> to see the list of commands. ");
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
