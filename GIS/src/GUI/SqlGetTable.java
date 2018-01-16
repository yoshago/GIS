package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import objects.DB;
import objects.SQLTable;
import objects.SqlDB;
import objects.Server;
import objects.coordinate;

import objects.wifiSpot;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import GUI.gisGui.MyComboBoxRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class SqlGetTable extends JFrame {

	private JPanel contentPane;

	private final JButton btnTestConn = new JButton("Test connection");
	private JTextField textIp;
	private JTextField textPort;
	private JTextField textUser;
	private JTextField textPswd;
	private SqlDB sql;
	private JTextField textDBName;
	private JTextArea sqlTextArea;
	private JComboBox comboBox;
	private boolean con;


	/**
	 * Create the frame.
	 */
	public SqlGetTable(Server server) {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnTestConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = textIp.getText();
				String port = textPort.getText();
				String user = textUser.getText();
				String pass = textPswd.getText();
				String DBName = textDBName.getText();
				sql = new SqlDB(ip,port,user,pass,DBName);
				con = sql.testConnection();
				if(con){
					updateConnection(con);
					updateTableList();
				}
				else{
					updateConnection(con);
				}
			}
		});
		btnTestConn.setBounds(42, 219, 140, 31);
		contentPane.add(btnTestConn);
		
		JButton btnNewButton = new JButton("Get table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() != null){
					String TableName = (String) comboBox.getSelectedItem();
					DB newDb;
					sqlTextArea.setText(server.FilterStackString());
					SQLTable Table = new SQLTable(sql,TableName);
					server.addSqlTable(Table);
				}
				else{
					updatePleaseSelect();
				}
			}
		});
		btnNewButton.setBounds(242, 219, 147, 31);
		contentPane.add(btnNewButton);
		
		textIp = new JTextField();
		textIp.setBounds(74, 51, 86, 20);
		contentPane.add(textIp);
		textIp.setColumns(10);
		
		textPort = new JTextField();
		textPort.setColumns(10);
		textPort.setBounds(74, 81, 86, 20);
		contentPane.add(textPort);
		
		textUser = new JTextField();
		textUser.setColumns(10);
		textUser.setBounds(74, 112, 86, 20);
		contentPane.add(textUser);
		
		textPswd = new JTextField();
		textPswd.setColumns(10);
		textPswd.setBounds(74, 143, 86, 20);
		contentPane.add(textPswd);
		
		JLabel lblIp = new JLabel("ip:");
		lblIp.setBounds(30, 54, 31, 14);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("port:");
		lblPort.setBounds(22, 84, 31, 14);
		contentPane.add(lblPort);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(10, 115, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(10, 146, 65, 14);
		contentPane.add(lblPassword);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 15));
		comboBox.setRenderer(new MyComboBoxRenderer("Select Table"));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(262, 21, 120, 20);
		contentPane.add(comboBox);
		
		sqlTextArea = new JTextArea();
		sqlTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		sqlTextArea.setBounds(242, 95, 182, 113);
		contentPane.add(sqlTextArea);
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblInfo.setBounds(307, 74, 65, 20);
		contentPane.add(lblInfo);
		
		JLabel lblTablesList = new JLabel("Tables list:");
		lblTablesList.setBounds(293, 6, 79, 14);
		contentPane.add(lblTablesList);
		
		JLabel lblDatabase = new JLabel("database:");
		lblDatabase.setBounds(10, 177, 65, 14);
		contentPane.add(lblDatabase);
		
		textDBName = new JTextField();
		textDBName.setColumns(10);
		textDBName.setBounds(74, 174, 86, 20);
		contentPane.add(textDBName);
		
	}


	protected void updatePleaseSelect() {
		if(con){
			sqlTextArea.setText("Select Table Please");
		}
		else{
			sqlTextArea.setText("Sorry, we are not connected yet.");
		}
		
	}


	protected void updateConnection(boolean con) {
		if(con){
			sqlTextArea.setText("Connection Succeeded!");
		}
		else{
			sqlTextArea.setText("Connection Failed :(");
		}
		
	}


	protected void updateTableList() {
		ArrayList<String> tbls = sql.getTables();
		String[] options = new String[tbls.size()];
		for(int i=0;i<options.length;i++){
			options[i]= tbls.get(i);
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(options);
		comboBox.setRenderer(new MyComboBoxRenderer("Select Table"));
		comboBox.setSelectedIndex(-1);
		comboBox.setModel(model);
		
		
	}
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }



}
