package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class LifePanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 2L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final Map<Player, JLabel> lifeLabels;

    

    /**
     * The default way in which the score is shown.
     */
    public static final LifeFormatter DEG_LIFE_FORMATTER =
        (Player player) -> String.format("Life: %3d", player.getLives());

    /**
     * The way to format the score information.
     */
    private LifeFormatter lifeFormatter = DEG_LIFE_FORMATTER;

    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    public LifePanel(List<Player> players) {
        super();
        assert players != null;

        setLayout(new GridLayout(2, players.size()));

        //for (int i = 1; i <= players.size(); i++) {
           // add(new JLabel("Player " + i, JLabel.CENTER));
        //}
        lifeLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel lifeLabel = new JLabel("0", JLabel.CENTER);
            lifeLabels.put(player, lifeLabel);
            add(lifeLabel);
        }
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : lifeLabels.entrySet()) {
            Player player = entry.getKey();
            String lives = "";
            if (!player.isAlive()) {
                lives = "You died.";
            }
            lives += lifeFormatter.format(player);
            entry.getValue().setText(lives);
        }
    }

    /**
     * Provide means to format the score for a given player.
     */
    public interface LifeFormatter {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the score panel use a dedicated score formatter.
     * @param lifeFormatter Score formatter to be used.
     */
    public void setLifeFormatter(LifeFormatter lifeFormatter) {
        assert lifeFormatter != null;
        this.lifeFormatter = lifeFormatter;
    }
}
