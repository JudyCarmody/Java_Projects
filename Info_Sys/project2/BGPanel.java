import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class BGPanel extends JPanel
{
	private JPanel bgpanel;
	
	public BGPanel(int width, int height)
	{		
		bgpanel = new JPanel();
		bgpanel.setBackground(new Color(100, 0, 150));
		bgpanel.setPreferredSize(new Dimension(width, height));
		add(bgpanel);
	}
}