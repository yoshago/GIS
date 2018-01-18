package main.java.Algorithms;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class ImaginationScansList {

private ArrayList<ImaginationScan> list;
private int size=0;

	public ImaginationScansList()
	{
		this.list=new ArrayList<ImaginationScan>();
	}
	public boolean add(ImaginationScan scan)
	{
		if (scan.getImagination()>-0.5){
		if(this.size==0)
		{
			list.add(scan);
			this.size++;
			return true;
		}
		else
		{
			int i=0;
			while(i<size && scan.compareTo(this.list.get(i))>0)
			{
				i++;
			}
			if(i<=3)
			{	
				list.add(i,scan);
				if(size<=4)
				size++;
				return true;
			}
		}
		}
		return false;
	}

	public int getSize()
	{
		return size;
	}
	public ArrayList<ImaginationScan> getList() {
		return list;
	}

}
