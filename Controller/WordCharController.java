package Controller;

import java.awt.event.*;

import Model.WordCharModel;
import View.WordCharView;

public class WordCharController {
    // Model Object
    // View Object
    private WordCharModel model;
    private WordCharView view;

    public WordCharController(WordCharModel model, WordCharView view) {
        this.model = model;
        this.view = view;

        view.addCountButtonListener(new CountButtonListener());
    }

    class CountButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            String text = view.getText();
            model.count(text);
            view.setWordCount(model.getWordCount());
            view.setCharCount(model.getCharCount());
        }
    }

    // control model object
    public void setWordCounter(int wordcount) {
        view.setWordCount(wordcount);
    }

    public void setCharCounter(int charcount) {
        view.setCharCount(charcount);
    }

    public int getWordCounter() {
        return model.getWordCount();
    }

    public int getCharCounter() {
        return model.getCharCount();
    }

    // control view object
    // public void updateView() {
    // view.printWordCharDetails(model.getWordCount(), model.getCharCount());
    // }

}
