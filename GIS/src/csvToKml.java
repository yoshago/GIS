import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
/*
 * this class uses class csvFilter and used by it
 * this class uses a csvFilter object and make .kml file
 * kml syntax was taken from: https://developers.google.com/kml/documentation/kml_tut
 */



//kjdsgblsbjvilB
public class csvToKml {

	private String kmlString;
	private csvFilter csvf;
	private ArrayList<Line> LineList= new ArrayList<Line>();
	
	public csvToKml(csvFilter csvf){
		this.csvf = csvf;
	}
	public String KmlAddScans(String kml, ArrayList<String[]> XYlist){//adds the points of the scans to the kml with ID as name
		
		for(int i=0;i <XYlist.size();i++){
			kml+=kmlScanCoordinateGenerator(XYlist.get(i)[0],XYlist.get(i)[1],XYlist.get(i)[2],XYlist.get(i)[3],XYlist.get(i)[4]);
		}
		
		return kml;
	}
	public String KmlAddwifis(String kml, ArrayList<String[]> XYlist){//adds the points of the wifis to the kml with SSID as name and MAC and Signal in the description.
		
		for(int i=0;i <XYlist.size();i++){
			kml+=kmlWifiCoordinateGenerator(XYlist.get(i)[0],XYlist.get(i)[1],XYlist.get(i)[2],XYlist.get(i)[4],"mac - "+ XYlist.get(i)[3] + "\n signal - "+XYlist.get(i)[5]);
		}
		
		return kml;
	}
	
	/*
	 * //takes a list of Line objects and manipulate him into proper String of a kml file
	 */
	public String ScanToKml(){
		ArrayList<Line> lineList = this.csvf.getList();
		try{
			final Kml kml = new Kml();
			Document document = kml.createAndSetDocument().withName("WifiMap");
			for (int i = 0; i < lineList.size(); i++) {
				Double longitude = Double.parseDouble(lineList.get(i).getLon());
				Double latitude = Double.parseDouble(lineList.get(i).getLat());
				String model = lineList.get(i).getId();
				String time = lineList.get(i).getTime();
				for (int j = 0; j < (lineList.get(i).getWifiList().size()); j++) {
					String ssid = lineList.get(i).getWifiList().get(j).getSsid();
					String mac = lineList.get(i).getWifiList().get(j).getMac();
					int frequncy = Integer.parseInt(lineList.get(i).getWifiList().get(j).getChannel());
					int signal = Integer.parseInt(lineList.get(i).getWifiList().get(j).getSignal());
		}
		
		ArrayList<String[]> ScansXYlist= new ArrayList<String[]>();
		ArrayList<String[]> WifisXYlist= new ArrayList<String[]>();
		for (int i=0;i<list.size();i++){//Creates a list of the scans with their locations.
			String[] scan = new String[5];
			scan[0]= list.get(i).getLon();
			scan[1] = list.get(i).getLat();
			scan[2] = list.get(i).getAlt();
			scan[3] = list.get(i).getId();
			String[] wifis =list.get(i).getWifis().split(",");
			String s = wifis[0] + " Wifis:\n";
			for (int j=2;j<wifis.length;j+=4){
				s+=wifis[j]+ "\n";
			}
			scan[4] = s;
			ScansXYlist.add(scan);
		}
		for (int i=0;i<list.size();i++){//Creates a list of the wifi networks that contains only one of each router based on the strongest signal location .
			String[] wifis = list.get(i).getWifis().split(",");
			String[] wifi = new String[6];
			wifi[0]= list.get(i).getLon();
			wifi[1] = list.get(i).getLat();
			wifi[2] = list.get(i).getAlt();
			wifi[3] = wifis[1];//mac
			wifi[4] = wifis[2];//ssid
			wifi[5] = wifis[3];//signal
			WifisXYlist.add(wifi);
			for(int j=5;j<wifis.length;j+=4){
				wifi[0]= list.get(i).getLon();
				wifi[1] = list.get(i).getLat();
				wifi[2] = list.get(i).getAlt();
				wifi[3] = wifis[j];
				wifi[4] = wifis[j+1];
				wifi[5] = wifis[j+2];
				boolean flag = false;
				for(int k = 0;k < WifisXYlist.size();k++){
					if (wifi[3].equals(WifisXYlist.get(k)[3])){
						if(Integer.parseInt(wifi[5]) > Integer.parseInt(WifisXYlist.get(k)[5])){
							WifisXYlist.remove(k);
							WifisXYlist.add(wifi);
							System.out.println("a");
							break;
						}
						else{
							System.out.println("b");
							flag =true;
							break;
						}
					}
				}
				if (!flag) WifisXYlist.add(wifi);
			}
			
			
			
		}
		
		String kml = " <kml xmlns=\"http://www.opengis.net/kml/2.2\">\n    <Document>\n       <name>Document.kml</name> <open>1</open>\n       <Style id=\"wifi\">\n      <IconStyle>\n        <Icon>\n          <href>http://www.freepngimg.com/download/wifi/4-2-wi-fi-png-images.png</href>\n        </Icon>\n      </IconStyle>\n    </Style>\n<Style id=\"Magnifier\">\n      <IconStyle>\n        <Icon>\n          <href>https://images.vexels.com/media/users/3/132064/isolated/preview/27a9fb54f687667ecfab8f20afa58bbb-search-businessman-circle-icon-by-vexels.png</href>\n        </Icon>\n      </IconStyle>\n    </Style><Style id=\"exampleStyleDocument\">           <LabelStyle>\n           <color>ff0000cc</color>\n           </LabelStyle>\n         </Style>\n\n       <Style id=\"transBluePoly\">\n      <LineStyle>\n        <width>1.5</width>\n      </LineStyle>\n      <PolyStyle>\n        <color>7dff0000</color>\n      </PolyStyle>\n    </Style>";
		kml=KmlAddScans(kml, ScansXYlist);
		kml=KmlAddwifis(kml, WifisXYlist);
		if (csvf.getMinCoordinate()!= null){
		kml=AddFilteringArea(csvf.getMinCoordinate(),csvf.getMaxCoordinate(),kml);
		}
		kml+="\n  </Document>\n</kml>";
		this.kmlString = kml;
		return kml;
	}
	public String getKmlString() {
		return kmlString;
	}
	public csvFilter getCsvf() {
		return csvf;
	}
	public ArrayList<Line> getLineList() {
		return LineList;
	}
	public void exportToKml(){
		String s = this.kmlString;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		File output= new File("outputEarth"+dateFormat.format(date)+".kml");
		try{
		PrintWriter pw = new PrintWriter(output);
		pw.write(s);
        pw.close();
        System.out.println("Kml exported successfully!");
		}
		catch(IOException ex)
		{
			System.out.println("error writing to file "+ex);
			System.exit(2);
		}
		run(output);
	}
	
	private String kmlScanCoordinateGenerator(String lon,String lat,String alt,String pointName,String Desc){// adds one point to the kml - used for the scans
		String all = "<Placemark>\n           <name>"+pointName+"</name>\n           <description>"+ Desc+"</description>\n           <styleUrl>#Magnifier</styleUrl>\n           <Point>\n              <altitudeMode>absolute</altitudeMode>\n              <coordinates>";
		all+= lon+","+lat+","+alt+"</coordinates>\n           </Point>\n       </Placemark>\n\n		";
		return all;
	}
	private String kmlWifiCoordinateGenerator(String lon,String lat,String alt,String pointName, String Desc){// adds one point to the kml - used for the wifi networks (with description)
		String all = "<Placemark>\n           <name>"+pointName+"</name>\n           <description>"+ Desc+"</description>\n           <styleUrl>#wifi</styleUrl>\n           <Point>\n              <altitudeMode>absolute</altitudeMode>\n              <coordinates>";
		all+= lon+","+lat+","+alt+"</coordinates>\n           </Point>\n       </Placemark>\n\n		";
		return all;
	}
	private String AddFilteringArea(String[] min, String[] max, String kml){//adds the rectangle of the filtering area to the kml
		kml+="<Placemark>\n      <name>Filtered Area</name>\n      <styleUrl>#transBluePoly</styleUrl>\n      <Polygon>\n        <extrude>1</extrude>\n        <outerBoundaryIs>\n          <LinearRing>\n            <coordinates>\n              " +min[0]+","+min[1]+",0\n              "+min[0]+","+max[1]+",0\n              "+max[0]+","+max[1]+",0\n              "+max[0]+","+min[1]+",0\n            </coordinates>\n          </LinearRing>\n        </outerBoundaryIs>\n      </Polygon>\n    </Placemark>";
		return kml;
	}
private void run(File output)
{
	try
	{
	Desktop.getDesktop().open(output);	
	}
	catch(IOException ex)
	{
		System.out.println("error running file\n"+ex);
		System.exit(2);
	}
}

}
