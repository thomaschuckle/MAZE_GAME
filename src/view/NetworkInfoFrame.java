package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkInfoFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField serverAddressField;
    private JTextField portField;
    private int port;
    private String serverAddress;
    private boolean isHost;

    public NetworkInfoFrame() {
        setTitle("Network Info");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame instead of exiting the application
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void showHostScreen() {
        isHost = true; // This will be for hosting the game

        JPanel hostPanel = new JPanel();
        hostPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel instruction = new JLabel("Host a Multiplayer Game", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));

        // Server address and port input fields
        JPanel addressPanel = new JPanel(new FlowLayout());
        addressPanel.add(new JLabel("Server Address:"));
        serverAddressField = new JTextField("localhost", 15); // Default to "localhost"
        addressPanel.add(serverAddressField);

        JPanel portPanel = new JPanel(new FlowLayout());
        portPanel.add(new JLabel("Port:"));
        portField = new JTextField("12345", 5); // Default port
        portPanel.add(portField);

        JButton startButton = new JButton("Start Hosting");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setServerAddress(serverAddressField.getText());
                setPort(Integer.parseInt(portField.getText()));

                // Logic to start hosting the game (e.g., start server thread)
                JOptionPane.showMessageDialog(NetworkInfoFrame.this, "Hosting Game...");
                startHostingGame();  // Example method to start the host game
                dispose();  // Close the frame after hosting the game
            }
        });

        hostPanel.add(instruction);
        hostPanel.add(addressPanel);
        hostPanel.add(portPanel);
        hostPanel.add(startButton);

        add(hostPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void showJoinScreen() {
        isHost = false; // This will be for joining a game

        JPanel joinPanel = new JPanel();
        joinPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel instruction = new JLabel("Join a Multiplayer Game", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));

        // Server address and port input fields
        JPanel addressPanel = new JPanel(new FlowLayout());
        addressPanel.add(new JLabel("Server Address:"));
        serverAddressField = new JTextField("localhost", 15); // Default to "localhost"
        addressPanel.add(serverAddressField);

        JPanel portPanel = new JPanel(new FlowLayout());
        portPanel.add(new JLabel("Port:"));
        portField = new JTextField("12345", 5); // Default port
        portPanel.add(portField);

        JButton joinButton = new JButton("Join Game");
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setServerAddress(serverAddressField.getText());
                setPort(Integer.parseInt(portField.getText()));

                // Logic to join the game (connect to the server)
                JOptionPane.showMessageDialog(NetworkInfoFrame.this, "Joining Game...");
                joinGame();  // Example method to join the game
                dispose();  // Close the frame after joining the game
            }
        });

        joinPanel.add(instruction);
        joinPanel.add(addressPanel);
        joinPanel.add(portPanel);
        joinPanel.add(joinButton);

        add(joinPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Getters and Setters
    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isHost() {
        return isHost;
    }

    private void startHostingGame() {
        // Logic to start hosting the game (e.g., start a server on the specified address and port)
        // For example: start a server socket that listens on the provided port
        System.out.println("Hosting game on " + serverAddress + ":" + port);
        // Here you would have networking code to host the game (server-side code)
    }

    private void joinGame() {
        // Logic to join the game (connect to the specified server address and port)
        // For example: connect to a server using the provided address and port
        System.out.println("Joining game at " + serverAddress + ":" + port);
        // Here you would have networking code to connect to a server (client-side code)
    }

    public void updateNetworkStatus(String status) {
        // This can be used to update the status of the network (e.g., connection status)
        JOptionPane.showMessageDialog(this, status);
    }
}
