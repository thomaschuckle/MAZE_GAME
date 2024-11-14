package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.SwingUtilities;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JButton endTurnButton;
    private final JToggleButton useWandButton;
    private final JButton insertTileButton;
    private final JButton viewCardButton;
    private final JButton upButton;
    private final JButton downButton;
    private final JButton leftButton;
    private final JButton rightButton;

    private Color mainButtonColor = Color.decode("#848484");
    private Color moveButtonColor = Color.decode("#848484");
    private Color buttonTextColor = Color.BLACK;

    // Constructor that accepts UIPrototype for setting dimensions
    public ControlPanel(UIPrototype uiPrototype) {
        // Set layout and border properties
        setLayout(new GridLayout(1, 1));
        setBackground(Color.decode("#803925"));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.decode("#ffffff")
        ));

        // Initialize buttons
        endTurnButton = createButton("End Turn", mainButtonColor);
        useWandButton = createToggleButton("Use Wand", mainButtonColor);
        insertTileButton = createButton("Insert Tile", mainButtonColor);
        viewCardButton = createButton("View Card", mainButtonColor);

        upButton = createButton("^", moveButtonColor);
        downButton = createButton("v", moveButtonColor);
        leftButton = createButton("<", moveButtonColor);
        rightButton = createButton(">", moveButtonColor);

        // Create and add action buttons panel
        JPanel actionButtonsPanel = createActionButtonsPanel(uiPrototype);
        add(actionButtonsPanel);
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(buttonTextColor);
        return button;
    }

    private JToggleButton createToggleButton(String text, Color backgroundColor) {
        JToggleButton toggleButton = new JToggleButton(text);
        toggleButton.setBackground(backgroundColor);
        toggleButton.setForeground(buttonTextColor);
        return toggleButton;
    }

    private JPanel createActionButtonsPanel(UIPrototype uiPrototype) {
        JPanel actionButtonsPanel = new JPanel(new GridLayout(1, 1));
        actionButtonsPanel.setBackground(Color.decode("#803925"));

        // Create panels for buttons
        JPanel buttonsPanel = createButtonsPanel(uiPrototype);
        JPanel moveButtonsPanel = createMoveButtonsPanel(uiPrototype);

        // Add panels to split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPanel, moveButtonsPanel);
        splitPane.setDividerLocation(0.6);
        splitPane.setResizeWeight(0.6);

        actionButtonsPanel.add(splitPane);
        return actionButtonsPanel;
    }

    private JPanel createButtonsPanel(UIPrototype uiPrototype) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.decode("#803925"));

        Dimension buttonSize = new Dimension(uiPrototype.getActionButtonDim()[0], uiPrototype.getActionButtonDim()[1]);
        setButtonSize(endTurnButton, buttonSize);
        setButtonSize(insertTileButton, buttonSize);
        setButtonSize(viewCardButton, buttonSize);
        setToggleButtonSize(useWandButton, buttonSize);

        buttonsPanel.add(endTurnButton);
        buttonsPanel.add(useWandButton);
        buttonsPanel.add(insertTileButton);
        buttonsPanel.add(viewCardButton);

        return buttonsPanel;
    }

    private JPanel createMoveButtonsPanel(UIPrototype uiPrototype) {
        JPanel moveButtonsPanel = new JPanel();
        moveButtonsPanel.setLayout(new BoxLayout(moveButtonsPanel, BoxLayout.Y_AXIS));
        moveButtonsPanel.setBackground(Color.decode("#803925"));

        Dimension buttonSize = new Dimension(uiPrototype.getActionButtonDim()[0], uiPrototype.getActionButtonDim()[1]);
        setButtonSize(upButton, buttonSize);
        setButtonSize(downButton, buttonSize);
        setButtonSize(leftButton, buttonSize);
        setButtonSize(rightButton, buttonSize);

        moveButtonsPanel.add(upButton);
        moveButtonsPanel.add(downButton);
        moveButtonsPanel.add(leftButton);
        moveButtonsPanel.add(rightButton);

        return moveButtonsPanel;
    }

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

    public void updatePanel() {
        SwingUtilities.invokeLater(() -> {
            endTurnButton.setBackground(mainButtonColor);
            useWandButton.setBackground(mainButtonColor);
            insertTileButton.setBackground(mainButtonColor);
            viewCardButton.setBackground(mainButtonColor);

            upButton.setBackground(moveButtonColor);
            downButton.setBackground(moveButtonColor);
            leftButton.setBackground(moveButtonColor);
            rightButton.setBackground(moveButtonColor);

            endTurnButton.setForeground(buttonTextColor);
            useWandButton.setForeground(buttonTextColor);
            insertTileButton.setForeground(buttonTextColor);
            viewCardButton.setForeground(buttonTextColor);

            upButton.setForeground(buttonTextColor);
            downButton.setForeground(buttonTextColor);
            leftButton.setForeground(buttonTextColor);
            rightButton.setForeground(buttonTextColor);

            revalidate();
            repaint();
        });
    }

    // Getter methods for buttons
    public JButton getEndTurnButton() { return endTurnButton; }
    public JToggleButton getUseWandButton() { return useWandButton; }
    public JButton getInsertTileButton() { return insertTileButton; }
    public JButton getViewCardButton() { return viewCardButton; }
    public JButton getUpButton() { return upButton; }
    public JButton getDownButton() { return downButton; }
    public JButton getLeftButton() { return leftButton; }
    public JButton getRightButton() { return rightButton; }
    
    // Setter methods for button colors
    public void setMainButtonColor(Color mainButtonColor) { this.mainButtonColor = mainButtonColor; updatePanel(); }
    public void setMoveButtonColor(Color moveButtonColor) { this.moveButtonColor = moveButtonColor; updatePanel(); }
    public void setButtonTextColor(Color buttonTextColor) { this.buttonTextColor = buttonTextColor; updatePanel(); }

    // Add action listener to specific button based on button name
    public void addButtonListener(String buttonName, ActionListener listener) {
        switch (buttonName) {
            case "endTurn": endTurnButton.addActionListener(listener); break;
            case "useWand": useWandButton.addActionListener(listener); break;
            case "insertTile": insertTileButton.addActionListener(listener); break;
            case "viewCard": viewCardButton.addActionListener(listener); break;
            case "up": upButton.addActionListener(listener); break;
            case "down": downButton.addActionListener(listener); break;
            case "left": leftButton.addActionListener(listener); break;
            case "right": rightButton.addActionListener(listener); break;
            default: break;
        }
    }
}
