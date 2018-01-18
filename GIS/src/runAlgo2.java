import main.java.Algorithms.personLocationFinder;
import main.java.Libraries.write;
import main.java.objects.DB;
import main.java.objects.coordinate;

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
//		personLocationFinder lf=new personLocationFinder("C:\\Users\\ישי\\Desktop\\testalgo","C:\\Users\\ישי\\Desktop\\testnogps");
//		lf.findLocation();
//		write.writeCsvFile(lf.getInput().getScansList(), "C:\\Users\\ישי\\Desktop\\testnogps\\");
		DB db=new DB("C:\\Users\\ישי\\Desktop\\testalgo",2);
		
		personLocationFinder lf=new personLocationFinder(db,"03/12/2017 08:53,model=Lenovo PB2-690Y_device=PB2PRO,32.10487307,35.21134308,692,2,1c:b9:c4:15:42:68,Ariel_University,11,-85,1c:b9:c4:15:44:58,Ariel_University,11,-88");
		coordinate coor=lf.findLocation();
		System.out.println(coor.toString());
	
	}
}
