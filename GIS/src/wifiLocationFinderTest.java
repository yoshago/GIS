import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

public class wifiLocationFinderTest {


	@Test
	public void testFindSpotsLocation() {
		ArrayList<wifiSpot> arrayWifi = new ArrayList<wifiSpot>();
		ArrayList<wifiSpot> arrayWifi2 = new ArrayList<wifiSpot>();

		coordinate coor1=new coordinate(35.5,32.5,750.0);
		coordinate coor2=new coordinate(36.0,33.0,760.0);

		wifiSpot wifi1 = new wifiSpot("wifi2", "mac2","5000", "-90",coor1);
		wifiSpot wifi2 = new wifiSpot("wifi2", "mac2", "5000", "-70",coor2);
	    
		
		arrayWifi.add(wifi1);
		arrayWifi2.add(wifi2);

		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		coordinate earth = new coordinate(100.0, 34.0, 890.0);
		String id = "id";
		singleScan scan1 = new singleScan(date.toString(), id, earth, arrayWifi);
		singleScan scan3 = new singleScan(date.toString(), id, earth, arrayWifi2);
		ArrayList<singleScan> sc = new ArrayList<singleScan>();
		DB db = new DB(sc);
		sc.add(scan1);
		sc.add(scan3);
		wifiLocationFinder wlf=new wifiLocationFinder(db);
		wlf.findSpotsLocation();
		coordinate test=new coordinate(35.81153846153846,32.81153846153846,756.2307692307692);
		assertEquals(0,wlf.getFinalWifiList().get(0).getCoordinate().compare(test));
	}


}
