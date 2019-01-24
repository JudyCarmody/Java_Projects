import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class DateReceiver extends JPanel implements ActionListener
{
	private JTextField da;//Date receiver
	private JButton okB;//Ok button
	private JLabel label;
	private JPanel drPanel, Bpanel;
	//public static final int LINES = 1;
	//public static final int CHAR_PER_LINE = 10;
	
	public DateReceiver()
	{
		//super("DATE RECEIVER", true, true, true, true);

		drPanel = new JPanel();
		drPanel.setSize(300,200);
		drPanel.setLayout(new BorderLayout());
	
		JLabel label = new JLabel("Enter date");
		label.setFont(new Font("", Font.PLAIN, 20));
		drPanel.add(label, BorderLayout.NORTH);
				
		da = new JTextField();//field only has one line
		drPanel.add(da, BorderLayout.CENTER);
				
		Bpanel = new JPanel();
		Bpanel.setLayout(new FlowLayout());
		
		
		okB = new JButton("Ok");
		okB.addActionListener(this);
		Bpanel.add(okB);
		
		drPanel.add(Bpanel, BorderLayout.SOUTH);
		add(drPanel);
		//setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Ok"))
		{
			FunctionPanel.dateStr = da.getText();
			//JOptionPane.showMessageDialog(this,"You typed: "+ dateStr);
		}
		
		else
			System.out.println("Error with GUI");
		
	}
	
	/*public class WindowDestroyer extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			//System.exit(0);
			dispose();
		}
	}
	
	public static void main(String[] args)
	{
		DateReveiver nda = new DateReveiver();
		//nda.setVisible(true);
	}*/

}