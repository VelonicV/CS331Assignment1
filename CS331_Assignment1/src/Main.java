import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

// Name: David Bereza, Aden Petersen
// Class: CS331
// Date: 4/12/15
// Assignment: 1

public class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Check if all arguments are entered.
		if(args.length != 4) {
			printHelp();
			return;
		}
		
		try {
			
			//Attempt to open and convert files to start and end states.
			
			//String init = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
			//String goal = new Scanner(new File(args[1])).useDelimiter("\\Z").next();
			String init = new Scanner(new File("start2.txt")).useDelimiter("\\Z").next();
			String goal = new Scanner(new File("goal2.txt")).useDelimiter("\\Z").next();
			
			String[] initVals = init.split(",|\n");
			String[] goalVals = goal.split(",|\n");
			
			if(initVals.length != 6 || goalVals.length != 6) {
				System.out.println("invalid start/goal file");
				return;
			}
					
			State start = new State(Integer.parseInt(initVals[3]), Integer.parseInt(initVals[4]),
					Integer.parseInt(initVals[5]), Integer.parseInt(initVals[0]),
					Integer.parseInt(initVals[1]), Integer.parseInt(initVals[2]),
					null, 0);
			
			State end = new State(Integer.parseInt(goalVals[3]), Integer.parseInt(goalVals[4]),
					Integer.parseInt(goalVals[5]), Integer.parseInt(goalVals[0]),
					Integer.parseInt(goalVals[1]), Integer.parseInt(goalVals[2]),
					null, 0);
			
			//Run the search with the given states and algorithm.
			
			//Search search = new Search(start, end, args[2]);
			Search search = new Search(start, end, "iddfs");
			if(!search.getSuccess()) {
				System.out.println("no solution found");
				return;
			}
			
			//Traverse and print steps and counters;
			Stack<State> result = new Stack<State>();
			State temp = search.getResult();
			
			while(temp != null) {
				result.push(temp);
				temp = temp.parent;
			}
			
			int count = 0;
			String output = "";	
			
			while(!result.isEmpty()) {
				output += result.pop().toString() + "\n";
				count++;
			}
			
			output += "Number of steps: " + count + "\n";			
			output += "Number of expansions: " + search.getExpansions() + "\n";
			
			System.out.println(output);
			PrintWriter outFile = new PrintWriter(args[3]);
			outFile.println(output);
			outFile.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void printHelp() {
		System.out.println("Parameters: < initial state file > < goal state file > < mode > < output file >");
	}
	
}
