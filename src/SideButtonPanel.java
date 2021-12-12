import javax.swing.*;
import java.awt.*;

public class SideButtonPanel extends JPanel {

    private final JButton bestFitsButton;
    private final JButton filterButton;
    private final JButton resetButton;

    public SideButtonPanel() {
        GridLayout layout = new GridLayout(0, 1);
        setLayout(layout);

        bestFitsButton = new JButton("Find Best Fits");
        filterButton = new JButton("Filter Selection");
        resetButton = new JButton("Reset");

        add(bestFitsButton);
        add(filterButton);
        add(resetButton);
    }

    public JButton getBestFitsButton() { return bestFitsButton; }
    public JButton getFilterButton() { return filterButton; }
    public JButton getResetButton() { return resetButton; }
}
