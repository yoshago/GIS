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
	private ArrayList<Long> combLastModified;
	private ArrayList<File> combFileList;
	private ArrayList<String> wigleFolderPath;
	private ArrayList<String> wigleFilePathList;
	
	public FilesUpdater(Server s)
	{
		this.s=s;
		this.switch_on=false;
		this.combFileList=s.getCombFilesList();
		this.wigleFolderPath=s.getWigleFolderPath(); 
		this.combLastModified=new ArrayList<Long>();
		for(int i=0;i<this.combFileList.size();i++)
		{
			combLastModified.add(this.combFileList.get(i).lastModified());
		}
		for(int i=0;i<this.wigleFolderPath.size();i++)
		{
			File[]tmp=new File(this.wigleFolderPath.get(i)).listFiles();
			for(int j=0;j<tmp.length;j++)
			{
				wigleFilePathList.add(tmp[j].getPath());
			}
		}
	}
	private void treatNewFile()
	{
		for(int i=0;i<this.wigleFolderPath.size();i++)
		{
			File tmp=new File(this.wigleFolderPath.get(i));
			File[] tmpArr=tmp.listFiles();
			DB db;
			for(int j=0;j<tmpArr.length;j++)
			{
				if(!wigleFilePathList.contains(tmpArr[j].getPath()))
				{
					this.wigleFilePathList.add(tmpArr[j].getPath());
					db=new DB(tmpArr[j]);
					s.addDB(db);
				}
			}
		}
	}
	
	private void treatUpdateFile()
	{
		boolean flag=false;
		for(int i=0;i<this.combFileList.size();i++)
		{
			if(this.combFileList.get(i).lastModified()!=this.combLastModified.get(i))
			{
				combLastModified.set(i, combFileList.get(i).lastModified());
				flag=true;
			}
		}
		if(flag)
			reloadDBS();
	}
	
	private void reloadDBS() 
	{
		this.s.getDbs().clearAll();
		for(int i=0;i<this.wigleFolderPath.size();i++)
		{
			DB tmp=new DB(this.wigleFolderPath.get(i),1);
			s.addDB(tmp);
		}
		for(int i=0;i<this.combFileList.size();i++)
		{
			DB tmp=new DB(this.combFileList.get(i));
			s.addDB(tmp);
		}
		
	}
	
	public void run()
	{
		while(switch_on)
		{
			System.out.println("test");
			treatNewFile();
			treatUpdateFile();
			try {
				Thread.sleep(3000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void setSwitch_on(boolean switch_on) {
		this.switch_on = switch_on;
	}
	public ArrayList<Long> getCombLastModified() {
		return combLastModified;
	}
	

}
