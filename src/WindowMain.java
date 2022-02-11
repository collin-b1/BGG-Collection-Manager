import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;

/*
 * This is not the entry point to the Java application, go to the "Main" class
 */

public class WindowMain extends JFrame {

    public static Options.SortType sortType = Options.SortType.NAME;

    private final Main instance;
    private JMenuItem exitItem;
    private JMenuItem faqItem;

    private final GamePanel gamePanel;
    //private SideButtonPanel sideButtonPanel;

    public WindowMain(Main instance) {
        super("GameHub");
        this.instance = instance;

        setSize(1000,600);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load List");
        JMenuItem useSampleItem = new JMenuItem("Use Sample Data");
        JMenuItem clearTableItem = new JMenuItem("Clear Table");
        JMenuItem printItem = new JMenuItem("Print Table");
        fileMenu.add(loadItem);
        fileMenu.add(useSampleItem);
        fileMenu.add(clearTableItem);
        fileMenu.add(printItem);
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // Create container
        JPanel container = new JPanel();
        // Set container layout to be 2 rows, 2 columns
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        container.setLayout(layout);

        JPanel empty = new JPanel();

        gbc.gridx = 0; // x .
        gbc.gridy = 0; // . .
        container.add(empty, gbc);

        TopBarPanel topBarPanel = new TopBarPanel(this);
        gbc.gridx = 1; // . x
        gbc.gridy = 0; // . .
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(topBarPanel, gbc);

        JPanel sideOptionsPanel = new OptionsPanel(this);
        gbc.insets = new Insets(7,7,7,7);
        gbc.weightx = 0; // This gives the table priority for remaining space
        gbc.weighty = 1;
        gbc.gridx = 0; // . .
        gbc.gridy = 1; // x .
        gbc.fill = GridBagConstraints.BOTH; // Fill remaining horizontal and vertical space
        container.add(sideOptionsPanel, gbc);

        gamePanel = new GamePanel();
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 1; // This gives the table priority for remaining space
        gbc.weighty = 1;
        gbc.gridx = 1; // . .
        gbc.gridy = 1; // . x
        gbc.fill = GridBagConstraints.BOTH; // Fill remaining horizontal and vertical space
        container.add(gamePanel, gbc);

        add(container);

        //pack();                               // Resize window to best size
        setLocationRelativeTo(null);            // Center window on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);// Close entire program on window exit
        setVisible(true);                       // Show window

        // https://stackoverflow.com/a/29210689
        loadItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                //System.out.println(chooser.getSelectedFile());
                gamePanel.clearTable();
                instance.readFromPath(chooser.getSelectedFile().getAbsolutePath());
                loadGames(instance.getGameList());
            }
        });

        useSampleItem.addActionListener(e -> {
            gamePanel.clearTable();
            instance.readFromPath("src/demo_collection.csv");
            loadGames(instance.getGameList());
        });

        clearTableItem.addActionListener(e -> clearGames());

        // https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#printing
        printItem.addActionListener(e -> {
            MessageFormat header = new MessageFormat("Page {0,number,integer}");
            try {
                getGamePanel().getTable().print(JTable.PrintMode.FIT_WIDTH, header, null);
            } catch (java.awt.print.PrinterException ex) {
                System.err.format("Cannot print %s%n", ex.getMessage());
            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void loadGames(ArrayList<BoardGame> gameList) {
        for (BoardGame bg : gameList) {
            gamePanel.addGame(bg);
        }
        gamePanel.populateTable();
    }

    // Removes all games from list, resets table
    public void clearGames() {
        gamePanel.clearTable();
        gamePanel.getValues().clear();
        instance.getGameList().clear();
    }

    // Adds back all games from game list to table
    public void resetGames() {
        gamePanel.clearValues();
        gamePanel.clearTable();
        gamePanel.instantiateValues();
        loadGames(instance.getGameList());
    }

    public void setSortType(Options.SortType sortType) {
        this.sortType = sortType;
    }
}
