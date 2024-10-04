package Main;

import java.awt.*;
import javax.swing.*;
import ui.UIPrototype;

public class Main {
	public static void main(String[] args) {
	    UIPrototype uiPrototype = new UIPrototype(); // Create UIPrototype instance

	    JFrame frame = createMainFrame(uiPrototype); // Create the main frame
	    frame.setJMenuBar(createMenuBar()); // Add the menu bar

	    // Create a split pane layout (15/85 proportion)
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setResizeWeight(0.15);  // 35% for the left panel, 70% for the right panel
	    splitPane.setEnabled(false);     // Disable manual resizing of the split

	    // Add left (user info + chat) and right (maze) panels to the split pane
	    splitPane.setLeftComponent(createLeftPanel(uiPrototype));
	    splitPane.setRightComponent(createMazePanel(uiPrototype));

	    frame.add(splitPane);  // Add the split pane to the main frame
	    frame.pack();          // Adjust the frame size based on components' preferred sizes
	    
	    // Set the frame to be centered on the screen *after* packing
	    frame.setLocationRelativeTo(null);
	    
	    frame.setVisible(true); // Display the frame
	}

    private static JFrame createMainFrame(UIPrototype uiPrototype) {
        JFrame frame = new JFrame("Maze Game UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(uiPrototype.getMaxDim()[0], uiPrototype.getMaxDim()[1]));
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Load"));
        fileMenu.add(new JMenuItem("Save"));

        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(new JMenuItem("Restart"));
        gameMenu.add(new JMenuItem("About"));

        JMenu languageMenu = new JMenu("Language");
        languageMenu.add(new JMenuItem("English"));
        languageMenu.add(new JMenuItem("Vietnamese"));

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(languageMenu);
        menuBar.add(new JMenu("Help"));
        menuBar.add(new JMenu("Network"));

        return menuBar;
    }

    // Combine the User Info panel and the Chat panel into the left side
    private static JPanel createLeftPanel(UIPrototype uiPrototype) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        // Add User Info and Chat panel to the left panel
        leftPanel.add(createUserInfoPanel(uiPrototype));
        leftPanel.add(createChatPanel(uiPrototype));

        // Add action buttons panel at the bottom
        leftPanel.add(createActionButtonsPanel(uiPrototype));
        return leftPanel;
    }

    private static JPanel createUserInfoPanel(UIPrototype uiPrototype) {
        // Outer panel with BorderLayout to control overall placement
        JPanel userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Info"));
        userInfoPanel.setPreferredSize(new Dimension(uiPrototype.getPInfoDim()[0], uiPrototype.getPInfoDim()[1]));

        // Inner panel with BoxLayout to stack labels vertically
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS)); // Vertical layout for labels

        // Create labels for user information
        JLabel usernameLabel = new JLabel("Username: Player1");
        JLabel scoreLabel = new JLabel("Score: 100");

        // Align components to the left
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add the labels to the inner panel
        labelsPanel.add(usernameLabel);
        labelsPanel.add(scoreLabel);

        // Add glue to push the labels up if necessary
        labelsPanel.add(Box.createVerticalGlue());

        // Add the inner panel (with labels) to the center of the outer panel
        userInfoPanel.add(labelsPanel, BorderLayout.CENTER);

        return userInfoPanel;
    }

    private static JPanel createChatPanel(UIPrototype uiPrototype) {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatPanel.setPreferredSize(new Dimension(uiPrototype.getChatDim()[0], uiPrototype.getChatDim()[1]));

        JTextArea chatArea = new JTextArea(5, 20);
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        JTextField chatInputField = new JTextField();
        JButton sendButton = new JButton("Send");
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        return chatPanel;
    }

    // Action buttons panel (optional for your game, but you can add controls here)
    private static JPanel createActionButtonsPanel(UIPrototype uiPrototype) {
        // Outer panel to hold the split pane with GridLayout
        JPanel actionButtonsPanel = new JPanel(new GridLayout(1, 1)); // 1 row, 1 column
        actionButtonsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        // Create the 60% panel with BoxLayout for the main buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Vertical layout

        // Dimension for the buttons
        Dimension buttonSize = new Dimension(uiPrototype.getActionButtonDim()[0], uiPrototype.getActionButtonDim()[1]);

        // Create and add main buttons
        JButton endTurnButton = new JButton("End Turn");
        JButton useWandButton = new JButton("Use Wand");
        JButton viewCardButton = new JButton("View Card");
        JButton insertTileButton = new JButton("Insert Tile");

        // Set preferred size for the main buttons
        setButtonSize(endTurnButton, buttonSize);
        setButtonSize(useWandButton, buttonSize);
        setButtonSize(viewCardButton, buttonSize);
        setButtonSize(insertTileButton, buttonSize);

        // Add buttons to the main buttons panel
        buttonsPanel.add(endTurnButton);
        buttonsPanel.add(useWandButton);
        buttonsPanel.add(viewCardButton);
        buttonsPanel.add(insertTileButton);

        // Add vertical glue to push buttons to the top if needed
        buttonsPanel.add(Box.createVerticalGlue());

        // Create the 40% panel with BoxLayout for additional buttons
        JPanel moveButtonsPanel = new JPanel();
        moveButtonsPanel.setLayout(new BoxLayout(moveButtonsPanel, BoxLayout.Y_AXIS)); // Vertical layout

        // Create and add additional buttons
        JButton upButton = new JButton("^");
        JButton downButton = new JButton("v");
        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        // Set preferred size for the additional buttons
        setButtonSize(upButton, buttonSize);
        setButtonSize(downButton, buttonSize);
        setButtonSize(leftButton, buttonSize);
        setButtonSize(rightButton, buttonSize);

        moveButtonsPanel.add(upButton);
        moveButtonsPanel.add(downButton);
        moveButtonsPanel.add(leftButton);
        moveButtonsPanel.add(rightButton);

        // Add vertical glue to push buttons to the top if needed
        moveButtonsPanel.add(Box.createVerticalGlue());

        // Create a JSplitPane to split the buttonsPanel and additionalButtonsPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPanel, moveButtonsPanel);
        splitPane.setDividerLocation(0.6); // Maintain 60/40 ratio
        splitPane.setResizeWeight(0.6); // Maintain 60/40 ratio when resizing

        // Add the split pane to the actionButtonsPanel
        actionButtonsPanel.add(splitPane);

        return actionButtonsPanel;
    }

    private static void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }


    private static JPanel createMazePanel(UIPrototype uiPrototype) {
        // Create the main container panel with a fixed size of 700x700
        JPanel mazeContainerPanel = new JPanel(new GridBagLayout());  // Use GridBagLayout to center components
        mazeContainerPanel.setPreferredSize(new Dimension(uiPrototype.getMazeAreaDim()[0], uiPrototype.getMazeAreaDim()[1])); // 700x700 container
        mazeContainerPanel.setBorder(BorderFactory.createTitledBorder("Maze"));

        // Create the actual maze panel with a size of 400x400
        JPanel mazePanel = new JPanel(new GridLayout(7, 7)); // 7x7 maze
        mazePanel.setPreferredSize(new Dimension(uiPrototype.getMazeDim()[0], uiPrototype.getMazeDim()[1])); // 400x400 maze
        mazePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border for the maze

        // Populate the maze with buttons (or any components)
        for (int i = 0; i < (7 * 7); i++) {
            JPanel tilePanel = new JPanel(new GridLayout(1, 1));
            tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mazePanel.add(tilePanel);
        }

        // Use GridBagConstraints to center the maze panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0); // No insets (optional)
        gbc.anchor = GridBagConstraints.CENTER; // Center the maze within the container

        // Add the maze panel to the main container panel
        mazeContainerPanel.add(mazePanel, gbc);

        return mazeContainerPanel; // Return the main container panel
    }
}