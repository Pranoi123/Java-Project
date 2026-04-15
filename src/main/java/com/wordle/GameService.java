package com.wordle;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final WordLoader wordLoader = new WordLoader();
    private final ConcurrentHashMap<String, GameState> games = new ConcurrentHashMap<>();

    public GameState createNewGame() {
        String targetWord = wordLoader.getRandomWord();
        GameState gameState = new GameState(targetWord);
        games.put(gameState.getGameId(), gameState);
        return gameState;
    }

    public GameState getGame(String gameId) {
        return games.get(gameId);
    }

    public void makeGuess(String gameId, String guess) {
        GameState game = games.get(gameId);
        if (game == null || game.isWon() || game.isLost()) {
            return;
        }

        if (!wordLoader.isValidWord(guess)) {
            throw new IllegalArgumentException("Invalid word");
        }

        LetterResult[] results = GameLogic.evaluateGuess(guess, game.getTargetWord());
        game.getBoard().recordGuess(guess, results);

        if (GameLogic.isWin(results)) {
            game.setWon(true);
        } else if (game.getBoard().isFull()) {
            game.setLost(true);
        }
    }

    public void removeGame(String gameId) {
        games.remove(gameId);
    }
}
