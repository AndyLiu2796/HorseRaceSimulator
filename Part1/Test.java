package Part1;
public class Test {
    public static void main (String[] args) {
        
        Race r = new Race(20,3);
        Horse h1 = new Horse('@',"an",0.9);
        Horse h2 = new Horse('^',"bn",0.9);
        Horse h3 = new Horse('&',"cn",0.9);
        r.addHorse(h1,0);
        r.addHorse(h2,1);
        r.addHorse(h3,2);
        
        r.startRace();
    }
}