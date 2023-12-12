package smrf.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TextAreaOutputStream extends OutputStream {
    private JTextArea textControl;

    /**
     * Creates a new instance of TextAreaOutputStream which writes
     * to the specified instance of javax.swing.JTextArea control.
     *
     * @param control   A reference to the javax.swing.JTextArea
     *                  control to which the output must be redirected
     *                  to.
     */
    public TextAreaOutputStream( JTextArea control ) {
        textControl = control;
    }

    /**
     * Writes the specified byte as a character to the
     * javax.swing.JTextArea.
     *
     * @param   b   The byte to be written as character to the
     *              JTextArea.
     */
    public void write( int b ) throws IOException {
        // append the data as characters to the JTextArea control
        textControl.append( String.valueOf( ( char )b ) );
    }
}

public class FirstGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrimerSequence;
	private JTextField txtMismatchThr;
	private JTextField txtCodingSeq;
	private JButton btnRunSearch;
	private JTextArea txtconsol;
	private JLabel lblCredits;
	private boolean searchisAlive = false;
	private boolean formatisAlive = false;
	DefaultTableModel model;
	DefaultListModel<String> modelList;
	////////
	private Runnable formatPackageRunnabe = new Runnable() {
		@Override
		public void run() {
			formatisAlive = true;
			FormatPackage();
			formatisAlive = false;
		}
	};
	private Thread thread2 = new Thread(formatPackageRunnabe);
	///////
	private Runnable runSearchRunnable = new Runnable() {
		@Override
		public void run() {
			searchisAlive=true;
			Thread thread4 = new Thread(loadAnimRunnable);
			thread4.start();
			txtconsol.setText("");
			System.out.println("////////////////////////////////////////////////////////////////////////////////");
			System.out.println("////////////////Restriction sites already present (remove these)");
			System.out.println("////////////////////////////////////////////////////////////////////////////////");
			runSearch(0,0);
			System.out.println("\n\n\n\n\n////////////////////////////////////////////////////////////////////////////////");
			System.out.println("///////////////////Restriction sites to obtain by mutagenesis");
			System.out.println("////////////////////////////////////////////////////////////////////////////////");
			runSearch(Integer.parseInt(txtMismatchThr.getText()),1);
			System.out.println("Program finished");
			System.out.println(
					"If no matches were obtained please run program again with the mismatched threshold increased by 1");
			System.out.println("Created by Tamer ElSayed Abdelbary - German University in Cairo");
			searchisAlive = false;
			
		}
	};
	private Thread thread3 = new Thread(runSearchRunnable);
	private Runnable loadAnimRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				loadAnim();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private Thread thread4 = new Thread(loadAnimRunnable);
	private Runnable loadAnim2Runnable = new Runnable() {
		@Override
		public void run() {
			try {
				loadAnim2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private Thread thread5 = new Thread(loadAnim2Runnable);
	private Path path1;
	private Path path2;
	//public static smrf.View.Table1 f2 = new Table1();

	/**
	 * Launch the application.
	 */
	public String filePath = "temp.txt" ;
	public File file = new File(filePath);
	public static int numberOfLines = countLines("temp.txt");
	/**
	 * @wbp.nonvisual location=183,534
	 */
	private final JTable jtableInvisible2 = new JTable();
	/**
	 * @wbp.nonvisual location=350,534
	 */
	private final JList<String> list = new JList<String>();
	private JLabel lblCurrentPackage;
	private JLabel lblPercentage;
	/**
	 * @wbp.nonvisual location=410,534
	 */
	//public String[] restrictionSites = new String[countLines(file)];
	//public String[] restrictionSitesNames = new String[countLines(file)];
	/**
	 * @throws InterruptedException 
	 * @wbp.nonvisual location=473,454
	 */
	  
	
	public void loadAnim() throws InterruptedException {
		while (searchisAlive) {
			Thread.sleep(1000);
			lblPercentage.setText("Search In Progress.");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress..");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress...");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress....");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress.....");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress......");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
			lblPercentage.setText("Search In Progress.......");
			if (!searchisAlive) {
				break;
			}
			Thread.sleep(500);
		}
		lblPercentage.setText("Search Finished");
		
	}
	public void loadAnim2() throws InterruptedException {
		while (formatisAlive) {
			Thread.sleep(1000);
			lblPercentage.setText("Enzyme package Loading:.");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:..");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:...");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:....");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:.....");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:......");
			Thread.sleep(500);
			lblPercentage.setText("Enzyme package Loading:.......");
			Thread.sleep(500);
		}
		lblPercentage.setText("Enzyme package Loading: DONE");
		
	}
	public boolean checkPrimer() {
		boolean check = true;
		String primer = txtPrimerSequence.getText();
		for (int i = 0; i < primer.length(); i++) {
			if (primer.charAt(i) == 'A' || primer.charAt(i) =='T' || primer.charAt(i) == 'G' || primer.charAt(i) =='C' || primer.charAt(i) == 'a' || primer.charAt(i) =='t' || primer.charAt(i) == 'g' || primer.charAt(i) =='c') {
				
			}
			else {
				check = false;
			}
		}
		return check;
	}
	public boolean checkInt(String string) {
		boolean check = true;
		try {
			int a = Integer.parseInt(string);
		} catch (Exception e) {
			// TODO: handle exception
			check = false;
		}
		return check;
	}
	public void FormatPackage() {
		boolean rerun = true;

		try {

			String line;
			boolean direct = true;
			while (rerun) {
				rerun = false;
				Path path11 = Paths.get("temp2.txt");
				Path path21 = Paths.get("temp.txt");
				File filer = new File("temp.txt");
				File filew = new File("temp2.txt");
				FileReader fr = new FileReader(filer);
				BufferedReader br = new BufferedReader(fr);
				FileWriter fw = new FileWriter(filew);
				BufferedWriter bw = new BufferedWriter(fw);
				//int currentLine = 0;

				while ((line = br.readLine()) != null) {
					// line = br.readLine().trim();
					if (line != null) {
						String[] dataRow = line.split(",");
						for (int i = 0; i < dataRow[1].length(); i++) {
							if (dataRow[1].charAt(i) == 'N' || dataRow[1].charAt(i) == 'n') {
								rerun = true;
								direct = false;
								StringBuffer sb = new StringBuffer(dataRow[1]);
								sb.setCharAt(i, 'A');
								bw.write(dataRow[0] + "," + sb);
								bw.newLine();

								sb.setCharAt(i, 'T');
								bw.write(dataRow[0] + "," + sb);
								bw.newLine();

								sb.setCharAt(i, 'G');
								bw.write(dataRow[0] + "," + sb);
								bw.newLine();

								sb.setCharAt(i, 'C');
								bw.write(dataRow[0] + "," + sb);
								bw.newLine();
								//numberOfLines = numberOfLines + 3;
								break;
							}
						}
						if (direct) {
							bw.write(dataRow[0] + "," + dataRow[1]);
							bw.newLine();
						}

						//currentLine++;
						//int percentage = (currentLine / numberOfLines) * 100;
						
					}
					direct = true;
				}
				br.close();
				fr.close();
				bw.close();
				fw.close();
				try {
					Files.copy(path11, path21, StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
					// TODO: handle exception
					//System.out.println("here 2");
				}
				
			}

		} catch (IOException ex) {
			//Logger.getLogger(FirstGUI.class.getName()).log(Level.SEVERE, null, ex);
			//System.out.println("here 3");
		}
		saveCurrentUnpacked(loadDefault());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblPercentage.setText("");
	}
	public void runSearch(int threshold, int thresholdMin) {
		
		String primer = txtPrimerSequence.getText();
		String[] codon = { "ttt", "ttc", "tta", "ttg", "tct", "tcc", "tca", "tcg", "tat", "tac", "taa",
				"tag", "tgt", "tgc", "tga", "tgg",

				"ctt", "ctc", "cta", "ctg", "cct", "ccc", "cca", "ccg", "cat", "cac", "caa", "cag", "cgt",
				"cgc", "cga", "cgg",

				"att", "atc", "ata", "atg", "act", "acc", "aca", "acg", "aat", "aac", "aaa", "aag", "agt",
				"agc", "aga", "agg",

				"gtt", "gtc", "gta", "gtg", "gct", "gcc", "gca", "gcg", "gat", "gac", "gaa", "gag", "ggt",
				"ggc", "gga", "ggg" };
		String[] protein = { "phe", "phe", "leu", "leu", "ser", "ser", "ser", "ser", "tyr", "tyr", "stop",
				"stop", "cys", "cys", "stop", "trp",

				"leu", "leu", "leu", "leu", "pro", "pro", "pro", "pro", "his", "his", "gln", "gln", "arg",
				"arg", "arg", "arg",

				"ile", "ile", "ile", "met", "thr", "thr", "thr", "thr", "asn", "asn", "lys", "lys", "ser",
				"ser", "arg", "arg",

				"val", "val", "val", "val", "ala", "ala", "ala", "ala", "asp", "asp", "glu", "glu", "gly",
				"gly", "gly", "gly" };
		String primercodeo = "";
		String primercoden = "";
		int first = Integer.parseInt(txtCodingSeq.getText());
		int mnum = 0;
		int misnum = 0;
		char[] arryres;
		char[] arryprim;
		for (int i = 0; i < jtableInvisible2.getRowCount(); i++) { // restrictionSites.length
			// cycles restriction sites
			for (int x = 0; x <= primer.length()-jtableInvisible2.getValueAt(i, 1).toString().length(); x++) {
				// cycles through primer positions
				// int F = jtableInvisible2.getRowCount();
				// String test = jtableInvisible2.getValueAt(0, 0).toString();
				arryres = jtableInvisible2.getValueAt(i, 1).toString().toCharArray(); // sequence
				arryprim = primer.substring(x, x + jtableInvisible2.getValueAt(i, 1).toString().length())
						.toCharArray();
				for (int z = 0; z < jtableInvisible2.getValueAt(i, 1).toString().length(); z++) {
					// cycles through characters
					if (Character.toLowerCase(arryres[z]) == Character.toLowerCase(arryprim[z])) {
						mnum++;
					}

				}
				misnum = jtableInvisible2.getValueAt(i, 1).toString().length() - mnum;
				// number of mismatches at position starting x in primer

				if (misnum <= threshold & misnum >= thresholdMin) {// sees if it is a silent mutation
					String rep = primer.substring(0, x) + jtableInvisible2.getValueAt(i, 1).toString()
							+ primer.substring(x + jtableInvisible2.getValueAt(i, 1).toString().length(),
									primer.length());
					String rep2 = primer.substring(0, x) + "["
							+ jtableInvisible2.getValueAt(i, 1).toString() + "]"
							+ primer.substring(x + jtableInvisible2.getValueAt(i, 1).toString().length(),
									primer.length());
					for (int z = 0; z + first + 2 < primer.length(); z = z + 3) {
						for (int f = 0; f < codon.length; f++) {
							if (primer.substring(z + first - 1, z + first + 2).equalsIgnoreCase(codon[f])) {
								primercodeo = primercodeo + protein[f];
							}
						}
					}
					for (int z2 = 0; z2 + first + 2 < rep.length(); z2 = z2 + 3) {
						for (int f2 = 0; f2 < codon.length; f2++) {
							if (rep.substring(z2 + first - 1, z2 + first + 2).equalsIgnoreCase(codon[f2])) {
								primercoden = primercoden + protein[f2];

							}
						}
					}

					if (primercodeo.equalsIgnoreCase(primercoden)) {
						System.out.println("Match confirmed between base number " + (x + 1) + " and "
								+ (x + jtableInvisible2.getValueAt(i, 1).toString().length()) + " for "
								+ jtableInvisible2.getValueAt(i, 1).toString() + " restriction site ("
								+ jtableInvisible2.getValueAt(i, 0).toString() + ")");
						System.out.println("New primer :");
						System.out.println(rep2);
						System.out.println(
								"--------------------------------------------------------------------------------");
					}
					primercodeo = "";
					primercoden = "";
				}
				misnum = 0;
				mnum = 0;
				if (x + jtableInvisible2.getValueAt(i, 1).toString().length() == primer.length()) {
					break;
				}
			}
		}
		
	
	}
	public static int countLines(String filepath) {
		File fileName = new File(filepath);
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while (reader.readLine() != null)
				lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}
	public void cleartable() {
		for(int i = jtableInvisible2.getRowCount()-1; i>=0; i--) {
			model.removeRow(i);
		}
	}
	public void load() {
		String filePath = "temp.txt" ;
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
			Logger.getLogger(Table1.class.getName()).log(Level.SEVERE, null, ex);
	      }
	        
	}
	public int loadDefault() {
		String filePath ="Default index.txt";
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
			Logger.getLogger(FirstGUI.class.getName()).log(Level.SEVERE, null, ex);
	      }
		return index;
	    
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
			Logger.getLogger(FirstGUI.class.getName()).log(Level.SEVERE, null, ex);
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
	public int loadCurrentUnpacked() {
		String filePath ="Current Loaded Package.txt";
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
			Logger.getLogger(FirstGUI.class.getName()).log(Level.SEVERE, null, ex);
	      }
		return index;
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
					FirstGUI frame = new FirstGUI();
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
	public FirstGUI() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lblCurrentPackage.setText("Current Enzyme Package: " + list.getModel().getElementAt(loadDefault()));
				if (loadCurrentUnpacked()!= loadDefault() & formatisAlive == false) {
					Thread thread5 = new Thread(loadAnim2Runnable);
					Thread thread2 = new Thread(formatPackageRunnabe);
					list.setSelectedIndex(loadDefault());
					String filepathdefault = list.getModel().getElementAt(list.getSelectedIndex()).toString() + ".txt";
					path1 = Paths.get(filepathdefault);
					path2 = Paths.get("temp.txt");
					try {
						Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//System.out.println("here1");
					}
					if (formatisAlive == false) {
						thread2.start();
						thread5.start();
					}
					
					
				}
			}
		});
		initComponents();
		createEvent();
		
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub	
		modelList = new DefaultListModel<String>();
		model = new DefaultTableModel();
		jtableInvisible2.setModel(model);
		list.setModel(modelList);
		Object[] columnsNames = {
				"Restriction Enzyme", "Restriction Site Sequence"	
			};
		model.setColumnIdentifiers(columnsNames);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstGUI.class.getResource("/smrf/resources/icon possibly6.png")));
		setTitle("SMRF (Silent Mutation for Restriction-site Finder)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPrimerSequence = new JLabel("Enter Primer Sequence");
		lblPrimerSequence.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtPrimerSequence = new JTextField();
		txtPrimerSequence.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrimerSequence.setColumns(10);
		
		JLabel lblMismatchThr = new JLabel("Enter Mismatch Threshold");
		lblMismatchThr.setHorizontalTextPosition(SwingConstants.LEADING);
		lblMismatchThr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtMismatchThr = new JTextField();
		txtMismatchThr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMismatchThr.setColumns(10);
		
		txtCodingSeq = new JTextField();
		txtCodingSeq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCodingSeq.setColumns(10);
		
		JLabel lblCodingSeq = new JLabel("<html>Enter First Number in Coding Sequence</html>\r\n");
		lblCodingSeq.setToolTipText("<html>For example in AA CTG ... <br>\r\nthe number entered should be 3</html>");
		lblCodingSeq.setIconTextGap(0);
		lblCodingSeq.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCodingSeq.setIcon(new ImageIcon(FirstGUI.class.getResource("/smrf/resources/icons8-question-mark-20.png")));
		lblCodingSeq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnRunSearch = new JButton("Run Search");
		btnRunSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRunSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRunSearch.setIcon(new ImageIcon(FirstGUI.class.getResource("/smrf/resources/icons8-search-41.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblCredits = new JLabel("Created by Tamer ElSayed Abdelbary - German University in Cairo\r");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnEditEnzyme = new JButton("Edit Enzyme List");
		btnEditEnzyme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table1 f2 = new Table1();
				//f2.setAlwaysOnTop(true);
				enable(false);
				f2.show();
				//f2.setAlwaysOnTop (true);
				
			}
		});
		loadlist();
		btnEditEnzyme.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblCurrentPackage = new JLabel();
		lblCurrentPackage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentPackage.setText("Current Enzyme Package: " + list.getModel().getElementAt(loadDefault()));
		
		lblPercentage = new JLabel("");
		lblPercentage.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(lblCurrentPackage, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMismatchThr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPrimerSequence, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCodingSeq, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtPrimerSequence, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
									.addGap(9))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtMismatchThr, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCodingSeq, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(btnEditEnzyme, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnRunSearch, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblPercentage, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrimerSequence, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrimerSequence, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMismatchThr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMismatchThr, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtCodingSeq, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodingSeq, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCurrentPackage, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPercentage, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRunSearch, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditEnzyme, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
		);
		
		txtconsol = new JTextArea();
		txtconsol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(txtconsol);
		contentPane.setLayout(gl_contentPane);
		PrintStream out = new PrintStream( new TextAreaOutputStream( txtconsol ) );

		// redirect standard output stream to the TextAreaOutputStream
		System.setOut( out );

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr( out );
		/*PrintStream outStream = new PrintStream( new TextAreaOutputStream(txtconsol));
	        System.setOut( outStream );
	        System.setErr( outStream );*/
		
        
	}

	private void createEvent() {
		
		txtCodingSeq.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnRunSearch.doClick();
		}
		});
		txtMismatchThr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRunSearch.doClick();
			}
		});
		txtPrimerSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRunSearch.doClick();
			}
		});
		// TODO Auto-generated method stub
		btnRunSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (formatisAlive) {
					JOptionPane.showMessageDialog(null,"Please wait untill unpacking is finished");
				}
				else if (txtPrimerSequence.getText().equals("") || txtMismatchThr.getText().equals("")|| txtCodingSeq.getText().equals(""))  {
					JOptionPane.showMessageDialog(null,"Please Fill in all fields before running the search");
				}
				else if (checkPrimer()==false) {
					JOptionPane.showMessageDialog(null, "The primer can only contain A, T, C or G and no spaces");
				}
				else if(txtPrimerSequence.getText().length() <= 8) {
					JOptionPane.showMessageDialog(null,"Please enter a primer with length greater than 8");
				}
				else if (checkInt(txtMismatchThr.getText()) == false && txtMismatchThr.getText()!= "") {
					JOptionPane.showMessageDialog(null, "Please Enter only Integers in the mismatch threshold");
				}
				else if (checkInt(txtCodingSeq.getText()) == false && txtCodingSeq.getText()!= "") {
					JOptionPane.showMessageDialog(null, "Please Enter only Integers in the first number in coding sequence field");
				}
				else if (Integer.parseInt(txtCodingSeq.getText()) <= 0) {
					JOptionPane.showMessageDialog(null, "please enter a Integer greater that 0 in the coding sequence field");
				}
				
				else if (searchisAlive){
						JOptionPane.showMessageDialog(null, "Please wait unill search is finished before Running a new search");
				}
				
				else if(Integer.parseInt(txtCodingSeq.getText())> txtPrimerSequence.getText().length()) {
					JOptionPane.showMessageDialog(null,"Enter a correct value for the start of the coding sequence");
				}
				
				
				
				else if (searchisAlive== false){
					load();
					Thread thread3 = new Thread(runSearchRunnable);
					//Thread thread4 = new Thread(loadAnimRunnable);
					if (searchisAlive== false) {
						thread3.start();
						//thread4.start();					
						}
				}
				
			}
		});
         
	}
}
