/**
 * 
 */
package objects;

import java.io.File;
import java.util.ArrayList;

import com.sun.jmx.snmp.tasks.ThreadService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * @author ישי
 *
 */
public class FilesUpdater implements Runnable{
	private Server s;
	private boolean switch_on;
	private ArrayList<Long> lastModified;
	private ArrayList<File> fileList;
	private ArrayList<String> pathList;
	public FilesUpdater(Server s)
	{
		this.s=s;
		this.switch_on=false;
		this.fileList=s.getFilesList();
		for(int i=0;i<this.fileList.size();i++)
		{
			lastModified.add(this.fileList.get(i).lastModified());
		}
	}
	private void treatNewFile()
	{
		for(int i=0;i<pathList.size();i++)
		{
			File tmp=new File(this.pathList.get(i));
			File[] tmparr=tmp.listFiles();
			DB db;
			for(int j=0;j<tmparr.length;j++)
			{
				if(!fileList.contains(tmparr[j]))
				{
					this.fileList.add(tmparr[j]);
					this.pathList.add(tmparr[j].getPath());
					db=new DB(tmparr[j]);
					s.addDB(db);
				}
			}
		}
	}
	
	private void treatUpdateFile()
	{
		for(int i=0;i<this.fileList.size();i++)
		{
			if(this.fileList.get(i).lastModified()!=this.lastModified.get(i))
			{
				
			}
		}
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(switch_on)
		{
			treatNewFile();
			treatUpdateFile();
			try {
				Thread.sleep(5000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	

}
