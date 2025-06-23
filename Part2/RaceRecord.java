package Part2;

/**
 * 
 * @author Ho Ming, Liu
 * @version 2.0
 */

 /**
  * RaceRecord class
  * This class creates the object to record the statistics of the horse in the race
  */

public class RaceRecord {
    private int finishTime;
    private boolean won;
    private double avgSpeed;

    /*
     * @param finishTime - time for the winning horse to end the race
     * @param won - whether the horse win the race
     * @param avgSpeed - average speed of the horse
     */
    public RaceRecord(int finishTime, boolean won, double avgSpeed) {
        this.finishTime = finishTime;
        this.won = won;
        this.avgSpeed = avgSpeed;
    }

    /*
     * Below are the object accessors and mutators
    */

    // This method returns finishTime
    //
    public int getFinishTime() {
        return finishTime;
    } // END getFinishTime

    // This method returns win status
    //
    public boolean isWon() {
        return won;
    } // END isWon

    // This method returns average speed
    //
    public double getAvgSpeed() {
        return avgSpeed;
    } // END getAvgSpeed

    // This method sets the finish time
    //
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    } // END setFinishTime

    // This method sets the win status
    //
    public void setWon(boolean won) {
        this.won = won;
    } // END setWon

    // This method sets the average speed of horse
    //
    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    } // END avgSpeed
}
