import java.io.Serializable;
import java.util.Vector;

///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
//CalculatedMove
//	Extended Move class that contains both a Move and a value for the move
//	Used by the NegaMax routine to enable both a move and a value to be returned
////////////////////////////////////////////////////////////////////////////////////
public class CalculatedMove extends Move implements Serializable
{
	private static final long serialVersionUID = 2930642963544059144L;
	
	//Value for the given move
	public int MoveValue;
	
	//Constructor - with move and value
	public CalculatedMove(Move move, int moveValue) 
	{
		super(move.fromX, move.fromY, move.toX, move.toY);
		
		MoveValue = moveValue;
	}

	//Constructor with just a value
	//	This can be required if no move was found but we still need to return
	//		a value for the given board position
	public CalculatedMove(int moveValue) 
	{
		MoveValue = moveValue;
	}

}
