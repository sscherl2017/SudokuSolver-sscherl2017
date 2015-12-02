import java.io.*;

import java.util.Scanner;

/**
* This class contains an int[][], which is 9 x 9, and represents a sudoku board and contains many methods to perform various operations
* on the sudoku board.
*/
public class GameBoard
{
	/** The int[][] which represents the sudoku board. */
	private int[][] board;
	
	/**
	* A constructor that initializes board with the data in the given file.
	* @param pathname		The name of the file which is to be read from
	*/
	public GameBoard(String pathname)
	{
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		board = new int[9][9];
		int index = 0;
		while(input.hasNextLine())
		{
			String s = input.nextLine();
			String[] helper = s.split(",");
			for (int i = 0; i < 9; i++) 
				board[index][i] = Integer.parseInt(helper[i]);
			index++;
		}
	}
	
	/**
	* A constructor that initializes board as a deep copy of another GameBoard.
	* @param g		The GameBoard which is to be copied
	*/
	public GameBoard(GameBoard g)
	{
		board = new int[9][9];
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
				board[r][c] = g.board[r][c];
		}
	}
	
	/** 
	* Returns true if the board is solved, false if it is not.
	* @return		True if the board is sovled, false if it is not
	*/
	public boolean solved()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (board[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	/**
	* Puts the given int in the given spot in the board.
	* @param r		The row of the spot where the int will be placed
	* @param c		The column of the spot where the int will be placed
	* @param n		The int that will be placed at the given spot
	*/
	public void place(int r, int c, int n)
	{
		board[r][c] = n;
	}
	
	/**
	* Returns true if the given number can be placed at the given spot, false if it cannot.
	* @param r		The row of the spot where the int will be tested at
	* @param c		The column of the spot where the int will be tested at
	* @param n		The int that will be tested at the given spot
	* @return		True if the int can be placed at the given spot, false if it cannot be
	*/
	public boolean canPlace(int r, int c, int n)
	{
		if (board[r][c] != 0)
			return false;
		for (int i = 0; i < 9; i++)
		{
			if (board[r][i] == n || board[i][c] == n)
				return false;
		}
		for (int row = r - (r % 3); row < (r - (r % 3)) + 3; row++)
		{
			for (int col = c - (c % 3); col < (c - (c % 3)) + 3; col++)
			{
				if(board[row][col] == n)
					return false;
			}
		}
		return true;
	}
	
	/**
	* Returns an Vector<Integer> that contains all the numbers that can be placed at a given spot.
	* @param r		The row of the spot that will be tested
	* @param c		The column of the spot that will be tested
	* @return		The Vector<Integer> that contains all the numbers that can be placed at the given spot
	*/
	public Vector<Integer> possibleNumbers(int r, int c)
	{
		Vector<Integer> output = new Vector<Integer>(9);
		for (int i = 0; i < 9; i++)
		{
			if (canPlace(r, c, i + 1))
				output.add(i + 1);
		}
		return output;
	}
	
	/**
	* Returns the coordinates of the spot on the board that can have the fewest numbers placed in it.
	* @return		The row of the most constrained pot followed by the column of the most constrained spot
	*/
	public int[] mostConstrainedSpot()
	{
		int[] leastRowCol = new int[2];
		leastRowCol[0] = -1;
		leastRowCol[1] = -1;
		int least = 10;
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				if (possibleNumbers(r, c).size() < least && possibleNumbers(r, c).size() != 0)
				{
					least = possibleNumbers(r, c).size();
					leastRowCol[0] = r;
					leastRowCol[1] = c;
				}
			}
		}
		return leastRowCol;
	}
	
	/**
	* Returns a String representation of the board.
	* @return		A String representation of the board
	*/
	public String toString()
	{
		String output = "";
		output += "------------- ------------- -------------\n";
		for (int i = 0; i < 9; i++)
		{
			output += "|";
			for (int j = 0; j < 9; j++)
			{
				output += " ";
				if (board[i][j] == 0)
					output += "  ";
				else
					output += board[i][j] + " ";
				output += "|";
				if ((j + 1) % 3 == 0 && j != 8)
					output += " |";
			}
			output += "\n------------- ------------- -------------\n";
			if ((i + 1) % 3 == 0 && i != 8)
				output += "------------- ------------- -------------\n";
		}
		return output;
	}
}