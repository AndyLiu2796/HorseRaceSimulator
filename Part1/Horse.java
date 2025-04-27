package Part1;
/**
 * Write a description of class Horse here.
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 */
public class Horse
{
    //Fields of class Horse
    private String horseName; // need encapsultion so might need private for the 5 attribute
    private char horseSymbol;
    private int horseDistance = 0;
    private double horseConfidence;
    private boolean horseFallen = false;
    
    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.setConfidence(horseConfidence);
    }
    
    // overload constructor
    public Horse () {
        this.horseSymbol = '\u0000';
        this.horseName = "";
        this.horseConfidence = -1;
    }
    
    //Other methods of class Horse

    // mutator method to make horse fall
    public void fall()
    {
        this.horseFallen = true;
        this.setConfidence(horseConfidence - 0.1);
    }

    // accessor method to get confidence
    public double getConfidence()
    {
        return this.horseConfidence;
    }
    
    // accessor method to get distance travelled
    public int getDistanceTravelled()
    {
        return this.horseDistance;
    }
    
    // accessor method to get name
    public String getName()
    {
        return this.horseName;
    }
    
    // accessor method to get symbol
    public char getSymbol()
    {
        return this.horseSymbol;    
    }
    
    // mutator method to reset distance
    public void goBackToStart()
    {
        this.horseDistance = 0;
    }
    
    // accessor method to get horseFallen status
    public boolean hasFallen() 
    {
        return this.horseFallen;
    }

    public void getUp()
    {
        this.horseFallen = false;
        setConfidence(this.horseConfidence);
    }

    // mutator method to move horse forward by 1(default)
    public void moveForward()
    {
        this.horseDistance++;
    }

    // mutator method to set confidence with a given value
    public void setConfidence(double newConfidence)
    {
        this.horseConfidence = Math.max(0.0, Math.min(newConfidence, 1.0));
    }
    
    // mutator method to set symbol
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
    
}
