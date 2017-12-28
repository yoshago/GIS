package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class FilterById extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String ID;
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	private String Operator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FilterById dialog = new FilterById();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FilterById() {
		setBounds(100, 100, 250, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		
		JTextField IDSubString = new JTextField();
		IDSubString.setColumns(10);
		IDSubString.setBounds(124, 36, 96, 20);
		contentPanel.add(IDSubString);
		
		JLabel label = new JLabel("ID Sub String");
		label.setBounds(136, 16, 83, 14);
		contentPanel.add(label);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		String[] operators = {"AND","OR","NOT"};
		JComboBox OperatrorBox = new JComboBox(operators);
		
		
		
		
		OperatrorBox.setBounds(31, 36, 83, 20);
		contentPanel.add(OperatrorBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ID = IDSubString.getText();
						Operator = (String)OperatrorBox.getSelectedItem();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
