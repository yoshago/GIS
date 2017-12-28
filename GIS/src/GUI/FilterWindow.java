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
import java.awt.event.ActionEvent;
import java.awt.Choice;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.SqlDateModel;

public class FilterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilterWindow frame = new FilterWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FilterWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFilterByTime = new JButton("Filter by Time");
		btnFilterByTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnFilterByTime.setBounds(40, 207, 110, 23);
		contentPane.add(btnFilterByTime);
		
		JTextField startTime = new JTextField();
		startTime.setBounds(160, 209, 90, 23);
		contentPane.add(startTime);
		startTime.setColumns(10);
		
		JLabel lblStart = new JLabel("Start Time");
		lblStart.setBounds(180, 187, 90, 14);
		contentPane.add(lblStart);
		
		
		JTextField endTime = new JTextField();
		endTime.setColumns(10);
		endTime.setBounds(260, 209, 90, 23);
		contentPane.add(endTime);
		
		JLabel lblEnd = new JLabel("End Time");
		lblEnd.setBounds(280, 187, 90, 14);
		contentPane.add(lblEnd);
		
		JButton btnIDFilter = new JButton("Filter by ID");
		btnIDFilter.setBounds(40, 137, 110, 23);
		contentPane.add(btnIDFilter);
		
		JTextField IDSubString = new JTextField();
		IDSubString.setColumns(10);
		IDSubString.setBounds(160, 139, 90, 23);
		contentPane.add(IDSubString);
		
		JLabel label = new JLabel("ID Sub String");
		label.setBounds(180, 117, 90, 14);
		contentPane.add(label);
		
		
		JLabel label_2 = new JLabel("End Time");
		label_2.setBounds(280, 42, 90, 14);
		contentPane.add(label_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(260, 64, 90, 23);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(160, 64, 90, 23);
		contentPane.add(textField_5);
		
		JLabel label_3 = new JLabel("Start Time");
		label_3.setBounds(180, 42, 90, 14);
		contentPane.add(label_3);
		
		JButton button_1 = new JButton("Filter by Time");
		button_1.setBounds(40, 62, 110, 23);
		contentPane.add(button_1);
	}
}
