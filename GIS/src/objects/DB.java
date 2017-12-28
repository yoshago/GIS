package objects;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Libraries.Filter;
import Libraries.read;
import Libraries.write;

/**
 * @author Yehonatan&Yishay
 *
 */
public class DB {
	private ArrayList<singleScan> scansList = new ArrayList<singleScan>();
	private int numOfWifiSpots;
	

	public ArrayList<singleScan> getScansList() {
		return scansList;
	}

	public void add(ArrayList<singleScan> array) {
		this.scansList.addAll(array);
	}
	/**
	 * @param scansList
	 * 
	 */

	public void setScansList(ArrayList<singleScan> scansList) {
		this.scansList = scansList;
	}

	/**
	 * 
	 */
	public DB() {
	}

	/**
	 * @param scansList
	 * constructor of the DB from ArrayList of singleScan objects.
	 */
	public DB(ArrayList<singleScan> scansList){
		this.scansList = scansList;
	}
	public DB(DB db){
		this.scansList = db.getScansList();
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
	
	

	/**
	 * 
	 * this function calls the other filter functions according to the user request, (Kind of a UI for filtering).
	 * 
	 */
	
	public void Filter(){
		Filter.mainFilter(scansList);
	}
	/**
	 * printing the DB to kml File
	 */
	public void toKML()
	{
		String kmlOutputPath;
		System.out.println("please enter path for output kml file");
		Scanner userInput=new Scanner(System.in);
		kmlOutputPath=userInput.nextLine();
		kmlFile kmlFile= new kmlFile(this.scansList, kmlOutputPath);
		kmlFile.SetKmlFIle();
		kmlFile.exportKml();
		//userInput.close();
	}
	/**
	 * printing the DB to csv File
	 */
	public void toCSV()
	{
		String pathOutput;
		System.out.println("please enter path for output csv file");
		Scanner userInput=new Scanner(System.in);
		pathOutput=userInput.nextLine();
		write.writeCsvFile(scansList, pathOutput);
	}

	public int getNumberOfWifiSpots() {
		this.numOfWifiSpots = 0;
		this.scansList.forEach(singleScan -> numOfWifiSpots += singleScan.getWifiSpotsList().size());
		return numOfWifiSpots;
	}
	
}
