import Controller.Logic;
import Model.BingoModel;
import View.UI;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        BingoModel[] bingoModels = new BingoModel[4];

        // Create four Bingo cards
        for (int i = 0; i < 4; i++) {
            bingoModels[i] = new BingoModel();
        }

        // Extract cards from models
        String[][][] cards = new String[4][][];
        for (int i = 0; i < 4; i++) {
            cards[i] = bingoModels[i].getCard();
        }

        // Print the four Bingo cards side by side
        ui.printBingoCards(cards);

        Logic logic = new Logic(ui);

        do {
            logic.playGame(); // Start the game
        } while (ui.promptToContinue()); // Check for continuation after each game round
    }
}

