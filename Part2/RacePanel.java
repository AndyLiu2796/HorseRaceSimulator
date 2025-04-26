package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RacePanel extends JPanel{
    private Race race;

    public RacePanel(Race race) {
        this.race = race;
        this.setPreferredSize(new Dimension(100+race.getTrackLength(), race.getLaneNumber()+100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    protected void UpdateScreen() {
        removeAll();
        for (Horse h : race.returnLanes()) {
            JTextField jtf = new JTextField();
            jtf.setPreferredSize(new Dimension(race.getTrackLength()*15, jtf.getPreferredSize().height));
            jtf.setMaximumSize(new Dimension(race.getTrackLength()*15, jtf.getPreferredSize().height));
            switch (race.getWeather()) {
                case "Muddy":
                    jtf.setBackground(Color.LIGHT_GRAY);
                    break;
                case "Dry":
                    jtf.setBackground(Color.YELLOW);
                    break;
                case "Icy":
                    jtf.setBackground(Color.CYAN);
                    break;
                case "Rainy":
                    jtf.setBackground(Color.BLUE);
                    break;
                default:
                    jtf.setBackground(Color.WHITE);
                    break;
            }
            if (h == null) {
                jtf.setText("Empty Lane");
            }
            else {
                String coatColor = h.getCoatColor().toString();
                switch (coatColor) {
                    case "Brown":
                        jtf.setForeground(Color.ORANGE);
                        break;
                    case "Black":
                        jtf.setForeground(Color.BLACK);
                        break;
                    case "Grey":
                        jtf.setForeground(Color.GRAY);
                        break;
                    case "White":
                        jtf.setForeground(Color.WHITE);
                        break;
                    default:
                        jtf.setForeground(Color.BLACK);
                        break;
                }
            }
            jtf.setText(printHorse(h));
            jtf.setEditable(false);
            jtf.setFont(new Font("Monospaced", Font.PLAIN, 16));
            jtf.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    HorseSingleStats hss = new HorseSingleStats();
                    hss.printStats(h);
                }
            });
            add(jtf);
        }
        JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(race.getTrackLength()*15, jtf.getPreferredSize().height));
        if (!race.checkLosers()) {
            jtf.setText("No winners");
        }
        else {
            if (race.getWinners().size() == 1) {
                jtf.setText("Winner: " + race.getWinners().get(0) + " won!");
            }
            else if (race.getWinners().size() > 1) {
                jtf.setText("Winners: " + String.join(" + ", race.getWinners()) + " won!");
            }
        }
        jtf.setEditable(false);
        add(jtf);
        revalidate();
        repaint();
    }


    protected String printHorse (Horse h) {
        StringBuilder sb = new StringBuilder();
        if (h == null) {
            sb.append(multipleReturn(' ', race.getTrackLength()));
            return sb.toString();
        }
        int spacesBefore = h.getDistanceTravelled();
        int spacesAfter = (race.getTrackLength() - h.getDistanceTravelled());
        
        sb.append(multipleReturn(' ', spacesBefore));
        if (h.hasFallen()) {
            sb.append('\u2322');
        }
        else {
            sb.append(h.getSymbol());
        }
        sb.append(multipleReturn(' ', spacesAfter));
        sb.append("|");
        return sb.toString();
    }

    private String multipleReturn(char aChar, int times) {
        int i = 0;
        String s = "";
        while (i < times) {
            s += Character.toString(aChar);
            i++;
        }
        return s;
    }

}
