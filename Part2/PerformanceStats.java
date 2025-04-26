package Part2;

import java.util.*;

public class PerformanceStats {
    private int totalMatch;
    private int wins;
    private double bestTime;
    private Map<String, Double> bestTimeperTrack = new HashMap<>();
    private List<HorsePerformance> hperf = new ArrayList<>();
    private List<Double> confidenceList = new ArrayList<>();
    
    public PerformanceStats() {
        this.totalMatch = 0;
        this.wins = 0;
        this.bestTime = Double.MAX_VALUE; // Initialize to a large value
    }

    public void addPerformance(HorsePerformance perf, boolean win, Horse h) {
        hperf.add(perf);
        totalMatch++;
        if (win) { wins++; };


        // or else fall insta -> best time
        if (perf.getFinishingTime() < bestTime && perf.isFell() == false) {
            bestTime = perf.getFinishingTime();
            
        }
        String matchkey = perf.getTrackName() + perf.getTrackCondition();
        // bestTimeperTrack.merge(matchkey, perf.getFinishingTime(), Math::min);
        bestTimeperTrack.merge(matchkey, perf.getFinishingTime(), Math::min);
        // Update the confidence list
        confidenceList.add(h.getConfidence());  


    }

    public List getHorsePerformances() {
        return this.hperf;
    }

    public Map<String, Double> getBestTime () {
        return bestTimeperTrack;
    }

    public double getWinRatio() {
        return totalMatch == 0 ? 0 : (double) wins / totalMatch;
    }

    public HorsePerformance getLastMatch() {
        return hperf.get(hperf.size() - 1);
    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public int getWins() {
        return wins;
    }
}
