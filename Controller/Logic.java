package Controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import Model.BingoModel;
import View.UI;

public class Logic {
    private final BingoModel[] bingoModels;
    private final UI view;
    private boolean firstRound = true;

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
    }

    private void shuffleBalls() {
        // System.out.println("\nCurrent Shuffled Numbers:");
        printShuffledNumbers(); // Display current queue before drawing

        if (!bingoModels[0].getShuffledNumbers().isEmpty()) {
            // Draw a single number and dequeue it
            int drawnNumber = bingoModels[0].getShuffledNumbers().poll();

            printDrawnNumber();

            // Mark the drawn number on each bingo card
            for (BingoModel cardModel : bingoModels) {
                cardModel.getDrawnNumbers().add(drawnNumber); // Add to drawn queue
                cardModel.markNumber(drawnNumber); // Mark the number on the bingo card
            }

            // Print the drawn number and the corresponding letter
            String range = getRange(drawnNumber);
            System.out.println("\nShuffling the balls...");
            System.out.println("In Letter " + range + ", number " + drawnNumber);
            System.out.println();

            // Print the updated Bingo cards
            int cardIndex = 1; // Index for keeping track of card numbers
            for (BingoModel cardModel : bingoModels) {
                System.out.println("\nBINGO Card " + cardIndex + ":");
                view.printBingoCard(cardModel.getCard());
                cardIndex++; // Increment for the next card
            }

            // Reshuffle the queue
            reshuffleQueue(); // Ensure the queue is reshuffled
        }

        // Check for winning condition after each draw
        for (BingoModel cardModel : bingoModels) {
            if (checkForWin(cardModel)) {
                System.out.println("Congratulations! You've won the game!");
                return; // Exit the game loop
            }
        }
    }

    private void reshuffleQueue() {
        Queue<Integer> currentQueue = bingoModels[0].getShuffledNumbers();

        List<Integer> list = new ArrayList<>(currentQueue);
        Collections.shuffle(list); // Shuffle the list

        Queue<Integer> reshuffledQueue = new ArrayDeque<>(list); // Rebuild the queue

        // Update all BingoModel instances with the new reshuffled queue
        for (BingoModel model : bingoModels) {
            model.setShuffledNumbers(reshuffledQueue); // Update the shuffled queue
        }
    }

    private void printShuffledNumbers() {
        Queue<Integer> shuffledQueue = bingoModels[0].getShuffledNumbers(); // Get current queue
        if (shuffledQueue.isEmpty()) {
            System.out.println("Shuffled Numbers: None"); // Display when the queue is empty
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
        System.out.println("Do you still want to continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    // private void shuffleBalls() {
    // // Draw a single number
    // int drawnNumber = bingoModels[0].getShuffledNumbers().poll();

    // // Print the drawn numbers before shuffling
    // printDrawnNumber();

    // // Print the shuffled number
    // String range = getRange(drawnNumber);
    // System.out.println("\nShuffling the balls...");
    // System.out.println("In Letter " + range + ", number " + drawnNumber);
    // System.out.println();

    // // Mark the drawn number on each bingo card and check for a win
    // int cardIndex = 1; // Index for keeping track of card numbers
    // for (BingoModel cardModel : bingoModels) {
    // // Track drawn numbers and mark the drawn number on the bingo card
    // cardModel.getDrawnNumbers().add(drawnNumber);
    // cardModel.markNumber(drawnNumber);

    // // Display which card number it is before printing
    // System.out.println("\nBINGO Card " + cardIndex + ":");
    // view.printBingoCard(cardModel.getCard()); // Print the updated card

    // cardIndex++; // Increment card index for the next card
    // }

    // // Check for winning condition (assuming you want to check for win after each
    // // number)
    // for (BingoModel cardModel : bingoModels) {
    // if (checkForWin(cardModel)) {
    // System.out.println("Congratulations! You've won the game!");
    // return; // Exit the game loop
    // }
    // }
    // }

    private void printDrawnNumber() {
        // Print the drawn numbers for all cards
        System.out.println("\nDrawn numbers:");
        for (Integer drawnNum : bingoModels[0].getDrawnNumbers()) {
            String rangeNum = getRange(drawnNum);
            System.out.println("In Letter " + rangeNum + ", number " + drawnNum);
        }
    }

    private void initialBingoCards() {
        int cardNumber = 1; // Variable to keep track of card numbers

        System.out.println("BINGO Cards:"); // Heading for the section of Bingo cards

        // Loop through each BingoModel to print their respective cards with card
        // numbers
        for (BingoModel cardModel : bingoModels) {
            System.out.println("\nBINGO Card " + cardNumber + ":"); // Display card number
            view.printBingoCard(cardModel.getCard()); // Print the actual bingo card
            cardNumber++; // Increment for the next card
        }
    }

    private boolean checkForWin(BingoModel cardModel) {
        String[][] card = cardModel.getCard();

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
            if (!cell.contains("X")) {
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
