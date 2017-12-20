import java.util.Scanner;

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
//		DB db1 = new DB("C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\1",2);
//		wifiLocationFinder wlf = new wifiLocationFinder(db1);
//		wlf.findSpotsLocation();
//		write.algo1toCsv(wlf.getFinalWifiList());
		personLocationFinder lf=new personLocationFinder("C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\data","C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\input");
		lf.findLocation();
		write.writeCsvFile(lf.input, "C:\\Users\\Yehonatan\\Downloads\\testing\\Gmail (3)\\output.csv");
	}

}
