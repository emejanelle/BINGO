import Controller.Logic;
import Model.BingoModel;
import View.UI;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        BingoModel model = new BingoModel();
        Logic logic = new Logic(model, ui);

        logic.StartMenu(); // Display a shuffled BINGO card
    }
}
