package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math;

/**
 * 
 * @author Ho Ming, Liu
 * @version 2.0
 */

 /**
  * RacePanel class- Simulates the horse track in a graphical framework
  * This class manages the visual representation of the race tracks
  * Note that the horse position in different tracks(e.g. oval/circular/straight/figure-eight) are done differently
  * but in general the relative position/actual logic treats the track as straight line
  * and is converted to the point on the shape using different formulas
  */
public class RacePanel extends JPanel{
    private Race race;

    // Constructor
    //
    public RacePanel(Race race) {
        this.race = race;
        this.setPreferredSize(new Dimension(100+race.getTrackLength(), race.getLaneNumber()+100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // This method updates the screen when a race starts
    //
    protected void UpdateScreen() {
        removeAll();
        revalidate();
        repaint();

        JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(race.getTrackLength()*15, jtf.getPreferredSize().height));
        jtf.setMaximumSize(new Dimension(race.getTrackLength()*15, jtf.getPreferredSize().height));
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
    } // END updateScreen

    // This method is for older version
    //
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
    } // END printHorse

    // Older method
    //
    private String multipleReturn(char aChar, int times) {
        int i = 0;
        String s = "";
        while (i < times) {
            s += Character.toString(aChar);
            i++;
        }
        return s;
    } // END multipleReturn

    // This method is the new graphic approach to print the track of different shapes
    //
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (race.getTrackShape()) {
            case "Straight":
                drawStraightTrack(g);
                drawStraightHorses(g);
                break;
            case "Oval":
                drawOvalTrack(g);
                drawOvalHorse(g);
                break;
            case "Circular":
                drawCircularTrack(g);
                drawCircularHorse(g);
                break;
            case "Figure-Eight":
                drawFigureEightTrack(g);
                drawFigureEightHorse(g);
                break;
            default:
                drawStraightTrack(g);
                drawStraightHorses(g);
                break;
        }
    } // END paintComponent

    // This method just converts string color to actual color
    //
    public Color returnTrackColor() {
        switch (race.getWeather()) {
            case "Muddy":
                return(Color.LIGHT_GRAY);
            case "Dry":
                return(Color.YELLOW);
            case "Icy":
                return(Color.CYAN);
            case "Rainy":
                return(Color.BLUE);
            default:
                return(Color.WHITE);
        }
    } // END returnTrackColor

    // This method just convert string horsecolor to actual horse
    //
    public Color returnHorseColor(Horse h) {
        String coatColor = h.getCoatColor().toString();
        switch (coatColor) {
            case "Brown":
                return(Color.ORANGE);
            case "Black":
                return(Color.BLACK);
            case "Grey":
                return(Color.GRAY);
            case "White":
                return(Color.WHITE);
            default:
                return(Color.BLACK);
        }
    } // END returnHorseColor

    // This method draws straight tracks
    //
    public void drawStraightTrack(Graphics g) {
        for (int i = 0; i <race.getLaneNumber(); i++) {
            int y = 50+i*50;
            g.setColor(returnTrackColor());
            g.drawLine(50,y, 50+race.getTrackLength()*10,y);
        }
    } // END drawStraightTrack

    // This method update horses' positions for straight tracks
    //
    public void drawStraightHorses(Graphics g) {
        int count = 0;
        for (Horse h: race.returnLanes()) {
            if (h != null) {
                int y = 50+count*50;
                double relativePos = (double) h.getDistanceTravelled() / race.getTrackLength();
                g.setColor(returnHorseColor(h));
                if (!h.hasFallen()) {
                    g.drawString(h.getSymbol(), (int)(50 + relativePos*race.getTrackLength()*10 - 5), y-5);
                }
                else {
                    g.drawString("❌", (int)(50 + relativePos*race.getTrackLength()*10 - 5), y-5);
                }
                

            }
            count++;
        }
    } // END drawStraightHorses

    // This method draws circular track
    //
    public void drawCircularTrack(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int smallestRadius = 100;
        for (int i = 0; i< race.getLaneNumber(); i++) {
            int radius = smallestRadius + i * 50;
            g.setColor(returnTrackColor());
            g.drawOval(midX-radius, midY-radius, radius*2, radius*2);
        }
    } // END drawCircularTrack

    // This method update horse position on circular track
    //
    public void drawCircularHorse(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int smallestRadius = 100;

        for (int i = 0; i < race.getLaneNumber(); i++) {
            Horse h = race.returnLanes().get(i);
            if (h != null) {
                double relativePos = (double) h.getDistanceTravelled() / race.getTrackLength();
                int radius = smallestRadius + i * 50;
                double angle = relativePos * 2 * Math.PI - Math.PI/2; // the minus part so it starts from top
                g.setColor(returnHorseColor(h));
                if (!h.hasFallen()) {
                    g.drawString(h.getSymbol(), (int)(midX + radius * Math.cos(angle) - 5), (int)(midY + radius * Math.sin(angle) - 5));
                }
                else {
                    g.drawString("❌", (int)(midX + radius * Math.cos(angle) - 5), (int)(midY + radius * Math.sin(angle) - 5));
                }
            }
        }
    } // END drawCircularHorse

    // This method draws oval track
    // essentially same cas circle cuz circle is kinda like a regular oval
    //
    public void drawOvalTrack(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int smallestRadius = 100;
        for (int i = 0; i< race.getLaneNumber(); i++) {
            int radiusX = smallestRadius + i * 50;
            int radiusY = smallestRadius + i * 25;
            g.setColor(returnTrackColor());
            g.drawOval(midX-radiusX, midY-radiusY, radiusX*2, radiusY*2);
        }
    } // END drawOvalTrack

    // This method updates horses' position in oval track
    //
    public void drawOvalHorse(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int smallestRadius = 100;
        for (int i = 0; i< race.getLaneNumber(); i++) {
            Horse h = race.returnLanes().get(i);
            if (h != null) {
                double relativePos = (double) h.getDistanceTravelled() / race.getTrackLength();
                int radiusX = smallestRadius + i * 50;
                int radiusY = smallestRadius + i * 25;
                double angle = relativePos * 2 * Math.PI - Math.PI/2; // start top
                g.setColor(returnHorseColor(h));
                if (!h.hasFallen()) {
                    g.drawString(h.getSymbol(), (int)(midX + radiusX * Math.cos(angle) - 5), (int)(midY + radiusY * Math.sin(angle) - 5));
                }
                else {
                    g.drawString("❌", (int)(midX + radiusX * Math.cos(angle) - 5), (int)(midY + radiusY * Math.sin(angle) - 5));
                }
            } 
            
        }
    } // END drawOvalHorses

    // THIS METHOD IS NOT IMPLEMENTED FULLY
    // JUST A PLACEHOLDER FOR NOW THERES SOME PROBLEM WITH THE GRAPHICAL REPRESENTATION
    //  This draws a figure eight track(sth like infinity sign)
    //
    public void drawFigureEightTrack(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int d = 2 * (100 + (race.getLaneNumber()-1) * 50);
        for (int i = 0; i < race.getLaneNumber(); i++) {
            int radius = 100 + i * 50;
            g.setColor(returnTrackColor());
            g.drawOval(midX-d/2 - radius, midY - radius, radius * 2, radius * 2);//left side
            g.drawOval(midX+d/2 - radius, midY - radius, radius * 2, radius * 2);// right side
        }
    } // END drawFigureEightTrack

    // This method updates the horses' position in figure-eight track
    //
    public void drawFigureEightHorse(Graphics g) {
        int midX = this.getWidth()/2;
        int midY = this.getHeight()/2;
        int d = 2 * (100 + (race.getLaneNumber()-1) * 50);
        for (int i = 0; i < race.getLaneNumber(); i++) {
            Horse h = race.returnLanes().get(i);
            if (h!=null) {
                double relativePos = (double) h.getDistanceTravelled() / race.getTrackLength();
                int radius = 100 + i * 50;
                double angle;
                int x;
                int y;
                if (relativePos < 0.5) {
                    angle = relativePos * 2 * Math.PI - Math.PI/2; // start top
                    x = (int)(midX-d/2 + radius * Math.cos(angle) - 5);
                    y = (int)(midY + radius * Math.sin(angle) - 5);
                }
                else {
                    angle = (relativePos - 0.5) * 2 * Math.PI + Math.PI/2;
                    x = (int)(midX+d/2 - radius * Math.cos(angle) - 5);
                    y = (int)(midY - radius * Math.sin(angle) - 5);
                }
                g.setColor(returnHorseColor(h));
                if (!h.hasFallen()) {
                    g.drawString(h.getSymbol(), x-5, y-5);
                }
                else {
                    g.drawString("❌", x-5, y-5);
                }
            }
        }
    } // END drawFigureEightHorse
}
