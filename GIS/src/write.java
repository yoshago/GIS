/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ישי
 *
 */
public class write {

	public static void writeCsvFile(String s)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String nameOfFile="output "+dateFormat.format(date)+".csv";
		try
		{
			PrintWriter pw = new PrintWriter(new File(nameOfFile));
			pw.write(s);	
			pw.close();
		}
		catch(IOException ex)
		{
			System.out.println("error writing file "+ex);
			System.exit(2);
		}
	}
}
