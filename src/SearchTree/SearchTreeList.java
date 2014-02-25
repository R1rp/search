package SearchTree;

import java.util.ArrayList;
import java.util.List;

public class SearchTreeList<ActionT,StateT> extends ArrayList<SearchTree<ActionT,StateT>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 590782429016610499L;

	
	public SearchTreeList(){
		super();
	}
	
	public List<ActionT> getPathTo(StateT state){
		for (SearchTree<ActionT,StateT> pair : this) {
			if(pair.State().equals(state))
				return pair.Path();
		}
		return new ArrayList<ActionT>() ;
	}
	
	public boolean contain(StateT state){
		for (SearchTree<ActionT, StateT> tree : this) {
			if(tree.State().equals(state))
				return true;
		}
		return false;
	}
}
