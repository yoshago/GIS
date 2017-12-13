import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class ImaginationScanList {

private ArrayList<ImaginationScan> list;
private int size=0;

	public ImaginationScanList()
	{
		this.list=new ArrayList<ImaginationScan>();
	}
	public boolean add(ImaginationScan scan)
	{
		if(this.size<4)
		{
			list.add(scan);
			this.size++;
			return true;
		}
		else
		{
			int i=0;
			while(i<size && scan.compare(this.list.get(i))>0)
			{
				i++;
			}
			if(i<=3)
			{	
				list.add(scan);
				size++;
				return true;
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
