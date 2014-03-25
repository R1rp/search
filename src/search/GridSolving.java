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
		Grid puzzle = new Grid(11,7);
		//setup some blocks and goal and robot position
		puzzle.setRobot(2,5);
		puzzle.setGoal(8,5);
		puzzle.setBlock(0,2,1,2);
		puzzle.setBlock(1,2,1,3);
		puzzle.setBlock(1,2,2,2);
		puzzle.setBlock(2,0,2,1);
		puzzle.setBlock(2,1,2,2);
		puzzle.setBlock(2,1,3,1);
		puzzle.setBlock(1,3,1,4);
		puzzle.setBlock(1,4,2,4);
		puzzle.setBlock(0,4,1,4);
		puzzle.setBlock(2,4,2,5);
		puzzle.setBlock(2,5,3,5);
		puzzle.setBlock(2,5,2,6);
		puzzle.setBlock(4,0,4,1);
		puzzle.setBlock(5,0,5,1);
		puzzle.setBlock(6,0,6,1);
		puzzle.setBlock(4,1,4,2);
		puzzle.setBlock(5,1,5,2);
		puzzle.setBlock(6,1,6,2);
		puzzle.setBlock(4,3,5,3);
		puzzle.setBlock(5,3,5,4);
		puzzle.setBlock(5,3,6,3);
		puzzle.setBlock(4,5,4,6);
		puzzle.setBlock(4,4,4,5);
		puzzle.setBlock(5,4,5,5);
		puzzle.setBlock(6,4,6,5);
		puzzle.setBlock(5,5,5,6);
		puzzle.setBlock(6,5,6,6);
		puzzle.setBlock(8,0,8,1);
		puzzle.setBlock(7,1,8,1);
		puzzle.setBlock(8,1,8,2);
		puzzle.setBlock(8,2,9,2);
		puzzle.setBlock(9,2,9,3);
		puzzle.setBlock(9,2,10,2);
		puzzle.setBlock(9,3,9,4);
		puzzle.setBlock(9,4,10,4);
		puzzle.setBlock(8,4,9,4);
		puzzle.setBlock(8,4,8,5);
		puzzle.setBlock(7,5,8,5);
		puzzle.setBlock(8,5,8,6);

		
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
