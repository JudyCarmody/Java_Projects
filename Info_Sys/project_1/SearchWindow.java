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
	
public class SearchWindow extends JFrame implements ActionListener{
	private static final boolean shouldFill = true; 
	private static final int WIDTH = 350;
	private static final int HEIGHT = 150;
	
	private JLabel feedBackLabel;
	private JTextField textArea;
	
	public JFrame sWindow;
	
	//Pulls new window for searching.
	public void SearchWindow(){
		sWindow = new JFrame("Search Window");
		sWindow.setLayout(new FlowLayout());
		sWindow.setPreferredSize(new Dimension(350, 175));
		
		// GridBagLayout for the buttonSearchPanel
		GridBagConstraints gbcS = new GridBagConstraints();		
		if (shouldFill) { gbcS.fill = GridBagConstraints.HORIZONTAL; }		
		
		JPanel searchMainPanel = new JPanel(new BorderLayout());
		JPanel searchInputPanel = new JPanel();
		JPanel buttonSearchPanel = new JPanel(new GridBagLayout());		
		JPanel feedBackPanel =  new JPanel(new BorderLayout());
		gbcS.insets = new Insets(2, 2, 5, 5);  // margins: TOP, LEFT, BOTTOM, RIGHT		
		
		textArea = new JTextField(25);
		textArea.setText("");
		// informationLabel.set
		
		//Feed Back Label
		feedBackLabel = new JLabel("Input name of what you want to see below.", SwingConstants.CENTER);
		feedBackLabel.setFont(new Font("", Font.PLAIN, 15));
		feedBackPanel.add(feedBackLabel, BorderLayout.CENTER);
		
		// icons for buttons
		ImageIcon firstNameSearch = new ImageIcon("icons/64X64_search_FN_confirm.png");
		ImageIcon firstNameOnMouseOver = new ImageIcon("icons/64X64_search_FN_confirm_onmouse.png");
		ImageIcon firstNameClick = new ImageIcon("icons/64X64_search_FN_confirm_click.png");

		ImageIcon lastNameSearch = new ImageIcon("icons/64X64_search_LN_confirm.png");
		ImageIcon lastNameOnMouseOver = new ImageIcon("icons/64X64_search_LN_confirm_onmouse.png");
		ImageIcon lastNameClick = new ImageIcon("icons/64X64_search_LN_confirm_click.png");
		
		ImageIcon clearNormal = new ImageIcon("icons/64X64_clear.png");
		ImageIcon clearOnMouseOver = new ImageIcon("icons/64X64_clear_onmouse.png");
		ImageIcon clearClick = new ImageIcon("icons/64X64_clear_click.png");
		
		ImageIcon closeWindowNormal = new ImageIcon("icons/64X64_x.png");
		ImageIcon closeWindowOnMouseOver = new ImageIcon("icons/64X64_x_onmouse.png");
		ImageIcon closeWindowClick = new ImageIcon("icons/64X64_x_click.png");
		
		// First Name Search Button
		JButton searchFN = new JButton(firstNameSearch);				
		searchFN.setRolloverEnabled(true);
		searchFN.setRolloverIcon(firstNameOnMouseOver);
		searchFN.setPressedIcon(firstNameClick);
		searchFN.setToolTipText("Search First Names");
		searchFN.addActionListener(this);
		searchFN.setActionCommand("Search First Names");
		gbcS.fill = GridBagConstraints.HORIZONTAL;
		gbcS.gridx = 0;
		gbcS.gridy = 0;
		searchFN.setFocusPainted(false);
		searchFN.setContentAreaFilled(false);
		searchFN.setMargin(new Insets(0, 0, 0, 0));
		searchFN.setBorder(BorderFactory.createEmptyBorder());
		buttonSearchPanel.add(searchFN, gbcS);

		// Last Name Search Button
		JButton searchLN = new JButton(lastNameSearch);				
		searchLN.setRolloverEnabled(true);
		searchLN.setRolloverIcon(lastNameOnMouseOver);
		searchLN.setPressedIcon(lastNameClick);
		searchLN.setToolTipText("Search Last Names");
		searchLN.addActionListener(this);
		searchLN.setActionCommand("Search Last Names");
		gbcS.fill = GridBagConstraints.HORIZONTAL;
		gbcS.gridx = 1;
		gbcS.gridy = 0;
		searchLN.setFocusPainted(false);
		searchLN.setContentAreaFilled(false);
		searchLN.setMargin(new Insets(0, 0, 0, 0));
		searchLN.setBorder(BorderFactory.createEmptyBorder());
		buttonSearchPanel.add(searchLN, gbcS);

		// Clear TextField Button
		JButton clearB = new JButton(clearNormal);				
		clearB.setRolloverEnabled(true);
		clearB.setRolloverIcon(clearOnMouseOver);
		clearB.setPressedIcon(clearClick);
		clearB.setToolTipText("Clear Search");
		clearB.addActionListener(this);
		clearB.setActionCommand("Clear Search");
		gbcS.fill = GridBagConstraints.HORIZONTAL;
		gbcS.gridx = 2;
		gbcS.gridy = 0;
		clearB.setFocusPainted(false);
		clearB.setContentAreaFilled(false);
		clearB.setMargin(new Insets(0, 0, 0, 0));
		clearB.setBorder(BorderFactory.createEmptyBorder());
		buttonSearchPanel.add(clearB, gbcS);
		
		// Close Pop-up Window
		JButton closeWindowButton = new JButton(closeWindowNormal);
		closeWindowButton.setRolloverEnabled(true);
		closeWindowButton.setRolloverIcon(closeWindowOnMouseOver);
		closeWindowButton.setPressedIcon(closeWindowClick);
		closeWindowButton.setToolTipText("Close This Window");
		closeWindowButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) { sWindow.dispose(); }
		});
		closeWindowButton.setActionCommand("Close Search Window");
		gbcS.fill = GridBagConstraints.HORIZONTAL;
		gbcS.gridx = 3;
		gbcS.gridy = 0;
		closeWindowButton.setFocusPainted(false);
		closeWindowButton.setContentAreaFilled(false);
		closeWindowButton.setMargin(new Insets(0, 0, 0, 0));
		closeWindowButton.setBorder(BorderFactory.createEmptyBorder());
		buttonSearchPanel.add(closeWindowButton, gbcS);

		searchInputPanel.add(textArea);
		searchMainPanel.add(feedBackLabel, BorderLayout.NORTH);
		searchMainPanel.add(searchInputPanel, BorderLayout.CENTER);
		searchMainPanel.add(buttonSearchPanel, BorderLayout.SOUTH);
		
		sWindow.add(searchMainPanel);
		sWindow.pack();
		sWindow.setLocationRelativeTo(null);
		//sWindow.setLocation(sWindow.getLocation().x - 420, sWindow.getLocation().y - 75);
		sWindow.setVisible(true);
	}

	// BUTTON METHOD
	public void actionPerformed(ActionEvent e){
		String[] info = new String[8];	
		String actionCommand = e.getActionCommand();
		
		//Sends DbConnection an array with the first name that will be used to search the joined table
		if(actionCommand.equals("Search First Names")) {
			info[0] = "FirstName";
			info[1] = textArea.getText().toString();
			DbConnection dbCon = new DbConnection(info, 0);
			String[] tempInfo = dbCon.ProcessResults();
			
			if(tempInfo[0] == null)
				feedBackLabel.setText("No record found.");
			
			else{
				feedBackLabel.setText("Record found!");
				GUI.firstName.setText(tempInfo[0]);
				GUI.lastName.setText(tempInfo[1]);
				GUI.addrLine1.setText(tempInfo[2]);
				GUI.city.setText(tempInfo[3]);
				GUI.state.setText(tempInfo[4]);
				GUI.zipcode.setText(tempInfo[5]);
				GUI.emailAddr.setText(tempInfo[6]);
				GUI.phoneNum.setText(tempInfo[7]);
			}
		}
				
		//Sends DbConnection an array with the last name that will be used to search the joined table
		else if(actionCommand.equals("Search Last Names")){
			info[0] = "LastName";
			info[1] = textArea.getText().toString();
			DbConnection dbCon = new DbConnection(info, 0);
			String[] tempInfo = dbCon.ProcessResults();
			
			if(tempInfo[0] == null)
				feedBackLabel.setText("No record found.");
	
			else{
				feedBackLabel.setText("Record found!");	
				GUI.firstName.setText(tempInfo[0]);
				GUI.lastName.setText(tempInfo[1]);
				GUI.addrLine1.setText(tempInfo[2]);
				GUI.city.setText(tempInfo[3]);
				GUI.state.setText(tempInfo[4]);
				GUI.zipcode.setText(tempInfo[5]);
				GUI.emailAddr.setText(tempInfo[6]);
				GUI.phoneNum.setText(tempInfo[7]);
			}
		}

		else if(actionCommand.equals("Clear Search")) { textArea.setText(""); }
		else System.out.println("Error in button interface.");
	}
}