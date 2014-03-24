package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import search.SearchingFramework;
import Jumble.Jumble;
import Jumble.JumbleMove;
import Jumble.JumbleSuccessorFunction;
import agendas.AgendaListDF;
/**
 * it basically the same for three solving class
 * @author Nelson
 *
 */
public class JumbleSolving {
	
	public static void main(String[] args)
	{
		//jumble successor function
		JumbleSuccessorFunction function = new JumbleSuccessorFunction();
		//jumble puzzle with a string "robot" this can be any string
		Jumble puzzle = Jumble.randomPuzzle("robot");
		//DFSearch agenda
		Agenda<Jumble> agenda = new AgendaListDF<Jumble>();
		//Plugin correct MOVES STATE FUINCTION to search
		SearchingFramework<JumbleMove,Jumble,JumbleSuccessorFunction > search 
		= new SearchingFramework<JumbleMove,Jumble,JumbleSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle+"\n");
		search.Search();
		List<JumbleMove> result = new ArrayList<JumbleMove>();
		result.addAll(search.getResult());//get result
		//print...
		for (JumbleMove move : result) {
			System.out.println(move);
			puzzle.makeMove(move);
			System.out.println(puzzle+"\n");
		}
		System.out.println("SOLUTION:");
		System.out.println(result);
	}

}
