package Controller;

import java.util.Scanner;
import java.util.Random;
import Model.BingoModel;
import View.UI;
import java.util.Queue;

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
        System.out.println("Do you still wanna Continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    public void displayBingoCard() {
        String[][] card = cardModel.getCard();
        view.printBingoCard(card);
        if (view.promptToPlay()) {
            shuffleBalls();
        }
    }

    private void shuffleBalls() {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> numberQueue = cardModel.getNumberQueue();
    System.out.println("Numbers in the queue:");
    for (Integer number : numberQueue) {
        System.out.print(number + " ");
    }
    System.out.println();
        System.out.println();
        System.out.println("Shuffling the balls...");
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        try {
            Thread.sleep(2000); // Wait for 2 seconds before printing the number
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Random rand = new Random();
        int randomNumber = rand.nextInt(75) + 1;
        String range = "";
        if (randomNumber >= 1 && randomNumber <= 15) {
            range = "B";
        } else if (randomNumber >= 16 && randomNumber <= 30) {
            range = "I";
        } else if (randomNumber >= 31 && randomNumber <= 45) {
            range = "N";
        } else if (randomNumber >= 46 && randomNumber <= 60) {
            range = "G";
        } else if (randomNumber >= 61 && randomNumber <= 75) {
            range = "O";
        }
        System.out.println("Sa Letrang " + range + ", number " + randomNumber + ".");
        cardModel.getNumberQueue().offer(randomNumber); // Store the random number into the queue 
        view.printBingoCard(cardModel.getCard()); // Print the updated card
        cardModel.getNumberQueue().offer(randomNumber); // Store the random number into the queue
        
    }
}
