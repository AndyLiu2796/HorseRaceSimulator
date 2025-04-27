package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Race race = new Race(30,0);
        RacePanel racePanel = new RacePanel(race);
        JFrame frame = new JFrame("Horse Race Simulator");
        final RaceBet[] currentRaceBet = new RaceBet[1];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
        frame.add(racePanel, BorderLayout.CENTER);
        frame.add(new JTextField("Statistics"), BorderLayout.SOUTH);
        // Horse h1 = new Horse("@", "Horse 1", 0.9);
        // race.addHorse(h1);
        HorsePanel horsePanel = new HorsePanel(race, racePanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem openHorsePanelItem = new JMenuItem("Open Horse Panel");
        JMenuItem openStatisticsItem = new JMenuItem("Open Statistics");
        JMenuItem openBettingItem = new JMenuItem("Open Betting");
        menu.add(openHorsePanelItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        openHorsePanelItem.addActionListener(e -> {
            JFrame horseFrame = new JFrame("Horse Panel");
            horseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            horseFrame.setSize(400, 400);
            horseFrame.setLayout(new BorderLayout());
            horseFrame.add(horsePanel, BorderLayout.CENTER);
            horseFrame.setVisible(true);
        });

        menu.add(openStatisticsItem);
        openStatisticsItem.addActionListener(e -> {
            Map<String, PerformanceStats> horseStatsMap = new HashMap<>();
            for (Horse h : race.returnLanes()) {
                if (h != null) {
                    horseStatsMap.put(h.getName(), h.getStats());
                }
            }

            StatisticsFrame sframe = new StatisticsFrame(horseStatsMap);
            sframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            sframe.setSize(400, 400);
            sframe.setVisible(true);
        });

        menu.add(openBettingItem);
        openBettingItem.addActionListener(e -> {
            RaceBet raceBet = new RaceBet(race);
            currentRaceBet[0] = raceBet;
            BettingFrame bf = new BettingFrame(raceBet, race);
            bf.setVisible(true);
        });
        TrackPanel trackPanel = new TrackPanel(race, racePanel);
        frame.add(trackPanel, BorderLayout.NORTH);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            racePanel.UpdateScreen();
        });
        // frame.add(refreshButton, BorderLayout.WEST);
        racePanel.UpdateScreen();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            new Thread(() -> {
                if (currentRaceBet[0] != null) {
                    race.startRace(racePanel, currentRaceBet[0]);
                }
                else {
                    race.startRace(racePanel, new RaceBet(race));
                }
            }).start();;
            startButton.setText("Reset");
        });
        frame.add(startButton, BorderLayout.SOUTH);
        // frame.add(refreshButton, BorderLayout.WEST);
        frame.setVisible(true);
    }
}
