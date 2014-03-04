package test;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import Grid.Grid;
import Grid.Grid.RobotMove;
import agendas.AgendaListA;
import agendas.AgendaListBF;
import agendas.AgendaListDF;
@Test
public class TestAgenda {
	private AgendaListA<Grid> a;
	private AgendaListBF<EightPuzzle> b;
	private AgendaListDF<EightPuzzle> d;
	private Grid x,y,z; 
  
  
  @BeforeMethod
  public void beforeMethod() {
	  a = new AgendaListA<Grid>();
	  b = new AgendaListBF<EightPuzzle>();
	  d = new AgendaListDF<EightPuzzle>();
	  b.push(EightPuzzle.orderedEightPuzzle());
	  d.push(EightPuzzle.orderedEightPuzzle());
	  x = new Grid(5,5);
	  y = new Grid(5,5);
	  z = new Grid(5,5);
	  x.setGoal(4, 4);
	  y.setGoal(4, 4);
	  z.setGoal(4, 4);
  }
  
  public void testPush(){
	  //for BF
	  assertEquals(EightPuzzle.orderedEightPuzzle().toString(),b.getList().get(0).toString());
	  EightPuzzle puzzle = EightPuzzle.orderedEightPuzzle();
	  puzzle.makeMove(PuzzleMove.UP);
	  EightPuzzle a = new EightPuzzle(puzzle);
	  b.push(a);
	  assertEquals(puzzle.toString(),b.getList().get(1).toString());  //add to last index(1)
	  
	  //for DF 
	  assertEquals(EightPuzzle.orderedEightPuzzle().toString(),d.getList().get(0).toString());
	  EightPuzzle puzzleD = EightPuzzle.orderedEightPuzzle();
	  puzzleD.makeMove(PuzzleMove.UP);
	  EightPuzzle k = new EightPuzzle(puzzleD);
	  d.push(k);
	  assertEquals(puzzleD.toString(),d.getList().get(0).toString());  //add to First (index 0)
  }
  
  public void testPop(){
	  //BF
	  assertEquals(EightPuzzle.orderedEightPuzzle().toString(),b.pop().toString());
	  assertEquals(0,b.getSize()); //should remove the puzzle size equals 0
	  
	  //DF 
	  //add stuff to agenda first
	  EightPuzzle puzzleD = EightPuzzle.orderedEightPuzzle();
	  puzzleD.makeMove(PuzzleMove.UP);
	  EightPuzzle k = new EightPuzzle(puzzleD);
	  d.push(k);
	  
	  assertEquals(puzzleD.toString(),d.pop().toString()); //pop equals the last item input
	  assertEquals(1, d.getSize());//pop will remove that item

  }
  public void testContains(){
	  assertEquals(true,b.contains(EightPuzzle.orderedEightPuzzle()));
  }
  
  public void testSort(){
	  y.makeMove(RobotMove.RIGHT);
	  y.makeMove(RobotMove.FORWARD);
	  y.makeMove(RobotMove.FORWARD);
	  z.makeMove(RobotMove.FORWARD);
	  z.makeMove(RobotMove.FORWARD);
	  z.makeMove(RobotMove.FORWARD);
	  //x did not move so F is the least;
	  //y move right so would be greater than z because right cost more
	  //add order is x y z
	  a.push(x);
	  a.push(y);
	  a.push(z);
	  a.sort();
	  //order should be x z y
	  assertEquals("["+x.toString() + ", " + z.toString() +", " + y.toString()+"]"
			  ,a.getList().toString());
	  
  }
  
}
