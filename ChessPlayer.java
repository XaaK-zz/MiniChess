import java.util.Random;
import java.util.Vector;

public class ChessPlayer 
{
	private char color;
	final int MAXDEPTH = 6;
	Boolean debug = false;
	Random randomGen = new Random(); 
	
	public ChessPlayer(char _color)
	{
		color = _color;
	}
	
	public Move GetNextMove(State currentBoard, long timeLimitInMS) throws MoveException
	{
		long start = System.currentTimeMillis();
		
		CalculatedMove tempMove = this.NegaMaxCalc(currentBoard, null, start+timeLimitInMS, MAXDEPTH);
		if(debug)
			System.out.println("Move Selected: " + tempMove.toString());
				
		return (Move)tempMove;
	}
	
	private String printWithDepth(String output, int depth)
	{
		String temp = "";
		for(int x=MAXDEPTH;x>depth;x--)
		{
			temp += "   ";
		}
		return temp + output;
	}
	
	private CalculatedMove NegaMaxCalc(State boardPosition, Move currentMove, long maxTimeMS, int currentDepth) throws MoveException
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
		
		if(boardPosition.IsGameOver() )
		{
			if(debug)
				System.out.println(printWithDepth("End of recursion because game over: " + currentMove.toString() + " Eval: " + (-boardPosition.Eval(boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
			
			return new CalculatedMove(-boardPosition.Eval(boardPosition.currentPlayer == 'W' ? 'W' : 'B'));
		}
		else if(currentDepth <= 0 || (maxTimeMS < System.currentTimeMillis() && currentMove != null))
		{
			if(debug)
				System.out.println(printWithDepth("End of recursion: " + currentMove.toString() + " Eval: " + (-boardPosition.Eval(boardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
			return new CalculatedMove(-boardPosition.Eval(boardPosition.currentPlayer == 'W' ? 'B' : 'W'));
		}
		else
		{
			Vector<Move> moves = boardPosition.MoveGen();
			for (Move oMove: moves)
			{
				State newBoardPosition = (State)DeepCopy.copy(boardPosition);
	            
				newBoardPosition.Move(oMove);
				CalculatedMove tempMove = NegaMaxCalc(newBoardPosition,oMove, maxTimeMS,currentDepth - 1);
				if(tempMove != null)
					moveCollection.add(new CalculatedMove(oMove,-tempMove.MoveValue));
				else
				{
					if(debug)
						System.out.println(printWithDepth("tempMove null.. Eval: " + " Eval: " + (-boardPosition.Eval(newBoardPosition.currentPlayer == 'W' ? 'B' : 'W')),currentDepth));
					
					moveCollection.add(new CalculatedMove(oMove,-newBoardPosition.Eval(newBoardPosition.currentPlayer == 'W' ? 'B' : 'W')));
				}
			}
			int minValue = -10000;
			Vector<CalculatedMove> tempMoves = new Vector<CalculatedMove>();
			CalculatedMove tempMove = null;
			
			if(debug)
				System.out.println(printWithDepth("Evaluating all returned moves...",currentDepth-1));
			
			for (CalculatedMove tempCalcMove: moveCollection)
			{
				if(debug)
					System.out.println(printWithDepth("tempCalcMove: Current Player: " + boardPosition.currentPlayer + " Move: " + 
						tempCalcMove.toString() + " - Value: " + tempCalcMove.MoveValue,currentDepth-1));
				
				if(tempCalcMove.MoveValue > minValue)
				{
					tempMoves.clear();
					tempMoves.add(tempCalcMove);
					minValue = tempCalcMove.MoveValue;
				}
				else if(tempCalcMove.MoveValue == minValue)
				{
					tempMoves.add(tempCalcMove);
				}
			}
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
}
