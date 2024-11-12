package model;

import java.util.Deque;
import java.util.ArrayDeque;

public class Chat {
    
    private Deque<String> messages;
    
    // Maximum number of chat messages to store
    private static final int MAX_CHAT_HISTORY = 250;

    // Constructor
    public Chat() {
        this.messages = new ArrayDeque<>();
    }
    
    // Add a new message to the chat
    public void addMessage(String message) {
        if (messages.size() >= MAX_CHAT_HISTORY) {
            messages.pollFirst();  // Remove the oldest message if the history exceeds MAX_CHAT_HISTORY
        }
        messages.addLast(message);  // Add the new message to the end of the deque
    }
    
    // Get the entire chat history as an array
    public Deque<String> getChatHistory() {
        return messages;
    }
    
    // Get the most recent message
    public String getLastMessage() {
        return messages.peekLast();
    }
    
    // Get the number of messages in the chat
    public int getMessageCount() {
        return messages.size();
    }

    // Clear the chat history
    public void clearChat() {
        messages.clear();
    }
}

