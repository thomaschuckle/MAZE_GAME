package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // Fields for the components
    private CardPanel cardPanel;
    private ChatPanel chatPanel;
    private ControlPanel controlPanel;
    private InfoPanel userInfoPanel;
    private MazePanel mazePanel;
    private MenuPanel menuPanel;
    private UIPrototype uiPrototype;

    // Constructor to initialize the frame
    public MainFrame(CardPanel cardPanel, ChatPanel chatPanel, ControlPanel controlPanel,
                     InfoPanel userInfoPanel, MazePanel mazePanel, MenuPanel menuPanel, UIPrototype uiPrototype) {
        
        this.cardPanel = cardPanel;
        this.chatPanel = chatPanel;
        this.controlPanel = controlPanel;
        this.userInfoPanel = userInfoPanel;
        this.mazePanel = mazePanel;
        this.menuPanel = menuPanel;
        this.uiPrototype = uiPrototype;
        
        // Initialize main frame
        setTitle("Maze Game UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(uiPrototype.getMaxDim()[0], uiPrototype.getMaxDim()[1]));

        // Set icon
        Image iconImage = Toolkit.getDefaultToolkit().getImage("Assets/blue.png");
        setIconImage(iconImage);
        
        // Set up menu bar (using the MenuPanel’s static method)
        setJMenuBar(menuPanel.getMenuBar());

        // Create a split pane layout (15/85 proportion)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.15);  // Allocate 15% for the left panel
        splitPane.setEnabled(false);      // Disable manual resizing

        // Combine user info, chat, and control panels in a left-side panel
        JPanel leftPanel = createLeftPanel(userInfoPanel, chatPanel, controlPanel, uiPrototype);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(new MazePanel(uiPrototype));

        // Add split pane to frame
        add(splitPane, BorderLayout.CENTER);
        
        // Set up and display the frame
        pack();               			// Adjust frame size based on components' preferred sizes
        setLocationRelativeTo(null); 	// Center on screen
    }

    // Combine the User Info, Chat, and Control panels into the left side
    private JPanel createLeftPanel(InfoPanel userInfoPanel, ChatPanel chatPanel, ControlPanel controlPanel, UIPrototype uiPrototype) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Add panels to the left panel
        leftPanel.add(userInfoPanel);
        leftPanel.add(chatPanel);
        leftPanel.add(controlPanel);
        return leftPanel;
    }

    // Getters and Setters
    public CardPanel getCardPanel() {
        return cardPanel;
    }

    public void setCardPanel(CardPanel cardPanel) {
        this.cardPanel = cardPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public InfoPanel getUserInfoPanel() {
        return userInfoPanel;
    }

    public void setUserInfoPanel(InfoPanel userInfoPanel) {
        this.userInfoPanel = userInfoPanel;
    }

    public MazePanel getMazePanel() {
        return mazePanel;
    }

    public void setMazePanel(MazePanel mazePanel) {
        this.mazePanel = mazePanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public UIPrototype getUiPrototype() {
        return uiPrototype;
    }

    public void setUiPrototype(UIPrototype uiPrototype) {
        this.uiPrototype = uiPrototype;
    }
}
