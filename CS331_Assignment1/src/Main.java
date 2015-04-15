import java.io.File;
import java.util.Stack;

// Name: David Bereza, Aden Petersen
// Class: CS331
// Date: 4/12/15
// Assignment: 1

public class Main {
	
	public static void main(String[] args) {
		
		//Check if all arguments are entered.
		if(args.length != 4) {
			printHelp();
			return;
		}
		
		//File start = new File(args[0]);
		//File end = new File(args[1]);
		//File output = new File(args[3]);
		
		try {
			State start = new State(3, 3, 1, 0, 0, 0, null, 0);
			State end = new State(0, 0, 0, 3, 3, 1, null, 0);
			
			Search search = new Search(start, end, "bfs");
			if(!search.getSuccess()) {
				System.out.println("no solution found");
				return;
			}
						
			Stack<State> result = new Stack<State>();
			State temp = search.getResult();
			
			while(temp != null) {
				result.push(temp);
				temp = temp.parent;
			}
			
			int count = 0;
			
			while(!result.isEmpty()) {
				System.out.println(result.pop());
				//result.pop();
				count++;
			}
			
			System.out.println("Result: " + search.getSuccess());
			System.out.println("Number of steps: " + count);			
			System.out.println("Number of expansions: " + search.getExpansions());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void printHelp() {
		System.out.println("\t< initial state file > < goal state file > < mode > < output file >");
	}
	
}
