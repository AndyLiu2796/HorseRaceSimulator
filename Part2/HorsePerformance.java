package Part2;

/**
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 * HorsePerformance class 
 * This is the class responsbile for oerformance on an individual horse in one race
 */
public class HorsePerformance {
    private String raceDate;
    private String trackName;
    private String trackCondition;
    private double finishingTime;
    private boolean fell;
    private Horse h;

    public HorsePerformance(String raceDate, String trackName, String trackCondition, double finishingTime, Horse horse) {
        this.raceDate = raceDate;
        this.trackName = trackName;
        this.trackCondition = trackCondition;
        this.finishingTime = finishingTime;
        this.h = horse;
    }

    /*
     * mutator and accessor methods for HorsePerformance
     */

    public String getRaceDate() {
        return raceDate;
    }
    public String getTrackName() {
        return trackName;
    }
    public String getTrackCondition() {
        return trackCondition;
    }
    public double getFinishingTime() {
        return finishingTime;
    }
    public boolean isFell() {
        return fell;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public void setTrackCondition(String trackCondition) {
        this.trackCondition = trackCondition;
    }
    public void setFinishingTime(double finishingTime) {
        this.finishingTime = finishingTime;
    }
    public void setFell(boolean fell) {
        this.fell = fell;
    }

    // Overiding cuz the default method only returns hashcode or something like reference
    @Override
    public String toString() {
        return "Name: " + h.getName() + ", Date: " + raceDate + ", Track: " + trackName + ", Condition: " + trackCondition + ", Time: " + finishingTime;
    }


}
