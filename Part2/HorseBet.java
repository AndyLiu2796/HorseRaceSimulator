package Part2;

import javax.swing.JOptionPane;

/**
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 * HorseBet class 
 * This is the class responsbile for bettings on an individual horse
 */
public class HorseBet {
    private Horse h;
    private double betAmount = 0;
    private double odds;

    public HorseBet (Horse h, double betAmount, double odds) {
        this.h = h;
        this.betAmount = betAmount;
        this.odds = odds;
    }

    public Horse getHorse() {
        return h;
    }
    public double getBetAmount() {
        return betAmount;
    }
    public double getOdds() {
        return odds;
    }

    public void setHorse(Horse h) {
        this.h = h;
    }
    public void setBetAmount(double betAmount) {
        if (betAmount < 0) {
            JOptionPane.showMessageDialog(null, "Bet amount cannot be negative.");
        }
        this.betAmount = betAmount;
    }
    public void setOdds(double odds) {
        if (odds < 0) {
            JOptionPane.showMessageDialog(null, "Odds cannot be negative.");
        }
        this.odds = odds;
    }

    



}
