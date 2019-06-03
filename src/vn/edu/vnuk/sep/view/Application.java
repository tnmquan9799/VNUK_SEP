package vn.edu.vnuk.sep.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import vn.edu.vnuk.sep.controller.Controller;
import vn.edu.vnuk.sep.dao.CasualWorkerDao;
import vn.edu.vnuk.sep.dao.LecturerDao;
import vn.edu.vnuk.sep.dao.PersonDao;
import vn.edu.vnuk.sep.dao.StaffDao;
import vn.edu.vnuk.sep.define.Define;
import vn.edu.vnuk.sep.model.CasualWorker;
import vn.edu.vnuk.sep.model.Lecturer;
import vn.edu.vnuk.sep.model.Person;
import vn.edu.vnuk.sep.model.Staff;
import vn.edu.vnuk.sep.util.CommonUtils;

@SuppressWarnings("serial")
public class Application extends JFrame {
	private JTextField tbxSearching;
	private JComboBox<String> cbxSearchingChoices;
	private JButton btnSearch;
	private JTable table;
	private JPopupMenu popup;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(new PersonDao().read(1L).toString());
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public void reloadTableData() {
		DefaultTableModel model = null;
		String keyword ="";
				try {
					model = new DefaultTableModel(loadRowData(keyword), loadColumnNames());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		table.setModel(model);
	}
	
	public void searchTableData() {
		DefaultTableModel model = null;
		String keyword = tbxSearching.getText();
		String sortBy = cbxSearchingChoices.getSelectedItem().toString();
		
		try {
			switch (sortBy) {
			case "Name":
				model = new DefaultTableModel(CommonUtils.sortByName(loadRowData(keyword)), loadColumnNames());
				break;
			case "Salary":
				model = new DefaultTableModel(CommonUtils.sortBySalary(loadRowData(keyword)), loadColumnNames());
				break;
			}
				
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		table.setModel(model);
	
	}
	
	public Application() throws SQLException {
		setTitle("Homepage");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 583);
		getContentPane().setLayout(null);
		String[] searchingChoices = {"Name", "Salary"};
		cbxSearchingChoices = new JComboBox(searchingChoices);
		cbxSearchingChoices.setBounds(26, 23, 118, 24);	
		getContentPane().add(cbxSearchingChoices);
		
		tbxSearching = new JTextField();
		tbxSearching.setBounds(178, 23, 438, 24);
		getContentPane().add(tbxSearching);
		tbxSearching.setColumns(10);
		
		btnSearch = new JButton("Apply/Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTableData();
			}
		});
		btnSearch.setBounds(654, 23, 117, 25);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 74, 745, 336);
		getContentPane().add(scrollPane);
		
		table = new JTable(loadRowData(""), loadColumnNames()) {
			public boolean isCellEditable(int row,int column){  
				return false;
		    }
		
		};
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		JButton btnAddEmployee = new JButton("ADD EMPLOYEE");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewEmployee addNewEmployeeFrame = new AddNewEmployee(new Person(), Define.TYPE_OF_ACTION_CREATE);
				addNewEmployeeFrame.setSize(500, 510);
				centreWindow(addNewEmployeeFrame);
				addNewEmployeeFrame.setVisible(true);
			}
		});
		btnAddEmployee.setBounds(26, 473, 174, 23);
		getContentPane().add(btnAddEmployee);
		
		JButton btnUpdateBasicSalary = new JButton("UPDATE BASIC SALARY");
		btnUpdateBasicSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateBasicSalary updateBasicSalary = new UpdateBasicSalary();
				centreWindow(updateBasicSalary);
				updateBasicSalary.setVisible(true);
			}
		});
		btnUpdateBasicSalary.setBounds(323, 473, 208, 23);
		getContentPane().add(btnUpdateBasicSalary);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				 //int a=JOptionPane.showConfirmDialog(f,"Are you sure?");  
				 //if(a==JOptionPane.YES_OPTION){  
				   //  f.(JFrame.EXIT_ON_CLOSE);  
				 //}
				 Component component = (Component) e.getSource();
			        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
			      int a=JOptionPane.showConfirmDialog(frame,"Are you sure?");  
					if(a==JOptionPane.YES_OPTION){  
						System.exit(0);    
					 }   
			}
		});
		btnExit.setBounds(682, 473, 89, 23);
		getContentPane().add(btnExit);
		
		JButton btnReloadTable = new JButton("Reload Table");
		btnReloadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadTableData();
			}
		});
		btnReloadTable.setBounds(656, 421, 115, 23);
		getContentPane().add(btnReloadTable);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddNewEmployee = new JMenuItem("Add new employee");
		mntmAddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewEmployee addNewEmployeeFrame = new AddNewEmployee(new Person(), Define.TYPE_OF_ACTION_CREATE);
				addNewEmployeeFrame.setSize(500, 510);
				centreWindow(addNewEmployeeFrame);
				addNewEmployeeFrame.setVisible(true);
			}
		});
		mnFile.add(mntmAddNewEmployee);
		
		JMenuItem mntmUpdateBasicSalary = new JMenuItem("Update basic salary");
		mntmUpdateBasicSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateBasicSalary updateBasicSalary = new UpdateBasicSalary();
				centreWindow(updateBasicSalary);
				updateBasicSalary.setVisible(true);
			}
		});
		mnFile.add(mntmUpdateBasicSalary);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instructions instructions = new Instructions();
				centreWindow(instructions);
				instructions.setVisible(true);
			}
		});
		mnHelp.add(mntmInstructions);
		
		popup = new JPopupMenu();
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		deleteMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete");
				try {
					switch(getTypeSelected()) {
					case Define.TYPE_OF_LECTURER:
						new LecturerDao().delete(getPersonIDSelected());
						break;
					
					case Define.TYPE_OF_STAFF:
						new StaffDao().delete(getPersonIDSelected());
						break;
					
					case Define.TYPE_OF_CASUAL_WORKER:
						new CasualWorkerDao().delete(getPersonIDSelected());
						break;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				reloadTableData();
			}
		});
		
		JMenuItem viewMenuItem = new JMenuItem("View details");
		viewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Person person = null;
				try {
					person = new PersonDao().read(getPersonIDSelected());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddNewEmployee addNewEmployee = new AddNewEmployee(person, Define.TYPE_OF_ACTION_EDIT);
				addNewEmployee.setSize(500, 510);
				centreWindow(addNewEmployee);	
				addNewEmployee.setVisible(true);
			}
		});
		
		popup.add(deleteMenuItem);
		popup.add(viewMenuItem);
		
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	private int getTypeSelected() {
		int result = 0;
		switch ((String) table.getValueAt(table.getSelectedRow(), 1)) {
		case "LECTURER":
			result = Define.TYPE_OF_LECTURER;
			break;
			
		case "STAFF":
			result = Define.TYPE_OF_STAFF;
			break;
			
		case "CASUAL WORKER":
			result = Define.TYPE_OF_CASUAL_WORKER;
			break;
		}
		
		return result;
	}
	
	private long getPersonIDSelected() {
		return (long) table.getValueAt(table.getSelectedRow(), 0);
	}
	
	private Object[] loadColumnNames() {
		return new Object[] {"ID", "Type", "Name", "Salary"};
	}
	
	private Object[][] loadRowData(String keyword) throws SQLException {
		Controller controller = new Controller();
		ArrayList<Person> persons = controller.getAllPersons(keyword);
		
		Object[][] obj = new Object[persons.size()][4];
		
		for (int index = 0; index < persons.size(); index++) {
			obj[index][0] = persons.get(index).getId();
			obj[index][1] = CommonUtils.getTypeString(persons.get(index).getType());
			obj[index][2] = persons.get(index).getName();
			
			switch (persons.get(index).getType()) {
			case Define.TYPE_OF_STAFF: {
				obj[index][3] = ((Staff) persons.get(index)).getSalary();
				break;
			}
			
			case Define.TYPE_OF_LECTURER: {
				obj[index][3] = ((Lecturer) persons.get(index)).getSalary();
				break;
			}
			
			case Define.TYPE_OF_CASUAL_WORKER: {
				obj[index][3] = ((CasualWorker) persons.get(index)).getSalary();
				break;
			}
			}
		}
		
		return obj;
	}
}
