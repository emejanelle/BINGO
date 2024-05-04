package Controller;

import Model.BingoModel;
import View.UI;
import java.util.Scanner;

public class Logic {
    private final BingoModel cardModel;
    private final UI view;

    boolean Play = true;
    static Scanner sc = new Scanner(System.in);

    public Logic(BingoModel model, UI view) {
        this.view = view;
        this.cardModel = new BingoModel();
    }

    public void drawBall() {
        if (cardModel.hasMoreBalls()) {
            String drawnBall = cardModel.drawBingoBall();
            System.out.println("Drawn Ball: " + drawnBall);
        } else {
            System.out.println("No more balls to draw.");
        }
    }

    // control model object

    // control view object

    public void displayBingoCard() {
        String[][] card = cardModel.getCard();
        view.printBingoCard(card);
    }

    public void StartMenu() {
        System.out.println("Welcome to BINGO!\n");

        while (Play) {
            System.out.println("Are you ready? \n\tP: Play \n\tQ. Quit");
            System.out.print("Choice: ");
            String choice = sc.next().toUpperCase();

            switch (choice) {
                case "P":
                    displayBingoCard();
                    drawBall();
                    break;

                case "Q":
                    System.out.println("Goodbye! Thank you for playing.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    return;
            }
        }

    }
}
