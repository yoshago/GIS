/**
 * 
 */
package objects;

import java.util.Stack;

/**
 * @author ישי
 *
 */
public class DBStack extends Stack<DB>{

	
	public DBStack()
	{
	    	super();
	}
	
	
	
	public DBStack(DB db)
	{
		super();
		super.push(db);
	}
	
	
	public DB push(DB db)
	{
		return super.push(db);
	}
	
	public DB pop()
	{
		return super.pop();
	}
	
	public void clear()
	{
		while(Size()>1)
			this.pop();
	}
	
	public void clearAll()
	{
		super.clear();
	}
	
	public int Size()
	{
		return super.size();
	}
	
	public void orFilter(int type, int not)
	{
		if(this.Size()>1)
		{
			DB db=new DB(this.peek());
			db.Filter(type,not);
		}
		else
		{
			DB db=new DB(this.get(0));
			db.Filter(type,not);
			for(int i=1;i<this.size();i++)
			{
				singleScan scan=this.peek().getScansList().get(i);
				if(!db.contains(scan))
					db.getScansList().add(scan);
			}
		}
	}
	
	public void andFilter(int type, int not)
	{
		if(this.Size()>1)
		{
			DB db=new DB(this.peek());
			db.Filter(type, not);
		}
		else
		{
		DB db=new DB(this.peek());
		db.Filter(type, not);
		this.push(db);
		}
	}
	
	
}
