import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean xTurn = true;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        while (true) {
            System.out.println("It is " + (xTurn ? "X" : "O") + "'s turn. Enter row and column (1-3) separated by a space:");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (isValidMove(row, col)) {
                board[row][col] = xTurn ? 'X' : 'O';
                printBoard();
                if (checkWin()) {
                    System.out.println((xTurn ? "X" : "O") + " wins!");
                    break;
                } else if (isBoardFull()) {
                    System.out.println("It is a tie!");
                    break;
                } else {
                    xTurn = !xTurn;
                }
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
        scanner.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean checkWin() {
        return checkRow(0) || checkRow(1) || checkRow(2) ||
               checkCol(0) || checkCol(1) || checkCol(2) ||
               checkDiag1() || checkDiag2();
    }

    static boolean checkRow(int row) {
        return board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2];
    }

    static boolean checkCol(int col) {
        return board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col];
    }

    static boolean checkDiag1() {
        return board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2];
    }

    static boolean checkDiag2() {
        return board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0];
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
