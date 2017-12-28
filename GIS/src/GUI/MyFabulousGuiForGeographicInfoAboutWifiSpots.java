package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.xml.bind.v2.model.core.ID;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MyFabulousGuiForGeographicInfoAboutWifiSpots {

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 300);
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
		btnAddFilter.setBounds(317, 211, 89, 23);
		frame.getContentPane().add(btnAddFilter);

		JButton btnImportWigleCsv = new JButton("import wigle csv");
		btnImportWigleCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(btnImportWigleCsv);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							fc.getSelectedFile().getPath());
				}
			}
		});
		btnImportWigleCsv.setBounds(297, 26, 127, 23);
		frame.getContentPane().add(btnImportWigleCsv);

		JButton btnImportComboCsv = new JButton("import combo csv");
		btnImportComboCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser  fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV files", "csv");
				fc.setFileFilter(filter);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(btnImportWigleCsv);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							fc.getSelectedFile().getPath());
				}
			}
		});
		btnImportComboCsv.setBounds(297, 63, 127, 23);
		frame.getContentPane().add(btnImportComboCsv);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Filter");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("By ID");
		FilterById id = new FilterById();
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				id.setVisible(true);
				while(id.getID()==null){
					
				}
				id.setVisible(false);
				frame.setVisible(true);
				
			}
		});
		System.out.println(id.getID());
		System.out.println(id.getOperator());

		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("By Location");

		mnNewMenu.add(mntmNewMenuItem_1);

	}
}
