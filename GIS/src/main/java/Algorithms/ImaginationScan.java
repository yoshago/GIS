package main.java.Algorithms;
import java.util.ArrayList;

import main.java.objects.coordinate;
import main.java.objects.singleScan;
import main.java.objects.wifiSpot;

/**
 * 
 */

/**
 * @author ���
 *
 */
public class ImaginationScan extends singleScan implements Comparable<ImaginationScan>{

private double imagination;
private String time;
private String id;
private coordinate coordinate;
private ArrayList<wifiSpot> wifiSpotsList = new ArrayList<wifiSpot>();


public final int norm=10000;
public final double sig_dif=0.4;
public final int min_dif=3;
public int no_signal=-120;
public int dif_no_signal=100;

	public ImaginationScan(String time, String id , coordinate coordinate, ArrayList<wifiSpot> wifiSpotList, double imagination)
	{
		super(time,id,coordinate,wifiSpotList);
		this.imagination=imagination;
		
	}
	
	@Override
	public int compareTo(ImaginationScan other) {
		return Double.compare(this.imagination, other.getImagination());
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
		int index=this.contains(input.get(i));
		if(index<0) return 100;
		else return Math.max(3,Math.abs(input.get(i).getSignal()-this.getWifiSpotsList().get(index).getSignal()));
	}

	public double getImagination() {
		return imagination;
	}




	
}
