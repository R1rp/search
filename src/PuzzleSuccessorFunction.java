package rp13.search.interfaces;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.SuccessorFunction;
import rp13.search.interfaces.Puzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;

/**
 * An example eight-puzzle successor function.
 * 
 * @author Nick Hawes
 * 
 * @param <ActionT>
 * @param <StateT>
 */
public class PuzzleSuccessorFunction implements
		SuccessorFunction<PuzzleMove, Puzzle> {

	/**
	 * 
	 * Get the possible successors of an eight-puzzle state. Only returns legal
	 * moves.
	 * 
	 */
	@Override
	public void getSuccessors(Puzzle _state,
			List<ActionStatePair<PuzzleMove, Puzzle>> _successors) {

		assert (_successors != null);

		// for each of the moves that are available
		for (PuzzleMove move : PuzzleMove.values()) {

			// check if it is possible
			if (_state.isPossibleMove(move)) {

				// create a copy of the input state as we don't want to change
				// it
				Puzzle successor = new Puzzle(_state);
				// apply the move
				successor.makeMove(move);
				// store the move and action together in a pair and add to
				// successor list
				_successors
						.add(new ActionStatePair<Puzzle.PuzzleMove, Puzzle>(
								move, successor));
			}

		}

	}

	public static void main(String[] args) {
		Puzzle state = Puzzle.orderedPuzzle();
		PuzzleSuccessorFunction successorFn = new PuzzleSuccessorFunction();

		// successors are added to the end of this list by the successor
		// function
		List<ActionStatePair<PuzzleMove, Puzzle>> successors = new ArrayList<ActionStatePair<PuzzleMove, Puzzle>>();
		;
		// get the successors of the given state
		successorFn.getSuccessors(state, successors);

		for (ActionStatePair<PuzzleMove, Puzzle> successor : successors) {

			System.out.println("Applied move " + successor.getAction()
					+ " and got:");

			System.out.println(successor.getState());

		}

	}

}
