package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import main.java.objects.DB;
import main.java.objects.DBStack;
import main.java.objects.Filter;

public class DBStackTest {

	File f=new File("C:\\Users\\ישי\\Desktop\\test\\test.csv");
	DB db=new DB(f);
    DBStack dbs= new DBStack(db);
    String[] arr=new String[6];
    String[] arr2=new String[6];
    
    @Test
    public void testAndFilter()
    {
    	arr[0]="0";
    	arr[1]="1";
    	arr[2]="ys";
    	
    	Filter f1=new Filter(arr);
    	Arrays.toString(arr);
    	dbs.filter(f1);
    	assertEquals(2, dbs.peek().getScansList().size());
    	
    }
    @Test
    public void testAndOrFilter()
    {
    	arr[0]="0";
    	arr[1]="1";
    	arr[2]="ys";
    	Filter f1=new Filter(arr);
    	arr2[0]="1";
    	arr2[1]="1";
    	arr2[2]="d";
    	Filter f2=new Filter(arr2);
    	dbs.filter(f1);
    	dbs.filter(f2);
    	assertEquals(3, dbs.peek().getScansList().size());
    }
    
    @Test
	public void testClear()
	{
		arr[0]="0";
    	arr[1]="1";
    	arr[2]="ys";
    	Filter f1=new Filter(arr);
    	dbs.filter(f1);
    	dbs.clear();
    	assertEquals(1, dbs.size());
	}
    
    @Test
    public void testClearAll()
    {
    	dbs.clearAll();
    	assertEquals(0, dbs.size());
    }

}
