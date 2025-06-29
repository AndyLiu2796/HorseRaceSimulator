package Part2;

import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * Horse Class is the class that manages the object horses with its attributes' corresponding mutator and accessor methods
 * 
 * @author Ho Ming Liu
 * @version 23 April 2024
 */

public class Horse
{
    //Fields of class Horse
    private String horseName; // need encapsultion so might need private for the 5 attribute
    private String horseSymbol;
    private int horseDistance = 0;
    private double horseConfidence;
    private boolean horseFallen = false;

    // // Extra fields of the class Horse
    private String breed = "Thoroughbred"; // default breed
    private String coatColor = "Black"; // default coat color
    private String saddleType = "None"; // default saddle type
    private String shoesType = "None"; // default shoes type
    private String accessories = "No"; // default accessories
    private int speed = 1;


    // extra field for stats
    protected PerformanceStats stats = new PerformanceStats();
    public PerformanceStats getStats() { return stats; }

    public Horse(String horseSymbol, String horseName, double horseConfidence, String breed, String coatColor, String accessories, String horseshoes, String saddle) {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        setConfidence(horseConfidence);
        this.breed = breed;
        this.coatColor = coatColor;
        this.saddleType = saddle;
        this.shoesType = horseshoes;
        this.accessories = accessories;

        
        switch (saddle) {
            case "Standard":
                break;
            case "Lightweight":
                this.speed += 1;
                this.horseConfidence += 0.1; // Increase confidence by 10%
                break;
            case "Heavyweight":
                this.speed -= 1;
                this.horseConfidence -= 0.1; // Decrease confidence by 10%
                break;
            default:
                break;
        }

        switch (horseshoes) {
            case "Lightweight":
                this.speed += 1;
                break;
            case "Regular":
            default:
                break;
        }

        switch (accessories) {
            case "Bridle":
                this.horseConfidence += 0.05;
                break;
            case "Blanket":
            case "Hat":
            case "None":
            default:
                break;
        }

        if (this.speed <= 0) {
            this.speed = 1; // Ensure speed is at least 1
        }

        if (this.horseConfidence > 1.0) {
            this.horseConfidence = 1.0; // Ensure confidence doesn't exceed 1.0
        }
        if (this.horseConfidence < 0) {
            this.horseConfidence = 0.1; // Ensure confidence doesn't go below 0
        }
    }

    // Constructor with parameters
    public Horse(String horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = Filter.firstSymbol(horseSymbol);
        this.horseName = horseName;
        setConfidence(horseConfidence);
    }
    
    // empty horse constructor
    //
    public Horse () {
        this.horseSymbol = "";
        this.horseName = "";
        this.horseConfidence = -1;
    }
    
    //Other methods of class Horse

    // mutator method to make horse fall
    //
    public void fall()
    {
        this.horseFallen = true;
        this.horseConfidence -= 0.1; // AS DISCUSSED IN GROUP, THIS IS UNSURE WHETHER WHICH PART NEEDS THIS BUT DOESNT RELOADING CODE RESET IT?
        if (this.horseConfidence < 0) {
            this.horseConfidence = 0; // Ensure confidence doesn't go below 0
        } // MIGHT AS WELL JSUT USE MATH.MIN FOR IT
    }

    // mutator method to hose rise
    //
    public void rise() {
        this.horseFallen = false;
    } // END rise
    
    // accessor method to get confidence
    //
    public double getConfidence() { return this.horseConfidence; }
    // END getConfidence

    // mutator to change confidence
    //
    public void addConfidence(double c) {
        horseConfidence += c;
    } // END addConfidence
    
    // accessor method to get distance travelled
    //
    public int getDistanceTravelled() { 
        if (this != null) {
            return this.horseDistance;
        } else {
            return 0;
        }
    } // END getDistanceTravelled
    
    // accessor method to get name
    //
    public String getName() { return this.horseName; }
    // END getName

    // mutator method to set name
    //
    public void setName(String horseName) { this.horseName = horseName;}
    // END setName
    
    // accessor method to get symbol
    //
    public String getSymbol() { return this.horseSymbol; }
    // END getSymbol
    
    // mutator method to reset distance
    //
    public void goBackToStart() { this.horseDistance = 0; }
    // END goBackToStart
    
    // accessor method to get horseFallen status
    //
    public boolean hasFallen() { 
        if (this != null) {
            return this.horseFallen; // or throw an exception
        }
        return true;
    } // END hasFallen

    // mutator method to move horse forward by 1(default)
    //
    public void moveForward() { this.horseDistance+=speed;}
    // END moveForward

    // mutator method to set confidence with a given value
    //
    public void setConfidence(double newConfidence) {
        if (newConfidence <= 1.0 && newConfidence >= 0.0)
        {
            this.horseConfidence = newConfidence;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid input. Please check all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Confidence should be between 0.0 and 1.0");
        }
    } // END setConfidence
    
    // mutator method to set symbol
    // 
    public void setSymbol(String newSymbol){
        this.horseSymbol = Filter.firstSymbol(newSymbol);
    } // END setSymbol

    // accessor method for breed
    //
    public String getBreed() { return breed; }
    // END getBreed

    // mutator method for breed
    //
    public void setBreed(String breed) { this.breed = breed; }
    // END setBreed

    // accessor method that returns coat color
    //
    public String getCoatColor() { return coatColor; }
    // END getCoatColor

    // mutator method that sets coat color
    //
    public void setCoatColor(String coatColor) { this.coatColor = coatColor;}
    // END setCoatColor

    public String getAccessories() {return accessories;}

    public void setAccessories(String accessories) {this.accessories = accessories;}
    
    public int getSpeed() { return speed; }

    // mutator method for setting speed with input validation
    //
    public void setSpeed(int speed) {
        if (speed > 0) {
            this.speed = speed;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input. Please check all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Speed should be greater than 0");
        }
    } // END setSpeed

    public String getSaddleType() { return saddleType; }
    public void setSaddleType(String saddleType) { this.saddleType = saddleType; }

    public String getShoesType() { return shoesType; }
    public void setShoesType(String shoesType) { this.shoesType = shoesType; }
}
