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
		LocationFinder lf=new LocationFinder("C:\\Users\\Yehonatan\\Documents\\OOP EX2 Test1\\comb","C:\\Users\\Yehonatan\\Documents\\OOP EX2 Test1\\noGps");
		lf.findLocation();
		write.writeCsvFile(lf.input, "C:\\Users\\Yehonatan\\Documents\\OOP EX2 Test1\\check1.csv");
	}

}
