import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DB {
	private ArrayList<singleScan> scansList = new ArrayList<singleScan>();
	
	public DB(ArrayList<singleScan> scansList){
		this.scansList = scansList;
	}
	
	private void removeDuplicateMac(){
 		Map<String, wifiSpot> macToWifiSpotMap = new HashMap<>();
  		this.scansList.forEach(singleScan -> singleScan.getWifiSpotsList().forEach(wifiSpot -> {
 			if (macToWifiSpotMap.containsKey(wifiSpot.getMac())) {
 				if (wifiSpot.compareTo(macToWifiSpotMap.get(wifiSpot.getMac()))==1) {
 					macToWifiSpotMap.put(wifiSpot.getMac(), wifiSpot);
 				}
 			}
 			else {
 				macToWifiSpotMap.put(wifiSpot.getMac(), wifiSpot);

  			}
  		}));
  		this.scansList.forEach(singleScan -> singleScan.getWifiSpotsList()
 				.removeIf(wifiSpot -> !wifiSpot.equals(macToWifiSpotMap.get(wifiSpot.getMac()))));
 	} 
	
	

	/**
	 * @param singleScanList
	 * this function calls the other filter functions according to the user request, (Kind of a UI for filtering).
	 * 
	 */
	
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
				flag=false;
			}
			else
			{
				int i=Integer.parseInt(input);
				if(i==1)
					filterByTime();
				if(i==2)
					filterByID();
				if(i==3)
					filterByLocation();	
			}
		}	
	}
	/**
	 * @param singleScanList
	 * this functions filter the singleScans by its time using start time and end time that the functions gets from the user.
	 */
	private void filterByTime()
	{
		System.out.println("please enter start time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputStartTime=new Scanner(System.in);
		String start=inputStartTime.nextLine();
		System.out.println("please enter end time: (in format YYYY-MM-DD hh:mm:ss)");
		Scanner inputEndTime=new Scanner(System.in);
		String end=inputEndTime.nextLine();

		for(int i=0;i<this.scansList.size();i++)
		{
			if(this.scansList.get(i).getTime().compareTo(start)<0 || this.scansList.get(i).getTime().compareTo(end)>0)
			{
				this.scansList.remove(i);
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
	private void filterByLocation()
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
		
		for(int i=0;i<this.scansList.size();i++)
		{
			coordinate singleScanCoor=this.scansList.get(i).getCoordinate();//remember to check with String 

			if(!(singleScanCoor.compare(min)>=0 && (singleScanCoor.compare(max)<=0 && singleScanCoor.compare(max)!=-2)))
			{
				this.scansList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * @param singleScanList
	 * filter the singleScans by id. remove all the singleScans with different id.
	 * 
	 */
	public void filterByID()
	{
		System.out.println("please enter id: ");
		Scanner inputID=new Scanner(System.in);
		String ID=inputID.nextLine();

		for(int i=0;i<this.scansList.size();i++)
		{
			if(!ID.equals(this.scansList.get(i).getId()))
			{
				this.scansList.remove(i);
				i--;
			}
		}
	}
}
