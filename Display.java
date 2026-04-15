import java.util.*;

/**
 * Handles all terminal rendering with ANSI colors for the Wordle game.
 */
public class Display {

    // ANSI color codes
    private static final String RESET  = "\u001B[0m";
    private static final String BOLD   = "\u001B[1m";

    // Tile background colors
    private static final String BG_GREEN  = "\u001B[42m";   // CORRECT
    private static final String BG_YELLOW = "\u001B[43m";   // PRESENT
    private static final String BG_GRAY   = "\u001B[100m";  // ABSENT
    private static final String BG_EMPTY  = "\u001B[40m";   // Empty row

    // Text colors
    private static final String FG_WHITE  = "\u001B[97m";
    private static final String FG_BLACK  = "\u001B[30m";
    private static final String FG_DARK   = "\u001B[90m";
    private static final String FG_GREEN  = "\u001B[32m";
    private static final String FG_YELLOW = "\u001B[33m";

    public static void printBanner() {
        System.out.println();
        System.out.println(BOLD + FG_WHITE + "  ██╗    ██╗ ██████╗ ██████╗ ██████╗ ██╗     ███████╗" + RESET);
        System.out.println(BOLD + FG_GREEN  + "  ██║    ██║██╔═══██╗██╔══██╗██╔══██╗██║     ██╔════╝" + RESET);
        System.out.println(BOLD + FG_YELLOW + "  ██║ █╗ ██║██║   ██║██████╔╝██║  ██║██║     █████╗  " + RESET);
        System.out.println(BOLD + FG_DARK   + "  ██║███╗██║██║   ██║██╔══██╗██║  ██║██║     ██╔══╝  " + RESET);
        System.out.println(BOLD + FG_WHITE  + "  ╚███╔███╔╝╚██████╔╝██║  ██║██████╔╝███████╗███████╗" + RESET);
        System.out.println(BOLD + FG_DARK   + "   ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝" + RESET);
        System.out.println();
        System.out.println(FG_DARK + "  Guess the 5-letter word in 6 tries." + RESET);
        System.out.println(FG_DARK + "  ■ Green = correct spot   ■ Yellow = wrong spot   ■ Gray = not in word" + RESET);
        System.out.println();
    }

    public static void printBoard(GameBoard board) {
        System.out.println();
        for (int row = 0; row < GameLogic.MAX_ATTEMPTS; row++) {
            System.out.print("  ");
            if (row < board.getCurrentAttempt()) {
                // Filled row
                String guess = board.getGuesses()[row];
                LetterResult[] rowResults = board.getResults()[row];
                for (int col = 0; col < GameLogic.WORD_LENGTH; col++) {
                    printTile(guess.charAt(col), rowResults[col]);
                }
            } else {
                // Empty rows
                for (int col = 0; col < GameLogic.WORD_LENGTH; col++) {
                    printEmptyTile();
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printTile(char letter, LetterResult result) {
        String bg = switch (result) {
            case CORRECT -> BG_GREEN;
            case PRESENT -> BG_YELLOW;
            case ABSENT  -> BG_GRAY;
        };
        String fg = switch (result) {
            case CORRECT -> FG_WHITE;
            case PRESENT -> FG_BLACK;
            case ABSENT  -> FG_WHITE;
        };
        System.out.print(BOLD + bg + fg + " " + letter + " " + RESET + " ");
    }

    private static void printEmptyTile() {
        System.out.print(BG_EMPTY + FG_DARK + " _ " + RESET + " ");
    }

    public static void printKeyboard(GameBoard board) {
        String[] rows = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        Map<Character, LetterResult> states = board.getLetterStates();

        System.out.println(FG_DARK + "  Keyboard:" + RESET);
        for (String row : rows) {
            System.out.print("  ");
            for (char c : row.toCharArray()) {
                LetterResult state = states.get(c);
                if (state == null) {
                    System.out.print(FG_DARK + "[" + c + "] " + RESET);
                } else {
                    String color = switch (state) {
                        case CORRECT -> FG_GREEN;
                        case PRESENT -> FG_YELLOW;
                        case ABSENT  -> FG_DARK;
                    };
                    System.out.print(BOLD + color + "[" + c + "] " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printWin(int attempts) {
        System.out.println();
        System.out.println(BOLD + FG_GREEN + "  🎉 BRILLIANT! You got it in " + attempts +
                (attempts == 1 ? " try!" : " tries!") + RESET);
        System.out.println();
    }

    public static void printLoss(String targetWord) {
        System.out.println();
        System.out.println(BOLD + FG_YELLOW + "  😔 Game over! The word was: " +
                BG_GREEN + FG_WHITE + " " + targetWord + " " + RESET);
        System.out.println();
    }

    public static void printInvalidWord() {
        System.out.println(FG_YELLOW + "  ⚠  Not in word list. Try again." + RESET);
    }

    public static void printInvalidLength() {
        System.out.println(FG_YELLOW + "  ⚠  Word must be exactly 5 letters." + RESET);
    }

    public static void printInvalidChars() {
        System.out.println(FG_YELLOW + "  ⚠  Only letters A-Z are allowed." + RESET);
    }

    public static void promptGuess(int attempt) {
        System.out.print(BOLD + FG_WHITE + "  Guess " + attempt + "/6 → " + RESET);
    }

    public static void printSeparator() {
        System.out.println(FG_DARK + "  ─────────────────────────────────" + RESET);
    }

    public static void printPlayAgainPrompt() {
        System.out.print(FG_WHITE + "  Play again? (Y/N): " + RESET);
    }
}
