package search;



import java.util.Arrays;
import java.util.Random;


public class Jumble {
	public static String a;
	public static String c;
	
	/**
	 * Explicit enumeration of moves the blank tile can take.
	 * 
	 * @author nah
	 * 
	 */

	/**
	 * The pieces in the puzzle, represented as an array.
	 */
	protected final char[] s_board = new char[4];
	
	private static char[] c_board = new char[4];
	
	private int m_currentinx = 0;

	/**
	 * Create an puzzle in its default configuration
	 * @return 
	 */
	private Jumble() {
	    a = "abcd";
		int x = a.length();
		for (int i = 0; i < x; i++) {
			c_board[i] = a.charAt(i);
		}
		
		
		
	}
	public  Jumble(Jumble _that) {
		
		this.c_board = new char[4];
		for(int i =0 ; i<4 ; i++)
			c_board[i] = _that.c_board[i];
	}

	public boolean equals() {

		return Arrays.equals(s_board, c_board);	

	}
	public static Jumble orderedPuzzle() {
		return new Jumble();
	}
	public boolean isPossibleMove(JumbleMove _move) {
		/**
		int newinx = 0;
		if(_move.m_move == 5)
		m_currentinx++;
		else if(_move.m_move == -5)
			m_currentinx--;
		else
		newinx = m_currentinx + _move.m_move;
			*/
		return (_move.getLeft() >= 0) && (_move.getLeft() <= a.length()) && (_move.getRight() >= 0) && (_move.getRight() <= a.length());


	}

	public boolean makeMove(JumbleMove _move) {
		if (isPossibleMove(_move)) {
			// where should the blank end up
			
			// get the piece that was in that position
			char toSwapWith = c_board[_move.getLeft()];
			// then swap them around
//			toSwapWith = c_board[i];
			c_board[_move.getLeft()] = c_board[_move.getRight()];
			c_board[_move.getRight()] = toSwapWith;
			
				
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Return an puzzle with the pieces in the correct order
	 * 
	 * @return Returns an ordered Puzzle.
	 */
	public String Answer()
	{
		return a;
	}

	/**
	 * Creates a randomised eight puzzle using the given number of random moves.
	 * @return 
	 */
	public static Jumble randomPuzzle() {
		  Random rnd = new Random(10);
		  Jumble random = new Jumble(orderedPuzzle());
	        int ran; 
	        char temp;
		
		for (int i = 0; i < rnd.nextInt(); i++) {
			random.makeMove(JumbleMove.Random());
			}
		//c.valueOf(c_board);
			return random;
		}
	
	
	public String toString() {
		StringBuilder sp = new StringBuilder();
		for (char letter : c_board) {
			sp.append(letter);
			
		}
		return sp.toString();
	}

	private String valueOf(char[] c_board2) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		Jumble p = Jumble.randomPuzzle();
		//System.out.println(a);
		System.out.println(p);
		
		for (JumbleMove move : JumbleMove.values()) {
			p.makeMove(move);
			
			System.out.println(move.toString());
			System.out.println(p);
		}
		

	}





}
