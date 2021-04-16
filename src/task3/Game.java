package task3;

import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static int getUsersMove(String[] moves) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your move: ");
        int moveIndex = input.nextInt() - 1;
        if (moveIndex == -1)
            return moves.length;
        System.out.println("Your move: " + moves[moveIndex]);
        return moveIndex;
    }

    public static int getComputersMove(String[] moves) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(moves.length);
    }

    public static int getIndexOfComputersMove(String computersMove, String[] moves) {
        List<String> movesList = new ArrayList<String>(Arrays.asList(moves));
        return movesList.indexOf(computersMove);
    }

    public static String[] shiftArrayOfMovesLeft(String[] moves, int shift) {
        for (int i = 0; i > shift; i--) {
            String buffer = moves[moves.length - 1];
            moves[moves.length - 1] = moves[0];
            for (int j = 1; j < moves.length - 1; j++) {
                moves[j - 1] = moves[j];
            }
            moves[moves.length - 2] = buffer;
        }
        return moves;
    }

    public static String[] shiftArrayOfMovesRight(String[] moves, int shift) {
        for (int i = 0; i < shift; i++) {
            String buffer = moves[0];
            moves[0] = moves[moves.length - 1];
            for (int j = 1; j < moves.length - 1; j++) {
                moves[moves.length - j] = moves[moves.length - j - 1];
            }
            moves[1] = buffer;
        }
        return moves;
    }

    public static void getGameResult(String[] moves, String computersMove, int usersMove) {
        if (usersMove < (int) Math.ceil(moves.length / 2.0)) {
            moves = shiftArrayOfMovesRight(moves, moves.length / 2 - usersMove);
        } else {
            moves = shiftArrayOfMovesLeft(moves, moves.length / 2 - usersMove);
        }
        int computersMoveIndex = getIndexOfComputersMove(computersMove, moves);
        System.out.println("Computer's move: " + moves[computersMoveIndex]);
        if (computersMoveIndex == moves.length / 2)
            System.out.println("Draw!");
        else if (computersMoveIndex > moves.length / 2)
            System.out.println("You lose!");
        else
            System.out.println("You win!");
    }

    public static void StartGame(String[] moves) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] randomKey = RandomKey.generateRandomKey();
        String computersMove = moves[getComputersMove(moves)];
        byte[] hmac = HMAC.getHMAC(randomKey, computersMove);
        System.out.println("HMAC:" + DatatypeConverter.printHexBinary(hmac));
        ConsoleMenu.generateMenu(moves);
        int usersMove = getUsersMove(moves);
        if (usersMove == moves.length)
            System.exit(0);
        getGameResult(moves, computersMove, usersMove);
        System.out.println("HMAC key:" + DatatypeConverter.printHexBinary(randomKey));
    }
}
