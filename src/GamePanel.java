import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.PriorityQueue;

/**
 * Sources:
 * https://www.tutorialspoint.com/how-to-set-the-color-to-alternate-rows-of-jtable-in-java
 * https://stackoverflow.com/questions/4577792/how-to-clear-jtable/4578501
  */

public class GamePanel extends JPanel {

    public final Color ALT_COLOR = new Color(225,225,225);

    private DefaultTableModel model;
    private JTable table;

    public static final String[] columns = {"Name", "Difficulty", "Players", "Time", "Rating"};
    private PriorityQueue<BoardGame> values;

    public GamePanel() {
        setLayout(new GridLayout());

        Object[][] data = {};
        instantiateValues();

        setTable(data);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);

        add(scrollPane);

        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    public void instantiateValues() {
        values = new PriorityQueue<>();
    }

    public void addGame(BoardGame bg) {
        values.add(bg);
    }

    public void populateTable() {
        clearTable();
        PriorityQueue<BoardGame> copy = new PriorityQueue<>(values);
        while (!copy.isEmpty()) {
            BoardGame bg = copy.poll();
            model.addRow(new Object[]{
                    bg.getName(),
                    String.format("%.2f", bg.getStats().getDifficulty()),
                    bg.getStats().getMinPlayers() + "-" + bg.getStats().getMaxPlayers(),
                    bg.getStats().getMinPlayingTime() + "-" + bg.getStats().getMaxPlayingTime(),
                    bg.getStats().getRating()
            });
        }
    }

    public void clearTable() {
        if (table != null) model.setRowCount(0);
    }

    public void setTable(Object[][] data) {
        clearTable();
        model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if(!component.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? ALT_COLOR : Color.WHITE);
                    component.setBackground(c);
                }
                return component;
            }
        };
        table.setModel(model);
    }

    public JTable getTable() {
        return this.table;
    }

    public PriorityQueue<BoardGame> getValues() {
        return values;
    }

    public void clearValues() {
        values.clear();
    }
}
