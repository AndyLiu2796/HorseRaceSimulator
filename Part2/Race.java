package Part2;

import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.time.LocalDate;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author Ho Ming, Liu
 * @version 2.0
 */

 /**
  * Race class- Simulates a custom-sized horse race with customizable track and weather conditions
  * This class manages the race setup, horse movements, race progress and results
  */
public class Race
{
    private int raceLength;
    private int laneNumber;
    private List<Horse> lanes = new ArrayList<>(); // 
    // extra fields
    private String trackShape = "Straight"; // default track shape others are like figure eight and circular i want a straight
    private String weather = "Dry"; // default track surface others are like muddy icy rainy

    private double totalIncome = 0.0;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the laness
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     * @param numLanes the number of lanes that would be added to the track
     */
    public Race(int distance, int numLanes)
    {
        // initialise instance variables
        this.raceLength = distance;
        this.laneNumber = numLanes;
        lanes.clear(); //reset arraylist to clear previous run
        for (int i = 0; i < this.laneNumber; i++) {
            lanes.add(null);
        }
    }

    /* *
     * GUI accessors and mutators
     */
    // GUI setters

    // This method sets the tracklength
    //
    public void setTrackLength(int length) {
        this.raceLength = length;
    } // END setTrackLength
    public void setLaneNumber(int laneNumber) { // IM THINKING LIKE USING THIS TO REMOVE THE PART IN CONSTRUCTOR
        this.laneNumber = laneNumber;
        lanes.clear(); //reset arraylist just in casse of previous effect
        for (int i = 0; i < this.laneNumber; i++) {
            lanes.add(null);
        }
    } // END setTrack

    // This method sets the lane number for non-initial run
    // 
    public void safeSetLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
        List<Horse> oldHorses = new ArrayList<>(lanes); // make a copy of the old horses
        lanes.clear(); //reset arraylist just in casse of previous effect
        for (int i = 0; i < this.laneNumber; i++) {
            lanes.add(null);
        }
        for (int i = 0; i < oldHorses.size(); i++) {
            if (i < this.laneNumber) {
                lanes.set(i, oldHorses.get(i)); // add the old horse to the new lane
            }
        }
        if (oldHorses.size() > this.laneNumber) {
            JOptionPane.showMessageDialog(null, "There are more horses than lanes. Some horses will be removed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } // END safeSetLaneNumber

    // This method sets the weather condition
    // 
    public void setWeather(String weather) {
        this.weather = weather;
    } // END setWeather

    // This method sets the shape of track
    //
    public void setTrackShape(String trackShape) {
        this.trackShape = trackShape;
    } // END setTrackShape

    // GUI getters

    // This method returns track length
    //
    public int getTrackLength() {
        return this.raceLength;
    } // END getTrackLength

    // This method returns lane number
    //
    public int getLaneNumber() {
        return this.laneNumber;
    } // END getLaneNumber

    // This method returns the weather condition
    //
    public String getWeather() {
        return this.weather;
    } // END getWeather

    // This method returns the track shapes
    //
    public String getTrackShape() {
        return this.trackShape;
    } // END getTrackShape

    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    // method if someone wants to assign horse to a specific line
    //
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber < 0 || laneNumber >= lanes.size()) {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
            return;
        }
        if (lanes.get(laneNumber) != null)
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is already a horse in that lane");
            return;
        }
        else {
            lanes.set(laneNumber, theHorse);
        }
    } // END addHorse

    // overload the method
    //
    public void addHorse(Horse theHorse)
    {
        for (int i = 0; i < lanes.size(); i++) {
            if (lanes.get(i) == null) {
                lanes.set(i, theHorse);
                return;
            }
        }
        System.out.println("unavailable lane for horse " + theHorse.getName());
    } // END addHorse
    
    // methods to reset distance before running
    //
    public void resetAllHorses() {
        for (Horse h : lanes) {
            if (h != null) {
                h.goBackToStart();
            }
        }
    } // END resetAllHorses

    // methods to move horses forward
    // 
    public void moveAllHorses() {
        for (Horse h : lanes) {
            if (h != null) {
                moveHorse(h);
            }
        }
    } // END moveAllHorses

    // methods to reset fallen status
    //
    public void riseAllHorses() {
        for (Horse h : lanes) {
            if (h != null) {
                h.rise();
            }
        }
    } // END riseAllHorses

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     * @param rp the racepanel created in another class
     * @param rb the bet initalised in another panel
     */
    public void startRace(RacePanel rp, RaceBet rb)
    {

        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        long raceStartTime = System.currentTimeMillis();
        long raceEndTime = 0; // Initialize with a default valu

        resetAllHorses();
        riseAllHorses();

        while (!finished)
        {
            moveAllHorses();

            SwingUtilities.invokeLater(() -> rp.UpdateScreen()); // EDT
            
            //if any of the three horses has won the race is finished
            for (Horse h: lanes) {
                if (h!= null && raceWonBy(h)) {
                    finished = true;
                    raceEndTime = System.currentTimeMillis();
                    break;
                }
            }

            if (!checkLosers()) {
                finished = true;
                raceEndTime = System.currentTimeMillis();
                System.out.println("All horses have fallen!");
            }
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
                // TimeUnit.MILLISECONDS.sleep(16); // lemme test if this makes it 60fps
            }catch(Exception e){}

        }

        double raceDuration = ((double)(raceEndTime - raceStartTime)) / 1000.0; // in seconds
        addRecord();
        rb.archiveResult();
        totalIncome += rb.returnIncome();
        JOptionPane.showMessageDialog(null, "Net Balance: " + totalIncome);
    } // END startRace

    // the method to add Record to track Horse performance
    //
    public void addRecord () {
        for (Horse h : lanes) {
            if (h != null) {
                // Create a new HorsePerformance object
                HorsePerformance hp = new HorsePerformance(LocalDate.now().toString(), raceLength+trackShape, weather, h.getDistanceTravelled(), h);
                // Add the performance record to the horse
                hp.setFell(h.hasFallen());
                h.stats.addPerformance(hp, raceWonBy(h), h);
            }
        }
    } // END addRecord

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        double moveForwardProbability = theHorse.getConfidence(); // default move forward probability
        double fallProbability = 0.1 * theHorse.getConfidence() * theHorse.getConfidence(); // default fall probability

        // variables for position and relative position
        int pos = theHorse.getDistanceTravelled();
        double relativePos = (double)(pos) / raceLength; // relative position to the middle of the track

        // Adjust probabilities based on weather
        // using case for >1 cases of a variable
        switch (weather) {
            case "Muddy":
                moveForwardProbability *= 0.8; // 20% less likely to move forward
                fallProbability *= 1.2; // 20% more likely to fall
                break;
            case "Icy":
                moveForwardProbability *= 0.5; // 50% less likely to move forward
                fallProbability *= 1.5; // 50% more likely to fall
                break;
            case "Rainy":
                moveForwardProbability *= 0.7; // 30% less likely to move forward
                fallProbability *= 1.3; // 30% more likely to fall
                break;
            case "Dry":
                // No change in probabilities
                break;  
            default:
                break;
        }

        // Adjust probabilities based on track shape
        switch (trackShape) {
            /*
             * The track shape is a string that can be "Oval", "Straight", "Figure Eight", or "Circular"
             * In this case, it is a figure-eight, which is like a infinity shape,
             * Given the fact the horses do slow down in both turns and in the intersection point
             * We apply slow down effect to the horses by tuning down the probability of moving forward
             * However they have different extent on lowering speed, specifically slower in intersection and only slightly lower in turns
             * we therefore apply different effect on the horses
             * The effect is 15% slower in turns and 25% slower in intersection
             * The intersection is essentially the midpoint = raceLength/2
             * the turns are the 25% and 75% of the raceLength
             * but they does not happen exactly there so we do it +- 10% of the raceLength
             */
            case "Figure Eight":
                if (Math.abs(relativePos - 0.5) < 0.1) { // intersection point
                    moveForwardProbability *= 0.75; // 25% less likely to move forward
                    fallProbability *= 1.25; // 25% more likely to fall
                } else if (Math.abs(relativePos - 0.25) < 0.1 || Math.abs(relativePos - 0.75) < 0.1) { // turns
                    moveForwardProbability *= 0.85; // 15% less likely to move forward
                    fallProbability *= 1.15; // 15% more likely to fall
                }
                break;
            case "Circular":
                // Assuming circular track, we can apply a different effect
                // For example, we can make it 10% less likely to move forward
                moveForwardProbability *= 0.9; // 10% less likely to move forward
                fallProbability *= 1.1; // 10% more likely to fall
                break;
            default:
                // No change in probabilities for other shapes
                break;
        }
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < moveForwardProbability)
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < fallProbability)
            {
                theHorse.fall();
            }
        }
    } // END moveHorse


        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    public boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= raceLength && theHorse != null)
        {
            theHorse.addConfidence(0.05);
            return true;
        }
        else
        {
            return false;
        }
    } // END raceWonBy
    
    // This method returns the list of winner(s)
    //
    public ArrayList<String> getWinners() {
        ArrayList<String> winners = new ArrayList<>();
        for (Horse h : lanes) {
            if (h == null) {
                continue; // skip null horses
            }
            if (raceWonBy(h)) {
                winners.add(h.getName());
            }
        }
        return winners;
    } // END getWinners

    // This method checks whether all horses have fallen
    // It returns false when all fallen
    //
    public boolean checkLosers() {
        for (Horse h : lanes) {
            if (h!=null && !h.hasFallen()) {
                return true; // at least one not fallen
            }
        }
        return false; // all horses have fallen
    } // END checkLosers

    // return the list of statistics of all horses
    //
    public ArrayList<String> getHorseStatistics () {
        ArrayList<String> horseStats = new ArrayList<>();
        for (Horse h : lanes) {
            horseStats.add(h.getName() + " - Distance: " + h.getDistanceTravelled() + ", Confidence: " + String.format("%.2f", h.getConfidence()));
        }
        return horseStats;
    } // END getHorseStatistics

    /***
     * Print the race on the terminal
     */
    // This method is not used in part 2
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        for (Horse h : lanes) {
            printLane(h);
            System.out.println();
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    // Also not used in this part
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
        System.out.println(" " + theHorse.getName().toUpperCase() + " (Current confidence " + String.format("%.2f", theHorse.getConfidence()) + ")");
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    // For Part 1 not Part 2
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    public ArrayList<Horse> returnLanes () {
        return new ArrayList<>(lanes);
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome += totalIncome;
    }

    // public RaceBet getRaceBet() {
    //     return raceBet;
    // }
}
