package Part2;

import javax.swing.*;
import java.awt.*;

public class RaceGUI {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Horse Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,1000);

        JPanel configPanel = createConfigPanel();
        JPanel trackPanel = createTrackPanel();
        JPanel statsPanel = createStatsPanel();
        JPanel horsePanel = createHorsePanel();

        frame.add(configPanel, BorderLayout.NORTH);
        frame.add(trackPanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.SOUTH);
        frame.add(horsePanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
    

    // Create a JPanel for the configuration
    private static JPanel createConfigPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // first row - number of lanes
        panel.add(new JLabel("Number of lanes: "));
        JTextField lanesInput = new JTextField();
        panel.add(lanesInput);

        // second row - length of lanes
        panel.add(new JLabel("Track Length: "));
        JTextField trackLengthInput = new JTextField();
        panel.add(trackLengthInput);

        // third row - Track Shape
        panel.add(new JLabel("Track Shape: "));
        JComboBox<String> shapeSelector = new JComboBox<>(new String[]{"Oval", "Figure-Eight", "Circular", "Others TBC"});
        panel.add(shapeSelector);

        // four row - Track Weather
        panel.add(new JLabel("Weather & Track Condition: "));
        JComboBox<String> WeatherSelector = new JComboBox<>(new String[]{"Muddy", "Dry", "Icy", "Rainy"});
        panel.add(WeatherSelector);

        // firth row - confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {

            int lanes = Integer.parseInt(lanesInput.getText()); // I DONT THINK IT CAN PARSE NON-NUMERICAL STRING INTO INT BUT DO THIS FOR NOW
            int trackLength = Integer.parseInt(trackLengthInput.getText()); // SAME AS ABOVE
            String shape = (String) shapeSelector.getSelectedItem();
            String weather = (String) WeatherSelector.getSelectedItem();

            System.out.println("Lanes: " + lanes);
            System.out.println("Track Length: " + trackLength);
            System.out.println("Track Shape: " + shape);
            System.out.println("Weather: " + weather);
        });
        panel.add(confirmButton);

        return panel;
    }

    //
    private static JPanel createTrackPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Example: 5 lanes
        panel.setBackground(Color.GREEN);
        return panel;
    }

    // 
    private static JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel statsLabel = new JLabel("Race Statistics:");
        JTextArea statsArea = new JTextArea(5, 30);
        statsArea.setEditable(false);

        panel.add(statsLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(statsArea), BorderLayout.CENTER);

        return panel;
    }

    // horse configuration
    private static JPanel createHorsePanel() {
        JPanel horsePanel = new JPanel(new GridLayout(3,2));

        JLabel horseNameLabel = new JLabel("Horse Name");
        JTextField horseNameField = new JTextField();
        
        JLabel horseSymbolLabel = new JLabel("Horse Symbol");
        JTextField horseSymbolField = new JTextField(1); // prob need to implement document filter to limit to one char only

        JLabel horseBreedLabel = new JLabel("Horse Breed");
        JComboBox<String> horseBreedSelector = new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse", "Others TBC"});

        // JLabel 

        horsePanel.add(horseNameLabel);
        horsePanel.add(horseNameField);
        horsePanel.add(horseSymbolLabel);
        horsePanel.add(horseSymbolField);
        horsePanel.add(horseBreedLabel);
        horsePanel.add(horseBreedSelector);

        return horsePanel;
    }



}

// JPanel configPanel = new JPanel();
//         configPanel.setLayout(new GridLayout(0, 2));
//         configPanel.setBounds(10, 10, 300, 200);
        
//         JLabel horseNameLabel = new JLabel("Horse Name:");
//         JTextField horseNameField = new JTextField();
//         JLabel horseSymbolLabel = new JLabel("Horse Symbol:");
//         JTextField horseSymbolField = new JTextField();
//         JLabel horseConfidenceLabel = new JLabel("Horse Confidence:");
//         JTextField horseConfidenceField = new JTextField();
        
//         configPanel.add(horseNameLabel);
//         configPanel.add(horseNameField);
//         configPanel.add(horseSymbolLabel);
//         configPanel.add(horseSymbolField);
//         configPanel.add(horseConfidenceLabel);
//         configPanel.add(horseConfidenceField);
        
//         return configPanel;