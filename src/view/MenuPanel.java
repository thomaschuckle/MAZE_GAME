package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Creating menu bar
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Load"));
        fileMenu.add(new JMenuItem("Save"));

        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(new JMenuItem("Restart"));
        gameMenu.add(new JMenuItem("About"));

        JMenu languageMenu = new JMenu("Language");
        languageMenu.add(new JMenuItem("English"));
        languageMenu.add(new JMenuItem("Vietnamese"));

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(languageMenu);
        menuBar.add(new JMenu("Help"));
        menuBar.add(new JMenu("Network"));

        return menuBar;
    }
}
