package com.wordle;

import java.util.*;

/**
 * Contains the core Wordle game logic for evaluating guesses.
 */
public class GameLogic {

    public static final int WORD_LENGTH = 5;
    public static final int MAX_ATTEMPTS = 6;

    /**
     * Evaluates a guess against the target word.
     * Handles duplicate letters correctly per official Wordle rules.
     *
     * @param guess  the player's guessed word (uppercase)
     * @param target the target word (uppercase)
     * @return array of LetterResult for each position
     */
    public static LetterResult[] evaluateGuess(String guess, String target) {
        LetterResult[] results = new LetterResult[WORD_LENGTH];
        boolean[] targetUsed = new boolean[WORD_LENGTH];
        boolean[] guessMatched = new boolean[WORD_LENGTH];

        // First pass: find exact matches (CORRECT)
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guess.charAt(i) == target.charAt(i)) {
                results[i] = LetterResult.CORRECT;
                targetUsed[i] = true;
                guessMatched[i] = true;
            }
        }

        // Second pass: find present but wrong position (PRESENT)
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guessMatched[i]) continue;
            results[i] = LetterResult.ABSENT;
            for (int j = 0; j < WORD_LENGTH; j++) {
                if (!targetUsed[j] && guess.charAt(i) == target.charAt(j)) {
                    results[i] = LetterResult.PRESENT;
                    targetUsed[j] = true;
                    break;
                }
            }
        }

        return results;
    }

    /**
     * Checks if the guess is a winning guess (all CORRECT).
     */
    public static boolean isWin(LetterResult[] results) {
        for (LetterResult r : results) {
            if (r != LetterResult.CORRECT) return false;
        }
        return true;
    }
}
