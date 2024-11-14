package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JMenuBar menuBar;
    
    private final JMenu fileMenu;
    private final JMenu gameMenu;
    private final JMenu languageMenu;
    private final JMenu helpMenu;
    private final JMenu networkMenu;
    
    // Lists to hold dynamic menu items and submenus
    private final List<JMenuItem> fileMenuItems = new ArrayList<>();
    private final List<JMenuItem> gameMenuItems = new ArrayList<>();
    private final List<JMenuItem> languageMenuItems = new ArrayList<>();
    private final List<JMenu> fileSubMenus = new ArrayList<>();
    private final List<JMenu> gameSubMenus = new ArrayList<>();
    private final List<JMenu> languageSubMenus = new ArrayList<>();

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

    private void setupMenuBar() {
        fileMenuItems.add(new JMenuItem("Load"));
        fileMenuItems.add(new JMenuItem("Save"));
        
        gameMenuItems.add(new JMenuItem("Restart"));
        gameMenuItems.add(new JMenuItem("About"));
        
        languageMenuItems.add(new JMenuItem("English"));
        languageMenuItems.add(new JMenuItem("Vietnamese"));
        
        // Populate menus with initial items if any exist in the lists
        populateMenu(fileMenu, fileMenuItems, fileSubMenus);
        populateMenu(gameMenu, gameMenuItems, gameSubMenus);
        populateMenu(languageMenu, languageMenuItems, languageSubMenus);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(languageMenu);
        menuBar.add(helpMenu);
        menuBar.add(networkMenu);
    }

    private void populateMenu(JMenu menu, List<JMenuItem> menuItems, List<JMenu> subMenus) {
        // Add all the items (JMenuItems) to the menu
        for (JMenuItem item : menuItems) {
            menu.add(item);
        }

        // Add all the submenus (JMenus) to the menu
        for (JMenu subMenu : subMenus) {
            menu.add(subMenu);
        }
    }

    // Methods to dynamically add items and submenus to specific menus
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

    // Methods to add submenus to specific menus
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

    // Getter for the menu bar
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    // Getters for each menu item list
    public List<JMenuItem> getFileMenuItems() { return fileMenuItems; }
    public List<JMenuItem> getGameMenuItems() { return gameMenuItems; }
    public List<JMenuItem> getLanguageMenuItems() { return languageMenuItems; }

    // Getters for each submenu list
    public List<JMenu> getFileSubMenus() { return fileSubMenus; }
    public List<JMenu> getGameSubMenus() { return gameSubMenus; }
    public List<JMenu> getLanguageSubMenus() { return languageSubMenus; }
}
