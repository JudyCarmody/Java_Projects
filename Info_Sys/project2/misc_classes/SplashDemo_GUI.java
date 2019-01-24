// https://docs.oracle.com/javase/tutorial/uiswing/misc/splashscreen.html

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
 
public class SplashDemo extends Frame implements ActionListener {
	private static Font GUIFont = new Font("Arial", Font.PLAIN, 20);
	private static Color shadow_drkBlue = new Color(0, 0, 50);
	private static Color highlight_lgtBlue = new Color(100, 100, 255);
	private static Color drkBlue = new Color(0, 0, 100);
	private static Color lgtBlue = new Color(45, 45, 255);
	
    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"Customer Database", "Occupied Rooms", "Available Rooms", "Rooms being cleaned or need to be cleaned","Amenities"};
        //g.setComposite(AlphaComposite.Clear);
        g.setColor(Color.BLACK);
		g.fillRect(60,375,375,45);
        g.setPaintMode();
        g.setColor(Color.WHITE);
        g.drawString("Loading "+comps[(frame/5)%5]+"...", 65, 400);
    }

    public SplashDemo() {
        HotelGUI runGUI = new HotelGUI();
		runGUI.setVisible(true);
        this.addWindowListener(closeWindow);
 
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<100; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
        setVisible(true);
        toFront();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
     
    private static WindowListener closeWindow = new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
        }
    };
     
	 
	public static void mainHelp(final String[] args)
	{
		JFrame frame = new JFrame();
		DefaultMetalTheme dmt = new DefaultMetalTheme()
		{
		// JFrame colors/font settings
		// ACTIVE ELEMENTS
			// background
			public ColorUIResource getWindowBackground()
			{return new ColorUIResource(Color.BLACK);}
	
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
		SwingUtilities.updateComponentTreeUI(frame);
	}

    public static void main (final String args[]) {
				SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				mainHelp(args);
				try{UIManager.setLookAndFeel(new MetalLookAndFeel());}
				catch(Exception e){e.printStackTrace();}
				SplashDemo test = new SplashDemo();
			}
		});
    }
}