///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////
//ChessPlayer
//	Represents a "basic" chess playing adversary
/////////////////////////////////////////////////////////////////////////////////////
public class ChessPlayer extends BasePlayer
{
	//Basic constructor with color
	public ChessPlayer(char _color)
	{
		super(_color);
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//Eval
	//Input: boardPosition (State): Current state of the board to evaluate
	//		 evalColor (char): Color to evaluate 
	//Output: Numerical representation of the given board for the given color
	//Purpose: Provides a "basic" evaluation routine as a baseline to compare
	//	other players against.
	//	Intentionally very basic.
	//////////////////////////////////////////////////////////////////////////////////
	protected int Eval(State boardPosition, char evalColor)
	{
		int temp = 0;
		
		for(int y=0;y<boardPosition.MAX_HEIGHT;y++)
		{
			for(int x=0;x<boardPosition.MAX_WIDTH;x++)
			{
				if(boardPosition.board[x][y] == 'K')
				{
					if(evalColor=='W')
						temp += 2000;
					else
						temp -= 2000;
				}
				else if(boardPosition.board[x][y] == 'k')
				{
					if(evalColor=='B')
						temp += 2000;
					else
						temp -= 2000;
				}
				else if(boardPosition.board[x][y] == 'Q')
				{
					if(evalColor=='W')
						temp += 900;
					else
						temp -= 900;
				}
				else if(boardPosition.board[x][y] == 'q')
				{
					if(evalColor=='B')
						temp += 900;
					else
						temp -= 900;
				}
				else if(boardPosition.board[x][y] == 'R')
				{
					if(evalColor=='W')
						temp += 500;
					else
						temp -= 500;
				}
				else if(boardPosition.board[x][y] == 'r')
				{
					if(evalColor=='B')
						temp += 500;
					else
						temp -= 500;
				}
				else if(boardPosition.board[x][y] == 'B')
				{
					if(evalColor=='W')
						temp += 300;
					else
						temp -= 300;
				}
				else if(boardPosition.board[x][y] == 'b')
				{
					if(evalColor=='B')
						temp += 300;
					else
						temp -= 300;
				}
				else if(boardPosition.board[x][y] == 'P')
				{
					if(evalColor=='W')
						temp += 100;
					else
						temp -= 100;
				}
				else if(boardPosition.board[x][y] == 'p')
				{
					if(evalColor=='B')
						temp += 100;
					else
						temp -= 100;
				}
				else if(boardPosition.board[x][y] == 'N')
				{
					if(evalColor=='W')
						temp += 300;
					else
						temp -= 300;
				}
				else if(boardPosition.board[x][y] == 'n')
				{
					if(evalColor=='B')
						temp += 300;
					else
						temp -= 300;
				}
			}
		}
		return temp;
	}
}
