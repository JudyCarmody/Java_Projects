import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopUpWindow extends JFrame implements ActionListener{
	private static final int WIDTH = 220;
	private static final int HEIGHT = 180;
	private static final int LINES = 3;
	private static final int CHAR_PER_LINE = 50;
 
 	private JFrame popWindow;
	private JPanel labelPanel, buttonPanel;
	private JTextArea theInput;
	
	private String sUpdate = "No input updated!";
	private String sMatchRecDel = "No matching record deleted!";
 
	public PopUpWindow(String message){
		popWindow = new JFrame("Pop Up Window");
		popWindow.setLayout(new BorderLayout());
		popWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		labelPanel = new JPanel(new BorderLayout());
		buttonPanel = new JPanel();

  		ImageIcon checkMark = new ImageIcon("icons/64x64_check.png");
  		JLabel labelCheckMark = new JLabel(checkMark);
	
		JLabel label = new JLabel(message);
		label.setFont(new Font(message, Font.PLAIN, 16));

  		ImageIcon xImg = new ImageIcon("icons/64x64_x.png");		
  		ImageIcon xImgOM = new ImageIcon("icons/64x64_x_onmouse.png");
		ImageIcon xImgClick = new ImageIcon("icons/64x64_x_click.png");	
		
		JButton sUpdateButton = new JButton(xImg);
		sUpdateButton.setRolloverEnabled(true);
		sUpdateButton.setRolloverIcon(xImgOM);
		sUpdateButton.setPressedIcon(xImgClick);
		sUpdateButton.setToolTipText("Close Program");		
		sUpdateButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				//sUpdate = theInput.getText();
				popWindow.dispose();
			}
		});
		sUpdateButton.setFocusPainted(false);
		sUpdateButton.setContentAreaFilled(false);
		sUpdateButton.setMargin(new Insets(0, 0, 0, 0));
		sUpdateButton.setBorder(BorderFactory.createEmptyBorder());
  
		/*JButton getButton = new JButton("Retreive");
		getButton.addActionListener(this);
		buttonPanel.add(getButton);*/
  
		/*JButton sMRDButton = new JButton("Record Match");
		sMRDButton.addActionListener(this);
		buttonPanel.add(sMRDButton);*/
  
		/*JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.BLUE);
  
		theInput = new JTextArea(LINES, CHAR_PER_LINE);
		theInput.setBackground(Color.WHITE);
		//theInput.setSize(100, 200);
		textPanel.add(theInput);
		getContentPane().add(textPanel, BorderLayout.CENTER);*/
  
  		buttonPanel.add(sUpdateButton);
		labelPanel.add(labelCheckMark, BorderLayout.WEST);
		labelPanel.add(label, BorderLayout.CENTER);
		
		popWindow.add(labelPanel, BorderLayout.NORTH);
		popWindow.add(buttonPanel, BorderLayout.SOUTH);
		popWindow.pack();
		popWindow.setLocationRelativeTo(null);
		popWindow.setVisible(true);
	}
	
	// BUTTON METHOD
	public void actionPerformed(ActionEvent e){
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Retreive"))
			theInput.setText(sUpdate);
		else
			System.out.println("Error with gui!");
	}
 
//	public class WindowDestroyer extends WindowAdapter
//	{ public void windowClosing(WindowEvent e) { System.exit(0); } }
 
	// MAIN METHOD
	public static void main(String[] args, String message){
		PopUpWindow popUpGui = new PopUpWindow(message);
		popUpGui.setVisible(true);
	}
}