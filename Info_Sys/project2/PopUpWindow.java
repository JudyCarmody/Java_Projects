import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;

import java.awt.event.*;
import java.awt.*;
public class PopUpWindow extends JFrame implements ActionListener{
	
	private static final int WIDTH = 275;
	private static final int HEIGHT = 150;
	private static final int LINES = 3;
	private static final int CHAR_PER_LINE = 50;
 
 	private static Color shadow_drkBlue = new Color(0, 0, 50);
	private static Color highlight_lgtBlue = new Color(100, 100, 255);
	private static Color drkBlue = new Color(0, 0, 100);
	private static Color lgtBlue = new Color(45, 45, 255);

	private static Font GUIFont = new Font("Arial", Font.PLAIN, 18);

 	private JFrame popWindow;
	private JPanel labelPanel, buttonPanel;
	private JLabel label;
	private JButton okBTN;
 
	public PopUpWindow(String message){
		
			// Sets icon for the window
		ImageIcon hotelIcon = new ImageIcon("hotel_images/hotelIcon_16x16.png");
		setIconImage(hotelIcon.getImage());
		
		popWindow = new JFrame("Confirm");
		popWindow.setLayout(new BorderLayout());
		//popWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		popWindow.setResizable(true);
		
		labelPanel = new JPanel();
		buttonPanel = new JPanel();
		
		labelPanel.setBackground(highlight_lgtBlue);
		buttonPanel.setBackground(highlight_lgtBlue);
			
		label = new JLabel(message);
		label.setForeground(shadow_drkBlue);
		label.setFont(GUIFont);	
		
		okBTN = new JButton("OK");
		okBTN.setBackground(lgtBlue);
		okBTN.setFont(GUIFont);
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
		labelPanel.add(label);
		
		popWindow.add(labelPanel, BorderLayout.CENTER);
		popWindow.add(buttonPanel, BorderLayout.SOUTH);
		popWindow.pack();
		popWindow.setLocationRelativeTo(null);
		popWindow.setVisible(true);
	}
	
	// WINDOW COLORS
	/* For some reason, these use the colors from the main GUI, so the colors here
		are arbitrary.
	*/
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
			{return new ColorUIResource(Color.GRAY);}
			
			// inactive title text
			public ColorUIResource getWindowTitleInactiveForeground()
			{return new ColorUIResource(Color.DARK_GRAY);}
			
			// bumps on title bar -- inacitve
			public ColorUIResource getControlHighlight()
			{return new ColorUIResource(Color.LIGHT_GRAY);}
			
			// border around frame, dark parts of icons, bumps on title bar -- inactive
			public ColorUIResource getControlDarkShadow()
			{return new ColorUIResource(Color.MAGENTA);}
			
			// background of the bumpy area of the title bar -- inactive
			public ColorUIResource getControl()
			{return new ColorUIResource(Color.BLUE);}

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