import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class testCoordinate {

	coordinate coor=new coordinate("6","5");
	coordinate bigger=new coordinate("12", "10.0", "5.0");
	coordinate smaller=new coordinate("3","4","50");
	coordinate strange=new coordinate("5","7","0");
	@Test
	public void compareTest() {
		assertEquals(-1, coor.compare(bigger));
		assertEquals(1, coor.compare(smaller));
		assertEquals(0, coor.compare(strange));
	}
	
	
	

}
