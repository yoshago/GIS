package main.java.objects;

import java.io.Serializable;
import java.util.Stack;

public class SqlTableStack extends Stack<SQLTable> implements Serializable{
	public SqlTableStack()
	{
	    	super();
	}
	
	
	
	public SqlTableStack(SQLTable fs)
	{
		super();
		super.push(fs);
	}
	
	
	public SQLTable push(SQLTable fs)
	{
		return super.push(fs);
	}
	
	public SQLTable pop()
	{
		return super.pop();
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
