package objects;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import Libraries.read;

public class FileUpdater implements Runnable{
	private Server s;
	private ArrayList<Long> fileLastModifiedList;
	private ArrayList<File> wigleFilesList;
	private boolean switch_on;
	private ArrayList<String> SQLLastModifiedList;
	
	public FileUpdater(Server s)
	{
		this.s=s;
		fileLastModifiedList=new ArrayList<Long>();
		wigleFilesList=new ArrayList<File>();
		this.switch_on=false;
		SQLLastModifiedList= new ArrayList<String>();
	}
	
	public void run()
	{
		while(this.switch_on)
		{
			treatUpdateFile();
			treatNewFile();
			treatDeleteFile();
			treatChengedSqlTable();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void treatChengedSqlTable() 
	{
		boolean changeFlag=false;
		for(int i=0;i<s.getSqlTablesList().size();i++)
		{
			SQLTable tmp=s.getSqlTablesList().get(i);
			String newTime=tmp.getLastModified();
			String lastModified=this.SQLLastModifiedList.get(i);
			if(!newTime.equals(lastModified))
			{
				this.SQLLastModifiedList.set(i, newTime);
				changeFlag=true;
			}
		}
		if(changeFlag)
		{
			reloadDB();
			s.getWindow().updateDataSheet();
		}
	}

	private void treatDeleteFile() 
	{
		boolean deleteFlag=false;
		for(int i=0;i<this.wigleFilesList.size();i++)
		{
			if(!Files.exists(this.wigleFilesList.get(i).toPath()))
			{
				this.wigleFilesList.remove(i);
				deleteFlag=true;
			}
		}
		for(int i=0;i<this.s.getCombFilesList().size();i++)
		{
			if(!Files.exists(this.s.getCombFilesList().get(i).toPath()))
			{
				this.s.getCombFilesList().remove(i);
				this.fileLastModifiedList.remove(i);
				deleteFlag=true;
			}
		}
		if(deleteFlag)
			reloadDB();
			s.getWindow().updateDataSheet();
	}

	private void treatNewFile() 
	{
		boolean changeFlag=false;
		for(int i=0;i<this.s.getWigleFolderPath().size();i++)
		{
			File tmpDir=new File(s.getWigleFolderPath().get(i));
			File[] fileList=tmpDir.listFiles();
			for(int j=0;j<fileList.length;j++)
			{
				if(!this.wigleFilesList.contains(fileList[j]) && read.isWigleFile(fileList[j]))
				{
					this.wigleFilesList.add(fileList[j]);
					DB db=new DB(fileList[j]);
					s.addDB(db);
					changeFlag=true;
				}
			}
		}
		if(changeFlag)
			s.getWindow().updateDataSheet();
	}

	private void treatUpdateFile() 
	{
		boolean changeFlag=false;
		for(int i=0;i<this.s.getCombFilesList().size();i++)
		{
			Long newTime=s.getCombFilesList().get(i).lastModified();
			if(newTime>this.fileLastModifiedList.get(i))
			{
				this.fileLastModifiedList.set(i, newTime);
				changeFlag=true;
			}
		}
		if(changeFlag)
		{
			reloadDB();
			s.getWindow().updateDataSheet();
		}
	}

	private void reloadDB() 
	{
		this.s.getDbs().clearAll();
		for(int i=0;i<this.s.getWigleFolderPath().size();i++)
		{
			DB tmp=new DB(this.s.getWigleFolderPath().get(i),1);
			s.addDB(tmp);
		}
		for(int i=0;i<this.s.getCombFilesList().size();i++)
		{
			DB tmp=new DB(this.s.getCombFilesList().get(i));
			s.addDB(tmp);
		}
		for(int i=0;i<this.s.getSqlTablesList().size();i++)
		{
			DB tmp=this.s.getSqlTablesList().get(i).readTable();
			s.addDB(tmp);
		}
	}

	public ArrayList<Long> getFileLastModifiedList() {
		return fileLastModifiedList;
	}

	public ArrayList<File> getWigleFilesList() {
		return wigleFilesList;
	}

	public void setSwitch_on(boolean switch_On) {
		this.switch_on = switch_On;
	}

	public ArrayList<String> getSQLLastModifiedList() {
		return SQLLastModifiedList;
	}
	
}
