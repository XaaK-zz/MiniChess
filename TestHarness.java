import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

//////////////////////////////////////////////////////
//TestHarness
//	Class used for hosting and running unit tests
//	Each chess piece has a suite of tests to ensure
//		it is moving correctly.  In addition, as defects
//		are discovered through testing they are duplicated
//		with a unit test before the fix is implemented.
//
//	All of the tests follow a similar pattern:
//		1. Initialize a game to  known board state
//		2. Generate the valid moves
//		3. Check to see if the expected moves are there and throw
//			a TestException if they are not
///////////////////////////////////////////////////////
public class TestHarness {

	public void TestKingMovement1() throws TestException, IOException
	{
		String testName = "TestKingMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"K....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,0,0,1)))
			throw new TestException(testName,"Failed to find valid 0,0->0,1 movement for King.");
		if(!oTemp.contains(new Move(0,0,1,1)))
			throw new TestException(testName,"Failed to find valid 0,0->1,1 movement for King.");
		if(!oTemp.contains(new Move(0,0,1,0)))
			throw new TestException(testName,"Failed to find valid 0,0->1,0 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement2() throws TestException, IOException
	{
		String testName = "TestKingMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"....K\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(4,0,3,0)))
			throw new TestException(testName,"Failed to find valid 4,0->3,0 movement for King.");
		if(!oTemp.contains(new Move(4,0,3,1)))
			throw new TestException(testName,"Failed to find valid 4,0->3,1 movement for King.");
		if(!oTemp.contains(new Move(4,0,4,1)))
			throw new TestException(testName,"Failed to find valid 4,0->4,1 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement3() throws TestException, IOException
	{
		String testName = "TestKingMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			"....K";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(4,5,3,5)))
			throw new TestException(testName,"Failed to find valid 4,5->3,5 movement for King.");
		if(!oTemp.contains(new Move(4,5,3,4)))
			throw new TestException(testName,"Failed to find valid 4,5->3,4 movement for King.");
		if(!oTemp.contains(new Move(4,5,4,4)))
			throw new TestException(testName,"Failed to find valid 4,5->4,4 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement4() throws TestException, IOException
	{
		String testName = "TestKingMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			"K....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,5,0,4)))
			throw new TestException(testName,"Failed to find valid 0,5->0,4 movement for King.");
		if(!oTemp.contains(new Move(0,5,1,4)))
			throw new TestException(testName,"Failed to find valid 0,5->1,4 movement for King.");
		if(!oTemp.contains(new Move(0,5,1,5)))
			throw new TestException(testName,"Failed to find valid 0,5->1,5 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement5() throws TestException, IOException
	{
		String testName = "TestKingMovement5";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..K..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for King.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->3,2 movement for King.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for King.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement6() throws TestException, IOException
	{
		String testName = "TestKingMovement6";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..Kq.\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for King.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->3,2 movement for King.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for King.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement7() throws TestException, IOException
	{
		String testName = "TestKingMovement7";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..KQ.\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for King.");
		if(oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Shouldn't be able to move from 2,2->3,2 for King.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for King.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestKingMovement8() throws TestException, IOException
	{
		String testName = "TestKingMovement7";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "2 B\n" + 
			"kqbnr\n" + 
			"ppppp\n" +
			".....\n" +
			"...P.\n" +
			"PPP.P\n" +
			"RNBQK";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(0,0,1,1)))
			throw new TestException(testName,"Invalid move from 0,0->1,1 movement for King.");
		
		System.out.println(" - Success");
	}
	
	public void TestQueenMovement1() throws TestException, IOException
	{
		String testName = "TestQueenMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..Q..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,2,0)))
			throw new TestException(testName,"Failed to find valid 2,2->2,0 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,4,2)))
			throw new TestException(testName,"Failed to find valid 2,2->4,2 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,2,5)))
			throw new TestException(testName,"Failed to find valid 2,2->2,5 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,0,2)))
			throw new TestException(testName,"Failed to find valid 2,2->0,2 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,4,0)))
			throw new TestException(testName,"Failed to find valid 2,2->4,0 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,0,0)))
			throw new TestException(testName,"Failed to find valid 2,2->0,0 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,0,4)))
			throw new TestException(testName,"Failed to find valid 2,2->0,4 movement for Queen.");
		if(!oTemp.contains(new Move(2,2,4,4)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Queen.");
		
		System.out.println(" - Success");
	}
	
	public void TestQueenMovement2() throws TestException, IOException
	{
		String testName = "TestQueenMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"Q....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,0,4,0)))
			throw new TestException(testName,"Failed to find valid 0,0->4,0 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,4,4)))
			throw new TestException(testName,"Failed to find valid 0,0->4,4 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,0,5)))
			throw new TestException(testName,"Failed to find valid 0,0->0,5 movement for Queen.");
					
		System.out.println(" - Success");
	}
	
	public void TestQueenMovement3() throws TestException, IOException
	{
		String testName = "TestQueenMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"Q..R.\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(0,0,4,0)))
			throw new TestException(testName,"Non valid movement (through friendly) 0,0->4,0 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,2,0)))
			throw new TestException(testName,"Failed to find valid 0,0->2,0 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,4,4)))
			throw new TestException(testName,"Failed to find valid 0,0->4,4 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,0,5)))
			throw new TestException(testName,"Failed to find valid 0,0->0,5 movement for Queen.");
					
		System.out.println(" - Success");
	}
	
	public void TestQueenMovement4() throws TestException, IOException
	{
		String testName = "TestQueenMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"Q..r.\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(0,0,4,0)))
			throw new TestException(testName,"Non valid movement (through enemy) 0,0->4,0 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,3,0)))
			throw new TestException(testName,"Failed to find valid 0,0->3,0 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,4,4)))
			throw new TestException(testName,"Failed to find valid 0,0->4,4 movement for Queen.");
		if(!oTemp.contains(new Move(0,0,0,5)))
			throw new TestException(testName,"Failed to find valid 0,0->0,5 movement for Queen.");
					
		System.out.println(" - Success");
	}
	
	public void TestRookMovement1() throws TestException, IOException
	{
		String testName = "TestRookMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"R....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,0,4,0)))
			throw new TestException(testName,"Failed to find valid 0,0->4,0 movement for Rook.");
		if(!oTemp.contains(new Move(0,0,0,5)))
			throw new TestException(testName,"Failed to find valid 0,0->0,5 movement for Rook.");
		if(oTemp.contains(new Move(0,0,4,5)))
			throw new TestException(testName,"Non valid movement from 0,0->4,5 movement for Rook.");
					
		System.out.println(" - Success");
	}
	
	public void TestRookMovement2() throws TestException, IOException
	{
		String testName = "TestRookMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"....R\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(4,0,0,0)))
			throw new TestException(testName,"Failed to find valid 4,0->0,0 movement for Rook.");
		if(!oTemp.contains(new Move(4,0,4,5)))
			throw new TestException(testName,"Failed to find valid 4,0->4,5 movement for Rook.");
		if(oTemp.contains(new Move(4,0,0,5)))
			throw new TestException(testName,"Non valid movement from 4,0->0,5 movement for Rook.");
					
		System.out.println(" - Success");
	}
	
	public void TestRookMovement3() throws TestException, IOException
	{
		String testName = "TestRookMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"..Q.R\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(4,0,0,0)))
			throw new TestException(testName,"Non valid movement from 4,0->0,0 movement for Rook.");
		if(!oTemp.contains(new Move(4,0,3,0)))
			throw new TestException(testName,"Failed to find valid 4,0->3,0 movement for Rook.");
		if(!oTemp.contains(new Move(4,0,4,5)))
			throw new TestException(testName,"Failed to find valid 4,0->4,5 movement for Rook.");
		if(oTemp.contains(new Move(4,0,0,5)))
			throw new TestException(testName,"Non valid movement from 4,0->0,5 movement for Rook.");
					
		System.out.println(" - Success");
	}
	
	public void TestRookMovement4() throws TestException, IOException
	{
		String testName = "TestRookMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"..q.R\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(4,0,0,0)))
			throw new TestException(testName,"Non valid movement from 4,0->0,0 movement for Rook.");
		if(!oTemp.contains(new Move(4,0,2,0)))
			throw new TestException(testName,"Failed to find valid 4,0->2,0 movement for Rook.");
		if(!oTemp.contains(new Move(4,0,4,5)))
			throw new TestException(testName,"Failed to find valid 4,0->4,5 movement for Rook.");
		if(oTemp.contains(new Move(4,0,0,5)))
			throw new TestException(testName,"Non valid movement from 4,0->0,5 movement for Rook.");
					
		System.out.println(" - Success");
	}
	
	public void TestPawnMovement1() throws TestException, IOException
	{
		String testName = "TestPawnMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 B\n" + 
			".....\n" + 
			"..p..\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(2,1,1,2)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,1->1,2 movement for Pawn.");
		if(oTemp.contains(new Move(2,1,3,2)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,1->3,2 movement for Pawn.");
		if(oTemp.contains(new Move(2,1,2,0)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,1->2,0 movement for Pawn.");
		if(oTemp.contains(new Move(2,1,1,0)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,1->1,0 movement for Pawn.");
		if(oTemp.contains(new Move(2,1,3,0)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,1->3,0 movement for Pawn.");
		
		if(!oTemp.contains(new Move(2,1,2,2)))
			throw new TestException(testName,"Failed to find valid 2,1->2,2 movement for Pawn.");
					
		System.out.println(" - Success");
	}
	
	public void TestPawnMovement2() throws TestException, IOException
	{
		String testName = "TestPawnMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			"..P..\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(2,4,1,3)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,4->1,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,3)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,4->3,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,1,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->1,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,2,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->2,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->3,5 movement for Pawn.");
		
		if(!oTemp.contains(new Move(2,4,2,3)))
			throw new TestException(testName,"Failed to find valid 2,4->2,3 movement for Pawn.");
					
		System.out.println(" - Success");
	}
	
	public void TestPawnMovement3() throws TestException, IOException
	{
		String testName = "TestPawnMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".r...\n" +
			"..P..\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,4,1,3)))
			throw new TestException(testName,"Failed to find valid 2,4->1,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,3)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,4->3,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,1,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->1,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,2,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->2,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->3,5 movement for Pawn.");
		
		if(!oTemp.contains(new Move(2,4,2,3)))
			throw new TestException(testName,"Failed to find valid 2,4->2,3 movement for Pawn.");
					
		System.out.println(" - Success");
	}
	
	public void TestPawnMovement4() throws TestException, IOException
	{
		String testName = "TestPawnMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".R...\n" +
			"..P..\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(2,4,1,3)))
			throw new TestException(testName,"Non valid movement (diag with friendly) 2,4->1,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,3)))
			throw new TestException(testName,"Non valid movement (diag with no enemy) from 2,4->3,3 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,1,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->1,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,2,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->2,5 movement for Pawn.");
		if(oTemp.contains(new Move(2,4,3,5)))
			throw new TestException(testName,"Non valid movement (backwards) from 2,4->3,5 movement for Pawn.");
		
		if(!oTemp.contains(new Move(2,4,2,3)))
			throw new TestException(testName,"Failed to find valid 2,4->2,3 movement for Pawn.");
					
		System.out.println(" - Success");
	}
	
	public void TestPawnMovement5() throws TestException, IOException
	{
		String testName = "TestPawnMovement5";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			"..p..\n" +
			"..P..\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.size() > 0)
			throw new TestException(testName,"Should be no valid moves.");
					
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement1() throws TestException, IOException
	{
		String testName = "TestBishopMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..B..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,0,0)))
			throw new TestException(testName,"Failed to find valid 2,2->0,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,0)))
			throw new TestException(testName,"Failed to find valid 2,2->4,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,0,4)))
			throw new TestException(testName,"Failed to find valid 2,2->0,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,4)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		
		if(oTemp.contains(new Move(2,2,2,0)))
			throw new TestException(testName,"Non valid movement from 2,2->2,0 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,0,2)))
			throw new TestException(testName,"Non valid movement from 2,2->0,2 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,5)))
			throw new TestException(testName,"Non valid movement from 2,2->2,5 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,4,2)))
			throw new TestException(testName,"Non valid movement from 2,2->4,2 movement for Bishop.");
		
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement2() throws TestException, IOException
	{
		String testName = "TestBishopMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"B....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,0,1,1)))
			throw new TestException(testName,"Failed to find valid 0,0->1,1 movement for Bishop.");
		if(!oTemp.contains(new Move(0,0,1,1)))
			throw new TestException(testName,"Failed to find valid 0,0->2,2 movement for Bishop.");
		if(!oTemp.contains(new Move(0,0,1,1)))
			throw new TestException(testName,"Failed to find valid 0,0->3,3 movement for Bishop.");
		if(!oTemp.contains(new Move(0,0,4,4)))
			throw new TestException(testName,"Failed to find valid 0,0->4,4 movement for Bishop.");
		
		if(oTemp.contains(new Move(0,0,4,0)))
			throw new TestException(testName,"Non valid movement from 0,0->4,0 movement for Bishop.");
		if(oTemp.contains(new Move(0,0,0,5)))
			throw new TestException(testName,"Non valid movement from 0,0->0,5 movement for Bishop.");
		
		if(!oTemp.contains(new Move(0,0,1,0)))
			throw new TestException(testName,"Failed to find valid 0,0->1,0 movement for Bishop.");
		if(!oTemp.contains(new Move(0,0,0,1)))
			throw new TestException(testName,"Failed to find valid 0,0->0,1 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement3() throws TestException, IOException
	{
		String testName = "TestBishopMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..B..\n" +
			"..r..\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,0,0)))
			throw new TestException(testName,"Failed to find valid 2,2->0,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,0)))
			throw new TestException(testName,"Failed to find valid 2,2->4,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,0,4)))
			throw new TestException(testName,"Failed to find valid 2,2->0,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,4)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		
		if(oTemp.contains(new Move(2,2,2,0)))
			throw new TestException(testName,"Non valid movement from 2,2->2,0 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,0,2)))
			throw new TestException(testName,"Non valid movement from 2,2->0,2 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,5)))
			throw new TestException(testName,"Non valid movement from 2,2->2,5 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,4,2)))
			throw new TestException(testName,"Non valid movement from 2,2->4,2 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Non valid movement (enemy position) 2,2->2,3 movement for Bishop.");
		
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement4() throws TestException, IOException
	{
		String testName = "TestBishopMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"r....\n" + 
			".....\n" +
			"..B..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,0,0)))
			throw new TestException(testName,"Failed to find valid 2,2->0,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,0)))
			throw new TestException(testName,"Failed to find valid 2,2->4,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,0,4)))
			throw new TestException(testName,"Failed to find valid 2,2->0,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,4)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		
		if(oTemp.contains(new Move(2,2,2,0)))
			throw new TestException(testName,"Non valid movement from 2,2->2,0 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,0,2)))
			throw new TestException(testName,"Non valid movement from 2,2->0,2 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,5)))
			throw new TestException(testName,"Non valid movement from 2,2->2,5 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,4,2)))
			throw new TestException(testName,"Non valid movement from 2,2->4,2 movement for Bishop.");
		
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement5() throws TestException, IOException
	{
		String testName = "TestBishopMovement5";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"R....\n" + 
			".....\n" +
			"..B..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,1,1)))
			throw new TestException(testName,"Failed to find valid 2,2->1,1 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,0)))
			throw new TestException(testName,"Failed to find valid 2,2->4,0 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,0,4)))
			throw new TestException(testName,"Failed to find valid 2,2->0,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,4,4)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		
		if(oTemp.contains(new Move(2,2,0,0)))
			throw new TestException(testName,"Non valid movement (friendly position) from 2,2->0,0 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,0)))
			throw new TestException(testName,"Non valid movement from 2,2->2,0 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,0,2)))
			throw new TestException(testName,"Non valid movement from 2,2->0,2 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,2,5)))
			throw new TestException(testName,"Non valid movement from 2,2->2,5 movement for Bishop.");
		if(oTemp.contains(new Move(2,2,4,2)))
			throw new TestException(testName,"Non valid movement from 2,2->4,2 movement for Bishop.");
		
		if(!oTemp.contains(new Move(2,2,2,1)))
			throw new TestException(testName,"Failed to find valid 2,2->2,1 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,1,2)))
			throw new TestException(testName,"Failed to find valid 2,2->1,2 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,3,2)))
			throw new TestException(testName,"Failed to find valid 2,2->4,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,2,2,3)))
			throw new TestException(testName,"Failed to find valid 2,2->2,3 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestBishopMovement6() throws TestException, IOException
	{
		String testName = "TestBishopMovement6";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".PP.P\n" +
			"R.BQK";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,5,3,4)))
			throw new TestException(testName,"Failed to find valid 2,5->3,4 movement for Bishop.");
		if(!oTemp.contains(new Move(2,5,4,3)))
			throw new TestException(testName,"Failed to find valid 2,5->4,3 movement for Bishop.");
		
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement1() throws TestException, IOException
	{
		String testName = "TestKnightMovement1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..N..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,1,0)))
			throw new TestException(testName,"Failed to find valid 2,2->1,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,0)))
			throw new TestException(testName,"Failed to find valid 2,2->3,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,1)))
			throw new TestException(testName,"Failed to find valid 2,2->0,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,1)))
			throw new TestException(testName,"Failed to find valid 2,2->4,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,3)))
			throw new TestException(testName,"Failed to find valid 2,2->0,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,3)))
			throw new TestException(testName,"Failed to find valid 2,2->4,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,1,4)))
			throw new TestException(testName,"Failed to find valid 2,2->1,4 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,4)))
			throw new TestException(testName,"Failed to find valid 2,2->3,4 movement for Knight.");
		
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement2() throws TestException, IOException
	{
		String testName = "TestKnightMovement2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"N....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(0,0,2,1)))
			throw new TestException(testName,"Failed to find valid 0,0->2,1 movement for Knight.");
		if(!oTemp.contains(new Move(0,0,1,2)))
			throw new TestException(testName,"Failed to find valid 0,0->1,2 movement for Knight.");
		if(oTemp.size() > 2)
			throw new TestException(testName,"Too many moves for Knight.");
			
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement3() throws TestException, IOException
	{
		String testName = "TestKnightMovement3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			".....\n" +
			".....\n" +
			".....\n" +
			"....N";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(4,5,3,3)))
			throw new TestException(testName,"Failed to find valid 4,5->3,3 movement for Knight.");
		if(!oTemp.contains(new Move(4,5,2,4)))
			throw new TestException(testName,"Failed to find valid 4,5->2,4 movement for Knight.");
		if(oTemp.size() > 2)
			throw new TestException(testName,"Too many moves for Knight.");
			
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement4() throws TestException, IOException
	{
		String testName = "TestKnightMovement4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".k...\n" + 
			".....\n" +
			"..N..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,1,0)))
			throw new TestException(testName,"Failed to find valid 2,2->1,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,0)))
			throw new TestException(testName,"Failed to find valid 2,2->3,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,1)))
			throw new TestException(testName,"Failed to find valid 2,2->0,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,1)))
			throw new TestException(testName,"Failed to find valid 2,2->4,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,3)))
			throw new TestException(testName,"Failed to find valid 2,2->0,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,3)))
			throw new TestException(testName,"Failed to find valid 2,2->4,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,1,4)))
			throw new TestException(testName,"Failed to find valid 2,2->1,4 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,4)))
			throw new TestException(testName,"Failed to find valid 2,2->3,4 movement for Knight.");
		
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement5() throws TestException, IOException
	{
		String testName = "TestKnightMovement5";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".k...\n" + 
			".pp..\n" +
			".pN..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(!oTemp.contains(new Move(2,2,1,0)))
			throw new TestException(testName,"Failed to find valid 2,2->1,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,0)))
			throw new TestException(testName,"Failed to find valid 2,2->3,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,1)))
			throw new TestException(testName,"Failed to find valid 2,2->0,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,1)))
			throw new TestException(testName,"Failed to find valid 2,2->4,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,3)))
			throw new TestException(testName,"Failed to find valid 2,2->0,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,3)))
			throw new TestException(testName,"Failed to find valid 2,2->4,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,1,4)))
			throw new TestException(testName,"Failed to find valid 2,2->1,4 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,4)))
			throw new TestException(testName,"Failed to find valid 2,2->3,4 movement for Knight.");
		
		System.out.println(" - Success");
	}
	
	public void TestKnightMovement6() throws TestException, IOException
	{
		String testName = "TestKnightMovement6";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".K...\n" + 
			".....\n" +
			"..N..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		Vector<Move> oTemp = oState.MoveGen();
		if(oTemp.contains(new Move(2,2,1,0)))
			throw new TestException(testName,"Non valid movement 2,2->1,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,0)))
			throw new TestException(testName,"Failed to find valid 2,2->3,0 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,1)))
			throw new TestException(testName,"Failed to find valid 2,2->0,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,1)))
			throw new TestException(testName,"Failed to find valid 2,2->4,1 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,0,3)))
			throw new TestException(testName,"Failed to find valid 2,2->0,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,4,3)))
			throw new TestException(testName,"Failed to find valid 2,2->4,3 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,1,4)))
			throw new TestException(testName,"Failed to find valid 2,2->1,4 movement for Knight.");
		if(!oTemp.contains(new Move(2,2,3,4)))
			throw new TestException(testName,"Failed to find valid 2,2->3,4 movement for Knight.");
		
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc1() throws TestException, IOException
	{
		String testName = "TestPlayerCalc1";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".n.p.\n" + 
			".....\n" +
			"..N..\n" +
			".....\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('W');
		try 
		{
			long start = System.currentTimeMillis();
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.fromSquare.x != 2 || tempMove.fromSquare.y != 2 || 
					tempMove.toSquare.x != 1 || tempMove.toSquare.y != 0)
				throw new TestException(testName,"Failed to capture Knight over Pawn.");
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc2() throws TestException, IOException
	{
		String testName = "TestPlayerCalc2";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".r...\n" +
			".p...\n" +
			".Q...\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('W');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.fromSquare.x == 1 && tempMove.fromSquare.y == 3 && 
					tempMove.toSquare.x == 1 && tempMove.toSquare.y == 2)
				throw new TestException(testName,"Picked a bad move.");
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc3() throws TestException, IOException
	{
		String testName = "TestPlayerCalc3";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".r...\n" +
			"...p.\n" +
			".Q..k\n" +
			".....\n" +
			".q...";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('W');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.fromSquare.x != 1 && tempMove.fromSquare.y != 3 && 
					tempMove.toSquare.x != 4 && tempMove.toSquare.y != 2)
				throw new TestException(testName,"Didn't capture king.");
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc4() throws TestException, IOException
	{
		String testName = "TestPlayerCalc4";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 B\n" + 
			".....\n" + 
			"ppp..\n" +
			".P...\n" +
			"..P..\n" +
			".....\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('B');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.toSquare.x != 1 || tempMove.toSquare.y != 2)
				throw new TestException(testName,"Didn't capture pawn Move: " + tempMove.toString());
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
		
	public void TestPlayerCalc5() throws TestException, IOException
	{
		String testName = "TestPlayerCalc5";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "5 B\n" + 
			"..qbr\n" + 
			"pkppp\n" +
			".P..n\n" +
			".....\n" +
			"..PPP\n" +
			"RNBQK";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('B');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.toSquare.x != 1 || tempMove.toSquare.y != 2)
				throw new TestException(testName,"Didn't capture pawn - " + tempMove.toString());
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc6() throws TestException, IOException
	{
		String testName = "TestPlayerCalc6";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			".....\n" + 
			".....\n" +
			"..p..\n" +
			".p...\n" +
			"PPP..\n" +
			".....";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('W');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.toSquare.x != 1 || tempMove.toSquare.y != 3)
				throw new TestException(testName,"Didn't capture pawn Move: " + tempMove.toString());
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
	public void TestPlayerCalc7() throws TestException, IOException
	{
		String testName = "TestPlayerCalc7";
		System.out.print("Running " + testName);
		
		State oState = new State();
		String board = "1 W\n" + 
			"....r\n" + 
			"qnp.p\n" +
			"k..bP\n" +
			"...P.\n" +
			"R....\n" +
			".NBQK";
		oState.ReadBoard(new StringReader(board));
		
		ChessPlayer oPlayer = new ChessPlayer('W');
		try 
		{
			long start = System.currentTimeMillis();
			
			Move tempMove = oPlayer.GetNextMove(oState, 1000);
			if(tempMove.toSquare.x != 0 || tempMove.toSquare.y != 2)
				throw new TestException(testName,"Didn't capture king Move: " + tempMove.toString());
			long elapsedTimeMillis = System.currentTimeMillis()-start;
			if(elapsedTimeMillis > 1500)
				throw new TestException(testName,"Took too long - " + elapsedTimeMillis);
		} catch (MoveException e) 
		{
			throw new TestException(testName, "Move Error detected: " + e.ErrorDescription);
		}
		System.out.println(" - Success");
	}
	
}
