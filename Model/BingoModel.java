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
    private Queue<Integer> shuffledNumbers; // shuffled numbers variable
    private final Queue<Integer> drawnNumbers; // drawn numbers variable (first in first out)

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";

    public BingoModel() {
        this.card = generateBingoCard();
        this.shuffledNumbers = generateShuffledNumbers();
        this.drawnNumbers = new ArrayDeque<>(); // fifo queue
    }

    private String[][] generateBingoCard() {
        String[][] card = new String[SIZE][SIZE];
        String[][] number = {
            { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" },
            { "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
            { "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45" },
            { "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" },
            { "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75" }
        };

        // Shuffle each column and assign to the card
        for (int col = 0; col < SIZE; col++) {
            String[] numbers = number[col];
            shuffleArray(numbers); // Shuffle the numbers
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

    // Shuffle method for arrays
    private void shuffleArray(String[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            String temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private Queue<Integer> generateShuffledNumbers() {
        List<Integer> numbersList = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbersList.add(i); // add numbers 1 through 75
        }
        Collections.shuffle(numbersList); // shuffle ulit numbers
        return new ArrayDeque<>(numbersList); // gagawing queue yung mga shinuffle na numbers (list)
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

    public void markNumber(int number) {
        String formattedNumber = String.format("%2d", number); //2 numbers format 

        // gumagawa ng version ng marked number na retains the same width
        String markedNumber = YELLOW + "-" + formattedNumber + " " + RESET; // dash adds width to maintain alignment

        // nagiiterate sa card to find and mark the number
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // use trimmed comparison to ensure alignment works with padding
                if (card[i][j].trim().equals(formattedNumber.trim())) {
                    card[i][j] = markedNumber; // mark the number with consistent width
                    return; 
                }
            }
        }
    }

    public String[][] getCard() {
        return card;
    }
}
