import static org.junit.Assert.*;

import org.junit.Test;


public class testFolder {

	@Test
	public void test() {
		Folder f=new Folder("for test folder");// "for test folder" is name of the folder for test, this folder is in project folder. 
		assertTrue(f.getOutputPath()!=null);
		assertTrue(f.getSingleScansList().size()!=0);
		Folder f1=new Folder("test folder1");
		
		assertEquals(4,f1.getSingleScansList().size());
	}

}
