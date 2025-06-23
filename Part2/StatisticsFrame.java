package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 
 * @author Ho Ming, Liu
 * @version 2.0
 */

 /**
  * StatisticsFrame class
  * This method initializes the frame of the Horse's performance
  */
public class StatisticsFrame extends JFrame{
    private JComboBox<String> horseList;
    private JTextArea singleRaceArea, overallRaceArea, trackArea, historyArea;
    private Map<String, PerformanceStats> horseStatsMap;

    /*
     * @param horsStatsMap - a hashmap where key is String of horseName and value is the PerformanceStats
     */
    public StatisticsFrame(Map<String, PerformanceStats> horseStatsMap) {
        this.horseStatsMap = horseStatsMap;
        setTitle("Horse Racing Statistics");
        setSize(2000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        horseList = new JComboBox<>(horseStatsMap.keySet().toArray(new String[0]));
        horseList.addActionListener(e -> updateStatistics());
        add(horseList, BorderLayout.NORTH);

        singleRaceArea = new JTextArea();
        singleRaceArea.setText("Single Match Statistics(Last Match): \n");
        singleRaceArea.setEditable(false);
        add(singleRaceArea, BorderLayout.WEST);

        overallRaceArea = new JTextArea();
        overallRaceArea.setText("Overall Match Statistics: \n");
        overallRaceArea.setEditable(false);
        add(overallRaceArea, BorderLayout.CENTER);

        trackArea = new JTextArea();
        trackArea.setText("Track Statistics: \n");
        trackArea.setEditable(false);
        add(trackArea, BorderLayout.EAST);

        historyArea = new JTextArea();
        historyArea.setText("History Statistics: \n");
        historyArea.setEditable(false);
        add(historyArea, BorderLayout.SOUTH);

        updateStatistics();
    }

    // This method update Statistics frame visually when called
    //
    private void updateStatistics() {
        String selectedHorse = (String) horseList.getSelectedItem();
        PerformanceStats stats = horseStatsMap.get(selectedHorse);

        singleRaceArea.setText("Single Match Statistics(Last Match): \n");
        singleRaceArea.append("Date: " + stats.getLastMatch().getRaceDate() + "\n");
        singleRaceArea.append("Finishing Time: " + stats.getLastMatch().getFinishingTime() + "\n");
        

        overallRaceArea.setText("Overall Match Statistics: \n");
        overallRaceArea.append("Total Matches: " + stats.getTotalMatch() + "\n");
        overallRaceArea.append("Wins: " + stats.getWins() + "\n");
        singleRaceArea.append("Win Ratio: " + stats.getWinRatio() + "\n");

        trackArea.setText("Track Statistics: \n");
        for (Map.Entry<String, Double> entry : stats.getBestTime().entrySet()) {
            trackArea.append(entry.getKey() + "'s Best Time : " + entry.getValue() + "\n");
        }

        historyArea.setText("History Statistics: \n");
        for (HorsePerformance perf : (ArrayList<HorsePerformance>) stats.getHorsePerformances()) {
            historyArea.append(perf.toString() + "\n");
        }
    } // END updateStatistics
}
