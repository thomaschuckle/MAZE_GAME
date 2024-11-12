package model;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class ScoreManager {
    
    private Map<String, Player> players; // A map to store players by their user names
    
    // Constructor
    public ScoreManager() {
        this.players = new HashMap<>();
    }

    // Add a player to the score manager with an initial score
    public void addPlayer(Player player) {
        if (!players.containsKey(player.getUsername())) {
            players.put(player.getUsername(), player);
        }
    }

    // Get the score of a specific player by user name
    public int getScore(String username) {
        Player player = players.get(username);
        return player != null ? player.getScore() : 0;  // Returns 0 if the player is not found
    }

    // Set the score for a specific player (called by controller to update scores)
    public void setScore(String username, int score) {
        Player player = players.get(username);
        if (player != null) {
            player.setScore(score);
        }
    }

    // Increase the score of a specific player by a specified amount (called by controller)
    public void increaseScore(String username, int amount) {
        Player player = players.get(username);
        if (player != null) {
            int newScore = player.getScore() + amount;
            player.setScore(newScore);
        }
    }

    // Decrease the score of a specific player by a specified amount (called by controller)
    public void decreaseScore(String username, int amount) {
        Player player = players.get(username);
        if (player != null) {
            int newScore = player.getScore() - amount;
            player.setScore(newScore);
        }
    }

    // Reset the scores of all players (e.g., when starting a new game)
    public void resetScores() {
        for (Player player : players.values()) {
            player.setScore(0);  // Reset all players' scores to 0
        }
    }

    // Get the score of all players (returns a map of user name to score)
    public Map<String, Integer> getAllScores() {
        Map<String, Integer> allScores = new HashMap<>();
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            allScores.put(entry.getKey(), entry.getValue().getScore());
        }
        return allScores;
    }
    
    // Get the leader board (returns players sorted by their score, highest to lowest)
    public Map<String, Integer> getLeaderboard() {
        Map<String, Integer> leaderboard = new HashMap<>();
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            leaderboard.put(entry.getKey(), entry.getValue().getScore());
        }
        leaderboard = leaderboard.entrySet().stream()
                                  .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))  // Sort in descending order
                                  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return leaderboard;
    }
}
