package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;

public class EightPuzzleSolving {
	
	public static void main(String[] args)
	{
		EightPuzzleSuccessorFunction function = new EightPuzzleSuccessorFunction();
		EightPuzzle puzzle = EightPuzzle.randomEightPuzzle();
		ArrayList<EightPuzzle> aList =
				new ArrayList <EightPuzzle>();
		Agenda<EightPuzzle> agenda = new AgendaListBF<EightPuzzle>(aList);
		SearchingFramework<PuzzleMove,EightPuzzle,EightPuzzleSuccessorFunction > search 
		= new SearchingFramework<PuzzleMove,EightPuzzle,EightPuzzleSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();
		List<PuzzleMove> result = new ArrayList<PuzzleMove>();
		result.addAll(search.getResult());
		for (PuzzleMove puzzleMove : result) {
			System.out.println(puzzleMove);
			puzzle.makeMove(puzzleMove);
			System.out.println(puzzle);
		}
	}

}
