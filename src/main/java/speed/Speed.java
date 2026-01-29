package speed;

import java.util.Scanner;

public class Speed {

    private static final int MAX_TASKS = 100;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] tasks, int taskNumber) {
        if (taskNumber == 0) {
            System.out.println("NO TASKS YET! SIUUU!");
        } else {
            System.out.println("Here are the tasks in your list bro:  ");
            for (int i = 0; i < taskNumber; i++) {
                System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon()
                        + "] " + tasks[i].getDescription());
            }
        }
    }

    public static void printMarkedTask(Task[] tasks, int markTaskIndex, int totalTaskCount) {
        if (markTaskIndex + 1 > totalTaskCount ) {
            printLine();
            System.out.println("Invalid task number man.");
            printLine();
        } else {
            tasks[markTaskIndex].markAsDone();
            printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println("[X] " + tasks[markTaskIndex].getDescription());
            printLine();
        }
    }

    public static void printUnmarkedTask(Task[] tasks, int unmarkTaskIndex, int totalTaskCount) {
        if (unmarkTaskIndex + 1 > totalTaskCount ) {
            printLine();
            System.out.println("Invalid task number man.");
            printLine();
        } else {
            tasks[unmarkTaskIndex].markAsNotDone();
            printLine();
            System.out.println("Ok, still waiting on this one bro:");
            System.out.println("[ ] " + tasks[unmarkTaskIndex].getDescription());
            printLine();
        }
    }

    public static void main(String[] args) {
        String logo =
                        "███████╗██████╗ ███████╗███████╗██████╗ \n" +
                        "██╔════╝██╔══██╗██╔════╝██╔════╝██╔══██╗\n" +
                        "███████╗██████╔╝█████╗  █████╗  ██║  ██║\n" +
                        "╚════██║██╔═══╝ ██╔══╝  ██╔══╝  ██║  ██║\n" +
                        "███████║██║     ███████╗███████╗██████╔╝\n" +
                        "╚══════╝╚═╝     ╚══════╝╚══════╝╚═════╝ \n";
        printLine();
        System.out.println("YO! I'M SPEED 🏃💨");
        System.out.println(logo);
        System.out.println("WHAT CAN I DO FOR YOU BROOOO?? 💥💥💥");
        printLine();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int totalTasksCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printLine();
                System.out.println("PEACE OUT BROTHER!🫡 GREEN APPLE!!!🍏🍏");
                printLine();
                break;
            } else if (input.equals("list")) {
                printLine();
                printList(tasks, totalTasksCount);
                printLine();
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int markTaskIndex = Integer.parseInt(parts[1]) - 1;

                printMarkedTask(tasks, markTaskIndex, totalTasksCount);
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int unmarkTaskIndex = Integer.parseInt(parts[1]) - 1;

                printUnmarkedTask(tasks, unmarkTaskIndex, totalTasksCount);

            } else {
                tasks[totalTasksCount++] = new Task(input);
                printLine();
                System.out.println("added: " + input);
                printLine();
            }

        }
    }
}