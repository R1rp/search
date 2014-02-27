import Grid.Grid;
import Grid.Grid.RobotMove;
import Grid.Node;



public class test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid a = new Grid(5,5,4,4);
		a.setRobot(0, 0);
		System.out.println(a);
		System.out.println(RobotMove.FORWARD);
		System.out.println(a.isPossibleMove(RobotMove.FORWARD));
		a.makeMove(RobotMove.FORWARD);
		System.out.println(a);
		
		System.out.println(RobotMove.RIGHT);
		System.out.println(a.isPossibleMove(RobotMove.RIGHT));
		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);
		
		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);

		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);


		
	}
}
