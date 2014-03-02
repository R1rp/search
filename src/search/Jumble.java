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
	
	public enum JumbleMove {
		 LEFT(-1), RIGHT(1), INLEFT(-5), INRIGHT(5);

		private final int m_move;
		private JumbleMove(int _move) {
			m_move = _move;
		}


		/**
		 * Cached result of values such that copy isn't done every time.
		 */
		private static final JumbleMove[] VALUES = values();

		/***
		 * Count of values in list
		 */
		private static final int SIZE = VALUES.length;
	}
	/*
	public enum IndexMove {
		INLEFT(-1), INRIGHT(1), INLEFT2(-2), INRIGHT2(2), STAY(0);

		private final int i_move;
		private IndexMove(int _move) {
			i_move = _move;
		}
		private static final IndexMove[] VALUES = values();

	}
	*/
	
	/**
	 * The pieces in the puzzle, represented as an array.
	 */
	protected final char[] s_board = new char[20];
	
	private static char[] c_board;
	
	private int m_currentinx = 0;

	/**
	 * Create an puzzle in its default configuration
	 * @return 
	 */
	private Jumble() {
	    a = "rpSearch";
		int x = a.length();
		for (int i = 0; i < x; i++) {
			s_board[i] = a.charAt(i);
		}
		
		
		
	}
	public Jumble(Jumble _that) {
		
		c_board = Arrays.copyOf(s_board, a.length());
		m_currentinx = _that.m_currentinx;
	}

	public boolean equals() {

				return Arrays.equals(s_board, c_board);
			


	}
	public static Jumble orderedPuzzle() {
		return new Jumble();
	}
	public boolean isPossibleMove(JumbleMove _move) {
		int newinx = 0;
		if(_move.m_move == 5)
		m_currentinx++;
		else if(_move.m_move == -5)
			m_currentinx--;
		else
		newinx = m_currentinx + _move.m_move;
			
		return (newinx >= 0) && (newinx <= a.length()) && (m_currentinx >= 0) && (m_currentinx <= a.length());


	}

	public boolean makeMove(JumbleMove _move) {
		int newinx = 0;
		if (isPossibleMove(_move)) {
			// where should the blank end up
			if(_move.m_move == 5)
				m_currentinx++;
				else if(_move.m_move == -5)
					m_currentinx--;
				else
				{
			 newinx = m_currentinx + _move.m_move;
			// get the piece that was in that position
			char toSwapWith = c_board[newinx];
			// then swap them around
//			toSwapWith = c_board[i];
			c_board[newinx] = c_board[m_currentinx];
			c_board[m_currentinx] = toSwapWith;
			m_currentinx = newinx;
				}
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
	public static void randomPuzzle() {
		  Random rnd = new Random();

	        int ran; 
	        char temp;
		
		for (int i = 0; i < c_board.length; i++) {
			
			ran =  rnd.nextInt(c_board.length);
			temp = c_board[i];
			c_board[i] = c_board[ran];
			c_board[ran] = temp;
		}
		c.valueOf(c_board);
		}
	
	
	public String toString() {
		return c;
	}

	public static void main(String[] args) {
		Jumble p = Jumble.orderedPuzzle();
		System.out.println(a);

		
		for (JumbleMove move : JumbleMove.values()) {
			p.makeMove(move);
			System.out.println(move);
			System.out.println(p);
		}

	}





}
