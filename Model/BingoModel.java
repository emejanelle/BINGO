package Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BingoModel {
    static String[] BINGO = { "B", "I", "N", "G", "O" };
    static String[][] number = {
            { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" },
            { "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
            { "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45" },
            { "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" },
            { "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75" }
    };

    final static int SIZE = 5;
    private final String[][] card;
    private final Queue<Integer> shuffledNumbers;
    private final List<Integer> drawnNumbers;

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";

    public BingoModel() {
        this.card = generateBingoCard();
        this.shuffledNumbers = generateShuffledNumbers();
        this.drawnNumbers = new ArrayList<>();
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
            numbersList.add(i);
        }

        Collections.shuffle(numbersList); // Shuffle the list of numbers

        Queue<Integer> shuffledQueue = new ArrayDeque<>(numbersList); // Convert shuffled list to queue

        return shuffledQueue;
    }

    public void markNumber(int number) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (card[i][j].equals(String.valueOf(number))) {
                    card[i][j] = YELLOW + "X" + number + "X" + RESET; // Add spaces around the number to indicate it's matched
                    return; // Once the number is found and marked, exit the method
                }
            }
        }
    }

    public String[][] getCard() {
        return card;
    }

    public Queue<Integer> getShuffledNumbers() {
        return shuffledNumbers;
    }

    public List<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public int getDrawnNumber() {
        return drawnNumbers.get(drawnNumbers.size() - 1); // Return the last drawn number
    }

    public void shuffleBalls() {
        int drawnNumber = shuffledNumbers.poll(); // Remove the first number from the shuffled numbers queue
        if (!drawnNumbers.contains(drawnNumber)) {
            drawnNumbers.add(drawnNumber); // Add the drawn number to the list of drawn numbers if it's not already present
        }
    }
}
