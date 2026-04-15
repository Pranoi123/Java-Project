package com.wordle;

/**
 * Represents the result state of each letter in a Wordle guess.
 */
public enum LetterResult {
    CORRECT,    // Green  - letter is in correct position
    PRESENT,    // Yellow - letter is in word but wrong position
    ABSENT      // Gray   - letter is not in word
}
