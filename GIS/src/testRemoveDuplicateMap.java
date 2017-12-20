import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

public class testRemoveDuplicateMap {

	@Test
	public void test() {
		ArrayList<wifiSpot> arrayWifi = new ArrayList<wifiSpot>();
		ArrayList<wifiSpot> arrayWifi2 = new ArrayList<wifiSpot>();
		wifiSpot wifi = new wifiSpot("wifi", "mac1", "5000", "-80");
		wifiSpot wifi1 = new wifiSpot("wifi2", "mac2","5000", "-90");
		wifiSpot wifi2 = new wifiSpot("wifi2", "mac2", "5000", "-70");
	
		
		arrayWifi.add(wifi);
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
		db.removeDuplicateMac();
		assertEquals(1, sc.get(1).getWifiSpotsList().size());
		assertEquals(1, sc.get(0).getWifiSpotsList().size());
		}

}
