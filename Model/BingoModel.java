package Model;

import java.util.ArrayDeque;
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
    private final Queue<Integer> numberQueue;
    private final Random rand = new Random();

    public BingoModel() {
        this.card = generateBingoCard();
        this.numberQueue = generateShuffledNumbers();
    }

    private Queue<Integer> generateShuffledNumbers() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= 75; i++) {
            queue.add(i);
        }
        Integer[] numbersArray = queue.toArray(new Integer[0]);
        for (int i = 0; i < numbersArray.length; i++) {
            int swapIndex = rand.nextInt(numbersArray.length);
            Integer temp = numbersArray[i];
            numbersArray[i] = numbersArray[swapIndex];
            numbersArray[swapIndex] = temp;
        }
        queue.clear();
        for (Integer number : numbersArray) {
            queue.add(number);
        }
        return queue;
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
                    card[row][col] = "Free";
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
        for (int i = range - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        return numbers;
    }

    public void markNumber(int number) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (card[i][j].equals(String.valueOf(number))) {
                    card[i][j] = "X" + number + "X"; // Add spaces around the number to indicate it's matched
                    return; // Once the number is found and marked, exit the method
                }
            }
        }
    }


    public String[][] getCard() {
        return card;
    }

    public Queue<Integer> getNumberQueue() {
        return numberQueue;
    }
}
