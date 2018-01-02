/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import objects.DB;
import objects.DBStack;
import objects.Filter;

/**
 * @author ישי
 *
 */
public class testDBStack {
    File f=new File("C:\\Users\\ישי\\Desktop\\‏‏\\תיקיה חדשהtest.csv");
	DB db=new DB(f);
    DBStack dbs= new DBStack(db);
    String[] arr={"0","1","ys"};
    String[] arr2={"1","1","d"};
    Filter f1=new Filter(arr);
    Filter f2=new Filter(arr2);
    
    public void testAndFilter()
    {
    	dbs.filter(f1);
    	assertEquals(2, dbs.Size());
    }
    public void testOrFilter()
    {
    	dbs.filter(f2);
    	assertEquals(3, dbs.Size());
    }
    
	
}
