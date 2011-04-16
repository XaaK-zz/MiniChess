///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;

//////////////////////////////////////////////////////////////////////////////////////
//EvolvingPlayer
//	Derived player from the BasePlayer class.
//	Implements a chess player with the ability to evolve the evaluation values through
//		many iterations.  Represents an attempt to solve the evaluation problems
//		through genetic programming.
/////////////////////////////////////////////////////////////////////////////////////
public class EvolvingPlayer extends BasePlayer implements Serializable
{

	//Internal value required for the serialization framework
	private static final long serialVersionUID = -717730464990011476L;
	
	//Current evaluation rules
	private Hashtable<Character,Integer> evalRules;
	
	//Default constructor
	public EvolvingPlayer()
	{
		evalRules = new Hashtable<Character,Integer>();
	}
	
	//Constructor with color
	public EvolvingPlayer(char _color) 
	{
		super(_color);
		evalRules = new Hashtable<Character,Integer>();
	}

	///////////////////////////////////////////////////////////////////////////////
	//Eval
	//Input: boardPosition (State): Board state to evaluate
	//		 evalColor (char): character representing the color to evaluate
	//Output: Value attributed to this board position for the given color
	//Purpose: Give a numerical representation for the given color for the
	//		provided board position
	//////////////////////////////////////////////////////////////////////////////
	protected int Eval(State boardPosition, char evalColor)
	{
		int temp = 0;
		
		if(boardPosition.IsGameOver())
		{
			int state = boardPosition.GetGameState();
			
			if(state == -1 && evalColor == 'W')
			{
				return Integer.MAX_VALUE;
			}
			else if(state == 1 && evalColor == 'B')
			{
				return Integer.MAX_VALUE;
			}
		}
		
		for(int y=0;y<boardPosition.MAX_HEIGHT;y++)
		{
			for(int x=0;x<boardPosition.MAX_WIDTH;x++)
			{
				if(this.evalRules.containsKey(boardPosition.board[x][y]))
				{
					int pieceValue = this.evalRules.get(boardPosition.board[x][y]);
					char piece = boardPosition.board[x][y];
					/*
					 //Trying to figure out how to add positioning information - this does not work yet
					if(boardPosition.IsSpaceThreatened(x, y))
					{
						if(piece == 'K' || piece == 'k' )
							pieceValue *= 5;
						else if(piece == 'Q' || piece == 'q')
							pieceValue *= 3;
						else 
							pieceValue *= 1.2;
					}
					*/
					//Adding some value to pawns if they advance
					/*
					if(piece == 'p' && evalColor == 'B')
					{
						if(y > 1)
							pieceValue += (y * 10);
					}
					else if(piece == 'P' && evalColor == 'W')
					{
						if(y < 5)
							pieceValue += ((5-y) * 10);
					}
					*/
					if(Character.isUpperCase(piece))
					{
						if(evalColor=='W')
							temp += pieceValue;
						else
							temp -= pieceValue;
					}
					else
					{
						if(evalColor=='B')
							temp += pieceValue;
						else
							temp -= pieceValue;
					}
					
					
				}
			}
		}
		
		return temp;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//LoadRules
	//Input: Comma-separated list of piece=value rules to load into this player
	//	Example: k=2000,q=1000,b=100....
	//Output: None
	//Purpose: Provide a quick way to load evaluation rules into a player object
	/////////////////////////////////////////////////////////////////////////////////
	public void LoadRules(String inputRules)
	{
		Scanner oScan = new Scanner(inputRules);
		oScan.useDelimiter(",");
		while(oScan.hasNext())
		{
			String input = oScan.next();
			char piece = input.charAt(0);
			int pieceValue = Integer.parseInt(input.substring(2));
			//Load the rule and the value into the evalRules hashtable
			evalRules.put(piece,pieceValue);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////
	//GetRules
	//Input: None
	//Output: Comma-separated list of evaluation rules for the current player
	//Purpose: Provide a way to extract the current rules for this player object
	//////////////////////////////////////////////////////////////////////////////
	public String GetRules()
	{
		StringBuilder oString = new StringBuilder();
		
		for(char piece : evalRules.keySet())
		{
			int pieceValue = evalRules.get(piece);
			oString.append(piece + "=" + pieceValue + ",");
		}
		return (String) oString.toString().subSequence(0, oString.toString().length()-1);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	//Mutate
	//Input: None
	//Output: None
	//Purpose: Randomly shifts each rule in this player +/- 10
	////////////////////////////////////////////////////////////////////////////
	public void Mutate()
	{
		for(char piece : evalRules.keySet())
		{
			int pieceValue = evalRules.get(piece);
			if(randomGen.nextBoolean())
				pieceValue -= randomGen.nextInt(10);
			else
				pieceValue += randomGen.nextInt(10);
			
			evalRules.put(piece, pieceValue);
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	//Combine
	//Input: oPlayer (EvolvingPlayer): A separate EvolvingPlayer object with which
	//		to combine the rules with the current object
	//Output: None
	//Purpose: Takes two players and combines the rules - thus propogating them to 
	//		the next generation.
	////////////////////////////////////////////////////////////////////////////
	public void Combine(EvolvingPlayer oPlayer)
	{
		for(char piece : evalRules.keySet())
		{
			int pieceValue = evalRules.get(piece);
			int pieceValue2 = oPlayer.evalRules.get(piece);
			evalRules.put(piece, ((pieceValue + pieceValue2)/2));
		}
	}
}
