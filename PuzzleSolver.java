package cs411_hw3;

// Emanuil Dobrev
// CS 411
// HW 4
// IterativeDeepeningSearch program
// solves the 15 puzzle

public class PuzzleSolver {

	static int nodes = 2;
	// recursively perform depth limited search
	public static DLSReturn DepthLimitedSearch(GraphNode init_state,
			                                   GraphNode goal_state,
			                                   int limit) {
		if(init_state.equals(goal_state)) {
			return new DLSReturn(init_state, DLSRESULT.SOLUTION);
		}
		else if( limit == 0)
			return new DLSReturn(DLSRESULT.CUTOFF);
		else {
			boolean cutoff_occurred = false;
			ACTION[] acts = init_state.getActions();
			for(int i = 0; i < acts.length; i++ ) {
				if (acts[i] == null)
					continue;
				nodes++;
				GraphNode child = init_state.ChildNode(acts[i]);
				DLSReturn ret =  DepthLimitedSearch(child, goal_state, limit -1);
				if (ret.getResult() == DLSRESULT.CUTOFF)
					cutoff_occurred = true;
				else if(ret.getResult() != DLSRESULT.FAILURE)
					return ret;
			}
			if(cutoff_occurred)
				return new DLSReturn(DLSRESULT.CUTOFF);
			return new DLSReturn(DLSRESULT.FAILURE);
		}
	}
	
	// perform depth limited search from depth 0 to max int
	public static DLSReturn IterativeDeepeningSearch(GraphNode init_state,
			                                         GraphNode goal_state) {
		for(int depth = 0; depth < Integer.MAX_VALUE; depth++) {
			DLSReturn result = DepthLimitedSearch(init_state, goal_state, depth);
			if (result.getResult() != DLSRESULT.CUTOFF)
				return result;
		}
		return null;
	}
	
	public static void main(String[] args) {
		// set init state and goal state
		GraphNode goalState = new GraphNode();
		goalState.parseInput("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		GraphNode initState = new GraphNode();
		initState.parseInput("2, 6, 10, 3, 1, 4, 7, 11, 8, 5, 9, 15, 12, 13, 14, 0");
		
		// do search and print solution
		System.out.println("Computing ... ");
		
		long start = System.currentTimeMillis();
		DLSReturn ret = IterativeDeepeningSearch(initState, goalState);
		long end = System.currentTimeMillis();
		
		ret.getNode().printSolution();
		System.out.printf("time: %d\n", end - start);
		System.out.printf("nodes: %d\n", nodes);
		System.out.printf("mem: %d\n", nodes * 20);
	}

}



