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
		write.writeCsvFile(toCsvString());
	}
	
	private String toCsvString()
	{
		String s="time,id,lon,lat,alt,size,";
 		for(int i=0;i<=9;i++)
 		{
 			s+="mac"+i+",ssid"+i+",channel"+i+",signal"+i+",";
 		}
 		s+="\n";
 		for(int i=0;i<this.singleScansList.size();i++)
 		{
 			s=s+singleScansList.get(i).toString()+"\n";
 		}
 		return s;
	}
	
}
