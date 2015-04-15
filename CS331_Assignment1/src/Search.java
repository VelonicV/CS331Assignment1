import java.util.*;

public class Search {
	
	private static final int MAX_DEPTH = 100;
	
	private State start;
	private State end;
	private State result;
	private int expand = 0;
	private boolean success = false;
	
	public Search(State start, State end, String searchType) throws Exception {
		this.start = start;
		this.result = start;
		this.end = end;
		
		if(!(start.validState() && end.validState())) {
			throw new Exception("Invalid start/end states");
		}
		
		switch(searchType) {
			
			case("bfs"):
				bfs();
				break;
			case("dfs"):
				dfs();
				break;
			case("iddfs"):
				for(int i = 0; i < MAX_DEPTH; i++) {
					iddfs(i);
				}
				break;
			case("astar"):
				astar();
				break;
			default:
				throw new Exception("Invalid search type");
			
		}	
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public int getExpansions() {
		return expand;
	}
	
	public State getResult() {
		return result;
	}
	
	private void bfs() {
		
		Queue<State> fringe = new LinkedList<State>();
		fringe.add(start);
		
		Hashtable<String, State> closed = new Hashtable<String, State>();
		
		for(;;) {
			
			if(fringe.isEmpty()) {
				result = null;
				return;
			}
			
			State temp = fringe.remove();
			if(temp.equals(end)) {
				result = temp;
				success = true;
				return;
			}
			
			if(!closed.containsKey(temp.toString())) {				
				closed.put(temp.toString(), temp);
				expand++;
				for(State s : temp.succ()) {
					fringe.add(s);
				}
			}		
		}
	}
	
	private void dfs() {
		
		Stack<State> fringe = new Stack<State>();
		fringe.push(start);
		
		Hashtable<String, State> closed = new Hashtable<String, State>();
		
		for(;;) {
			
			if(fringe.isEmpty()) {
				result = null;
				return;
			}
			
			State temp = fringe.pop();
			if(temp.equals(end)) {
				result = temp;
				success = true;
				return;
			}
			
			if(!closed.containsKey(temp.toString())) {				
				closed.put(temp.toString(), temp);
				expand++;
				for(State s : temp.succ()) {
					fringe.push(s);
				}
			}		
		}
		
	}
	
	private void iddfs(int limit) {
		
		Stack<State> fringe = new Stack<State>();
		fringe.push(start);
		
		HashMap<String, State> closed = new HashMap<String, State>();
		
		for(;;) {
			
			if(fringe.isEmpty()) {
				result = null;
				break;
			}
			
			State temp = fringe.pop();
			
			if(temp.depth() >= limit) {
				continue;
			}
			
			if(temp.equals(end)) {
				result = temp;
				success = true;
				return;
			}
			
			if(!closed.containsKey(temp.toString())) {				
				closed.put(temp.toString(), temp);
				expand++;
				for(State s : temp.succ()) {
					fringe.push(s);
				}
			}		
		}
		
		closed.clear();
		
	}
	
	private void astar() {
		
		PriorityQueue<State> fringe = new PriorityQueue<State>(10, new Heuristic(end));
		fringe.add(start);
		
		Hashtable<String, State> closed = new Hashtable<String, State>();
		
		for(;;) {
			
			if(fringe.isEmpty()) {
				result = null;
				break;
			}
			
			State temp = fringe.remove();
			
			if(temp.equals(end)) {
				result = temp;
				success = true;
				return;
			}
			
			if(!closed.containsKey(temp.toString())) {				
				closed.put(temp.toString(), temp);
				expand++;
				for(State s : temp.succ()) {
					fringe.add(s);
				}
			}		
		}
		
	}
	
}
