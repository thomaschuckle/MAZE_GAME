package view;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel ingredient1Label;
    private JLabel ingredient2Label;
    private JLabel ingredient3Label;

    // Constructor
    public CardPanel() {
        // Set layout for the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initialize labels (empty initially, will be updated by controller)
        ingredient1Label = new JLabel("Ingredient 1: ");
        ingredient2Label = new JLabel("Ingredient 2: ");
        ingredient3Label = new JLabel("Ingredient 3: ");

        // Customize font for better visibility
        ingredient1Label.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredient2Label.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredient3Label.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add labels to the panel
        add(ingredient1Label);
        add(ingredient2Label);
        add(ingredient3Label);
        
        // Set preferred size for the panel
        setPreferredSize(new Dimension(200, 150));
    }

    // Method to update the view with new ingredient values
    public void updateIngredients(int ingredient1, int ingredient2, int ingredient3) {
        ingredient1Label.setText("Ingredient 1: " + ingredient1);
        ingredient2Label.setText("Ingredient 2: " + ingredient2);
        ingredient3Label.setText("Ingredient 3: " + ingredient3);
    }
}
