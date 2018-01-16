package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class SQLTable extends SqlDB {
	private String tableName;
	private String lastModified;
	
	
	

	public SQLTable(SqlDB db, String tableName){
		super(db);
		this.tableName = tableName;
		updateLastModified();
	}



	public SQLTable(String ip, String port, String user, String password, String DBName) {
		super(ip, port, user, password, DBName);
		
	}
	
	public void updateLastModified(){
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			
			pst = _con.prepareStatement("SELECT update_time	FROM   information_schema.tables	WHERE  table_schema = '"+_DBName	+"'       AND table_name = '"+tableName+"'");
			rs = pst.executeQuery();
			if(rs.next()){
				lastModified = rs.getString(1);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTableName() {
		return tableName;
	}



	public String getLastModified() {
		return lastModified;
	}



	public DB readTable(){
		DB table = new DB();
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			
			pst = _con.prepareStatement("SELECT * FROM " + tableName);
			rs = pst.executeQuery();
			while(rs.next()){
				String time = rs.getString(2);
				String ssid = rs.getString(3);
				double lat = rs.getDouble(4);
				double lon = rs.getDouble(5);
				double alt = rs.getDouble(6);
				coordinate coor = new coordinate(lon,lat,alt);
				int size = rs.getInt(7);
				ArrayList<wifiSpot> wsl = new ArrayList<wifiSpot>();
				for (int i=8;i<7+2*size;i+=2){
					wifiSpot ws = new wifiSpot(rs.getString(i),rs.getInt(i+1),coor);
					wsl.add(ws);
				}
				singleScan ss = new singleScan(time,ssid,coor,wsl);
				table.getScansList().add(ss);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;

	}

	


}
