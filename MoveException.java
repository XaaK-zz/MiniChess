////////////////////////////////////////////////////////
//MoveException
//	Custom Exception class designed to tell clients there has
//		been  move error.
////////////////////////////////////////////////////////
@SuppressWarnings("serial")
public class MoveException extends Exception 
{
	//Specific moving issue
	public String ErrorDescription;
	
	//basic constructor
	public MoveException(String desc)
	{
		ErrorDescription = desc;
	}
}
