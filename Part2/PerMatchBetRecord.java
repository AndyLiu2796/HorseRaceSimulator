package Part2;

/**
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 * PerMatchBetRecord class 
 * This is the class responsbile for bettings each match
 */

public class PerMatchBetRecord {
    public double totalBetAmount;
    public double netResult;

    public PerMatchBetRecord(double totalBetAmount, double netResult) {
        this.totalBetAmount = totalBetAmount;
        this.netResult = netResult;
    }
}
