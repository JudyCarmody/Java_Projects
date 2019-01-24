import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;

public class LotteryNumbers extends JFrame implements ActionListener
{
	public static final int WIDTH = 440;
	public static final int HEIGHT = 350;
	public static final int LINES = 1;
	public static final int CHAR_PER_LINE = 1;
	private JTextField pick1, pick2, pick3, pick4, pick5, rand1, rand2, rand3, rand4, rand5, numMatch, prizeWon;
	
	private int[] randomNum = new int[5];
	private int[] userNum = new int[5];
	private int r;
	private int u;
	private String userInput1, userInput2, userInput3, userInput4, userInput5, randomGen1, randomGen2, randomGen3, randomGen4, randomGen5;
	private int userInt1, userInt2, userInt3, userInt4, userInt5;
	
	public LotteryNumbers()
	{
		JFrame main = new JFrame("Lottery");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(new GridLayout(1, 1));
		main.setBackground(Color.gray);
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JPanel gamePanel = new JPanel(new GridLayout(2, 2));
		gamePanel.setBackground(Color.gray);
	
		// numbers
		//		user input
		JPanel userPickPanel = new JPanel();
		userPickPanel.setBackground(Color.gray);
		this.pick1 = new JTextField(1);
		this.pick2 = new JTextField(1);
		this.pick3 = new JTextField(1);
		this.pick4 = new JTextField(1);
		this.pick5 = new JTextField(1);
		
		//		random numbers
		JPanel randomNumPanel = new JPanel();
		randomNumPanel.setBackground(Color.gray);
		this.rand1 = new JTextField(1);
		this.rand2 = new JTextField(1);
		this.rand3 = new JTextField(1);
		this.rand4 = new JTextField(1);
		this.rand5 = new JTextField(1);
		
		userPickPanel.add(new JLabel("Player Numbers"));
		userPickPanel.add(pick1);
		userPickPanel.add(pick2);
		userPickPanel.add(pick3);
		userPickPanel.add(pick4);
		userPickPanel.add(pick5);
		
		randomNumPanel.add(new JLabel("Random Numbers"));
		randomNumPanel.add(rand1);
		randomNumPanel.add(rand2);
		randomNumPanel.add(rand3);
		randomNumPanel.add(rand4);
		randomNumPanel.add(rand5);
		
		// puts user input and random numbers on same panel
		JPanel numbersPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		numbersPanel.setBackground(Color.gray);
		numbersPanel.add(userPickPanel);
		numbersPanel.add(randomNumPanel);
		
		// buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.gray);
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(this);
		buttonPanel.add(playButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		buttonPanel.add(resetButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		buttonPanel.add(exitButton);
		
		// matches and prize won display
		JPanel matchDisplay = new JPanel();
		matchDisplay.setBackground(Color.gray);
		matchDisplay.add(new JLabel("Number of Matches")); 
		this.numMatch = new JTextField(1);
		matchDisplay.add(numMatch);
		
		JPanel prizeDisplay = new JPanel();
		prizeDisplay.setBackground(Color.gray);
		prizeDisplay.add(new JLabel("Prize Won")); 
		this.prizeWon = new JTextField(9);
		prizeDisplay.add(prizeWon);
		
		JPanel matchPrize = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		matchPrize.setBackground(Color.gray);
		matchPrize.add(matchDisplay);
		matchPrize.add(prizeDisplay);
		
		// prizes panel
		JPanel prizesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prizesPanel.setBackground(Color.gray);
		prizesPanel.add(new JLabel("PRIZES"));
		prizesPanel.add(new JLabel("               ")); 
		prizesPanel.add(new JLabel("0 matches = no prize"));
		prizesPanel.add(new JLabel("               ")); 
		prizesPanel.add(new JLabel("1 match = $2")); 
		prizesPanel.add(new JLabel("               "));
		prizesPanel.add(new JLabel("2 matches = $5")); 
		prizesPanel.add(new JLabel("               "));
		prizesPanel.add(new JLabel("3 matches = $10")); 
		prizesPanel.add(new JLabel("4 matches = $5,000")); 
		prizesPanel.add(new JLabel("5 matches = $50,000")); 		

		main.add(gamePanel);
		gamePanel.add(numbersPanel);
		gamePanel.add(matchPrize);
		gamePanel.add(prizesPanel);
		gamePanel.add(buttonPanel);
		
		main.pack();
		main.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		LotteryNumbers guiLottery = new LotteryNumbers();	
	}

	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Play"))
		{
			userNumGen();
			randomNumGen();			
			compareArrays(userNum, randomNum);
		}
		
		else if(actionCommand.equals("Reset"))
		{
			clearFields();
		}
		
		else if(actionCommand.equals("Exit"))
		{
			System.exit(0);
		}
		
		else
		{
			System.out.println("Error in button interface.");
		}
	}
	
	public int[] userNumGen()
	{
		userInput1 = pick1.getText();
		userInput2 = pick2.getText();
		userInput3 = pick3.getText();
		userInput4 = pick4.getText();
		userInput5 = pick5.getText();

		userInt1 = Integer.parseInt(userInput1);
		userInt2 = Integer.parseInt(userInput2);
		userInt3 = Integer.parseInt(userInput3);
		userInt4 = Integer.parseInt(userInput4);
		userInt5 = Integer.parseInt(userInput5);	

		userNum[0] = userInt1;
		userNum[1] = userInt2;
		userNum[2] = userInt3;
		userNum[3] = userInt4;
		userNum[4] = userInt5;

		return(userNum);
	}

	public int[] randomNumGen()
	{
		Random rand = new Random();
			
		for (r = 0; r < randomNum.length; r++)
		{
			randomNum[r] = rand.nextInt(10);
		}
		
		randomNumtoStr(randomNum);

		return(randomNum);
	}

	public void randomNumtoStr(int[] randomNum)
	{
		randomGen1 = Integer.toString(randomNum[0]);
		randomGen2 = Integer.toString(randomNum[1]);
		randomGen3 = Integer.toString(randomNum[2]);
		randomGen4 = Integer.toString(randomNum[3]);
		randomGen5 = Integer.toString(randomNum[4]);

		rand1.setText(randomGen1);
		rand2.setText(randomGen2);
		rand3.setText(randomGen3);
		rand4.setText(randomGen4);
		rand5.setText(randomGen5);
	}
	
	private void compareArrays(int[] userNum, int[] randomNum)
	{
		int count = 0;
		int i;
			
		for(i = 0; i < randomNum.length; i++)
		{ 
			if(userNum[i] == randomNum[i])
			{
				count++;
			}
		}
		
		prizeActual(count);
	}

	public void prizeActual(int count)
	{
		String matchStr = Integer.toString(count);
		numMatch.setText(matchStr);
		
		if (count == 0)
		{
			prizeWon.setText(" no prize");
		}	
	
		if(count == 1)
		{	
			prizeWon.setText(" $2");
		}
		
		if(count == 2)
		{
			prizeWon.setText(" $5");
		}
		
		if(count == 3)
		{
			prizeWon.setText(" $10");
		}
		
		if(count == 4)
		{	
			prizeWon.setText(" $5,000");
		}
		
		if(count == 5)
		{	
			prizeWon.setText(" $50,000");
		}
	}

	public void clearFields()
	{
		pick1.setText("");
		pick2.setText("");
		pick3.setText("");
		pick4.setText("");
		pick5.setText("");
			
		rand1.setText("");
		rand2.setText("");
		rand3.setText("");
		rand4.setText("");
		rand5.setText("");
			
		numMatch.setText("");
		prizeWon.setText("");
	}
}
