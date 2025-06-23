# Horse Race Simulator
A Java0based horse race simulation with both textual and graphical interfaces. User can simulate races, place bets and view statistics

## Directory Structure
```
HorseRaceSimulator/
│
├── .git/             # (hidden) Git Repo
├── Part1/            # Text-based simulation
└── Part2/            # Graphical version

```

## Dependencies
- Java 8 or newer
- No external libraries required(All used are included in Java standard libraries)
- Compiles and runs from command line. Not necessary to use IDE

## Setup Instructions
1. If you do not have the folder, start with:
```bash
git clone https://github.com/AndyLiu2796/HorseRaceSimulator
cd HorseRaceSimulator
```

2. Compiling
For Part 1(Text)
```
cd Part1
javac Part1\*.java
```

For Part 2(GUI)
```
cd Part2
javac Part2\*.java
```

3. Run
Text
```
# Note that there are some preset values that you can change
java Part1.Test
```

In Test.java
```
public static void race() {

	Race r = new Race(20,3); // Change the first paramter for laneLength and second for laneNumber
	
	Horse h1 = new Horse('@',"an",0.9);
	
	Horse h2 = new Horse('^',"bn",0.9);
	// U don't need to fill in all lanes here
	
	// Horse h3 = new Horse('&',"cn",0.9);
	
	r.addHorse(h1,0);
	
	r.addHorse(h2,1);
	
	// r.addHorse(h3,2);
	
	r.startRace();

}
```

GUI
```
java RaceGUI.startRaceGUI()
// OR
java Part2.RaceGUI;
```

1. Look at the top panel, adjust the values to your liking
	1. Then press the left button below "Weather & Track Condition"
	2. the right button is for (changing the values and you want to preserver the horses you added)
	3. for the shape, the figure-eight shape is currently flawed due to some mathematic formula issues. It looks weird but it does run properly in the background.(Others are fine)
2. Click the top-left Options
	1. Click horse Panel to start adding Horses
	2. choose the values to your liking
		1. You can use emojis/any character as symbol
		2. Confidence should be between 0 and 1. You can enter exceeding values but it will be set to be within the range
		3. Click addHorse
		4. You can keep adding Horse until the lanes are full or leaving some lanes empty are completely fine
		5. Don't use same name of Horse Name
3. Click the Open Betting in Options
	1. It is normal to appear "Horse Not Found" because you have not betted yet
	2. please resize the frame horizontally wider or else some components are hidden
	3. On the top of the Betting Frame, choose the horse you desire and enter the bet amount
	4. Click Confirm Bet and it will appear your betamount and odds and expected winnings
	5. Click show stats if you want to see the betting statistics on all horses and your total balance
	6. the bottom shows the stats for one horse
	7. If you desire to see the change of odds after changing track condition(1.2), click refresh in this frame
4. If you want to view the statistics of the horses you added, click Horse Stats in Option, choose the respective horse and click show stats
5. Click the button at the bottom of the main frame to start the game
	1. the program notifies you your net balance at the end of match
		1. You start with 0, you can bet any number on any horses you want
			1. if your horse win you get the money if your horse lose you also get the money
			2. technically you can hedge by investing in all horses
			3. the amount you win minus that you lose will be the net change this match which will be added to the balance where you can check
6. click the Open statistics in the option
	1. The top left is the statistics of last match with date, time and win ratio
	2. center is the overall stats with total matches and wins
	3. rightmost is the best time for each type of track(denoted by (length)(shape)(condition))
	4. Bottom is the historical statistics of all matches it performs
	5. Note that the load and save functions are not implemented, you can keep restarting the match in this session and keep your stats but they are gone once you terminate the program
