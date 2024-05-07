package Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BingoModel {
    private static final int SIZE = 5;
    private final String[][] card;
    private Queue<Integer> shuffledNumbers; // To hold shuffled numbers for drawing
    private final Queue<Integer> drawnNumbers; // To hold drawn numbers in a FIFO manner

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";

    public BingoModel() {
        this.card = generateBingoCard();
        this.shuffledNumbers = generateShuffledNumbers();
        this.drawnNumbers = new ArrayDeque<>(); // Initializing as FIFO queue
    }

    private String[][] generateBingoCard() {
        String[][] card = new String[SIZE][SIZE];
        int[][] numberRanges = {
                { 1, 15 }, // B
                { 16, 30 }, // I
                { 31, 45 }, // N
                { 46, 60 }, // G
                { 61, 75 } // O
        };

        // Shuffle each column and populate the card
        for (int col = 0; col < SIZE; col++) {
            String[] numbers = generateShuffledColumn(numberRanges[col][0], numberRanges[col][1]);
            for (int row = 0; row < SIZE; row++) {
                if (col == 2 && row == 2) { // Free space in the center
                    card[row][col] = YELLOW + "Free" + RESET;
                } else {
                    card[row][col] = numbers[row];
                }
            }
        }
        return card;
    }

    private String[] generateShuffledColumn(int start, int end) {
        int range = end - start + 1;
        String[] numbers = new String[range];

        for (int i = 0; i < range; i++) {
            numbers[i] = Integer.toString(start + i);
        }

        // Shuffle the numbers in place
        Random rand = new Random();
        for (int i = range - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }

    private Queue<Integer> generateShuffledNumbers() {
        List<Integer> numbersList = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbersList.add(i); // Add numbers 1 through 75
        }
        Collections.shuffle(numbersList); // Shuffle the numbers
        return new ArrayDeque<>(numbersList); // Create a queue from the shuffled list
    }

    public Queue<Integer> getShuffledNumbers() {
        return shuffledNumbers;
    }

    public void setShuffledNumbers(Queue<Integer> newQueue) {
        this.shuffledNumbers = newQueue;
    }

    public Queue<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    // Marking function with alignment fixes
    public void markNumber(int number) {
        // Ensure the number is formatted with at least 2 characters
        String formattedNumber = String.format("%2d", number);

        // Create a version of the marked number that retains the same width
        String markedNumber = YELLOW + "-" + formattedNumber + " " + RESET; // The dash adds width to maintain alignment

        // Iterate through the card to find and mark the number
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Use trimmed comparison to ensure alignment works with padding
                if (card[i][j].trim().equals(formattedNumber.trim())) {
                    card[i][j] = markedNumber; // Mark the number with consistent width
                    return; // Exit once we've marked it
                }
            }
        }
    }

    public String[][] getCard() {
        return card;
    }

}
