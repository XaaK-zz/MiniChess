import java.io.Serializable;

///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////
//Move 
//	Class representing a move (from Square->to Square)
////////////////////////////////////////////////////////////////////////
public class Move implements Serializable
{
	private static final long serialVersionUID = -7674360126274753246L;
	
	//from/to Square properties
	public short fromX;
	public short fromY;
	public short toX;
	public short toY;
	
	/////////////////////////////////////////////////////////
	//Move Constructor
	//	Basic constructor
	/////////////////////////////////////////////////////////
	public Move()
	{
		fromX = fromY = toX = toY = 0;
	}
	
	//////////////////////////////////////////////////////////
	//Move Constructor
	//Input: fromX (short): x value of the source square 
	//			this move originates from
	//		fromY (short): y value of the source square 
	//			this move originates from
	//		toX (short): x value of the destination square 
	//			this move is for
	//		fromY (short): y value of the destination square 
	//			this move is for
	/////////////////////////////////////////////////////////
	public Move(short _fromX, short _fromY, short _toX, short _toY)
	{
		fromX = _fromX;
		fromY = _fromY;
		toX = _toX;
		toY = _toY;
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
	public Move(int _fromX, int _fromY, int _toX, int _toY)
	{
		this((short)_fromX,(short)_fromY,(short)_toX,(short)_toY);
	}
	
	//////////////////////////////////////////////////////////////
	//toString
	//	Overriding toString to allow easy printing of the move
	///////////////////////////////////////////////////////////////
	public String toString()
	{
		short fromCol = (short) ((short)'a' + fromX);
		short toCol = (short) ((short)'a' + toX);
		return Character.toString((char)fromCol) + ((Integer)(fromY+1)).toString() + "-" +
			Character.toString((char)toCol) + ((Integer)(toY+1)).toString();
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
		if(this.fromX == oTemp.fromX &&
		   this.fromY == oTemp.fromY &&
		   this.toX == oTemp.toX &&
		   this.toY == oTemp.toY)
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
			return new Move((short)_col1,(short)(_row1-1),(short)_col2,(short)(_row2-1));
		}
		catch(Exception ex)
		{
			throw new MoveException("Unable to parse " + move);
		}
	}

}
