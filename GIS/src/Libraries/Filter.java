package Libraries;
import java.util.ArrayList;
import java.util.Scanner;

import objects.coordinate;
import objects.singleScan;


/**
 *
 * @author Yehonatan&Yishay
 * @description A library class. contains functions to filter ArrayList of singleScan objects.
 *
 */
public class Filter {
	public static void mainFilter(ArrayList<singleScan> scansList) 
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
				flag=false;
			}
			else
			{
				int i=Integer.parseInt(input);
				if(i==1)
					filterByTime(scansList,1,"","");
//				if(i==2)
//					filterByID(scansList,1);
//				if(i==3)
//					filterByLocation(scansList,1,"","");	
			}
		}	
	}
	/**
	 * @param singleScanList
	 * this functions filter the singleScans by its time using start time and end time that the functions gets from the user.
	 */
	public static void filterByTime(ArrayList<singleScan> scansList, int not,String startTime,String endTime)
	{
		for(int i=0;i<scansList.size();i++)
		{
			if(not==1 && (scansList.get(i).getTime().compareTo(startTime)<0 || scansList.get(i).getTime().compareTo(endTime)>0))
			{
				scansList.remove(i);
				i--;
			}
			if(not==0 && !(scansList.get(i).getTime().compareTo(startTime)<0 || scansList.get(i).getTime().compareTo(endTime)>0))
			{
				scansList.remove(i);
				i--;
			}
		}
	}

	/**
	 * @param singleScanList
	 * Uses to filter the singleScans by location.
	 * the function gets Minimum coordinate and Maximum coordinate and creates an imagined rectangle area and remove all the coordinates out of the border of the rectangle. 
	 * 
	 */
	public static void filterByLocation(ArrayList<singleScan> scansList, int not, String minLon, String minLat, String maxLon, String maxLat)
	{	
		coordinate min=new coordinate(minLon,minLat);
		coordinate max=new coordinate(maxLon,maxLat);
		
		for(int i=0;i<scansList.size();i++)
		{
			coordinate singleScanCoor=scansList.get(i).getCoordinate();

			if(not==1 && !(singleScanCoor.compare(min)>=0 && (singleScanCoor.compare(max)<=0 && singleScanCoor.compare(max)!=-2)))
			{
				scansList.remove(i);
				i--;
			}
			if(not==0 && (singleScanCoor.compare(min)>=0 && (singleScanCoor.compare(max)<=0 && singleScanCoor.compare(max)!=-2)))
			{
				scansList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * @param singleScanList
	 * filter the singleScans by id. remove all the singleScans with different id.
	 * if not=0 not filter, if not=1 regular filter
	 */
	public static void filterByID(ArrayList<singleScan> scansList, int not, String ID)
	{	
		for(int i=0;i<scansList.size();i++)
		{
			if( not==1 && !ID.equals(scansList.get(i).getId()))
			{
				scansList.remove(i);
				i--;
			}
			if(not==0 && ID.equals(scansList.get(i).getId()))
			{
				scansList.remove(i);
				i--;
			}
		}
	}



	

}
