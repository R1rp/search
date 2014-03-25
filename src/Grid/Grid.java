package Grid;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Puzzle;


public class Grid implements Comparable<Grid>,Puzzle{
	/** enum for moving the robot
	 * the value * 90 degrees equals to the turn value of the robot
	 * @author Nelson
	 * only have forward left and right so the robot cant go backwards to avoid duplicate state
	 */
	public enum RobotMove{
		FORWARD(0),LEFT(+1),RIGHT(-1),BACKWARD(+2);

		public final int r_move;

		private RobotMove(int _move) {
			r_move = _move;
		}
		
	}
	/**
	 * enum for where direction the robot are facing 
	 * @author Nelson
	 *
	 */
	public enum RobotDirection{
		MINUS_X(0),MINUS_Y(1),PLUS_X(2),PLUS_Y(3);
		private final int val;
		
		private RobotDirection(int _val) {
			val	=_val;
			
		}
		
	}
	/**
	 * @param grid Node[][] to represent the grid
	 * @param height of the grid
	 * @param width of the grid
	 * @param goalX x-coor of goal
	 * @param goalY y-coor of goal
	 * @param robotX x-coor of robot
	 * @parm robotY y-coor of robot
	 * @param robotDirection an int to represent the direction of the robot is facing
	 * @param d estimate distance
	 * @param g cost
	 * @param f total cost
	 * @param blocks a List to store blocks position for the copy methods
	 */
	private Node[][] grid; 
	protected int height;
	protected int width;
	private int goalX;
	private int goalY;
	private int robotX;
	private int robotY;
	private int robotDirection;
	private int d; 
	private int g; 
	private int f; 
	private List<BlockPair> blocks ; 
	
	/**
	 * constructor of a grid with a height and width
	 */
	public Grid(int width,int height){ //set this to be private

		this.height = height;
		this.width = width;
		this.grid = new Node[width][height];
		/** 
		 * set blocks for the edges
		 */
		for(int x =0 ; x<this.width ; x++){
			for(int y =0 ; y<this.height ; y++){
				this.grid[x][y] = new Node(x,y);
				//set block for edges
				if(x==0){ //if x == 0 that means is left most 
					grid[x][y].setLeftBlock();  
				}
				if(x==width-1){//right most
					grid[x][y].setRightBlock();
				}
				if(y==0){//Top most
					grid[x][y].setUpBlock();
				}
				if(y==height-1){//Bottom most
					grid[x][y].setDownBlock();
				}
			}
		}
		this.blocks = new ArrayList<BlockPair>();
		this.robotDirection = RobotDirection.PLUS_X.val; //set robot face up 
		this.robotX =0;
		this.robotY=0;
		this.g = 0;
		calculateCost(); //calculate the cost
	}

	/**
	 * return robot direction
	 * @return an int represent which direction robot is facing
	 */
	public int getRobotDirection() {
		return robotDirection;
	}

	/**
	 * set which direction the robot is facing
	 * @param robotDirection the int representation
	 */
	public void setRobotDirection(int robotDirection) {
		this.robotDirection = robotDirection;
	}


	//create a copy method (much the same as the constructor)
	/**
	 * a copy method to copy THAT state
	 * basically copy everything 
	 * @param that input grid
	 */
	public Grid (Grid that){
		this.width = that.width;
		this.height = that.height;
		this.goalX = that.goalX;
		this.goalY = that.goalY;
		this.robotDirection = that.robotDirection;
		this.robotX = that.robotX;
		this.robotY = that.robotY;
		this.grid = new Node[width][height];
		for(int i =0 ; i<this.width ; i++){
			for(int j =0 ; j<this.height ; j++){
				this.grid[i][j] = new Node(i,j);
				//set block for edges
				if(i==0){
					this.grid[i][j].setLeftBlock();  
				}
				if(i==width-1){
					this.grid[i][j].setRightBlock();
				}
				if(j==0){
					this.grid[i][j].setUpBlock();
				}
				if(j==height-1){
					this.grid[i][j].setDownBlock();
				}
			}
		}
		
		 //set blocks for the new grid according to the block list
		 
		this.blocks = new ArrayList<BlockPair>();
		for (BlockPair pair : that.blocks) {
			this.setBlock(pair.getX(), pair.getY(), pair.getA(), pair.getB());
		}
		
		setGoal(this.goalX,this.goalY);//set goal
		setRobot(this.robotX,this.robotY);//set robot 
		this.g = that.g; 
		calculateCost();//calculate the cost
		
	}
	/**
	 * private method to calculate the cost
	 */
	private void calculateCost(){
		int dx = this.robotX -goalX;  //distance x must be positive
		if(dx<0)
			{dx = -1*dx;}
		int dy = this.robotY -goalY; //distance y must be positive
		if(dy<0)
			{dy=-1*dy;}
		this.d = dx+dy; //add it up
		this.f = d+g; //f = d+g
	}
	
	/**
	 * 
	 * @return if the goal position is same as robot position
	 */
	public boolean isGoal(){
		return grid[goalX][goalY].isRobot() ;
	}
	
	/**
	 * set the robot position
	 * and other position set isRobot() be false
	 * @param x robot x-coor
	 * @param y robot y-coor
	 * 
	 */
	public void setRobot(int x,int y){
		this.robotX = x;
		this.robotY = y;
		for (Node[] nodes : grid) {
			for (Node node : nodes) {
				node.noRobot();
			}
		}
		grid[robotX][robotY].setRobot();
	}
	/**
	 * Set the goal position of the grid
	 * @param goalX x-coor
	 * @param goalY y-coor
	 */
	public void setGoal(int goalX , int goalY){
		this.goalX = goalX;
		this.goalY = goalY;
		grid[goalX][goalY].setFlag();
	}
	
	/**
	 * Let the robot move
	 * robot can have three moves 
	 * but it depends on which direction the robot is facing
	 * so check the facing direction after doing each moves
	 * @param rmove either move FOWARD LEFT OR RIGHT
	 */
	public void makeMove(RobotMove rmove){
		if(isPossibleMove(rmove)){
			//because some setup mistakes for RIGHT(-1) and LEFT(1) value 
			//and the direction value
			//so it is a minus
			robotDirection -= rmove.r_move; //robot rotate get new facing direction
			if(robotDirection>3){
				robotDirection = RobotDirection.MINUS_X.val; // if greater 3 than is left
			}
			if(robotDirection<0){
				robotDirection = RobotDirection.PLUS_Y.val; // if less than 0  than is down
			}
			
			//determine which direction the robot is facing and do the moves
			if(robotDirection==RobotDirection.MINUS_X.val){
				setRobot(robotX-1 ,robotY);  //if robot is move left then set new robot position
			}
			if(robotDirection==RobotDirection.MINUS_Y.val){
				setRobot(robotX ,robotY-1); //move up
			}
			if(robotDirection==RobotDirection.PLUS_X.val){
				setRobot(robotX+1 ,robotY); //move right
			}
			if(robotDirection==RobotDirection.PLUS_Y.val){
				setRobot(robotX ,robotY+1); //move down
			}
			if(rmove==RobotMove.FORWARD){
				g++; //move forward cost1
			}
			if(rmove==RobotMove.LEFT||rmove==RobotMove.RIGHT){
				g=g+2; //turn cost 2
			}
			if(rmove==RobotMove.BACKWARD){
				g+=3;
			}
			calculateCost(); //re-calculate cost
		}
	}
	/** return if it is a possible move*/
	public boolean isPossibleMove(RobotMove rmove){
		int temp = robotDirection; //set var for robot direction becoz dont wanna change it
		temp -= rmove.r_move;
		{
			if(temp==4){
				temp = 0;
			}
				else if(temp<0){
				temp = 3;
			}
		}
		
		if(temp==RobotDirection.MINUS_X.val){
			return !grid[robotX][robotY].isLeftBlock(); //if move left check left is blocked;
		}
		else if(temp==RobotDirection.MINUS_Y.val){
			return !grid[robotX][robotY].isUpBlock(); //check up
		}
		else if(temp==RobotDirection.PLUS_X.val){
			return !grid[robotX][robotY].isRightBlock(); //check right
		}
		else if(temp==RobotDirection.PLUS_Y.val){
			return !grid[robotX][robotY].isDownBlock(); //check down
		}
		return true;
	}

	/**
	 * 
	 * @param that a grid to compare to 
	 * @return robot direction 
	 */
	public boolean equals(Grid that){
		return this.robotX==that.robotX&&    
				this.robotY==that.robotY&&
				this.robotDirection==that.robotDirection&&
				this.f==that.f&&
				this.g==that.g;
				
	}
	
	/**
	 * get f 
	 * @return f
	 */
	public int getF(){
		return f;
	}
	
	
	/**
	 * compare the f;
	 */
	public int compareTo(Grid that) {
		int a= this.f-that.f;
		if (a==0){
			return this.g-that.g; //if f is same check g;
		}
		return a;
	}
	/**
	 * 
	 * @param x x-coor
	 * @param y y-coor
	 * @param a x-coor of another node
	 * @param b y-coor of another
	 */
	public void setBlock(int x , int y , int a, int b){
		if(x==a){ // if up down block
			if(b>y){
				grid[x][y].setDownBlock();
				grid[a][b].setUpBlock();
			}
			else{
				grid[x][y].setUpBlock();
				grid[a][b].setDownBlock();
			}
		}
		if(y==b){ // if left right block
			if(a>x){
				grid[x][y].setRightBlock();
				grid[a][b].setLeftBlock();
			}
			else{
				grid[x][y].setLeftBlock();
				grid[a][b].setRightBlock();
			}
		}
		
		blocks.add(new BlockPair(x,y,a,b));//add to blocks to store for copy method
	}
	/**
	 * return the grid for some debug
	 * @param x x-coor
	 * @param y y-coor
	 * @return Node of x-coor y-coor
	 */
	public Node Node(int x , int y){
		return grid[x][y];
	}
	/**
	 * get robot x-coor
	 * @return robot x-coor
	 */
	public int getRobotX(){
		return robotX;
	}
	/**
	 * ger robot y-coor
	 * @return robot y-coor
	 */
	public int getRobotY(){
		return robotY;
	}
	/**
	 * to string methods
	 * represent the block, goal and robot position
	 * also include which direction the robot is facing
	 */
	public String toString(){
		StringBuilder sp = new StringBuilder();
		for(int y=0 ; y<height ; y++){
			for(int x =0 ; x<width ;x++){
				sp.append(grid[x][y]);
			}
				sp.append("\n");
			}
		for (RobotDirection value : RobotDirection.values()) {
			if (robotDirection==value.val){
				sp.append("Facing " + value +"\n");
			}
		}
		return sp.toString();
	}
	/**
	 * little tests
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid a = new Grid(5,5);
		a.setRobot(0, 0);
		a.setGoal(2,1);
		System.out.println(a);
		System.out.println(RobotMove.LEFT);
		System.out.println(a.isPossibleMove(RobotMove.LEFT));
		a.makeMove(RobotMove.LEFT);
		System.out.println(a);
		
		System.out.println(RobotMove.RIGHT);
		System.out.println(a.isPossibleMove(RobotMove.RIGHT));
		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);
		
		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);

		a.makeMove(RobotMove.RIGHT);
		System.out.println(a);
		a.makeMove(RobotMove.BACKWARD);
		System.out.println(a);

	}
	
}
