import static org.junit.Assert.*;

import org.junit.Test;

public class testCsvProcess {

	@Test
	public void test() {
		processCsv pc=new processCsv("test folder1\\output2017-11-23 20-07-47.csv");
		assertEquals(4,pc.getSingleScanList().size()); //for this test you mustn't filter 
	}

}
