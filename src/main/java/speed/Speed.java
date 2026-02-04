package speed;

import speed.task.Deadline;
import speed.task.Event;
import speed.task.Task;
import speed.task.Todo;

import java.util.Scanner;

/**
 * Main class for the Speed application.
 */

public class Speed {

    private static final int MAX_TASKS = 100;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static final String EVENT_FORMAT_ERROR =
            "Format: event <description> /from <start time> /to <end time>";
    private static final String DEADLINE_FORMAT_ERROR =
            "Format: deadline <description> /by <when>";

    public static void printList(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            printLine();
            System.out.println("NO TASKS YET! SIUUU!");
            printLine();
        } else {
            printLine();
            System.out.println("Here are the tasks in your list bro:  ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toDisplayString());
            }
            printLine();
        }
    }

    public static void printAddedTask(Task task, int totalTaskCount) {
        printLine();
        System.out.println("Gotcha, I've added this task:");
        System.out.println(task.toDisplayString());
        System.out.println("Now you have " + totalTaskCount + " tasks in the list.");
        printLine();
    }

    public static void printMarkedTask(Task[] tasks, int markTaskIndex, int totalTaskCount) {
        if (!isValidTaskIndex(markTaskIndex, totalTaskCount) ) {
            printLine();
            System.out.println("Invalid task number man.");
            printLine();
        } else {
            tasks[markTaskIndex].markAsDone();
            printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println(tasks[markTaskIndex].toDisplayString());
            printLine();
        }
    }

    public static void printUnmarkedTask(Task[] tasks, int unmarkTaskIndex, int totalTaskCount) {
        if (!isValidTaskIndex(unmarkTaskIndex, totalTaskCount)) {
            printLine();
            System.out.println("Invalid task number man.");
            printLine();
        } else {
            tasks[unmarkTaskIndex].markAsNotDone();
            printLine();
            System.out.println("Ok, still waiting on this one bro:");
            //System.out.println("[ ] " + tasks[unmarkTaskIndex].getDescription());
            System.out.println(tasks[unmarkTaskIndex].toDisplayString());

            printLine();
        }
    }

    private static void printEventFormatError() {
        printLine();
        System.out.println(EVENT_FORMAT_ERROR);
        printLine();
    }
    private static void printDeadlineFormatError() {
        printLine();
        System.out.println(DEADLINE_FORMAT_ERROR);
        printLine();
    }

    // Helper to check validity of index
    private static boolean isValidTaskIndex(int idx, int total) {
        return idx >= 0 && idx < total;
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int totalTasksCount = 0;

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                bye();
                break;

            } else if (input.equals("list")) {
                printList(tasks, totalTasksCount);

            } else if (input.startsWith("mark ")) {
                String[] parts = input.split(" ");
                int markTaskIndex = Integer.parseInt(parts[1]) - 1;

                printMarkedTask(tasks, markTaskIndex, totalTasksCount);

            } else if (input.startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int unmarkTaskIndex = Integer.parseInt(parts[1]) - 1;

                printUnmarkedTask(tasks, unmarkTaskIndex, totalTasksCount);

            } else if (input.startsWith("todo ")) {
                String[] parts = input.split(" ", 2);
                String description = parts.length > 1 ? parts[1].trim() : ""; //checks for description
                tasks[totalTasksCount++] = new Todo(description);
                printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

            } else if (input.startsWith("deadline ")) {
              //remove deadline from input
                String deadlineContent = input.substring("deadline".length()).trim();
                String[] parts = deadlineContent.split(" /by",2); //split at most by 2 parts

                //check format
                if (parts.length < 2) {
                    printDeadlineFormatError();
                    continue;
                }

                String description = parts[0].trim();
                String deadline = parts[1].trim();

                tasks[totalTasksCount++] = new Deadline(description,deadline);
                printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

            } else if (input.startsWith("event ")){
                String eventContent = input.substring("event".length()).trim();
                String[] parts = eventContent.split(" /from | /to ", 3);

                // Check format
                if (parts.length < 3) {
                    printEventFormatError();
                    continue;
                }

                String description = parts[0].trim();
                String startTime = parts[1].trim();
                String endTime = parts[2].trim();

                tasks[totalTasksCount++] = new Event(description, startTime, endTime);
                printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

            } else {
                tasks[totalTasksCount++] = new Task(input);
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }
    }

    private static void bye() {
        printLine();
        System.out.println("PEACE OUT BROTHER!🫡 GREEN APPLE!!!🍏🍏");
        printLine();
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
        printLine();
    }
}


