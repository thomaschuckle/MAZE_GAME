package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Tile;

public class MazePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating the maze panel
    public JPanel createMazePanel(UIPrototype uiPrototype) {
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

        // Load coin images into an array (25 coins from Assets/gold_1 to Assets/gold_25)
        Image[] coinImages = new Image[25];
        for (int i = 0; i < coinImages.length; i++) {
            coinImages[i] = Toolkit.getDefaultToolkit().getImage("Assets/gold_" + (i + 1) + ".png");

            // Check if the image is loaded properly
            if (coinImages[i] == null) {
                System.out.println("Failed to load coin image: " + "Assets/gold_" + (i + 1) + ".png");
            }
        }

        // Create a Map to specify unique images for specific tiles
        Map<String, Image> specificTileImages = new HashMap<>();
        specificTileImages.put("0,0", tileImages[5]);
        specificTileImages.put("0,6", tileImages[6]);
        specificTileImages.put("6,0", tileImages[3]);
        specificTileImages.put("6,6", tileImages[4]);

        specificTileImages.put("0,2", tileImages[9]);
        specificTileImages.put("0,4", tileImages[9]);
        specificTileImages.put("2,4", tileImages[9]);

        specificTileImages.put("2,0", tileImages[7]);
        specificTileImages.put("4,0", tileImages[7]);
        specificTileImages.put("2,2", tileImages[7]);

        specificTileImages.put("6,2", tileImages[8]);
        specificTileImages.put("6,4", tileImages[8]);
        specificTileImages.put("4,2", tileImages[8]);

        specificTileImages.put("2,6", tileImages[10]);
        specificTileImages.put("4,6", tileImages[10]);
        specificTileImages.put("4,4", tileImages[10]);

        // Create an instance of Random for selecting images randomly
        Random random = new Random();

        // List to keep track of remaining coin images
        List<Image> remainingCoins = new ArrayList<>(Arrays.asList(coinImages));

        // Add tiles (7x7 grid for the maze) using Tile objects
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                // Check if this tile has a specific image assigned
                String tileKey = row + "," + col;
                Image tileImage;

                if (specificTileImages.containsKey(tileKey)) {
                    tileImage = specificTileImages.get(tileKey); // Use the specific image
                } else {
                    if (row >= 1 && row <= 5 && col >= 1 && col <= 5) {
                        // For tiles [1 to 5][1 to 5], we need to exclude tileImages[1] and tileImages[2]
                        // Create a list of available images excluding tileImages[1] and tileImages[2]
                        List<Image> availableTileImages = new ArrayList<>(Arrays.asList(tileImages));
                        availableTileImages.remove(tileImages[1]);
                        availableTileImages.remove(tileImages[2]);

                        // Select a random image from the available images
                        int randomIndex = random.nextInt(availableTileImages.size());
                        tileImage = availableTileImages.get(randomIndex);
                    } else {
                        // For other tiles, select a random image from the full tileImages array
                        int randomIndex = random.nextInt(tileImages.length);
                        tileImage = tileImages[randomIndex];
                    }
                }

                // Create a Tile object for each grid cell with its corresponding image
                Tile tile = new Tile(row, col, tileImage);

                // For tiles in the range [1 to 5][1 to 5], assign a random coin image
                // Skip coin assignment for tiles [2][2], [2][4], [4][2], [4][4]
                if (row >= 1 && row <= 5 && col >= 1 && col <= 5 &&
                    !(row == 2 && col == 2) && !(row == 2 && col == 4) && !(row == 4 && col == 2) && !(row == 4 && col == 4)) {
                    
                    // Randomly pick a coin from the remaining coins
                    int coinIndex = random.nextInt(remainingCoins.size());
                    Image coinImage = remainingCoins.get(coinIndex);

                    // Set the coin image on the tile (you may have a method to add coins to the tile)
                    tile.setCoinImage(coinImage);

                    // Remove the coin from the remaining coins list
                    remainingCoins.remove(coinIndex);
                }

                // Set tile panel size and add to maze grid
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
    
    // Helper method for setting button size
    private static void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }
}
