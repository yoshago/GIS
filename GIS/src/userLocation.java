import java.util.ArrayList;

public class userLocation {

	private ArrayList<singleScan> dataBase;
	private ArrayList<singleScan> input;
	

	public userLocation(ArrayList<singleScan> dataBase, ArrayList<singleScan> input)
	{
		this.input=input;
		this.dataBase=dataBase;
	}

	public void Locating()
	{
		ImaginationScanList list= new ImaginationScanList();
		double imagination=0;
		for(int i=0;i<this.input.size();i++)
		{
			for(int j=0;j<this.dataBase.size();j++)
			{
				imagination=testImagination(dataBase.get(j),input.get(i));
				ImaginationScan scan=new ImaginationScan(dataBase.get(j),imagination);
				list.add(scan);
			}
			this.input.get(i).setCoordinate(findLocation(list,input.get(i)));
		}
	}

	private coordinate findLocation(ImaginationScanList list, singleScan inputScan) 
	{
		double moneLon=0;
		double moneLat=0;
		double moneAlt=0;
		double mech=0;
		double pi=1;

		for(int i=0;i<list.getSize();i++)
		{
			pi=list.getList().get(i).computePI(inputScan.getWifiSpotsList());
			moneLon+=list.getList().get(i).getScan().getCoordinate().getLon()*pi;
			moneLat+=list.getList().get(i).getScan().getCoordinate().getLat()*pi;
			moneAlt+=list.getList().get(i).getScan().getCoordinate().getAlt()*pi;
			mech+=pi;
		}
		coordinate coor=new coordinate(moneLon/mech,moneLat/mech,moneAlt/mech);
		return coor;
	}

	private double testImagination(singleScan singleScan,singleScan inputScan)
	{
		double imagination=0;
		int tmp=0;
		for(int i=0;i<inputScan.getSize();i++)
		{
			tmp=singleScan.contains(inputScan.getWifiSpotsList().get(i));
			if(tmp>=0)
			{
				imagination*=Math.abs(-inputScan.getWifiSpotsList().get(i).getSignal()-Math.abs(-inputScan.getWifiSpotsList().get(i).getSignal()-singleScan.getWifiSpotsList().get(tmp).getSignal()))/-inputScan.getWifiSpotsList().get(i).getSignal();
			}
			else imagination*=0.1;
		}
		return imagination;
	}
}
