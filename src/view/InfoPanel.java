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
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating user info panel
    public JPanel createUserInfoPanel(UIPrototype uiPrototype) {
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
    
    // Helper method for setting button size
    private static void setButtonSize(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
    }
}
