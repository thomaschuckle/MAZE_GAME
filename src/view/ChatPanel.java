package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ChatPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTextArea chatArea;
    private final JTextField chatInputField;
    private final JButton sendButton;

    // Constructor that accepts UIPrototype for setting dimensions
    public ChatPanel(UIPrototype uiPrototype) {
        // Panel layout and styling
        setLayout(new BorderLayout());
        setBackground(Color.decode("#014342")); // Set the background color
        
        // Create and set titled border with customized title color
        TitledBorder chatBorder = BorderFactory.createTitledBorder("Chat");
        chatBorder.setTitleColor(Color.decode("#fc019c")); 
        setBorder(chatBorder);

        // Set preferred dimensions from UIPrototype
        setPreferredSize(new Dimension(uiPrototype.getChatDim()[0], uiPrototype.getChatDim()[1]));

        // Initialize chat area
        chatArea = new JTextArea(5, 20);
        chatArea.setEditable(false);
        chatArea.setBackground(Color.decode("#014342")); 
        chatArea.setForeground(Color.decode("#fc019c")); 

        // Wrap chat area in a scroll pane and add to center
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBackground(Color.decode("#013743")); 
        add(chatScrollPane, BorderLayout.CENTER);

        // Initialize chat input field with styling
        chatInputField = new JTextField();
        chatInputField.setBackground(Color.decode("#014342")); 
        chatInputField.setForeground(Color.decode("#fc019c")); 

        // Initialize send button with styling
        sendButton = new JButton("Send");
        sendButton.setBackground(Color.decode("#4CAF50")); 
        sendButton.setForeground(Color.decode("#FFFFFF")); 

        // Setup input panel, include input field and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.decode("#013743")); 
        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add the input panel to the bottom of the chat panel
        add(inputPanel, BorderLayout.SOUTH);
    }

    // Update the chat area with new messages
    public void updateChatArea(String message) {
        chatArea.append(message + "\n");
    }

    // Retrieve the text from the input field
    public String getChatInput() {
        return chatInputField.getText();
    }

    // Clear the input field
    public void clearInputField() {
        chatInputField.setText("");
    }

    // Attach an action listener to the send button
    public void addSendButtonListener(ActionListener listener) {
        sendButton.addActionListener(listener);
    }
}
