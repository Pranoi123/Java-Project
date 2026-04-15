import java.util.*;

/**
 * ============================================================
 *  WORDLE - Java Console Edition
 * ============================================================
 *  How to compile and run:
 *    javac *.java
 *    java WordleGame
 * ============================================================
 */
public class WordleGame {

    private final WordLoader wordLoader;
    private final Scanner scanner;

    public WordleGame() {
        this.wordLoader = new WordLoader();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame();
        game.run();
    }

    public void run() {
        Display.printBanner();

        boolean playAgain = true;
        while (playAgain) {
            playRound();
            Display.printPlayAgainPrompt();
            if (!scanner.hasNextLine()) break;
            String answer = scanner.nextLine().trim().toUpperCase();
            playAgain = answer.equals("Y");
            System.out.println();
        }

        System.out.println("  Thanks for playing WORDLE! 👋\n");
        scanner.close();
    }

    private void playRound() {
        String targetWord = wordLoader.getRandomWord();
        GameBoard board = new GameBoard();
        boolean won = false;

        Display.printBoard(board);
        Display.printKeyboard(board);

        while (!board.isFull() && !won) {
            Display.promptGuess(board.getCurrentAttempt() + 1);
            String guess = readGuess();

            if (guess == null) continue; // invalid input, re-prompt

            LetterResult[] results = GameLogic.evaluateGuess(guess, targetWord);
            board.recordGuess(guess, results);

            // Clear and redraw
            Display.printBoard(board);
            Display.printKeyboard(board);

            if (GameLogic.isWin(results)) {
                won = true;
            }
        }

        if (won) {
            Display.printWin(board.getCurrentAttempt());
        } else {
            Display.printLoss(targetWord);
        }

        Display.printSeparator();
    }

    /**
     * Reads and validates player input.
     * Returns the valid 5-letter uppercase word, or null if invalid.
     */
    private String readGuess() {
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.length() != GameLogic.WORD_LENGTH) {
            Display.printInvalidLength();
            return null;
        }

        if (!input.matches("[A-Z]+")) {
            Display.printInvalidChars();
            return null;
        }

        if (!wordLoader.isValidWord(input)) {
            Display.printInvalidWord();
            return null;
        }

        return input;
    }
}
