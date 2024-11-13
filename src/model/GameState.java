package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
	
	// Game state info
    private int numPlayers;
    private int currentTurn;
    
    private boolean gameStarted;
    private boolean gameOver;
    
    private String statusMessage;
    
    private List<Player> players;
    private Map<Player, Integer> scores;
    private Map<Player, Integer> wandsPlayersHave;
    private Map<Player, Integer> turnsPlayersHave;
    
    private int currentPosToCapture;
    private Tile currentTileToCapture;
    
    private String language;

    private ScoreManager scoreManager;
    
    // Constructors
    public GameState(int numPlayers, List<Player> players, String language, ScoreManager scoreManager) {
        this.numPlayers = numPlayers;
        this.players = players;
        this.language = language;
        this.scoreManager = scoreManager;
        this.scores = new HashMap<>();
        this.wandsPlayersHave = new HashMap<>();
        this.turnsPlayersHave = new HashMap<>();
        this.statusMessage = "";
        this.currentTurn = 0;
        this.gameStarted = false;
        this.gameOver = false;

        // Initialize players in ScoreManager
        for (Player player : players) {
            this.scores.put(player, 0);
            this.wandsPlayersHave.put(player, 0);
            this.turnsPlayersHave.put(player, 0);
            this.scoreManager.addPlayer(player); // Add each player to the score manager
        }
    }

    // Getters and Setters
    public int getNumPlayers() { return numPlayers; }
    public void setNumPlayers(int numPlayers) { this.numPlayers = numPlayers; }

    public int getCurrentTurn() { return currentTurn; }
    public void setCurrentTurn(int currentTurn) { this.currentTurn = currentTurn; }

    public boolean isGameStarted() { return gameStarted; }
    public void setGameStarted(boolean gameStarted) { this.gameStarted = gameStarted; }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

    public String getStatusMessage() { return statusMessage; }
    public void setStatusMessage(String statusMessage) { this.statusMessage = statusMessage; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public Map<Player, Integer> getScores() { return scores; }
    public void setScores(Map<Player, Integer> scores) { this.scores = scores; }

    public Map<Player, Integer> getWandsPlayersHave() { return wandsPlayersHave; }
    public void setWandsPlayersHave(Map<Player, Integer> wandsPlayersHave) { this.wandsPlayersHave = wandsPlayersHave; }

    public Map<Player, Integer> getTurnsPlayersHave() { return turnsPlayersHave; }
    public void setTurnsPlayersHave(Map<Player, Integer> turnsPlayersHave) { this.turnsPlayersHave = turnsPlayersHave; }

    public int getCurrentPosToCapture() { return currentPosToCapture; }
    public void setCurrentPosToCapture(int currentPosToCapture) { this.currentPosToCapture = currentPosToCapture; }

    public Tile getCurrentTileToCapture() { return currentTileToCapture; }
    public void setCurrentTileToCapture(Tile currentTileToCapture) { this.currentTileToCapture = currentTileToCapture; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    // Additional Logic
    public void nextTurn() {
        currentTurn += 1;
    }
    
    public void resetTurns() {
        turnsPlayersHave.replaceAll((player, turns) -> 0);
    }
    
    public void startGame() {
        this.gameStarted = true;
        this.gameOver = false;
        this.currentTurn = 0;
        this.scoreManager.resetScores(); // Reset scores in ScoreManager
    }
    
    public void endGame() {
        this.gameOver = true;
        this.statusMessage = "Game Over";
        Player winner = scoreManager.getWinner(); // Fetch the winner from ScoreManager
        if (winner != null) {
            updateStatusMessage("Winner: " + winner.getUsername());
        }
    }
    
    public void updateStatusMessage(String message) {
        this.statusMessage = message;
    }
    
    public void incrementTurnForPlayer(Player player) {
        turnsPlayersHave.put(player, turnsPlayersHave.get(player) + 1);
    }
    
    public void decrementTurnForPlayer(Player player) {
        turnsPlayersHave.put(player, Math.max(0, turnsPlayersHave.get(player) - 1));
    }

    public void updateScore(Player player, int amount) {
        if (players.contains(player)) {
            int newScore = scores.get(player) + amount;
            scores.put(player, newScore);
            scoreManager.setScore(player.getUsername(), newScore); // Update score in ScoreManager
        }
    }
}
