/////////////////////////////////////////////////////////////////////////
//Move 
//	Class representing a move (from Square->to Square)
////////////////////////////////////////////////////////////////////////
public class Move 
{
	//from/to Square properties
	public Square fromSquare;
	public Square toSquare;
	
	public Move()
	{
		fromSquare = null;
		toSquare = null;
	}
	
	//////////////////////////////////////////////////////////
	//Move Constructor
	//Input: from (Square): Square object representing the
	//			source square this move originates from
	//		to (Square): Square object representing the 
	//			destination square for this move
	/////////////////////////////////////////////////////////
	public Move(Square from,Square to)
	{
		fromSquare = from;
		toSquare = to;
	}
	
	//////////////////////////////////////////////////////////
	//Move Constructor
	//Input: fromX (int): x value of the source square 
	//			this move originates from
	//		fromY (int): y value of the source square 
	//			this move originates from
	//		toX (int): x value of the destination square 
	//			this move is for
	//		fromY (int): y value of the destination square 
	//			this move is for
	/////////////////////////////////////////////////////////
	public Move(int fromX, int fromY, int toX, int toY)
	{
		fromSquare = new Square(fromX,fromY);
		toSquare = new Square(toX,toY);
	}
	
	//////////////////////////////////////////////////////////////
	//toString
	//	Overriding toString to allow easy printing of the move
	///////////////////////////////////////////////////////////////
	public String toString()
	{
		return ("Move: From:" + fromSquare.toString() + " To:" + toSquare.toString());
	}
	
	///////////////////////////////////////////////////////////////
	//equals
	//	Overriding the equals method so Move objects can be used in 
	//		collections easily.  
	//	Moves are defined as equal if the from and to squares match
	////////////////////////////////////////////////////////////////
	public boolean equals(Object other) 
	{ 
		Move oTemp = (Move)other;
		if(this.fromSquare.x == oTemp.fromSquare.x &&
		   this.fromSquare.y == oTemp.fromSquare.y &&
		   this.toSquare.x == oTemp.toSquare.x &&
		   this.toSquare.y == oTemp.toSquare.y)
			return true;
		else
			return false;
    }
	
	////////////////////////////////////////////////////////////////////
	//parseMoveString
	//Input: String to parse into a move.  This must be in the format 
	//	a1-b2, where the first pair is the source square and the second
	//	pair is the destination.
	//Output: Move object if the string is successfully parsed.
	//Exception: A MoveException is thrown if the string cannot be parsed
	//Purpose: Static method used to parse human-entered move strings.
	////////////////////////////////////////////////////////////////////
	public static Move parseMoveString(String move) throws MoveException
	{
		try
		{
			char col1 = move.charAt(0);
			char row1 = move.charAt(1);
			char col2 = move.charAt(3);
			char row2 = move.charAt(4);
		
			int _col1 = (int)Character.toLowerCase(col1) - (int)'a';
			int _col2 = (int)Character.toLowerCase(col2) - (int)'a';
			int _row1 = Character.digit(row1,10);
			int _row2 = Character.digit(row2,10);
			return new Move(_col1,_row1-1,_col2,_row2-1);
		}
		catch(Exception ex)
		{
			throw new MoveException("Unable to parse " + move);
		}
	}

}
