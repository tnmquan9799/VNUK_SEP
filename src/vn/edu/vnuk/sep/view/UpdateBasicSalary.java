package vn.edu.vnuk.sep.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.edu.vnuk.sep.define.Define;

@SuppressWarnings("serial")
public class UpdateBasicSalary extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNewBasicSalary;
	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBasicSalary frame = new UpdateBasicSalary();
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
	public UpdateBasicSalary() {
		setTitle("Update Basic Salary");
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 361, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNewBasicSalary = new JTextField();
		textFieldNewBasicSalary.setBounds(180, 27, 114, 20);
		contentPane.add(textFieldNewBasicSalary);
		textFieldNewBasicSalary.setColumns(10);
		
		JLabel lbNewBasicSalary = new JLabel("New Basic Salary:");
		lbNewBasicSalary.setBounds(39, 27, 114, 20);
		contentPane.add(lbNewBasicSalary);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Define.newMinimumWage = Float.valueOf(textFieldNewBasicSalary.getText());
				JOptionPane.showMessageDialog(frame, "UPDATE NEW BASIC SALARY SUCCESSFULLY!");

			}
		});
		btnUpdate.setBounds(125, 88, 89, 23);
		contentPane.add(btnUpdate);
		
	}
}
