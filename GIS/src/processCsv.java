import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * this class take the .csv file that class scanFolder export and filter him by user preferences
 * this class uses class csvToKml and used by it 
 * 
 */


public class processCsv {
	private File f;
	private ArrayList<singleScan> singleScanList;
	private String[] MinCoordinate;
	private String[] MaxCoordinate;

	public processCsv(String path)
	{
		this.f=new File(path);
		this.singleScanList=read.readOutputFolderFile(f);
		mainFilter();
	}

	
	private void mainFilter() 
	{
		boolean flag=true;
		Scanner choose=new Scanner(System.in);
		while(flag)
		{
			System.out.println("filter by:\n1.for time choose 1\n2.for id choose 2\n3.for location choose 3\n4.to not filter-press any key");
			String input=choose.nextLine();
			//		choose.close();

			if(!input.equals("1") && !input.equals("2") && !input.equals("3"))
			{
				System.out.println("you chose not to filter");
				toKML();
				toCSV();
				flag=false;
			}
			else
			{
				int i=Integer.parseInt(input);
				if(i==1)
					filterTime();
				if(i==2)
					filterID();
				if(i==3)
					filterLocation();	
			}
		}	
	}


	
	
	
	
	
	
	
	
	
	
	
//	public void filterTime()
//	{	
//		System.out.println("please enter start time: (in format YYYY-MM-DD hh:mm:ss)");
//		Scanner inputStartTime=new Scanner(System.in);
//		String start=inputStartTime.nextLine();
//		System.out.println("please enter end time: (in format YYYY-MM-DD hh:mm:ss)");
//		Scanner inputEndTime=new Scanner(System.in);
//		String end=inputEndTime.nextLine();
//
//		for(int i=1;i<singleScanList.size();i++)
//		{
//			if(singleScanList.get(i).getTime().compareTo(start)<0 || singleScanList.get(i).getTime().compareTo(end)>0)
//			{
//				singleScanList.remove(i);
//				i--;
//			}
//		}
//
//	}
//	public void filterLocation()
//	{
//		System.out.println("Please enter the coordinate of the down-left corner of the rectangle area to filter (in format lon,lat)");
//		Scanner inputlocation=new Scanner(System.in);
//		String start=inputlocation.nextLine();
//		this.MinCoordinate = start.split(",");
//		Double minLon = Double.parseDouble(start.split(",")[0]);
//		Double minLat = Double.parseDouble(start.split(",")[1]);
//		System.out.println("Please enter the coordinate of the up-right corner of the rectangle area to filter (in format lon,lat)");
//		String end=inputlocation.nextLine();
//		this.MaxCoordinate = end.split(",");
//		Double maxLon = Double.parseDouble(end.split(",")[0]);
//		Double maxLat = Double.parseDouble(end.split(",")[1]);
//		for(int i=1;i<singleScanList.size();i++)
//		{
//			double myLon=Double.parseDouble(singleScanList.get(i).getLon());//remember to check with String 
//			double myLat=Double.parseDouble(singleScanList.get(i).getLat());
//			if(myLon<minLon || myLon>maxLon || myLat<minLat || myLat>maxLat)
//			{
//				singleScanList.remove(i);
//				i--;
//			}
//		}
//
//	}
//
//	public void filterID()
//	{
//		System.out.println("please enter id: ");
//		Scanner inputID=new Scanner(System.in);
//		String ID=inputID.nextLine();
////		inputID.close();
//		for(int i=1;i<singleScanList.size();i++)
//		{
//			if(!ID.equals(singleScanList.get(i).getId()))
//			{
//				singleScanList.remove(i);
//				i--;
//			}
//		}
//
//	}
//
//	public String toString()
//	{
//		String s="";
//		for(int i=0;i<singleScanList.size();i++)
//		{
//			s+=singleScanList.get(i).toString1()+"\n";
//		}
//		return s;
//	}
//	public void toCSV()
//	{
//		try
//		{
//			PrintWriter pw = new PrintWriter(new File("C:\\Users\\ישי\\Desktop\\summer_proj\\oop\\checkfolder\\check1.csv"));
//			pw.write(toString());	
//			pw.close();
//		}
//		catch(IOException ex)
//		{
//			System.out.println("error writing file "+ex);
//			System.exit(2);
//		}
//	}
//	private void uiFilter()
//	{
//		boolean flag=true;
//		Scanner choose=new Scanner(System.in);
//		while(flag)
//		{
//			
//			System.out.println("filter by:\n1.for time choose 1\n2.for id choose 2\n3.for location choose 3\n4.to not filter-press any key");
//			String input=choose.nextLine();
//			//		choose.close();
//
//			if(!input.equals("1") && !input.equals("2") && !input.equals("3"))
//			{
//				System.out.println("you chose not to filter");
//				toKML();
//				toCSV();
//				flag=false;
//				
//			}
//			else{
//				int i=Integer.parseInt(input);
//				if(i==1)
//					filterTime();
//				if(i==2)
//					filterID();
//				if(i==3)
//					filterLocation();
//				
//			}
//		}
//	}
//	private void toKML()
//	{
//		csvToKml csvk= new csvToKml(this);
//		//		this.toCSV();
//		csvk.ScanToKml();
//		csvk.exportToKml();
//	}
//
//	public String[] getMinCoordinate(){
//		return MinCoordinate;
//	}
//
//	public String[] getMaxCoordinate(){
//		return MaxCoordinate;
//	}
//
//	public File getF() {
//		return f;
//	}
//
//
//	public ArrayList<singleScan> getSingleScanList() {
//		return singleScanList;
//	}
//
//
//	
//
//
//
//	//	 34.986,32.135
//	//	 
//	//	 
//	//	 34.992,32.141
}
