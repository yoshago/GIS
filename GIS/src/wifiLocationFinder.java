import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class wifiLocationFinder {
	private DB dataBase;
	private ArrayList<wifiSpot> finalWifiList;
	
	public wifiLocationFinder(DB dataBase)
	{
		this.dataBase=dataBase;
		
	}
	
	private Map<String, ArrayList<wifiSpot>> macMap()
	{
		Map<String, ArrayList<wifiSpot>> macToWifiSpotMap = new HashMap<>();
		this.dataBase.getScansList().forEach(singleScan -> singleScan.getWifiSpotsList().forEach(wifiSpot -> {
			if (macToWifiSpotMap.containsKey(wifiSpot.getMac())) 
			{
				macToWifiSpotMap.get(wifiSpot.getMac()).add(wifiSpot);                                                      //if (wifiSpot.compareBySignal(macToWifiSpotMap.get(wifiSpot.getMac()))==1) {macToWifiSpotMap.put(wifiSpot.getMac(), wifiSpot);}
			} 
			else 
			{
				macToWifiSpotMap.put(wifiSpot.getMac(),new ArrayList<wifiSpot>());
				macToWifiSpotMap.get(wifiSpot.getMac()).add(wifiSpot);
			}
		}));
		return macToWifiSpotMap;
	}          
	
	public void findSpotsLocation()
	{
		ArrayList<wifiSpot> tmpwifiList=new ArrayList<wifiSpot>();
		Map<String, ArrayList<wifiSpot>> macToWifiSpotMap = macMap();
		macToWifiSpotMap.forEach((key,wifiList)->this.finalWifiList.add(wifiSpotLocation(wifiList)));
	}
	/**
	 *
	 * @param wifiList
	 * @return wifiSspot
	 * @description return wifi with Weighted average of location
	 */
	private wifiSpot wifiSpotLocation(ArrayList<wifiSpot> wifiList)
	{
		double moneLon=0;
		double moneLat=0;
		double moneAlt=0;
		double mech=0;
		
		for(int i=0;i<wifiList.size();i++)
		{
			double weight=1/Math.pow(wifiList.get(i).getSignal(),2);
			moneLon+=wifiList.get(i).getCoordinate().getLon()*weight;
			moneLat+=wifiList.get(i).getCoordinate().getLat()*weight;
			moneAlt+=wifiList.get(i).getCoordinate().getAlt()*weight;
			mech+=weight;
		}
		coordinate coor=new coordinate(moneLon/mech,moneLat/mech,moneAlt/mech);
		wifiSpot ws=new wifiSpot(wifiList.get(0).getMac(), wifiList.get(0).getSsid(), wifiList.get(0).getChannel(), "-20", coor);
		return ws; 
	}
}
