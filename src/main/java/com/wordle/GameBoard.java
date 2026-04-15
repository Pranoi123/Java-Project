package com.wordle;

import java.util.*;

/**
 * Tracks the state of the game board — all guesses and their letter results.
 */
public class GameBoard {

    private final String[] guesses;
    private final LetterResult[][] results;
    private int currentAttempt;

    // Tracks the best known result for each letter A-Z
    private final Map<Character, LetterResult> letterStates;

    public GameBoard() {
        guesses = new String[GameLogic.MAX_ATTEMPTS];
        results = new LetterResult[GameLogic.MAX_ATTEMPTS][];
        currentAttempt = 0;
        letterStates = new LinkedHashMap<>();
    }

    /**
     * Records a guess and its evaluation results.
     */
    public void recordGuess(String guess, LetterResult[] guessResults) {
        guesses[currentAttempt] = guess;
        results[currentAttempt] = guessResults;
        updateLetterStates(guess, guessResults);
        currentAttempt++;
    }

    private void updateLetterStates(String guess, LetterResult[] guessResults) {
        for (int i = 0; i < GameLogic.WORD_LENGTH; i++) {
            char c = guess.charAt(i);
            LetterResult existing = letterStates.get(c);
            LetterResult newResult = guessResults[i];

            // Priority: CORRECT > PRESENT > ABSENT
            if (existing == null || priority(newResult) > priority(existing)) {
                letterStates.put(c, newResult);
            }
        }
    }

    private int priority(LetterResult r) {
        return switch (r) {
            case CORRECT -> 2;
            case PRESENT -> 1;
            case ABSENT  -> 0;
        };
    }

    public int getCurrentAttempt() { return currentAttempt; }
    public String[] getGuesses()   { return guesses; }
    public LetterResult[][] getResults() { return results; }
    public Map<Character, LetterResult> getLetterStates() { return letterStates; }
    public boolean isFull()        { return currentAttempt >= GameLogic.MAX_ATTEMPTS; }
}
