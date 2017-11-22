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

	private String path;
	private File[] listOfFiles;
	private ArrayList<singleScan> singleScansList;
	
	public Folder(String s)
	{
		this.path=s;
		File folder = new File(this.path);
		this.listOfFiles = folder.listFiles();
		this.singleScansList=read.readFolder(listOfFiles);
		write.writeCsvFile(this.singleScansList,"output");
	}
}
