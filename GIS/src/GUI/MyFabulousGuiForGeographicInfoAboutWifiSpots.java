package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.xml.bind.v2.model.core.ID;

import Libraries.read;
import objects.DB;
import objects.DBStack;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class MyFabulousGuiForGeographicInfoAboutWifiSpots {

	private JFrame frame;
	private JTextArea DataSheet;
	private JTextField MinCoorLon;
	private JTextField MinCoorLat;
	private JTextField MaxCoorLon;
	private JTextField MaxCoorLat;
	private JTextField StartTime;
	private JTextField EndTime;
	private JTextField IDSubString;
	private JLabel lblMinCoordinate;
	private JLabel lblMaxCoordinate;
	private JLabel lblLongtitude;
	private JLabel lblLatitude;
	private JLabel lblStartTime;
	private JLabel lblEndTime;
	private JLabel lblTimeSpace;
	private JLabel lblIdSubstring;
	DB db =new DB();
	private DBStack DBS = new DBStack(db);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFabulousGuiForGeographicInfoAboutWifiSpots window = new MyFabulousGuiForGeographicInfoAboutWifiSpots();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFabulousGuiForGeographicInfoAboutWifiSpots() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnFilter = new JButton("Filter");
		frame.getContentPane().add(btnFilter, BorderLayout.WEST);
		frame.getContentPane().setLayout(null);

		JButton btnAddFilter = new JButton("Add Filter");
		btnAddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilterWindow FilterW = new FilterWindow();
				FilterW.setVisible(true);
			}
		});
		btnAddFilter.setBounds(462, 308, 89, 23);
		frame.getContentPane().add(btnAddFilter);

		

		
		
		DataSheet = new JTextArea();
		DataSheet.setEditable(false);
		DataSheet.setBounds(0, 33, 228, 309);
		frame.getContentPane().add(DataSheet);
		DataSheet.setColumns(10);
		
		JButton btnResetDb = new JButton("Reset DB");
		btnResetDb.setBounds(245, 308, 89, 23);
		frame.getContentPane().add(btnResetDb);
		
		JButton btnUndoLastFilter = new JButton("Undo last filter");
		btnUndoLastFilter.setBounds(344, 308, 111, 23);
		frame.getContentPane().add(btnUndoLastFilter);
		
		JLabel lblCurrentData = new JLabel("Current Data");
		lblCurrentData.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentData.setBounds(63, 8, 121, 23);
		frame.getContentPane().add(lblCurrentData);
		
		String[] operatorOptions = {"AND", "OR"};
		JComboBox operator = new JComboBox(operatorOptions);
		operator.setRenderer(new MyComboBoxRenderer("Choose operator"));
		operator.setSelectedIndex(-1);
		operator.setBounds(245, 33, 127, 20);
		frame.getContentPane().add(operator);
		
		JLabel lblFilterOperator = new JLabel("Filter operator");
		lblFilterOperator.setBounds(245, 15, 80, 14);
		frame.getContentPane().add(lblFilterOperator);
		
		MinCoorLon = new JTextField();
		MinCoorLon.setEditable(false);
		MinCoorLon.setBounds(369, 84, 86, 20);
		frame.getContentPane().add(MinCoorLon);
		MinCoorLon.setColumns(10);
		
		
		MinCoorLat = new JTextField();
		MinCoorLat.setEditable(false);
		MinCoorLat.setBounds(465, 84, 86, 20);
		frame.getContentPane().add(MinCoorLat);
		MinCoorLat.setColumns(10);
		
		JCheckBox chckbxUseNotOperator = new JCheckBox("Use NOT operator");
		chckbxUseNotOperator.setBounds(338, 278, 135, 23);
		frame.getContentPane().add(chckbxUseNotOperator);
		
		lblMinCoordinate = new JLabel("Min coordinate:");
		lblMinCoordinate.setBounds(265, 87, 89, 14);
		frame.getContentPane().add(lblMinCoordinate);
		
		lblMaxCoordinate = new JLabel("Max coordinate:");
		lblMaxCoordinate.setBounds(265, 118, 89, 14);
		frame.getContentPane().add(lblMaxCoordinate);
		
		MaxCoorLon = new JTextField();
		MaxCoorLon.setEditable(false);
		MaxCoorLon.setColumns(10);
		MaxCoorLon.setBounds(369, 115, 86, 20);
		frame.getContentPane().add(MaxCoorLon);
		
		MaxCoorLat = new JTextField();
		MaxCoorLat.setEditable(false);
		MaxCoorLat.setColumns(10);
		MaxCoorLat.setBounds(465, 115, 86, 20);
		frame.getContentPane().add(MaxCoorLat);
		
		String[] options = {"Time", "ID", "Location"};
		JComboBox FilterType = new JComboBox(options);
		FilterType.setRenderer(new MyComboBoxRenderer("Choose filter"));
		FilterType.setSelectedIndex(-1);
		FilterType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String)FilterType.getSelectedItem()== "Time"){
					ShowTimeFilterObjects();
				}
				else if((String)FilterType.getSelectedItem()== "ID"){
					ShowIDFilterObjects();
				}
				else if ((String)FilterType.getSelectedItem()== "Location"){
					ShowLocationFilterObjects();
				}
			}
		});
		FilterType.setBounds(398, 33, 111, 20);
		frame.getContentPane().add(FilterType);
		
		JLabel lblFilterType = new JLabel("Filter type");
		lblFilterType.setBounds(398, 15, 75, 14);
		frame.getContentPane().add(lblFilterType);
		
		lblLongtitude = new JLabel("longtitude");
		lblLongtitude.setBounds(369, 64, 65, 14);
		frame.getContentPane().add(lblLongtitude);
		
		lblLatitude = new JLabel("latitude");
		lblLatitude.setBounds(465, 64, 65, 14);
		frame.getContentPane().add(lblLatitude);
		
		StartTime = new JTextField();
		StartTime.setEditable(false);
		StartTime.setBounds(369, 164, 86, 20);
		frame.getContentPane().add(StartTime);
		StartTime.setColumns(10);
		
		EndTime = new JTextField();
		EndTime.setEditable(false);
		EndTime.setColumns(10);
		EndTime.setBounds(465, 164, 86, 20);
		frame.getContentPane().add(EndTime);
		
		lblStartTime = new JLabel("Start time");
		lblStartTime.setBounds(369, 146, 65, 14);
		frame.getContentPane().add(lblStartTime);
		
		lblEndTime = new JLabel("End time");
		lblEndTime.setBounds(465, 146, 65, 14);
		frame.getContentPane().add(lblEndTime);
		
		lblTimeSpace = new JLabel("Time space:");
		lblTimeSpace.setBounds(265, 167, 89, 14);
		frame.getContentPane().add(lblTimeSpace);
		
		IDSubString = new JTextField();
		IDSubString.setEditable(false);
		IDSubString.setColumns(10);
		IDSubString.setBounds(369, 213, 86, 20);
		frame.getContentPane().add(IDSubString);
		
		lblIdSubstring = new JLabel("ID SubString");
		lblIdSubstring.setBounds(369, 195, 104, 14);
		frame.getContentPane().add(lblIdSubstring);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAddData = new JMenu("Add data");
		menuBar.add(mnAddData);
		
		JMenuItem mntmAddFolder = new JMenuItem("Add folder");
		mntmAddFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(mntmAddFolder);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					DBS.get(0).add(read.readFolder(fc.getSelectedFile().listFiles()));
				}
				updateDataSheet();
			}

			
		});
		mnAddData.add(mntmAddFolder);
		
		JMenuItem mntmAddFile = new JMenuItem("Add file");
		mntmAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV files", "csv");
				fc.setFileFilter(filter);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(mntmAddFile);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					read.readOutputFolderFile(fc.getSelectedFile(),DBS.get(0).getScansList());
					
				}
				updateDataSheet();
			}
		});
		mnAddData.add(mntmAddFile);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmToCsv = new JMenuItem("To CSV");
		mnExport.add(mntmToCsv);
		
		JMenuItem mntmToKml = new JMenuItem("To KML");
		mnExport.add(mntmToKml);
		

	}
	private void updateDataSheet() {
		String dataSheetStr = "Number of records: " + DBS.peek().getScansList().size() + "\n\nNumber of wifi spots: "+ DBS.peek().getNumberOfWifiSpots();
		this.DataSheet.setText(dataSheetStr);
//		this.DataSheet.setCaretPosition(position);
		
	}
	private void ShowTimeFilterObjects(){
		this.MinCoorLon.setEditable(false);
		this.MinCoorLat.setEditable(false);
		this.MaxCoorLon.setEditable(false);
		this.MaxCoorLat.setEditable(false);
		this.StartTime.setEditable(true);
		this.EndTime.setEditable(true);
		this.IDSubString.setEditable(false);
	}
	private void ShowIDFilterObjects(){
		this.MinCoorLon.setEditable(false);
		this.MinCoorLat.setEditable(false);
		this.MaxCoorLon.setEditable(false);
		this.MaxCoorLat.setEditable(false);
		this.StartTime.setEditable(false);
		this.EndTime.setEditable(false);
		this.IDSubString.setEditable(true);
	}
	private void ShowLocationFilterObjects(){
		this.MinCoorLon.setEditable(true);
		this.MinCoorLat.setEditable(true);
		this.MaxCoorLon.setEditable(true);
		this.MaxCoorLat.setEditable(true);
		this.StartTime.setEditable(false);
		this.EndTime.setEditable(false);
		this.IDSubString.setEditable(false);
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
