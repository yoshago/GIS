package test.java;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.micromata.opengis.kml.v_2_2_0.Kml;
import main.java.objects.DB;
import main.java.objects.kmlFile;
import main.java.objects.singleScan;

public class testKmlFile {

	@Test
	public void testKmlFile() {
		DB db = new DB("C:\\Users\\Yehonatan\\git\\GIS\\GIS",1);
		ArrayList<singleScan> ssl = db.getScansList();
		kmlFile KmlFile = new kmlFile(ssl,"C:\\Users\\Yehonatan\\git\\GIS\\GIS");
		assertEquals(6, KmlFile.getScansList().get(0).getWifiSpotsList().size());
		assertEquals(true, KmlFile.isMarshalTest());
	}

}
