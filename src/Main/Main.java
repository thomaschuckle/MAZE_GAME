package Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Random;

import ui.*;

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

	 // Creating main frame
    private static JFrame createMainFrame(UIPrototype uiPrototype) {
        JFrame frame = new JFrame("Maze Game UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(uiPrototype.getMaxDim()[0], uiPrototype.getMaxDim()[1]));
        frame.setLocationRelativeTo(null);
        Image iconImage = Toolkit.getDefaultToolkit().getImage("Assets/blue.png");
        
        // Set the icon image for the frame
        frame.setIconImage(iconImage);
        return frame;
    }

    // Creating menu bar
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

    // Creating user info panel
    private static JPanel createUserInfoPanel(UIPrototype uiPrototype) {
    	// Outer panel with BorderLayout to control overall placement
    	JPanel userInfoPanel = new JPanel(new BorderLayout());

    	// Create the titled border with a white title
    	TitledBorder titledBorder = BorderFactory.createTitledBorder("User Info");
    	titledBorder.setTitleColor(Color.decode("#d7bcac")); // Set title color

    	// Set the border to the userInfoPanel
    	userInfoPanel.setBorder(titledBorder);
    	userInfoPanel.setPreferredSize(new Dimension(uiPrototype.getPInfoDim()[0], uiPrototype.getPInfoDim()[1]));
    	userInfoPanel.setBackground(Color.decode("#013743")); // Set the background color
    	
        // Inner panel with BoxLayout to stack labels vertically
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS)); // Vertical layout for labels
        labelsPanel.setBackground(Color.decode("#013743"));
        
        // Create labels for user information
        JLabel userTurnLabel = new JLabel("PLAYER 1's TURN");
        userTurnLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userTurnLabel.setForeground(Color.decode("#fb7f05"));
        labelsPanel.add(userTurnLabel);

        JLabel instructionLabel = new JLabel("Click arrow in maze to insert block");
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionLabel.setForeground(Color.decode("#fb7f05"));
        labelsPanel.add(instructionLabel);

        labelsPanel.add(Box.createVerticalStrut(10)); // Add vertical space

        for (int i = 0; i < 4; i++) {
            JLabel userLabel = new JLabel("P" + (i + 1) + ": 0 pts | ////");
            userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            userLabel.setForeground(Color.decode("#f62002"));
            labelsPanel.add(userLabel);
        }

        labelsPanel.add(Box.createVerticalStrut(10)); // Add vertical space
        
        JLabel currentPosLabel = new JLabel("Current position to capture: ");
        currentPosLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentPosLabel.setForeground(Color.decode("#f62002"));
        labelsPanel.add(currentPosLabel);
        
        labelsPanel.add(Box.createVerticalStrut(40)); // Add vertical space

        // Create a new panel for the current block and controls
        JPanel labelsPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout for horizontal alignment
        labelsPanel2.setBackground(Color.decode("#013743"));
        
        JLabel currentBlockLabel = new JLabel("Current Block: ");
        currentBlockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentBlockLabel.setForeground(Color.decode("#ff7f00"));
        labelsPanel2.add(currentBlockLabel);

        // Create a 1x1 square for the current block
        JPanel currentBlock = new JPanel();
        currentBlock.setPreferredSize(new Dimension(60, 60)); // Set size for the block
        currentBlock.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelsPanel2.add(currentBlock); // Add the square after the label

        labelsPanel2.add(Box.createHorizontalStrut(1)); // Add horizontal space between the block and buttons

        // Button panel with FlowLayout for horizontal alignment
        Dimension buttonSize = new Dimension(45, 20); // Define uniform size for buttons

        JButton leftTurnButton = new JButton("<-");
        setButtonSize(leftTurnButton, buttonSize);
        leftTurnButton.setBackground(Color.decode("#0b6e89")); // Set background color for left button (green)
        leftTurnButton.setForeground(Color.WHITE); // Set text color for left button
        labelsPanel2.add(leftTurnButton); // Add the left button

        JButton rightTurnButton = new JButton("->");
        setButtonSize(rightTurnButton, buttonSize);
        rightTurnButton.setBackground(Color.decode("#0b6e89")); // Set background color for right button (red)
        rightTurnButton.setForeground(Color.WHITE); // Set text color for right button
        labelsPanel2.add(rightTurnButton); // Add the right button

        // Add the new labelsPanel2 to the userInfoPanel
        userInfoPanel.add(labelsPanel, BorderLayout.NORTH); // Add labelsPanel to the top
        userInfoPanel.add(labelsPanel2, BorderLayout.CENTER); // Add labelsPanel2 to the center

        // Add glue to push the labels up if necessary
        labelsPanel.add(Box.createVerticalGlue());
        
        return userInfoPanel;
    }

    // Creating chat panel
    private static JPanel createChatPanel(UIPrototype uiPrototype) {
    	JPanel chatPanel = new JPanel(new BorderLayout());

    	// Create the titled border with a white title
    	TitledBorder chatBorder = BorderFactory.createTitledBorder("Chat");
    	chatBorder.setTitleColor(Color.decode("#fc019c")); // Set title color

    	// Set the border to the chatPanel
    	chatPanel.setBorder(chatBorder);
    	chatPanel.setPreferredSize(new Dimension(uiPrototype.getChatDim()[0], uiPrototype.getChatDim()[1]));
    	chatPanel.setBackground(Color.decode("#014342")); // Set the background color

    	// Create the chat area
    	JTextArea chatArea = new JTextArea(5, 20);
    	chatArea.setEditable(false);
    	chatArea.setBackground(Color.decode("#014342")); // Set chat area background color
    	chatArea.setForeground(Color.decode("#fc019c")); // Set text color

    	// Create the scroll pane for chat area
    	JScrollPane chatScrollPane = new JScrollPane(chatArea);
    	chatScrollPane.setBackground(Color.decode("#013743")); // Set background color of scroll pane
    	chatPanel.add(chatScrollPane, BorderLayout.CENTER);

    	// Create the input field for chat
    	JTextField chatInputField = new JTextField();
    	chatInputField.setBackground(Color.decode("#014342")); // Set input field background color
    	chatInputField.setForeground(Color.decode("#fc019c")); // Set text color
    	// Create the send button and set background color
    	JButton sendButton = new JButton("Send");
    	sendButton.setBackground(Color.decode("#4CAF50")); // Set button background color
    	sendButton.setForeground(Color.decode("#FFFFFF")); // Set button text color 

    	// Create a panel for input and set background color
    	JPanel inputPanel = new JPanel(new BorderLayout());
    	inputPanel.setBackground(Color.decode("#013743")); // Set input panel background color
    	inputPanel.add(chatInputField, BorderLayout.CENTER);
    	inputPanel.add(sendButton, BorderLayout.EAST);

    	// Add the input panel to the chat panel
    	chatPanel.add(inputPanel, BorderLayout.SOUTH);

    	return chatPanel;
    }

    // Creating action buttons panel
    private static JPanel createActionButtonsPanel(UIPrototype uiPrototype) {
    	JPanel actionButtonsPanel = new JPanel(new GridLayout(1, 1)); // 1 row, 1 column
    	actionButtonsPanel.setBorder(BorderFactory.createTitledBorder(
    	        BorderFactory.createLineBorder(Color.WHITE), // Border line color
    	        "Actions", // Title text
    	        TitledBorder.LEFT, // Title position
    	        TitledBorder.TOP, // Title position
    	        new Font("Arial", Font.BOLD, 14), // Title font
    	        Color.decode("#ffffff") // Title color (white)
    	));
    	actionButtonsPanel.setBackground(Color.decode("#803925")); // Set background color

        // Create the 60% panel with BoxLayout for the main buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Vertical layout
        buttonsPanel.setBackground(Color.decode("#803925")); // Set background color for the main buttons panel

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

        // Set button colors
        endTurnButton.setBackground(Color.decode("#848484")); // Set background color for "End Turn" button
        endTurnButton.setForeground(Color.decode("#000000")); // Set text color

        useWandButton.setBackground(Color.decode("#848484")); // Set background color for "Use Wand" button
        useWandButton.setForeground(Color.decode("#000000")); // Set text color

        viewCardButton.setBackground(Color.decode("#848484")); // Set background color for "View Card" button
        viewCardButton.setForeground(Color.decode("#000000")); // Set text color

        insertTileButton.setBackground(Color.decode("#848484")); // Set background color for "Insert Tile" button
        insertTileButton.setForeground(Color.decode("#000000")); // Set text color

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
        moveButtonsPanel.setBackground(Color.decode("#803925")); // Set background color for the move buttons panel

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

        // Set colors for additional buttons
        upButton.setBackground(Color.decode("#848484")); // Set background color for up button
        upButton.setForeground(Color.decode("#000000")); // Set text color

        downButton.setBackground(Color.decode("#848484")); // Set background color for down button
        downButton.setForeground(Color.decode("#000000")); // Set text color

        leftButton.setBackground(Color.decode("#848484")); // Set background color for left button
        leftButton.setForeground(Color.decode("#000000")); // Set text color

        rightButton.setBackground(Color.decode("#848484")); // Set background color for right button
        rightButton.setForeground(Color.decode("#000000")); // Set text color

        // Add buttons to the move buttons panel
        moveButtonsPanel.add(upButton);
        moveButtonsPanel.add(downButton);
        moveButtonsPanel.add(leftButton);
        moveButtonsPanel.add(rightButton);

        // Add vertical glue to push buttons to the top if needed
        moveButtonsPanel.add(Box.createVerticalGlue());

        // Create a JSplitPane to split the buttonsPanel and moveButtonsPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPanel, moveButtonsPanel);
        splitPane.setDividerLocation(0.6); // Maintain 60/40 ratio
        splitPane.setResizeWeight(0.6); // Maintain 60/40 ratio when resizing

        // Add the split pane to the actionButtonsPanel
        actionButtonsPanel.add(splitPane);

        return actionButtonsPanel;
    }

    // Helper method for setting button size
    private static void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }
    
    // Creating the maze panel
    private static JPanel createMazePanel(UIPrototype uiPrototype) {
        // Load your background image
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("Assets/output.jpg");

        // Create the main container panel with GridBagLayout
        JPanel mazeContainerPanel = new JPanel(new GridBagLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        mazeContainerPanel.setPreferredSize(new Dimension(uiPrototype.getMazeAreaDim()[0], uiPrototype.getMazeAreaDim()[1]));
        mazeContainerPanel.setBorder(BorderFactory.createTitledBorder("Maze"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1); // Add some padding between tiles and buttons
        gbc.fill = GridBagConstraints.BOTH;

        // Define the size for each tile
        int tileSize = Math.min(uiPrototype.getMazeDim()[0] / 7, uiPrototype.getMazeDim()[1] / 7);

        // Load tile images into an array
        Image[] tileImages = new Image[13];
        for (int i = 0; i < tileImages.length; i++) {
            tileImages[i] = Toolkit.getDefaultToolkit().getImage("Assets/maze_path/" + i + ".png");

            // Check if the image is loaded properly
            if (tileImages[i] == null) {
                System.out.println("Failed to load image: " + "Assets/maze_path/" + i + ".png");
            }
        }

        // Create an instance of Random
        Random random = new Random();

        // Add tiles (7x7 grid for the maze) using Tile objects
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                // Select a random index for the tile image
                int randomIndex = random.nextInt(tileImages.length);

                // Get a random image for the current tile
                Image tileImage = tileImages[randomIndex];

                // Create a Tile object for each grid cell with its corresponding random image
                Tile tile = new Tile(row, col, tileImage);
                tile.getPanel().setMaximumSize(new Dimension(tileSize, tileSize));
                tile.getPanel().setMinimumSize(new Dimension(tileSize, tileSize));
                tile.getPanel().setPreferredSize(new Dimension(tileSize, tileSize));

                gbc.gridx = col + 1; // Adjust x to leave space for buttons on the sides
                gbc.gridy = row + 1; // Adjust y to leave space for buttons on top and bottom

                mazeContainerPanel.add(tile.getPanel(), gbc); // Add the tile's JPanel to the grid
            }
        }

        // Load images for buttons (as before)
        ImageIcon upIcon = new ImageIcon("Assets/buttons/up.png");
        ImageIcon downIcon = new ImageIcon("Assets/buttons/down.png");
        ImageIcon leftIcon = new ImageIcon("Assets/buttons/left.png");
        ImageIcon rightIcon = new ImageIcon("Assets/buttons/right.png");
        
        // Add top and bottom buttons in the container panel
        for (int col = 1; col < 7; col += 2) {
            JButton topButton = new JButton(downIcon);
            setButtonSize(topButton, new Dimension(75, 25)); // Set button size
            gbc.gridx = col + 1; // Align with grid columns
            gbc.gridy = 0; // Above the grid
            mazeContainerPanel.add(topButton, gbc);

            JButton bottomButton = new JButton(upIcon);
            setButtonSize(bottomButton, new Dimension(75, 25)); // Set button size
            gbc.gridy = 8; // Below the grid
            mazeContainerPanel.add(bottomButton, gbc);
        }

        // Add left and right buttons in the container panel
        for (int row = 1; row < 7; row += 2) {
            JButton leftButton = new JButton(rightIcon);
            setButtonSize(leftButton, new Dimension(25, 75)); // Set button size
            gbc.gridx = 0; // Left of the grid
            gbc.gridy = row + 1; // Align with grid rows
            mazeContainerPanel.add(leftButton, gbc);

            JButton rightButton = new JButton(leftIcon);
            setButtonSize(rightButton, new Dimension(25, 75)); // Set button size
            gbc.gridx = 8; // Right of the grid
            mazeContainerPanel.add(rightButton, gbc);
        }

        return mazeContainerPanel; // Return the main container panel with maze and buttons
    }

}