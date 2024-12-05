package view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class SplashIntro {

    private JFrame frame;
    private int gameMode = -1;  // -1: No selection, 0: Local, 1: Network
    private int userRoleInNetworkMode = -1;  // -1: No selection, 0: Host, 1: Join
    private int numberOfPlayers = -1;  // Number of players in the game
    private CountDownLatch latch;

    public SplashIntro(CountDownLatch latch) {
        this.latch = latch;
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeMessage = new JLabel("Welcome to Master Labyrinth!", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeMessage.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel logo = new JLabel(new ImageIcon("C:/CST8221_300/LAB_CST8221/MazeGame/Assets/splash.png")); // Replace with actual logo path
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        welcomePanel.add(welcomeMessage, BorderLayout.NORTH);
        welcomePanel.add(logo, BorderLayout.CENTER);

        frame.add(welcomePanel);
        frame.setVisible(true);

        Timer timer = new Timer(3000, e -> {
            frame.dispose();
            showGameModeSelectionScreen();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void showGameModeSelectionScreen() {
        frame = new JFrame("Game Mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel modePanel = new JPanel();
        modePanel.setLayout(new GridLayout(3, 1, 10, 10));
        modePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instruction = new JLabel("Select Game Mode:", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton localButton = new JButton("Play Locally");
        JButton networkButton = new JButton("Play Over Network");

        localButton.addActionListener(e -> {
            gameMode = 0; // Local Game
            frame.dispose();
            showNumberOfPlayersScreen();
        });

        networkButton.addActionListener(e -> {
            gameMode = 1; // Network Game
            frame.dispose();
            showNetworkModeScreen();
        });

        modePanel.add(instruction);
        modePanel.add(localButton);
        modePanel.add(networkButton);

        frame.add(modePanel);
        frame.setVisible(true);
    }

    private void showNetworkModeScreen() {
        frame = new JFrame("Network Mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel userNetworkRolePanel = new JPanel();
        userNetworkRolePanel.setLayout(new GridLayout(3, 1, 10, 10));
        userNetworkRolePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instruction = new JLabel("Do you want to Host or Join a game?", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton hostButton = new JButton("Host");
        JButton joinButton = new JButton("Join");

        hostButton.addActionListener(e -> {
            userRoleInNetworkMode = 0; // Host
            frame.dispose();
            showNumberOfPlayersScreen();
        });

        joinButton.addActionListener(e -> {
            userRoleInNetworkMode = 1; // Join
            frame.dispose();
            latch.countDown(); // Release the latch when done
        });

        userNetworkRolePanel.add(instruction);
        userNetworkRolePanel.add(hostButton);
        userNetworkRolePanel.add(joinButton);

        frame.add(userNetworkRolePanel);
        frame.setVisible(true);
    }

    private void showNumberOfPlayersScreen() {

        // Create the frame
        frame = new JFrame("Number of Players");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the frame on screen

        // Panel to hold the content
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows for instruction, text field, and button
        playerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Instruction label
        JLabel instruction = new JLabel("Enter number of players:", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18)); // Make font size readable

        // Text field for input
        JTextField playersField = new JTextField();
        playersField.setHorizontalAlignment(JTextField.CENTER);
        playersField.setFont(new Font("Arial", Font.PLAIN, 16)); // A slightly larger font

        // Confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Same font size for consistency
        confirmButton.addActionListener(e -> {
            try {
                numberOfPlayers = Integer.parseInt(playersField.getText());
                frame.dispose(); // Close the frame
                latch.countDown(); // Release the latch when done
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to the panel
        playerPanel.add(instruction);
        playerPanel.add(playersField);
        playerPanel.add(confirmButton);

        // Add the panel to the frame and make the frame visible
        frame.add(playerPanel);
        frame.setVisible(true);
    }

    // Getters
    public int getGameMode() {
        return gameMode;
    }

    public int getUserRoleInNetworkMode() {
        return userRoleInNetworkMode;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
