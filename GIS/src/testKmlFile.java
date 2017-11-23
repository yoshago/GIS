import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class testKmlFile {

	@Test
	
	
	public void testRemvoeDuplicates() {
		Folder f = new Folder("C:\\Users\\Yehonatan\\git\\GIS\\GIS");
		ArrayList<singleScan> ssl = f.getSingleScansList();
		kmlFile KmlFile = new kmlFile(ssl,"C:\\Users\\Yehonatan\\git\\GIS\\GIS");
		assertEquals(6, KmlFile.getScansList().get(0).getWifiSpotsList().size());
		assertEquals(true, KmlFile.isMarshalTest());
	}

}
