import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class testWifiSpot {

	wifiSpot ws=new wifiSpot("mac", "ssid", "channel", "-32", new coordinate());
	wifiSpot higherSignal=new wifiSpot("mac", "ssid", "channel", "-25", new coordinate());
	wifiSpot lowerSignal=new wifiSpot("mac", "ssid", "channel", "-92", new coordinate());
	wifiSpot equalSignal=new wifiSpot("mac", "ssid", "channel", "-32", new coordinate());

	@Test
	public void compareBySignalTest() {
		
		assertEquals(-1, ws.compareBySignal(higherSignal));
		assertEquals(1, ws.compareBySignal(lowerSignal));
		assertEquals(0, ws.compareBySignal(equalSignal));

	}

}
