import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yehonatan&Yishay
 * @description this object contains: 
 *              1. csv file- exported by Folder class (using write library).
 *              2. singleScan list- a list of singleScan objects that each one of them contains the data of a one scan of the wigleWifi app.
 *              this class call Filter class and send all data after filtering to kmlFile class
 *               
 * 
 */


public class processCsv {
	private File f;
	private ArrayList<singleScan> singleScanList;

	public processCsv(String path)
	{
		this.f=new File(path);
		this.singleScanList=read.readOutputFolderFile(f);
		Filter.mainFilter(this.singleScanList);
		toKML();
		write.writeCsvFile(this.singleScanList,"processCsvTestFile");
	}
	
	
	/**
	 * this function take output path from user and send all data and output path to kmlFile
	 */
	private void toKML()
	{
		String kmlOutputPath;
		System.out.println("please enter path for output");
		Scanner userInput=new Scanner(System.in);
		kmlOutputPath=userInput.nextLine();
		kmlFile kmlFile= new kmlFile(this.singleScanList, kmlOutputPath);
		userInput.close();
	}
    

	public File getF() {
		return f;
	}


	public ArrayList<singleScan> getSingleScanList() {
		return singleScanList;
	}
	
}
