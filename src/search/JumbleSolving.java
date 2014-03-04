package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import search.SearchingFramework;
import Jumble.Jumble;
import Jumble.JumbleMove;
import Jumble.JumbleSuccessorFunction;
import agendas.AgendaListDF;

public class JumbleSolving {
	
	public static void main(String[] args)
	{
		JumbleSuccessorFunction function = new JumbleSuccessorFunction();
		Jumble puzzle = Jumble.randomPuzzle("robot");

		Agenda<Jumble> agenda = new AgendaListDF<Jumble>();
		SearchingFramework<JumbleMove,Jumble,JumbleSuccessorFunction > search 
		= new SearchingFramework<JumbleMove,Jumble,JumbleSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();
		List<JumbleMove> result = new ArrayList<JumbleMove>();
		result.addAll(search.getResult());
		for (JumbleMove move : result) {
			System.out.println(move);
			puzzle.makeMove(move);
			System.out.println(puzzle);
		}
		System.out.println("SOLUTION:");
		System.out.println(result);
	}

}
