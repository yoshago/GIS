import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class ReadTest {

	@Test
	public void test() {
		File f=new File("C:\\git\\GIS\\GIS");
		
		assertEquals(0, read.readFolder(f.listFiles()).size());
	}

}
