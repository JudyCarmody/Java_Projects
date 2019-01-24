import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.*;
import java.awt.*;
public class PopUpWindow extends JFrame implements ActionListener{
	
	private static final int WIDTH = 220;
	private static final int HEIGHT = 180;
	private static final int LINES = 3;
	private static final int CHAR_PER_LINE = 50;
 
 	private static Color shadow_drkBlue = new Color(0, 0, 50);
	private static Color highlight_lgtBlue = new Color(100, 100, 255);
	private static Color drkBlue = new Color(0, 0, 100);
	private static Color lgtBlue = new Color(45, 45, 255);

	private static Font GUIFont = new Font("Arial", Font.PLAIN, 20);

 	private JFrame popWindow;
	private JPanel labelPanel, buttonPanel;
	private JLabel label;
	private JButton okBTN;
	
	private String confirmSTRY = "Changes Made", confirmSTRN = "Changes not made";
 
	public PopUpWindow(String message){
		
			// Sets icon for the window
		ImageIcon hotelIcon = new ImageIcon("hotel_images/hotelIcon_16x16.png");
		setIconImage(hotelIcon.getImage());
		
		popWindow = new JFrame("Confirm");
		popWindow.setLayout(new BorderLayout());
		popWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		popWindow.setBackground(Color.MAGENTA);
		
		labelPanel = new JPanel();
		buttonPanel = new JPanel();
			
		label = new JLabel(message);
		label.setFont(GUIFont);	
		
		okBTN = new JButton("OK");
		okBTN.setToolTipText("Ok");
		okBTN.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				
				popWindow.dispose();
			}
		});
		okBTN.setFocusPainted(false);
		okBTN.setContentAreaFilled(false);
		okBTN.setMargin(new Insets(0, 0, 0, 0));
		 
  		buttonPanel.add(okBTN);
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
		if(actionCommand.equals("OK"))
			popWindow.dispose();
		else
			System.out.println("Error with gui!");
	}
 
	// MAIN METHOD
	public static void main(final String[] args, String message){		
		PopUpWindow popUpGui = new PopUpWindow(message);
		popUpGui.setVisible(true);
	}
}