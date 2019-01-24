import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class RoomStatusPanels extends JFrame implements ActionListener {
	JPanel roomsMPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	private String[] inputInfo = new String[2];
	private JLabel 	occLabel, numOccLabel, cleanLabel, cusNameLabel, roomTypeLabel, dateLabel;
	public RoomStatusPanels(){
	
	//ADDDING ROOM STATUES DISPLAYS (ABRAHAM CODE)
	 // Master panel for room panels.
	roomsMPanel.setBackground(Color.WHITE);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int inset = 0;		
	int width = screenSize.width  - inset*2;
	int height = screenSize.height - inset*2;
	roomsMPanel.setSize(width/2, height-100);
	
	//sends the string inputinfo and a sql type number to db conncetion which returns information to fill string[]
	inputInfo[0] = "Room_ID";
	inputInfo[1] = "1";
	DbConnection connection = new DbConnection(inputInfo, 0);
	String[] receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	/*HotelGUI.roomInfo1[0] = connection.CheckedIn;
	HotelGUI.roomInfo1[1] = connection.NumberOcc;
	HotelGUI.roomInfo1[2] = connection.Clean;
	HotelGUI.roomInfo1[3] = connection.CustomerN;
	HotelGUI.roomInfo1[4] = connection.RoomTypeName;
	HotelGUI.roomInfo1[5] = connection.Date;*/
	CreateRoom(inputInfo, 0, 0);
	
	inputInfo[1] = "2";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 1, 0);
	
	inputInfo[1] = "3";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 2, 0);
	
	inputInfo[1] = "4";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 0,1);
	
	inputInfo[1] = "5";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 1,1);
	
	inputInfo[1] = "6";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 2,1);
	
	inputInfo[1] = "7";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 0,2);
	
	inputInfo[1] = "8";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 1,2);
	
	inputInfo[1] = "9";
	connection = new DbConnection(inputInfo, 0);
	receivedInfo = connection.ProcessResults();
	for(int i = 0; i < receivedInfo.length; i++){
		HotelGUI.roomInfo1[i] = receivedInfo[i];
	}
	CreateRoom(inputInfo, 2,2);
	
	gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.gridheight = 2;
	JButton fUpdateB = new JButton("UPDATE ROOMS");
	fUpdateB.addActionListener(this);
	fUpdateB.setBackground(Color.white);
	roomsMPanel.add(fUpdateB, gbc);
	
	Border blackline = BorderFactory.createLineBorder(Color.black,5);
	roomsMPanel.setBorder(blackline);

	HotelGUI.desktop.add(roomsMPanel);
	roomsMPanel.setVisible(true);
	
}
	
	void CreateRoom(String[] input, int row, int column){
		JPanel roomPanel = new JPanel(new GridBagLayout()); //Room 1 panel with labels and update button
		GridBagConstraints roomPanelgbc = new GridBagConstraints();
		roomPanelgbc.anchor = GridBagConstraints.FIRST_LINE_START;
		roomPanelgbc.fill = GridBagConstraints.HORIZONTAL;
		roomPanelgbc.weightx = 0.5;
		roomPanelgbc.weighty = 0.5;
		roomPanelgbc.gridx = 0;
		roomPanelgbc.gridy = 0;
		roomPanelgbc.insets = new Insets(10,5,10,5);
		JLabel roomLabel = new JLabel("ROOM " + inputInfo[1], SwingConstants.CENTER);
		roomLabel.setFont(new Font(roomLabel.getName(), Font.BOLD, 40));
		roomLabel.setBorder(BorderFactory.createMatteBorder(5,5,2,2, Color.black));
		roomPanel.add(roomLabel,roomPanelgbc);
		//information made from dbconnection retuened string[]
		roomPanelgbc.gridy = 1;
		roomPanel.add(new JLabel("Occupied: " + HotelGUI.roomInfo1[0]),roomPanelgbc);
		roomPanelgbc.gridy = 2;
		roomPanel.add(new JLabel("Number Occupied: " + HotelGUI.roomInfo1[1]),roomPanelgbc);
		roomPanelgbc.gridy = 3;
		roomPanel.add(new JLabel("Clean: " + HotelGUI.roomInfo1[2]),roomPanelgbc);
		roomPanelgbc.gridy = 4;
		roomPanel.add(new JLabel("Customer Name: " + HotelGUI.roomInfo1[3]),roomPanelgbc);
		roomPanelgbc.gridy = 5;
		roomPanel.add(new JLabel("Room Type: " + HotelGUI.roomInfo1[4]),roomPanelgbc);
		roomPanelgbc.gridy = 6;
		roomPanel.add(new JLabel("Date Checked In: " + HotelGUI.roomInfo1[5]),roomPanelgbc);
		roomPanel.validate();
		roomPanel.setBackground(Color.LIGHT_GRAY);
		Color bColor = new Color(58,107,207);
		Border border = BorderFactory.createLineBorder(bColor, 5);
		roomPanel.setBorder(border);
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START; //Start to adding room 1 panel to master room panel
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.insets = new Insets(5,5,5,5);
		roomsMPanel.add(roomPanel, gbc);
	}
	
@Override
	public void actionPerformed(ActionEvent e) {
	String[] inputInfo = {"",""};
	String actionCommand = e.getActionCommand();
	if (actionCommand.equals("UPDATE ROOMS")) {
		roomsMPanel.revalidate();
		}
	else if (actionCommand.equals("UPDATE ROOM 2")) {

		}
	else if (actionCommand.equals("UPDATE ROOM 3")) {

		}
	}
}
