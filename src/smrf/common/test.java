package smrf.common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class test {

	private JFrame frameResModify;
	private JScrollPane scrollPane;
	private JTextField textResName;
	private JTextField textResSeq;
	private JTable jtable;
	DefaultTableModel model;
	Object[] row = new Object[2];
	
	
	/**
	 * Launch the application.
	 */

	public void cleartable() {
		int rowcount = jtable.getRowCount();
		for(int i = rowcount-1; i>=0; i--) {
			model.removeRow(i);
		}
	}
	public void Save() {
		String filePath = "restriction sites.txt" ;
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
			Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
	      }
	        
	}  
	public void load() {
		String filePath = "restriction sites.txt" ;
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
			//for(int i = 0; i < lines.length; i++ ) {
				//String[] row = lines[i].toString().split(",");
				//model.addRow(row);
			//}
			
			
	            
			br.close();
			fr.close();
	            
		} catch (IOException ex) {
			Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
	      }
	        
	}  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frameResModify.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameResModify = new JFrame();
		frameResModify.setTitle("Modify Restriction Enzyme List");
		frameResModify.setIconImage(Toolkit.getDefaultToolkit().getImage(test.class.getResource("/resources/icon possibly6.png")));
		frameResModify.setBounds(100, 100, 920, 625);
		frameResModify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new DefaultTableModel();
		Object[] columnsNames = {
				"Restriction Enzyme", "Restriction Site Sequence"	
			};
		model.setColumnIdentifiers(columnsNames);
		
		JLabel lblNewLabel = new JLabel("Restriction site name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textResName = new JTextField();
		textResName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textResName.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		
		jtable = new JTable();
		
		
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = jtable.getSelectedRow();
				textResName.setText(model.getValueAt(selectedrow, 0).toString());
				textResSeq.setText(model.getValueAt(selectedrow, 1).toString());
			}
		});
		jtable.setBorder(new LineBorder(new Color(0, 0, 0)));
		jtable.setBackground(Color.WHITE);
		jtable.setModel(model);
		scrollPane.setViewportView(jtable);
		
		JLabel lblNewLabel_1 = new JLabel("Restriction site sequence:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textResSeq = new JTextField();
		textResSeq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textResSeq.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textResName.getText().equals("") || textResName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields before adding data");
				}
				else {
					row[0]= textResName.getText();
					row[1]= textResSeq.getText();
					model.addRow(row);
					textResName.setText("");
					textResSeq.setText("");
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = jtable.getSelectedRow();
				if (selectedrow >= 0) {
					model.removeRow(selectedrow);
					textResName.setText("");
					textResSeq.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a row to delete");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = jtable.getSelectedRow();
				if (textResName.getText().equals("") || textResName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields before Updating data");
				}
				else if(selectedrow>=0) {
					model.setValueAt(textResName.getText(), selectedrow, 0);
					model.setValueAt(textResSeq.getText(), selectedrow, 1);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a row to Update");
					
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResName.setText("");
				textResSeq.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnExitWithoutSaving = new JButton("<html> Exit Without<br>Saving</html>");
		btnExitWithoutSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		btnExitWithoutSaving.setHorizontalAlignment(SwingConstants.LEFT);
		btnExitWithoutSaving.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JButton btnSaveAndexit = new JButton("<html> Save  And<br>Exit</html>");
		btnSaveAndexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();
			}
		});
		btnSaveAndexit.setHorizontalAlignment(SwingConstants.LEFT);
		btnSaveAndexit.setFont(new Font("Tahoma", Font.BOLD, 22));
		GroupLayout groupLayout = new GroupLayout(frameResModify.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addComponent(lblNewLabel))
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
							.addGap(22))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnExitWithoutSaving, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textResSeq, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addGap(64)))
						.addComponent(textResName, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
						.addComponent(btnSaveAndexit, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addGap(57)
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(textResName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textResSeq, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addGap(57)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addGap(305))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnSaveAndexit, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnExitWithoutSaving, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
							.addGap(35))))
		);
		frameResModify.getContentPane().setLayout(groupLayout);
	}
}
