package Algorithms;
import java.io.File;
import java.util.ArrayList;

import Libraries.read;
import objects.DB;
import objects.coordinate;
import objects.singleScan;
import objects.wifiSpot;


/**
 * @author ישי
 *
 */
public class personLocationFinder {
	
	DB DB;
	DB input;
	
	public personLocationFinder(String pathDB,String pathInput)
	{
		File fDB=new File(pathDB);
		File fInput=new File(pathInput);
		this.DB.setScansList(read.readOutputFolder(fDB.listFiles()));
		this.input.setScansList(read.readOutputFolder(fInput.listFiles()));
	}
	public personLocationFinder(DB db,String scan)
	{
		this.DB = db;
		ArrayList<singleScan> scansList= new ArrayList<singleScan>();
		this.input = new DB();
		this.input.getScansList().add(new singleScan(scan));
	}
	
	public void findLocation()
	{
		for(int i=0;i<input.getScansList().size();i++)
		{
			Locating(input.getScansList().get(i));
			
		}
		
	}
	
	private  void Locating(singleScan input)
	{
		ImaginationScansList list= new ImaginationScansList();
		double imagination=0;
		for(int j=0;j<this.DB.getScansList().size();j++)
		{
			imagination=computeImagination(DB.getScansList().get(j),input.getWifiSpotsList());
			ImaginationScan scan=new ImaginationScan(DB.getScansList().get(j).getTime(),DB.getScansList().get(j).getId(),DB.getScansList().get(j).getCoordinate(),DB.getScansList().get(j).getWifiSpotsList(),imagination);
			list.add(scan);
		}
		input.setCoordinate(findLocation(list,input));

	}
	
	private coordinate findLocation(ImaginationScansList list, singleScan inputScan) 
	{
		double moneLon=0;
		double moneLat=0;
		double moneAlt=0;
		double mech=0;
		double pi;
		for(int i=0;i<list.getSize();i++)
		{
			pi=list.getList().get(i).computePI(inputScan.getWifiSpotsList());
			moneLon+=list.getList().get(i).getCoordinate().getLon()*pi;
			moneLat+=list.getList().get(i).getCoordinate().getLat()*pi;
			moneAlt+=list.getList().get(i).getCoordinate().getAlt()*pi;
			mech+=pi;
		}
		coordinate coor=new coordinate();
		if (mech>0){
		coor=new coordinate(moneLon/mech,moneLat/mech,moneAlt/mech);
		}
		return coor;
	}
	
	private double computeImagination(singleScan singleScan,ArrayList<wifiSpot> input)
	{
		double imagination=1;
		int tmp=0;
		boolean flag =false;
		for(int i=0;i<input.size();i++)
		{
			tmp=singleScan.contains(input.get(i));
			if(tmp>=0)
			{
				flag =true;
				int delta=Math.abs(input.get(i).getSignal()-singleScan.getWifiSpotsList().get(tmp).getSignal());
				imagination*=(Math.abs(input.get(i).getSignal())-delta)/-input.get(i).getSignal();
			}
			else imagination*=0.1;
		}
		if (flag ==true)
		{
		return imagination;
		}
		else return -1;
	}

	public DB getDB() {
		return DB;
	}

	public DB getInput() {
		return input;
	}
	
}
