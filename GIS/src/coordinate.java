/**
 * 
 */

/**
 * @author ישי
 *
 */
public class coordinate {
	 private double lon;
	 private double lat;
     private double alt;
      
       
       public coordinate()
       {
    	   this.lon=0;
    	   this.lat=0;
    	   this.alt=0;
       }
       public coordinate(String lon, String lat ,String alt)
       {
    	   this.lon=Double.parseDouble(lon);
    	   this.lat=Double.parseDouble(lat);
    	   this.alt=Double.parseDouble(alt);
       }
       
       public coordinate(String lon ,String lat)
       {
    	   this.lon=Double.parseDouble(lon);
    	   this.lat=Double.parseDouble(lat);
    	   this.alt=0;
       }
       
       public int compare(coordinate another)
       {
    	   if(another.lon<=this.lon && another.lat<=this.lat)
    		   return 1;
    	   if(another.lon>=this.lon && another.lat>=this.lat)
    		   return -1;
    	   return 0;
       }
       
       public String toString()
       {
    	   return lon + "," + lat + "," + alt;
       }
	public double getLon() {
		return lon;
	}
	public double getLat() {
		return lat;
	}
	public double getAlt() {
		return alt;
	}
       
}
