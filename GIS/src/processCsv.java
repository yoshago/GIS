import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class take the .csv file that class scanFolder export and filter him by user preferences
 * this class uses class csvToKml and used by it 
 * 
 */

public class processCsv {
	private File f;
	private ArrayList<singleScan> singleScanList;
	private String[] MinCoordinate;
	private String[] MaxCoordinate;

	public processCsv(String path)
	{
		this.f=new File(path);
		this.singleScanList=read.readOutputFolderFile(f);
		Filter.mainFilter(this.singleScanList);
		toKML();
	}
	
	
	private void toKML()
	{
		String kmlOutputPath;
		System.out.println("please enter path for output");
		Scanner userInput=new Scanner(System.in);
		kmlOutputPath=userInput.nextLine();
		kmlFile kmlFile= new kmlFile(this.singleScanList, kmlOutputPath);
		userInput.close();
	}

	
	public String[] getMinCoordinate(){
		return MinCoordinate;
	}

	public String[] getMaxCoordinate(){
		return MaxCoordinate;
	}

	public File getF() {
		return f;
	}


	public ArrayList<singleScan> getSingleScanList() {
		return singleScanList;
	}
	
}
