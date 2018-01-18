package test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import main.java.objects.wifiSpot;
import main.java.objects.coordinate;

public class testWifiSpot {

	wifiSpot ws=new wifiSpot("mac", "ssid", "channel", "-32", new coordinate());
	wifiSpot higherSignal=new wifiSpot("mac", "ssid", "channel", "-25", new coordinate());
	wifiSpot lowerSignal=new wifiSpot("mac", "ssid", "channel", "-92", new coordinate());
	wifiSpot equalSignal=new wifiSpot("mac", "ssid", "channel", "-32", new coordinate());

	@Test
	public void compareBySignalTest() {
		
		assertEquals(-1, ws.compareTo(higherSignal));
		assertEquals(1, ws.compareTo(lowerSignal));
		assertEquals(0, ws.compareTo(equalSignal));

	}

}
