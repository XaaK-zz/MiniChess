import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

///////////////////////////////////////////////////////////////////////////////////////
//Driver
//	Client class used to illustrate the State class usage.
///////////////////////////////////////////////////////////////////////////////////////
public class Driver {

	public static void main(String[] args) throws IOException, MoveException
	{
		Random oRand = new Random(); 
		char continueYN;
		
		int players = 0;
		Boolean keepPlaying = true;
		State oState = new State();
		
		ChessPlayer whitePlayer = null;
		ChessPlayer blackPlayer = null;
		
		int MaxMoveTimeLimitMS = 10000;
		
		////////////////////////////////////////////
		//Get Number of players
		////////////////////////////////////////////
		try
		{
			System.out.println("Hello - welcome to the Stella-3000 MiniChess simulator.\nHow many human players 0 or 1? Enter 9 for test mode.");
			Scanner kb = new Scanner( System.in) ; 
			players = kb.nextInt();
			if(players != 0 && players != 1 && players != 9)
				throw new Exception();
		}
		catch(Exception ex)
		{
			System.out.println("Unknown input - proceeding with 0 human players...");
		}
		
		//Main game loop
		do
		{
			//if requested test mode - simply execute and leave
			if(players == 9)
			{
				TestMode();
				return;
			}
			
			//Init new game state
			oState.NewGame();
			oState.ShowBoard(System.out);
			
			//0 players
			if(players == 0)
			{
				//Create two computer players
				whitePlayer = new ChessPlayer('W');
				blackPlayer = new ChessPlayer('B');
				
				do
				{
					try
					{
						System.out.println("Calculating next white move...");
						Move whiteMove = whitePlayer.GetNextMove(oState, MaxMoveTimeLimitMS);
						System.out.println("White Move Selected: " + whiteMove);
						
						//Execute move
						oState.Move(whiteMove);
						
						oState.ShowBoard(System.out);
						if(!oState.IsGameOver())
						{
							System.out.println("Calculating next black move...");
							Move blackMove = blackPlayer.GetNextMove(oState, MaxMoveTimeLimitMS);
							System.out.println("Black Move Selected: " + blackMove);
							
							//Execute move
							oState.Move(blackMove);
							
							oState.ShowBoard(System.out);
							
						}
					}
					catch(MoveException ex)
					{
						System.out.println("Well, this is embarrassing.  I seem to have made an invalid move.\n" + ex.ErrorDescription);
						return;
					}
					
				}
				while(!oState.IsGameOver());
			}
			else if(players == 1)
			{
				blackPlayer = new ChessPlayer('B');
				
				do
				{
					Boolean inputError = false;
					
					do
					{
						//////////////////////////////////////////
						//Player input loop
						////////////////////////////////////////
						try
						{
							inputError = false;
							System.out.println("Please enter a move in the form of a1-a2.\nColumns are a,b,c,d,e; Rows are 1-6 from the top.");
							Scanner kb = new Scanner( System.in) ; 
							String playerInput = kb.next();
							//Parse the move and apply it to the game state
							oState.Move(Move.parseMoveString(playerInput));
						}
						catch(MoveException ex)
						{
							System.out.println("I am exceedingly embarrassed to tell you there seems to be a problem with the move entered.\n" + ex.ErrorDescription);
							inputError = true;
						}
					}
					while(inputError);
				
					//SHow board
					oState.ShowBoard(System.out);
					
					if(!oState.IsGameOver())
					{
						//Computer's turn
						System.out.println("Calculating my next move to best crush you...");
						//Get moves
						//Vector<Move> oTemp = oState.MoveGen();
						//Pick random
						//Move tempMove = oTemp.get(oRand.nextInt(oTemp.size()));
						Move tempMove = blackPlayer.GetNextMove(oState, 1000);
						System.out.println("Move Selected: " + tempMove);
						try
						{
							//Execute move
							oState.Move(tempMove);
						}
						catch(MoveException ex)
						{
							System.out.println("Well, this is embarrassing.  I seem to have made an invalid move.\n" + ex.ErrorDescription);
							return;
						}
						oState.ShowBoard(System.out);
					}
				}
				while(!oState.IsGameOver());
			}
			
			//Current game has finished - check to see how it ended...
			if(oState.GetGameState() == 1)
				System.out.println("Black won!");
			else if(oState.GetGameState() == -1)
				System.out.println("White won!");
			else
				System.out.println("Draw match!");
			
			System.out.println("Play again? y/n");
			
			//////////////////////////////////////////
			//Player input - y/n 
			//////////////////////////////////////////
			try
			{
				Scanner kb = new Scanner( System.in) ; 
				String x = kb.next();
				continueYN = x.charAt(0);
			}
			catch(Exception ex)
			{
				System.out.println("What??  I'll take that as a NO.");
				continueYN = 'n';
			}
			if(Character.toLowerCase(continueYN) == 'n')
				keepPlaying = false;
				
		}
		while(keepPlaying);
		
		System.out.println("Thanks for playing.");
	}
	
	////////////////////////////////////////////////////
	//TestMode
	//	Internal method used to invoke the testharness
	//		methods built to support test-driven development
	//////////////////////////////////////////
	private static void TestMode() throws IOException
	{
		TestHarness oTest = new TestHarness();
		try
		{
			/*
			oTest.TestKingMovement1();
			oTest.TestKingMovement2();
			oTest.TestKingMovement3();
			oTest.TestKingMovement4();
			oTest.TestKingMovement5();
			oTest.TestKingMovement6();
			oTest.TestKingMovement7();
			oTest.TestKingMovement8();
			oTest.TestQueenMovement1();
			oTest.TestQueenMovement2();
			oTest.TestQueenMovement3();
			oTest.TestQueenMovement4();
			oTest.TestRookMovement1();
			oTest.TestRookMovement2();
			oTest.TestRookMovement3();
			oTest.TestRookMovement4();
			oTest.TestPawnMovement1();
			oTest.TestPawnMovement2();
			oTest.TestPawnMovement3();
			oTest.TestPawnMovement4();
			oTest.TestPawnMovement5();
			oTest.TestBishopMovement1();
			oTest.TestBishopMovement2();
			oTest.TestBishopMovement3();
			oTest.TestBishopMovement4();
			oTest.TestBishopMovement5();
			oTest.TestBishopMovement6();
			oTest.TestKnightMovement1();
			oTest.TestKnightMovement2();
			oTest.TestKnightMovement3();
			oTest.TestKnightMovement4();
			oTest.TestKnightMovement5();
			oTest.TestKnightMovement6();
			oTest.TestPlayerCalc1();
			oTest.TestPlayerCalc2();
			oTest.TestPlayerCalc3();*/
			oTest.TestPlayerCalc4();
			oTest.TestPlayerCalc5();
			oTest.TestPlayerCalc6();
			oTest.TestPlayerCalc7();
		}
		catch(TestException ex)
		{
			System.out.print(" - Test Validation Error!\n" + ex.TestName + "\n" + ex.FailureReason + "\n");
		}
		
	}
	
}
