package game;

import java.util.Arrays;
import java.util.Map;

public final class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
//            Cell.X, '❌',
//            Cell.O, '⭕',
//            Cell.E, '⬛'
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '_'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m;
    private final int n;
    private final int k;
    private int empty;

    public TicTacToeBoard() {
        this.m = 3;
        this.n = 3;
        this.cells = new Cell[3][3];
        this.k = 3;
        this.empty = m * n;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public TicTacToeBoard(int m, int n, int k) {
        this.cells = new Cell[m][n];
        this.k = k;
        this.m = m;
        this.n = n;
        this.empty = m * n;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        empty--;

        if (empty == 0) {
            return Result.DRAW;
        }

        int[] results = new int[4];
        int[] del_r = new int[] {0, 1, 1, -1};
        int[] del_c = new int[] {1, 0, 1, 1};

        for (int i = 0; i < 4; i++) {
            int temp_r = move.getRow() - k * del_r[i];
            int temp_c = move.getColumn() - k * del_c[i];
            for (int j = 0; j < 2 * k - 1; j++) {
                temp_r += del_r[i];
                temp_c += del_c[i];
                if (temp_r >= 0 && temp_c >= 0 && temp_r < m && temp_c < n && cells[temp_r][temp_c] == turn) {
                    results[i]++;
                } else {
                    results[i] = 0;
                }
                if (results[i] == k) {
                    return Result.WIN;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int temp_r = move.getRow() - 4 * del_r[i];
            int temp_c = move.getColumn() - 4 * del_c[i];
            for (int j = 0; j < 2 * 4 - 1; j++) {
                temp_r += del_r[i];
                temp_c += del_c[i];
                if (temp_r >= 0 && temp_c >= 0 && temp_r < m && temp_c < n && cells[temp_r][temp_c] == turn) {
                    results[i]++;
                } else {
                    results[i] = 0;
                }
                if (results[i] == 4) {
                    return Result.EXTRA;
                }
            }
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public void clear() {
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int rws = (int) Math.ceil(Math.log10(m - 1));
        int cws = (int) Math.ceil(Math.log10(n - 1));
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rws; i++) {
            sb.append(" ");
        }
        for (int i = 0; i < cws; i++) {
            sb.append(" ");
        }
        for (int r = 0; r < n; r++) {
            for (int i = 0; r > 0 && i < cws + 1 - (int) Math.ceil(Math.log10(r + 1)); i++) {
                sb.append(" ");
            }
            sb.append(r);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            for (int i = 0; i < rws - (int) Math.ceil(Math.log10(r + 2 - Math.signum(r))); i++) {
                sb.append(" ");
            }
            sb.append(r);
            for (int c = 0; c < n; c++) {
                for (int i = 0; i < cws; i++) {
                    sb.append(" ");
                }
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}