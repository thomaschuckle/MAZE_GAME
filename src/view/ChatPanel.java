package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ChatPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating chat panel
    public JPanel createChatPanel(UIPrototype uiPrototype) {
    	JPanel chatPanel = new JPanel(new BorderLayout());

    	// Create the titled border with a white title
    	TitledBorder chatBorder = BorderFactory.createTitledBorder("Chat");
    	chatBorder.setTitleColor(Color.decode("#fc019c")); // Set title color

    	// Set the border to the chatPanel
    	chatPanel.setBorder(chatBorder);
    	chatPanel.setPreferredSize(new Dimension(uiPrototype.getChatDim()[0], uiPrototype.getChatDim()[1]));
    	chatPanel.setBackground(Color.decode("#014342")); // Set the background color

    	// Create the chat area
    	JTextArea chatArea = new JTextArea(5, 20);
    	chatArea.setEditable(false);
    	chatArea.setBackground(Color.decode("#014342")); // Set chat area background color
    	chatArea.setForeground(Color.decode("#fc019c")); // Set text color

    	// Create the scroll pane for chat area
    	JScrollPane chatScrollPane = new JScrollPane(chatArea);
    	chatScrollPane.setBackground(Color.decode("#013743")); // Set background color of scroll pane
    	chatPanel.add(chatScrollPane, BorderLayout.CENTER);

    	// Create the input field for chat
    	JTextField chatInputField = new JTextField();
    	chatInputField.setBackground(Color.decode("#014342")); // Set input field background color
    	chatInputField.setForeground(Color.decode("#fc019c")); // Set text color
    	// Create the send button and set background color
    	JButton sendButton = new JButton("Send");
    	sendButton.setBackground(Color.decode("#4CAF50")); // Set button background color
    	sendButton.setForeground(Color.decode("#FFFFFF")); // Set button text color 

    	// Create a panel for input and set background color
    	JPanel inputPanel = new JPanel(new BorderLayout());
    	inputPanel.setBackground(Color.decode("#013743")); // Set input panel background color
    	inputPanel.add(chatInputField, BorderLayout.CENTER);
    	inputPanel.add(sendButton, BorderLayout.EAST);

    	// Add the input panel to the chat panel
    	chatPanel.add(inputPanel, BorderLayout.SOUTH);

    	return chatPanel;
    }
}
