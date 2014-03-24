package SearchTree;

import java.util.ArrayList;
import java.util.List;
/**
 * search node contains a state and the path to that state
 * @author Nelson
 *
 * @param <ActionT> Action Type
 * @param <StateT> Puzzle Type
 */
public class SearchTreeNode<ActionT,StateT> {

	private final StateT state;
	private final List<ActionT> path;
	
	/**
	 * a node with no moves to that state (the original puzzle)
	 * @param state original puzzle
	 */
	public SearchTreeNode(StateT state){
		this.state = state;
		this.path = new ArrayList<ActionT>();
	}
	
	/**
	 * a node with a copy of list of action
	 * @param state a puzzle
	 * @param path list of path
	 */
	public SearchTreeNode(StateT state,List<ActionT> path){
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
	/**
	 * check if a path is same as this path
	 * @param abc path input
	 * @return true if same
	 */
	public boolean equalPath(List<ActionT> abc){
		for(int i =0 ; i<path.size() ; i++){
			if(path.get(i)!=abc.get(i)){
				return false;
			}
			
		}
		return true;
	}
		
}
