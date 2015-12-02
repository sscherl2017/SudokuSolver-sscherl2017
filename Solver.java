import java.util.ArrayList;

/**
* This class contains a main methods which executes the solving process of a given board.
*/
public class Solver
{
	/**
	* Takes in a sudoku board through the command line and outputs the solution. It adds the initial board to the stack.
	* Then it removes the top board from the stack, checks if it is solved, finds the most constrained spot on the board, adds all possible numbers
	* to the board at the most constrained spot, then adds all these back to the stack and repeats the process until the stack is empty.
	*/
	public static void main(String[] args)
	{
		final long startTime = System.currentTimeMillis();
		GameBoard input = new GameBoard("SudokuBoard" + Integer.parseInt(args[0]) + ".csv");
		Stack<GameBoard> stack = new LinkedList<GameBoard>();
		stack.push(input);
		while (stack.isEmpty() != true)
		{
			GameBoard current = stack.pop();
			if (current.solved())
			{
				System.out.println("\n" + "This is the solution to the given board" + "\n" + current);
				final long endTime = System.currentTimeMillis();
				System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds" + "\n");
				return;
			}
			if (current.mostConstrainedSpot()[0] != -1)
			{
				int[] mostConstrained = current.mostConstrainedSpot();
				Vector<Integer> possibleNumbers = current.possibleNumbers(mostConstrained[0], mostConstrained[1]);
				for (Integer i: possibleNumbers)
				{
					GameBoard possibility = new GameBoard(current);
					possibility.place(mostConstrained[0], mostConstrained[1], i);
					stack.push(possibility);
				}
			}
		}
		System.out.println("The given board is unsolvable");
	}
}