import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testKmlFile {

	@Test
	
	
	
	public void test() {
		Folder f = new Folder("C:\\Users\\Yehonatan\\git\\GIS\\GIS");
		ArrayList<singleScan> before = f.getSingleScansList();
		ArrayList<singleScan> after = before;
		kmlFile KmlFile = new kmlFile(after,"C:\\Users\\Yehonatan\\git\\GIS\\GIS");
		assertEquals(10, before.get(0).getSize());
		System.out.println(after.get(0).getSize());
		assertEquals(5, after.get(0).getSize());
	}

}
