package ListsFiles;

import java.io.*;
import java.util.*;

public class FileReadDemo
{
	Scanner scan;
	
   public static void main( String[] args )
   {
	   new FileReadDemo();
   }
   /**
    * constructor
    */
   public FileReadDemo()
   {
	  try
	  {
    	   loadData();
    	   // processData();
    	   // outputResults();
	  }
	  catch(Exception e)
	  { System.out.println("something happened: "+e); }
	 
   }
   
   public void loadData() throws Exception
   {
	  //try
	  //{
           scan = new Scanner(new File("data.txt"));
	   	   while ( scan.hasNextLine() )
	   	   {
	   		    String s = scan.nextLine();
	   		    System.out.println("read="+s);
	   	   }
/*
	  }
	  catch(FileNotFoundException fe)
	  { System.out.println("can't find the file."); }
      catch( Exception e )
	  { System.out.println("loadData: exception="+e);}
		  
*/
   }
}
