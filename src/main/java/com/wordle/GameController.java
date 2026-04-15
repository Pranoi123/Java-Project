package com.wordle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/new-game")
    public ResponseEntity<Map<String, Object>> newGame() {
        GameState game = gameService.createNewGame();
        Map<String, Object> response = new HashMap<>();
        response.put("gameId", game.getGameId());
        response.put("board", getBoardState(game.getBoard()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guess")
    public ResponseEntity<Map<String, Object>> guess(@RequestBody Map<String, String> body) {
        String gameId = body.get("gameId");
        String guess = body.get("guess").toUpperCase();

        GameState game = gameService.getGame(gameId);
        if (game == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Game not found"));
        }

        try {
            gameService.makeGuess(gameId, guess);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("results", game.getBoard().getResults()[game.getBoard().getCurrentAttempt() - 1]);
        response.put("board", getBoardState(game.getBoard()));
        response.put("keyboard", game.getBoard().getLetterStates());
        response.put("won", game.isWon());
        response.put("lost", game.isLost());
        if (game.isLost()) {
            response.put("targetWord", game.getTargetWord());
        }
        return ResponseEntity.ok(response);
    }

    private List<List<String>> getBoardState(GameBoard board) {
        List<List<String>> boardState = new ArrayList<>();
        String[] guesses = board.getGuesses();
        for (int i = 0; i < GameLogic.MAX_ATTEMPTS; i++) {
            List<String> row = new ArrayList<>();
            if (i < board.getCurrentAttempt()) {
                for (char c : guesses[i].toCharArray()) {
                    row.add(String.valueOf(c));
                }
            } else {
                for (int j = 0; j < GameLogic.WORD_LENGTH; j++) {
                    row.add("");
                }
            }
            boardState.add(row);
        }
        return boardState;
    }
}
