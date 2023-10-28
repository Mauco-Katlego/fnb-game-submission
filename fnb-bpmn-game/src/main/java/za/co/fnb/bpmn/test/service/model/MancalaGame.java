package za.co.fnb.bpmn.test.service.model;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MancalaGame {

    public void printWin(Player winner) {
    }

    public enum Player {
        One ((board.length - 2) / 2, board.length - 1),
        Two (board.length - 1, (board.length - 2) / 2);

        private int kalahLocation;
        private int kalahSkip;

        Player(int location, int skip) {
            kalahLocation = location;
            kalahSkip = skip;
        }

        int getKalah() {
            return board[kalahLocation];
        }

        int getKalahLoc() {
            return kalahLocation;
        }

        int getSkip() {
            return kalahSkip;
        }
    }

    private static int[] board;
    private Player turn;
    private final int STARTING_AMOUNT = 4;

    public MancalaGame() {
        board = new int[14];
        turn = Player.One;
        reset();
    }

    public int[] getBoard() {
        return board;
    }

    public Player getTurn() {
        return turn;
    }

    public void reset() {
        for (int i = 0; i < board.length; i++) {
            board[i] = STARTING_AMOUNT;
        }
        for (Player p : Player.values()) {
            board[p.getKalahLoc()] = 0;
        }
        turn = Player.One;
    }

    public void printBoard() {
        System.out.println("    (1) (2) (3) (4) (5) (6) ");
        System.out.println("-------------------------------");
        System.out.print("|  ");
        for (int i = board.length - 2; i >= board.length / 2; i--) {
            System.out.print("| ");
            System.out.printf("%-2s", board[i]);
        }
        System.out.print("|  |\n|");
        System.out.printf("%-2d|-----------------------|%2d|\n", Player.Two.getKalah(), Player.One.getKalah());
        System.out.print("|  ");
        for (int i = 0; i < (board.length / 2) - 1; i++) {
            System.out.print("| ");
            System.out.printf("%-2s", board[i]);
        }
        System.out.println("|  |");
        System.out.println("-------------------------------");
        System.out.println("    (1) (2) (3) (4) (5) (6) ");
    }

    public boolean isOver() {
        return sum(Player.One) == 0 || sum(Player.Two) == 0;
    }

    public Player getWinner() {
        Player winner = null;
        if (isOver()) {
            for (Player p : Player.values()) {
                board[p.getKalahLoc()] += sum(p);
            }
            int totalOne = Player.One.getKalah();
            int totalTwo = Player.Two.getKalah();
            if (totalOne > totalTwo) {
                winner = Player.One;
            } else if (totalOne < totalTwo) {
                winner = Player.Two;
            }
            for (int i = 0; i < board.length; i++) {
                if (i != Player.One.getKalahLoc() && i != Player.Two.getKalahLoc()) {
                    board[i] = 0;
                }
            }
        }
        return winner;
    }

    public int sum(Player m) {
        int sum = 0;
        int start = (m.getSkip() + 1) % board.length;
        for (int i = start; i < start + (board.length - 1) / 2; i++) {
            sum += board[i];
        }
        return sum;
    }

    public void switchTurn() {
        if (turn == Player.One) {
            turn = Player.Two;
        } else {
            turn = Player.One;
        }
    }

    public int markBoard(int pos) {
        int handAmount = board[pos];
        board[pos] = 0;
        while (handAmount > 0) {
            pos = (pos + 1) % board.length;
            handAmount--;
            if (pos == turn.getSkip()) {
                pos = (pos + 1) % board.length;
            }
            board[pos]++;
        }
        boolean taken = false;
        if (pos != turn.getKalahLoc() && board[pos] == 1 && board[getOpposite(pos)] != 0) {
            board[turn.getKalahLoc()] += board[pos] + board[getOpposite(pos)];
            board[pos] = 0;
            board[getOpposite(pos)] = 0;
            taken = true;
        }
        printBoard();
        if (taken) {
            System.out.println("Pieces taken!");
        } else if (!isOver() && pos == turn.getKalahLoc()) {
            System.out.println("Go again player " + turn + ". You landed in the Kalah.");
            return 1;
        }
        return 0;
    }

    private int getOpposite(int pos) {
        return board.length - 2 - pos;
    }

    public int readValue() {
        Scanner s = new Scanner(System.in);

        int position = 2;
        boolean valid = false;
        while (!valid) {
            try {
                position = s.nextInt();
                if (position < 1 || position > 6) {
                    System.out.println("Invalid Position, input again:");
                } else {
                    if (turn == Player.One) {
                        position--;
                    } else {
                        position = board.length - 1 - position;
                    }
                    if (board[position] == 0) {
                        System.out.print("Spot is empty. Choose another spot:");
                    } else {
                        valid = true;
                    }
                }
            } catch (InputMismatchException e) {
                s.next(); // Throw away the offending input
                System.out.println("Invalid Position, input again:");
            }
        }
        return position;
    }





}
