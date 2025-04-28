package Part2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class BettingFrame extends JFrame{
    private RaceBet raceBet;
    private List<Horse> horses;
    private JComboBox<String> horseComboBox;
    private JTextField betAmountField;
    private JButton confirmBetButton;
    private JLabel oddsLabel;
    private JTextArea live;

    private Map<String, Horse> horseNameMap = new HashMap<>();

    public BettingFrame(RaceBet raceBet, Race race) {
        // for (HorseBet hb : raceBet.getHorseBets()) {
        //     System.out.println(hb.getHorse().getName());
        // }
        this.raceBet = raceBet;
        this.horses = new ArrayList<>();
        for (HorseBet hb : raceBet.getHorseBets()) {
            horses.add(hb.getHorse());
            horseNameMap.put(hb.getHorse().getName(), hb.getHorse());
        }
    
        setTitle("Betting Frame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        horseComboBox = new JComboBox<>(horseNameMap.keySet().toArray(new String[0]));
        betAmountField = new JTextField(20);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            reloadRaceBet(race);
            reloadHorses();

        });
        
        top.add(new JLabel("Choose Horse: "));
        top.add(horseComboBox);

        top.add(new JLabel("Bet Amount: "));
        top.add(betAmountField);
        top.add(refreshButton);

        JPanel center = new JPanel(new GridLayout(1,3));
        List<HorseBet> horseBetsList = new ArrayList<>(raceBet.getHorseBets());
        oddsLabel = new JLabel();
        confirmBetButton = new JButton("Confirm Bet");
        center.add(oddsLabel);
        center.add(confirmBetButton);
        JButton showStatsButton = new JButton("Show Stats");
        center.add(showStatsButton);

        live = new JTextArea();
        live.setEditable(false);

        horseComboBox.addActionListener(e -> {
            showOdds();
            updateRealTimeBet();
        });
        confirmBetButton.addActionListener(e -> addBet());

        showStatsButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (HorseBet hb : horseBetsList) {
                sb.append(hb.getHorse().getName() + " Odds: " + String.format("%.2f", hb.getOdds()) + "\n");
                sb.append(hb.getHorse().getName() + " Amount: " + hb.getBetAmount() + "\n");
                sb.append("Net: " + race.getTotalIncome() + "\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Betting Stats", JOptionPane.INFORMATION_MESSAGE);
        });
        
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(live, BorderLayout.SOUTH);

        showOdds();
        updateRealTimeBet();
    }
    
    private void showOdds() {
        String horseN = (String) horseComboBox.getSelectedItem();
        Horse horse = horseNameMap.get(horseN);
        // with the value put it inside the made method
        double odds = raceBet.calcOdd(horse);
        oddsLabel.setText("Odds: " + String.format("%.2f", odds));
    }

    private void updateRealTimeBet() {
        live.setText("");
        StringBuilder sb = new StringBuilder();
        String horseN = (String) horseComboBox.getSelectedItem();
        Horse horse = horseNameMap.get(horseN);
        double odds = raceBet.calcOdd(horse);
        for (HorseBet hb : raceBet.getHorseBets()) {
            if (hb.getHorse() != null && hb.getHorse().getName().equals(horseN)) {
                odds = hb.getOdds();
                sb.append("Horse: " + hb.getHorse().getName() + " Odds: " + String.format("%.2f", odds) + " Amount: " + hb.getBetAmount() + "\n");
                break;
            }
        }
        live.setText(sb.toString());
    }

    private void addBet() {
        String horseN = (String) horseComboBox.getSelectedItem();
        Horse horse = horseNameMap.get(horseN);
        double bet = 0;
        if (betAmountField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a bet amount.");
            return;
        }
        if (Double.parseDouble(betAmountField.getText()) > 0) {
            bet = Double.parseDouble(betAmountField.getText());
            JOptionPane.showMessageDialog(this, "Bet done");
        } else {
            JOptionPane.showMessageDialog(this, "Bet amount invalid.");
            return;
        }
        raceBet.placeAmount(bet, horse);
        showOdds();
        updateRealTimeBet();
    }

    private void reloadHorses() {
        horseNameMap.clear();
        horses.clear();
        for (HorseBet hb : raceBet.getHorseBets()) {
            if (hb.getHorse() != null) {
                horses.add(hb.getHorse());
                horseNameMap.put(hb.getHorse().getName(), hb.getHorse());
            }
            
        }
        horseComboBox.setModel(new DefaultComboBoxModel<>(horseNameMap.keySet().toArray(new String[0])));
        showOdds();
        updateRealTimeBet();
    }

    public void reloadRaceBet(Race race) {
        this.raceBet = new RaceBet(race);
        reloadHorses();
    }
}  
