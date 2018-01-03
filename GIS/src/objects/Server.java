/**
 * 
 */
package objects;

import java.io.*;
import java.util.ArrayList;

/**
 * @author ישי
 *
 */
public class Server implements java.io.Serializable{

	private DBStack dbs;
	private FilterStack fs;
	private ArrayList<File> combFilesList;
	private ArrayList<String> wigleFolderPath;
	private FilesUpdater fu;
	
	public Server()
	{
		this.dbs= new DBStack(new DB());
		this.fs=new FilterStack();
		this.combFilesList=new ArrayList<File>();
		this.wigleFolderPath=new ArrayList<String>();
		this.fu=new FilesUpdater(this);
	}

	public void addCombFile(File f)
	{
		this.combFilesList.add(f);
		DB tmp=new DB(f);
		this.addDB(tmp);
		this.fu.getCombLastModified().add(f.lastModified());
		this.fu.setSwitch_on(true);
		new Thread(fu).start();
	}
	
	public void addWigleFolder(String path)
	{
		this.wigleFolderPath.add(path);
		DB tmp=new DB(path,1);
		this.addDB(tmp);
		this.fu.setSwitch_on(true);
		new Thread(fu).start();
		
	}
	public void addDB(DB db)
	{
		{
			DBStack another=new DBStack(db);
			for(int i=0;i<this.fs.size();i++)
			{
				another.filter(fs.get(i));
			}
			for(int i=0;i<this.dbs.Size();i++)
			{
				for(int j=0;j<another.get(i).getScansList().size();j++)
				{
					singleScan scan=another.get(i).getScansList().get(j);
					if(!this.dbs.get(i).contains(scan))
						this.dbs.get(i).getScansList().add(scan);
				}
			}
		}
	}

	public void filter(String []arr)
	{
		Filter f=new Filter(arr);
		fs.add(f);
		dbs.filter(f);
	}
	
	public void saveDB(String path)
	{
		String filename=path+".ser";
		try
		{  
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(dbs.get(0));
			out.close();
			file.close();
		}
		catch(IOException ex)
		{
			System.out.println("IOException serialize did not succeed");
		}
	}

	public void openDB(String path)
	{
		try
		{   
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(file);
			this.addDB((DB)in.readObject()); 
			in.close();
			file.close();
		}

		catch(IOException ex)
		{
			System.out.println("IOException deserialize did not succeed");
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("Class Not Found Exception deserialize did not succeed");
		}
	}
	
	public void saveFilters(String path)
	{
		String filename=path+".ser";
		try
		{  
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(fs);
			out.close();
			file.close();
		}

		catch(IOException ex)
		{
			System.out.println("IOException serialize did not succeed");
		}
	}
	public void openFilter(String path)
	{
		try
		{   
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(file);
			this.fs = (FilterStack)in.readObject(); 
			in.close();
			file.close();
		}

		catch(IOException ex)
		{
			System.out.println("IOException deserialize did not succeed");
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("Class Not Found Exception deserialize did not succeed");
		}
	}
	
	public void undo()
	{
		if(dbs.Size()==1||fs.Size()==0)
			return;
		fs.pop();
		dbs.pop();
	}
	
	public void clearFilters()
	{
		this.dbs.clear();
		this.fs.clearAll();
	}
	
	public void clearAll()
	{
		this.dbs.clearAll();
		this.fs.clearAll();
	}
	
	public String DBStackString()
	{
		return dbs.toString();
	}
	
	public String FilterStackString()
	{
		return this.fs.toString();
	}

	public DBStack getDbs() {
		return dbs;
	}

	public FilterStack getFs() {
		return fs;
	}

	public ArrayList<File> getCombFilesList() {
		return combFilesList;
	}

	public ArrayList<String> getWigleFolderPath() {
		return wigleFolderPath;
	}
	
	

}
