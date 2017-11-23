import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Yehonatan&Yishay
 * @description A library class. contains functions to filter ArrayList of singleScan objects.
 *
 */
public class Filter {
	
	/**
	 * @param singleScanList
	 * this function calls the other filter functions according to the user request, (Kind of a UI for filtering).
	 * 
	 */
	public static void mainFilter(ArrayList<singleScan> singleScanList) 
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
					filterByTime(singleScanList);
				if(i==2)
					filterByID(singleScanList);
				if(i==3)
					filterByLocation(singleScanList);	
			}
		}	
//		choose.close();
	}


	/**
	 * @param singleScanList
	 * this functions filter the singleScans by its time using start time and end time that the functions gets from the user.
	 */
	public static void filterByTime(ArrayList<singleScan> singleScanList)
	{
		System.out.println("please enter start time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputStartTime=new Scanner(System.in);
		String start=inputStartTime.nextLine();
		System.out.println("please enter end time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputEndTime=new Scanner(System.in);
		String end=inputEndTime.nextLine();

		for(int i=0;i<singleScanList.size();i++)
		{
			if(singleScanList.get(i).getTime().compareTo(start)<0 || singleScanList.get(i).getTime().compareTo(end)>0)
			{
				singleScanList.remove(i);
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
	public static void filterByLocation(ArrayList<singleScan> singleScanList)
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
		
		for(int i=0;i<singleScanList.size();i++)
		{
			coordinate singleScanCoor=singleScanList.get(i).getCoordinate();//remember to check with String 

			if(!(singleScanCoor.compare(min)>=0 && (singleScanCoor.compare(max)<=0 && singleScanCoor.compare(max)!=-2)))
			{
				singleScanList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * @param singleScanList
	 * filter the singleScans by id. remove all the singleScans with different id.
	 * 
	 */
	public static void filterByID(ArrayList<singleScan> singleScanList)
	{
		System.out.println("please enter id: ");
		Scanner inputID=new Scanner(System.in);
		String ID=inputID.nextLine();

		for(int i=0;i<singleScanList.size();i++)
		{
			if(!ID.equals(singleScanList.get(i).getId()))
			{
				singleScanList.remove(i);
				i--;
			}
		}
	}

}
