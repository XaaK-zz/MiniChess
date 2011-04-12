///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.util.Vector;

//////////////////////////////////////////////////////////////////////////////////////
//BasePlayer class
//	Abstract base class for the Player.  Contains several low-level routines for
//		selecting a move and evaluating a board position
//////////////////////////////////////////////////////////////////////////////////////
public abstract class BasePlayer 
{
	//what side this player is one
	protected char color;
	//Max search depth
	protected int MAXDEPTH = 4;
	//Debug mode flag
	protected Boolean debug = false;
	//random generator
	protected Random randomGen = new Random(); 
	
	/////////////////////////////////////////////////////////////////////////////////
	//BasePlayer
	//	Default constructor
	////////////////////////////////////////////////////////////////////////////////
	public BasePlayer()
	{
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//BasePlayer
	//	Constructor with color parameter
	////////////////////////////////////////////////////////////////////////////////
	public BasePlayer(char _color)
	{
		color = _color;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//GetNextMove
	//Input: currentBoard (State): Current board position to evaluate
	//		 timeLimitInMS (long): How many milliseconds are allowed for this move
	//Output: Move object representing the next move by this player
	//Purpose: Using the NegaMaxCalc routine, select the best possible move.
	////////////////////////////////////////////////////////////////////////////////
	public Move GetNextMove(State currentBoard, long timeLimitInMS) throws MoveException
	{
		//get the current time
		long start = System.currentTimeMillis();
		
		//Use NegaMaxCalc to find the best move
		CalculatedMove tempMove = this.NegaMaxCalc(currentBoard, null, start+timeLimitInMS, MAXDEPTH);
		
		//Show the move selected....
		if(debug)
			System.out.println("Move Selected: " + tempMove.toString());
			
		return (Move)tempMove;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//Eval
	//Input: boardPosition (State): Current board position to evaluate
	//		 evalColor (char): Color of the pieces to evalutate
	//Output: int representing the evalutaion value of the input boardPosition for the
	//		requested color 
	//Purpose: Evalutate the requested board position for the requested color
	//	Note: This is an abstract method and will be overridden in each derived
	//		player class, in order to customize the evaluation routine for 
	//		best effectiveness.
	////////////////////////////////////////////////////////////////////////////////
	protected abstract int Eval(State boardPosition, char evalColor);
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//NegaMaxCalc
	//Input:
	//	boardPosition (State): Current state of the board for this round of negamax
	//	currentMove (Move): Move object that led to the current board position
	//	maxTimeMS (long): How long we have to calculate the negamax value in MS
	//	currentDepth (int): Current depth we are at in the depth-first search
	//Output:
	//	CalculatedMove object containing a move to perform as well as the resulting value
	//		of the board position
	//Purpose: Perform a depth-first traversal of the board state.
	/////////////////////////////////////////////////////////////////////////////////////////////
	protected CalculatedMove NegaMaxCalc(State boardPosition, Move currentMove, long maxTimeMS, int currentDepth) throws MoveException
	{
		if(debug)
		{
			if(currentMove != null)
				System.out.println(printWithDepth("NegaMaxCalc - currentMove: " + currentMove.toString() + " Depth: " + currentDepth +
						" Current Player: " + boardPosition.currentPlayer,currentDepth));
			else
				System.out.println(printWithDepth("NegaMaxCalc - Depth: " + currentDepth + " Current Player: " + boardPosition.currentPlayer,currentDepth));
		}
		
		Vector<CalculatedMove> moveCollection = new Vector<CalculatedMove>();
		
		//Check to see if this is an ending position
		if(boardPosition.IsGameOver() )
		{
			if(debug)
				System.out.println(printWithDepth("End of recursion because game over: " + currentMove.toString() + " Eval: " + (-Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
			
			return new CalculatedMove(-Eval(boardPosition,boardPosition.currentPlayer));
		}
		else if(currentDepth <= 0 || (maxTimeMS < System.currentTimeMillis() && currentMove != null))
		{
			//Are we at the depth OR are we out of time
			if(debug)
				System.out.println(printWithDepth("End of recursion: " + currentMove.toString() + " Eval: " + (-Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
			
			//Evaluate the current position
			return new CalculatedMove(-Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W'));
		}
		else
		{
			//Get all the moves from this position
			Vector<Move> moves = boardPosition.MoveGen();
			for (Move oMove: moves)
			{
				//Create a copy of the board position
				State newBoardPosition = (State)DeepCopy.copy(boardPosition);
	            
				//Apply the move
				newBoardPosition.Move(oMove);
				
				//Calculate the negamax of this move
				CalculatedMove tempMove = NegaMaxCalc(newBoardPosition,oMove, maxTimeMS,currentDepth - 1);
				
				//If no valid moves
				if(tempMove != null)
					moveCollection.add(new CalculatedMove(oMove,-tempMove.MoveValue));
				else
				{
					if(debug)
						System.out.println(printWithDepth("tempMove null.. Eval: " + " Eval: " + (-Eval(boardPosition,newBoardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
					
					//Add this possible move to the move collection
					moveCollection.add(new CalculatedMove(oMove,-Eval(newBoardPosition,newBoardPosition.currentPlayer == 'W' ? 'B' : 'W')));
				}
			}
			
			//Setup data for evaluating moves
			int minValue = Integer.MIN_VALUE;
			Vector<CalculatedMove> tempMoves = new Vector<CalculatedMove>();
			CalculatedMove tempMove = null;
			
			if(debug)
				System.out.println(printWithDepth("Evaluating all returned moves...",currentDepth-1));
			
			//Evalutate each returned move
			for (CalculatedMove tempCalcMove: moveCollection)
			{
				if(debug)
					System.out.println(printWithDepth("tempCalcMove: Current Player: " + boardPosition.currentPlayer + " Move: " + 
						tempCalcMove.toString() + " - Value: " + tempCalcMove.MoveValue,currentDepth-1));
				
				//If found a better move
				if(tempCalcMove.MoveValue > minValue)
				{
					//Clear the collection and add this move
					tempMoves.clear();
					tempMoves.add(tempCalcMove);
					//Set the new min value
					minValue = tempCalcMove.MoveValue;
				}
				else if(tempCalcMove.MoveValue == minValue)
				{
					//Same value - add to the collection
					tempMoves.add(tempCalcMove);
				}
			}
			
			//Select a random move from the set - or just get one, if only one move available
			if(tempMoves.size() > 1)
				tempMove = tempMoves.get(randomGen.nextInt(tempMoves.size()-1));
			else if(tempMoves.size() == 1)
				tempMove = tempMoves.get(0);
			
			if(debug)
				if(tempMove != null)
					System.out.println(printWithDepth("Returning move from NegaMaxCalc - " + tempMove.toString(),currentDepth-1));
			
			return tempMove;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	//printWithDepth
	//Input: output (String): String to display on the console
	//		 depth (int): Current depth of the recursion tree
	//Output: String to display
	//Purpose: This utility method is use for formatting the debug output into different indented
	//		"tiers" - making it easier to understand what debug output relates to the specific
	//		level of recursion
	/////////////////////////////////////////////////////////////////////////////////////////
	protected String printWithDepth(String output, int depth)
	{
		String temp = "";
		for(int x=MAXDEPTH;x>depth;x--)
		{
			temp += "   ";
		}
		return temp + output;
	}
}
