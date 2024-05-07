package View;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;

    // Reset
    public final String RESET = "\033[0m"; // Text

    public final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW

    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printBingoCard(String[][] card) {
        System.out.println("  ".repeat(25) + "┌─────┬─────┬─────┬─────┬─────┐");
        System.out.println("  ".repeat(25) +
                "|  " + YELLOW_BOLD + "B  " + RESET + "|  " + YELLOW_BOLD + "I" + RESET + "  |  " + YELLOW_BOLD + "N  "
                + RESET + "|  " + YELLOW_BOLD + "G " + RESET + " |  " + YELLOW_BOLD + "O" + RESET + "  |");
        System.out.println("  ".repeat(25) + "├─────┼─────┼─────┼─────┼─────┤");

        // Print each row with consistent cell alignment
        for (int row = 0; row < card.length; row++) {
            System.out.print("  ".repeat(25) + "|");
            for (int col = 0; col < card[row].length; col++) {
                System.out.print(String.format(" %-4s|", card[row][col])); // Left-aligned, fixed
                                                                           // width
            }
            System.out.println();
            if (row < card.length - 1) {
                System.out.println("  ".repeat(25) + "├─────┼─────┼─────┼─────┼─────┤");
            }
        }

        System.out.println("  ".repeat(25) + "└─────┴─────┴─────┴─────┴─────┘");
    }

    public boolean promptToPlay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  ".repeat(15) + "Do you want to play? (Press Enter to Continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");
    }

    public boolean promptToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  ".repeat(15) + "Do you wanna Continue? (Press Enter to continue or type 'exit' to quit)");
        String input = scanner.nextLine().trim().toLowerCase();
        return !input.equals("exit");

    }

    public UI() {
        this.scanner = null;

        String[] title = {
                " ".repeat(43) + "888~~\\  888 888b    |  e88~~\\    ,88~-_    d8b",
                " ".repeat(43) + "888   | 888 |Y88b   | d888      d888   \\  !Y88! ",
                " ".repeat(43) + "888 _/  888 | Y88b  | 8888 __  88888    |  Y8Y ",
                " ".repeat(43) + "888  \\  888 |  Y88b | 8888   | 88888    |   8  ",
                " ".repeat(43) + "888   | 888 |   Y88b| Y888   |  Y888   /    e ",
                " ".repeat(43) + "888__/  888 |    Y888  \"88__/    `88_-~    \"8\"  "
        }; // Corrected syntax (added semicolon at the end)

        // Display the welcome message with ASCII art
        for (String line : title) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            Thread.sleep(1000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
