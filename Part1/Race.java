package Part1;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class Race
{
    private int raceLength;
    private List<Horse> lanes = new ArrayList<>();

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the laness
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int numLanes)
    {
        // initialise instance variables
        raceLength = distance;
        for (int i = 0; i < numLanes; i++) {
            lanes.add(null);
        }
    }
    // {
    //     // initialise instance variables
    //     raceLength = distance;
    //     // for (Horse h : lanes) {
    //     //     h = null;
    //     // }
    // }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (lanes.get(laneNumber) != null)
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is already a horse in that lane");
            return;
        }
        else {
            lanes.set(laneNumber, theHorse);
        }
    }

    // overload the method
    public void addHorse(Horse theHorse)
    {
        for (int i = 0; i < lanes.size(); i++) {
            if (lanes.get(i) == null) {
                lanes.set(i, theHorse);
                return;
            }
        }
        System.out.println("unavialable lane for horse " + theHorse.getName());
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        while (repeat) {
            finished = false;
            //reset all the lanes (all horses not fallen and back to 0). 
            for (Horse h : lanes) {
                h.goBackToStart();
                h.getUp(); // reset the horse's fallen status
            }

            while (!finished)
            {
                //move each horse
                for (Horse h : lanes) {
                    moveHorse(h);
                }
                // moveHorse(lane1Horse);
                // moveHorse(lane2Horse);
                // moveHorse(lane3Horse);
                            
                //print the race positions
                printRace();
                
                //if any of the three horses has won the race is finished
                for (Horse h: lanes) {
                    if (raceWonBy(h)) {
                        finished = true;
                        break;
                    }
                }
                // if ( raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse) )
                // {
                //     finished = true;
                    
                // }
                if (!checkLosers()) {
                    finished = true;
                    System.out.println("All horses have fallen!");
                }
            
                //wait for 100 milliseconds
                try{ 
                    TimeUnit.MILLISECONDS.sleep(100);
                    // TimeUnit.MILLISECONDS.sleep(16); // lemme test if this makes it 60fps
                }catch(Exception e){}

            }
            // print the winners
            if (getWinners().size() == 0) {
                System.out.println("No winners!");
            }
            else if (getWinners().size() == 1) {
                System.out.print("Winner: ");
                System.out.println(getWinners().get(0) + " won!");
            }
            else {
                System.out.print("Winners: ");
                System.out.println(String.join(" + ", getWinners()) + " won!");
            }
            // ask if the user wants to repeat the game
            System.out.println("Do you want to repeat the game? (y/n)");
            String input = scanner.nextLine().trim().toLowerCase();
            while (!input.equals("y") && !input.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                input = scanner.nextLine().trim().toLowerCase();
            }

            if (input.equals("n")) {
                repeat = false;
            }
        }
        
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= raceLength)
        {
            if (theHorse.getConfidence() < 0.85) {
                theHorse.setConfidence(theHorse.getConfidence() + 0.1);
            }
             // increase confidence by 0.1
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public ArrayList<String> getWinners() {
        ArrayList<String> winners = new ArrayList<>();
        for (Horse h : lanes) {
            if (raceWonBy(h)) {
                winners.add(h.getName());
            }
        }
        return winners;
    }

    // This method checks whether all horses have fallen
    // It returns false when all fallen
    public boolean checkLosers() {
        for (Horse h : lanes) {
            if (!h.hasFallen()) {
                return true; // at least one not fallen
            }
        }
        return false; // all horses have fallen
    }

    /***
     * Print the race on the terminal
     */
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
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
