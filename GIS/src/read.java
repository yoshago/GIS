import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




/**
 * @author Yehonatan&Yishay
 * @description A library class. the class contains static functions that associated with reading files and folders.
 *              using by classes: Folder, processCsv.
 */
public class read {
	/**
	 * @param listOfFiles-list of all file from user's Folder.
	 * @return singleScanList-contains all data from the wigle-wifi files in the folder. 
	 *         every singleScan represent one scan of wigle-wifi application. 
	 */
	public static ArrayList<singleScan> readFolder(File[] listOfFiles)
	{
		ArrayList<singleScan> singleScanList=new ArrayList<singleScan>();
		for(int i=0;i<listOfFiles.length;i++)
		{
			readFile(listOfFiles[i],singleScanList);	
		}
		return singleScanList;
	}

	/**
	 * @param f-one file from the "listOfFiles" 
	 * @param singleScanList-list of singleScan objects.
	 * @description this function get file and process him into a list of singleScans.
	 */
	public static void readFile(File f, ArrayList<singleScan> singleScanList)
	{
		final int MAC_INDEX=0;
		final int SSID_INDEX=1;
		final int TIME_INDEX=3;
		final int CHANNEL_INDEX=4;
		final int SIGNAL_INDEX=5;
		final int LAT_INDEX=6;
		final int LON_INDEX=7;
		final int ALT_INDEX=8;
		final int ID_INDEX=2;
		final int TYPE_INDEX=10;// GSM/WIFI

		try
		{
			if(!isWigleFile(f))
			{
				System.out.println("not a wigle wifi file");
				return;
			}
			String[] readLine=new String[12];
			FileReader fr=new FileReader(f.getPath());
			BufferedReader br=new BufferedReader(fr);
			String str;

			str = br.readLine();
			int count =0;
			String id="";
			coordinate coor=new coordinate();
			singleScan singleScan=new singleScan(readLine[TIME_INDEX],id, coor);

			while (str != null)
			{
				readLine = str.split(",");
				if(count==0)//the id is in the first line only
					id=readLine[ID_INDEX].substring(6);
				else if(count>1 && readLine.length==11 && readLine[TYPE_INDEX].contains("WIFI"))
				{
					coor=new coordinate(readLine[LON_INDEX], readLine[LAT_INDEX], readLine[ALT_INDEX]);
					wifiSpot ws=new wifiSpot(readLine[MAC_INDEX],readLine[SSID_INDEX],readLine[CHANNEL_INDEX],readLine[SIGNAL_INDEX], coor);

					if(readLine[TIME_INDEX].equals(singleScan.getTime()))
						singleScan.add(ws);
					else
					{
						if(count>2 && !singleScan.getTime().contains("1970-01-01 02:00:00"))
							singleScanList.add(singleScan);

						singleScan=new singleScan(readLine[TIME_INDEX],id,coor);
						singleScan.add(ws);
					}
				}
				count++;
				str=br.readLine();
			}
			singleScanList.add(singleScan);
			br.close();
			fr.close();
		}
		catch(IOException ex)
		{
			System.out.println("error reading file "+ex);
		}
      
	}

	/**
	 * @param f-file from listOfFiles.  
	 * @return true if this file is a Wigle-wifi file,  else-false
	 */
	private static boolean isWigleFile(File f)
	{
		if(!f.getName().contains(".csv"))
		{
			System.out.println(f.getName()+" is not a csv file");
			return false;
		}
		try
		{
			FileReader fr=new FileReader(f.getPath());
			BufferedReader br=new BufferedReader(fr);
			String str;
			str = br.readLine();
			if (!str.contains("WigleWifi-1.4")) 
			{
				System.out.println(f.getName()+" is not a wigleWifi file");
				br.close();
				fr.close();
				return false;
			}
			br.close();
			fr.close();
		}
		catch(IOException ex)
		{
			System.out.println("error reading file "+ex);
		}
		return true;
	}



	/**
	 * @description this function called by process csv class, get file and return singleScan list,
	 *              that contains data from each scan of wigle-wifi application in one singleScan.
	 * @param f- output file that exported by Folder class
	 * @return singleScan list
	 *  
	 */
	public static ArrayList<singleScan> readOutputFolderFile(File f)
	{
		ArrayList<singleScan> singleScanList=new ArrayList<singleScan>();
		try
		{
			String[] singleScanLine=new String[46];
			FileReader fr=new FileReader(f.getPath());
			BufferedReader br=new BufferedReader(fr);
			String str;
			str = br.readLine();
			str=br.readLine();
			while (str != null)
			{
				singleScanLine = str.split(",");
				coordinate coor=new coordinate(singleScanLine[2] ,singleScanLine[3] ,singleScanLine[4]);
				singleScan singleScan=new singleScan(singleScanLine[0], singleScanLine[1] ,coor);
				int i=5;
				while(++i<singleScanLine.length && singleScanLine[i]!=null )
				{
					wifiSpot wifiSpot=new wifiSpot(singleScanLine[i],singleScanLine[++i],singleScanLine[++i],singleScanLine[++i],coor);
					singleScan.add(wifiSpot);
				}
				singleScanList.add(singleScan);
				str=br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(IOException ex)
		{
			System.out.println("error reading file "+ex);
			System.exit(2);
		}
		return singleScanList;
	}
}