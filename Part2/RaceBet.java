package Part2;

import java.util.*;
import javax.swing.JOptionPane;

/**
 * 
 * @author Ho Ming, Liu
 * @version 2.0
 */

 /**
  * RaceBet class- The class responsible for betting horses before race
  * This class manages the betting function of the simulation
  */
public class RaceBet {
    private Race race;
    private double totalWinScore;
    private double totalBetAmount = 0.0;
    private Map<Horse, HorseBet> horseBetMap = new HashMap<>();
    private List<PerMatchBetRecord> matchIncomes = new ArrayList<>();

    
    /* This constructor initializes the bet for every horse
     * 
     * 
     */
    public RaceBet (Race race) {
        this.race = race;
        for (Horse h : race.returnLanes()) {
            if (h != null) {
                HorseBet horseBet = new HorseBet(h, 0, 0);
                horseBet.setOdds(calcOdd(h));
                horseBetMap.put(h, horseBet);
                totalWinScore += calcScore(h);
            }
            
        }
    }

    // This method calculates the odd of each horse according to track conditions
    //
    public double calcOdd(Horse h) {
        if (horseBetMap.get(h) == null) {
            JOptionPane.showMessageDialog(null, "Horse not found.");
            return 0;
        }
        if (h == null) {
            return 0;
        }
        double factor = 1.0;

        switch (race.getWeather()) {
            case "Muddy":
                factor = 0.8;
                break;
            case "Dry":
                factor = 1.0;
                break;
            case "Icy":
                factor = 0.7;
                break;
            case "Rainy":
                factor = 0.5;
                break;
            default:
                factor = 1.0;
                break;
        }

        double winscore = 0.5 * h.getStats().getWinRatio() + 0.1 * h.getConfidence() + 0.2 * factor + 0.2 * h.getSpeed();
        double probability = winscore / totalWinScore;
        double baseodds = 1.0 / probability;
        double ratio = horseBetMap.get(h).getBetAmount() / (totalBetAmount + 1);
        double finalOdds = baseodds * (1 + 0.3*(1 - ratio));
        if (finalOdds < 1) {
            finalOdds = 1;
        }
        return Double.parseDouble(String.format("%.2f", finalOdds));
    } // END calcOdd

    // This method calculates the win score of the horse
    //
    public double calcScore(Horse h) {
        if (h == null) {
            return 0;
        }
        double winscore = 0.5 * h.getStats().getWinRatio() + 0.1 * h.getConfidence() + 0.2 * h.getSpeed();
        return winscore;
    } // END calcScore

    /* 
     * @param amount - The amount of bet user want to add
     * @param h - The horse the user want to bet on
     */
    // This method allows user to place Amount
    //
    public void placeAmount (double amount, Horse h) {
        if (h == null) {
            JOptionPane.showMessageDialog(null, "Horse not found.");
            return;
        }
        if (amount < 0) {
            JOptionPane.showMessageDialog(null, "Bet amount cannot be negative.");
        }
        if (horseBetMap.containsKey(h)) {
            HorseBet horseBet = horseBetMap.get(h);
            // refresh once just in case;
            horseBet.setOdds(calcOdd(h));
            
            if (JOptionPane.showConfirmDialog(null, "Bet amount for " + h.getName() + " is set to " + amount, "Bet Amount", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                horseBet.setBetAmount(amount);
                totalBetAmount += amount;
                JOptionPane.showMessageDialog(null, ("Bet amount for " + h.getName() + " is set to " + amount + "Expected winning is: " + (amount * horseBet.getOdds())));
            } else {
                JOptionPane.showMessageDialog(null, "Bet amount not set.");
            }
        } 
    } // END placeAmount

    // This method returns the overall net change of the bets in the race
    //
    public double returnIncome() {
        double newIncome = 0.0;
        for (HorseBet hb : getHorseBets()) {
            if (hb.getHorse() != null) {
                if (race.raceWonBy(hb.getHorse())) {
                    newIncome += (hb.getBetAmount() * hb.getOdds());
                } else {
                    newIncome -= hb.getBetAmount();
                }
            }
        }
        return newIncome;
    } // END returnIncome

    // This method archives the result for record
    //
    public void archiveResult() {
        PerMatchBetRecord record = new PerMatchBetRecord(totalBetAmount, returnIncome());
        matchIncomes.add(record);
        totalBetAmount = 0; // reset or else the betamount adds up every match
    } // END archiveResult

    // This method return the list of incomes every round
    //
    public List<PerMatchBetRecord> getMatchIncomes() {
        return matchIncomes;
    } // END getMatchIncomes

    // This method returns the bet of the horse
    //
    public Collection<HorseBet> getHorseBets() {
        return horseBetMap.values();
    } // END getHorseBets
}
