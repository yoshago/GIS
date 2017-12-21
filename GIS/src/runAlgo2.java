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
		personLocationFinder lf=new personLocationFinder("C:\\Users\\ישי\\Desktop\\testalgo","C:\\Users\\ישי\\Desktop\\testnogps");
		lf.findLocation();
		write.writeCsvFile(lf.input.getScansList(), "C:\\Users\\ישי\\Desktop\\testnogps\\check1.csv");
	}
}
