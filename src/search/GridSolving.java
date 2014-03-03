package search;

import java.util.ArrayList;
import java.util.List;

import agendas.AgendaListA;
import rp13.search.interfaces.Agenda;
import Grid.Grid;
import Grid.Grid.RobotMove;
import Grid.GridSuccessorFunction;

public class GridSolving {
	
	public static void main(String[] args) {
		
		GridSuccessorFunction function = new GridSuccessorFunction();
		Grid puzzle = new Grid(5,5);
		//setup some blocks and goal and robot position
		puzzle.setRobot(0,0);
		puzzle.setGoal(4,4);
		puzzle.setBlock(4,3,4,4);
		puzzle.setBlock(2,1,1,1);
		puzzle.setBlock(0,0,0,1);

		
		//searching stuff
		
		Agenda<Grid> agenda = new AgendaListA<Grid>();
		SearchingFramework<RobotMove,Grid,GridSuccessorFunction > search 
		= new SearchingFramework<RobotMove,Grid,GridSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();
		List<RobotMove> result = new ArrayList<RobotMove>();
		result.addAll(search.getResult());
		for (RobotMove robotMove : result) {
			puzzle.makeMove(robotMove);
			System.out.println(robotMove);
			System.out.println(puzzle);
		}
		System.out.println(result);
	}
	
	
}
