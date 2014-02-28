import Grid.Grid;
import Grid.Grid.RobotMove;



public class test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid a = new Grid(11,7);
		a.setRobot(4, 4);
		a.setGoal(5, 5);
		a.setBlock(4,0 , 4,1);
		a.setBlock(6,2,7,2);
		System.out.println(a.Node(4,1).isDownBlock());
		System.out.println(a);
		
		a.makeMove(RobotMove.FORWARD);
	
		a.makeMove(RobotMove.RIGHT);
		 
		Grid b = new Grid(a);
		
		a.makeMove(RobotMove.RIGHT);
		System.out.println(b);
		System.out.println(b.getF());
		
		System.out.println(a);
		System.out.println(a.getF());
		
	}
}
