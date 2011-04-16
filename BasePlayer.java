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
	protected int MAXDEPTH = 6;
	//Debug mode flag
	protected Boolean debug = true;
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
		Boolean outOfTime = false;
		int d = 1;
		CalculatedMove storedMove = null;
		
		do
		{
			if(debug)
				System.out.println("--------Starting level " + d + " -------------------");
			
			//Use NegaMaxCalc to find the best move
			CalculatedMove tempMove = this.NegaMaxCalc(currentBoard, start+timeLimitInMS, d, 
					Integer.MIN_VALUE, Integer.MAX_VALUE);
			//CalculatedMove tempMove = this.NegaMaxCalc(currentBoard, null, start+timeLimitInMS, d, 
			//		Integer.MAX_VALUE, Integer.MIN_VALUE);
			if(tempMove != null)
			{
				storedMove = tempMove;
				/*
				if(tempMove.MoveValue == Integer.MAX_VALUE)
				{
					if(debug && storedMove != null)
						System.out.println("MAX VALUE");
					outOfTime = true;
				}
				*/
			}
			else
				outOfTime = true;
			d++;
			if(d >= MAXDEPTH)
				outOfTime = true;
			if(debug && storedMove != null)
				System.out.println("Move Selected at level d: " + d + " storedMove: " + storedMove.toString());
			
		}
		while(!outOfTime);
		
		//System.out.println("Depth searched: " + d);
		
		//Show the move selected....
		if(debug && storedMove != null)
			System.out.println("Move Selected: " + storedMove.toString());
			
		return (Move)storedMove;
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
	//  alpha (int): TODO
	//	beta (int): TODO
	//Output:
	//	CalculatedMove object containing a move to perform as well as the resulting value
	//		of the board position
	//Purpose: Perform a depth-first traversal of the board state.
	/////////////////////////////////////////////////////////////////////////////////////////////
	protected CalculatedMove NegaMaxCalc(State boardPosition, long maxTimeMS, 
			int currentDepth, int alpha, int beta) throws MoveException
	{
		if(debug)
		{
			//if(currentMove != null)
			//	System.out.println(printWithDepth("NegaMaxCalc - currentMove: " + currentMove.toString() + " Depth: " + currentDepth +
			//			" Current Player: " + boardPosition.currentPlayer,currentDepth));
			//else
			//	System.out.println(printWithDepth("NegaMaxCalc - Depth: " + currentDepth + " Current Player: " + boardPosition.currentPlayer,currentDepth));
		}
		
		Vector<CalculatedMove> moveCollection = new Vector<CalculatedMove>();
		
		//Check to see if this is an ending position
		if(boardPosition.IsGameOver() )
		{
			//if(debug)
			//	System.out.println(printWithDepth("End of recursion because game over: Eval: " + (Eval(boardPosition,boardPosition.currentPlayer)),currentDepth));
			
			return new CalculatedMove(Eval(boardPosition,boardPosition.currentPlayer));
		}
		else if(maxTimeMS < System.currentTimeMillis())
		{
			return null;
		}
		else if(currentDepth <= 0)
		{
			//Are we at the depth OR are we out of time
			//if(debug)
			//	System.out.println(printWithDepth("End of recursion: Eval: " + (Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
			
			//Evaluate the current position
			return new CalculatedMove(Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'W' : 'B'));
		}
		else
		{
			//Get all the moves from this position
			Vector<Move> moves = boardPosition.MoveGen();
			if(moves.size() == 0)
				return null;
			Move storedMove = new Move();
			int alphaTemp = alpha;
			
			//int betaTemp = Integer.MIN_VALUE;
			for (Move oMove: moves)
			{
				//if(debug)
				//	System.out.println(printWithDepth("Move Loop: Move: " + oMove.toString() + " beta value: " + beta + " alphaTemp: " + alphaTemp,currentDepth));
				
				//Create a copy of the board position
				//State newBoardPosition = (State)DeepCopy.copy(boardPosition);
	            
				//Apply the move
				Undo undoMove = boardPosition.Move(oMove);
				
				
				int value = -1;
				CalculatedMove tempMove = NegaMaxCalc(boardPosition, maxTimeMS,currentDepth - 1, -beta, -alphaTemp);	
				
				//if(debug && tempMove != null)
				//	System.out.println(printWithDepth("tempMove: " + tempMove.toString(),currentDepth));
				
				//if(boardPosition.IsGameOver())
				//	value = -Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'W' : 'B');
				//else
				//	value = -Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W');
				if(tempMove != null)
				{
					tempMove.MoveValue *= -1;
					//alphaTemp = java.lang.Math.max(storedMove.MoveValue, alphaTemp);
					if(tempMove.MoveValue > alphaTemp)
					{
						//if(debug)
						//	System.out.println(printWithDepth("tempMove.MoveValue > alphaTemp: " + tempMove.MoveValue + " " + alphaTemp,currentDepth));
						if(debug)
						{
							System.out.println(currentDepth + " Keeping Move: " + oMove + " Value: " + tempMove.MoveValue + " Board After Move:");
							boardPosition.ShowBoard(System.out);
						}
						alphaTemp = tempMove.MoveValue;
						storedMove.fromX = oMove.fromX;
						storedMove.toX = oMove.toX;
						storedMove.fromY = oMove.fromY;
						storedMove.toY = oMove.toY;
					}
					if(alphaTemp >= beta)
					{
						if(debug && storedMove != null)
							System.out.println(printWithDepth("beta cutoff",currentDepth));
						
						//alphaTemp = tempMove.MoveValue;
						storedMove.fromX = oMove.fromX;
						storedMove.toX = oMove.toX;
						storedMove.fromY = oMove.fromY;
						storedMove.toY = oMove.toY;
						boardPosition.UndoMove(undoMove);
						break;
					}
					
				}
				//if(value < alphaTemp)
				//	alphaTemp = value;
				//if(value > betaTemp)
				//	betaTemp = value;
				
				//if(value > beta)
				//{
					//if(debug)
					//	System.out.println(printWithDepth("value >= beta -- value: " + value,currentDepth));
					
					//moveCollection.add(new CalculatedMove(oMove,-value));
				//}
				//else
				//if(value <= beta)
				boardPosition.UndoMove(undoMove);
			}
			//	if(alphaTemp >= beta)
			//	{
			//		if(debug)
			//		{
			//			System.out.println(printWithDepth("alphaTemp >= beta -- alphaTemp: " + alphaTemp + " beta: " 
			//					+ beta + " Move: " + oMove,currentDepth));
			//		}
			//		return new CalculatedMove(oMove,alphaTemp);
			//	}
				
				/*
				if(alphaTemp < beta )
				{
					if(debug)
						System.out.println(printWithDepth("alphaTemp < beta -- alphaTemp: " + alphaTemp + " beta: " + beta,currentDepth));
					
					//beta = value;
				
					//Calculate the negamax of this move
					CalculatedMove tempMove = NegaMaxCalc(boardPosition,oMove, maxTimeMS,currentDepth - 1, -beta, -alphaTemp);
				
					//If no valid moves
					if(tempMove != null)
					{
						if(debug)
							System.out.println(printWithDepth("tempMove found: " + tempMove.toString() + " MoveValue: " + tempMove.MoveValue,currentDepth));
					
						//if(tempMove.MoveValue >= alphaTemp)
						//	alphaTemp = tempMove.MoveValue;
					
						moveCollection.add(new CalculatedMove(oMove,-tempMove.MoveValue));
						
					}
					else
					{
						if(debug)
							System.out.println(printWithDepth("tempMove null.. Eval: " + " Eval: " + (Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
						
						//Add this possible move to the move collection
						moveCollection.add(new CalculatedMove(oMove,Eval(boardPosition,boardPosition.currentPlayer == 'W' ? 'B' : 'W')));
					}
					
				}
				*/
				//boardPosition.UndoMove(undoMove);
			if(debug)
				System.out.println(currentDepth + " End... -> storedMove: " + storedMove);
			if(storedMove == null)
				return new CalculatedMove(alphaTemp);
			else
				return new CalculatedMove(storedMove,alphaTemp);
			/*
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
			*/
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
		for(int x=0;x<depth;x++)
		{
			temp += "   ";
		}
		return temp + depth+ " " + output;
	}
}
