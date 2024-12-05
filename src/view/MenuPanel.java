package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JMenuBar menuBar;

    private final JMenu fileMenu;
    private final JMenu gameMenu;
    private final JMenu languageMenu;
    private final JMenu helpMenu;
    private final JMenu networkMenu;

    private final List<JMenuItem> fileMenuItems = new ArrayList<>();
    private final List<JMenuItem> gameMenuItems = new ArrayList<>();
    private final List<JMenuItem> languageMenuItems = new ArrayList<>();
    private final List<JMenuItem> networkMenuItems = new ArrayList<>();
    private final List<JMenu> fileSubMenus = new ArrayList<>();
    private final List<JMenu> gameSubMenus = new ArrayList<>();
    private final List<JMenu> languageSubMenus = new ArrayList<>();

    // Remove the reference to NetworkInfoPanel from here
    private NetworkInfoFrame networkInfoFrame;  // This will be set later through setter

    public MenuPanel() {
        // Initialize JMenuBar and JMenu components
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        gameMenu = new JMenu("Game");
        languageMenu = new JMenu("Language");
        helpMenu = new JMenu("Help");
        networkMenu = new JMenu("Network");

        // Set up the menu bar
        setupMenuBar();
    }

    public void setNetworkInfoFrame(NetworkInfoFrame networkInfoFrame) {
        this.networkInfoFrame = networkInfoFrame;  // Set NetworkInfoFrame dynamically
    }

    private void setupMenuBar() {
        fileMenuItems.add(new JMenuItem("Load"));
        fileMenuItems.add(new JMenuItem("Save"));

        gameMenuItems.add(new JMenuItem("Restart"));
        gameMenuItems.add(new JMenuItem("About"));

        languageMenuItems.add(new JMenuItem("English"));
        languageMenuItems.add(new JMenuItem("Vietnamese"));

        // Add "Host," "Join," and "Leave" to the Network menu
        JMenuItem hostMenuItem = new JMenuItem("Host");
        JMenuItem joinMenuItem = new JMenuItem("Join");
        JMenuItem leaveMenuItem = new JMenuItem("Leave");

        networkMenuItems.add(hostMenuItem);
        networkMenuItems.add(joinMenuItem);
        networkMenuItems.add(leaveMenuItem);

        // Add action listeners for network menu items
        hostMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                networkInfoFrame = new NetworkInfoFrame();
                if (networkInfoFrame != null) {
                    networkInfoFrame.showHostScreen();  // Show the host screen
                }
            }
        });

        joinMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                networkInfoFrame = new NetworkInfoFrame();
                if (networkInfoFrame != null) {
                    networkInfoFrame.showJoinScreen();  // Show the join screen
                }
            }
        });

        leaveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (networkInfoFrame != null) {
                    networkInfoFrame.updateNetworkStatus("Left the network.");
                }
                JOptionPane.showMessageDialog(null, "You have left the network.");
            }
        });

        // Populate menus with initial items
        populateMenu(fileMenu, fileMenuItems, fileSubMenus);
        populateMenu(gameMenu, gameMenuItems, gameSubMenus);
        populateMenu(languageMenu, languageMenuItems, languageSubMenus);
        populateMenu(networkMenu, networkMenuItems, null);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(languageMenu);
        menuBar.add(helpMenu);
        menuBar.add(networkMenu);
    }

    private void populateMenu(JMenu menu, List<JMenuItem> menuItems, List<JMenu> subMenus) {
        for (JMenuItem item : menuItems) {
            menu.add(item);
        }

        if (subMenus != null) {
            for (JMenu subMenu : subMenus) {
                menu.add(subMenu);
            }
        }
    }

    // Dynamic menu item addition methods
    public void addFileMenuItem(JMenuItem item) {
        fileMenuItems.add(item);
        fileMenu.add(item);
    }

    public void addGameMenuItem(JMenuItem item) {
        gameMenuItems.add(item);
        gameMenu.add(item);
    }

    public void addLanguageMenuItem(JMenuItem item) {
        languageMenuItems.add(item);
        languageMenu.add(item);
    }

    public void addNetworkMenuItem(JMenuItem item) {
        networkMenuItems.add(item);
        networkMenu.add(item);
    }

    public void addFileSubMenu(JMenu subMenu) {
        fileSubMenus.add(subMenu);
        fileMenu.add(subMenu);
    }

    public void addGameSubMenu(JMenu subMenu) {
        gameSubMenus.add(subMenu);
        gameMenu.add(subMenu);
    }

    public void addLanguageSubMenu(JMenu subMenu) {
        languageSubMenus.add(subMenu);
        languageMenu.add(subMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public List<JMenuItem> getFileMenuItems() { return fileMenuItems; }
    public List<JMenuItem> getGameMenuItems() { return gameMenuItems; }
    public List<JMenuItem> getLanguageMenuItems() { return languageMenuItems; }
    public List<JMenuItem> getNetworkMenuItems() { return networkMenuItems; }
    public List<JMenu> getFileSubMenus() { return fileSubMenus; }
    public List<JMenu> getGameSubMenus() { return gameSubMenus; }
    public List<JMenu> getLanguageSubMenus() { return languageSubMenus; }
}
