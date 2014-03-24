package Grid;
/**
 * a class that represent a single node on the grid
 * @author Nelson
 *
 */
public class Node {
	
	private final int x; //x-coor
	private final int y; //y-coor
	private boolean upBlock;//is up blocked?
	private boolean leftBlock;//is down blocked?
	private boolean rightBlock;//is right blocked?
	private boolean downBlock;//is down blocked?
	private boolean isRobot;//is the robot on that node?
	private boolean isFlag;//is the flag(goal) on that node?
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		//all is false at first
		this.upBlock = false;
		this.leftBlock = false;
		this.rightBlock = false;
		this.downBlock = false;
		this.isRobot = false;
		this.isFlag = false;
	}
	
	//setters and getters only
	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot() {
		this.isRobot = true;
	}
	//set no robot
	public void noRobot(){
		this.isRobot=false;
	}
	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag() {
		this.isFlag = true;
	}
	public boolean isUpBlock() {
		return upBlock;
	}
	public void setUpBlock() {
		this.upBlock = true;
	}
	public boolean isLeftBlock() {
		return leftBlock;
	}
	public void setLeftBlock() {
		this.leftBlock = true;
	}
	public boolean isRightBlock() {
		return rightBlock;
	}
	public void setRightBlock() {
		this.rightBlock = true;
	}
	public boolean isDownBlock() {
		return downBlock;
	}
	public void setDownBlock() {
		this.downBlock = true;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	/**
	 * string representation of a single node
	 * if down is block show -> _ 
	 * if right is block show -> o|
	 * we don t have to set if up is block and left is block because the node next to it will do 
	 * and also it is hard
	 * 
	 * if there is a goal or robot and it is down block. use small letter
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(isFlag()==true){
				if(isDownBlock()==true){
					sb.append("g");   //use G represent the goal
										// if down block is a block use small letter
				}
				else if(isRobot()==true){
					sb.append("V"); //flag and robot on same position  VICTORY!
				}
				else sb.append("G");       		
			}
		else if(isRobot()==true){
			if(isDownBlock()==true){
				sb.append("r");//use R represent the Robot
					// if down move is a block use small letter
			}
			else sb.append("R");
		}
		else{
			if(isDownBlock()==true){
				sb.append("_");           
				//if down move is blocked then use underline
			}
			else sb.append("O"); // use o represent the grid
		}
			
		if(isRightBlock()==true){
			sb.append("|");      //best way to represent right block
		}
		else{
			sb.append(" "); // if no block just a space
		}
		return sb.toString();
	}
	
}
