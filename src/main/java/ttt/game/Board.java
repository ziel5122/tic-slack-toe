package ttt.game;

import ttt.messages.Info;

import javax.ws.rs.core.Response;

public class Board {

    private int[][] board;

    public Board() {
        board = new int[4][4];
    }

    public Response checkEnd(String user_id, int value) {
        if (rowWin(value) || colWin(value) || diagWin(value)) {
            return Info.win(user_id, this);
        } else if (scratch()) {
            return Info.scratch(this);
        }

        return null;
    }

    private boolean colWin(int value) {
        if (board[1][1] == value && board[2][1] == value && board[3][1] == value)
            return true;

        if (board[1][2] == value && board[2][2] == value && board[3][2] == value)
            return true;

        if (board[1][3] == value && board[2][3] == value && board[3][3] == value)
            return true;

        return false;
    }

    private boolean diagWin(int value) {
        if (board[2][2] == value) {
            if (board[1][1] == value && board[3][3] == value)
                return true;

            if (board[1][3] == value && board[3][1] == value)
                return true;
        }

        return false;
    }

    public char getLetter(int square_value) {
        switch(square_value) {
            case -1: return 'X';
            case 1: return 'O';
        }

        return ' ';
    }

    public boolean move(String square_coords, int value) {
        int two_digit = Integer.parseInt(square_coords);
        int row = two_digit / 10;
        int col = two_digit % 10;

        if (row < 1 || row > 3 || col < 1 || col > 3)
            return false;

        if (board[row][col] != 0) {
            return false;
        }

        board[row][col] = value;

        return true;
    }

    private boolean rowWin(int value) {
        if (board[1][1] == value && board[1][2] == value && board[1][3] == value)
            return true;

        if (board[2][1] == value && board[2][2] == value && board[2][3] == value)
            return true;

        if (board[3][1] == value && board[3][2] == value && board[3][3] == value)
            return true;

        return false;
    }

    private boolean scratch() {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String board_string = "```-------------\n";
        board_string += "| " + getLetter(board[1][1]) + " | " + getLetter(board[1][2]) + " | " + getLetter(board[1][3]) + " |\n";
                 board_string += "|---+---+---|\n";
        board_string += "| " + getLetter(board[2][1]) + " | " + getLetter(board[2][2]) + " | " + getLetter(board[2][3]) + " |\n";
                 board_string += "|---+---+---|\n";
        board_string += "| " + getLetter(board[3][1]) + " | " + getLetter(board[3][2]) + " | " + getLetter(board[3][3]) + " |\n";
                 board_string += "-------------```";

        return board_string;
    }
}
