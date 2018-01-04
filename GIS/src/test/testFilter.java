package test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import objects.DB;
import objects.Filter;
import objects.singleScan;


public class testFilter {
	File f=new File("test\\test.csv");
	File f1=new File("test\\test3.csv");
	DB db=new DB(f);
	DB db1=new DB(f1);
	String[] arr= new String[6];
	
	@Test
	public void filterByTimeTest() {
		arr[0]="0";
    	arr[1]="1";
    	arr[2]="2017-11-09  13:15:00";
 		arr[3]="2017-11-09  13:22:00";
 		Filter f=new Filter(arr);
		db1.filter(f);
 		assertEquals(4, db1.getScansList().size());
	}
	@Test
	public void filterByTimeNOTtest() {
		arr[0]="0";
    	arr[1]="0";
    	arr[2]="2017-11-09 13:15:00";
 		arr[3]="2017-11-09 13:21:00";
 		Filter f=new Filter(arr);
		db1.filter(f);
 		assertEquals(2, db1.getScansList().size());
	}
	@Test
	public void filterByLocationTest() {
		arr[0]="0";
    	arr[1]="1";
    	arr[2]="35.2070";
 		arr[3]="32.0";
 		arr[4]="35.2072";
 		arr[5]="33.2";
 		Filter f=new Filter(arr);
		db.filter(f);
 		assertEquals(4, db.getScansList().size());  
	}
	@Test
	public void filterByLocationNOTtest() {
		arr[0]="0";
    	arr[1]="0";
    	arr[2]="35.2070";
 		arr[3]="32.0";
 		arr[4]="35.2072";
 		arr[5]="33.2";
 		Filter f=new Filter(arr);
		db.filter(f);
 		assertEquals(2, db.getScansList().size());  
	}
	@Test
	public void filterByIDtest() {
		arr[0]="0";
    	arr[1]="1";
    	arr[2]="ys";
    	Filter f=new Filter(arr);
		db.filter(f);
    	assertEquals(2, db.getScansList().size());
	}
	@Test
	public void filterByIDNOTtest() {
		arr[0]="0";
    	arr[1]="0";
    	arr[2]="ys";
    	Filter f=new Filter(arr);
		db.filter(f);
    	assertEquals(4, db.getScansList().size());
	}

}
