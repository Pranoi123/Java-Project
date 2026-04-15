import java.io.*;
import java.util.*;

/**
 * Loads and manages the word list from words.txt
 */
public class WordLoader {

    private static final String WORDS_FILE = "words.txt";
    private List<String> words;

    public WordLoader() {
        words = new ArrayList<>();
        loadWords();
    }

    private void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toUpperCase();
                if (line.length() == 5 && line.matches("[A-Z]+")) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading words file: " + e.getMessage());
            System.exit(1);
        }

        if (words.isEmpty()) {
            System.err.println("No valid words found in " + WORDS_FILE);
            System.exit(1);
        }
    }

    /**
     * Returns a random word from the word list.
     */
    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    /**
     * Checks if the given word is in the valid word list.
     */
    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }

    public List<String> getAllWords() {
        return Collections.unmodifiableList(words);
    }
}
