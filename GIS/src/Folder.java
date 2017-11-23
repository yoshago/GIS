import java.io.File;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class Folder {
    private String outputPath;
	private String path;
	private File[] listOfFiles;
	private ArrayList<singleScan> singleScansList;
	
	public Folder(String s)
	{
		this.path=s;
		File folder = new File(this.path);
		this.listOfFiles = folder.listFiles();
		this.singleScansList=read.readFolder(listOfFiles);
		this.outputPath=write.writeCsvFile(this.singleScansList,path+"\\output");
	}

	public ArrayList<singleScan> getSingleScansList() {
		return singleScansList;
	}

	public void setSingleScansList(ArrayList<singleScan> singleScansList) {
		this.singleScansList = singleScansList;
	}

	public String getOutputPath() {
		return outputPath;
	}
	
}
