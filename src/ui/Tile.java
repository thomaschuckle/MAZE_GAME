package ui;

import java.awt.*;
import javax.swing.*;

public class Tile {
    private int x;
    private int y;
    private Image image;  // Image for the tile
    private JPanel panel;

    public Tile(int x, int y, Image image) {
    	
        this.x = x;
        this.y = y;
        this.image = image;
        this.panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Draw the image inside the panel
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
}