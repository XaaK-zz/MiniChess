///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////

public class Undo 
{
	public Move reversedMove = null;
	public char destPiece = '.';
	public char movingPiece = '.';
	
	public Undo(Move move, char _movingPiece, char _destPiece)
	{
		if(move != null)
		{
			reversedMove = new Move();
			reversedMove.toX = move.fromX;
			reversedMove.toY = move.fromY;
			reversedMove.fromX = move.toX;
			reversedMove.fromY = move.toY;
			
			destPiece = _destPiece;
			movingPiece = _movingPiece;
		}
	}
}
