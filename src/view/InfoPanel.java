package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    // Fields to dynamically store player info and wands
    private String[] playerUsernames = {"P1", "P2", "P3", "P4"};
    private int[] playerScores = {0, 0, 0, 0};
    private String[] playerWands = {"////", "////", "////", "////"};
    private String currentPlayerTurn = "P1";
    private String currentInstruction = "Click arrow in maze to insert block";
    private String currentBlock = "Current Block: ";

    // Labels that need to be updated dynamically
    private JLabel userTurnLabel;
    private JLabel instructionLabel;
    private JLabel[] playerInfoLabels;
    private JLabel currentBlockLabel;

    // Button color fields with default colors
    private Color leftButtonColor = Color.decode("#0b6e89");  // Default color for the left button
    private Color rightButtonColor = Color.decode("#0b6e89"); // Default color for the right button
    private Color buttonTextColor = Color.WHITE;              // Default text color for buttons

    // Constructor to initialize and build the panel, accepting uiPrototype
    public InfoPanel(UIPrototype uiPrototype) {
        // Initialize player info labels
        playerInfoLabels = new JLabel[playerUsernames.length];

        // Set up the panel layout
        setLayout(new BorderLayout());

        // Create and set up the user info panel
        JPanel userInfoPanel = new JPanel(new BorderLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("User Info");
        titledBorder.setTitleColor(Color.decode("#d7bcac"));
        userInfoPanel.setBorder(titledBorder);
        userInfoPanel.setPreferredSize(new Dimension(uiPrototype.getPInfoDim()[0], uiPrototype.getPInfoDim()[1]));
        userInfoPanel.setBackground(Color.decode("#013743"));

        // Panel for the player info labels
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBackground(Color.decode("#013743"));

        // Add the user turn label
        userTurnLabel = new JLabel(currentPlayerTurn + "'s TURN");
        userTurnLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userTurnLabel.setForeground(Color.decode("#fb7f05"));
        labelsPanel.add(userTurnLabel);

        // Add the instruction label
        instructionLabel = new JLabel(currentInstruction);
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionLabel.setForeground(Color.decode("#fb7f05"));
        labelsPanel.add(instructionLabel);

        // Add vertical space
        labelsPanel.add(Box.createVerticalStrut(10));

        // Add player info labels
        for (int i = 0; i < playerUsernames.length; i++) {
            playerInfoLabels[i] = new JLabel(playerUsernames[i] + ": " + playerScores[i] + " | " + playerWands[i]);
            playerInfoLabels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            playerInfoLabels[i].setForeground(Color.decode("#f62002"));
            labelsPanel.add(playerInfoLabels[i]);
        }

        // Add vertical space
        labelsPanel.add(Box.createVerticalStrut(10));

        // Add current position label
        JLabel currentPosLabel = new JLabel("Current position to capture: ");
        currentPosLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentPosLabel.setForeground(Color.decode("#f62002"));
        labelsPanel.add(currentPosLabel);

        // Add space
        labelsPanel.add(Box.createVerticalStrut(40));

        // Panel for current block info and buttons
        JPanel labelsPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelsPanel2.setBackground(Color.decode("#013743"));

        // Add current block label
        currentBlockLabel = new JLabel(currentBlock);
        currentBlockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentBlockLabel.setForeground(Color.decode("#ff7f00"));
        labelsPanel2.add(currentBlockLabel);

        // Add a dummy panel for the block visualization (could be replaced later)
        JPanel currentBlockPanel = new JPanel();
        currentBlockPanel.setPreferredSize(new Dimension(60, 60));
        currentBlockPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        labelsPanel2.add(currentBlockPanel);

        // Add space between buttons
        labelsPanel2.add(Box.createHorizontalStrut(1));

        // Set up button size and add left button
        Dimension buttonSize = new Dimension(45, 20);
        JButton leftTurnButton = new JButton("<-");
        setButtonSize(leftTurnButton, buttonSize);
        leftTurnButton.setBackground(leftButtonColor);
        leftTurnButton.setForeground(buttonTextColor);
        labelsPanel2.add(leftTurnButton);

        // Add right button
        JButton rightTurnButton = new JButton("->");
        setButtonSize(rightTurnButton, buttonSize);
        rightTurnButton.setBackground(rightButtonColor);
        rightTurnButton.setForeground(buttonTextColor);
        labelsPanel2.add(rightTurnButton);

        // Add the panels to the user info panel
        userInfoPanel.add(labelsPanel, BorderLayout.NORTH);
        userInfoPanel.add(labelsPanel2, BorderLayout.CENTER);

        // Add the user info panel to the main panel
        add(userInfoPanel, BorderLayout.CENTER);

        // Add space to the bottom of the panel
        labelsPanel.add(Box.createVerticalGlue());
    }

    // Method to update the panel whenever fields are changed
    public void updatePanel() {
        SwingUtilities.invokeLater(() -> {
            userTurnLabel.setText(currentPlayerTurn + "'s TURN");
            instructionLabel.setText(currentInstruction);

            for (int i = 0; i < playerUsernames.length; i++) {
                playerInfoLabels[i].setText(playerUsernames[i] + ": " + playerScores[i] + " | " + playerWands[i]);
            }

            currentBlockLabel.setText(currentBlock);
            revalidate();
            repaint();
        });
    }

    // Getters and setters for fields
    public String[] getPlayerUsernames() { return playerUsernames; }
    public void setPlayerUsernames(String[] playerUsernames) { this.playerUsernames = playerUsernames; updatePanel(); }
    public int[] getPlayerScores() { return playerScores; }
    public void setPlayerScores(int[] playerScores) { this.playerScores = playerScores; updatePanel(); }
    public String[] getPlayerWands() { return playerWands; }
    public void setPlayerWands(String[] playerWands) { this.playerWands = playerWands; updatePanel(); }
    public String getCurrentPlayerTurn() { return currentPlayerTurn; }
    public void setCurrentPlayerTurn(String currentPlayerTurn) { this.currentPlayerTurn = currentPlayerTurn; updatePanel(); }
    public String getCurrentInstruction() { return currentInstruction; }
    public void setCurrentInstruction(String currentInstruction) { this.currentInstruction = currentInstruction; updatePanel(); }
    public String getCurrentBlock() { return currentBlock; }
    public void setCurrentBlock(String currentBlock) { this.currentBlock = currentBlock; updatePanel(); }

    // Getters and setters for button colors
    public Color getLeftButtonColor() { return leftButtonColor; }
    public void setLeftButtonColor(Color leftButtonColor) { this.leftButtonColor = leftButtonColor; updatePanel(); }

    public Color getRightButtonColor() { return rightButtonColor; }
    public void setRightButtonColor(Color rightButtonColor) { this.rightButtonColor = rightButtonColor; updatePanel(); }

    public Color getButtonTextColor() { return buttonTextColor; }
    public void setButtonTextColor(Color buttonTextColor) { this.buttonTextColor = buttonTextColor; updatePanel(); }

    private static void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }
}
