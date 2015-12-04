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
	* A constructor that initializes board as a deep copy of another GameBoard and adds the given number to the given spot.
	* @param g		The GameBoard which is to be copied
	* @param row		The row of the spot where the number will be added
	* @param col		The col of the spot where the number will be added
	* @param n		The number which will be placed in the given spot
	*/
	public GameBoard(GameBoard g, int row, int col, int n)
	{
		board = new int[9][9];
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
				board[r][c] = g.board[r][c];
		}
		board[row][col] = n;
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
	* Returns the coordinates of the spot on the board that can have the fewest numbers placed in it, the amount of numbers that can go in the spot, and those numbers.
	* @return		The row of the most constrained pot followed by the column of the most constrained spot, followed by the amount of numbers in the list, followed by the numbers which can go there
	*/
	public int[] mostConstrainedSpot()
	{
	//leastRowColNums[0] and leastRowColNums[1] are row and column, leastRowColNums[2] is the amount of numbers that can go there
	//and leastRowColNums[3 through 11] are either the number or a 0 if the number cant go there.
		int[] leastRowColNums = new int[12];
		leastRowColNums[0] = -1;
		leastRowColNums[1] = -1;
		int[] holder = new int[10];
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				for (int i = 1; i < 10 && board[r][c] == 0; i++)
				{
					if(canPlace(r, c, i))
					{
						holder[i] = i;
						holder[0] += 1;
					}
				}
				if ((holder[0] < leastRowColNums[2] || leastRowColNums[2] == 0) && holder[0] != 0)
				{
					leastRowColNums[2] = holder[0];
					leastRowColNums[0] = r;
					leastRowColNums[1] = c;
					for (int i = 0; i < 9; i++)
						leastRowColNums[i + 3] = holder[i + 1];
				}
				if (holder[0] == 1)
					return leastRowColNums;
				holder = new int[10];
			}
		}
		return leastRowColNums;
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