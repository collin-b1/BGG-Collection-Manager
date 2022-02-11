import javax.swing.*;

public class TopBarPanel extends JPanel {

    private final JComboBox sortMenu;
    private final String[] options = {
            "Name",
            "Players",
            "Minutes",
            "Complexity",
            "Rating",
    };

    public TopBarPanel(WindowMain mainWindow) {

        sortMenu = new JComboBox(Options.SortType.values());
        JLabel sortLabel = new JLabel("Sort By: ");
        add(sortLabel);
        add(sortMenu);

        sortMenu.addActionListener(e -> {
            mainWindow.setSortType((Options.SortType) sortMenu.getSelectedItem());
            mainWindow.resetGames();
        });
    }
}
