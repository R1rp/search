package SearchTree;

import java.util.ArrayList;
import java.util.List;

public class SearchTree<ActionT,StateT> {

	private final StateT state;
	private final List<ActionT> path;
	
	
	public SearchTree(StateT state){
		this.state = state;
		this.path = new ArrayList<ActionT>();
	}
	
	public SearchTree(StateT state,List<ActionT> path){
		this.state = state;

		this.path = path;
		
	}
	
	public List<ActionT> Path(){
		return path;
	}
	
	public StateT State(){
		return state;
	}
	
	public void addAction(ActionT move){
		path.add(move);
	}
		
		
}