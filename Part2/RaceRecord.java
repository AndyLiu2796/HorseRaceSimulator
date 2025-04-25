package Part2;


public class RaceRecord {
    private int finishTime;
    private boolean won;
    private double avgSpeed;

    public RaceRecord(int finishTime, boolean won, double avgSpeed) {
        this.finishTime = finishTime;
        this.won = won;
        this.avgSpeed = avgSpeed;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public boolean isWon() {
        return won;
    }
    public double getAvgSpeed() {
        return avgSpeed;
    }
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    public void setWon(boolean won) {
        this.won = won;
    }
    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }
    
}
