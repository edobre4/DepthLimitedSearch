package cs411_hw3;

// return type for depth limited search
// can return a node if a solution was found
// otherwise will return DLSRESULT of FAILURE or CUTOFF
// 
public class DLSReturn {
	private GraphNode node;
	private DLSRESULT result;
	
	public DLSReturn(DLSRESULT res) {
		result = res;
		node = null;
	}
	
	public DLSReturn(GraphNode n, DLSRESULT res) {
		node = n;
		result = res;
	}
	
	public GraphNode getNode() {
		return node;
	}
	
	public DLSRESULT getResult() {
		return result;
	}
}
