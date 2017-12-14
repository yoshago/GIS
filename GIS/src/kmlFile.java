

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * @author Yehonatan&Yishay
 * 
 *
 * 
 */

public class kmlFile {
	private File kmlFileOutput;
	private final String DocumentName = "WifiSpotsMap";
	private final Kml kmlObject = new Kml();
	private Document document = kmlObject.createAndSetDocument().withName(DocumentName);
	private ArrayList<singleScan> scansList = new ArrayList<singleScan>();
	private ArrayList<wifiSpot> wifiSpotsList = new ArrayList<wifiSpot>();
	private boolean marshalTest;
	private Map<String, ArrayList<wifiSpot>> macToWifiSpotMap = new HashMap<>();

	/**
	 * this class represents an object of the type kmlFile and its constructor takes a list of singleScan object 
	 * and process it into a kml file using Java Api Kml library.
	 * the kml file can be shown with google earth.
	 * 
	 * 
	 * @param scansList
	 * @param outputPath
	 * 
	 */
	public kmlFile(ArrayList scansList, String outputPath){
		this.scansList = scansList;
		addStyles();
		addScansPlacemarks();
		addWifiSpotsPlacemarks();
		exportKml(outputPath);
	}
	


	/**
	 * Adds Icon styles to the document
	 * 
	 */
	private void addStyles(){
		document.createAndAddStyle().withId("wifiIcon").createAndSetIconStyle().withScale(1.5).withIcon(new Icon().withHref("http://www.freepngimg.com/download/wifi/4-2-wi-fi-png-images.png"));
		document.createAndAddStyle().withId("Magnifier").createAndSetIconStyle().withScale(1.5).withIcon(new Icon().withHref("https://images.vexels.com/media/users/3/132064/isolated/preview/27a9fb54f687667ecfab8f20afa58bbb-search-businessman-circle-icon-by-vexels.png"));
	}
	
	/**
	 * Removing duplicate wifi spots (by mac address).
	 * Each wifi spot will occur only once in the singleScan where the signal is the strongest(The closest place to the actual router).  
	 * The function uses HashMap to map each wifiSpot to its mac.
	 */
	
	                       
	                                                                                                  //scansList.forEach(singleScan -> singleScan.getWifiSpotsList().removeIf(wifiSpot -> !wifiSpot.equals(macToWifiSpotMap.get(wifiSpot.getMac()))));
	

	
	
	public ArrayList<singleScan> getScansList() {
		return scansList;
	}

	/**
	 * Adds points of each scan to the kml file using the JAK library (java api for kml).
	 * 
	 */
	private void addScansPlacemarks(){
		for (int i=0;i<scansList.size();i++){
			coordinate scanLocation = scansList.get(i).getCoordinate();
			String model = scansList.get(i).getId();
			String time = scansList.get(i).getTime();
			int numOfWifiSpots = scansList.get(i).getSize();
			String Description = numOfWifiSpots + " wifi networks found:\n";
			for (int j=0;j<scansList.get(i).getWifiSpotsList().size();j++){
				Description+= scansList.get(i).getWifiSpotsList().get(j).getSsid()+"\n";
			}
			Placemark p = this.document.createAndAddPlacemark();
			p.createAndSetTimeStamp().withWhen(time.replace(' ', 'T'));
			p.withName(model).withDescription(Description).withStyleUrl("#Magnifier").createAndSetPoint().addToCoordinates(scanLocation.getLon(), scanLocation.getLat(), scanLocation.getAlt());
		}
	}
	
	/**
	 * Adds points of each wifi spot to the kml using the JAK library (java api for kml)
	 * (used only after removing the duplicated wifi spots).
	 * 
	 */
	
	private void addWifiSpotsPlacemarks(){
		for (int i = 0; i < scansList.size(); i++) {
			coordinate scanLocation = scansList.get(i).getCoordinate();
			String model = scansList.get(i).getId();
			String time = scansList.get(i).getTime();
			ArrayList<wifiSpot> currentWifiSpotsList = scansList.get(i).getWifiSpotsList();
			for (int j = 0; j < (currentWifiSpotsList.size()); j++){
				String ssid = currentWifiSpotsList.get(j).getSsid();
				String mac = currentWifiSpotsList.get(j).getMac();
				String frequency = currentWifiSpotsList.get(j).getChannel();
				int signal = currentWifiSpotsList.get(j).getSignal();
				String description = "SSID: " + "<b>" + ssid + "</b>" + "<br/>" + "MAC: " + "<b>" + mac + "</b>"
						+ "<br/>" + "Time: " + "<b>" + time + "</b>" + "<br/>" + "Model: " + "<b>" + model
						+ "</b>" + "<br/>" + "Longitude: " + "<b>" + scanLocation.getLon() + "</b>" + "<br/>" + "Latitude: "
						+ "<b>" + scanLocation.getLat() + "</b>" + "<br/>"  + "Altitude: " 
						+ "<b>"+ scanLocation.getAlt() + "</b>" + "<br/>" +  "Frequency: " + "<b>" + frequency + "</b>"
						+ "<br/>" + "Signal: " + "<b>" + signal + "</b>";
				Placemark p = this.document.createAndAddPlacemark();
				p.createAndSetTimeStamp().withWhen(time.replace(' ', 'T'));
				p.withName(ssid).withStyleUrl("#wifiIcon").withOpen(Boolean.TRUE).withDescription(description).createAndSetPoint().addToCoordinates(scanLocation.getLon(), scanLocation.getLat(),scanLocation.getAlt());
			}
		}
	}


	/**
	 * Exports the kml file to the output path.
	 * The name of the exported file will contain the time of export.
	 * 
	 */
	
	private void exportKml(String outputPath){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		kmlFileOutput = new File(outputPath +"\\outputEarth"+dateFormat.format(date)+".kml");
		try{
		marshalTest = kmlObject.marshal(kmlFileOutput);
		System.out.println("kml exported successfully!");
		run(kmlFileOutput);
		}
		catch (IOException ex) {
			System.out.print("Error exporting file\n" + ex);
			System.exit(2);
		}
		
	}

	public boolean isMarshalTest() {
		return marshalTest;
	}
	
	private void run(File output1)
	{
		try
		{
		Desktop.getDesktop().open(output1);	
		}
		catch(IOException ex)
		{
			System.out.println("error running file\n"+ex);
			System.exit(2);
		}
	}
	
	
	
}
