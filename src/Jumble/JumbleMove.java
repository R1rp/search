package Jumble;

import java.util.ArrayList;
import java.util.Random;

public class JumbleMove {
	private final int x;
	private final int y;
	 

	  public JumbleMove(int x, int y) {
	    this.x = x;
	    this.y = y;
	    
	  }

	  public int getX() { return x; }
	  public int getY() { return y; }
      
	  
	  public static ArrayList<JumbleMove> values(int length){ // contain all the values of possible moves 

		ArrayList<JumbleMove>  n = new ArrayList<JumbleMove>();
		for(int i =0;i<length ; i++){
			for(int j =0 ;j<length; j++){
				if(i!=j)
				n.add(new JumbleMove(i,j));
			}
				
		}
		  return n;
		  
	  }
	  public String toString(){
		return "("+x+","+y+")";
		  
	  }
	
	  
	  public static JumbleMove Random(int length){
		  Random x = new Random();
		  Random y = new Random();
		  int a = x.nextInt(length);
		  int b = y.nextInt(length);
		  while(a==b){
			  b= y.nextInt(length);
		  }
		
		  return new JumbleMove(a,b);
	  }
	  
	  public static void main(String[] args){
		  System.out.println(JumbleMove.values(5));
		  System.out.println(JumbleMove.Random(4));
	  }
}


