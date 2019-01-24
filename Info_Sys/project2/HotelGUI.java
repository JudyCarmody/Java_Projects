import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;

import java.awt.event.*;
import java.awt.*;

public class HotelGUI extends JFrame implements ActionListener {
	public final int INTFRAME_W = 450;
	public final int INTFRAME_H = 600;
	public static JDesktopPane desktop;
	public Dimension screenSize;
	
	private static Font menuFont = new Font("Arial", Font.PLAIN, 18);
	private static Font GUIFont = new Font("Arial", Font.PLAIN, 20);
	private final Image hotelBG;
	private JMenuItem oMenuItem, rMenuItem, bMenuItem;

	private static Color shadow_drkBlue = new Color(0, 0, 50);
	private static Color highlight_lgtBlue = new Color(100, 100, 255);
	private static Color drkBlue = new Color(0, 0, 100);
	private static Color lgtBlue = new Color(45, 45, 255);
	public static String[] roomInfo1 = new String[8];
	public static SingleFrame frame;
	  
	
	public HotelGUI()
	{
		super("Sugar Daddy's No-Tell Motel Hotel and Resort");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int inset = 0;		
		int width = screenSize.width  - inset*2;
		int height = screenSize.height - inset*2;
		setBounds(inset, inset, width, height);
		
		// Sets icon for the window
		ImageIcon hotelIcon = new ImageIcon("hotel_images/hotelIcon_16x16.png");
		setIconImage(hotelIcon.getImage());
		
		// background for JDesktop
		ImageIcon background = new ImageIcon("hotel_images/hotelBG.png");
		Image hotelBG_scale = background.getImage();
		hotelBG = hotelBG_scale.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		
		//Set up the GUI.
		desktop = new JDesktopPane() //a specialized layered pane for JInternalFrame
		{
			@Override
			protected void paintComponent(Graphics graphics)
			{
				super.paintComponent(graphics);
				graphics.drawImage(hotelBG, 0, 0, this);
			}
		};
		
		RoomStatusPanels roomP = new RoomStatusPanels(); // Creates the back panels which show the statues of the hotels rooms
		/*
		// CHECK AVAILABLITY PANEL, running in background of GUI
		JPanel backgroundPanel = new JPanel();
		BGPanel bgpanel = new BGPanel(width/2, height);
		backgroundPanel.add(bgpanel);
		backgroundPanel.setBackground(Color.MAGENTA);
		backgroundPanel.setSize(width/2, height);
		backgroundPanel.setVisible(true);
		desktop.add(backgroundPanel);*/
		
//		desktop.setBackground(Color.BLACK); // background of main window
		setContentPane(desktop);
		setJMenuBar(createMenuBar());

		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}
	
	public static void mainHelp(final String[] args)
	{
		JFrame frame = new JFrame();
		DefaultMetalTheme dmt = new DefaultMetalTheme()
		{
		// JFrame colors/font settings
		// ACTIVE ELEMENTS
			// background
			public ColorUIResource getWindowBackground()
			{return new ColorUIResource(Color.GREEN);}
	
			// active title bar color
			public ColorUIResource getWindowTitleBackground()
			{return new ColorUIResource(highlight_lgtBlue);}
			
			// active title bar text
			public ColorUIResource getWindowTitleForeground()
			{return new ColorUIResource(shadow_drkBlue);}
			
			// highlight for icons -- active
			public ColorUIResource getPrimaryControl()
			{return new ColorUIResource(highlight_lgtBlue);}

			// the border around the frame, bumps on title bar -- active
			public ColorUIResource getPrimaryControlDarkShadow()
			{return new ColorUIResource(drkBlue);}	

		// MENU
			// menu in top left corner highlight, and dots on active title bar
			public ColorUIResource getPrimaryControlHighlight()
			{return new ColorUIResource(highlight_lgtBlue);}
			
			// menu background
			public ColorUIResource getMenuBackground()
			{return new ColorUIResource(highlight_lgtBlue);}
			
			// menu text color
			public ColorUIResource getMenuForeground()
			{return new ColorUIResource(Color.BLACK);}
			
			// menu text font
			public FontUIResource getMenuTextFont()
			{return new FontUIResource(menuFont);}
			
			// menu disabled menu items
			public ColorUIResource getMenuDisabledForeground()
			{return new ColorUIResource(Color.DARK_GRAY);}
			
			// menu selected item background
			public ColorUIResource getMenuSelectedBackground()
			{return new ColorUIResource(shadow_drkBlue);}
			
			// menu selected item foreground
			public ColorUIResource getMenuSelectedForeground()
			{return new ColorUIResource(Color.WHITE);}
			
		// INACTIVE LEMENTS
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
		SwingUtilities.updateComponentTreeUI(frame);
	}

	// CREATE MENU BAR
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Letters Used: B,E,L,M,N,O,Q,R

		// file menu
		JMenu optionsMenu = new JMenu("File");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(optionsMenu);

			// check availability
		oMenuItem = new JMenuItem("New Customer");
		oMenuItem.setMnemonic(KeyEvent.VK_N);
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		oMenuItem.setActionCommand("new");
		oMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont); // keyboard shortcut font size
		optionsMenu.add(oMenuItem);

			// make reservation
		oMenuItem = new JMenuItem("Returning Customer");
		oMenuItem.setMnemonic(KeyEvent.VK_E);
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		oMenuItem.setActionCommand("return");
		oMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		optionsMenu.add(oMenuItem);
		
		// Check the customer database
		oMenuItem = new JMenuItem("Check Customer DB");
		oMenuItem.setMnemonic(KeyEvent.VK_D);
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		oMenuItem.setActionCommand("database check");
		oMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		optionsMenu.add(oMenuItem);


			// exit application
		oMenuItem = new JMenuItem("Quit");
		oMenuItem.setMnemonic(KeyEvent.VK_Q);
		oMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		oMenuItem.setActionCommand("quit");
		oMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		optionsMenu.add(oMenuItem);
		
		// bill menu
		JMenu billMenu = new JMenu("Bill");
		billMenu.setMnemonic(KeyEvent.VK_B);
		menuBar.add(billMenu);
		
			// generate bill
		bMenuItem = new JMenuItem("Generate Bill");
		bMenuItem.setMnemonic(KeyEvent.VK_B);
		bMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
		bMenuItem.setActionCommand("bill");
		bMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		billMenu.add(bMenuItem);
		
		// rooms menu
		JMenu roomMenu = new JMenu("Rooms");
		roomMenu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(roomMenu);
		
			// make reservation: short term
		rMenuItem = new JMenuItem("Make Reservation");
		rMenuItem.setMnemonic(KeyEvent.VK_R);
		rMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		rMenuItem.setActionCommand("reservation");
		rMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		roomMenu.add(rMenuItem);
		
			// make reservation: long-term
		rMenuItem = new JMenuItem("Lease Room");
		rMenuItem.setMnemonic(KeyEvent.VK_L);
		rMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		rMenuItem.setActionCommand("lease");
		rMenuItem.addActionListener(this);
		UIManager.put("MenuItem.acceleratorFont", menuFont);
		roomMenu.add(rMenuItem);
		return menuBar;
	}

	// Menu actions
	public void actionPerformed(ActionEvent e) {
		if ("new".equals(e.getActionCommand())) {createFrame("New Customer");}
		else if ("return".equals(e.getActionCommand())) {createFrame("Returning Customer");}
		else if ("bill".equals(e.getActionCommand())) {createFrame("Generate Bill");}
		else if ("reservation".equals(e.getActionCommand())) {createFrame("Make Reservation");}
		else if ("lease".equals(e.getActionCommand())) {createFrame("Lease Room");}
		else if ("database check".equals(e.getActionCommand())) {createFrame("Check Customer DB");}
		else {quit();}
	}

	// create new frame, keyboard/menu
	protected void createFrame(String string) {
		frame = new SingleFrame(string, INTFRAME_W, INTFRAME_H, screenSize);
		frame.pack();
		frame.setVisible(true);
		desktop.add(frame);
		try {frame.setSelected(true);}
		catch (java.beans.PropertyVetoException e) {}
	}
	
	// exit
	protected void quit() {System.exit(0);}

	// DISPLAY GUI
	private static void createAndShowGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		HotelGUI frame = new HotelGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// MAIN METHOD
	public static void main(final String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				mainHelp(args);
				try{UIManager.setLookAndFeel(new MetalLookAndFeel());}
				catch(Exception e){e.printStackTrace();}
				UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", Color.LIGHT_GRAY); // color of keyboard shortcuts in menu, not highlighted
				UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorSelectionForeground", Color.GRAY); // color of keyboard shortcuts in menu, highlighted
				new HotelGUI();
				createAndShowGUI();
			}
		});
	}
}
