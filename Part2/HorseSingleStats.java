package Part2;

import javax.swing.*;

/**
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 * HorseSingleStat class 
 * This is the class responsbile for the panel showing the horse performance in one race
 */

public class HorseSingleStats {
    public JFrame printStats(Horse s) {
        JFrame frame = new JFrame("Horse Statistics");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + s.getName());
        JLabel symbolLabel = new JLabel("Symbol: " + s.getSymbol());
        JLabel distanceLabel = new JLabel("Distance Travelled: " + s.getDistanceTravelled());
        JLabel confidenceLabel = new JLabel("Confidence: " + s.getConfidence());
        // JLabel speedLabel = new JLabel("Speed: " + s.getSpeed());
        JLabel fallenLabel = new JLabel("Fallen: " + (s.hasFallen() ? "Yes" : "No"));

        panel.add(nameLabel);
        panel.add(symbolLabel);
        panel.add(distanceLabel);
        panel.add(confidenceLabel);
        // panel.add(speedLabel);
        panel.add(fallenLabel);

        frame.add(panel);
        frame.setVisible(true);

        return frame;

    }
}
