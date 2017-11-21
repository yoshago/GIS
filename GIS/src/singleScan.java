import java.util.ArrayList;
/**
 * this class used by classes: linesCollector & csvFilter
 *  this class  collect object of wifi
 */
public class singleScan {
    private String time;
    private String id;
    private coordinate coordinate;
    private int size=0;
    private ArrayList<wifiSpot> wifiSpotsList = new ArrayList<wifiSpot>();
    public singleScan()
    {
    	
    }
   public singleScan(String time, String id , coordinate coordinate)
   {
	    this.time=time;
	    this.id=id;
	   this.coordinate=coordinate;
   }
  
   /**
    * 
    * @param w(wifi object)
    * add & sort wifi object into the list of wifi networks
    * 
    */
    public void add (wifiSpot w)
    { 
    	int i=0;
    	if(wifiSpotsList.isEmpty())  wifiSpotsList.add(w);
    	else
    	{
    	while(i<wifiSpotsList.size() && w.compareBySignal(this.wifiSpotsList.get(i))<0){
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
    /**
     * this function called by add function. 
     * used to get sure that that only 10 wifi objects stay in wifilist(the most strong signals)
     */
    private void removeWorstSignal() {
		if(this.wifiSpotsList.size()>10) this.wifiSpotsList.remove(10);
		
	}
	public String toString()
    {
    	String s=this.time+","+this.id+","+this.coordinate.toString()+","+this.size+",";
    	for(int i=0;i<wifiSpotsList.size();i++)
    	{
    		s+=wifiSpotsList.get(i).toString();
    	}
    	return s;	
    }
   
	public String getTime() {
		return time;
	}
	public String getId() {
		return id;
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
	
}
