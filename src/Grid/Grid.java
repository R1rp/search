package Grid;

import rp13.search.interfaces.Puzzle;


public class Grid implements Comparable<Grid>,Puzzle{
	public enum RobotMove{
		FORWARD(0),LEFT(-1),RIGHT(1);

		private final int r_move;

		private RobotMove(int _move) {
			r_move = _move;
		}
		
	}
	
	public enum RobotDirection{
		Left(0),Up(1),Right(2),Down(3);
		private final int val;
		
		private RobotDirection(int _val) {
			val	=_val;
			
		}
		
	}
	private Node[][] grid;
	private final int height;
	private final int width;
	private int goalX;
	private int goalY;
	private int robotX;
	private int robotY;
	private int robotDirection;
	private int d; //estimate distance
	private int g; //cost
	private int f; //total cost;
	
	public Grid(int width,int height){

		this.height= height;
		this.width = width;
		this.grid = new Node[width][height];
		for(int i =0 ; i<this.width ; i++){
			for(int j =0 ; j<this.height ; j++){
				this.grid[i][j] = new Node(i,j);
				//set block for edges
				if(i==0){
					grid[i][j].setLeftBlock();  
				}
				if(i==width-1){
					grid[i][j].setRightBlock();
				}
				if(j==0){
					grid[i][j].setDownBlock();
				}
				if(j==height-1){
					grid[i][j].setUpBlock();
				}
			}
		}
		
		this.robotDirection = RobotDirection.Up.val;
		this.g = 0;
		calculateCost();
	}

	private void calculateCost(){
		int dx = this.robotX -goalX; 
		if(dx<0)
			{dx = -1*dx;}
		int dy = this.robotY -goalY;
		if(dy<0)
			{dy=-1*dy;}
		this.d = dx+dy;
		this.f = d+g;
	}
	public boolean isGoal(){
		return grid[goalX][goalY].isRobot() ;
	}
	
	public void setRobot(int x,int y){
		this.robotX = x;
		this.robotY = y;
		for (Node[] nodes : grid) {
			for (Node node : nodes) {
				node.RobotMove();
			}
			
		}
		grid[robotX][robotY].setRobot();
	}
	public void setGoal(int goalX , int goalY){
		this.goalX = goalX;
		this.goalY = goalY;
		grid[goalX][goalY].setFlag();
	}
	
	public void makeMove(RobotMove rmove){
		
		if(isPossibleMove(rmove)){
			robotDirection += rmove.r_move;
			if(robotDirection>3){
				robotDirection = RobotDirection.Left.val;
			}
			if(robotDirection<0){
				robotDirection = RobotDirection.Down.val;
			}
		
			if(robotDirection==RobotDirection.Left.val){
				setRobot(robotX-1 ,robotY);
			}
			if(robotDirection==RobotDirection.Up.val){
				setRobot(robotX ,robotY+1);
			}
			if(robotDirection==RobotDirection.Right.val){
				setRobot(robotX+1 ,robotY);
			}
			if(robotDirection==RobotDirection.Down.val){
				setRobot(robotX ,robotY-1);
			}
			if(rmove==RobotMove.FORWARD){
				g++;
			}
			if(rmove==RobotMove.LEFT||rmove==RobotMove.RIGHT){
				g=g+2;
			}
			calculateCost();
		}
	}
	/** return if it is a possible move*/
	public boolean isPossibleMove(RobotMove rmove){
		int temp = robotDirection;
		temp += rmove.r_move;
		{
			if(temp==4){
				temp = 0;
			}
				else if(temp<0){
				temp = 3;
			}
		}
		
		if(temp==RobotDirection.Left.val){
			return !grid[robotX][robotY].isLeftBlock();
		}
		if(temp==RobotDirection.Up.val){
			return !grid[robotX][robotY].isUpBlock();
		}
		if(temp==RobotDirection.Right.val){
			return !grid[robotX][robotY].isRightBlock();
		}
		if(temp==RobotDirection.Down.val){
			return !grid[robotX][robotY].isDownBlock();
		}
		return true;
	}

	
	public boolean equals(Grid that){
		return this.robotX==that.robotX&&
				this.robotY==that.robotY;
	}
	public int getF(){
		return f;
	}
	//compare method
	public int compareTo(Grid arg0) {
		int a= this.f-arg0.f;
		if (a==0){
			return this.g-arg0.g;
		}
		return a;
	}
	
	public void setBlock(int x , int y , int a, int b){
		if(x==a){ // if horizontal block
			if(b>y){
				grid[x][y].setUpBlock();
				grid[a][b].setDownBlock();}
			else{
				grid[x][y].setDownBlock();
				grid[a][b].setUpBlock();}
		}
		if(y==b){ // if vertical block
			if(a>x){
				grid[x][y].setRightBlock();
				grid[a][b].setLeftBlock();
			}
			else{
				grid[x][y].setLeftBlock();
				grid[a][b].setRightBlock();
			}
		}
	}
	
	public Node Node(int x , int y){
		return grid[x][y];
	}
	
	public String toString(){
		StringBuilder sp = new StringBuilder();
		for(int y=height-1 ; y>=0 ; y--){
			for(int x =0 ; x<width ;x++){
				sp.append(grid[x][y]);
			}
				sp.append("\n");
			}
		for (RobotDirection value : RobotDirection.values()) {
			if (robotDirection==value.val){
				sp.append("Faceing " + value +"\n");
			}
		}
		
		return sp.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid a = new Grid(5,5);
		a.setRobot(0, 0);
		a.setGoal(2,1);
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
