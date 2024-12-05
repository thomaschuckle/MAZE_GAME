package Main;

import model.*;
import view.*;
import controller.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        
        // Create a CountDownLatch to wait for the SplashIntro to finish
        CountDownLatch latch = new CountDownLatch(1);
        
        // Show the splash intro screen first
        SplashIntro splashIntro = new SplashIntro(latch); // Pass latch to SplashIntro
        
        // Wait for the user to finish with the splash intro
        try {
            latch.await();  // Block here until the latch count reaches zero (when the splash intro is done)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get the selected game mode (0 for Local, 1 for Network)
        int gameMode = splashIntro.getGameMode();
        
        // Check if game mode is selected, otherwise, exit
        if (gameMode == -1) {
            JOptionPane.showMessageDialog(null, "No game mode selected. Exiting...");
        }

        // Handle Local Game Mode
        if (gameMode == 0) {
            // Ask for number of players (local game mode)
            int numberOfPlayers = splashIntro.getNumberOfPlayers();
            
            // Validate number of players for local game
            if (numberOfPlayers < 1 || numberOfPlayers > 4) {
                JOptionPane.showMessageDialog(null, "Please select between 1 and 4 players for local mode.");
                return;  // Exit if the number of players is out of valid range
            }
            
            // Initialize models based on the number of players
            ArrayList<Card> cardList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {  // Pre-generate 4 recipes for local play
                Card recipe = new Card();
                recipe.generateRecipe();
                cardList.add(recipe);
            }

            // Create players based on the number of players selected
            ArrayList<Player> playerList = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                playerList.add(new Player());
            }

            // Game models for logic
            ScoreManager scoreManagerModel = new ScoreManager();
            Chat chatModel = new Chat();
            Maze mazeModel = new Maze();
            
            GameState gameStateModel = new GameState(playerList.size(), playerList, "English", scoreManagerModel);
            
            // Initialize views for local mode
            UIPrototype uiPrototype = new UIPrototype();
            CardPanel cardPanel = new CardPanel();
            ChatPanel chatPanel = new ChatPanel(uiPrototype);
            ControlPanel controlPanel = new ControlPanel(uiPrototype);
            InfoPanel userInfoPanel = new InfoPanel(uiPrototype);
            MazePanel mazePanel = new MazePanel(uiPrototype);
            MenuPanel menuPanel = new MenuPanel();
            
            // New MainFrame for local play
            MainFrame mainFrame = new MainFrame(cardPanel, chatPanel, controlPanel, userInfoPanel, mazePanel, menuPanel, uiPrototype);
            
            // Initialize controller for local game
            GameController gameController = new GameController(cardList, playerList, 
                    scoreManagerModel, chatModel, 
                    mazeModel, gameStateModel, 
                    cardPanel, chatPanel, 
                    controlPanel, userInfoPanel, 
                    mazePanel, menuPanel);
            
            gameController.initializeGame();  // Start the local game
            mainFrame.setVisible(true);
            
        } else if (gameMode == 1) {
            // Network Game Mode
            // Get user role in network mode (0 for Host, 1 for Join)
            int userRoleInNetworkMode = splashIntro.getUserRoleInNetworkMode();
            
            // Validate user role selection
            if (userRoleInNetworkMode == -1) {
                JOptionPane.showMessageDialog(null, "No role selected for network mode. Exiting...");
                return;
            }

            // Ask for number of players for network mode (this should happen before initializing models)
            int numberOfPlayers = splashIntro.getNumberOfPlayers();
            
            // Validate the number of players for network game mode
            if ((numberOfPlayers < 2 && splashIntro.getUserRoleInNetworkMode()  != 1) || (numberOfPlayers > 4 && splashIntro.getUserRoleInNetworkMode()  != 1)) { // Assuming network mode requires at least 2 players
                JOptionPane.showMessageDialog(null, "Please select between 2 and 4 players for network mode.");
                return;  // Exit if the number of players is out of valid range
            }
            
            // Initialize models for multiplayer game based on number of players
            ArrayList<Card> cardList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {  // Pre-generate 4 recipes for multiplayer
                Card recipe = new Card();
                recipe.generateRecipe();
                cardList.add(recipe);
            }

            ArrayList<Player> playerList = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                playerList.add(new Player());  // Add the correct number of players
            }

            // Game models for multiplayer logic
            ScoreManager scoreManagerModel = new ScoreManager();
            Chat chatModel = new Chat();
            Maze mazeModel = new Maze();
            
            GameState gameStateModel = new GameState(playerList.size(), playerList, "English", scoreManagerModel);
            
            // Initialize views for network mode
            UIPrototype uiPrototype = new UIPrototype();
            CardPanel cardPanel = new CardPanel();
            ChatPanel chatPanel = new ChatPanel(uiPrototype);
            ControlPanel controlPanel = new ControlPanel(uiPrototype);
            InfoPanel userInfoPanel = new InfoPanel(uiPrototype);
            MazePanel mazePanel = new MazePanel(uiPrototype);
            MenuPanel menuPanel = new MenuPanel();
            
            // New MainFrame for multiplayer
            MainFrame mainFrame = new MainFrame(cardPanel, chatPanel, controlPanel, userInfoPanel, mazePanel, menuPanel, uiPrototype);
            
            // Initialize multiplayer controller
            MultiplayerController multiplayerController = new MultiplayerController(cardList, playerList, 
                    scoreManagerModel, chatModel, 
                    mazeModel, gameStateModel, 
                    cardPanel, chatPanel, 
                    controlPanel, userInfoPanel, 
                    mazePanel, menuPanel);
            
            // Handle multiplayer host or join based on role
            if (userRoleInNetworkMode == 0) {
                // Host Multiplayer Game
                JOptionPane.showMessageDialog(null, "Starting as Host...");
                
                // Create and show the NetworkInfoFrame
                NetworkInfoFrame networkInfoFrame = new NetworkInfoFrame();
                networkInfoFrame.showHostScreen(); // Show Host setup screen
                
                // Wait for the user to complete the host setup
                // This could be handled by checking if the setup is complete in the frame
                while (networkInfoFrame.isVisible()) {
                    // Keep the event loop running while the frame is visible
                    try {
                        Thread.sleep(100); // Sleep briefly to prevent blocking the UI thread
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                // Now that the user has finished setting up, proceed with the main frame
                mainFrame.setVisible(true); // Show the main game screen
                multiplayerController.setupHostGame();  // Call logic to start hosting the game
                
            } else if (userRoleInNetworkMode == 1) {
                // Join Multiplayer Game
                JOptionPane.showMessageDialog(null, "Joining Game...");
                
                // Create and show the NetworkInfoFrame
                NetworkInfoFrame networkInfoFrame = new NetworkInfoFrame();
                networkInfoFrame.showJoinScreen(); // Show Join setup screen
                
                // Wait for the user to complete the join setup
                while (networkInfoFrame.isVisible()) {
                    // Keep the event loop running while the frame is visible
                    try {
                        Thread.sleep(100); // Sleep briefly to prevent blocking the UI thread
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                // Now that the user has finished setting up, proceed with the main frame
                mainFrame.setVisible(true); // Show the main game screen
                multiplayerController.setupJoinGame();  // Call logic to start joining the game
            }

        } else {
        	System.out.println("Error, no selected game mode");
        }
    }
}
