package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.xml.bind.v2.model.core.ID;

import Libraries.read;
import objects.DB;
import objects.DBStack;
import objects.Server;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

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
	private Server server = new Server();
	private JComboBox FilterType;
	private JComboBox operator;
	private JCheckBox chckbxUseNotOperator;
	private JMenuItem mntmPreviewData;
	private JTable table;
	private Object[] tableColumns = {"time","id","lon","lat","alt","size","mac0","ssid0","channel0","signal0","mac1","ssid1","channel1","signal1",
			"mac2","ssid2","channel2","signal2",
			"mac3","ssid3","channel3","signal3",
			"mac4","ssid4","channel4","signal4",
			"mac5","ssid5","channel5","signal5",
			"mac6","ssid6","channel6","signal6",
			"mac7","ssid7","channel7","signal7",
			"mac8","ssid8","channel8","signal8",
			"mac9","ssid9","channel9","signal9"};
	private JMenuItem mntmFilter;
	private JMenuItem mntmCurentFilter;


	

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
		frame.setBounds(100, 100, 608, 402);
		Color bgColor = new Color(16737894);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnFilter = new JButton("Filter");
		frame.getContentPane().add(btnFilter, BorderLayout.WEST);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(bgColor);

		JButton btnAddFilter = new JButton("Add Filter");
		btnAddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] Array = new String[6];
				String filterType = (String) FilterType.getSelectedItem();
				String operatorStr = (String) operator.getSelectedItem();
				boolean isNot = chckbxUseNotOperator.isSelected();
				if(filterType != null&&operatorStr!= null){
					if (operatorStr.equals("AND")) Array[0] = "0";
					else Array[0] = "1";
					if(isNot) Array[1] = "0";
					else Array[1] = "1";
					if(filterType.equals("Time")){
						Array[2] = StartTime.getText();
						Array[3] = EndTime.getText();
					}
					else if(filterType.equals("ID")){
						Array[2] = IDSubString.getText();
					}
					else if (filterType.equals( "Location")){
						Array[2] = MinCoorLon.getText();
						Array[3] = MinCoorLat.getText();
						Array[4] = MaxCoorLon.getText();
						Array[5] = MaxCoorLat.getText();
					}
				}
				System.out.println(Arrays.toString(Array));
				server.filter(Array);
				updateDataSheet();
			}
		});
		btnAddFilter.setBounds(483, 308, 89, 23);
		frame.getContentPane().add(btnAddFilter);

		

		
		
		DataSheet = new JTextArea();
		DataSheet.setEditable(false);
		DataSheet.setBackground(new Color(10079232));
		DataSheet.setBounds(0, 33, 228, 309);
		frame.getContentPane().add(DataSheet);
		DataSheet.setColumns(10);
		
		JButton btnResetDb = new JButton("Reset DB");
		btnResetDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.getDbs().clearAll();
				updateDataSheet();
			}
		});
		btnResetDb.setBounds(245, 308, 89, 23);
		frame.getContentPane().add(btnResetDb);
		
		JButton btnUndoLastFilter = new JButton("Undo last filter");
		btnUndoLastFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.undo();
				updateDataSheet();
			}
		});
		btnUndoLastFilter.setBounds(344, 308, 129, 23);
		frame.getContentPane().add(btnUndoLastFilter);
		
		JLabel lblCurrentData = new JLabel("Current Data");
		lblCurrentData.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentData.setBounds(63, 8, 121, 23);
		frame.getContentPane().add(lblCurrentData);
		
		String[] operatorOptions = {"AND", "OR"};
		operator = new JComboBox(operatorOptions);
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
		
		chckbxUseNotOperator = new JCheckBox("Use NOT operator");
		chckbxUseNotOperator.setBounds(338, 278, 135, 23);
		frame.getContentPane().add(chckbxUseNotOperator);
		
		lblMinCoordinate = new JLabel("Min coordinate:");
		lblMinCoordinate.setBounds(265, 87, 89, 14);
		frame.getContentPane().add(lblMinCoordinate);
		
		lblMaxCoordinate = new JLabel("Max coordinate:");
		lblMaxCoordinate.setBounds(265, 118, 95, 14);
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
		FilterType = new JComboBox(options);
		FilterType.setRenderer(new MyComboBoxRenderer("Choose filter"));
		FilterType.setSelectedIndex(-1);
		FilterType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filType = (String)FilterType.getSelectedItem();
				String operatorStr = (String) operator.getSelectedItem();
				if(operatorStr!=null){
					if(filType.equals("Time")){
						ShowTimeFilterObjects();
					}
					else if(filType.equals("ID")){
						ShowIDFilterObjects();
					}
					else if (filType.equals("Location")){
						ShowLocationFilterObjects();
					}
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
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StartTime = new JFormattedTextField(format);
		StartTime.setToolTipText("please enter time in format yyyy-mm-dd hh:mm:ss");
		StartTime.setEditable(false);
		StartTime.setBounds(369, 164, 86, 20);
		frame.getContentPane().add(StartTime);
		StartTime.setColumns(10);
		
		EndTime = new JTextField();
		EndTime.setToolTipText("please enter time in format yyyy-mm-dd hh:mm:ss");
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
		
		JMenu mnAddData = new JMenu("Import");
		menuBar.add(mnAddData);
		
		JMenuItem mntmAddFolder = new JMenuItem("Folder (wigle files)");
		mntmAddFolder.setToolTipText("accept only folder with wigle files");
		mntmAddFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(mntmAddFolder);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					server.addWigleFolder(fc.getSelectedFile().getAbsolutePath());
				}
				updateDataSheet();
			}

			
		});
		mnAddData.add(mntmAddFolder);
		
		JMenuItem mntmAddFile = new JMenuItem("File (combo file)");
		mntmAddFile.setToolTipText("accept only combo csv file");
		mntmAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV files", "csv");
				fc.setFileFilter(filter);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(mntmAddFile);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					server.addCombFile(fc.getSelectedFile());
					
				}
				updateDataSheet();
			}
		});
		mnAddData.add(mntmAddFile);
		
		mntmFilter = new JMenuItem("Filter");
		mntmFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAddData.add(mntmFilter);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmToCsv = new JMenuItem("To CSV");
		mntmToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showSaveDialog(mntmToCsv);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					server.addWigleFolder(fc.getSelectedFile().getAbsolutePath());
				}	
			}
		});
		
		mntmPreviewData = new JMenuItem("preview data");
		mntmPreviewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame Tableframe = new JFrame();
				table = new JTable(server.getDbs().peek().toTable(),tableColumns);
				JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				Tableframe.getContentPane().add(scrollPane, BorderLayout.CENTER);
				Tableframe.setSize(1000, 500);
				Tableframe.setVisible(true);
			}
		});
		mnExport.add(mntmPreviewData);
		mnExport.add(mntmToCsv);
		
		JMenuItem mntmToKml = new JMenuItem("To KML");
		mntmToKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  kmlFc = new JFileChooser();
				kmlFc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = kmlFc.showSaveDialog(mntmToKml);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					DB dbToKml = server.getDbs().peek();
					dbToKml.removeDuplicateMac();
					dbToKml.toKML(kmlFc.getSelectedFile());
				}	
			}
		});
		mnExport.add(mntmToKml);
		
		mntmCurentFilter = new JMenuItem("Current filter");
		mntmCurentFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnExport.add(mntmCurentFilter);
		
		
		
		
		JMenu mnAlgorithms = new JMenu("Algorithms");
		menuBar.add(mnAlgorithms);
		
		JMenuItem mntmAssessMacLocation = new JMenuItem("Assess MAC Location");
		mntmAssessMacLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Algo1Window a1 = new Algo1Window(server.getDbs().get(0));
				a1.setVisible(true);
			}
		});
		mnAlgorithms.add(mntmAssessMacLocation);
		
		JMenu mnAssessScanLocation = new JMenu("Assess Scan Location");
		mnAlgorithms.add(mnAssessScanLocation);
		
		JMenuItem mntmByScanString = new JMenuItem("By scan string");
		mntmByScanString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Algo2WindowStr a2s = new Algo2WindowStr(server.getDbs().get(0));
				a2s.setVisible(true);
			}
		});
		mnAssessScanLocation.add(mntmByScanString);
		
		JMenuItem mntmByListOf =new JMenuItem("By list of macs");
		mntmByListOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAssessScanLocation.add(mntmByListOf);
		

	}
	private void updateDataSheet() {
		String dataSheetStr = "DB Data:\n  Number of records: " + server.getDbs().peek().getScansList().size() + "\n  Number of wifi spots: "+ server.getDbs().peek().getMACAmount()+"\n\nFilter Data:\n  All DB\n"+server.getFs().toString();
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
