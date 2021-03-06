import java.util.ArrayList;

public class State implements Comparable<State>{
	
	public int rMiss, rCan, rBoat, lMiss, lCan, lBoat, depth;
	public State parent;
	
	public State(int rMiss, int rCan, int rBoat, int lMiss, int lCan, int lBoat, State parent, int depth) {
		
		this.rMiss = rMiss;
		this.rCan = rCan;
		this.rBoat = rBoat;
		this.lMiss = lMiss;
		this.lCan = lCan;
		this.lBoat = lBoat;
		this.parent = parent;
		this.depth = depth;
	}
	
	//Return list of all possible successors.
	public ArrayList<State> succ() {
		
		ArrayList<State> potentialStates = new ArrayList<State>();
		
		//Right-side boat
		if(rBoat == 1) {			
			//One missionary
			potentialStates.add(new State(rMiss - 1, rCan, 0, lMiss + 1, lCan, 1, this, depth + 1));
			
			//Two missionaries
			potentialStates.add(new State(rMiss - 2, rCan, 0, lMiss + 2, lCan, 1, this, depth + 1));
		
			//One Cannibal
			potentialStates.add(new State(rMiss, rCan - 1, 0, lMiss, lCan + 1, 1, this, depth + 1));
		
			//Two Cannibals
			potentialStates.add(new State(rMiss, rCan - 2, 0, lMiss, lCan + 2, 1, this, depth + 1));
		
			//One and One
			potentialStates.add(new State(rMiss - 1, rCan - 1, 0, lMiss + 1, lCan + 1, 1, this, depth + 1));			
		}
		//Left-side boat
		else {	
			//One missionary
			potentialStates.add(new State(rMiss + 1, rCan, 1, lMiss - 1, lCan, 0, this, depth + 1));
	
			//Two missionaries
			potentialStates.add(new State(rMiss + 2, rCan, 1, lMiss - 2, lCan, 0, this, depth + 1));
			
			//One Cannibal
			potentialStates.add(new State(rMiss, rCan + 1, 1, lMiss, lCan - 1, 0, this, depth + 1));
		
			//Two Cannibals
			potentialStates.add(new State(rMiss, rCan + 2, 1, lMiss, lCan - 2, 0, this, depth + 1));

			//One and One
			potentialStates.add(new State(rMiss + 1, rCan + 1, 1, lMiss - 1, lCan - 1, 0, this, depth + 1));
		}
		
		//Check each state for validity before returning.
		ArrayList<State> validStates = new ArrayList<State>();
		for(State s : potentialStates) {
			if(s.validState()) {
				validStates.add(s);
			}
		}
		
		return validStates;
		
	}
	
	@Override 
	public String toString() {		
		return lMiss + "," + lCan + "," + lBoat + "\n"
			 + rMiss + "," + rCan + "," + rBoat + "\n";		
	}
	
	@Override
	public boolean equals(Object state) {
		
		if (state instanceof State) {
			
			State temp = (State) state;
			
			if((temp.rMiss == this.rMiss) && 
					(temp.rCan == this.rCan) && 
					(temp.rBoat == this.rBoat) && 
					(temp.lMiss == this.lMiss) && 
					(temp.lCan == this.lCan) && 
					(temp.lBoat == this.lBoat)) {
				
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}		
	}
	
	public boolean validState() {
		
		if((rMiss < 0) || (rCan < 0) || (lMiss < 0) || (lCan < 0)) {
			return false;
		}
		else if ((lCan > lMiss) && (lMiss > 0)) {
			return false;
		}
		else if ((rCan > rMiss) && (rMiss > 0)) {
			return false;
		}
		else if(!(((lBoat == 1) && (rBoat == 0)) || ((lBoat == 0) && (rBoat == 1)))) {
			return false;
		}
		
		return true;
	}
	
	public int compareTo(State state) {
		if(this.depth > state.depth) {
			return -1;
		}
		else if(this.depth < state.depth) {
			return 1;
		}
		else {
			return 0;
		}
	}
}

