import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ישי
 *
 */
public class ImaginationScan {

private double imagination;
private singleScan scan;

public final int norm=10000;
public final double sig_dif=0.4;
public final int min_dif=3;
public int no_signal=-120;
public int dif_no_signal=100;

	public ImaginationScan(singleScan scan,double imagination)
	{
		this.imagination=imagination;
		this.scan=scan;
	}
	
	public int compare(ImaginationScan another)
	{
		if(this.imagination>another.imagination) return 1;
		if(this.imagination==another.imagination) return 0;
		return -1;
	}
	
	public double computePI(ArrayList<wifiSpot> input)
	{
		double pi=1;
		for(int i=0;i<input.size();i++)
		{
			pi*=computeWeight(input, i);
		}
		return pi;
	}

	private double computeWeight(ArrayList<wifiSpot> input, int i) 
	{
		double weight;
		weight=norm/(Math.pow(diff(input,i), sig_dif)*Math.pow(input.get(i).getSignal(), 2));
		return weight;
	}

	private int diff(ArrayList<wifiSpot> input, int i) {
		int index=scan.contains(input.get(i));
		if(index<0) return 100;
		else return Math.max(3,Math.abs(input.get(i).getSignal()-this.scan.getWifiSpotsList().get(index).getSignal()));
	}

	public singleScan getScan() {
		return scan;
	}
	
}
