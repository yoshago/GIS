package objects;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Libraries.read;
import Libraries.write;

/**
 * @author Yehonatan&Yishay
 *
 */
public class DB {
	private ArrayList<singleScan> scansList = new ArrayList<singleScan>();
	private int numOfWifiSpots;
	

	public ArrayList<singleScan> getScansList() 
	{
		return scansList;
	}

	public void add(ArrayList<singleScan> array) 
	{
		this.scansList.addAll(array);
	}
	/**
	 * @param scansList
	 * 
	 */

	public void setScansList(ArrayList<singleScan> scansList) 
	{
		this.scansList = scansList;
	}

	/**
	 * 
	 */
	public DB() 
	{
		scansList = new ArrayList<singleScan>();
	}
	
	public DB(File f)
	{
		read.readSingleFile(f, this.scansList);
	}

	/**
	 * @param scansList
	 * constructor of the DB from ArrayList of singleScan objects.
	 */
	public DB(ArrayList<singleScan> scansList){
		this.scansList = scansList;
	}
	public DB(DB db){
		this.scansList = new ArrayList<singleScan>(db.getScansList());
	}
	/**
	 * @param Path
	 * @param type
	 * constructor of DB from csv files
	 * there is two types of input files 1.wigle files, 2.output files.
	 */
	public DB(String Path, int type){
		File folder = new File(Path);
		File[] listOfFiles = folder.listFiles();
		if (type==1){
			this.scansList = read.readFolder(listOfFiles);
		}
		if (type==2){
			this.scansList = read.readOutputFolder(listOfFiles);
		}
	}
	
	/**
	 * the function leaves in the DB only one object of each wifi router (judging by the mac) the object that will remain is the one with the strongest signal.
	 * the functions uses a hashmap to link each mac address to one wifiSpot object. 
	 */
	public void removeDuplicateMac(){
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
	public int getMACAmount(){
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
  		return macToWifiSpotMap.size();
	}
	
	

	/**
	 * 
	 * this function calls the other filter functions according to the user request, (Kind of a UI for filtering).
	 * 
	 */
	
	public void filter(Filter f){
		f.filter(scansList);
	}
	/**
	 * printing the DB to kml File
	 */
	public void toKML(File outputPath)
	{
		String kmlOutputPath=outputPath.getPath();
		kmlFile kmlFile= new kmlFile(this.scansList, kmlOutputPath);
		kmlFile.SetKmlFIle();
		kmlFile.exportKml();
	}
	/**
	 * printing the DB to csv File
	 */
	public void toCSV(File outputPath)
	{
		String pathOutput=outputPath.getPath();
		write.writeCsvFile(scansList, pathOutput);
	}

	public int getNumberOfWifiSpots() {
		this.numOfWifiSpots = 0;
		this.scansList.forEach(singleScan -> numOfWifiSpots += singleScan.getWifiSpotsList().size());
		return numOfWifiSpots;
	}
	
	public boolean contains(singleScan scan)
	{
		for(int i=0;i<this.scansList.size();i++)
		{
			if(scan.equals(this.scansList.get(i)))
				return true;
		}
		return false;
	}
	public Object[][] toTable(){
		Object[][] data = new Object[this.scansList.size()][46];
		for(int i=0;i<scansList.size();i++){
			Object[] row =scansList.get(i).toString().split(",");
			for (int j = 0; j < row.length-1; j++) {
				System.out.println(row.length);
				data[i][j] = row[j];
			}
		}
		return data;
	}
	
	public String toString()
	{
		String s="";
		for(int i=0;i<this.scansList.size();i++)
		{
			s+=this.getScansList().get(i).toString();
		}
		s+="\n";
		return s;
	}
}
