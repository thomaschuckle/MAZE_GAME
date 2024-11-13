package model;

import java.util.Deque;
import java.util.ArrayDeque;

public class Chat {
    
    private Deque<String> messages;                       // Deque to store chat messages
    private static final int MAX_CHAT_HISTORY = 250;      // Max limit for chat message history

    // Constructors ---------------------------------------------------------------------------------

    /**
     * Initializes the Chat object with an empty message deque.
     */
    public Chat() {
        this.messages = new ArrayDeque<>();
    }
    
    // Chat Management ------------------------------------------------------------------------------

    /**
     * Adds a new message to the chat. If the number of messages exceeds MAX_CHAT_HISTORY,
     * the oldest message is removed to maintain the limit.
     *
     * @param message The message to add to the chat history.
     */
    public void addMessage(String message) {
        if (messages.size() >= MAX_CHAT_HISTORY) {
            messages.pollFirst();  						// Remove the oldest message if history exceeds limit
        }
        messages.addLast(message);  					// Add the new message to the end of the deque
    }
    
    /**
     * Retrieves the entire chat history.
     *
     * @return The deque containing all chat messages.
     */
    public Deque<String> getChatHistory() {
        return messages;
    }
    
    /**
     * Retrieves the most recent message in the chat.
     *
     * @return The last message added to the chat, or null if chat is empty.
     */
    public String getLastMessage() {
        return messages.peekLast();
    }
    
    /**
     * Retrieves the current number of messages in the chat.
     *
     * @return The count of messages stored in the chat.
     */
    public int getMessageCount() {
        return messages.size();
    }

    /**
     * Clears all messages from the chat history.
     */
    public void clearChat() {
        messages.clear();
    }
}
