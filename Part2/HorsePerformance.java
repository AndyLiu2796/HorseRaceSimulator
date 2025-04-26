package Part2;

public class HorsePerformance {
    private String raceDate;
    private String trackName;
    private String trackCondition;
    private double finishingTime;
    private Horse horse;

    public HorsePerformance(String raceDate, String trackName, String trackCondition, double finishingTime, Horse horse) {
        this.raceDate = raceDate;
        this.trackName = trackName;
        this.trackCondition = trackCondition;
        this.finishingTime = finishingTime;
        this.horse = horse;
    }

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
    public Horse getHorse() {
        return horse;
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
    public void setHorse(Horse horse) {
        this.horse = horse;
    }
    
}
