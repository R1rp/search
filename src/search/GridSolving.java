package search;

import java.util.ArrayList;
import java.util.List;

import agendas.AgendaListA;
import rp13.search.interfaces.Agenda;
import Grid.Grid;
import Grid.Grid.RobotMove;
import Grid.GridSuccessorFunction;
/**
 * it basically the same for three solving class
 * @author Nelson
 *
 */
public class GridSolving {
	
	public static void main(String[] args) {
		//Grid Successor function
		GridSuccessorFunction function = new GridSuccessorFunction();
		//new Grid
		Grid puzzle = new Grid(10,7);
		//setup some blocks and goal and robot position
		puzzle.setRobot(0,0);
		puzzle.setGoal(9,6);
		puzzle.setBlock(1,0,2,0);
		puzzle.setBlock(0,1,1,1);
		puzzle.setBlock(0,2,0,3);
		puzzle.setBlock(2,2,2,3);
		puzzle.setBlock(2,3,3,3);
		puzzle.setBlock(1,5,2,5);
		puzzle.setBlock(3,6,4,6);
		puzzle.setBlock(5,5,5,6);
		puzzle.setBlock(4,4,5,4);
		puzzle.setBlock(4,1,5,1);
		puzzle.setBlock(4,2,5,2);
		puzzle.setBlock(5,2,5,3);
		puzzle.setBlock(6,4,7,4);
		puzzle.setBlock(6,5,7,5);
		puzzle.setBlock(5,0,6,0);

		
		//searching stuff
		//A* agenda
		Agenda<Grid> agenda = new AgendaListA<Grid>();
		//Searching framework with correct MOVES,PUZZLE and FUNCTION
		SearchingFramework<RobotMove,Grid,GridSuccessorFunction > search 
		= new SearchingFramework<RobotMove,Grid,GridSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();//do the search
		List<RobotMove> result = new ArrayList<RobotMove>();
		result.addAll(search.getResult());
		//do the moves and print state
		for (RobotMove robotMove : result) {
			puzzle.makeMove(robotMove);
			System.out.println(robotMove);
			System.out.println(puzzle);
		}
		System.out.println(result);
	}
	
	
}
