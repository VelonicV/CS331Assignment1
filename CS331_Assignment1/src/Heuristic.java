import java.util.Comparator;

public class Heuristic implements Comparator<State> {
	
	private State goal;
	
	public Heuristic(State goal) {
		this.goal = goal;
	}
	
	@Override
	public int compare(State x, State y) {
		
		int xDiff = Math.abs(goal.rMiss - x.rMiss)
				+ Math.abs(goal.rCan - x.rCan)
				+ Math.abs(goal.lMiss - x.lMiss)
				+ Math.abs(goal.lCan - x.lCan);
		
		int yDiff = Math.abs(goal.rMiss - y.rMiss)
				+ Math.abs(goal.rCan - y.rCan)
				+ Math.abs(goal.lMiss - y.lMiss)
				+ Math.abs(goal.lCan - y.lCan);
		
		return xDiff - yDiff;
		
	}
	
}
