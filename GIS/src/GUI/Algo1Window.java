package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import Algorithms.wifiLocationFinder;
import objects.DB;
import objects.coordinate;
import objects.wifiSpot;

public class Algo1Window extends JFrame {

	private JPanel contentPane;

	private final JButton btnComputeLocation = new JButton("Compute Location");
	private JTextField mac;
	private JTextField lon;
	private JTextField lat;
	private JTextField alt;
	private ArrayList<wifiSpot> wsl;
	private coordinate estCoor = new coordinate();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FilterWindow frame = new FilterWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Algo1Window(DB db) {
		wifiLocationFinder wlf = new wifiLocationFinder(db);
		wlf.findSpotsLocation();
	    wsl =wlf.getFinalWifiList();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnComputeLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MacStr = mac.getText();
				wsl.forEach(wifiSpot -> {
					if(wifiSpot.getMac().equals(MacStr)) estCoor = wifiSpot.getCoordinate();
				});
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
		mac.setBounds(150, 35, 117, 20);
		
		contentPane.add(mac);
		mac.setColumns(10);
		
		lon = new JTextField();
		lon.setEditable(false);
		lon.setBounds(140, 92, 141, 20);
		contentPane.add(lon);
		lon.setColumns(10);
		
		JLabel lblMacAddress = new JLabel("MAC Address:");
		lblMacAddress.setBounds(160, 10, 107, 14);
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
