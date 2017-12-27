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
					filterByTime(scansList);
				if(i==2)
					filterByID(scansList);
				if(i==3)
					filterByLocation(scansList);	
			}
		}	
	}
	/**
	 * @param singleScanList
	 * this functions filter the singleScans by its time using start time and end time that the functions gets from the user.
	 */
	public static void filterByTime(ArrayList<singleScan> scansList)
	{
		System.out.println("please enter start time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputStartTime=new Scanner(System.in);
		String start=inputStartTime.nextLine();
		System.out.println("please enter end time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputEndTime=new Scanner(System.in);
		String end=inputEndTime.nextLine();

		for(int i=0;i<scansList.size();i++)
		{
			if(scansList.get(i).getTime().compareTo(start)<0 || scansList.get(i).getTime().compareTo(end)>0)
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
	public static void filterByLocation(ArrayList<singleScan> scansList)
	{

		System.out.println("Please enter the coordinate of the down-left corner of the rectangle area to filter (in format lon,lat)");
		Scanner inputlocation=new Scanner(System.in);
		String start=inputlocation.nextLine();
		String minLon = start.split(",")[0];
		String minLat = start.split(",")[1];
		coordinate min=new coordinate(minLon,minLat);
		
		System.out.println("Please enter the coordinate of the up-right corner of the rectangle area to filter (in format lon,lat)");
		String end=inputlocation.nextLine();
		String maxLon = end.split(",")[0];
		String maxLat = end.split(",")[1];
		coordinate max=new coordinate(maxLon,maxLat);
		
		for(int i=0;i<scansList.size();i++)
		{
			coordinate singleScanCoor=scansList.get(i).getCoordinate();//remember to check with String 

			if(!(singleScanCoor.compare(min)>=0 && (singleScanCoor.compare(max)<=0 && singleScanCoor.compare(max)!=-2)))
			{
				scansList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * @param singleScanList
	 * filter the singleScans by id. remove all the singleScans with different id.
	 * 
	 */
	public static void filterByID(ArrayList<singleScan> scansList)
	{
		System.out.println("please enter id: ");
		Scanner inputID=new Scanner(System.in);
		String ID=inputID.nextLine();

		for(int i=0;i<scansList.size();i++)
		{
			if(!ID.equals(scansList.get(i).getId()))
			{
				scansList.remove(i);
				i--;
			}
		}
	}



	

}
