
/**
 * @author Yehonatan&Yishay
 * @description an object class of the type coordinate that represents location in the world
 * 				using logtitude, latitude and altitude (the format of the coordinateis in geographic .
 *
 */
public class coordinate {
	 private double lon;
	 private double lat;
     private double alt;
      
       
       /**
     *  empty constructor
     */ 
    public coordinate()
       {
    	   this.lon=0;
    	   this.lat=0;
    	   this.alt=0;
       }
       /**
     * @param lon - represents the longitude of the coordinate
     * @param lat - represents the latitude of the coordinate
     * @param alt - represents the altitude of the coordinate
     * 
     * this is the constructor of the object
     */
    public coordinate(String lon, String lat ,String alt)
       {
    	   this.lon=Double.parseDouble(lon);
    	   this.lat=Double.parseDouble(lat);
    	   this.alt=Double.parseDouble(alt);
       }
       
       /**
     * @param lon represents the longitude of the coordinate
     * @param lat represents the latitude of the coordinate
     * 
     * this constructor gets only lon and lat and set the alt to 0
     */
    public coordinate(String lon ,String lat)
       {
    	   this.lon=Double.parseDouble(lon);
    	   this.lat=Double.parseDouble(lat);
    	   this.alt=0;
       }
       
       /**
     * @param another - another coordinate to compare with
     * @return 0 if the compared coordinate is equal to this coordinate
     * 		   1 if the compared coordinate is smaller in both longitude and latitude
     * 		  -1 if the compared coordinate is bigger in both longitude and latitude
     * 		  -2 otherwise
     */
    public int compare(coordinate another)
       {
    	   if(another.lon<this.lon && another.lat<this.lat)
    		   return 1;
    	   if(another.lon>this.lon && another.lat>this.lat)
    		   return -1;
    	   if(another.lon==this.lon && another.lat==this.lat)
    		   return 0;
    	   return -2;
       }
       
       public String toString()
       {
    	   return lon + "," + lat + "," + alt;
       }
	/**
	 * @return longitude value of the coordinate
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @return latitude value of the coordinate
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @return altitude value of the coordinate
	 */
	public double getAlt() {
		return alt;
	}
       
}
