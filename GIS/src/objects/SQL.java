package objects;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SQL {
	//	  private static String _ip = "5.29.193.52";
	//	  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	//	  private static String _user = "oop1";
	//	  private static String _password = "Lambda1();";

	private static String _ip;
	private static String _url;
	private static String _port;
	private static String _DBName;
	private static String _user;
	private static String _password;
	private static Connection _con = null;

	public SQL(String ip,String port,String user,String password,String DBName ){
		_ip = ip;
		_port = port;
		_DBName = DBName;
		_url = "jdbc:mysql://"+_ip+":"+_port+"/"+_DBName;
		_user = user;
		_password = password;
	}

	public ArrayList<String> getTables(){
		ArrayList<String> tables = new ArrayList<String>();
		DatabaseMetaData m = null;
		ResultSet rs = null;
		try {
			m = _con.getMetaData();
			rs = m.getTables(null, null, "%", null);
			while (rs.next()) {
				tables.add(rs.getString(3));
			}
			return tables;
		} catch (SQLException e) {
			e.printStackTrace();
			return tables;
		}
	}

	public DB readTable(String tableName, String filterQuery){
		DB table = new DB();
		ResultSet rs = null;
		PreparedStatement pst;
		try {
			if(filterQuery.length()>0)
			pst = _con.prepareStatement("SELECT * FROM " + tableName + " WHERE "+filterQuery);
			else
				pst = _con.prepareStatement("SELECT * FROM " + tableName);
			rs = pst.executeQuery();
			while(rs.next()){
				String time = rs.getString(1);
				String ssid = rs.getString(2);
				double lat = rs.getDouble(3);
				double lon = rs.getDouble(4);
				double alt = rs.getDouble(5);
				coordinate coor = new coordinate(lon,lat,alt);
				int size = rs.getInt(6);
				ArrayList<wifiSpot> wsl = new ArrayList<wifiSpot>();
				for (int i=7;i<7+2*size;i+=2){
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


	public static String get_ip() {
		return _ip;
	}

	public static void set_ip(String _ip) {
		SQL._ip = _ip;
	}

	public static String get_url() {
		return _url;
	}

	public static void set_url(String _url) {
		SQL._url = _url;
	}

	public static String get_user() {
		return _user;
	}

	public static void set_user(String _user) {
		SQL._user = _user;
	}

	public static String get_password() {
		return _password;
	}

	public static void set_password(String _password) {
		SQL._password = _password;
	}

	public boolean testConnection(){
		try {
			_con = DriverManager.getConnection(_url, _user, _password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
