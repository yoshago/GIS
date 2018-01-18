package test.java;

import static org.junit.Assert.*;

import java.io.File;


import org.junit.Test;

import main.java.GUI.gisGui;
import main.java.objects.DB;

import main.java.objects.Server;

public class testServer {
	gisGui window;
	Server s=new Server(window);
	File f=new File("test\\test.csv");
	File f2=new File("test\\test2.csv");
	DB db=new DB(f);
	DB db2=new DB(f2);
	String[] arr=new String[6];
	String[] arr2=new String[6];
	 String path=("test");
	@Test
	public void testAddDB_andFilter() {
		s.addDB(db);
		arr[0]="0";
    	arr[1]="1";
    	arr[2]="ys";
    	s.filter(arr);
    	arr2[0]="1";
    	arr2[1]="1";
    	arr2[2]="d";
    	s.filter(arr2);
    	s.addDB(db2);
    	s.saveFilters("test");
    	assertEquals(4, s.getDbs().peek().getScansList().size());
	}
	
	@Test
	public void testAddCombFile() //test==true, the exeption is only in the test(it's OK) 
	{
		s.addCombFile(f);
		s.addCombFile(f2);
		assertEquals(2, s.getFu().getFileLastModifiedList().size());
	}
	
	@Test
	public void testAddwigleFolder() 
	{
		s.addWigleFolder(path);
		assertEquals(3, s.getFu().getWigleFilesList().size());
	}
	
	public void testSaveFilter() 
	{
		s.filter(arr);
		s.filter(arr2);
		
	}
	
	@Test
	public void testOpenFilter() 
	{
		s.openFilter("test\\Filters_2018-01-04 22-21-00.ser");
		assertEquals(2, s.getFs().size());
	}
	
}
