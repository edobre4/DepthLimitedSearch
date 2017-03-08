package cs411_hw3;

// node class
public class GraphNode {
	// the node has state which is the state of the board
	// and a parent which is a node containing the previous state
	// of the board
	private int[] state;
	private GraphNode parent;
	
	// def constructor
	public GraphNode() {
		state = null;
		parent = null;
	}
	
	// 2 param contrustor
	public GraphNode(int[] s, GraphNode p) {
		state = s.clone();
		parent = p;
	}
	
	// getter for state
	public int[] getState() {
		return state;
	}
	
	// setter for state
	public void setState(int[] s) {
		state = s;
	}
	
	// getter for parent
	public GraphNode getParent() {
		return parent;
	}
	
	// setter for parent
	public void setParent(GraphNode node) {
		parent = node;
	}
	
	// test if the states of this node and the arguments are
	// identical
	public boolean equals(GraphNode node) {
		for(int i = 0; i < 16; i++) {
			if (node.state[i] != this.state[i]) 
				return false;
		}
		return true;
	}
	
	// prints the state to the standard output
	public void printNode() {
		if (this.state == null) 
			return;
		for(int i = 0; i < 16; i++) {
			System.out.printf("%2d ", (int) this.state[i]);
			if ( (i+1) % 4 
					== 0) {
				System.out.printf("\n");
			}
		}
	}
	
	
	// returns the actions available from this state
	public ACTION[] getActions() {
		ACTION[] acts = new ACTION[4];
		for (int i = 0; i < 4; i++) 
			acts[i] = null;
		for (int i = 0; i < 16; i++ ) {
			if (this.state[i] == 0) {
				if( i  > 3 )
					acts[0] = ACTION.UP;
				if (i < 12) 
					acts[1] = ACTION.DOWN;
				if( (i % 4) != 0) 
					acts[2] = ACTION.LEFT;
				if ((i + 1) % 4 != 0) 
					acts[3] = ACTION.RIGHT;
			}
		}
		return acts;
	}

	// returns a new node that results from applying action
	// act to the state of this node
	// the parent of the resultant node is this node
	public GraphNode ChildNode(ACTION act) {
		if (act == null) 
			return null;
		GraphNode child = new GraphNode(this.state, this);
		int i;
		for(i = 0; i < 16; i++) {
			if (child.state[i] == 0)
				break;
		}
		
		if (act == ACTION.UP) {
			child.state[i] = child.state[i-4];
			child.state[i-4] = 0;
		}
		else if (act == ACTION.DOWN) {
			child.state[i] = child.state[i+4];
			child.state[i+4] = 0;
		}
		else if (act == ACTION.LEFT) {
			child.state[i] = child.state[i-1];
			child.state[i-1] = 0;
		}
		else if (act == ACTION.RIGHT) {
			child.state[i] = child.state[i+1];
			child.state[i+1] = 0;
		}
		
		return child;
	}
	
	// parses string s into the state of node
	// s has the form "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15"
	public void parseInput(String s) {
		this.state = new int[16];
		String temp[];
		temp = s.split(", ");
		for(int i = 0; i < 16; i++) {
			this.state[i] =  Integer.parseInt(temp[i]);
		}
	}
	
	// print the path of nodes starting from this node and ending
	// at the root
	public void printSolution() {
		GraphNode temp = this;
		System.out.printf("Goal State\n     v\n");
		while (temp != null) {
			temp.printNode();
			System.out.printf("     ^\n");
			temp = temp.parent;
		}
		System.out.printf("Initial State\n");
	}
}
