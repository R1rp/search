package Jumble;

import java.util.ArrayList;
import java.util.Random;
/**
 * a class that represent the moves for jumble puzzle
 * @author Nelson
 *
 */
public class JumbleMove {
	private final int x;
	private final int y;
	 

	
	  public JumbleMove(int x, int y) {
	    this.x = x;
	    this.y = y;
	    
	  }

	  public int getX() { return x; }
	  public int getY() { return y; }
      
	  /**
	   * get all possible moves by a given string
	   * like for a string have 3 char
	   * return list of (0,1)(0,2)(1,2).........
	   * @param length length of a string
	   * @return list of possible moves
	   */
	  public static ArrayList<JumbleMove> values(int length){
		ArrayList<JumbleMove>  n = new ArrayList<JumbleMove>();
		for(int i =0;i<length ; i++){
			for(int j =0 ;j<length; j++){
				if(i<j)
					n.add(new JumbleMove(i,j));		
				}
			}
		return n;
	}  
	  
	  /**
	   * return a random move
	   * generate two random number within the length
	   * @param length
	   * @return
	   */
	  public static JumbleMove Random(int length){
		  Random x = new Random();
		  Random y = new Random();
		  int a = x.nextInt(length);
		  int b = y.nextInt(length);
		  while(a==b){ //two index cant be the same
			  b= y.nextInt(length);
		  }
		
		  return new JumbleMove(a,b);
	  }
	  
	  public String toString(){
			return "("+x+","+y+")";
			  
		  }
	  public static void main(String[] args){
		  System.out.println(JumbleMove.values(5));
		  System.out.println(JumbleMove.Random(4));
	  }
}


