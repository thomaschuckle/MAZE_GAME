package Main;

import model.*;
import view.*;
import controller.*;

public class Main {
    public static void main(String[] args) {
        
        // Initialize models
//        Card cardModel = new Card();
//        Chat chatModel = new Chat();
//        GameState gameStateModel = new GameState();
//        Maze mazeModel = new Maze();
//        Player playerModel = new Player();
//        ScoreManager scoreManagerModel = new ScoreManager();
//        
//        // Initialize views
        CardPanel cardPanel = new CardPanel();
        ChatPanel chatPanel = new ChatPanel();
        ControlPanel controlPanel = new ControlPanel();
        InfoPanel userInfoPanel = new InfoPanel();
        MazePanel mazePanel = new MazePanel();
        MenuPanel menuPanel = new MenuPanel();
        
        // Initialize controllers with dependencies
//        CardController cardController = new CardController(cardModel, cardPanel, gameStateModel);
//        ChatController chatController = new ChatController(chatModel, chatPanel);
//        CollisionManager collisionManager = new CollisionManager(mazeModel, playerModel);
//        GameController gameController = new GameController(playerModel, gameStateModel, scoreManagerModel, mazeModel, cardController, collisionManager);
//        InputManager inputManager = new InputManager(gameController);
//        MazeLoader mazeLoader = new MazeLoader(mazeModel);

        // Link views with controllers
//        controlPanel.setInputManager(inputManager);  // ControlPanel handles user commands through inputManager
//        mazePanel.setGameController(gameController);  // MazePanel updates the game state via gameController

        // New MainFrame that incorporates all views
        UIPrototype uiPrototype = new UIPrototype();
        MainFrame mainFrame = new MainFrame(cardPanel, chatPanel, controlPanel, userInfoPanel, mazePanel, menuPanel, uiPrototype);
        
//        // Pass MainFrame to GameController (if it needs to update the entire UI)
//        gameController.setMainFrame(mainFrame);
//        
//        // Start the application by setting up the initial game state
//        gameController.initializeGame();
        mainFrame.setVisible(true);
    }
}
