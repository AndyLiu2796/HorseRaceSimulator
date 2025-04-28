package Part1;
public class Test {
    public static void main (String[] args) {
        // UNCOMMENT IF U WANT TO TRY THESE TEST METHODS YOURSELF
        // testMove();
        // testFall();
        // testConfidenceUpperBound();
        // testConfidenceLowerBound();
        race();
        
    }

    public static void testMove() {
        Horse h = new Horse('a', "TestHorse", 0.5);
        System.out.println("Initial distance: " + h.getDistanceTravelled());
        h.moveForward();
        System.out.println("Distance after moving forward: " + h.getDistanceTravelled());
        h.moveForward();
        System.out.println("Distance after moving forward again: " + h.getDistanceTravelled());
        h.goBackToStart();
        System.out.println("Distance after going back to start: " + h.getDistanceTravelled());
    }

    public static void testFall() {
        Horse h = new Horse('b', "TestHorse", 0.5);
        System.out.println("Initial fallen status: " + h.hasFallen());
        System.out.println("Initial confidence: " + h.getConfidence());
        h.fall();
        System.out.println("Fallen status after falling: " + h.hasFallen());
        System.out.println("Confidence after falling: " + h.getConfidence());
        h.getUp();
        System.out.println("Fallen status after getting up: " + h.hasFallen());
        System.out.println("Confidence after getting up: " + h.getConfidence());
        System.out.println("NB: Get up is a method made to reset the horse to the original state for replaying game");
    }

    public static void testConfidenceUpperBound() {
        Horse h = new Horse('d', "HighConf", 0.95);
        System.out.println("Initial confidence: " + h.getConfidence()); // Expected: 0.95
        h.setConfidence(2.0);
        System.out.println("Confidence after setConfidence(2.0): " + h.getConfidence()); // Expected: 1.0
    }

    public static void testConfidenceLowerBound() {
        Horse h = new Horse('e', "LowConf", 0.05);
        System.out.println("Initial confidence: " + h.getConfidence()); // Expected: 0.05
        h.setConfidence(-0.5);
        System.out.println("Confidence after setConfidence(-0.5): " + h.getConfidence()); // Expected: 0.0
    }

    public static void race() {
        Race r = new Race(20,3);
        Horse h1 = new Horse('@',"an",0.9);
        Horse h2 = new Horse('^',"bn",0.9);
        // Horse h3 = new Horse('&',"cn",0.9);
        r.addHorse(h1,0);
        r.addHorse(h2,1);
        // r.addHorse(h3,2);
        
        r.startRace();
    }


}