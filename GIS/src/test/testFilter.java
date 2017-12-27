package test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import Libraries.Filter;
import Libraries.read;
import objects.singleScan;


public class testFilter {

	@Test
	public void filterByTimeTest() {//  from time: 31/10/2017 10:26:18   to time: 31/10/2017 10:26:18
		ArrayList<singleScan> singleScanList=new ArrayList<singleScan>();
		File f=new File("WigleWifi_20171031103241.csv");
		read.readFile(f, singleScanList);
		Filter.filterByTime(singleScanList); 
		assertEquals(8, singleScanList.get(0).getSize());
	}
	
	public void filterByLocationTest() {//min:34.9881520041904,32.1377246464064 max:34.9882748210541,32.1378214163929
		ArrayList<singleScan> singleScanList=new ArrayList<singleScan>();
		File f=new File("WigleWifi_20171031103241.csv");
		read.readFile(f, singleScanList);
		Filter.filterByLocation(singleScanList);      
		assertEquals(5, singleScanList.get(0).getSize()+singleScanList.get(1).getSize());

	}
	
	public void filterByID() {//id: GT-I9500
		ArrayList<singleScan> singleScanList=new ArrayList<singleScan>();
		File f=new File("WigleWifi_20171031103241.csv");
		read.readFile(f, singleScanList);
		File f1=new File("WigleWifi_20171109191315.csv");
		read.readFile(f1, singleScanList);
		Filter.filterByID(singleScanList);
		int size=0;
		for(int i=0;i<singleScanList.size();i++){
			size+=singleScanList.get(i).getSize();
		}
		assertEquals(26, size);
	}

}
