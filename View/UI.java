package View;

public class UI {

    public void printBingoCard(String[][] card) {
        System.out.println("BINGO Card:");
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

    // ┌ ┘ └ ┐ ─ ┬ ┴ ┼
    // public UI() {

    // }
}
