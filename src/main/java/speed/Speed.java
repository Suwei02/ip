package speed;

import speed.task.Deadline;
import speed.task.Event;
import speed.task.Task;
import speed.task.Todo;
import speed.exception.SpeedException;
import speed.storage.Storage;


import java.util.ArrayList;
import java.util.Scanner;

import  speed.ui.Ui;

/**
 * Main class for the Speed application.
 */

public class Speed {

    public static void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            Ui.noTasksMessage();
        } else {
            Ui.printLine();
            System.out.println("Here are the tasks in your list bro:  ");
            int taskCount = 1;
            for (Task task : tasks) {

                System.out.println((taskCount++) + "." + task.displayString());
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

    public static void printMarkedTask(ArrayList<Task> tasks, int markTaskIndex) {
            tasks.get(markTaskIndex).markAsDone();
            Ui.printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println(tasks.get(markTaskIndex).displayString());
            Ui.printLine();
    }

    public static void printUnmarkedTask(ArrayList<Task>  tasks, int unmarkTaskIndex) {
            tasks.get(unmarkTaskIndex).markAsNotDone();
            Ui.printLine();
            System.out.println("Ok, still waiting on this one bro:");
            System.out.println(tasks.get(unmarkTaskIndex).displayString());
            Ui.printLine();
    }

    public static void printDeletedTask(Task removedTask, int remainingTasksCount) {
        Ui.printLine();
        System.out.println("Gotchu bro! Deleting this task:");
        System.out.println(removedTask.displayString());
        System.out.println("Now you have " + remainingTasksCount + " tasks in the list.");
        Ui.printLine();
    }

    public static void main(String[] args) {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        //Create storage and load
        Storage storage = new Storage("./data/speed.txt");

        try {
            tasks = storage.load(); // loads saved tasks (or empty if file missing)
        } catch (SpeedException e) {
            Ui.printLine();
            System.out.println("Warning: could not load saved tasks. Starting with an empty list.");
            Ui.printLine();
            tasks = new ArrayList<>();
        }

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    Ui.printByeMessage();
                    break;

                } else if (input.equals("list")) {
                    printList(tasks);

                } else if (input.equals("mark") || input.startsWith("mark ")) {
                    int markTaskIndex = parseTaskIndex(input,tasks.size());
                    printMarkedTask(tasks, markTaskIndex);
                    storage.save(tasks);

                } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                    int unmarkTaskIndex = parseTaskIndex(input,tasks.size());
                    printUnmarkedTask(tasks, unmarkTaskIndex);
                    storage.save(tasks);


                } else if (input.equals("delete") ||  input.startsWith("delete ")) {
                    int deleteTaskIndex = parseTaskIndex(input,tasks.size());
                    Task removedTask = tasks.remove(deleteTaskIndex);
                    printDeletedTask(removedTask, tasks.size());
                    storage.save(tasks);

                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    String[] parts = input.split(" ", 2);
                    String description = parts.length < 2 ? "" : parts[1].trim();

                    if (description.isEmpty()) {
                        throw new SpeedException(Ui.ERROR_EMPTY_TODO);
                    }
                    Task newTask = new Todo(description);
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());
                    storage.save(tasks);

                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    //remove deadline from input by starting at index 8(deadline length).
                    Task newDeadline = getNewDeadline(input);
                    tasks.add(newDeadline);
                    printAddedTask(newDeadline, tasks.size());
                    storage.save(tasks);

                } else if (input.equals("event") || input.startsWith("event ")) {
                    Task newEvent = getNewEvent(input);
                    tasks.add(newEvent);
                    printAddedTask(newEvent, tasks.size());
                    storage.save(tasks);

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

    private static Task getNewEvent(String input) throws SpeedException {
        String eventContent = input.substring("event".length()).trim();
        String[] parts = eventContent.split(" /from | /to ", 3);

        // Check format
        if (parts.length < 3) {
            throw new SpeedException(Ui.EVENT_FORMAT_ERROR);
        }

        String description = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();

        Task newEvent = new Event(description, startTime, endTime);
        return newEvent;
    }

    private static Task getNewDeadline(String input) throws SpeedException {
        String deadlineContent = input.substring("deadline".length()).trim();
        String[] parts = deadlineContent.split(" /by", 2); //split at most by 2 parts

        //check format
        if (parts.length < 2) {
            throw new SpeedException(Ui.DEADLINE_FORMAT_ERROR);
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        Task newDeadline = new Deadline(description, deadline);
        return newDeadline;
    }

    // Helper
    private static boolean isValidTaskIndex(int idx, int total) {
        return idx >= 0 && idx < total;
    }

    //Helper to check validity of index
    private static int parseTaskIndex(String input, int totalTaskCount) throws SpeedException {
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

