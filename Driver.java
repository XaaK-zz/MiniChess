///////////////////////////////////////////////////////////////////////////////////////
// Zach Greenvoss
// CS 542 - Combinatorial Games
///////////////////////////////////////////////////////////////////////////////////////
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
		char continueYN;
		
		int players = 0;
		Boolean keepPlaying = true;
		State oState = new State();
		
		BasePlayer whitePlayer = null;
		BasePlayer blackPlayer = null;
		
		int MaxMoveTimeLimitMS = 1000;
		
		////////////////////////////////////////////
		//Get Number of players
		////////////////////////////////////////////
		try
		{
			System.out.println("Hello - welcome to the Stella-3000 MiniChess simulator.\nHow many human players 0 or 1? Enter 9 for test mode.  \nEnter 8 for geneic algoorithm (hit q to quit this).");
			Scanner kb = new Scanner( System.in) ; 
			players = kb.nextInt();
			if(players != 0 && players != 1 && players != 9 && players != 8)
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
			else if(players == 8)
			{
				EvolMode();
				return;
			}
			
			//Init new game state
			oState.NewGame();
			oState.ShowBoard(System.out);
			
			//0 players
			if(players == 0)
			{
				//Create two computer players
				//whitePlayer = new ChessPlayer('W');
				whitePlayer = new EvolvingPlayer('W');
				((EvolvingPlayer)whitePlayer).LoadRules("k=2005,R=546,Q=913,P=45,r=446,B=344,N=344,q=908,p=107,K=3000,n=300,b=297");
				//blackPlayer = new ChessPlayer('B');
				blackPlayer  = new EvolvingPlayer('B');
				((EvolvingPlayer)blackPlayer).LoadRules("k=2005,R=546,Q=913,P=45,r=446,B=344,N=344,q=908,p=107,K=3000,n=300,b=297");
				
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
				//blackPlayer = new ChessPlayer('B');
				blackPlayer = new EvolvingPlayer('B');
				((EvolvingPlayer)blackPlayer).LoadRules("k=2005,R=546,Q=913,P=45,r=446,B=344,N=344,q=908,p=107,K=3000,n=300,b=297");
				
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
				
					//Show board
					oState.ShowBoard(System.out);
					
					if(!oState.IsGameOver())
					{
						//Computer's turn
						System.out.println("Calculating my next move to best crush you...");
						//Select next move
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
	private static void TestMode() throws IOException, MoveException
	{
		TestHarness oTest = new TestHarness();
		try
		{
			oTest.TestPlayerCalc4();
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
			
			//oTest.TestPlayerCalc1();
			
			oTest.TestPlayerCalc2();
			oTest.TestPlayerCalc3();
			oTest.TestPlayerCalc4();
			oTest.TestPlayerCalc5();
			oTest.TestPlayerCalc6();
			oTest.TestPlayerCalc7();
			
			oTest.TestPlayerCalc8();
			
			oTest.TestEvolPlayer1();
			oTest.TestEvolPlayer2();
			
			oTest.TestTiming();
			
			oTest.TestThreatenLogic1();
			oTest.TestThreatenLogic2();
			
			oTest.TestPlayerCalc3();
			*/
			//oTest.UndoMoveTest1();
		}
		catch(TestException ex)
		{
			System.out.print(" - Test Validation Error!\n" + ex.TestName + "\n" + ex.FailureReason + "\n");
		}
	}
	
	private static State RunGame(BasePlayer whitePlayer, BasePlayer blackPlayer, 
			Boolean showOutput, int MaxMoveTimeLimitMS) throws MoveException, IOException
	{
		State oState = new State();
		do
		{
			if(showOutput)
				System.out.println("Calculating next white move...");
			Move whiteMove = whitePlayer.GetNextMove(oState, MaxMoveTimeLimitMS);
			if(showOutput)
				System.out.println("White Move Selected: " + whiteMove);
			
			//Execute move
			oState.Move(whiteMove);
			
			if(showOutput)
				oState.ShowBoard(System.out);
			
			if(!oState.IsGameOver())
			{
				if(showOutput)
					System.out.println("Calculating next black move...");
				Move blackMove = blackPlayer.GetNextMove(oState, MaxMoveTimeLimitMS);
				if(showOutput)
					System.out.println("Black Move Selected: " + blackMove);
				
				//Execute move
				oState.Move(blackMove);
				
				if(showOutput)
					oState.ShowBoard(System.out);
				
			}
		}
		while(!oState.IsGameOver());
		
		return oState;
	}
	
	//Genetic algorithm:
	//	1. Create N evolving players - all with basic eval rules
	//		Mutate each one
	//	2. Run each player against basic player 10 times
	//		Keep track of those who win more than 50%
	//	3. Create new group of 10
	//		Keep winners from previous round + fill in new ones (as copies from winner)
	//		Mutate each one?  Or just new ones
	//	Repeat 2 until quit or win 100%
	private static void EvolMode() throws MoveException, IOException
	{
		//Scanner keyboard = new Scanner(System.in);
		int generation=1;
		Boolean shouldQuit = false;
		int numberOfPlayers = 10;
		int numberOfGames = 10;
		String startRules = "K=2000,k=2000,Q=900,q=900,R=500,r=500,B=300,b=300,P=100,p=100,N=300,n=300";
		Vector<EvolvingPlayer> players = new Vector<EvolvingPlayer>();
		ChessPlayer basicPlayer = new ChessPlayer('B');
		int playerWin[] = new int[numberOfPlayers];
		long start,end;
		Random randGen = new Random();
		
		for(int x=0;x<numberOfPlayers;x++)
		{
			EvolvingPlayer oPlayer = new EvolvingPlayer('W');
			oPlayer.LoadRules(startRules);
			oPlayer.Mutate();
			players.add(oPlayer);
		}
		
		while(!shouldQuit)
		{
			for(int x=0;x<numberOfPlayers;x++)
			{
				playerWin[x] = 0;
				for(int game=0;game<numberOfGames;game++)
				{
					start = System.currentTimeMillis();
					System.out.print("Running EvolvingPlayer: " + x + " in game " + game + " in generation " + generation);
					State oState = RunGame(players.get(x),basicPlayer,false,1000);
					end = System.currentTimeMillis() - start;
					System.out.println(" finished in " + end + " MilliSeconds.");
					if(oState.GetGameState() == -1)	//White won
					{
						System.out.println("   Victory for EvolvingPlayer: " + x + " in game " + game + " in generation " + generation);
						playerWin[x] = playerWin[x] + 1;
					}
				}
			}
			generation++;
			System.out.println("Examining win/loss for this generation...");
			Vector<EvolvingPlayer> tempVector = new Vector<EvolvingPlayer>();
			String tempRules = "";
			for(int x=0;x<numberOfPlayers;x++)
			{
				if(playerWin[x] >= (numberOfGames/2))
				{
					System.out.println("Player " + x + " keeping to next gen.");
					//tempVector.add((EvolvingPlayer)DeepCopy.copy(players.get(x)));
					tempVector.add(players.get(x));
					tempRules = players.get(x).GetRules();
				}
			}
			
			System.out.println("Combining winning rules for next generation...");
			int numberOfCombinings = randGen.nextInt(tempVector.size()-1);
			for(int x=0;x<numberOfCombinings;x++)
			{
				int player1Num = randGen.nextInt(tempVector.size()-1);
				int player2Num = randGen.nextInt(tempVector.size()-1);
				System.out.println("Combining player " + player1Num + " and player " + player2Num);
				EvolvingPlayer player1 = tempVector.get(player1Num);
				EvolvingPlayer player2 = tempVector.get(player2Num);
				player1.Combine(player2);
				if(randGen.nextInt(100) < 5)
				{
					System.out.println("...Mutation...");
					player1.Mutate();
				}
			}
			//Now we have players to keep for this gen - rebuild arroy
			
			System.out.println("Rebuilding array - keeping rules: " + tempRules);
			for(int x=0;x<numberOfPlayers;x++)
			{
				if(tempVector.size()-1 > x)
				{
					//players.set(x, (EvolvingPlayer)DeepCopy.copy(tempVector.get(x)));
					players.set(x, (tempVector.get(x)));
				}
				else
				{
					EvolvingPlayer oPlayer = new EvolvingPlayer('W');
					if(tempRules.compareTo("") == 0)
						oPlayer.LoadRules(startRules);
					else
						oPlayer.LoadRules(tempRules);
					oPlayer.Mutate();	
					players.set(x,oPlayer);
				}
			}
			System.out.println("Player array - current rules: ");
			for(int x=0;x<numberOfPlayers;x++)
			{
				System.out.println("   Player: " + x + players.get(x).GetRules());
			}
			/*
			System.out.println("Continue? y/n");
			String input = "";
			try
			{
				input = keyboard.next();
			}
			catch(Exception ex)
			{
				input = "n";
			}
			if(input.compareTo("n") == 0)
				shouldQuit = true;
				*/
		}
	}
	
}
