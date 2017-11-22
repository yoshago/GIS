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

	public static String writeCsvFile(ArrayList<singleScan> singleScanList,String pathOutput)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String nameOfFile=pathOutput + dateFormat.format(date) + ".csv";
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
		System.out.println("done print csv file");
		return nameOfFile;
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
