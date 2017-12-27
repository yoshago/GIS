package Libraries;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.singleScan;
import objects.wifiSpot;

/**
 * @author Yehonatan&Yishay
 * @description this library including write function, that used in classes: Folder,processCsv.
 * @include writeCsvFile function that export file, 
 *          and singleScanListToCsvString function that make String from list of singleScan 
 */



public class write {

	
	/**
	 * @param singleScanList- singleScan list  that contains all the data.
	 * @param pathOutput- String that contains output path
	 * @return return path+name of the exported file.
	 */
	public static boolean writeCsvFile(ArrayList<singleScan> scansList,String pathOutput)
	{
		boolean flag=false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String nameOfFile=pathOutput + dateFormat.format(date) + ".csv";
		try
		{
			PrintWriter pw = new PrintWriter(new File(nameOfFile));
			pw.write(singleScanListToCsvString(scansList));	
			pw.close();
		}
		catch(IOException ex)
		{
			System.out.println("error writing file "+ex);
			System.exit(2);
		}
		System.out.println("done print csv file");
		flag=true;
		return flag;
	}
	
	/**
	 * @param singleScanList- singleScan list  that contains all the data.
	 * @return String of all the data from singleScanList
	 */
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
	
	public static void algo1toCsv(ArrayList<wifiSpot> wsl)
	{
		String nameOfFile="algo1output.csv";
		try
		{
			PrintWriter pw = new PrintWriter(new File(nameOfFile));
			pw.write(algo1toString(wsl));	
			pw.close();
		}
		catch(IOException ex)
		{
			System.out.println("error writing file "+ex);
			System.exit(2);
		}
		System.out.println("done print csv file");
	}

	private static String algo1toString(ArrayList<wifiSpot> wsl)
	{
		String s="";
		for(int i=0;i<wsl.size();i++)
		{
			s+=wsl.get(i).toString()+wsl.get(i).getCoordinate().toString()+"\n";
		}
		return s;
	}
	
}
