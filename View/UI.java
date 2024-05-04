package View;

import java.util.Scanner;

public class UI {
    

    public void printBingoCard(String[][] card) {
        System.out.println("BINGO Card:");
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("┌─────┬─────┬─────┬─────┬─────┐");
        System.out.println("|  B  |  I  |  N  |  G  |  O  |");
        System.out.println("├" + "─────┼".repeat(4) + "─────┤");

        // Print rows with card numbers and consistent cell alignment
        for (int row = 0; row < card.length; row++) {
            System.out.print("|");
            for (int col = 0; col < card[row].length; col++) {
                System.out.print(String.format(" %-4s|", card[row][col])); // Left-aligned, fixed width
            }
            System.out.println();
            if (row < card.length - 1) {
                System.out.println("├" + "─────┼".repeat(4) + "─────┤");
            }
        }

        System.out.println("└" + "─────┴".repeat(4) + "─────┘");
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
        System.out.println("Welcome to BINGO!");
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}
