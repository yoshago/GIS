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
	public void readFolderTest() {
		File f=new File("C:\\");
		assertEquals(0, read.readFolder(f.listFiles()).size());
	}

}
