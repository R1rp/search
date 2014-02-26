import Grid.Node;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node[][] a = new Node[10][10];
		for(int y=0 ; y<10 ; y++){
			for(int x =0 ; x<10 ;x++){
				a[x][y] = new Node(x,y);
			}
		}
		
		a[1][1].setRightBlock();
		a[3][4].setDownBlock();
		a[3][4].setRightBlock();
		a[2][4].setDownBlock();
		a[9][9].setRobot();
		a[5][4].setFlag();
		a[5][4].setRightBlock();
		a[5][4].setDownBlock();
		for(int y=0 ; y<10 ; y++){
			for(int x =0 ; x<10 ;x++){
				System.out.print(a[y][x]);
			}
			System.out.println("");
		}
		
		
	}

}
