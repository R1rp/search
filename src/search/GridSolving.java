package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import Grid.Grid;
import Grid.Grid.RobotMove;
import Grid.GridSuccessorFunction;

public class GridSolving {

	public static void main(String[] args) {
		
		GridSuccessorFunction function = new GridSuccessorFunction();
		Grid puzzle = new Grid(7,11);
		puzzle.setRobot(0,0);
		puzzle.setGoal(2,1);
		puzzle.setBlock(1,1,2,1);
			
		ArrayList<Grid> aList = new ArrayList <Grid>();
		Agenda<Grid> agenda = new AgendaListA<Grid>(aList);
		SearchingFramework<RobotMove,Grid,GridSuccessorFunction > search 
		= new SearchingFramework<RobotMove,Grid,GridSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();
		List<RobotMove> result = new ArrayList<RobotMove>();
		result.addAll(search.getResult());
		for (RobotMove robotmove : result) {
			System.out.println(robotmove);
			puzzle.makeMove(robotmove);
			System.out.println(puzzle);
			

		}
		

	}
	
}
