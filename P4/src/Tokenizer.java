import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tokenizer {
    private ArrayList<String> words;

    // A constructor with a String argument that specifies the file from which to obtain the words. (Linear time)
    public Tokenizer(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder currentWord = new StringBuilder();
            words = new ArrayList<String>();
            for(int currentChar = reader.read(); currentChar != -1; currentChar = reader.read()) {
                if (currentChar == (int)' ' || currentChar == (int)'\n' || currentChar == (int)'\r' || currentChar == (int)'\t') {
                    if (currentWord.length() > 0) {
                        words.add(currentWord.toString().toLowerCase());
                        currentWord = new StringBuilder();
                    }
                    continue;
                }
                if (Character.isLetterOrDigit((char)currentChar)) currentWord.append((char)currentChar);
            }
            if (!currentWord.isEmpty()) words.add(currentWord.toString().toLowerCase()); // Letter or number ends last word in file
        }
        catch(Exception e) {
            System.out.println("There was a problem reading the specified file.");
        }
    }

    // A constructor with a String[] that obtains the words directly from the String array. No file is read. (Linear time)
    public Tokenizer(String[] words) {
        this.words = new ArrayList<String>();
        for (String word : words) {
            StringBuilder newWord = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                char currentChar = word.charAt(j);
                if (currentChar == ' ' || currentChar == '\n' || currentChar == '\r' || currentChar == '\t') {
                    if (newWord.length() > 0) {
                        this.words.add(newWord.toString().toLowerCase());
                        newWord = new StringBuilder();
                    }
                    continue;
                }
                if (Character.isLetterOrDigit(currentChar)) newWord.append(currentChar);
            }
            if (!newWord.isEmpty()) this.words.add(newWord.toString().toLowerCase()); // Letter or number ends last word in array entry
        }
    }

    // Returns the word list created from the constructors. (Constant time)
    public ArrayList<String> wordList() {
        return words;
    }
}
