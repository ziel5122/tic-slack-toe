package ttt.game;

public class Board {

    private int[][] board;

    public Board() {
        board = new int[4][4];
    }

    public char getLetter(int square_value) {
        switch(square_value) {
            case -1: return 'X';
            case 1: return 'O';
        }

        return ' ';
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
