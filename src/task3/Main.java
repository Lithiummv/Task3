package task3;

import java.util.Arrays;

public class Main {
    public static String[] getUniqueArgs(String[] args) {
        return Arrays.stream(args).distinct().toArray(String[]::new);
    }

    public static void checkArgs(String[] args) throws Exception {
        if (args.length <= 2)
            throw new Exception("Enter the number of moves(non-repeating) greater than two");
        if (args.length != getUniqueArgs(args).length)
            throw new Exception("Enter non-repeating moves");
        if (args.length % 2 == 0)
            throw new Exception("Enter an odd number of moves(non-repeating)");
    }

    public static void main(String[] args) {
        try {
            checkArgs(args);
            Game.StartGame(args);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
