package tictactoe;

import java.util.*;

public class Main {
    private static boolean isX = true;
    public static void main(String[] args) {
        // write your code here
        char[][] matrix = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = '_';
            }
        }
        displayGame(matrix);
        while (true) {
            if (isXwin(matrix) || isOwin(matrix) || isDraw(matrix) ) {
                result(matrix);
                break;
            }
            matrix = insertMatrix(matrix);
            displayGame(matrix);
        }
    }
    public static void displayGame(char[][] matrix){
        System.out.print("---------\n");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.print("---------\n");
    }
    public static char[][] insertMatrix(char[][] matrix) {
        char[][] res = new char[3][3];
        res = matrix;
        int i;
        int j;
        String input;
        boolean isInserted = false;
        char play;
        if (isX) {
            play = 'X';
            isX = !isX;
        }else{
            play = 'O';
            isX = !isX;
        }
        do {
            System.out.print("Enter the coordinates: ");
            input = new Scanner(System.in).nextLine();
            if (input.split(" ").length > 1) {
                if (input.trim().split(" ")[0].trim().matches("\\d") || input.split(" ")[1].trim().equals("\\d")) {
                    i = Integer.parseInt(input.split(" ")[0]);
                    j = Integer.parseInt(input.split(" ")[1]);
                    if (i <= 3 && j <= 3) {
                        if (matrix[3 - j][i - 1] == '_') {
                            res[3 - j][i - 1] = play;
                            isInserted = true;

                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }

                } else {
                    System.out.println("You should enter numbers!");
                }
            }else{
                System.out.println("You should enter numbers!");
            }

            if (isInserted) {
                break;
            }
        } while (true);
        return res;
    }

    public static int differenceXO(char[][] matrix) {
        int xs = 0;
        int os = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 'X') {
                    xs++;
                }
                if (matrix[i][j] == 'O') {
                    os++;
                }
            }
        }
        return Math.abs(xs - os);
    }

    public static void result(char[][] matrix) {

        if (differenceXO(matrix) <= 1) {
            if (isOwin(matrix) && isXwin(matrix)) {
                System.out.println("Impossible");
                return;
            }
            if (!isOwin(matrix) && !isXwin(matrix) && !isNotFinished(matrix)) {
                System.out.println("Draw");
                return;
            }
            if (isXwin(matrix)) {
                System.out.println("X wins");
                return;
            }
            if (isOwin(matrix)) {
                System.out.println("O wins");
            } else {
                System.out.println("Game not finished");
            }
        } else {
            System.out.println("Impossible");
        }
    }

    public static boolean isOwin(char[][] matrix) {
        boolean isWin = false;
        int diag1 = 0;
        int diag2 = 0;
        // check x win
        for (int i = 0; i < 3; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < 3; j++) {
                if (i == j && matrix[i][j] == 'O') {
                    diag1++;
                }
                if (i + j == 2 && matrix[i][j] == 'O') {
                    diag2++;
                }
                if (matrix[i][j] == 'O') {
                    x++;
                    if (x == 3) {
                        isWin = true;
                    }
                }
                if (matrix[j][i] == 'O') {
                    y++;
                    if (y == 3) {
                        isWin = true;
                    }
                }
            }
        }
        if (diag1 == 3 || diag2 == 3) {
            isWin = true;
        }
        return isWin;
    }

    public static boolean isXwin(char[][] matrix) {
        boolean isWin = false;
        int diag1 = 0;
        int diag2 = 0;
        // check x win
        for (int i = 0; i < 3; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < 3; j++) {
                if (i == j && matrix[i][j] == 'X') {
                    diag1++;
                }
                if (i + j == 2 && matrix[i][j] == 'X') {
                    diag2++;
                }
                if (matrix[i][j] == 'X') {
                    x++;
                    if (x == 3) {
                        isWin = true;
                    }
                }
                if (matrix[j][i] == 'X') {
                    y++;
                    if (y == 3) {
                        isWin = true;
                    }
                }

            }
        }
        if (diag1 == 3 || diag2 == 3) {
            isWin = true;
        }
        return isWin;
    }

    public static boolean isNotFinished(char[][] matrix) {
        // check Finish
        boolean isItNotfinished = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == '_') {
                    isItNotfinished = true;
                }
            }
        }

        return isItNotfinished;
    }
    public static boolean isDraw(char[][]matrix) {
        // check Finish

        boolean res = false;
        if (!isXwin(matrix) && !isOwin(matrix) && !isNotFinished(matrix)) {
            res = true;
        }

        return res;
    }
}