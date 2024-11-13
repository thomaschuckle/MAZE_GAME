package model;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class ScoreManager {
    
    // Fields ---------------------------------------------------------------------------------------

    private Map<String, Player> players;               // A map to store players by their usernames
    
    // Constructors ---------------------------------------------------------------------------------

    /**
     * Initializes the ScoreManager with an empty player map.
     */
    public ScoreManager() {
        this.players = new HashMap<>();
    }

    // Player Management ----------------------------------------------------------------------------

    /**
     * Adds a player to the score manager with an initial score.
     *
     * @param player The Player object to add.
     */
    public void addPlayer(Player player) {
        if (!players.containsKey(player.getUsername())) {
            players.put(player.getUsername(), player);
        }
    }

    // Score Management -----------------------------------------------------------------------------

    /**
     * Retrieves the score of a specific player by username.
     *
     * @param username The username of the player.
     * @return The score of the player, or 0 if the player is not found.
     */
    public int getScore(String username) {
        Player player = players.get(username);
        return player != null ? player.getScore() : 0;
    }

    /**
     * Sets the score for a specific player. Called by controller to update scores.
     *
     * @param username The username of the player.
     * @param score The new score to set for the player.
     */
    public void setScore(String username, int score) {
        Player player = players.get(username);
        if (player != null) {
            player.setScore(score);
        }
    }

    /**
     * Increases the score of a specific player by a specified amount. Called by controller.
     *
     * @param username The username of the player.
     * @param amount The amount to increase the player's score by.
     */
    public void increaseScore(String username, int amount) {
        Player player = players.get(username);
        if (player != null) {
            int newScore = player.getScore() + amount;
            player.setScore(newScore);
        }
    }

    /**
     * Decreases the score of a specific player by a specified amount. Called by controller.
     *
     * @param username The username of the player.
     * @param amount The amount to decrease the player's score by.
     */
    public void decreaseScore(String username, int amount) {
        Player player = players.get(username);
        if (player != null) {
            int newScore = player.getScore() - amount;
            player.setScore(newScore);
        }
    }

    /**
     * Resets the scores of all players, typically used when starting a new game.
     */
    public void resetScores() {
        for (Player player : players.values()) {
            player.setScore(0);  // Reset all players' scores to 0
        }
    }

    // Score Retrieval ------------------------------------------------------------------------------

    /**
     * Retrieves the scores of all players.
     *
     * @return A map of usernames to their respective scores.
     */
    public Map<String, Integer> getAllScores() {
        Map<String, Integer> allScores = new HashMap<>();
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            allScores.put(entry.getKey(), entry.getValue().getScore());
        }
        return allScores;
    }
    
    /**
     * Retrieves the leaderboard, with players sorted by their scores in descending order.
     *
     * @return A map of usernames to their scores, sorted from highest to lowest.
     */
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
    
    /**
     * Retrieves the player with the highest score.
     *
     * @return The Player object with the highest score, or null if there are no players.
     */
    public Player getWinner() {
        int maxScore = players.values().stream()
                              .mapToInt(Player::getScore)
                              .max()
                              .orElse(-1);

        return players.values().stream()
                      .filter(player -> player.getScore() == maxScore)
                      .findFirst()
                      .orElse(null);
    }
}
