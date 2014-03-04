package Jumble;



import java.util.ArrayList;
import java.util.List;
import rp13.search.interfaces.Puzzle;


public class Jumble implements Puzzle{
	private static String a;
	private List<Character> c_board ;
	
	/**
	 * Create an puzzle in its default configuration
	 * @return 
	 */
	private Jumble(String a) {
	    Jumble.a = a;
		c_board = new ArrayList<Character>();
		for (int i = 0; i < a.length(); i++) {
			c_board.add(a.charAt(i));
		}
			
	}
	/**
	 * create new copy method
	 * @param _that copy that jumble
	 */
	public Jumble(Jumble _that) {
		
		this.c_board = new ArrayList<Character>();
		for (int i = 0; i < _that.c_board.size(); i++) {
			this.c_board.add(_that.c_board.get(i));
		}
	}

	/**
	 * return a order puzzle
	 * @param a the string
	 * @return order jumble with the string
	 */
	public static Jumble orderedPuzzle(String a) {
		return new Jumble(a);
	}
	/**
	 * make a move
	 * @param _move swap index
	 */
	public void makeMove(JumbleMove _move) {
		
			// where should the blank end up
			if(isPossibleMove(_move)){
			// get the piece that was in that position
			char left = c_board.get(_move.getX());
			char right = c_board.get(_move.getY());
			// then swap them around
//			toSwapWith = c_board[i];
			 c_board.set(_move.getX() , right);
			 c_board.set(_move.getY() , left);
			}
				
		}
	/**
	 * because the moves generator could be maximum to be 10 character
	 * if those index are over the array size then is false
	 * @param _move input
	 * @return if the move is possible for the jumble
	 */
	public boolean isPossibleMove(JumbleMove _move){
		return _move.getX()<c_board.size() &&_move.getY()<c_board.size();
	}

	public int getLength(){
		return a.length();
	}
	/**
	 * Creates a randomised jumble using the given number of random moves.
	 * @return 
	 */
	public static Jumble randomPuzzle(String a) {
		 
		  Jumble random = new Jumble(orderedPuzzle(a));

		for (int i = 0; i < a.length()*a.length()*a.length(); i++) { //random several moves
			random.makeMove(JumbleMove.Random(a.length()));
		}
			return random;
		}
	
	
	/**
	 * return the state of the puzzle
	 */
	public String toString() {
		StringBuilder sp = new StringBuilder();
		for (char letter : c_board) {
			sp.append(letter);
			
		}
		return sp.toString();
	}

	
	/**
	 * equals methods
	 * 
	 */
	@Override
	public boolean equals(Object _that){
		if (_that instanceof Jumble) {
			Jumble that = (Jumble) _that;
		
		for(int i =0; i<this.c_board.size();i++){
			if(this.c_board.get(i)!=that.c_board.get(i))
				return false;
			}
		}
		return true;
	}
	/**
	 * isGoal if this is equals to ordered string
	 */
	@Override
	public boolean isGoal() {
			
		return this.equals(orderedPuzzle(a));
	}


	/**
	 * test some basic stuff
	 * 
	 */
	public static void main(String[] args) {
		Jumble p = Jumble.orderedPuzzle("java");
		System.out.println(p);
		Jumble b = new Jumble(p);
		System.out.println(b.equals(p));
		for (JumbleMove moves : JumbleMove.values(4)) {
			if(b.isPossibleMove(moves)){
			 b = new Jumble(p);
			b.makeMove(moves);
			System.out.println(moves);
			System.out.println(b);
			}
		}
		Jumble r = Jumble.randomPuzzle("java");
		System.out.println(r);
		System.out.println(p.isGoal());
		
		

	}


}
