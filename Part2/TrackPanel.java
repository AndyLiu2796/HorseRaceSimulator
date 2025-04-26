package Part2;

import javax.swing.*;
import java.awt.*;

public class TrackPanel extends JPanel {
    public TrackPanel(Race race, RacePanel racePanel) {
        setLayout(new GridLayout(5, 2));

        // first row - number of lanes
        add(new JLabel("Number of lanes: "));
        JTextField lanesInput = new JTextField();
        add(lanesInput);

        // second row - length of lanes
        add(new JLabel("Track Length: "));
        JTextField trackLengthInput = new JTextField();
        add(trackLengthInput);

        // third row - Track Shape
        add(new JLabel("Track Shape: "));
        JComboBox<String> shapeSelector = new JComboBox<>(new String[]{"Straight", "Oval", "Figure-Eight", "Circular"});
        add(shapeSelector);

        // four row - Track Weather
        add(new JLabel("Weather & Track Condition: "));
        JComboBox<String> WeatherSelector = new JComboBox<>(new String[]{"Muddy", "Dry", "Icy", "Rainy"});
        add(WeatherSelector);

        // firth row - confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {

            int lanes = Integer.parseInt(lanesInput.getText()); // I DONT THINK IT CAN PARSE NON-NUMERICAL STRING INTO INT BUT DO THIS FOR NOW
            int trackLength = Integer.parseInt(trackLengthInput.getText()); // SAME AS ABOVE
            String shape = (String) shapeSelector.getSelectedItem();
            String weather = (String) WeatherSelector.getSelectedItem();

            race.setLaneNumber(lanes);
            race.setTrackLength(trackLength);
            race.setTrackShape(shape);
            race.setWeather(weather);
            confirmButton.setText("Reset");
        });
        add(confirmButton);
    }
}

