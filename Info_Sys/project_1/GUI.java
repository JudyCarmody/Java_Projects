import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener{
	public JTextField textArea;
	public static JTextField firstName, lastName, addrLine1, city, state, zipcode, emailAddr, phoneNum;
	private JFrame main;
	
	private static final boolean shouldFill = true;
	private static final int WIDTH = 525, HEIGHT = 300;
	
	private Color labelTextColor = new Color(0,0,0); // COLORS: red, green, blue
	public GUI()
	{
		super( "NAMES Table of addressBook Database" );
	
		GridBagConstraints gbc = new GridBagConstraints();				
		if (shouldFill) { gbc.fill = GridBagConstraints.HORIZONTAL; } // natural height, maximum width 
		
		// main frame, where everything will be placed.
		JFrame main = new JFrame("Address Book");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(new BorderLayout());
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// create the textfields
		firstName = new JTextField(30);
		lastName = new JTextField(30);
		addrLine1 = new JTextField(30);
		city = new JTextField(30);
		state = new JTextField(2);
		zipcode = new JTextField(11);
		emailAddr = new JTextField(30);		
		phoneNum = new JTextField(10);
		
		// creating JLabels, text will be aligned to the RIGHT
		JLabel firstNameLabel = new JLabel("First Name: ", SwingConstants.RIGHT);
		JLabel lastNameLabel = new JLabel("Last Name: ", SwingConstants.RIGHT);
		JLabel addrLine1Label = new JLabel("Address: ", SwingConstants.RIGHT);
		JLabel cityLabel = new JLabel("City: ", SwingConstants.RIGHT);
		JLabel stateLabel = new JLabel("State: ", SwingConstants.RIGHT);
		JLabel zipcodeLabel = new JLabel("Zipcode: ", SwingConstants.RIGHT);
		JLabel emailAddrLabel = new JLabel("Home E-mail: ", SwingConstants.RIGHT);
		JLabel phoneNumLabel = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		
		// coloring the text on the JLabels
		firstNameLabel.setForeground(labelTextColor);
		lastNameLabel.setForeground(labelTextColor);
		addrLine1Label.setForeground(labelTextColor);
		cityLabel.setForeground(labelTextColor);
		stateLabel.setForeground(labelTextColor);
		zipcodeLabel.setForeground(labelTextColor);
		emailAddrLabel.setForeground(labelTextColor);
		phoneNumLabel.setForeground(labelTextColor);

/* 
			Address Information Panel
			layering panels to get desired effect
		
			addrInfoLabelPanel and addrInfoTextPanel are added to the addrInfoPanel
			addrInfoPanel is added to the main panel
*/
		JPanel addrInfoPanel = new JPanel(new BorderLayout());
		
		JPanel addrInfoLabelPanel = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0; // set row at upper left.  Left-Most: gridx = 0
		gbc.weightx = 0; // distribute space among COLUMNS
		gbc.weighty = 0; // distribute space among ROWS
		gbc.insets = new Insets(2, 20, 2, 0); // margins: TOP, LEFT, BOTTOM, RIGHT
		addrInfoLabelPanel.add(firstNameLabel, gbc);
		addrInfoLabelPanel.add(lastNameLabel, gbc);
		addrInfoLabelPanel.add(addrLine1Label, gbc);
		addrInfoLabelPanel.add(cityLabel, gbc);
		addrInfoLabelPanel.add(stateLabel, gbc);
		addrInfoLabelPanel.add(zipcodeLabel, gbc);
		addrInfoLabelPanel.add(emailAddrLabel, gbc);
		addrInfoLabelPanel.add(phoneNumLabel, gbc);
		
		JPanel addrInfoTextPanel = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0; 
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 0, 0, 12); // margins: TOP, LEFT, BOTTOM, RIGHT
		addrInfoTextPanel.add(firstName, gbc); // adds textfield with GridBagConstraints
		addrInfoTextPanel.add(lastName, gbc);
		addrInfoTextPanel.add(addrLine1, gbc);
		addrInfoTextPanel.add(city, gbc);
		addrInfoTextPanel.add(state, gbc);
		addrInfoTextPanel.add(zipcode, gbc);
		addrInfoTextPanel.add(emailAddr, gbc);
		addrInfoTextPanel.add(phoneNum, gbc);
	
		// button panel
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		gbc.insets = new Insets(2, 2, 5, 5);  // margins: TOP, LEFT, BOTTOM, RIGHT

		// Menu icons
		ImageIcon searchNormal = new ImageIcon("icons/64X64_search.png");
		ImageIcon searchOnMouseOver = new ImageIcon("icons/64X64_search_onmouse.png");
		ImageIcon searchClick = new ImageIcon("icons/64X64_search_click.png");
		
		ImageIcon addNormal = new ImageIcon("icons/64X64_add_floppy.png");
		ImageIcon addOnMouseOver = new ImageIcon("icons/64X64_add_floppy_onmouse.png");
		ImageIcon addClick = new ImageIcon("icons/64X64_add_floppy_click.png");
		
		ImageIcon editNormal = new ImageIcon("icons/64X64_edit.png");
		ImageIcon editOnMouseOver = new ImageIcon("icons/64X64_edit_onmouse.png");
		ImageIcon editClick = new ImageIcon("icons/64X64_edit_click.png");		
		
		ImageIcon clearNormal = new ImageIcon("icons/64X64_clear.png");
		ImageIcon clearOnMouseOver = new ImageIcon("icons/64X64_clear_onmouse.png");
		ImageIcon clearClick = new ImageIcon("icons/64X64_clear_click.png");				
	
		ImageIcon delNormal = new ImageIcon("icons/64X64_delete_trashcan.png");
		ImageIcon delOnMouseOver = new ImageIcon("icons/64X64_delete_trashcan_onmouse.png");
		ImageIcon delClick = new ImageIcon("icons/64X64_delete_trashcan_click.png");		
		
		ImageIcon printNormal = new ImageIcon("icons/64X64_print.png");
		ImageIcon printOnMouseOver = new ImageIcon("icons/64X64_print_onmouse.png");		
		ImageIcon printClick = new ImageIcon("icons/64X64_print_click.png");

		ImageIcon exitNormal = new ImageIcon("icons/64X64_x.png");
		ImageIcon exitOnMouseOver = new ImageIcon("icons/64X64_x_onmouse.png");
		ImageIcon exitClick = new ImageIcon("icons/64X64_x_click.png");

		// search button
		JButton searchButton = new JButton(searchNormal); // create new Button with image
		searchButton.setRolloverEnabled(true); // enable image rollover
		searchButton.setRolloverIcon(searchOnMouseOver); // set image for rollover
		searchButton.setPressedIcon(searchClick); // icon when button is pressed
		searchButton.setToolTipText("Search Address Book"); // text that appears when mouse overs over button
		searchButton.addActionListener(this); // action listener for this button
		searchButton.setActionCommand("Search"); // set action command, used instead of JLabel
		gbc.fill = GridBagConstraints.HORIZONTAL; // makes component wide enough to fill horizontally
		gbc.gridx = 0; // set row at upper left.  Left-Most: gridx = 0
		gbc.gridy = 0; // set column at upper left.  Top-Most: gridy = 0

			// ------- Transparent Button -------
		searchButton.setFocusPainted(false); // TRUE == focus state painted; FALSE == focus state not painted
		searchButton.setContentAreaFilled(false); // TRUE == paint content area; FALSE == transparent content area
		searchButton.setMargin(new Insets(0, 0, 0, 0)); //  sets margins, Inset returns the margins between border and label: TOP, LEFT, BOTTOM, RIGHT
		searchButton.setBorder(BorderFactory.createEmptyBorder()); // sets border: BorderFactory creates a Border object
		buttonPanel.add(searchButton, gbc); // add button to panel
		
		// add button
		JButton addButton = new JButton(addNormal);		
		addButton.setRolloverEnabled(true);
		addButton.setRolloverIcon(addOnMouseOver);
		addButton.setPressedIcon(addClick);
		addButton.setToolTipText("Add Entry");
		addButton.addActionListener(this);
		addButton.setActionCommand("Add");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		addButton.setFocusPainted(false);
		addButton.setContentAreaFilled(false);
		addButton.setMargin(new Insets(0, 0, 0, 0));
		addButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(addButton, gbc);
		
		// edit button
		JButton editButton = new JButton(editNormal);
		editButton.setRolloverEnabled(true);
		editButton.setRolloverIcon(editOnMouseOver);
		editButton.setPressedIcon(editClick);
		editButton.setToolTipText("Edit Entry");
		editButton.addActionListener(this);
		editButton.setActionCommand("Edit");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(false);
		editButton.setMargin(new Insets(0, 0, 0, 0));
		editButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(editButton, gbc);		
		
		// clear fields
		JButton clearButton = new JButton(clearNormal);
		clearButton.setRolloverEnabled(true);
		clearButton.setRolloverIcon(clearOnMouseOver);
		clearButton.setPressedIcon(clearClick);
		clearButton.setToolTipText("Clear Fields");
		clearButton.addActionListener(this);
		clearButton.setActionCommand("ClearFields");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 0;
		clearButton.setFocusPainted(false);
		clearButton.setContentAreaFilled(false);
		clearButton.setMargin(new Insets(0, 0, 0, 0));
		clearButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(clearButton, gbc);		
		
		// print button		
		JButton printButton = new JButton(printNormal);
		printButton.setRolloverEnabled(true);
		printButton.setRolloverIcon(printOnMouseOver);
		printButton.setPressedIcon(printClick);
		printButton.setToolTipText("Print Entry");
		printButton.addActionListener(this);
		printButton.setActionCommand("Print");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 0;
		printButton.setFocusPainted(false);
		printButton.setContentAreaFilled(false);
		printButton.setMargin(new Insets(0, 0, 0, 0));
		printButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(printButton, gbc);		

		// delete button
		JButton delButton = new JButton(delNormal);
		delButton.setRolloverEnabled(true);
		delButton.setRolloverIcon(delOnMouseOver);
		delButton.setPressedIcon(delClick);
		delButton.setToolTipText("Delete Entry");
		delButton.addActionListener(this);
		delButton.setActionCommand("Delete");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		delButton.setFocusPainted(false);
		delButton.setContentAreaFilled(false);
		delButton.setMargin(new Insets(0, 0, 0, 0));
		delButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(delButton, gbc);

		// exit button
		JButton exitButton = new JButton(exitNormal);
		exitButton.setRolloverEnabled(true);
		exitButton.setRolloverIcon(exitOnMouseOver);
		exitButton.setPressedIcon(exitClick);
		exitButton.setToolTipText("Close Program");
		exitButton.addActionListener(this);
		exitButton.setActionCommand("Exit");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setMargin(new Insets(0, 0, 0, 0));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(exitButton, gbc);
		
		// adding all of the panels together, packing, then making them visible
		addrInfoPanel.add(addrInfoLabelPanel, BorderLayout.WEST);
		addrInfoPanel.add(addrInfoTextPanel, BorderLayout.EAST);
		main.add(addrInfoPanel, BorderLayout.CENTER);
		main.add(buttonPanel, BorderLayout.SOUTH);
		main.pack();
		main.setLocationRelativeTo(null); // centers the GUI, MUST BE AFTER pack();
		main.setVisible(true);
	}

	// MAIN METHOD
	public static void main(String[] args) { GUI guiAddrBook = new GUI(); }

	// BUTTON METHOD
	public void actionPerformed(ActionEvent e){
		String[] info = new String[8];
		
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Search"))
		{
			SearchWindow searchWin = new SearchWindow();
			searchWin.SearchWindow();
		}
		
		//Sends array of now information to DbConncetion
		else if(actionCommand.equals("Add")){
			info[0]=firstName.getText().toString();
			info[1]=lastName.getText().toString();
			info[2]=city.getText().toString();
			info[3]=addrLine1.getText().toString();
			info[4]=zipcode.getText().toString();
			info[5]=state.getText().toString();
			info[6]=emailAddr.getText().toString();
			info[7]=phoneNum.getText().toString();

			DbConnection dbCon = new DbConnection(info, 1);
			
			firstName.setText("");
			lastName.setText("");
			city.setText("");
			addrLine1.setText("");
			zipcode.setText("");
			state.setText("");
			emailAddr.setText("");
			phoneNum.setText("");
		
			PopUpWindow popWin = new PopUpWindow("Entry Added!");
		} 
		
		else if(actionCommand.equals("ClearFields")) {
			firstName.setText("");
			lastName.setText("");
			city.setText("");
			addrLine1.setText("");
			zipcode.setText("");
			state.setText("");
			emailAddr.setText("");
			phoneNum.setText("");
		}
	
		//Sends new information to be saved to the row that contains the first name
		else if(actionCommand.equals("Edit")) {
			info[0]=firstName.getText().toString();
			info[1]=lastName.getText().toString();
			info[2]=city.getText().toString();
			info[3]=addrLine1.getText().toString();
			info[4]=zipcode.getText().toString();
			info[5]=state.getText().toString();
			info[6]=emailAddr.getText().toString();
			info[7]=phoneNum.getText().toString();
				
			DbConnection dbCon = new DbConnection(info, 3);
				
			PopUpWindow popWin = new PopUpWindow("Entry Updated!");
		}
		
		else if(actionCommand.equals("Print"))
		{
			Print printWin = new Print();
			printWin.Print();
		}
		
		//Sends DbConnection an array with the name of the row that will be deleted
		else if(actionCommand.equals("Delete")) {
			info[0] = firstName.getText();
			info[1] = lastName.getText();
			DbConnection dbCon = new DbConnection(info, 2);

			PopUpWindow popWin = new PopUpWindow("Entry Deleted!");
			//popWin.PopUpWindow();			
	
/*			PopUpWindow popUpGui = new PopUpWindow("Entry Deleted");
			popUpGui.setLocationRelativeTo(null);
			popUpGui.setVisible(true);
*/		}
	
		else if(actionCommand.equals("Exit")) { System.exit(0); }
	}
}