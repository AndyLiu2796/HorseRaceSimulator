package Part2;

import java.util.List;
import javax.swing.*;

/**
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 * HorseStatsOption class 
 * This is the class responsbile for panelon an individual horse
 */
public class HorseStatsOption extends JFrame{
    private JComboBox<Horse> horseComboBox;
    private List<Horse> horseList;
    
    public HorseStatsOption(List<Horse> horses) {
        this.horseList = horses;
        setTitle("Horse Statistics");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        horseComboBox = new JComboBox<>(horseList.toArray(new Horse[0]));
        JButton showStatsButton = new JButton("Show Stats");
        
        showStatsButton.addActionListener(e -> {
            Horse selectedHorse = (Horse) horseComboBox.getSelectedItem();
            if (selectedHorse != null) {
                HorseSingleStats statsFrame = new HorseSingleStats();
                statsFrame.printStats(selectedHorse);
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(horseComboBox);
        panel.add(showStatsButton);
        
        add(panel);
        setVisible(true);
    }
}
