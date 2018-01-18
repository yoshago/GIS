/**
 * 
 */
package main.java.objects;

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
	
	public DB peek()
	{
		return super.peek();
	}
	
	public void clear()
	{
		while(Size()>1)
			this.pop();
	}
	
	public void clearAll()
	{
		super.clear();
		this.push(new DB());
	}
	
	public int Size()
	{
		return super.size();
	}
	
	public void filter(Filter f)
	{
		if(f.getType()==0)
			andFilter(f);
		if(f.getType()==1)
			orFilter(f);
	}
	
	public void orFilter(Filter f)
	{
		DB db=new DB(this.get(0));
		if(this.Size()==1)
		{
			this.push(db);
		}
		else
		{
			db.filter(f);
			for(int i=0;i<this.peek().getScansList().size();i++)
			{
					singleScan scan=this.peek().getScansList().get(i);
					if(!db.contains(scan))
					db.getScansList().add(scan);
			}
			this.push(db);
		}
	}
	
	public void andFilter(Filter f)
	{
			DB db=new DB(this.peek());
			db.filter(f);
			this.push(db);
	}
	
	public String toString()
	{
		String s="";
		for(int i=0;i<this.Size();i++)
		{
			s+=this.get(i).toString();
		}
		s+="\n";
		return s;
	}
	
}
