package Controller;

import java.util.Queue;
import java.util.Scanner;
import Model.BingoModel;
import View.UI;

public class Logic {
    private final BingoModel[] bingoModels;
    private final UI view;
    private boolean firstRound = true;
    public final String GREEN = "\033[0;32m"; // GREEN
    public final String RED = "\033[0;31m"; // RED
    public final String RESET = "\033[0m"; // Text
    public final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN

    public Logic(UI view) {
        this.view = view;
        this.bingoModels = new BingoModel[4]; // Create an array to hold four BingoModel instances
        for (int i = 0; i < 4; i++) {
            bingoModels[i] = new BingoModel(); // Create a BingoModel instance for each index
        }
    }

    public void playGame() {
        if (firstRound) {
            initialBingoCards(); // Print all four bingo cards
            firstRound = false;
            if (!view.promptToPlay()) {
                return;
            }
        }
        do {
            shuffleBalls();
        } while (promptToContinue());

        // Exit the program after winning
        System.out.println("  ".repeat(15) + GREEN + "Congratulations! You've won the game!" + RESET);
        System.exit(0);
    }

    private void shuffleBalls() {
        printShuffledNumbers(); // Display current queue before drawing

        if (!bingoModels[0].getShuffledNumbers().isEmpty()) {
            int drawnNumber = bingoModels[0].getShuffledNumbers().poll(); // Draw a single number
            printDrawnNumber();
            for (BingoModel cardModel : bingoModels) {
                cardModel.getDrawnNumbers().add(drawnNumber);
                cardModel.markNumber(drawnNumber);
            }
            System.out.println("\n" + "  ".repeat(15) + YELLOW_UNDERLINED + "Drawing a number..." + RESET);
            System.out
                    .println("  ".repeat(15) + "In Letter " + getRange(drawnNumber) + ", number " + drawnNumber + "\n");
            int cardIndex = 1;
            for (BingoModel cardModel : bingoModels) {
                System.out.println("\n" + "  ".repeat(30) + "BINGO Card " + cardIndex + ":");
                view.printBingoCard(cardModel.getCard());
                cardIndex++;
            }

            // Check for winning condition after each draw
            for (BingoModel cardModel : bingoModels) {
                if (checkForWin(cardModel.getCard())) {
                    System.out.println("  ".repeat(15) + GREEN + "Congratulations! You've won the game!" + RESET);
                    return; // Exit the game loop if a win is detected
                }
            }
        }
    }

    private void printShuffledNumbers() {
        Queue<Integer> shuffledQueue = bingoModels[0].getShuffledNumbers(); // Get current queue
        if (shuffledQueue.isEmpty()) {
            System.out.println("  ".repeat(15) + "Shuffled Numbers: None"); // Display when the queue is empty
        } else {
            System.out.print("Shuffled Numbers: ");
            for (Integer number : shuffledQueue) {
                String range = getRange(number); // Determine the range for each number
                System.out.print(range + number + " "); // Print the number with its range
            }
            System.out.println(); // Print a newline after all numbers are printed
        }
    }

    public boolean promptToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "  ".repeat(15) + "Do you still want to continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    private void printDrawnNumber() {
        // Print the drawn numbers for all cards
        System.out.println("\n" + "  ".repeat(15) + GREEN_UNDERLINED + "Drawn numbers:" + RESET);
        for (Integer drawnNum : bingoModels[0].getDrawnNumbers()) {
            String rangeNum = getRange(drawnNum);
            System.out.println("  ".repeat(15) + "In Letter " + rangeNum + ", number " + drawnNum);
        }
    }

    private void initialBingoCards() {
        int cardNumber = 1; // Variable to keep track of card numbers

        // Loop through each BingoModel to print their respective cards with card
        // numbers
        for (BingoModel cardModel : bingoModels) {
            System.out.println("\n" + "  ".repeat(30) + "BINGO Card " + cardNumber + ":"); // Display card number
            view.printBingoCard(cardModel.getCard()); // Print the actual bingo card
            cardNumber++; // Increment for the next card
        }
    }

    private boolean checkForWin(String[][] card) {
        // Check for horizontal win
        for (int row = 0; row < card.length; row++) {
            if (checkLine(card[row])) {
                return true;
            }
        }

        // Check for vertical win
        for (int col = 0; col < card[0].length; col++) {
            String[] column = new String[card.length];
            for (int row = 0; row < card.length; row++) {
                column[row] = card[row][col];
            }
            if (checkLine(column)) {
                return true;
            }
        }

        // Check for diagonal win (top-left to bottom-right)
        String[] diagonal1 = new String[card.length];
        for (int i = 0; i < card.length; i++) {
            diagonal1[i] = card[i][i];
        }
        if (checkLine(diagonal1)) {
            return true;
        }

        // Check for diagonal win (top-right to bottom-left)
        String[] diagonal2 = new String[card.length];
        for (int i = 0; i < card.length; i++) {
            diagonal2[i] = card[i][card.length - 1 - i];
        }
        return checkLine(diagonal2);
    }

    private boolean checkLine(String[] line) {
        for (String cell : line) {
            if (!cell.contains("-")) {
                return false; // Line is not completely marked
            }
        }
        return true; // All cells in the line are marked
    }

    private String getRange(int number) {
        if (number >= 1 && number <= 15) {
            return "B";
        } else if (number >= 16 && number <= 30) {
            return "I";
        } else if (number >= 31 && number <= 45) {
            return "N";
        } else if (number >= 46 && number <= 60) {
            return "G";
        } else {
            return "O";
        }
    }
}
