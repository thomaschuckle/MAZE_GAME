package model;

import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;
import java.awt.Image;

public class Player {
    
    // User Info:
    private String username;                        // Player's user name
    private int score;                              // Player's current score
    private int wands;                              // Number of wands currently held
    private Card recipe;                            // Player's recipe card
    private Map<String, Object> playerSettings;     // Player's settings
    private Image character;                        // Character image for player avatar
    
    // For Game Logic:
    private int[] position = new int[2];                        // Current position on 7x7 maze
    private boolean[] validMoveDirection = new boolean[4];      // Valid move directions: Left, Up, Right, Down
    private boolean isMyTurn;                                   // Whether it's the player's turn
    private boolean isInsertingTile;                            // Whether the player is inserting a tile
    private boolean isUsingWand;                                // Whether the player is using a wand
    private Deque<Integer> coinsCaptured = new ArrayDeque<>();  // History of captured coins
    private Deque<String> playerChatLog = new ArrayDeque<>();   // History of sent messages

    // Constructors -------------------------------------------------------------------------------
    public Player() {}

    public Player(String username, int score, int wands, Card recipe, Map<String, Object> playerSettings,
                  Image character, int[] position, boolean[] validMoveDirection, boolean isMyTurn,
                  boolean isInsertingTile, boolean isUsingWand, Deque<Integer> coinsCaptured,
                  Deque<String> playerChatLog) {
        this.username = username;
        this.score = score;
        this.wands = wands;
        this.recipe = recipe;
        this.playerSettings = playerSettings;
        this.character = character;
        this.position = position;
        this.validMoveDirection = validMoveDirection;
        this.isMyTurn = isMyTurn;
        this.isInsertingTile = isInsertingTile;
        this.isUsingWand = isUsingWand;
        this.coinsCaptured = coinsCaptured;
        this.playerChatLog = playerChatLog;
    }

    // Getters Setters -----------------------------------------------------------------------------
    
    // Get set user name
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Get set score
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    // Get set wands
    public int getWands() {
        return wands;
    }
    public void setWands(int wands) {
        this.wands = wands;
    }
    
    // Get set recipe
    public Card getRecipe() {
        return recipe;
    }
    public void setRecipe(Card recipe) {
        this.recipe = recipe;
    }
    
    // Get set settings
    public Map<String, Object> getPlayerSettings() {
        return playerSettings;
    }
    public void setPlayerSettings(Map<String, Object> playerSettings) {
        this.playerSettings = playerSettings;
    }
    
    // Get set positions
    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }
    public int getPosX() {
        return position[0];
    }
    public void setPosX(int x) {
        this.position[0] = x;
    }
    public int getPosY() {
        return position[1];
    }
    public void setPosY(int y) {
        this.position[1] = y;
    }
    
    // Get set valid moves
    public boolean[] getValidMoveDirection() {
        return validMoveDirection;
    }
    public void setValidMoveDirection(boolean[] validMoveDirection) {
        this.validMoveDirection = validMoveDirection;
    }
    
    // Get set my turn
    public boolean isMyTurn() {
        return isMyTurn;
    }
    public void setMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }
    
    // Get set coins captured
    public Deque<Integer> getCoinsCaptured() {
        return coinsCaptured;
    }
    public void setCoinsCaptured(Deque<Integer> coinsCaptured) {
        this.coinsCaptured = coinsCaptured;
    }
    
    // Get set user's chat log
    public Deque<String> getPlayerChatLog() {
        return playerChatLog;
    }
    public void setPlayerChatLog(Deque<String> playerChatLog) {
        this.playerChatLog = playerChatLog;
    }
    
    // Get set user's tile insert status
    public boolean isInsertingTile() {
        return isInsertingTile;
    }
    public void setInsertingTile(boolean isInsertingTile) {
        this.isInsertingTile = isInsertingTile;
    }
    
    // Get set wand usage status
    public boolean isUsingWand() {
        return isUsingWand;
    }
    public void setUsingWand(boolean isUsingWand) {
        this.isUsingWand = isUsingWand;
    }
    
    // Getters and Setters for new character image
    public Image getCharacter() {
        return character;
    }
    public void setCharacter(Image character) {
        this.character = character;
    }
    
    // Game Actions -------------------------------------------------------------------------------
    
    // Start Turn
    public void startTurn() {
        setMyTurn(true);
    }
    
 // Movement logic with valid direction checks
    public void moveLeft() {
        if (getPosX() > 0 && validMoveDirection[0]) {
            setPosX(getPosX() - 1);
        }
    }
    public void moveRight() {
        if (getPosX() < 6 && validMoveDirection[2]) {
            setPosX(getPosX() + 1);
        }
    }
    public void moveUp() {
        if (getPosY() > 0 && validMoveDirection[1]) {
            setPosY(getPosY() - 1);
        }
    }
    public void moveDown() {
        if (getPosY() < 6 && validMoveDirection[3]) {
            setPosY(getPosY() + 1);
        }
    }
   
    // End Turn
    public boolean endTurn() {
        setMyTurn(false);
        return isMyTurn();
    }

    // Use Wand
    public int useWand() {
        if (wands > 0) {
            setWands(getWands() - 1);
        }
        return getWands();
    }
    
    // View Card
    public Card viewCard() {
        return getRecipe();
    }
    
    // Insert Tile
    public boolean insertTile() {
        setInsertingTile(true);
        return isInsertingTile();
    }
   
    // Capture coins
    public void captureCoin(int coin) {
        coinsCaptured.addLast(coin);
    }
    public Integer getLastCapturedCoin() {
        return coinsCaptured.peekLast();
    }
    public Integer undoCaptureCoin() {
        return coinsCaptured.pollLast();
    }
    
    // Send messages
    public void addChatMessage(String message) {
        playerChatLog.addLast(message);
    }
    public String getLastChatMessage() {
        return playerChatLog.peekLast();
    }
}
