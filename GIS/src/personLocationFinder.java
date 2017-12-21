import java.io.File;
import java.util.ArrayList;


/**
 * @author ���
 *
 */
public class personLocationFinder {
	
	ArrayList<singleScan> DB;
	ArrayList<singleScan> input;
	
	public personLocationFinder(String pathDB,String pathInput)
	{
		File fDB=new File(pathDB);
		File fInput=new File(pathInput);
		this.DB=read.readOutputFolder(fDB.listFiles());
		this.input=read.readOutputFolder(fInput.listFiles());
	}
	
	public void findLocation()
	{
		for(int i=0;i<input.size();i++)
		{
			Locating(input.get(i));
			
		}
		
	}
	
	public  void Locating(singleScan input)
	{
		ImaginationScansList list= new ImaginationScansList();
		double imagination=0;
		for(int j=0;j<this.DB.size();j++)
		{
			imagination=computeImagination(DB.get(j),input.getWifiSpotsList());
			ImaginationScan scan=new ImaginationScan(DB.get(j).getTime(),DB.get(j).getId(),DB.get(j).getCoordinate(),DB.get(j).getWifiSpotsList(),imagination);
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
	
}
