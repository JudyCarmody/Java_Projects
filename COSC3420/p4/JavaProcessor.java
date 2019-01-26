/*
 Carmody, Judy
 COSC 3420.001
 Project # 4
 Due: April 4, 2018

*/

import java.util.Random;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class JavaProcessor extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final Color COLOR_1 = new Color(99,00,00);
	public static final Color COLOR_2 = new Color(00,99,99);
	public static final Color COLOR_3 = new Color(00,66,66);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color ORANGE = new Color(255, 128, 0);
	public static final Color YELLOW = new Color(255, 255, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color CYAN = new Color(0, 255, 255);
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color PURPLE = new Color(128,0, 255);
	public static final Color MAGENTA = new Color(255, 0, 255);
	
	private JTextField fileIn;
	private JTextArea textDisplay;
	private JLabel nyanCatLabel;
	private String fileInput, line;
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private int count=0;
    private ArrayList<String> javaEditArrLi = new ArrayList<String>();
	
	public JavaProcessor(){
	
		// JFrame
		JFrame main = new JFrame("Java File Checker");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(new GridLayout(1,1));
		main.setBackground(Color.WHITE);
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// JPanels
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel functionPanel = new JPanel(new BorderLayout());
		CustomPanel displayPanel = new CustomPanel(YELLOW, PURPLE);
		CustomPanel buttonPanel = new CustomPanel(ORANGE, BLUE);
		CustomPanel fileInputOutputPanel = new CustomPanel(CYAN,MAGENTA);
		
		// text display - JTextArea inside JScrollPane
		textDisplay = new JTextArea(800,400);
		textDisplay.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textDisplay);
		GradientViewport viewport = new GradientViewport(COLOR_1, COLOR_2);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(850,475));
		
		textDisplay.setOpaque(false);
		viewport.setView(textDisplay);
		scrollPane.setViewport(viewport);
		add(scrollPane);		
		
		// buttons
		JButton readBtn = new JButton("Read File");
		readBtn.addActionListener(this);
		JButton checkBtn = new JButton("Check File");
		checkBtn.addActionListener(this);
		JButton saveBtn = new JButton("Save File");
		saveBtn.addActionListener(this);
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(this);
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(this);
		
		// button tooltips
		readBtn.setToolTipText("Import file into program");
		checkBtn.setToolTipText("Check file for errors");
		saveBtn.setToolTipText("Save file");
		clearBtn.setToolTipText("Clear fields");
		exitBtn.setToolTipText("Exit Program");
		
		// text field - input
		this.fileIn = new JTextField(50);
		fileIn.setCaretPosition(0);
		fileIn.setCaretColor(Color.GREEN);
		JLabel label = new JLabel("File In");
		fileInputOutputPanel.add(label);
		
		// borders
		Border thinBorder = BorderFactory.createLineBorder(Color.CYAN, 1);
		fileIn.setBorder(null);
		textDisplay.setBorder(thinBorder);
		
		// background colors
		fileIn.setBackground(COLOR_3);
		readBtn.setBackground(Color.GREEN);
		checkBtn.setBackground(Color.ORANGE);
		saveBtn.setBackground(Color.YELLOW);
		clearBtn.setBackground(Color.CYAN);
		exitBtn.setBackground(Color.RED);
		
		// foreground colors
		textDisplay.setForeground(Color.LIGHT_GRAY);
		fileIn.setForeground(Color.GRAY);
		label.setForeground(Color.BLACK);
		readBtn.setForeground(Color.BLACK);
		checkBtn.setForeground(Color.BLACK);
		saveBtn.setForeground(Color.BLACK);
		clearBtn.setForeground(Color.BLACK);
		exitBtn.setForeground(Color.BLACK);
		
		// text styles
		label.setFont(new Font("Verdana", Font.BOLD, 16));
		fileIn.setFont(new Font("Verdana", Font.PLAIN, 16));
		textDisplay.setFont(new Font("Verdana", Font.PLAIN, 10));
		readBtn.setFont(new Font("Verdana", Font.BOLD, 16));
		checkBtn.setFont(new Font("Verdana", Font.BOLD, 16));
		saveBtn.setFont(new Font("Verdana", Font.BOLD, 16));
		clearBtn.setFont(new Font("Verdana", Font.BOLD, 16));
		exitBtn.setFont(new Font("Verdana", Font.BOLD, 16));
		
		// adding the components together
		buttonPanel.add(readBtn);
		buttonPanel.add(checkBtn);
		buttonPanel.add(saveBtn);
		buttonPanel.add(clearBtn);
		buttonPanel.add(exitBtn);
		fileInputOutputPanel.add(fileIn);
		displayPanel.add(scrollPane);
		
		// putting it all together
		main.add(contentPanel);
		functionPanel.add(fileInputOutputPanel, BorderLayout.NORTH);
		functionPanel.add(buttonPanel, BorderLayout.SOUTH);
		contentPanel.add(functionPanel, BorderLayout.NORTH);
		contentPanel.add(displayPanel, BorderLayout.CENTER);
		main.pack();
		main.setVisible(true);
		fileIn.requestFocusInWindow();
	}

	public void actionPerformed(ActionEvent e){
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Read File")){fileInOut();}
		else if(actionCommand.equals("Check File")){checkFile(javaEditArrLi);}
		else if(actionCommand.equals("Save File")){saveFile(fileInput);}
		else if(actionCommand.equals("Clear")){clearFields();}
		else if(actionCommand.equals("Exit")){System.exit(0);}
		else{System.out.println("Error in button interface, program ending.");
			System.exit(0);
		}
	}
	
	// read file
	private void fileInOut(){
		fileInput = fileIn.getText();
		
		// read input from a .java file, add to arrayList, display in JTextArea
		try{
			inputStream = new BufferedReader(new FileReader(fileInput));
			while((line=inputStream.readLine()) != null){javaEditArrLi.add(line);}
			for(String s : javaEditArrLi){textDisplay.append(s+"\n");}
			inputStream.close();
        }catch(FileNotFoundException e){
			System.out.println("File "+fileIn+" not found.\n"+"Program ending.");
			System.exit(0);
        }catch(IOException e){
			System.out.println("Error reading from file " +fileIn+ ".\n"
				+"Program ending."); System.exit(0);
        }
	}
	
	// check file
	private void checkFile(ArrayList<String> javaEditArrLi){
		try{
			// check file here
			outputStream = new PrintWriter(new FileOutputStream(fileInput));
		}
		catch(FileNotFoundException e){
			System.out.println("File "+fileInput+" not found.\n"+"Program ending.");
			System.exit(0);
		}
	}
	
	// clear fields
	private void clearFields(){fileIn.setText(""); textDisplay.setText("");}
	
	// save file and exit
	private void saveFile(String fileInput){
		for(String s : javaEditArrLi) outputStream.print(s + "\n");
        outputStream.close();
		System.out.println("\nFile saved to: "+fileInput+"\nProgram Ending.");
	}
	
	// MAIN METHOD
	public static void main(String[] args){JavaProcessor JavaProcessor = new JavaProcessor();}
}