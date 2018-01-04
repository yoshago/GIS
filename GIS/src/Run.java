import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Algorithms.personLocationFinder;
import Libraries.read;
import Libraries.write;
import objects.DB;

/**
 * @author ישי
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("please enter path for input");
//		Scanner userInput=new Scanner(System.in);
//		String inputPath=userInputnextLine();
//		Folder f=new Folder(inputPath);
//		processCsv processCsv=new processCsv(f.getOutputPath());
//		DB db1 = new DB("C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\data",2);
//		wifiLocationFinder wlf = new wifiLocationFinder(db1);
//		wlf.findSpotsLocation();
//		write.algo1toCsv(wlf.getFinalWifiList());
		personLocationFinder lf=new personLocationFinder("C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\data","C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\input");
		lf.findLocation();
		write.writeCsvFile(lf.getInput().getScansList(), "C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\Algo2_BM3_TS1_");
//		File f = new File("C:/Users/Yehonatan/workspace/wigle Files");
//		DB x =new DB(read.readFolder(f.listFiles()));
//		x.toTable();

	}

}
