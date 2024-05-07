package View;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;

    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printBingoCard(String[][] card) {
        System.out.println("┌─────┬─────┬─────┬─────┬─────┐");
        System.out.println("|  B  |  I  |  N  |  G  |  O  |");
        System.out.println("├─────┼─────┼─────┼─────┼─────┤");

        // Print each row with consistent cell alignment
        for (int row = 0; row < card.length; row++) {
            System.out.print("|");
            for (int col = 0; col < card[row].length; col++) {
                System.out.print(String.format(" %-4s|", card[row][col])); // Left-aligned, fixed width
            }
            System.out.println();
            if (row < card.length - 1) {
                System.out.println("├─────┼─────┼─────┼─────┼─────┤");
            }
        }

        System.out.println("└─────┴─────┴─────┴─────┴─────┘");
    }
    

    public boolean promptToPlay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play? (Press Enter to Continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    public boolean promptToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wanna Continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    public UI() {
        this.scanner = null;
        System.out.println("Welcome to BINGO!");
        try {
            Thread.sleep(1000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
