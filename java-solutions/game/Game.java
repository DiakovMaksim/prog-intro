package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        while (true) {
            int result1;
            do
            {
                result1 = move(board, player1, 1);
            } while (result1 == 4);
            if (result1 != -1) {
                return result1;
            }
            int result2;
            do {
                result2 = move(board, player2, 2);
            } while (result2 == 4);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:\n" + board);
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 3 - no;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else if (result == Result.EXTRA) {
                return 4;
            } else {
                return -1;
            }
        } catch (Exception e) {
            log("Player " + no + " lose");
            return 3 - no;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
