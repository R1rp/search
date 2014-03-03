package test;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Grid.Grid;
import Grid.Grid.RobotMove;

@Test
public class TestGrid {
	
	private Grid state;
	private Grid state2;
	private Grid state3;
  @BeforeMethod
  public void f() {
	  state = new Grid(5,5);
	  state2 = new Grid(5,5);
	  state.setGoal(2,2);
	  state3 = new Grid(3,3);
	  state3.setGoal(1, 1);
  }
  
  public void testMove(){
	  assertEquals(false,state.isPossibleMove(RobotMove.LEFT));//left is block
	  state.makeMove(RobotMove.FORWARD);
	  assertEquals(true,state.Node(0, 1).isRobot());
	  state.makeMove(RobotMove.RIGHT);
	  assertEquals(true,state.Node(1, 1).isRobot());
	  
  }
  
  public void testBlock(){
	  state2.setBlock(0, 0, 1, 0);
	  //should not be move right
	  assertEquals(false,state2.isPossibleMove(RobotMove.RIGHT));
  }
  
  public void testGoal(){
	  assertEquals(true,state3.Node(1, 1).isFlag());
	  state3.makeMove(RobotMove.FORWARD);
	  state3.makeMove(RobotMove.RIGHT); // robot moves to flag position
	  assertEquals(true,state3.isGoal());
  }
}
