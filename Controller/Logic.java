package Controller;

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
            printBingoCards(); // Print all four bingo cards
            firstRound = false;
            if (!view.promptToPlay()) {
                return;
            }
        }

        do {
            shuffleBalls();
        } while (promptToContinue());
    }

    public boolean promptToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you still want to continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    private void shuffleBalls() {
        // Draw a single number
        int drawnNumber = bingoModels[0].getShuffledNumbers().poll();
    
        // Print the drawn numbers before shuffling
        printDrawnNumber();
    
        // Print the shuffled number
        String range = getRange(drawnNumber);
        System.out.println("\nShuffling the balls...");
        System.out.println("In Letter " + range + ", number " + drawnNumber);
        System.out.println();
    
        // Mark the drawn number and print the updated card for each card
        for (BingoModel cardModel : bingoModels) {
            cardModel.getDrawnNumbers().add(drawnNumber); // Add the drawn number to each card
            cardModel.markNumber(drawnNumber); // Mark the drawn number on the card
            // Print the updated card
            view.printBingoCard(cardModel.getCard());
        }
    
        // Check for winning condition (assuming you want to check for win after each number)
        for (BingoModel cardModel : bingoModels) {
            if (checkForWin(cardModel)) {
                System.out.println("Congratulations! You've won the game!");
                return; // Exit the game loop
            }
        }
    }
    
    
    private void printDrawnNumber(){
        // Print the drawn numbers for all cards
        System.out.println("\nDrawn numbers:");
        for (Integer drawnNum : bingoModels[0].getDrawnNumbers()) {
            String rangeNum = getRange(drawnNum);
            System.out.println("In Letter " + rangeNum + ", number " + drawnNum);
        }
    }
    
    
    
    
    

    private void printBingoCards() {
        System.out.println("BINGO Cards:");
        String[][][] cards = new String[4][][]; // Create a 3D array to hold cards for all four bingo models
        for (int i = 0; i < 4; i++) {
            cards[i] = bingoModels[i].getCard(); // Retrieve each bingo card and assign it to the array
        }
        view.printBingoCards(cards); // Print all four bingo cards
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
            if (!cell.startsWith("X")) {
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
