package com.wordle;

import java.util.UUID;

public class GameState {
    private final String gameId;
    private final String targetWord;
    private final GameBoard board;
    private boolean won;
    private boolean lost;

    public GameState(String targetWord) {
        this.gameId = UUID.randomUUID().toString();
        this.targetWord = targetWord;
        this.board = new GameBoard();
        this.won = false;
        this.lost = false;
    }

    public String getGameId() { return gameId; }
    public String getTargetWord() { return targetWord; }
    public GameBoard getBoard() { return board; }
    public boolean isWon() { return won; }
    public boolean isLost() { return lost; }

    public void setWon(boolean won) { this.won = won; }
    public void setLost(boolean lost) { this.lost = lost; }
}
