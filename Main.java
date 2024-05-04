import Controller.Logic;
import Model.BingoModel;
import View.UI;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        Logic logic = new Logic(ui);

        do {
            logic.playGame(); // Start the game
        } while (ui.promptToContinue()); // Check for continuation after each game round
    }
}

