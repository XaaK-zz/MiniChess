import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

//////////////////////////////////////////////////////////////////////////////
//State class
//	Represents the game state for minichess
/////////////////////////////////////////////////////////////////////////////
public class State implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8516275507674257572L;

	///2D array representing the board spaces
	//	Uses the character representation of the pieces to distinguish them
	private char[][] board;
	
	//Current game turn
	private short currentTurn;
	
	//Current player - indicated by 'B' or 'W'
	public char currentPlayer;
	
	//constants for the board size
	final int MAX_WIDTH = 5;
	final int MAX_HEIGHT = 6;
	
	//flag to indicate the king has been captures
	private Boolean kingCaptured =false;
	
	//default constructor
	public State() throws IOException
	{
		NewGame();
	}
	
	////////////////////////////////////////////////////////////////
	//NewGame
	//Input: None
	//Output: None
	//Purpose: Initializes the board with a fresh state and resets
	//	the various internal member variables.
	////////////////////////////////////////////////////////////////
	public void NewGame() throws IOException
	{
		board = new char[MAX_WIDTH][MAX_HEIGHT];
		String strBoard = "1 W\n" + 
			"kqbnr\n" + 
			"ppppp\n" +
			".....\n" +
			".....\n" +
			"PPPPP\n" +
			"RNBQK";
		
		this.ReadBoard(new StringReader(strBoard));
		
		currentTurn = 1;
		currentPlayer = 'W';
		kingCaptured = false;
	}
	
	////////////////////////////////////////////////////////////////
	//ShowBoard
	//Input: PrintStream object to display the board output
	//	(99% of the time this will be passed System.out)
	//Output: None
	//Purpose: Writes the current state of the board to the output
	////////////////////////////////////////////////////////////////
	public void ShowBoard(java.io.PrintStream out)
	{
		out.print(currentTurn);
		out.print(' ');
		out.print(currentPlayer);
		out.print('\n');
		
		for(int y=0;y<MAX_HEIGHT;y++)
		{
			for(int x=0;x<MAX_WIDTH;x++)
			{
				out.print(board[x][y]);
			}
			out.print('\n');
		}
	}
	
	////////////////////////////////////////////////////////////////
	//ReadBoard
	//Input: java.io.Reader object containing the data to read in
	//	This could be a file or a stringreader
	//Output: None
	//Purpose: Serializes a board "state" into the current object
	////////////////////////////////////////////////////////////////
	public void ReadBoard(java.io.Reader in) throws IOException
	{
		Scanner s = null;
        try 
        {
        	//Use the Java Scanner to read the data
            s = new Scanner(new BufferedReader(in));
            s.useLocale(Locale.US);

            //read "header" information
            currentTurn = s.nextShort();
            String temp = s.next();
            currentPlayer = temp.charAt(0);
            
            //Read the board
            for(int y=0;y<MAX_HEIGHT;y++)
    		{
            	temp = s.next();
            	for(int x=0;x<MAX_WIDTH;x++)
    			{
    				board[x][y] = temp.charAt(x);
    			}
    		}
            
        } 
        finally 
        {
        	//Close the scanner
            s.close();
        }
	}
	
	////////////////////////////////////////////////////////////////
	//IsGameOver
	//Input: None
	//Output: Boolean flag indicating the game is finished
	//Purpose: Tells a client this game state is over
	////////////////////////////////////////////////////////////////
	public Boolean IsGameOver()
	{
		return kingCaptured || this.currentTurn > 40;
	}
	
	////////////////////////////////////////////////////////////////
	//GetGameState
	//Input: None
	//Output: Integer used as a simple enumeration value:
	//	1: Black has won
	//	-1: White has won
	//	0: Draw
	//	NULL: Game is currently in progress
	//Purpose: This is meant to be called after the game is complete 
	//	to see the final game state.  If called while the game is in
	//	progress it will simply return null.
	////////////////////////////////////////////////////////////////
	public int GetGameState()
	{
		if(kingCaptured)
		{
			if(this.currentPlayer == 'B')
				return 1;
			else
				return -1;
		}
		else if(this.currentTurn > 40)
		{
			return 0;
		}
		else
			return (Integer) null;
	}
	
	////////////////////////////////////////////////////////////////
	//Move
	//Input: Move object to execute against the current game state
	//Output: None
	//Exceptions: Throws a MoveException if an invalid move is
	//	detected.
	//Purpose: Validates and executes a the requested move.
	////////////////////////////////////////////////////////////////
	public void Move(Move moveToExecute) throws MoveException
	{
		//Get the moving Piece and destination piece
		char movingPiece = board[moveToExecute.fromSquare.x][moveToExecute.fromSquare.y];
		char destPiece = board[moveToExecute.toSquare.x][moveToExecute.toSquare.y];
		
		//Move validation logic
		if(movingPiece == '.')
			throw new MoveException("Start space is not a valid piece.");
		
		if(currentPlayer == 'W' && !Character.isUpperCase(movingPiece))
			throw new MoveException("Please select your own piece.");

		if(currentPlayer == 'B' && Character.isUpperCase(movingPiece))
			throw new MoveException("Please select your own piece.");
		
		//Get the valid moves from the current position
		Vector<Move> tempMoves = this.MoveGen(this.currentPlayer);
		if(tempMoves.contains(moveToExecute))
		{
			//Legal move - execute move
			board[moveToExecute.fromSquare.x][moveToExecute.fromSquare.y] = '.';
			board[moveToExecute.toSquare.x][moveToExecute.toSquare.y] = movingPiece;
			
			//Check for King
			if(destPiece == 'k' || destPiece == 'K')
				this.kingCaptured = true;
			else
			{
				//Switch current player
				if(this.currentPlayer == 'B')
				{
					this.currentPlayer = 'W';
					//Advance Turn counter if black just went (complete cycle)
					currentTurn++;
				}
				else
					this.currentPlayer = 'B';
			}
			
			//Special case - handle pawn->queen conversion
			if(movingPiece == 'P' && moveToExecute.toSquare.y == 0)
				board[moveToExecute.toSquare.x][moveToExecute.toSquare.y] = 'Q';
			else if(movingPiece == 'p' && moveToExecute.toSquare.y == MAX_HEIGHT-1)
				board[moveToExecute.toSquare.x][moveToExecute.toSquare.y] = 'q';
		}
		else
		{
			//Was not found in the move collection - must not be a valid move
			throw new MoveException("Not a valid move.");
		}
			
	}
	
	////////////////////////////////////////////////////////////////
	//MoveGen
	//Input: None
	//Output: Vector of Move objects
	//Purpose: Invokes the MoveGen method for the current player
	////////////////////////////////////////////////////////////////	
	public Vector<Move> MoveGen()
	{
		return this.MoveGen(this.currentPlayer);
	}
	
	////////////////////////////////////////////////////////////////
	//MoveGen
	//Input: char - indicating what color to generate the moves for
	//	valid inputs are 'B' for Black or 'W' for White
	//Output: Vector of Move objects
	//Purpose: Walks through the board elements and calculates the 
	//	available moves for each of the pieces of the indicated 
	//	color.
	////////////////////////////////////////////////////////////////	
	public Vector<Move> MoveGen(char colorToMove)
	{
		Vector<Move> ValidMoves = new Vector<Move>();
		
		for(int y=0;y<MAX_HEIGHT;y++)
		{
			for(int x=0;x<MAX_WIDTH;x++)
			{
				if(board[x][y] == 'Q' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('Q',x,y,true,true,false,true,true,false));
				else if(board[x][y] == 'q' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('q',x,y,true,true,false,true,true,false));
				else if(board[x][y] == 'R' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('R',x,y,true,false,false,true,false,false));
				else if(board[x][y] == 'r' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('r',x,y,true,false,false,true,false,false));
				else if(board[x][y] == 'B' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('B',x,y,true,true,false,false,true,false));
				else if(board[x][y] == 'b' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('b',x,y,true,true,false,false,true,false));
				else if(board[x][y] == 'P' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('P',x,y,true,true,false,false,false,true));
				else if(board[x][y] == 'p' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('p',x,y,true,true,false,false,false,true));
				else if(board[x][y] == 'N' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('N',x,y,false,false,true,false,false,true));
				else if(board[x][y] == 'n' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('n',x,y,false,false,true,false,false,true));
				else if(board[x][y] == 'K' && colorToMove=='W')
					ValidMoves.addAll(MovePiece('K',x,y,true,true,false,false,false,false));
				else if(board[x][y] == 'k' && colorToMove=='B')
					ValidMoves.addAll(MovePiece('k',x,y,true,true,false,false,false,false));
				
			}
		}
		return ValidMoves;
	}
	
	public int Eval(char evalColor)
	{
		int temp = 0;
		
		for(int y=0;y<MAX_HEIGHT;y++)
		{
			for(int x=0;x<MAX_WIDTH;x++)
			{
				//if(board[x][y] == 'K' && this.currentPlayer=='W')
				//	temp += 100;
				//else if(board[x][y] == 'k' && this.currentPlayer=='B')
				//	temp += 100;
				if(board[x][y] == 'K')
				{
					if(evalColor=='W')
						temp += 2000;
					else
						temp -= 2000;
				}
				else if(board[x][y] == 'k')
				{
					if(evalColor=='B')
						temp += 2000;
					else
						temp -= 2000;
				}
				else if(board[x][y] == 'Q')
				{
					if(evalColor=='W')
						temp += 900;
					else
						temp -= 900;
				}
				else if(board[x][y] == 'q')
				{
					if(evalColor=='B')
						temp += 900;
					else
						temp -= 900;
				}
				else if(board[x][y] == 'R')
				{
					if(evalColor=='W')
						temp += 500;
					else
						temp -= 500;
				}
				else if(board[x][y] == 'r')
				{
					if(evalColor=='B')
						temp += 500;
					else
						temp -= 500;
				}
				else if(board[x][y] == 'B')
				{
					if(evalColor=='W')
						temp += 300;
					else
						temp -= 300;
				}
				else if(board[x][y] == 'b')
				{
					if(evalColor=='B')
						temp += 300;
					else
						temp -= 300;
				}
				else if(board[x][y] == 'P')
				{
					if(evalColor=='W')
						temp += 100;
					else
						temp -= 100;
				}
				else if(board[x][y] == 'p')
				{
					if(evalColor=='B')
						temp += 100;
					else
						temp -= 100;
				}
				else if(board[x][y] == 'N')
				{
					if(evalColor=='W')
						temp += 300;
					else
						temp -= 300;
				}
				else if(board[x][y] == 'n')
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
	
	///////////////////////////////////////////////////////////////////////////////////
	//MovePiece
	//Input: 
	//	movingPiece (char): The character representation of the 
	//		piece currently moving.  Casing matters - as it will
	//		be used to determine what side the piece is on.
	//	x (int): Current horizontal position on the board
	//	y (int): Current vertical position on the board
	//	orthogonal (Boolean): Indicates if this piece can move orthogonally.
	//	diagonal (Boolean): Indicates if this piece can move diagonally
	//	stepWise (Boolean): Indicates if this piece can move stepwise
	//		note: this means moving like the knight
	//	multiStepOrthogonal (Boolean): Indicates if the piece can move more than 
	//		one square in the orthogonal directions.
	// multiStepDiag (Boolean): Indicates if the piece can move more than 
	//		one square in the diagonal directions.
	// forceDirection (Boolean): Indicates the moving piece can only move in a specific direction
	//		for example: the pawn.
	//Output: Vector of Move objects - containing the valid moves available for this piece
	//Purpose: This is  private utility method used by the MoveGen method to find valid movements
	//		for pieces.  It uses the Scan method - looking in each of the "directions" from the
	//		indicated piece. Any valid moves found are added to the Move Vector and eventually 
	//		returned to the caller.
	//////////////////////////////////////////////////////////////////////////////////////
	private Vector<Move> MovePiece(char movingPiece, int x, int y, Boolean orthogonal, Boolean diagonal, Boolean stepWise,
			Boolean multiStepOrthogonal, Boolean multiStepDiag, Boolean forceDirection)
	{
		Vector<Move> ValidMoves = new Vector<Move>();
		char color = 'B';
		if(Character.isUpperCase(movingPiece))
			color = 'W';
		
		if(orthogonal)
		{
			if((color == 'B' && forceDirection) || !forceDirection)
			{
				//Look "South"
				if(Character.toLowerCase(movingPiece) == 'p' || Character.toLowerCase(movingPiece) == 'b')
					ValidMoves.addAll(this.Scan(x,y,0,1,multiStepOrthogonal ? -1 : 1,movingPiece,false,false));
				else
					ValidMoves.addAll(this.Scan(x,y,0,1,multiStepOrthogonal ? -1 : 1,movingPiece,true,false));
			}
			
			if((color == 'W' && forceDirection) || !forceDirection)
			{
				//Look "North"
				if(Character.toLowerCase(movingPiece) == 'p' || Character.toLowerCase(movingPiece) == 'b')
					ValidMoves.addAll(this.Scan(x,y,0,-1,multiStepOrthogonal ? -1 : 1,movingPiece,false,false));
				else
					ValidMoves.addAll(this.Scan(x,y,0,-1,multiStepOrthogonal ? -1 : 1,movingPiece,true,false));
			}
			
			if(!forceDirection)
			{
				//Look "East"
				if(Character.toLowerCase(movingPiece) == 'b')
					ValidMoves.addAll(this.Scan(x,y,1,0,multiStepOrthogonal ? -1 : 1,movingPiece,false,false));
				else
					ValidMoves.addAll(this.Scan(x,y,1,0,multiStepOrthogonal ? -1 : 1,movingPiece,true,false));
							
				//Look "West"
				if(Character.toLowerCase(movingPiece) == 'b')
					ValidMoves.addAll(this.Scan(x,y,-1,0,multiStepOrthogonal ? -1 : 1,movingPiece,false,false));
				else
					ValidMoves.addAll(this.Scan(x,y,-1,0,multiStepOrthogonal ? -1 : 1,movingPiece,true,false));
			}
		}
		
		if(diagonal)
		{
			if((color == 'W' && forceDirection) || !forceDirection)
			{
				//Look "NorthEast"
				if(Character.toLowerCase(movingPiece) == 'p')
					ValidMoves.addAll(this.Scan(x,y,1,-1,multiStepDiag ? -1 : 1,movingPiece,true,true));
				else
					ValidMoves.addAll(this.Scan(x,y,1,-1,multiStepDiag ? -1 : 1,movingPiece,true,false));
								
				//Look "NorthWest"
				if(Character.toLowerCase(movingPiece) == 'p')
					ValidMoves.addAll(this.Scan(x,y,-1,-1,multiStepDiag ? -1 : 1,movingPiece,true,true));
				else
					ValidMoves.addAll(this.Scan(x,y,-1,-1,multiStepDiag ? -1 : 1,movingPiece,true,false));
			}
			
			if((color == 'B' && forceDirection) || !forceDirection)
			{
				//Look "SouthEast"
				if(Character.toLowerCase(movingPiece) == 'p')
					ValidMoves.addAll(this.Scan(x,y,1,1,multiStepDiag ? -1 : 1,movingPiece,true,true));
				else
					ValidMoves.addAll(this.Scan(x,y,1,1,multiStepDiag ? -1 : 1,movingPiece,true,false));

				//Look "SouthWest"
				if(Character.toLowerCase(movingPiece) == 'p')
					ValidMoves.addAll(this.Scan(x,y,-1,1,multiStepDiag ? -1 : 1,movingPiece,true,true));
				else
					ValidMoves.addAll(this.Scan(x,y,-1,1,multiStepDiag ? -1 : 1,movingPiece,true,false));
			}
			
		}
		
		if(stepWise)
		{
			//Knight movement
			ValidMoves.addAll(this.Scan(x,y,2,1,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,-2,1,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,2,-1,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,-2,-1,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,1,2,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,1,-2,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,-1,2,1,movingPiece,true,false));
			ValidMoves.addAll(this.Scan(x,y,-1,-2,1,movingPiece,true,false));	
		}
		
		return ValidMoves;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//Scan
	//Input: 
	//	x (int): Current horizontal position on the board
	//	y (int): Current vertical position on the board
	//	dx (int): Amount of squares this piece can move in the 
	//		horizontal direction.
	//	dy (int): Amount of squares this piece can move in the 
	//		vertical direction.
	//	maxMoves (int): Number of moves allowed by this piece.
	//		If the piece can move an unlimited number of moves then
	//		this should be set to -1.
	//	movingPiece (char): The character representation of the 
	//		piece currently moving.  Casing matters - as it will
	//		be used to determine what side the piece is on.
	//	canCapture (Boolean): Indicates if this piece is allowed to capture as part
	//		of this move
	//	mustCapture (Boolean): Indicates if this piece can only make a move if 
	//		it is capturing an opponent's piece.
	//
	//Output: Vector of Move objects - containing the valid moves available for this piece
	//	from this position.
	//Purpose: Internal utility method used by the MovePiece method.  Scans the board
	//		for a valid movement for a given piece.
	//////////////////////////////////////////////////////////////////////////////////////
	private Vector<Move> Scan(int x, int y, int dx, int dy, int maxMoves, char movingPiece, 
			Boolean canCapture, Boolean mustCapture)
	{
		Vector<Move> validMoves = new Vector<Move>();
		boolean keepLooking = true;
		int currentX = x+dx;
		int currentY = y+dy;
		int steps = 1;
		do
		{
			//bound check
			if(currentX < 0 || currentX >= MAX_WIDTH)
				keepLooking = false;
			else if(currentY < 0 || currentY >= MAX_HEIGHT)
				keepLooking = false;
			else
			{
				if(board[currentX][currentY] == '.')
				{
					if(!mustCapture)
					{
						validMoves.add(new Move(x,y,currentX,currentY));
					}
				}
				else
				{
					//found another piece - figure out if own or opponent
					if(Character.isUpperCase(movingPiece) && Character.isUpperCase(board[currentX][currentY]))
						keepLooking = false;
					else if(Character.isLowerCase(movingPiece) && Character.isLowerCase(board[currentX][currentY]))
						keepLooking = false;
					else
					{
						//Found opponent piece
						if(canCapture)
							validMoves.add(new Move(x,y,currentX,currentY));
						keepLooking = false;
					}
				}
			}
			
			//Max moves
			if(maxMoves != -1 && ++steps >= maxMoves)
				keepLooking = false;
			else
			{
				currentX += dx;
				currentY += dy;
			}
			
		}
		while(keepLooking);
		
		return validMoves;
	}

	
}
