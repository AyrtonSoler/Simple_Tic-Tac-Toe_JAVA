package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "_________"; // Start with an empty grid

        printGrid(userInput);

        boolean isXTurn = true;
        while (true) {
            System.out.print("Enter the coordinates (row and column): ");
            String coordinates = scanner.nextLine();

            if (!coordinates.matches("\\d \\d")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String[] parts = coordinates.split(" ");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int index = (row - 1) * 3 + (col - 1);

            if (userInput.charAt(index) != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            userInput = userInput.substring(0, index) + (isXTurn ? 'X' : 'O') + userInput.substring(index + 1);
            printGrid(userInput);

            String result = analyzeGameState(userInput);
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }

            isXTurn = !isXTurn; // Switch turn
        }

        scanner.close();
    }

    public static void printGrid(String state) {
        System.out.println("---------");
        System.out.println("| " + state.charAt(0) + " " + state.charAt(1) + " " + state.charAt(2) + " |");
        System.out.println("| " + state.charAt(3) + " " + state.charAt(4) + " " + state.charAt(5) + " |");
        System.out.println("| " + state.charAt(6) + " " + state.charAt(7) + " " + state.charAt(8) + " |");
        System.out.println("---------");
    }

    public static String analyzeGameState(String state) {
        if (isImpossible(state)) {
            return "Impossible";
        } else if (isWinner(state, 'X')) {
            return "X wins";
        } else if (isWinner(state, 'O')) {
            return "O wins";
        } else if (state.contains("_")) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    public static boolean isImpossible(String state) {
        int xCount = 0;
        int oCount = 0;

        for (char c : state.toCharArray()) {
            if (c == 'X') {
                xCount++;
            } else if (c == 'O') {
                oCount++;
            }
        }

        boolean xWins = isWinner(state, 'X');
        boolean oWins = isWinner(state, 'O');

        return Math.abs(xCount - oCount) > 1 || (xWins && oWins);
    }

    public static boolean isWinner(String state, char player) {
        return (state.charAt(0) == player && state.charAt(1) == player && state.charAt(2) == player) ||
                (state.charAt(3) == player && state.charAt(4) == player && state.charAt(5) == player) ||
                (state.charAt(6) == player && state.charAt(7) == player && state.charAt(8) == player) ||
                (state.charAt(0) == player && state.charAt(3) == player && state.charAt(6) == player) ||
                (state.charAt(1) == player && state.charAt(4) == player && state.charAt(7) == player) ||
                (state.charAt(2) == player && state.charAt(5) == player && state.charAt(8) == player) ||
                (state.charAt(0) == player && state.charAt(4) == player && state.charAt(8) == player) ||
                (state.charAt(2) == player && state.charAt(4) == player && state.charAt(6) == player);
    }
}
