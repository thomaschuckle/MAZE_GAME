package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    // Fields to store dynamic menu items
    private List<JMenuItem> fileMenuItems = new ArrayList<>();
    private List<JMenuItem> gameMenuItems = new ArrayList<>();
    private List<JMenuItem> languageMenuItems = new ArrayList<>();

    // Creating menu bar
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create and populate file menu
        JMenu fileMenu = new JMenu("File");
        for (JMenuItem item : fileMenuItems) {
            fileMenu.add(item);
        }

        // Create and populate game menu
        JMenu gameMenu = new JMenu("Game");
        for (JMenuItem item : gameMenuItems) {
            gameMenu.add(item);
        }

        // Create and populate language menu
        JMenu languageMenu = new JMenu("Language");
        for (JMenuItem item : languageMenuItems) {
            languageMenu.add(item);
        }

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(languageMenu);
        menuBar.add(new JMenu("Help"));
        menuBar.add(new JMenu("Network"));

        return menuBar;
    }

    // Methods to add items to different menus
    public void addFileMenuItem(JMenuItem item) {
        fileMenuItems.add(item);
    }

    public void addGameMenuItem(JMenuItem item) {
        gameMenuItems.add(item);
    }

    public void addLanguageMenuItem(JMenuItem item) {
        languageMenuItems.add(item);
    }

    // Getter methods for menu items
    public List<JMenuItem> getFileMenuItems() {
        return fileMenuItems;
    }

    public List<JMenuItem> getGameMenuItems() {
        return gameMenuItems;
    }

    public List<JMenuItem> getLanguageMenuItems() {
        return languageMenuItems;
    }
}
