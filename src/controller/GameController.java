package controller;

import java.util.ArrayList;

import model.Card;
import model.Chat;
import model.GameState;
import model.Maze;
import model.Player;
import model.ScoreManager;
import view.CardPanel;
import view.ChatPanel;
import view.ControlPanel;
import view.InfoPanel;
import view.MazePanel;
import view.MenuPanel;

public class GameController {
	
	
	// Steps to finishing the controller
	
	// 1. One method to set game up.
	
	// 2. For each buttons, create new field to store buttons OR just view.getButton() 
	//    AND THEN .addActionListener() for that button
	
	// 3. Each action Listener will listen for events, if triggered, check game rules and then 
	//    update model and view accordingly. (these codes go inside block of action listener)
	
	public GameController(ArrayList<Card> cardList, ArrayList<Player> playerList, ScoreManager scoreManagerModel,
			Chat chatModel, Maze mazeModel, GameState gameStateModel, CardPanel cardPanel, ChatPanel chatPanel,
			ControlPanel controlPanel, InfoPanel userInfoPanel, MazePanel mazePanel, MenuPanel menuPanel) {
		// TODO generate maze grid (images)
	}
	
	public void initializeGame() {
		
	}

}
