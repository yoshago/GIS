/**
 * 
 */
package objects;

import java.util.Stack;



/**
 * @author ישי
 *
 */
public class FilterStack extends Stack<Filter> {
	public FilterStack()
	{
	    	super();
	}
	
	
	
	public FilterStack(Filter fs)
	{
		super();
		super.push(fs);
	}
	
	
	public Filter push(Filter fs)
	{
		return super.push(fs);
	}
	
	public Filter pop()
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


}
