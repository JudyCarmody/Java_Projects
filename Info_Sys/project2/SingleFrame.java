import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.*;

public class SingleFrame extends JInternalFrame
{
	private JPanel functionPanel;
	private int off_x = 0, off_y = 0;

	public SingleFrame(String string, int width, int height, Dimension screenSize)
	{
		super(string, true, true, true, true); // the booleans: resizable, closable, maximizable, iconifiable 
		// Menu commands
		if(string == "New Customer")
		{
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel newCust = new FunctionPanel(string, width, height);
			functionPanel.add(newCust);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
	
		else if(string == "Returning Customer")
		{
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel returnCust = new FunctionPanel(string, width, height);
			functionPanel.add(returnCust);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
	
		else if(string == "Generate Bill")
		{	
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel genBill= new FunctionPanel(string, width, height);
			functionPanel.add(genBill);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
	
		else if(string == "Make Reservation")
		{	
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel makeRes = new FunctionPanel(string, width, height);
			functionPanel.add(makeRes);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
		
		else if(string == "Lease Room")
		{	
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel leaseRm = new FunctionPanel(string, width, height);
			functionPanel.add(leaseRm);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
		else if(string == "Check Customer DB")
		{	
			off_x = screenSize.width/2;
			ImageIcon hotelIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_images/hotelIcon_16x16.png"));
			this.setFrameIcon(hotelIcon);
			functionPanel = new JPanel();
			FunctionPanel leaseRm = new FunctionPanel(string, width, height);
			functionPanel.add(leaseRm);
			add(functionPanel);
			setBounds(off_x, off_y, width+25, height);
		}
	}
}