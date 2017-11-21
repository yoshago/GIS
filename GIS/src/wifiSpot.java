

/**
 * wifi objects are build in read class
 * and uses at line class
 * each wifi contains: mac address, name of spot, signal volume, channel.
 * this class contains functions: compare, toString.  
 */
public class wifiSpot {
	

	private String ssid;
	private String mac;
	private String signal;
	private String channel;
	private coordinate coordinate;
	/**
	 * Constructor: get(name of spot,mac address,signal volume,channel
	 * 
	 */
	public wifiSpot(String mac, String ssid, String channel, String signal, coordinate coordinate)
	{
		this.ssid=ssid;
		this.mac=mac;
		this.signal=signal;
		this.channel=channel;
		this.coordinate=coordinate;
	}
	/**
	 * toString: return string of wifi in format "mac,name,signal volume,channel".
	 */
	public String toString()
	{
		return this.mac+","+this.ssid+","+this.channel+","+this.signal+",";
		
	}
	/**
	 * 
	 * compare: compare two wifi objects by signal volume. return int.
	 * 
	 */
	public int compareBySignal(wifiSpot w)
	{
		if(Integer.parseInt(this.signal)>Integer.parseInt(w.signal))
			return 1;
		else if(Integer.parseInt(this.signal)<Integer.parseInt(w.signal))
				return -1;
		else return 0;
	}
	
	public String getSsid() {
		return ssid;
	}

	public String getMac() {
		return mac;
	}

	public String getSignal() {
		return signal;
	}

	public String getChannel() {
		return channel;
	}
	public coordinate getCoordinate() {
		return coordinate;
	}
	
}
