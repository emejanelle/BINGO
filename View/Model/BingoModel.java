package Model;

import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class BingoModel {
    static String[] BINGO = { "B", "I", "N", "G", "O" };
    // static String[][] number = { { "1", "2", "3", "4", "5", "6", "7", "8", "9",
    // "10", "11", "12", "13", "14", "15" },
    // { "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
    // "28", "29", "30" },
    // { "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
    // "43", "44", "45" },
    // { "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
    // "58", "59", "60" },
    // { "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72",
    // "73", "74", "75" }
    // };

    static String[] deck = new String[BINGO.length * 15];
    static String[][] shuffled = new String[BINGO.length * 15][];

    final static int SIZE = 5;
    private final String[][] card;
    private Queue<String> bingoBalls;
    final Random rand = new Random();;
    // private int deckPosition;

    public BingoModel() {
        this.card = generateBingoCard();
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

            int index = 0;
            for (int row = 0; row < SIZE; row++) {
                if (col == 2 && row == 2) { // Free space in the center
                    card[row][col] = "Free";
                } else {
                    card[row][col] = numbers[index++];
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
        for (int i = range - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }

    private Queue<String> generateshuffledBinggoBalls() {
        Queue<String> bingoQueue = new LinkedList<>();
        for (int i = 0; i < BINGO.length; i++) {
            for (int j = 1; j <= 15; j++) {
                bingoQueue.add(BINGO[i] + (i * 15 + j));
            }
        }

        // Shuffle the queue
        LinkedList<String> shuffledList = new LinkedList<>(bingoQueue);
        for (int i = shuffledList.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = shuffledList.get(i);
            shuffledList.set(i, shuffledList.get(j));
            shuffledList.set(j, temp);
        }

        return new LinkedList<>(shuffledList); // Return a new shuffled queue
    }

    public String[][] getCard() {
        return card;
    }

    public String drawBingoBall() {
        return bingoBalls.poll(); // Remove and return the first ball in the queue
    }

    public boolean hasMoreBalls() {
        return !bingoBalls.isEmpty(); // Check if there are more balls to draw
    }
}
