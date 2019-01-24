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
	
public class Print extends JFrame implements ActionListener{
	private JFrame pWindow;
	private JComboBox tableList;
	
	public void Print(){
		pWindow = new JFrame("Print Window");
		pWindow.setLayout(new FlowLayout());
		pWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel PrintMainPanel = new JPanel(new BorderLayout());
		JPanel printButtonPanel = new JPanel();
//		JPanel PrintMainPanel = new JPanel(new GridLayout(3, 1));		
	
		String[] tableNames = {"Names", "Email", "Address", "PhoneNumber", "Joined"};
		
		tableList = new JComboBox(tableNames);
		tableList.setSelectedIndex(0);
		tableList.addActionListener(this);
		
		JLabel instLabel = new JLabel("Select the type of information you want to printed. ", SwingConstants.CENTER);
		
		// button icons
		ImageIcon printCheck = new ImageIcon("icons/64X64_print_check.png");
		ImageIcon printCheckOnMouseOver = new ImageIcon("icons/64X64_print_check_onmouse.png");
		ImageIcon printCheckClick = new ImageIcon("icons/64X64_print_check_click.png");
		
		ImageIcon closeWindowNormal = new ImageIcon("icons/64X64_x.png");
		ImageIcon closeWindowOnMouseOver = new ImageIcon("icons/64X64_x_onmouse.png");
		ImageIcon closeWindowClick = new ImageIcon("icons/64X64_x_click.png");
		
		// print confirmation button
		JButton printB = new JButton(printCheck);
		printB.addActionListener(this);
		printB.setRolloverEnabled(true);
		printB.setRolloverIcon(printCheckOnMouseOver);
		printB.setPressedIcon(printCheckClick);
		printB.setToolTipText("Print This Entry");
		printB.addActionListener(this);
		printB.setActionCommand("Print Selected");
		printB.setFocusPainted(false);
		printB.setContentAreaFilled(false);
		printB.setMargin(new Insets(0, 0, 0, 0));
		printB.setBorder(BorderFactory.createEmptyBorder());
		
		// close window button
		JButton closeB = new JButton(closeWindowNormal);
		closeB.setRolloverEnabled(true);
		closeB.setRolloverIcon(closeWindowOnMouseOver);
		closeB.setPressedIcon(closeWindowClick);
		closeB.setToolTipText("Close This Window");
		closeB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) { pWindow.dispose(); }
		});
		closeB.setFocusPainted(false);
		closeB.setContentAreaFilled(false);
		closeB.setMargin(new Insets(0, 0, 0, 0));
		closeB.setBorder(BorderFactory.createEmptyBorder());
		
		printButtonPanel.add(printB);
		printButtonPanel.add(closeB);
		PrintMainPanel.add(instLabel, BorderLayout.NORTH);
		PrintMainPanel.add(tableList, BorderLayout.CENTER);	
		PrintMainPanel.add(printButtonPanel, BorderLayout.SOUTH);

		pWindow.add(PrintMainPanel);
		pWindow.pack();
		pWindow.setLocationRelativeTo(null);
		pWindow.setVisible(true);
	}
	
	// BUTTON METHOD
	public void actionPerformed(ActionEvent e){
		String[] info = new String[8];
		String actionCommand = e.getActionCommand();
	
		if(actionCommand.equals("Print Selected")) {
//			info[0] = (String) tableList.getSelectedItem();
			DbConnection dbCon = new DbConnection(info, 4);
			String[] tempInfo = dbCon.ProcessResults();

			PopUpWindow popUpGui = new PopUpWindow("Table saved to file!");
		}
	}
}