package objects;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SqlDB {
	//	  private static String _ip = "5.29.193.52";
	//	  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	//	  private static String _user = "oop1";
	//	  private static String _password = "Lambda1();";

	protected  String _ip;
	protected  String _url;
	protected  String _port;
	protected  String _DBName;
	protected  String _user;
	protected  String _password;
	protected  Connection _con = null;

	public SqlDB(String ip,String port,String user,String password,String DBName ){
		_ip = ip;
		_port = port;
		_DBName = DBName;
		_url = "jdbc:mysql://"+_ip+":"+_port+"/"+_DBName;
		_user = user;
		_password = password;
	}

	public SqlDB(SqlDB db) {
		_ip = db._ip;
		_port = db._port;
		_DBName = db._DBName;
		_url = "jdbc:mysql://"+_ip+":"+_port+"/"+_DBName;
		_user = db._user;
		_password = db._password;
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

	

	public  String get_ip() {
		return _ip;
	}

	public  void set_ip(String ip) {
		_ip = ip;
	}

	public  String get_url() {
		return _url;
	}

	public  void set_url(String url) {
		_url = url;
	}

	public  String get_user() {
		return _user;
	}

	public  void set_user(String user) {
		_user = user;
	}

	public  String get_password() {
		return _password;
	}

	public  void set_password(String password) {
		_password = password;
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
