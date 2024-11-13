package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;  // Use JToggleButton instead of JButton
import javax.swing.border.LineBorder;

public class MazePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    // 7x7 array of JPanels representing the maze
    private JPanel[][] mazeGrid = new JPanel[7][7];
    
    // Background image field
    private Image backgroundImage;
    
    // Constructor for creating the maze panel
    public MazePanel(UIPrototype uiPrototype) {
        // Set initial background image
        setBackgroundImage("Assets/output.jpg"); // default background image
        
        // Create the main container panel with GridBagLayout
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(uiPrototype.getMazeAreaDim()[0], uiPrototype.getMazeAreaDim()[1]));
        setBorder(BorderFactory.createTitledBorder("Maze"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1); // Add some padding between tiles and buttons
        gbc.fill = GridBagConstraints.BOTH;
        
        // Define the size for each tile
        int tileSize = Math.min(uiPrototype.getMazeDim()[0] / 7, uiPrototype.getMazeDim()[1] / 7);
        
        // Create maze grid and assign initial tile panels
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                mazeGrid[row][col] = createTilePanel(row, col, tileSize);
                
                gbc.gridx = col + 1; // Adjust x to leave space for buttons on the sides
                gbc.gridy = row + 1; // Adjust y to leave space for buttons on top and bottom
                
                add(mazeGrid[row][col], gbc); // Add the tile's JPanel to the grid
            }
        }

        // Add top, bottom, left, and right toggle buttons (as toggle buttons)
        addButtons(gbc);
    }
    
    // Method to set the background image
    public void setBackgroundImage(String imagePath) {
        backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        repaint(); // Request a repaint to apply the background image
    }

    // Override paintComponent to paint the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method to create a tile panel with its default settings
    private JPanel createTilePanel(int row, int col, int tileSize) {
        JPanel tilePanel = new JPanel();
        tilePanel.setPreferredSize(new Dimension(tileSize, tileSize));
        tilePanel.setMinimumSize(new Dimension(tileSize, tileSize));
        tilePanel.setMaximumSize(new Dimension(tileSize, tileSize));
        
        // Customize tile panel properties or add components as needed
        
        return tilePanel;
    }

    // Getter and setter for image in a specific tile
    public void setTileImage(int row, int col, Image image) {
        // Update the image for a specific tile
        // You can customize this method to integrate with Tile objects or other representations
        mazeGrid[row][col].getGraphics().drawImage(image, 0, 0, mazeGrid[row][col]);
        repaint();
    }
    
    public Image getTileImage(int row, int col) {
        // Return the current image of the specified tile
        return null; // Placeholder, needs integration with tile model
    }

    // Helper method for adding toggle buttons around the maze grid
    private void addButtons(GridBagConstraints gbc) {
        ImageIcon upIcon = new ImageIcon("Assets/buttons/up.png");
        ImageIcon downIcon = new ImageIcon("Assets/buttons/down.png");
        ImageIcon leftIcon = new ImageIcon("Assets/buttons/left.png");
        ImageIcon rightIcon = new ImageIcon("Assets/buttons/right.png");
        
        // Add top and bottom toggle buttons
        for (int col = 1; col < 7; col += 2) {
            JToggleButton topButton = createToggleButton(downIcon, new Dimension(75, 25));
            topButton.setSelected(false);  // Ensure the button starts unselected
            gbc.gridx = col + 1;
            gbc.gridy = 0;
            add(topButton, gbc);

            JToggleButton bottomButton = createToggleButton(upIcon, new Dimension(75, 25));
            bottomButton.setSelected(false);  // Ensure the button starts unselected
            gbc.gridy = 8;
            add(bottomButton, gbc);
        }

        // Add left and right toggle buttons
        for (int row = 1; row < 7; row += 2) {
            JToggleButton leftButton = createToggleButton(rightIcon, new Dimension(25, 75));
            leftButton.setSelected(false);  // Ensure the button starts unselected
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            add(leftButton, gbc);

            JToggleButton rightButton = createToggleButton(leftIcon, new Dimension(25, 75));
            rightButton.setSelected(false);  // Ensure the button starts unselected
            gbc.gridx = 8;
            add(rightButton, gbc);
        }
    }

    // Helper method to create toggle buttons
    private JToggleButton createToggleButton(ImageIcon icon, Dimension size) {
        JToggleButton toggleButton = new JToggleButton(icon);
        toggleButton.setPreferredSize(size);
        toggleButton.setMaximumSize(size);
        toggleButton.setMinimumSize(size);

        // Set the button's selection state to false initially or true if you want it selected initially
        toggleButton.setSelected(false);

        // Make the button transparent while keeping the border visible
        toggleButton.setOpaque(false);

        // Add a border around the button for better visibility (change on selection)
        toggleButton.setBorder(new LineBorder(java.awt.Color.WHITE, 2));  // White border with 2px width

        // Add action listener to handle toggle state changes
        toggleButton.addActionListener(e -> {
            // Provide feedback on toggle state
            if (toggleButton.isSelected()) {
                // Change the border or background when selected
                toggleButton.setBorder(new LineBorder(java.awt.Color.GREEN, 2));  // Green border when selected
            } else {
                // Reset border color to default when deselected
                toggleButton.setBorder(new LineBorder(java.awt.Color.WHITE, 2));  // White border when not selected
            }
        });

        // Optional: Add a background color change to provide clearer feedback
        toggleButton.setBackground(java.awt.Color.BLACK);  // Set to black or any color to indicate pressed state
        toggleButton.setContentAreaFilled(false);  // Disable default content area filling, will use border and background
        
        // Return the toggle button with interactive visual feedback
        return toggleButton;
    }
    
    // Method to update the maze grid based on the controller's logic (e.g., player movement)
    public void updateMazeGrid() {
        // This method will be called by the controller to update the maze (e.g., player movements, item collection)
    }
}
