package Part2;

import javax.swing.*;
import java.awt.*;

public class HorsePanel extends JPanel {
    // horse configuration
    // private static JPanel createHorsePanel() {
    //     JPanel horsePanel = new JPanel(new GridLayout(3,2));

    //     JLabel horseNameLabel = new JLabel("Horse Name");
    //     JTextField horseNameField = new JTextField();
        
    //     JLabel horseSymbolLabel = new JLabel("Horse Symbol");
    //     JTextField horseSymbolField = new JTextField(1); // prob need to implement document filter to limit to one char only

    //     JLabel horseBreedLabel = new JLabel("Horse Breed");
    //     JComboBox<String> horseBreedSelector = new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse", "Others TBC"});

    //     // JLabel 

    //     horsePanel.add(horseNameLabel);
    //     horsePanel.add(horseNameField);
    //     horsePanel.add(horseSymbolLabel);
    //     horsePanel.add(horseSymbolField);
    //     horsePanel.add(horseBreedLabel);
    //     horsePanel.add(horseBreedSelector);

    //     return horsePanel;
    // }

    public HorsePanel(Race race, RacePanel racePanel) {
        setLayout(new GridLayout(5,2));

        JLabel horseNameLabel = new JLabel("Horse Name");
        JTextField horseNameField = new JTextField();
        add(horseNameLabel);
        add(horseNameField);
        
        JLabel horseSymbolLabel = new JLabel("Horse Symbol");
        JTextField horseSymbolField = new JTextField(1); // prob need to implement document filter to limit to one char only
        add(horseSymbolLabel);
        add(horseSymbolField);

        JLabel horseConfidenceLabel = new JLabel("Horse Confidence");
        JTextField horseConfidenceField = new JTextField();
        add(horseConfidenceLabel);
        add(horseConfidenceField);

        JLabel horseBreedLabel = new JLabel("Horse Breed");
        JComboBox<String> horseBreedSelector = new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse"});
        add(horseBreedLabel);
        add(horseBreedSelector);

        JLabel horseColorLabel = new JLabel("Coat Color");
        JComboBox<String> horseColorSelector = new JComboBox<>(new String[]{"Brown", "Black", "Grey", "White"});
        add(horseColorLabel);
        add(horseColorSelector);

        JLabel saddleLabel = new JLabel("Saddle Type");
        JComboBox<String> saddleSelector = new JComboBox<>(new String[]{"Standard", "Racing", "Fancy"});
        add(saddleLabel);
        add(saddleSelector);

        JLabel shoesLabel = new JLabel("Horseshoes");
        JComboBox<String> shoesSelector = new JComboBox<>(new String[]{"Regular", "Lightweight", "Heavyweight"});
        add(shoesLabel);
        add(shoesSelector);

        JLabel accessoriesLabel = new JLabel("Accessories");
        JComboBox<String> accessoriesSelector = new JComboBox<>(new String[]{"None", "Bridle", "Blanket", "Hat"});
        add(accessoriesLabel);
        add(accessoriesSelector);

        JButton addHorseButton = new JButton("Add Horse");
        addHorseButton.addActionListener(e -> {
            String name = horseNameField.getText();
            String symbol = Filter.firstSymbol(horseSymbolField.getText());
            double confidence = Double.parseDouble(horseConfidenceField.getText());
            String breed = (String) horseBreedSelector.getSelectedItem();
            String coatColor = (String) horseColorSelector.getSelectedItem();
            String saddle = (String) saddleSelector.getSelectedItem();
            String horseshoes = (String) shoesSelector.getSelectedItem();
            String accessories = (String) accessoriesSelector.getSelectedItem();
            Horse h = new Horse(symbol, name, confidence, breed, coatColor, accessories, horseshoes, saddle);
            race.addHorse(h);
            // racePanel.repaint();
            racePanel.UpdateScreen();
        });
        add(addHorseButton);


        
    }
}
