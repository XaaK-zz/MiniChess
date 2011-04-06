public class CalculatedMove extends Move 
{
	public int MoveValue;
	
	public CalculatedMove(Move move, int moveValue) 
	{
		super(move.fromSquare, move.toSquare);
		
		MoveValue = moveValue;
	}
/*
	public CalculatedMove(int fromX, int fromY, int toX, int toY, int moveValue) 
	{
		super(fromX, fromY, toX, toY);
		
		MoveValue = moveValue;
	}
	*/

	public CalculatedMove(int moveValue) 
	{
		MoveValue = moveValue;
	}

}
