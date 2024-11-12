package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating action buttons panel
    public JPanel createActionButtonsPanel(UIPrototype uiPrototype) {
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
}
