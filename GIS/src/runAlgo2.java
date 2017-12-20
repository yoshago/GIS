/**
 * 
 */

/**
 * @author ישי
 *
 */
public class runAlgo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocationFinder lf=new LocationFinder("C:\\Users\\ישי\\Desktop\\testalgo","C:\\Users\\ישי\\Desktop\\testnogps");
		lf.findLocation();
		write.writeCsvFile(lf.input, "C:\\Users\\ישי\\Desktop\\testnogps\\check1.csv");
	}
}
