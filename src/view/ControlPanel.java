package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton; // Import JToggleButton
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import javax.swing.SwingUtilities;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    // Define buttons to be used by the controller
    private JButton endTurnButton;
    private JToggleButton useWandButton; // Changed to JToggleButton
    private JButton insertTileButton; // Changed to JToggleButton
    private JButton viewCardButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;

    // Default colors for the buttons
    private Color mainButtonColor = Color.decode("#848484"); // Updated color
    private Color moveButtonColor = Color.decode("#848484"); // Updated color
    private Color buttonTextColor = Color.BLACK;

    public ControlPanel(UIPrototype uiPrototype) {
        // Initialize buttons when the panel is created
        endTurnButton = createButton("End Turn", mainButtonColor);
        useWandButton = createToggleButton("Use Wand", mainButtonColor); // Use toggle button
        insertTileButton = createButton("Insert Tile", mainButtonColor); // Use toggle button
        viewCardButton = createButton("View Card", mainButtonColor);

        upButton = createButton("^", moveButtonColor);
        downButton = createButton("v", moveButtonColor);
        leftButton = createButton("<", moveButtonColor);
        rightButton = createButton(">", moveButtonColor);

        // Set up layout and initialize the panel
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), 
                "Actions", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Arial", Font.BOLD, 14), 
                Color.decode("#ffffff")
        ));
        setBackground(Color.decode("#803925"));
        
        // Create and add buttons panel
        JPanel actionButtonsPanel = createActionButtonsPanel(uiPrototype);
        add(actionButtonsPanel);
    }

    // Create a regular button with a specific label and background color
    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(buttonTextColor); // Set text color
        return button;
    }

    // Create a toggle button with a specific label and background color
    private JToggleButton createToggleButton(String text, Color backgroundColor) {
        JToggleButton toggleButton = new JToggleButton(text);
        toggleButton.setBackground(backgroundColor);
        toggleButton.setForeground(buttonTextColor); // Set text color
        return toggleButton;
    }

    private JPanel createActionButtonsPanel(UIPrototype uiPrototype) {
        JPanel actionButtonsPanel = new JPanel(new GridLayout(1, 1));
        actionButtonsPanel.setBackground(Color.decode("#803925"));

        // Create the main buttons panel (60% of the width)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.decode("#803925"));

        Dimension buttonSize = new Dimension(uiPrototype.getActionButtonDim()[0], uiPrototype.getActionButtonDim()[1]);
        setButtonSize(endTurnButton, buttonSize);
        setToggleButtonSize(useWandButton, buttonSize);
        setButtonSize(insertTileButton, buttonSize);
        setButtonSize(viewCardButton, buttonSize);

        buttonsPanel.add(endTurnButton);
        buttonsPanel.add(useWandButton);
        buttonsPanel.add(insertTileButton);
        buttonsPanel.add(viewCardButton);

        // Create the move buttons panel (40% of the width)
        JPanel moveButtonsPanel = new JPanel();
        moveButtonsPanel.setLayout(new BoxLayout(moveButtonsPanel, BoxLayout.Y_AXIS));
        moveButtonsPanel.setBackground(Color.decode("#803925"));

        setButtonSize(upButton, buttonSize);
        setButtonSize(downButton, buttonSize);
        setButtonSize(leftButton, buttonSize);
        setButtonSize(rightButton, buttonSize);

        moveButtonsPanel.add(upButton);
        moveButtonsPanel.add(downButton);
        moveButtonsPanel.add(leftButton);
        moveButtonsPanel.add(rightButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPanel, moveButtonsPanel);
        splitPane.setDividerLocation(0.6);
        splitPane.setResizeWeight(0.6);
        
        actionButtonsPanel.add(splitPane);
        return actionButtonsPanel;
    }

    // Method for setting the button size
    private void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }
    
    private void setToggleButtonSize(JToggleButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }

    // Update method to refresh the panel when button colors change
    public void updatePanel() {
        SwingUtilities.invokeLater(() -> {
            // Apply colors to the buttons
            endTurnButton.setBackground(mainButtonColor);
            useWandButton.setBackground(mainButtonColor);
            insertTileButton.setBackground(mainButtonColor);
            viewCardButton.setBackground(mainButtonColor);

            upButton.setBackground(moveButtonColor);
            downButton.setBackground(moveButtonColor);
            leftButton.setBackground(moveButtonColor);
            rightButton.setBackground(moveButtonColor);

            // Set the text color for all buttons
            endTurnButton.setForeground(buttonTextColor);
            useWandButton.setForeground(buttonTextColor);
            insertTileButton.setForeground(buttonTextColor);
            viewCardButton.setForeground(buttonTextColor);

            upButton.setForeground(buttonTextColor);
            downButton.setForeground(buttonTextColor);
            leftButton.setForeground(buttonTextColor);
            rightButton.setForeground(buttonTextColor);

            revalidate();  // Refresh the layout
            repaint();     // Force repaint of the panel
        });
    }

    // Getter methods for the controller to interact with buttons
    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    public JToggleButton getUseWandButton() {
        return useWandButton; // Return the toggle button
    }

    public JButton getInsertTileButton() {
        return insertTileButton; // Return the toggle button
    }

    public JButton getViewCardButton() {
        return viewCardButton;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    // Method for adding listeners (controller will pass the listeners)
    public void addButtonListener(String buttonName, ActionListener listener) {
        switch (buttonName) {
            case "endTurn":
                endTurnButton.addActionListener(listener);
                break;
            case "useWand":
                useWandButton.addActionListener(listener);
                break;
            case "insertTile":
                insertTileButton.addActionListener(listener);
                break;
            case "viewCard":
                viewCardButton.addActionListener(listener);
                break;
            case "up":
                upButton.addActionListener(listener);
                break;
            case "down":
                downButton.addActionListener(listener);
                break;
            case "left":
                leftButton.addActionListener(listener);
                break;
            case "right":
                rightButton.addActionListener(listener);
                break;
            default:
                break;
        }
    }

    // Getter and setter for button color properties
    public Color getMainButtonColor() {
        return mainButtonColor;
    }

    public void setMainButtonColor(Color mainButtonColor) {
        this.mainButtonColor = mainButtonColor;
        updatePanel();
    }

    public Color getMoveButtonColor() {
        return moveButtonColor;
    }

    public void setMoveButtonColor(Color moveButtonColor) {
        this.moveButtonColor = moveButtonColor;
        updatePanel();
    }

    public Color getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(Color buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
        updatePanel();
    }
}
