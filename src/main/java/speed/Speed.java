package speed;

import speed.task.Deadline;
import speed.task.Event;
import speed.task.Task;
import speed.task.Todo;
import speed.exception.SpeedException;

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
                System.out.println((i + 1) + "." + tasks[i].displayString());
            }
            Ui.printLine();
        }
    }

    public static void printAddedTask(Task task, int totalTaskCount) {
        Ui.printLine();
        System.out.println("Gotcha, I've added this task:");
        System.out.println(task.displayString());
        System.out.println("Now you have " + totalTaskCount + " tasks in the list.");
        Ui.printLine();
    }

    public static void printMarkedTask(Task[] tasks, int markTaskIndex, int totalTaskCount) {
            tasks[markTaskIndex].markAsDone();
            Ui.printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println(tasks[markTaskIndex].displayString());
            Ui.printLine();
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
            System.out.println(tasks[unmarkTaskIndex].displayString());
            Ui.printLine();
        }
    }


    public static void main(String[] args) {
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int totalTasksCount = 0;

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    Ui.printByeMessage();
                    break;

                } else if (input.equals("list")) {
                    printList(tasks, totalTasksCount);

                } else if (input.equals("mark") || input.startsWith("mark ")) {
                    int markTaskIndex = parseTaskIndex(input,totalTasksCount);
                    printMarkedTask(tasks, markTaskIndex, totalTasksCount);

                } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                    int unmarkTaskIndex = parseTaskIndex(input,totalTasksCount);
                    printUnmarkedTask(tasks, unmarkTaskIndex, totalTasksCount);
                    //Ensures 'todoo read book' is not allowed, and throws an ERROR_EMPTY_TODO for empty description
                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    String[] parts = input.split(" ", 2);
                    String description = parts.length < 2 ? "" : parts[1].trim();
                    if (description.isEmpty()) {
                        throw new SpeedException(Ui.ERROR_EMPTY_TODO);
                    }
                    tasks[totalTasksCount++] = new Todo(description);
                    printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    //remove deadline from input
                    String deadlineContent = input.substring("deadline".length()).trim();
                    String[] parts = deadlineContent.split(" /by", 2); //split at most by 2 parts

                    //check format
                    if (parts.length < 2) {
                        throw new SpeedException(Ui.DEADLINE_FORMAT_ERROR);
                    }

                    String description = parts[0].trim();
                    String deadline = parts[1].trim();

                    tasks[totalTasksCount++] = new Deadline(description, deadline);
                    printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

                } else if (input.equals("event") || input.startsWith("event ")) {
                    String eventContent = input.substring("event".length()).trim();
                    String[] parts = eventContent.split(" /from | /to ", 3);

                    // Check format
                    if (parts.length < 3) {
                        throw new SpeedException(Ui.EVENT_FORMAT_ERROR);
                    }

                    String description = parts[0].trim();
                    String startTime = parts[1].trim();
                    String endTime = parts[2].trim();

                    tasks[totalTasksCount++] = new Event(description, startTime, endTime);
                    printAddedTask(tasks[totalTasksCount - 1], totalTasksCount);

                } else if (input.equals("help")) {
                    Ui.printCommandList();
                } else {
                    throw new SpeedException(Ui.ERROR_UNKNOWN_COMMAND);
                }
            } catch (SpeedException e) {
                Ui.showError(e.getMessage());
            }
        }
    }
    // Helper
    private static boolean isValidTaskIndex(int idx, int total) {
        return idx >= 0 && idx < total;
    }

    //Helper to check validity of index
    private static int parseTaskIndex (String input, int totalTaskCount) throws SpeedException {
        String[] parts = input.trim().split("\\s+"); //splits string by one or more spaces
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
}

