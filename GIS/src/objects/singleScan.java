package objects;
import java.util.ArrayList;
/**
 * this class used by classes: linesCollector & csvFilter
 *  this class  collect object of wifi
 */
/**
 * @author Yehonatan&Yishay
 * @description this object represent one scan of wigle-wifi application, and contains:
 *              1. list of wifiSpot objects.
 *              2. time of scan.
 *              3. id of the scanner smartphone.
 *              4. location of scan.
 *              5. size of wifiSpot list(maximum 10)
 */
public class singleScan {

	private String time;
	private String id;
	private coordinate coordinate;
	private ArrayList<wifiSpot> wifiSpotsList = new ArrayList<wifiSpot>();
	private int size=wifiSpotsList.size();
	/**
	 * @description empty constructor.
	 */
	public singleScan()
	{

	}
	/**
	 * @param time-time of scan.
	 * @param id-id of the scanner smartphone.
	 * @param coordinate-location of scan.
	 */
	public singleScan(String time, String id , coordinate coordinate)
	{
		this.time=time;
		this.id=id;
		this.coordinate=coordinate;
	}
	public singleScan(String time, String id , coordinate coordinate, ArrayList<wifiSpot> wifiSpotList)
	{
		this.time=time;
		this.id=id;
		this.coordinate=coordinate;
		this.wifiSpotsList = wifiSpotList;

	}

	/**
	 * @description this function add wifiSpot to wifiSpotsList
	 * @param w-wifiSpot object
	 * 
	 */
	public void add (wifiSpot w)
	{ 
		int i=0;
		if(wifiSpotsList.isEmpty())  wifiSpotsList.add(w);
		else
		{
			while(i<wifiSpotsList.size() && w.compareTo(this.wifiSpotsList.get(i))<0){
				i++;
				if(i>10) return;
			}
			wifiSpotsList.add(i, w);
		}
		if(size<10)//the maximum size is 10
			size++;
		if(size==10)
			removeWorstSignal();
	}
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @description
	 * this function called by add function. 
	 * used to get sure that that only 10 wifi objects stay in wifilist(the most strong signals)
	 */
	private void removeWorstSignal() {
		if(this.wifiSpotsList.size()>10) this.wifiSpotsList.remove(10);

	}
	public String toString()
	{
		String s=time+","+id+","+coordinate+","+size+",";
		for(int i=0;i<wifiSpotsList.size();i++)
		{
			s+=wifiSpotsList.get(i).toString();
		}
		s+="\n";
		return s;	
	}

	public int contains(wifiSpot another)
	{
		for(int i=0;i<this.getSize();i++)
		{
			if(this.wifiSpotsList.get(i).getMac().equals(another.getMac())) 
				return i;
		}
		return -1;
	}

	public String getTime() {
		return time;
	}
	public String getId() {
		return id;
	}
	public void setWifiSpotsList(ArrayList<wifiSpot> wifiSpotsList) {
		this.wifiSpotsList = wifiSpotsList;
	}
	public coordinate getCoordinate() {
		return coordinate;
	}
	public ArrayList<wifiSpot> getWifiSpotsList() {
		return wifiSpotsList;
	}
	public int getSize() {
		return size;
	}
	public void setCoordinate(coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public boolean equals(singleScan another)
	{
		if(!(this.time.equals(another.time) && this.coordinate.compare(another.coordinate)==0 && this.id.equals(another.id) && this.getSize()==another.getSize()))
			return false;
		return true;
	}
}
