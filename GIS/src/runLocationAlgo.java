import java.io.File;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class runLocationAlgo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f;
		String path="C:\\Users\\ישי\\Desktop\\testalgo";
		f=new File(path);
		File[] listOfFiles=f.listFiles();
		ArrayList<singleScan> dataBase=read.readOutputFolder(listOfFiles);
		path="C:\\Users\\ישי\\Desktop\\testalgo2";
		f=new File(path);
		listOfFiles=f.listFiles();
		ArrayList<singleScan> input=read.readOutputFolder(listOfFiles);
		userLocation up =new userLocation(dataBase,input);
		up.Locating();
		
	}

}
