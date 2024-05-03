package Model;

public class WordCharModel {
    private int wordCounter;
    private int charCounter;

    public WordCharModel() {
        wordCounter = 0;
        charCounter = 0;
    }

    public void count(String text) {
        String[] words = text.trim().split("\\s+");
        wordCounter = words.length;
        charCounter = text.length();
    }

    public int getWordCount() {
        return wordCounter;
    }

    public int getCharCount() {
        return charCounter;
    }

}
