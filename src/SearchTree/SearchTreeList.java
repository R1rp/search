package SearchTree;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Puzzle;

public class SearchTreeList<ActionT,StateT extends Puzzle> extends ArrayList<SearchTree<ActionT,StateT>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 590782429016610499L;

	/**
	 * call super class
	 */
	public SearchTreeList(){
		super();
	}
	/**
	 * get the path
	 * @param state the state we want to get the path to
	 * @return if there is a state in the tree which equals the state
	 * return the path else return empty list
	 */
	public List<ActionT> getPathTo(StateT state){
		for (SearchTree<ActionT,StateT> pair : this) {
			if(pair.State().equals(state))
				return pair.Path();
		}
		return new ArrayList<ActionT>() ;
	}
	/**
	 * check whether a state is in the tree
	 * @param state the state we wanna check
	 * @return true is contain or false
	 */
	public boolean contain(StateT state){
		for (SearchTree<ActionT, StateT> tree : this) {
			if(tree.State().equals(state))
				return true;
		}
		return false;
	}
	/**
	 * Check whether there is a goal state in the list
	 * @return isGoal?
	 */
	public boolean isGoal(){
		for (SearchTree<ActionT,StateT> tree : this) {
			if(tree.State().isGoal())
				return true;
		}
		return false;
	}
	/**
	 * get path to goal
	 * @return path to goal state. The ULTIMATE SOLUTION
	 */
	public List<ActionT> getPathToGoal(){
		for (SearchTree<ActionT,StateT> pair : this) {
			if(pair.State().isGoal())
				return pair.Path();
		}
		return new ArrayList<ActionT>() ;
	}
}
