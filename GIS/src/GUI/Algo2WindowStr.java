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
import objects.coordinate;
import objects.singleScan;
import objects.wifiSpot;

public class Algo2WindowStr extends JFrame {

	private JPanel contentPane;

	private final JButton btnComputeLocation = new JButton("Compute Location");
	private JTextField scan;
	private JTextField lon;
	private JTextField lat;
	private JTextField alt;
	private ArrayList<wifiSpot> wsl;
	private coordinate estCoor = new coordinate();



	/**
	 * Create the frame.
	 */
	public Algo2WindowStr(DB db) {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnComputeLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String scanStr = scan.getText();
				personLocationFinder plf = new personLocationFinder(db,scanStr);
				plf.findLocation();
				estCoor = plf.getInput().getScansList().get(0).getCoordinate();
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
		
		scan = new JTextField();
		scan.setBounds(29, 35, 384, 20);
		
		contentPane.add(scan);
		scan.setColumns(10);
		
		lon = new JTextField();
		lon.setEditable(false);
		lon.setBounds(140, 92, 141, 20);
		contentPane.add(lon);
		lon.setColumns(10);
		
		JLabel lblMacAddress = new JLabel("Scan string:");
		lblMacAddress.setBounds(180, 10, 107, 14);
		contentPane.add(lblMacAddress);
		
		JLabel lblEstimatedLocation = new JLabel("Estimated location:");
		lblEstimatedLocation.setBounds(161, 67, 120, 14);
		contentPane.add(lblEstimatedLocation);
		
		JLabel lblLon = new JLabel("Lon:");
		lblLon.setBounds(84, 95, 46, 14);
		contentPane.add(lblLon);
		
		JLabel lblLst = new JLabel("Lat:");
		lblLst.setBounds(84, 120, 46, 14);
		contentPane.add(lblLst);
		
		JLabel lblAlt = new JLabel("Alt:");
		lblAlt.setBounds(84, 145, 46, 14);
		contentPane.add(lblAlt);
		
		lat = new JTextField();
		lat.setColumns(10);
		lat.setEditable(false);
		lat.setBounds(140, 117, 141, 20);
		contentPane.add(lat);
		
		alt = new JTextField();
		alt.setEditable(false);
		alt.setColumns(10);
		alt.setBounds(140, 142, 141, 20);
		contentPane.add(alt);
		
	}
}
