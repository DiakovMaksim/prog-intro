package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lineScanner = new Scanner(System.in);
        System.out.println("Enter three positive numbers: m, n, k and rounds, where m x n - size of the board, you need to get k in row for get round and you need to get round rounds for win:");
        String line = lineScanner.nextLine();
        Scanner scanner = new Scanner(line);
        int[] mnk = new int[4];
        for (int i = 0; i < 4; i++) {
            while (!scanner.hasNextInt()) {
                line = lineScanner.nextLine();
                scanner = new Scanner(line);
            }
            mnk[i] = scanner.nextInt();
            if (mnk[i] < 0) {
                mnk[i] = 0;
            }
        }
        if (mnk[2] == 1 || mnk[0] * mnk[1] * mnk[2] * mnk[3] == 0 || mnk[2] > Math.max(mnk[0], mnk[1])) {
            System.out.println("Error: invalid enter");
            return;
        }
        int Xwins = 0;
        int Owins = 0;
        Game game;
        Board board = new TicTacToeBoard(mnk[0], mnk[1], mnk[2]);
        do {
            int result;
            boolean XFirst;
            if ((Xwins + Owins) % 2 == 0) {
                game = new Game(true, new HumanPlayer(), new RandomPlayer(mnk[0], mnk[1]));
                XFirst = true;
            } else {
                game = new Game(true, new RandomPlayer(mnk[0], mnk[1]), new HumanPlayer());
                XFirst = false;
            }
            result = game.play(board);
            board.clear();
            if (result == 1 && XFirst || result == 2 && !XFirst) {
                Xwins++;
            } else if (result == 2 || result == 1) {
                Owins++;
            }
        } while (Xwins < mnk[3] && Owins < mnk[3]);
        if (Xwins == mnk[3]) {
            System.out.println("1st player won the match");
        } else {
            System.out.println("2nd player won the match");
        }
    }
}