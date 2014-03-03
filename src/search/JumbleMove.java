package search;

import java.util.ArrayList;
import java.util.Random;

public class JumbleMove {
	private final int left;
	  private final int right;
	  private static ArrayList<JumbleMove> n;

	  public JumbleMove(int left, int right) {
	    this.left = left;
	    this.right = right;
	  }

	  public int getLeft() { return left; }
	  public int getRight() { return right; }
      
	  public static ArrayList<JumbleMove> values(){
		  n = new ArrayList<JumbleMove>();
		  n.add(new JumbleMove(0,1));
		  n.add(new JumbleMove(0,2));
		  n.add(new JumbleMove(0,3));
		  n.add(new JumbleMove(1,2));
		  n.add(new JumbleMove(1,3));
		  n.add(new JumbleMove(2,3));
		return n;
		  
	  }
	  public String toString(){
		return left+","+right;
		  
	  }
	  public static JumbleMove Random(){
		  Random rnd = new Random(5);
		  int a = rnd.nextInt();
		  return n.get(a);
		  
	  }
}


