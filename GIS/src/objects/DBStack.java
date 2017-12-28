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
	public void andFilter()
	{
		
	}
	
}
