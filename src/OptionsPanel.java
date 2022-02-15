import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class OptionsPanel extends JPanel {

    private final WindowMain windowMain;

    private final SideButtonPanel sideButtonPanel;
    private final JTextField playersField;
    private final JTextField minutesField;
    private final JTextField complexityField;
    private final JCheckBox expansionsButton;

    public OptionsPanel(WindowMain windowMain) {
        this.windowMain = windowMain;

        sideButtonPanel = new SideButtonPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        // Players Field/Label
        playersField = new JTextField();
        JLabel playersLabel = new JLabel("Players: ");
        playersLabel.setLabelFor(playersField);

        // Time Field/Label
        minutesField = new JTextField();
        JLabel minutesLabel = new JLabel("Minutes: ");
        minutesLabel.setLabelFor(minutesField);

        // Complexity Field/Label
        complexityField = new JTextField();
        JLabel complexityLabel = new JLabel("Max Difficulty: ");
        complexityLabel.setLabelFor(complexityField);

        // Expansions Field/Label
        expansionsButton = new JCheckBox();
        JLabel expansionsLabel = new JLabel("Expansions?: ");
        expansionsLabel.setLabelFor(expansionsButton);

        gbc.anchor = GridBagConstraints.WEST;

        // Append Players
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(playersLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(playersField, gbc);

        // Append Time
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(minutesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(minutesField, gbc);

        // Append Complexity
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(complexityLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(complexityField, gbc);

        // Append Expansions
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(expansionsLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(expansionsButton, gbc);
        expansionsButton.setSelected(true);

        // Append Button Panel
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.NONE;
        add(sideButtonPanel, gbc);

        // Add functionality to buttons
        registerButtonActions();
    }

    public void filter() {
        Options options = getOptions();

        PriorityQueue<BoardGame> values = windowMain.getGamePanel().getValues();
        ArrayList<BoardGame> toRemove = new ArrayList<>();

        Iterator<BoardGame> iterator = values.iterator();

        while (iterator.hasNext()) {
            BoardGame next = iterator.next();
            if (!next.getStats().matches(options)) {
                toRemove.add(next);
            }
        }

        for (BoardGame bg : toRemove) {
            values.remove(bg);
        }

        windowMain.getGamePanel().populateTable();
    }

    public void registerButtonActions() {
        sideButtonPanel.getBestFitsButton().addActionListener(e -> {
            if (!optionsFilled()) return;
            windowMain.setSortType(Options.SortType.RATING);
            windowMain.resetGames();
            filter();
            JFrame frame = new JFrame();

            PriorityQueue<BoardGame> list = windowMain.getGamePanel().getValues();

            BoardGame bestRating = list.peek();
            BoardGame bestPlayers = list.peek();

            while (!list.isEmpty()) {
                if (IntStream.of(list.peek().getStats().getRecPlayers()).anyMatch(x -> x == getOptions().getMinPlayers())) {
                    bestPlayers = list.peek();
                    break;
                }
                list.poll();
            }

            String optText = String.format(
                    "BEST RATING%n%s%nDifficulty: %.2f/5.0%nTime: %s minutes%nRating: %.2f%n%nBEST AT %s PLAYER(S)%n%s%nDifficulty: %.2f/5.0%nTime: %s minutes%nRec. Players: %s",
                    bestRating.getName(),
                    bestRating.getStats().getDifficulty(),
                    bestRating.getStats().getMaxPlayingTime(),
                    bestRating.getStats().getRating(),
                    getOptions().getMinPlayers(),
                    bestPlayers.getName(),
                    bestPlayers.getStats().getDifficulty(),
                    bestPlayers.getStats().getMaxPlayingTime(),
                    Arrays.toString(bestPlayers.getStats().getRecPlayers())
            );



            JOptionPane.showMessageDialog(frame, optText, "Recommended Game(s)", JOptionPane.INFORMATION_MESSAGE);
        });

        sideButtonPanel.getFilterButton().addActionListener(e -> {
            if (optionsFilled()) {
                filter();
            }
        });

        sideButtonPanel.getResetButton().addActionListener(e -> {
            windowMain.resetGames();
        });
    }

    public Options getOptions() {
        Options opt = new Options();

        int players = 0;
        if (!playersField.getText().equals("")) {
            players = Integer.parseInt(playersField.getText());
        }
        opt.setMinPlayers(players);

        int minutes = Integer.MAX_VALUE;
        if (!minutesField.getText().equals("")) {
            minutes = Integer.parseInt(minutesField.getText());
        }
        opt.setMaxPlayingTime(minutes);

        double difficulty = 5.0;
        if (!complexityField.getText().equals("")) {
            difficulty = Double.parseDouble(complexityField.getText());
        }
        opt.setDifficulty(difficulty);

        boolean expansions = expansionsButton.isSelected();
        opt.setIsExpansion(expansions);
        return opt;
    }

    boolean optionsFilled() {
        return playersField.getText().length() > 0 && minutesField.getText().length() > 0 && complexityField.getText().length() > 0;
    }
}
