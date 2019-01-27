import java.awt.*;
import javax.swing.*;

public class CustomPanel extends JPanel{
	private Color color1, color2;
	public CustomPanel(Color color1, Color color2){
		this.color1 = color1; this.color2 = color2;
		setOpaque(false);
	}
	
	public CustomPanel(LayoutManager layout, Color color1, Color color2){
		super(layout); 
		this.color1 = color1; this.color2 = color2;
		setOpaque(false);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		Paint gradient = new GradientPaint(0, 0, color1, width, height, color2);
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, width, height);
	}
}