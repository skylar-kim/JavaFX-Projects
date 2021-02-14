package ListsFiles;

import java.io.*;
import java.util.*;

public class Exceptional
{
	Scanner in;
    public static void main( String[] args )
    {
    	try
    	{ new Exceptional(); }
    	catch(BarryException be )
    	{ System.out.println("BarryException happened"); }
    	
    }
    
    public Exceptional() throws BarryException
    {
    	in = new Scanner(System.in);
    	System.out.println("enter a number, 7 to crash: ");
    	int answer = in.nextInt();
    	if ( answer==7 )
    	{
    		throw new BarryException();
    	}
    	System.out.println("Exceptional constructor finished normally");
    }
    
    public class BarryException extends Exception
    {
    	static final long serialVersionUID = 1;
    }
}
