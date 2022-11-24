package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int moveRow;
            int moveCol;
            Scanner sc = new Scanner(in.nextLine());
            while (!sc.hasNextInt()) {
                System.out.println("Invalid row");
                sc = new Scanner(in.nextLine());
            }
            moveRow = sc.nextInt();
            while (!sc.hasNextInt()) {
                System.out.println("Invalid col");
                sc = new Scanner(in.nextLine());
            }
            moveCol = sc.nextInt();
            final Move move = new Move(moveRow, moveCol, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
