package Controller;

import java.util.Random;
import Model.BingoModel;
import View.UI;
import java.util.Scanner;

public class Logic {
    private final BingoModel cardModel;
    private final UI view;
    private boolean firstRound = true;

    public Logic(UI view) {
        this.view = view;
        this.cardModel = new BingoModel();
    }

    public void playGame() {
        if (firstRound) {
            view.printBingoCard(cardModel.getCard());
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
        // Display all drawn numbers before shuffling
        System.out.println("\nDrawn numbers:");
        for (Integer drawnNumber : cardModel.getDrawnNumbers()) {
            String range = getRange(drawnNumber);
            System.out.println("In Letter " + range + ", number " + drawnNumber);
        }
    
        System.out.println("\nShuffling the balls...");
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    
        cardModel.shuffleBalls(); // Shuffle the balls and mark the drawn number
        int lastDrawnNumber = cardModel.getDrawnNumber(); // Get the last drawn number
        String lastRange = getRange(lastDrawnNumber); // Get the range of the last drawn number
        System.out.println("In Letter " + lastRange + ", number " + lastDrawnNumber); // Display the last drawn number
        view.printBingoCard(cardModel.getCard()); // Print the updated card
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
    
