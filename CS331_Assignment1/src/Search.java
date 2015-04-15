import java.util.*;

public class Search {
	
	private static final int MAX_DEPTH = Integer.MAX_VALUE;
	
	private State start;
	private State end;
	private State result;
	private int expand = 0;
	private boolean success = false;
	
	public Search(State start, State end, String searchType) throws Exception {
		
		this.start = start;
		this.end = end;
		
		if(!(start.validState() && end.validState())) {
			throw new Exception("Invalid start/end states");
		}
		
		switch(searchType) {		
			case("bfs"):
				bfs();
				break;
			case("dfs"):
				dfs(MAX_DEPTH);
				break;
			case("iddfs"):
				iddfs();
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
	
	private void iddfs() {
		for(int i = 1; i < MAX_DEPTH; i++) {
			if(!success) {
				dfs(i);
			}
			else {
				break;
			}
		}
	}
	
	private void dfs(int depth) {
		
		Stack<State> fringe = new Stack<State>();
		fringe.push(start);
		
		HashMap<String, State> closed = new HashMap<String, State>();
		
		for(;;) {
			
			if(fringe.isEmpty()) {
				result = null;
				break;
			}
			
			State temp = fringe.pop();
			
			if(temp.depth >= depth) {
				continue;
			}
			
			if(temp.equals(end)) {
				result = temp;
				success = true;
				return;
			}
			
			if((!closed.containsKey(temp.toString())) || closed.get(temp.toString()).depth > temp.depth) {				
				closed.put(temp.toString(), temp);
				expand++;
				for(State s : temp.succ()) {
					fringe.push(s);
				}
			}		
		}
	}
	
	private void bfs() {
		
		Queue<State> fringe = new LinkedList<State>();
		queueHelper(fringe);	
	}
	
	private void astar() {
		
		PriorityQueue<State> fringe = new PriorityQueue<State>(10, new Heuristic(end));
		queueHelper(fringe);
	}
	
	private void queueHelper(Queue<State> fringe) {
		
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
}
