// https://docs.oracle.com/javase/tutorial/uiswing/misc/splashscreen.html

import java.awt.*;
import java.awt.event.*;
 
public class SplashDemo extends Frame implements ActionListener {
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
        super("SplashScreen demo");
        setSize(300, 200);
        setLayout(new BorderLayout());
        Menu m1 = new Menu("File");
        MenuItem mi1 = new MenuItem("Exit");
        m1.add(mi1);
        mi1.addActionListener(this);
        this.addWindowListener(closeWindow);
 
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        mb.add(m1);
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
     
    public static void main (String args[]) {
        SplashDemo test = new SplashDemo();
    }
}