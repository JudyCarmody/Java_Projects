import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;

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
//		popWindow.setLayout();
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
	
	// WINDOW COLORS
	public static void mainHelp(final String[] args)
	{
		JFrame popFrame = new JFrame();
		DefaultMetalTheme dmt = new DefaultMetalTheme()
		{
		// JFrame colors/font settings
		// ACTIVE ELEMENTS
			// background
			public ColorUIResource getWindowBackground()
			{return new ColorUIResource(Color.ORANGE);}
	
			// active title bar color
			public ColorUIResource getWindowTitleBackground()
			{return new ColorUIResource(Color.GREEN);}
			
			// active title bar text
			public ColorUIResource getWindowTitleForeground()
			{return new ColorUIResource(Color.RED);}
			
			// highlight for icons -- active
			public ColorUIResource getPrimaryControl()
			{return new ColorUIResource(Color.YELLOW);}

			// the border around the frame, bumps on title bar -- active
			public ColorUIResource getPrimaryControlDarkShadow()
			{return new ColorUIResource(Color.PINK);}	

		// INACTIVE ELEMENTS
			// inactive title bar color
			public ColorUIResource getWindowTitleInactiveBackground()
			{return new ColorUIResource(shadow_drkBlue);}
			
			// inactive title text
			public ColorUIResource getWindowTitleInactiveForeground()
			{return new ColorUIResource(Color.DARK_GRAY);}
			
			// bumps on title bar -- inacitve
			public ColorUIResource getControlHighlight()
			{return new ColorUIResource(shadow_drkBlue);}
			
			// border around frame, dark parts of icons, bumps on title bar -- inactive
			public ColorUIResource getControlDarkShadow()
			{return new ColorUIResource(shadow_drkBlue);}
			
			// background of the bumpy area of the title bar -- inactive
			public ColorUIResource getControl()
			{return new ColorUIResource(shadow_drkBlue);}

		// MISC ELEMENTS
			// font of title bar
			public FontUIResource getWindowTitleFont()
			{return new FontUIResource(GUIFont);}
		};
		
		MetalLookAndFeel.setCurrentTheme(dmt);
		SwingUtilities.updateComponentTreeUI(popFrame);
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
	public static void main(final String[] args, final String message){
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				mainHelp(args);
				try{UIManager.setLookAndFeel(new MetalLookAndFeel());}
				catch(Exception e){e.printStackTrace();}
				new PopUpWindow(message);
			}
		});
		
//		PopUpWindow popUpGui = new PopUpWindow(message);
//		popUpGui.setVisible(true);
	}
}