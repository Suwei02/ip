package speed;

import speed.task.Deadline;
import speed.task.Event;
import speed.task.Task;
import speed.task.Todo;

import java.util.Scanner;

import  speed.ui.Ui;

/**
 * Main class for the Speed application.
 */

public class Speed {

    private static final int MAX_TASKS = 100;

    public static void printList(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            Ui.printLine();
            System.out.println("NO TASKS YET! SIUUU!");
            Ui.printLine();
        } else {
            Ui.printLine();
            System.out.println("Here are the tasks in your list bro:  ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toDisplayString());
            }
            Ui.printLine();
        }
    }

    public static void printAddedTask(Task task, int totalTaskCount) {
        Ui.printLine();
        System.out.println("Gotcha, I've added this task:");
        System.out.println(task.toDisplayString());
        System.out.println("Now you have " + totalTaskCount + " tasks in the list.");
        Ui.printLine();
    }

    public static void printMarkedTask(Task[] tasks, int markTaskIndex, int totalTaskCount) {
        if (!isValidTaskIndex(markTaskIndex, totalTaskCount) ) {
            Ui.printLine();
            System.out.println("Invalid task number man.");
            Ui.printLine();
        } else {
            tasks[markTaskIndex].markAsDone();
            Ui.printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println(tasks[markTaskIndex].toDisplayString());
            Ui.printLine();
        }
    }

    public static void printUnmarkedTask(Task[] tasks, int unmarkTaskIndex, int totalTaskCount) {
        if (!isValidTaskIndex(unmarkTaskIndex, totalTaskCount)) {
            Ui.printLine();
            System.out.println("Invalid task number man.");
            Ui.printLine();
        } else {
            tasks[unmarkTaskIndex].markAsNotDone();
            Ui.printLine();
            System.out.println("Ok, still waiting on this one bro:");
            //System.out.println("[ ] " + tasks[unmarkTaskIndex].getDescription());
            System.out.println(tasks[unmarkTaskIndex].toDisplayString());

            Ui.printLine();
        }
    }



    // Helper to check validity of index
    private static boolean isValidTaskIndex(int idx, int total) {
        return idx >= 0 && idx < total;
    }

    public static void main(String[] args) {
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int totalTasksCount = 0;

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                Ui.bye();
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
                    Ui.printDeadlineFormatError();
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
                    Ui.printEventFormatError();
                    continue;
                }

                String description = parts[0].trim();
                String startTime = parts[1].trim();
                String endTime = parts[2].trim();

                tasks[totalTasksCount++] = new Event(description, startTime, endTime);
                printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

            } else {
                Ui.printErrorMsg();
            }
        }
    }




}


