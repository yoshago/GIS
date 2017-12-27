import static org.junit.Assert.*;

import org.junit.Test;

public class testSingleScan {
///test add and removeWorstSignal
	@Test
	public void addTest() {
		singleScan sc=new singleScan();
		assertEquals(0,sc.getSize());
		sc.add(new wifiSpot("mac", "ssid", "channel", "-20", new coordinate()));
		assertEquals(1,sc.getSize());
		sc.add(new wifiSpot("mac", "ssid", "channel", "-20", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-80", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-30", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-40", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-50", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-60", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-70", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-20", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-27", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-90", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-20", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-23", new coordinate()));
		sc.add(new wifiSpot("mac", "ssid", "channel", "-20", new coordinate()));
		assertEquals("-20",sc.getWifiSpotsList().get(0).getSignal());
		assertEquals("-20",sc.getWifiSpotsList().get(1).getSignal());
		assertEquals("-20",sc.getWifiSpotsList().get(2).getSignal());
		assertEquals("-20",sc.getWifiSpotsList().get(3).getSignal());
		assertEquals("-20",sc.getWifiSpotsList().get(4).getSignal());
		assertEquals("-23",sc.getWifiSpotsList().get(5).getSignal());
		assertEquals("-27",sc.getWifiSpotsList().get(6).getSignal());
		assertEquals("-30",sc.getWifiSpotsList().get(7).getSignal());
		assertEquals("-40",sc.getWifiSpotsList().get(8).getSignal());
		assertEquals("-50",sc.getWifiSpotsList().get(9).getSignal());
	}

}
