package Grid;

public class Node {
	
	private final int x;
	private final int y;
	private boolean upBlock;
	private boolean leftBlock;
	private boolean rightBlock;
	private boolean downBlock;
	private boolean isRobot;
	private boolean isFlag;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.upBlock = false;
		this.leftBlock = false;
		this.rightBlock = false;
		this.downBlock = false;
		this.isRobot = false;
		this.isFlag = false;
	}
	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot() {
		this.isRobot = true;
	}
	
	public void RobotMove(){
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
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(isFlag()==true){
				if(isDownBlock()==true){
					sb.append("g");   //use G represent the goal
										// if down move is a block use small letter
				}
				else if(isRobot()==true){
					sb.append("V");
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
				sb.append("_");           // use o represent the grid
				//if down move is blocked then use underline
			}
			else sb.append("O");
		}
			
		if(isRightBlock()==true){
			sb.append("|");      //best way to represent right block
		}
		else{
			sb.append(" ");
		}
		return sb.toString();
	}

	public static void main(String[] args){
		Node a = new Node(1,1);
		
		System.out.println(a);
	}
}
