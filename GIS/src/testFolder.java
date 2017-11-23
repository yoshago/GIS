import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Yehonatan&Yishay
 * @description testing class Folder.
 */
public class testFolder {

	@Test
	public void test() {
		Folder f=new Folder("for test folder");
		assertTrue(f.getOutputPath()!=null);
		assertTrue(f.getSingleScansList().size()!=0);
	}

}
