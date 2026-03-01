package speed;

import speed.task.Task;
import speed.task.TaskList;
import speed.exception.SpeedException;
import speed.storage.Storage;
import speed.parser.Parser;

import java.util.Scanner;

import  speed.ui.Ui;

/**
 * Main class for the Speed application.
 */

public class Speed {

    public static void printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            Ui.noTasksMessage();
        } else {
            Ui.printLine();
            System.out.println("Here are the tasks in your list bro:  ");
            int taskCount = 1;
            for (Task task : tasks.getTasks()) {
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

    public static void printMarkedTask(TaskList tasks, int markTaskIndex) {
            tasks.markTask(markTaskIndex);
            Ui.printLine();
            System.out.println("HELL YEAH! ANOTHER TASK DONE:");
            System.out.println(tasks.getTask(markTaskIndex).displayString());
            Ui.printLine();
    }

    public static void printUnmarkedTask(TaskList tasks, int unmarkTaskIndex) {
            tasks.unmarkTask(unmarkTaskIndex);
            Ui.printLine();
            System.out.println("Ok, still waiting on this one bro:");
            System.out.println(tasks.getTask(unmarkTaskIndex).displayString());
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
        TaskList tasks;

        //Create storage, use relative path
        Storage storage = new Storage("./data/speed.txt");

        try {
            tasks = new TaskList(storage.load()); // loads saved tasks (or empty if file missing)
        } catch (SpeedException e) {
            Ui.printLoadingWarning();
            tasks = new TaskList();
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
                    int markTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    printMarkedTask(tasks, markTaskIndex);
                    storage.save(tasks.getTasks());

                } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                    int unmarkTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    printUnmarkedTask(tasks, unmarkTaskIndex);
                    storage.save(tasks.getTasks());


                } else if (input.equals("delete") ||  input.startsWith("delete ")) {
                    int deleteTaskIndex = Parser.parseTaskIndex(input, tasks.size());
                    Task removedTask = tasks.deleteTask(deleteTaskIndex);
                    printDeletedTask(removedTask, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("todo") || input.startsWith("todo ")) {
                    Task newTask = Parser.parseTodo(input);
                    tasks.addTask(newTask);
                    printAddedTask(newTask, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                    Task newDeadline = Parser.parseDeadline(input);
                    tasks.addTask(newDeadline);
                    printAddedTask(newDeadline, tasks.size());
                    storage.save(tasks.getTasks());

                } else if (input.equals("event") || input.startsWith("event ")) {
                    Task newEvent = Parser.parseEvent(input);
                    tasks.addTask(newEvent);
                    printAddedTask(newEvent, tasks.size());
                    storage.save(tasks.getTasks());

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

}

