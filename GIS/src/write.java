/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ישי
 *
 */
public class write {

	public static void writeCsvFile(ArrayList<singleScan> singleScanList,String name)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String nameOfFile=name + dateFormat.format(date) + ".csv";
		try
		{
			PrintWriter pw = new PrintWriter(new File(nameOfFile));
			pw.write(singleScanListToCsvString(singleScanList));	
			pw.close();
		}
		catch(IOException ex)
		{
			System.out.println("error writing file "+ex);
			System.exit(2);
		}
	}
	
	public static String singleScanListToCsvString(ArrayList<singleScan> singleScanList)
	{
		String s="time,id,lon,lat,alt,size,";
 		for(int i=0;i<=9;i++)
 		{
 			s+="mac"+i+",ssid"+i+",channel"+i+",signal"+i+",";
 		}
 		s+="\n";
 		for(int i=0;i<singleScanList.size();i++)
 		{
 			s=s+singleScanList.get(i).toString()+"\n";
 		}
 		return s;
	}
}
