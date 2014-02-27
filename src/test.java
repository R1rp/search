import Grid.Grid;
import Grid.Grid.RobotMove;
import Grid.Node;



public class test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid a = new Grid(11,7);
		a.setRobot(0, 0);
		a.setGoal(5, 5);
		a.setBlock(4,0 , 4,1);
		a.setBlock(6,2,7,2);
		System.out.println(a.Node(4,1).isDownBlock());
		System.out.println(a);

		
	}
}
