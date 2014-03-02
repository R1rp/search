import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import search.AgendaListA;
import search.SearchingFramework;
import Grid.Grid;
import Grid.GridSuccessorFunction;
import Grid.Grid.RobotMove;



public class test {
	
	public static void main(String[] args) {
		GridSuccessorFunction function = new GridSuccessorFunction();
		Grid puzzle = new Grid(5,5);
		puzzle.setRobot(0,0);
		puzzle.setGoal(4,4);
		puzzle.setBlock(1,1,2,1);
		puzzle.setBlock(3,4 , 4,4);
		puzzle.setBlock(4,3,3,3);
		puzzle.setBlock(2, 2, 3, 2);
		
			
		ArrayList<Grid> aList = new ArrayList <Grid>();
		Agenda<Grid> agenda = new AgendaListA<Grid>(aList);
		SearchingFramework<RobotMove,Grid,GridSuccessorFunction > search 
		= new SearchingFramework<RobotMove,Grid,GridSuccessorFunction >
		(function, puzzle, agenda);
		
		System.out.println(puzzle);
		search.Search();
		List<RobotMove> result = new ArrayList<RobotMove>();
		result.addAll(search.getResult());
		for(int i =0 ; i<3;i++){
			puzzle.makeMove(result.get(i));
		}
		System.out.println(puzzle);
		System.out.println(result);
		
		
		 //test detect online
		puzzle=new Grid(puzzle); // new puzzle to solve
		puzzle.setBlock(3, 0, 4, 0);
		aList = new ArrayList <Grid>();
		agenda = new AgendaListA<Grid>(aList);
		function = new GridSuccessorFunction();
		SearchingFramework<RobotMove,Grid,GridSuccessorFunction > research 
		= new SearchingFramework<RobotMove,Grid,GridSuccessorFunction >(function, puzzle, agenda);
		research.Search();
		System.out.println(puzzle);
		System.out.println(research.getResult());
		
	}
}
