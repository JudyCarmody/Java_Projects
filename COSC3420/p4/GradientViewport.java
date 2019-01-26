import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JViewport;

public class GradientViewport extends JViewport{
	private Color color1, color2;
	public GradientViewport(Color color1, Color color2){
		this.color1 = color1; this.color2 = color2;}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		GradientPaint gPaint = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2, false);	
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gPaint); g2d.fillRect(0, 0, getWidth(), getHeight());		
	}
}