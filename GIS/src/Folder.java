import java.io.File;
import java.util.ArrayList;


/**
 * @author Yontan&Yishay
 * @description the class represents an object of type Folder that contains path to folder, 
 *              list of files in folder,
 *              list of all data from the files in folder,
 *              path for output file that exported after data processing 
 *
 */

public class Folder implements readWriteInterface{
    private String outputPath;
	private String path;
	private File[] listOfFiles;
	private ArrayList<singleScan> singleScansList;
	
	/**
	 * @param path-contains path to the input folder 
	 * @description this constructor get path, update listOfFiles, 
	 *              and call functions from read and write libraries   
	 */
	public Folder(String path)
	{
		this.path=path;
		File folder = new File(this.path);
		this.listOfFiles = folder.listFiles();
		read();
		write();
	}

	public void read()
	{
		this.singleScansList=read.readFolder(listOfFiles);
	}
	public void write()
	{
		this.outputPath=write.writeCsvFile(this.singleScansList,this.path+"\\output");
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

	public String getPath() {
		return path;
	}

	public File[] getListOfFiles() {
		return listOfFiles;
	}
	
	
}
