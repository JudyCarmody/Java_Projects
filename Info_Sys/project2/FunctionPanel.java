import javax.swing.BorderFactory;
import java.awt.print.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.util.*;
import java.util.regex.Pattern;

public class FunctionPanel extends JPanel implements ActionListener
{
	private JComboBox roomList, customerList, RTypeList, eCustomerList, bCustomerList;
	private JTextArea numOcc, dateLease;
	public static String dateStr;
	private String selectedRoom;
	private JPanel functionPanel;
	private String[] strServicesArray;
	private StringBuilder serviceCode;
	private JCheckBox[] cbArray;
	private JTextArea PhoneNumber, customerN, CardNumber, ZipCode;
	
	public FunctionPanel(String string, int width, int height)
	{		
		functionPanel = new JPanel(new GridBagLayout());
		//functionPanel.setPreferredSize(new Dimension(width, height));

		if(string == "New Customer"){
			functionPanel.setBackground(new Color(235, 235, 235));
			NewCustomer();
		}
		
		else if(string == "Returning Customer"){
			functionPanel.setBackground(new Color(235, 235, 235));
			EditCustomer();
		}
		else if(string == "Generate Bill"){
			functionPanel.setBackground(new Color(235, 235, 235));
			GenerateBillPanel();
			functionPanel.revalidate();
		}	
		else if(string == "Make Reservation")
		{functionPanel.setBackground(new Color(235, 235, 235));}	

		else if(string == "Lease Room"){
			functionPanel.setBackground(new Color(235, 235, 235));
			LeaseRoomPanel();
		}	
		else if(string == "Check Customer DB"){
			functionPanel.setBackground(new Color(235, 235, 235));
			CheckCustomerDB();
		}	
		add(functionPanel);
	}
	//fills panel made by FunctionPanel class
	private void NewCustomer(){
		GridBagConstraints gbc = new GridBagConstraints();
	
		JLabel cnLabel = new JLabel("Customer Name: ");
		cnLabel.setFont(new Font(cnLabel.getName(), Font.BOLD, 20));
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20,10,10,10);
		functionPanel.add(cnLabel,gbc);
		JLabel cnumLabel = new JLabel("Card Number: ");
		cnumLabel.setFont(new Font(cnumLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 1;
		functionPanel.add(cnumLabel, gbc);
		JLabel zpLabel = new JLabel("Zip Code: ");
		zpLabel.setFont(new Font(zpLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 2;
		functionPanel.add(zpLabel, gbc);
		JLabel pnumLabel = new JLabel("Phone Number: ");
		pnumLabel.setFont(new Font(pnumLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 3;
		functionPanel.add(pnumLabel, gbc);
	
		
		customerN = new JTextArea(1,15);
		customerN.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 0;
		customerN.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(customerN, gbc);
		
		CardNumber = new JTextArea(1,15);
		CardNumber.setBackground(Color.WHITE);
		gbc.gridy = 1;
		CardNumber.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(CardNumber, gbc);
		
		ZipCode = new JTextArea(1,15);
		ZipCode.setBackground(Color.WHITE);
		gbc.gridy = 2;
		ZipCode.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(ZipCode, gbc);
		
		PhoneNumber = new JTextArea(1,15);
		PhoneNumber.setBackground(Color.WHITE);
		gbc.gridy = 3;
		PhoneNumber.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(PhoneNumber, gbc);
		
		JButton sumbitNCustomer = new JButton("SUBMIT NEW CUSTOMER");
		sumbitNCustomer.addActionListener(this);
		sumbitNCustomer.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		functionPanel.add(sumbitNCustomer, gbc);
	}
	//fills panel made by FunctionPanel class
	private void EditCustomer(){
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel cnLabel = new JLabel("Customer Name: ");
		cnLabel.setFont(new Font(cnLabel.getName(), Font.BOLD, 20));
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20,10,10,10);
		functionPanel.add(cnLabel,gbc);
		JLabel cnumLabel = new JLabel("Card Number: ");
		cnumLabel.setFont(new Font(cnumLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 1;
		functionPanel.add(cnumLabel, gbc);
		JLabel zpLabel = new JLabel("Zip Code: ");
		zpLabel.setFont(new Font(zpLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 2;
		functionPanel.add(zpLabel, gbc);
		JLabel pnumLabel = new JLabel("Phone Number: ");
		pnumLabel.setFont(new Font(pnumLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 3;
		functionPanel.add(pnumLabel, gbc);
	
		
		String[] inputInfo = {"CustomerN", "Customers","Customer_ID"};
		DbConnection connection = new DbConnection(inputInfo, 1);
		
		String[] strCustomerArray = connection.arrayL.toArray(new String[0]);
		eCustomerList = new JComboBox(strCustomerArray);
		eCustomerList.setSelectedIndex(0);
		eCustomerList.addActionListener(this);
		eCustomerList.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 0;
		functionPanel.add(eCustomerList, gbc);
		
		CardNumber = new JTextArea(1,15);
		CardNumber.setBackground(Color.WHITE);
		gbc.gridy = 1;
		CardNumber.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(CardNumber, gbc);
		
		ZipCode = new JTextArea(1,15);
		ZipCode.setBackground(Color.WHITE);
		gbc.gridy = 2;
		ZipCode.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(ZipCode, gbc);
		
		PhoneNumber = new JTextArea(1,15);
		PhoneNumber.setBackground(Color.WHITE);
		gbc.gridy = 3;
		PhoneNumber.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(PhoneNumber, gbc);
		
		JButton sumbitNCustomer = new JButton("UPDATE CUSTOMER");
		sumbitNCustomer.addActionListener(this);
		sumbitNCustomer.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridheight = 2;
		functionPanel.add(sumbitNCustomer, gbc);
		
		JButton deleteCustomer = new JButton("DELETE CUSTOMER");
		deleteCustomer.addActionListener(this);
		deleteCustomer.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 4;
		gbc.gridx = 1;
		gbc.gridheight = 2;
		functionPanel.add(deleteCustomer, gbc);
	}
	//fills panel made by FunctionPanel class(INCOMPLETE)
	private void GenerateBillPanel(){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20,10,10,10);
		JLabel cnLabel = new JLabel("Customer Name: ");
		cnLabel.setFont(new Font(cnLabel.getName(), Font.BOLD, 20));
		functionPanel.add(cnLabel, gbc);
		
		String[] inputInfo = {"CustomerN", "Customers","Customer_ID"};
		DbConnection connection = new DbConnection(inputInfo, 1);
		String[] strCustomerArray = connection.arrayL.toArray(new String[0]);
		bCustomerList = new JComboBox(strCustomerArray);
		bCustomerList.setSelectedIndex(0);
		bCustomerList.addActionListener(this);
		bCustomerList.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 0;
		functionPanel.add(bCustomerList, gbc);

		JButton generateBillB = new JButton("CREATE BILL");
		generateBillB.addActionListener(this);
		generateBillB.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		functionPanel.add(generateBillB, gbc);
	}

	//fills panel made by FunctionPanel class
	private void LeaseRoomPanel(){
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Adding information labels
		JLabel rLabel = new JLabel("Room: ");
		rLabel.setFont(new Font(rLabel.getName(), Font.BOLD, 20));
		gbc.anchor = GridBagConstraints.FIRST_LINE_START; //Start to adding room 1 panel to master room panel
		gbc.fill = GridBagConstraints.RELATIVE;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20,10,10,10);
		functionPanel.add(rLabel, gbc);
		JLabel cLabel = new JLabel("Customer: ");
		cLabel.setFont(new Font(cLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 1;
		functionPanel.add(cLabel, gbc);
		JLabel rtLabel = new JLabel("Room Type: ");
		rtLabel.setFont(new Font(rtLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 2;
		functionPanel.add(rtLabel, gbc);
		JLabel noLabel = new JLabel("Number of Occupents: ");
		noLabel.setFont(new Font(noLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 3;
		functionPanel.add(noLabel, gbc);
		JLabel ssLabel = new JLabel("Special Service: ");
		ssLabel.setFont(new Font(ssLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 4;
		functionPanel.add(ssLabel, gbc);
		JLabel dLabel = new JLabel("Date: ");
		dLabel.setFont(new Font(dLabel.getName(), Font.BOLD, 20));
		gbc.gridy = 5;
		functionPanel.add(dLabel, gbc);
		
		//Adding information input areas
		String[] inputInfo = {"Room_ID","Room", "Room_ID"};
		DbConnection connection = new DbConnection(inputInfo, 1);
		
		String[] strRoomArray = connection.arrayL.toArray(new String[0]);
		roomList = new JComboBox(strRoomArray);
		roomList.setSelectedIndex(0);
		roomList.addActionListener(this);
		roomList.setBackground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 0;
		functionPanel.add(roomList, gbc);
		
		inputInfo[0] = "CustomerN";
		inputInfo[1] = "Customers";
		inputInfo[2] = "Customer_ID";
		connection = new DbConnection(inputInfo, 1);
		
		String[] strCustomerArray = connection.arrayL.toArray(new String[0]);
		customerList = new JComboBox(strCustomerArray);
		customerList.setSelectedIndex(0);
		customerList.addActionListener(this);
		customerList.setBackground(Color.WHITE);
		gbc.gridy = 1;
		functionPanel.add(customerList, gbc);
		
		inputInfo[0] = "RoomTypeName";
		inputInfo[1] = "RoomType";
		inputInfo[2] = "RoomType_ID";
		connection = new DbConnection(inputInfo, 1);	
		String[] strRTypeArray = connection.arrayL.toArray(new String[0]);
		RTypeList = new JComboBox(strRTypeArray);
		RTypeList.setSelectedIndex(0);
		RTypeList.addActionListener(this);
		RTypeList.setBackground(Color.WHITE);
		gbc.gridy = 2;
		functionPanel.add(RTypeList, gbc);
		
		numOcc = new JTextArea(1,15);
		numOcc.setBackground(Color.WHITE);
		gbc.gridy = 3;
		numOcc.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(numOcc, gbc);
		
		inputInfo[0] = "Service";
		inputInfo[1] = "SpecialCharges";
		inputInfo[2] = "SpecialCharges_ID";
		connection = new DbConnection(inputInfo, 1);
		strServicesArray = connection.arrayL.toArray(new String[0]);
		JPanel checkBoxP = new JPanel(new GridLayout(Math.round(strServicesArray.length/2),2));
		checkBoxP.setBackground(new Color(235, 235, 235));
		cbArray = new JCheckBox[strServicesArray.length];
		for (int i = 0; i < strServicesArray.length; i++){
			cbArray[i] = new JCheckBox(strServicesArray[i]);
			checkBoxP.add(cbArray[i]);
			cbArray[i].setBackground(new Color(235, 235, 235));
		}
		gbc.gridy = 4;
		functionPanel.add(checkBoxP, gbc);
	
		/*JButton dateB = new JButton("Date");
		dateB.addActionListener(this);
		dateB.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 5;
		functionPanel.add(dateB, gbc);*/
		
		dateLease = new JTextArea(1,15);
		dateLease.setBackground(Color.WHITE);
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateLease.setText(sdf.format(date));
		gbc.gridy = 5;
		dateLease.setBorder(BorderFactory.createMatteBorder(5, 5, 2, 2, Color.black));
		functionPanel.add(dateLease, gbc);
		
		JButton submitB = new JButton("RENT ROOM");
		submitB.addActionListener(this);
		submitB.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		functionPanel.add(submitB, gbc);
	}

	private void CheckCustomerDB() {
		JPanel customerDBPanel = new JPanel(new GridBagLayout()); //Room 1 panel with labels and update button
		customerDBPanel.setBackground(Color.white);
		GridBagConstraints cusDBgbc = new GridBagConstraints();
		cusDBgbc.anchor = GridBagConstraints.FIRST_LINE_START;
		cusDBgbc.weightx = 0.5;
		cusDBgbc.weighty = 0.5;
		cusDBgbc.gridx = 0;
		cusDBgbc.gridy = 0;
		cusDBgbc.insets = new Insets(5,5,5,5);
		String[] inputInfo = {};
		DbConnection tableQuery = new DbConnection(inputInfo, 8);
		JTable custTable = tableQuery.rstable;
		JTableHeader header = custTable.getTableHeader();
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		Font tablefont = new Font("Arial", Font.PLAIN, 15);
		header.setFont(tablefont);
		custTable.setBackground(Color.white);
	    custTable.setFont(tablefont);
		for (int column = 0; column < custTable.getColumnCount(); column++)
		{
		    TableColumn tableColumn = custTable.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		    for (int row = 0; row < custTable.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = custTable.getCellRenderer(row, column);
		        Component c = custTable.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width  + 10;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        //  We've exceeded the maximum width, no need to check other rows
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		    tableColumn.setPreferredWidth( preferredWidth );
		}
		custTable.setRowHeight(30);

		functionPanel.add(new JScrollPane(custTable));
		/*
		JLabel idLabel = new JLabel("ID Number");
		idLabel.setFont(new Font(idLabel.getName(), Font.BOLD, 20));
		customerDBPanel.add(idLabel,cusDBgbc );
		cusDBgbc.gridx++;
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, 20));
		customerDBPanel.add(nameLabel,cusDBgbc );
		cusDBgbc.gridx++;
		JLabel cardNumLabel = new JLabel("Card Number");
		cardNumLabel.setFont(new Font(cardNumLabel.getName(), Font.BOLD, 20));
		customerDBPanel.add(cardNumLabel,cusDBgbc );
		cusDBgbc.gridx++;
		JLabel phoneNumLabel = new JLabel("Phone Number");
		phoneNumLabel.setFont(new Font(phoneNumLabel.getName(), Font.BOLD, 20));
		customerDBPanel.add(phoneNumLabel,cusDBgbc );
		cusDBgbc.gridx++;
		*/
		
		functionPanel.add(customerDBPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if (actionCommand.equals("RENT ROOM")) {
			//Getting information from drop down list to see if the selected room is available.
			String[] inputInfo =  new String[7];
			inputInfo[0] = "Room_ID";
			inputInfo[1] = (String) roomList.getSelectedItem(); 
			DbConnection checkAv = new DbConnection(inputInfo, 0);
			String availabilityS[] = checkAv.ProcessResults();
			System.out.println(availabilityS[0]);
			//Using string returned from the dbconnection, checks person in if Occupied = N
			if (availabilityS[0].equals("N")){
			inputInfo[0] = "\'" + (String) roomList.getSelectedItem() + "\'"; 
			String[] temp = {(String)customerList.getSelectedItem()};
			DbConnection getNameIndexCon = new DbConnection(temp, 4);
			String[] selectedNameNum = getNameIndexCon.arrayL.toArray(new String[0]);
			inputInfo[1] = "\'" + selectedNameNum[4] + "\'";
			inputInfo[2] = "\'" + Integer.toString(RTypeList.getSelectedIndex()+1) + "\'";
			inputInfo[3] = "\'" + numOcc.getText() + "\'";
			serviceCode = new StringBuilder(strServicesArray.length);
			for (int i = 0; i < strServicesArray.length; i++){
				if(cbArray[i].isSelected() == true){
					serviceCode.append(i+1);
				}
			}
			inputInfo[4] = "\'" + serviceCode.toString() + "\'";
			inputInfo[5] = "\'" + dateLease.getText() + "\'";
			inputInfo[6] = (String) customerList.getSelectedItem();
			DbConnection connection = new DbConnection(inputInfo,2);
			}else if (availabilityS[0].equals("Y")){
           	 PopUpWindow popWin = new PopUpWindow("Room " + (String) roomList.getSelectedItem() +" is occupied! ");
			}
		}else if (actionCommand.equals("SUBMIT NEW CUSTOMER")) {
			String[] inputInfo =  new String[4];
			inputInfo[0] = customerN.getText();
			inputInfo[1] = CardNumber.getText();
			inputInfo[2] = ZipCode.getText();
			inputInfo[3] = PhoneNumber.getText();
			DbConnection connection = new DbConnection(inputInfo,3);
		}else if(actionCommand.equals("Date")){
				/*
				JFrame dateF = new JFrame();
				DateReveiver dr = new DateReveiver();
				dateF.add(dr);
				dateF.setVisible(true);
				
				
				SingleFrame frame = new SingleFrame("Pick Date", 450, 600, new Dimension(500,500));
				frame.setVisible(true);
				add(frame);
				try {frame.setSelected(true);}
				catch (java.beans.PropertyVetoException a) {}*/
			}else if(actionCommand.equals("UPDATE CUSTOMER")){
				String[] inputInfo =  new String[4];
				inputInfo[0] = eCustomerList.getSelectedItem().toString();
				inputInfo[1] = CardNumber.getText();
				inputInfo[2] = ZipCode.getText();
				inputInfo[3] = PhoneNumber.getText();
				DbConnection connection = new DbConnection(inputInfo,5);
			}else if(actionCommand.equals("DELETE CUSTOMER")){
				String[] inputInfo =  new String[2];
				inputInfo[0] = eCustomerList.getSelectedItem().toString();
				inputInfo[1] = CardNumber.getText();
				DbConnection connection = new DbConnection(inputInfo,6);
			}else if(actionCommand.equals("CREATE BILL")){
				GenerateBill();
			}else if(actionCommand.equals("PRINT BILL")){
				printComponenet();
			}else{
				 JComboBox cb = (JComboBox)e.getSource();
			     String upInfo = (String)cb.getSelectedItem();
			     UpdateEditCustomerInfo();
			}
	}
	private void GenerateBill(){

	    //selectedRoom = "Room WHERE Room.Room_ID = \'" + Integer.toString(cb.getSelectedIndex()) + "\'";
	    String[] inputA = {"SpecialService_ID", "Room WHERE Room.Customer_ID = \'" + Integer.toString(bCustomerList.getSelectedIndex()) + "\'",
	    		"\'\'"};
		DbConnection connection = new DbConnection(inputA, 1);
		Object[] objspecialChargesIDArray = connection.arrayL.toArray();
		String[] specialChargesID = Arrays.copyOf(objspecialChargesIDArray, objspecialChargesIDArray.length, String[].class);
		String chargesS = specialChargesID[0];
		int[] intArray = new int[chargesS.length()];
		for (int i = 0 ; i < chargesS.length() ; i++){
		intArray[i] = Integer.parseInt(String.valueOf(chargesS.charAt(i)));
		intArray[i] = Integer.parseInt(String.valueOf(chargesS.charAt(i)));
		}
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM SpecialCharges WHERE ");
		int i;
		for(i = 0 ; i < intArray.length - 1 ; i++){
			query.append("SpecialCharges_ID = \'" + intArray[i] + "\' OR ");
		}
		query.append("SpecialCharges_ID = \'" + intArray[i] + "\'");
		
		inputA[0] = query.toString();
		connection = new DbConnection(inputA, 7);
		Object[] specialCInfoOb = connection.arrayL.toArray();
		String[] specialCInfoSt = Arrays.copyOf(specialCInfoOb, specialCInfoOb.length, String[].class);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(10,10,10,10);
		int strCount = 0;
		for(int k = 0 ; k < intArray.length; k ++){
		JLabel genBillLineLabel = new JLabel(specialCInfoSt[strCount]);
		strCount+=2;
		genBillLineLabel.setFont(new Font(genBillLineLabel.getName(), Font.BOLD, 20));
		gbc.gridy++;
		functionPanel.add(genBillLineLabel, gbc);
		}
		gbc.gridx = 1;
		gbc.gridy = 2;
		strCount = 1;
		int totalCost = 0;
		for(int k = 0 ; k < intArray.length; k ++){
			JLabel genBillPriceLabel = new JLabel( specialCInfoSt[strCount] + "$", SwingConstants.LEFT);
			totalCost+= Integer.parseInt(specialCInfoSt[strCount]);
			strCount+=2;
			genBillPriceLabel.setFont(new Font(genBillPriceLabel.getName(), Font.ITALIC, 20));
			gbc.gridy++;
			functionPanel.add(genBillPriceLabel, gbc);
			}
		JLabel totalCostLabel = new JLabel("Total Amount Due: " + Integer.toString(totalCost) + "$", SwingConstants.LEFT);
		totalCostLabel.setFont(new Font(totalCostLabel.getName(), Font.BOLD, 25));
		gbc.gridy++;
		functionPanel.add(totalCostLabel, gbc);
		
		JButton printBill = new JButton("PRINT BILL");
		printBill.addActionListener(this);
		printBill.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		functionPanel.add(printBill, gbc);
		
		functionPanel.revalidate();
		HotelGUI.frame.pack();
	}
	private void UpdateEditCustomerInfo() {
		String[] inputInfo = {""};
		if(eCustomerList.getSelectedItem().toString() != null)
		inputInfo[0] = eCustomerList.getSelectedItem().toString();
		DbConnection connection = new DbConnection(inputInfo, 4);
		Object[] objCustomerInfoArray = connection.arrayL.toArray();
		String[] strCustomerInfoArray = Arrays.copyOf(objCustomerInfoArray, objCustomerInfoArray.length, String[].class);
		CardNumber.setText(strCustomerInfoArray[1]);
		ZipCode.setText(strCustomerInfoArray[3]);
		PhoneNumber.setText(strCustomerInfoArray[2]);
	}
	public void printComponenet(){

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      functionPanel.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
		}
}