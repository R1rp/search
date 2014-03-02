package search;



import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;
import search.Jumble.JumbleMove;

/**
 * An example eight-puzzle successor function.
 * 
 * @author Nick Hawes
 * 
 * @param <ActionT>
 * @param <StateT>
 */
public class JumbleSuccessorFunction implements
		SuccessorFunction<JumbleMove, Jumble> {

	/**
	 * 
	 * Get the possible successors of an eight-puzzle state. Only returns legal
	 * moves.
	 * 
	 */
	@Override
	public void getSuccessors(Jumble _state,
			List<ActionStatePair<JumbleMove, Jumble>> _successors) {

		assert (_successors != null);

		// for each of the moves that are available
		for (JumbleMove move : JumbleMove.values()) {

			// check if it is possible
			if (_state.isPossibleMove(move)) {

				// create a copy of the input state as we don't want to change
				// it
				Jumble successor = new Jumble(_state);
				// apply the move
				successor.makeMove(move);
				// store the move and action together in a pair and add to
				// successor list
				_successors
						.add(new ActionStatePair<Jumble.JumbleMove, Jumble>(
								move, successor));
			}

		}

	}

	public static void main(String[] args) {
		Jumble state = Jumble.orderedPuzzle();
		JumbleSuccessorFunction successorFn = new JumbleSuccessorFunction();

		// successors are added to the end of this list by the successor
		// function
		List<ActionStatePair<JumbleMove, Jumble>> successors = new ArrayList<ActionStatePair<JumbleMove, Jumble>>();
		;
		// get the successors of the given state
		successorFn.getSuccessors(state, successors);

		for (ActionStatePair<JumbleMove, Jumble> successor : successors) {

			System.out.println("Applied move " + successor.getAction()
					+ " and got:");

			System.out.println(successor.getState());

		}

	}

}
