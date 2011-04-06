/////////////////////////////////////////////////////
//TestException
//	Custom Exception used as part of the unit test 
//		framework.  When a unit test fails this exception
//		is thrown with the details.
/////////////////////////////////////////////////////////
@SuppressWarnings("serial")
public class TestException extends Exception 
{
	//Unit test causing the issue 
	public String TestName;
	//Detailed description of the problem
	public String FailureReason;
	
	//Basic constructor
	public TestException(String testName, String failureReason)
	{
		TestName = testName;
		FailureReason = failureReason;
	}
}
