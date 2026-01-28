import java.util.Scanner;

public class Speed {

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printList(String[] list, int taskNumber) {
        if(taskNumber == 0) {
            System.out.println("NO TASKS YET CHAT!");
        }
        else {
            for (int currentTaskNumber = 1; currentTaskNumber <= taskNumber; currentTaskNumber++) {
                System.out.println(currentTaskNumber + ". " + list[currentTaskNumber - 1]);
            }
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
        String[] tasks = new String[100];
        int taskNumber = 0;

        while(true){
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printLine();
                System.out.println("PEACE OUT BROTHER!🫡 GREEN APPLE!!!🍏🍏");
                printLine();
                break;
            }
            else if (input.equals("list")) {
                printLine();
                printList(tasks, taskNumber);
                printLine();
            }
            else {
                tasks[taskNumber++] = input;
                printLine();
                System.out.println("added: " + input);
                printLine();
            }

        }

    }
}