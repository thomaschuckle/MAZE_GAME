package model;

import java.awt.*;
import javax.swing.*;

public class Tile {
    private int x;
    private int y;
    private Image image;  // Image for the tile
    private Image coinImage;  // Image for the coin that may appear on top of the tile
    private JPanel panel;

    public Tile(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.coinImage = null;  // Initially, no coin image assigned

        this.panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Draw the tile image
                }
                if (coinImage != null) {
                    // Draw the coin image on top of the tile, in the center
                    int coinSize = getWidth() / 4;  // Set the coin size to 1/4th of the tile size
                    int coinX = (getWidth() - coinSize) / 2;
                    int coinY = (getHeight() - coinSize) / 2;
                    g.drawImage(coinImage, coinX, coinY, coinSize, coinSize, this);  // Draw the coin image
                }
            }
        };
        this.panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.panel.setPreferredSize(new Dimension(100, 100));  // Make each tile a square
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setImage(Image image) {
        this.image = image;
        this.panel.repaint(); // Repaint the panel to reflect the new image
    }

    public Image getImage() {
        return image;
    }

    // Set the coin image for this tile
    public void setCoinImage(Image coinImage) {
        this.coinImage = coinImage;
        this.panel.repaint();  // Repaint the panel to reflect the new coin image
    }

    // Get the coin image for this tile
    public Image getCoinImage() {
        return coinImage;
    }
}
