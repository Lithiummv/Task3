package task3;

public class ConsoleMenu {
    public static void generateMenu(String[] moves) {
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length; i++)
            System.out.println(i + 1 + " - " + moves[i]);
        System.out.println("0 - exit");
    }
}
