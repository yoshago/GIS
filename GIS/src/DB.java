import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DB {
	private ArrayList<singleScan> scansList = new ArrayList<singleScan>();
	
	public ArrayList<singleScan> getScansList() {
		return scansList;
	}

	public void setScansList(ArrayList<singleScan> scansList) {
		this.scansList = scansList;
	}

	public DB(ArrayList<singleScan> scansList){
		this.scansList = scansList;
	}
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
	 * @param singleScanList
	 * this function calls the other filter functions according to the user request, (Kind of a UI for filtering).
	 * 
	 */
	
	public void Filter(){
		Filter.mainFilter(scansList);
	}
	public void toKML()
	{
		String kmlOutputPath;
		System.out.println("please enter path for output kml file");
		Scanner userInput=new Scanner(System.in);
		kmlOutputPath=userInput.nextLine();
		kmlFile kmlFile= new kmlFile(this.scansList, kmlOutputPath);
		//userInput.close();
	}
	public void toCSV()
	{
		String pathOutput;
		System.out.println("please enter path for output csv file");
		Scanner userInput=new Scanner(System.in);
		pathOutput=userInput.nextLine();
		write.writeCsvFile(scansList, pathOutput);
	}
	
}
