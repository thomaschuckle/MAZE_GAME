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

    private JTextArea chatArea;
    private JTextField chatInputField;

    // Constructor that accepts UIPrototype for setting dimensions
    public ChatPanel(UIPrototype uiPrototype) {
        // Initialize components
        setLayout(new BorderLayout());

        // Create the titled border with a white title
        TitledBorder chatBorder = BorderFactory.createTitledBorder("Chat");
        chatBorder.setTitleColor(Color.decode("#fc019c")); // Set title color
        setBorder(chatBorder);
        setBackground(Color.decode("#014342")); // Set the background color

        // Use UIPrototype to set the dimensions of the chat panel
        setPreferredSize(new Dimension(uiPrototype.getChatDim()[0], uiPrototype.getChatDim()[1]));

        // Create the chat area
        chatArea = new JTextArea(5, 20);
        chatArea.setEditable(false);
        chatArea.setBackground(Color.decode("#014342")); // Set chat area background color
        chatArea.setForeground(Color.decode("#fc019c")); // Set text color

        // Create the scroll pane for chat area
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBackground(Color.decode("#013743")); // Set background color of scroll pane
        add(chatScrollPane, BorderLayout.CENTER);

        // Create the input field for chat
        chatInputField = new JTextField();
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
        add(inputPanel, BorderLayout.SOUTH);
    }

    // Update the chat area with the latest messages
    public void updateChatArea(String message) {
        chatArea.append(message + "\n");
    }

    // Get the text from the input field
    public String getChatInput() {
        return chatInputField.getText();
    }

    // Clear the input field
    public void clearInputField() {
        chatInputField.setText("");
    }

    // Add an action listener to the send button
    public void addSendButtonListener(ActionListener listener) {
        // Get the send button from the panel and add the listener
        JButton sendButton = (JButton) ((JPanel) getComponent(1)).getComponent(1);
        sendButton.addActionListener(listener);
    }
}
