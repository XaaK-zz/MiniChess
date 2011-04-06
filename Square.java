//////////////////////////////////////////////////////
//Square
//	Represents a specific space on the game board
///////////////////////////////////////////////////////
public class Square 
{
	//horizontal position (0-based, from the left->right)
	public int x;
	//vertical position (0-based, from the top->down
	public int y;
	
	//////////////////////////////////////////////////
	//Constructor
	//	Allows the x,y value to be passed in to the new
	//		object
	////////////////////////////////////////////////////
	public Square(int xx,int yy)
	{
		x = xx;
		y = yy;
	}
	
	///////////////////////////////////////////////////////////////
	//toString
	//	Overridden toString method to make printing and displaying
	//		a square position easier.
	///////////////////////////////////////////////////////////////
	public String toString()
	{
		int col = (int)'a' + x;
		return Character.toString((char)col) + ((Integer)(y+1)).toString();
	}
}
