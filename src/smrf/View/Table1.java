package smrf.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.*;

public class Table1 extends JFrame{

	private JScrollPane scrollPane;
	private JTextField textResName;
	private JTextField textResSeq;
	private JTable jtable;
	DefaultTableModel model;
	DefaultListModel<String> modelList;
	Object[] row = new Object[2];
	public int index;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnSetDefault;
	private JButton btnLoad;
	private JButton btnDeleteList;
	private JButton btnAddList;
	private JList<String> list;
	public static String filepathCurrent;
	private JLabel lblPackageName;
	/**
	 * Launch the application.
	 */
	public String filepath(int a) {
		String filepath = list.getModel().getElementAt(a).toString() + ".txt";
		return filepath;
		
	}
	public void newfile(String filePath) {
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void delete(String filePath) {
		File file = new File(filePath);
		file.delete();
		
	}
	public int loadDefault() {
		String filePath = "default index.txt";
		File file = new File(filePath);
		int index = 0;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			//DefaultTableModel model = (DefaultTableModel) jtable.getModel();
			//Object[] lines = br.lines().toArray();
			index = Integer.parseInt(br.readLine().toString()) ;
			   
			br.close();
			fr.close();
			
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
		return index;
	    
	}
	public void saveDefault(int a) {
		String filePath = "default index.txt";
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(a));
			
	            
	        bw.close();
	        fw.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	}
	public void loadlist() {
		String filePath = "packages names.txt";
		File file = new File(filePath);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line= br.readLine()) != null) {
			     //line = br.readLine().trim();
			     if(line != null) {
			           modelList.addElement(line.toString());;
			     }
			}
			br.close();
			fr.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	}
	public void savelist() {
		String filePath = "packages names.txt";
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < list.getModel().getSize(); i++){//rows
				bw.write(list.getModel().getElementAt(i).toString());
				bw.newLine();
			}
			
	            
	            bw.close();
	            fw.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	}
	public void cleartable() {
		int rowcount = jtable.getRowCount();
		for(int i = rowcount-1; i>=0; i--) {
			model.removeRow(i);
		}
	}
	public void Savetable(String filePath) {
		//String filePath = "restriction sites save1.txt" ;
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < jtable.getRowCount(); i++){//rows
				for(int j = 0; j < jtable.getColumnCount(); j++){//columns
					bw.write(jtable.getValueAt(i, j).toString()+",");
	                }
				bw.newLine();
			}
			
	            
	            bw.close();
	            fw.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	        
	}  
	
	public void loadtable(String filePath) {
		File file = new File(filePath);
		cleartable();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			//DefaultTableModel model = (DefaultTableModel) jtable.getModel();
			//Object[] lines = br.lines().toArray();
			String line;
			while((line= br.readLine()) != null) {
			     //line = br.readLine().trim();
			     if(line != null) {
			           String[] dataRow = line.split(",");
			           model.addRow(dataRow);
			     }
			}
			updateRowHeights();
			//for(int i = 0; i < lines.length; i++ ) {
				//String[] row = lines[i].toString().split(",");
				//model.addRow(row);
			//}
			
			
	            
			br.close();
			fr.close();
			lblPackageName.setText(list.getModel().getElementAt(list.getSelectedIndex())+ ":");
	            
		} catch (IOException ex) {
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	        
	}
	private void updateRowHeights()
	{
	    for (int row = 0; row < jtable.getRowCount(); row++)
	    {
	        int rowHeight = jtable.getRowHeight();

	        for (int column = 0; column < jtable.getColumnCount(); column++)
	        {
	            Component comp = jtable.prepareRenderer(jtable.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        jtable.setRowHeight(row, rowHeight);
	    }
	}
	public void saveCurrentUnpacked(int a) {
		String filePath = "Current Loaded Package.txt";
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(a));
			
	            
	        bw.close();
	        fw.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(FirstGUI.class.getName()).log(Level.SEVERE, null, ex);
	      }
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table1 frame = new Table1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Table1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Savetable(filepathCurrent);
				smrf.View.JframeModal.f1.enable(true);
			}
		});
		
		initialize();
		events();
	}

	private void events() {
		// TODO Auto-generated method stub
		list.setSelectedIndex(loadDefault());
		String filepath = list.getModel().getElementAt(list.getSelectedIndex()).toString() + ".txt";
		filepathCurrent = filepath;
		loadtable(filepath);
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = jtable.getSelectedRow();
				textResName.setText(jtable.getValueAt(selectedrow, 0).toString());
				textResSeq.setText(jtable.getValueAt(selectedrow, 1).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() == 0) {
					JOptionPane.showInternalMessageDialog(null, "You can not edit the Default package");
				}
				else if (textResName.getText().equals("") || textResName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields before adding data");
				}
				else if (textResName.getText().equals("WHO IS") && textResSeq.getText().equals("MENNA?")) {
					row[0]= "THE MOST BEUTIFULL";
					row[1]= "GIRL IN THE WORLD";
					model.addRow(row);
					textResName.setText("");
					textResSeq.setText("");
					saveCurrentUnpacked(10000000);
				}
				else {
					row[0]= textResName.getText();
					row[1]= textResSeq.getText();
					model.addRow(row);
					textResName.setText("");
					textResSeq.setText("");
					saveCurrentUnpacked(10000000);
					
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = jtable.getSelectedRow();
				if (list.getSelectedIndex() == 0) {
					JOptionPane.showInternalMessageDialog(null, "You can not edit the Default package");
				}
				else if (selectedrow >= 0) {
					model.removeRow(selectedrow);
					textResName.setText("");
					textResSeq.setText("");
					saveCurrentUnpacked(10000000);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a row to delete");
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = jtable.getSelectedRow();
				if (list.getSelectedIndex() == 0) {
					JOptionPane.showInternalMessageDialog(null, "You can not edit the Default package");
				}
				else if (textResName.getText().equals("") || textResName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields before Updating data");
				}
				else if(selectedrow>=0) {
					model.setValueAt(textResName.getText(), selectedrow, 0);
					model.setValueAt(textResSeq.getText(), selectedrow, 1);
					saveCurrentUnpacked(10000000);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a row to Update");
					
				}
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResName.setText("");
				textResSeq.setText("");
			}
		});
		btnAddList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String packageName = JOptionPane.showInputDialog( "What is the title of your new package? ");
				if (packageName != null) {
					String filepath = packageName + ".txt";
					newfile(filepath);
					modelList.addElement(packageName);
					savelist();
					
				}
				
			}
		});
		btnDeleteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedindex = list.getSelectedIndex();
				if (selectedindex == 0) {
					JOptionPane.showMessageDialog(null, "You cannot delete the default package!");
				}
				else if (selectedindex > 0) {
					int delete = JOptionPane. showConfirmDialog(null, "Are you sure you want to delete? ");
					if (delete ==0) {
						if (selectedindex == loadDefault()) {
							saveDefault(0);
						}
						else if (selectedindex < loadDefault()) {
							saveDefault(loadDefault()-1);
						}
						String filepath = list.getModel().getElementAt(list.getSelectedIndex()).toString() + ".txt";
						delete(filepath);
						modelList.remove(selectedindex);
						savelist();
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Package to delete");
				}
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()>=0) {
					Savetable(filepathCurrent);
					String filepath = list.getModel().getElementAt(list.getSelectedIndex()).toString() + ".txt";
					loadtable(filepath);
					filepathCurrent = filepath;
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a Package to Load");
				}
				
			}
		});
		btnSetDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDefault(list.getSelectedIndex());
			}
		});
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Table1.class.getResource("/smrf/resources/table.png")));
		setTitle("Modify Restriction Enzyme List");
		setBounds(100, 100, 920, 625);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Object[] columnsNames = {
				"Restriction Enzyme", "Restriction Site Sequence"	
			};
		model = new DefaultTableModel(columnsNames,0);
		modelList = new DefaultListModel<String>();
		//model.setColumnIdentifiers(columnsNames);
		
		JLabel lblResName = new JLabel("Restriction site name:");
		lblResName.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textResName = new JTextField();
		textResName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textResName.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		
		jtable = new JTable();
		jtable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		jtable.setGridColor(Color.LIGHT_GRAY);
		jtable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//jtable.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
		
		
		
		
		jtable.setBorder(null);
		jtable.setBackground(Color.WHITE);
		jtable.setModel(model);
		scrollPane.setViewportView(jtable);
		
		JLabel lblResSeq = new JLabel("Restriction site sequence:");
		lblResSeq.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textResSeq = new JTextField();
		textResSeq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textResSeq.setColumns(10);
		
		btnAdd = new JButton("Add");
		
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		btnDelete = new JButton("Delete");
		
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		btnUpdate = new JButton("Update");
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		btnClear = new JButton("Clear");
		
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblEnzymePackage = new JLabel("Enzyme package:");
		lblEnzymePackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		btnAddList = new JButton("");
		btnAddList.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/smrf/resources/plus.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		btnAddList.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnDeleteList = new JButton("");
		btnDeleteList.setIcon(new ImageIcon(Table1.class.getResource("/smrf/resources/trashs.png")));
		
		btnLoad = new JButton("Display Package");
		
		btnLoad.setBackground(new Color(255, 255, 255));
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnSetDefault = new JButton("Load Package");
		
		btnSetDefault.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblPackageName = new JLabel("");
		lblPackageName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jtable.getColumnModel().getColumn(0).setPreferredWidth(5);   //.getColumn(1).setPreferredWidth(10);
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnLoad.doClick();
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setModel(modelList);
		loadlist();
		scrollPane_1.setViewportView(list);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(2)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblResName)
												.addComponent(lblResSeq, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
											.addGap(23)))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textResName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
										.addComponent(textResSeq, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
										.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnSetDefault, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAddList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
							.addGap(33))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEnzymePackage, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPackageName, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPackageName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblResName, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblResSeq, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(textResName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textResSeq, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEnzymePackage, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnSetDefault, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnAddList, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteList, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
					.addGap(14))
		);
		
		
		getContentPane().setLayout(groupLayout);
		
	}
}
