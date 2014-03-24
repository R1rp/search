package Grid;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;
import Grid.Grid.RobotMove;
/**
 * no big difference to the eight puzzle successor function
 * @author Nelson
 *
 */
public class GridSuccessorFunction implements
		SuccessorFunction<RobotMove, Grid> {


	@Override
	public void getSuccessors(Grid _state,
			List<ActionStatePair<RobotMove, Grid>> _successors) {
		assert (_successors != null);
		// for each of the moves that are available
		for (RobotMove move : RobotMove.values()) {

			// check if it is possible
			if (_state.isPossibleMove(move)) {
				// create a copy of the input state as we don't want to change
				// it
				Grid successor = new Grid(_state);
				// apply the move
				successor.makeMove(move);
				// store the move and action together in a pair and add to
				// successor list
				_successors
						.add(new ActionStatePair<Grid.RobotMove, Grid>(
								move, successor));		
			}
		}
	}
	
	public static void main(String args[]){
		Grid state = new Grid(7,11);
		state.setGoal(4, 4);
		state.setRobot(0,0);
		GridSuccessorFunction successorFn = new GridSuccessorFunction();
		List<ActionStatePair<RobotMove, Grid>> successors 
			= new ArrayList<ActionStatePair<RobotMove,Grid>>();
		
		successorFn.getSuccessors(state, successors);

		for (ActionStatePair<RobotMove, Grid> successor : successors) {

			System.out.println("Applied move " + successor.getAction()
					+ " and got:");

			System.out.println(successor.getState());
		}
	}
}
