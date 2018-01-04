package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.SqlDateModel;

import Algorithms.personLocationFinder;
import Algorithms.wifiLocationFinder;
import Libraries.write;
import objects.DB;
import objects.Server;
import objects.coordinate;
import objects.singleScan;
import objects.wifiSpot;

public class Algo2WindowMacs extends JFrame {
	

	private JPanel contentPane;

	private final JButton btnComputeLocation = new JButton("Compute Location");
	private JTextField mac;
	private JTextField lon;
	private JTextField lat;
	private JTextField alt;
	private JTextField signal;
	private ArrayList<String[]> MACList = new ArrayList<String[]>();
	private coordinate estCoor = new coordinate();




	/**
	 * Create the frame.
	 */
	public Algo2WindowMacs(Server server) {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnComputeLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estCoor = server.scanLocation(MACList);
				String longtitude = ""+estCoor.getLon();
				String latitude = ""+estCoor.getLat();
				String altitude = ""+estCoor.getAlt();
				lon.setText(longtitude); 
				lat.setText(latitude);
				alt.setText(altitude);

			}
		});
		btnComputeLocation.setBounds(147, 219, 140, 31);
		contentPane.add(btnComputeLocation);
		
		mac = new JTextField();
		mac.setBounds(107, 29, 97, 20);
		
		contentPane.add(mac);
		mac.setColumns(10);
		
		lon = new JTextField();
		lon.setEditable(false);
		lon.setBounds(146, 115, 141, 20);
		contentPane.add(lon);
		lon.setColumns(10);
		
		JLabel lblMacAddress = new JLabel("MAC:");
		lblMacAddress.setBounds(142, 10, 63, 14);
		contentPane.add(lblMacAddress);
		
		JLabel lblEstimatedLocation = new JLabel("Estimated location:");
		lblEstimatedLocation.setBounds(167, 90, 120, 14);
		contentPane.add(lblEstimatedLocation);
		
		JLabel lblLon = new JLabel("Lon:");
		lblLon.setBounds(90, 118, 46, 14);
		contentPane.add(lblLon);
		
		JLabel lblLst = new JLabel("Lat:");
		lblLst.setBounds(90, 143, 46, 14);
		contentPane.add(lblLst);
		
		JLabel lblAlt = new JLabel("Alt:");
		lblAlt.setBounds(90, 168, 46, 14);
		contentPane.add(lblAlt);
		
		lat = new JTextField();
		lat.setColumns(10);
		lat.setEditable(false);
		lat.setBounds(146, 140, 141, 20);
		contentPane.add(lat);
		
		alt = new JTextField();
		alt.setEditable(false);
		alt.setColumns(10);
		alt.setBounds(146, 165, 141, 20);
		contentPane.add(alt);
		
		signal = new JTextField();
		signal.setColumns(10);
		signal.setBounds(214, 29, 97, 20);
		contentPane.add(signal);
		
		JLabel lblSignal = new JLabel("Signal:");
		lblSignal.setBounds(241, 10, 63, 14);
		contentPane.add(lblSignal);
		
		JButton btnAddMac = new JButton("Add MAC");
		btnAddMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] MAC = {mac.getText(),signal.getText()};
				MACList.add(MAC);
			}
		});
		btnAddMac.setBounds(160, 60, 89, 23);
		contentPane.add(btnAddMac);
		
	}
}
