import java.io.File;
import java.util.ArrayList;

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
//		toKML();
	}
	
	
//	private void toKML()
//	{
//		csvToKml csvk= new csvToKml(this.singleScanList);
//		//		this.toCSV();
//		csvk.ScanToKml();
//		csvk.exportToKml();
//	}

	
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
