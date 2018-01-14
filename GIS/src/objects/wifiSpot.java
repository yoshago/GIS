package objects;

/**
 * @author Yehonatan&Yishay
 * @description wifiSpot objects are build in read class
 *              and uses at singleScan class
 *              each wifiSpot contains: mac address, name of spot, signal volume, channel and coordinate.
 *              this class contains functions: compare, toString.  
 */

public class wifiSpot implements Comparable<wifiSpot> {
	

	private String ssid;
	private String mac;
	private int signal;
	private String channel;
	private coordinate coordinate;
	/**
	 * Constructor: get(name of spot,mac address,signal volume,channel
	 * 
	 */
	/**
	 * @param mac-mac address
	 * @param ssid-name of spot
	 * @param channel-frequency
	 * @param signal-signal volume
	 * @param coordinate-the location that this spot scanned
	 */
	public wifiSpot(String mac, String ssid, String channel, String signal, coordinate coordinate)
	{
		this.ssid=ssid;
		this.mac=mac;
		this.signal=Integer.parseInt(signal);
		this.channel=channel;
		this.coordinate=coordinate;
	}
	
	public wifiSpot(String mac,int signal,coordinate coordinate){
		this.mac=mac;
		this.signal=(signal);
		this.coordinate=coordinate;
	}
	
	public wifiSpot(String mac, String ssid, String channel, String signal)
	{
		this.ssid=ssid;
		this.mac=mac;
		this.signal=Integer.parseInt(signal);
		this.channel=channel;
	}
	public wifiSpot(String mac, String signal) {
		this.mac=mac;
		this.signal=Integer.parseInt(signal);
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
	/**
	 * @param another- another wifiSpot object
	 * @return 1 if this spot's volume signal higher than another, 0 if volume signal equal in both spots, -1 if this spot's volume signal lower than anothar. 
	 */
	
	
	public String getSsid() {
		return ssid;
	}

	public String getMac() {
		return mac;
	}

	public int getSignal() {
		return signal;
	}

	public String getChannel() {
		return channel;
	}
	public coordinate getCoordinate() {
		return coordinate;
	}
	
	@Override
	public int compareTo(wifiSpot wifi) {
		return Integer.compare(this.signal, wifi.getSignal());
	}
	
}
