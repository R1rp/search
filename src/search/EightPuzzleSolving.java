package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import agendas.AgendaListBF;

/**
 * it basically the same for three solving class
 * @author Nelson
 *
 */
public class EightPuzzleSolving {
	
	public static void main(String[] args)
	{
		//successor function
		EightPuzzleSuccessorFunction function = new EightPuzzleSuccessorFunction();
		//the puzzle
		EightPuzzle puzzle = EightPuzzle.randomEightPuzzle();
		//BFSearch agenda
		Agenda<EightPuzzle> agenda = new AgendaListBF<EightPuzzle>();
		//add all to the searching framework with the correct type
		SearchingFramework<PuzzleMove,EightPuzzle,EightPuzzleSuccessorFunction > search 
		= new SearchingFramework<PuzzleMove,EightPuzzle,EightPuzzleSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		//do the search for goal
		search.Search();
		List<PuzzleMove> result = new ArrayList<PuzzleMove>();
		//add the result to a array list
		result.addAll(search.getResult());
		//do the moves and print state+ moves
		for (PuzzleMove puzzleMove : result) {
			System.out.println(puzzleMove);
			puzzle.makeMove(puzzleMove);
			System.out.println(puzzle);
		}
		System.out.println(result);
	}

}
