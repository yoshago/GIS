/**
 * 
 */
package objects;

/**
 * @author ישי
 *
 */
public class Server {

	DBStack dbs;
	FilterStack fs;
	
	public Server()
	{
		this.dbs= new DBStack();
		this.fs=new FilterStack();
	}
	
	public void addDB(DB db)
	{
		
//		this.dbs.get(0).;
	}
	
	public void filter(String []arr)
	{
		Filter f=new Filter(arr);
		if(arr[0]=="0")
			dbs.andFilter(f);
		if(arr[0]=="1")
			dbs.orFilter(f);
	}
}
