/*
 Carmody, Judy
 Tahamtani, Behrang
 COSC 3420.001
 Project # 4
 Due: April 4, 2018
 
 Assigns science fair judges to projects.
*/

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class JudgeListGenerator extends JFrame implements ActionListener{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Color BURNT_ORANGE = new Color(221,89,0);
    public static final Color LT_ORANGE = new Color(255,115,40);
    public static final Color DRK_ORANGE_2 = new Color(125,50,0);
	
	private JButton genListBtn, clearBtn, helpBtn, saveBtn, exitBtn;
    private JTextField projectList, judgeList;
    private JTextArea textDisplay;
	private JPanel contentPanel, functionPanel, listPanel, displayPanel, buttonPanel, pListPanel, jListPanel;
    private String projectListIn, judgeListIn, lineP, lineJ;
    private PrintWriter outputStream;
    private BufferedReader projectInputStream, judgeInputStream;
    private int count=0;
    private ArrayList<String> projectArrLi = new ArrayList<String>();
    private ArrayList<String> judgeArrLi = new ArrayList<String>();
	
    public JudgeListGenerator(){
    
        // JFrame
        JFrame main = new JFrame("Java File Checker");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new GridLayout(1,1));
        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        main.setResizable(false);
        
        // JPanels
        contentPanel = new JPanel(new BorderLayout());
        functionPanel = new JPanel(new BorderLayout());
        listPanel = new JPanel(new BorderLayout());
        displayPanel = new JPanel();
        buttonPanel = new JPanel();
        pListPanel = new JPanel();
        jListPanel = new JPanel();
        
        // text display - JTextArea inside JScrollPane
        textDisplay = new JTextArea();
        textDisplay.setEditable(false);
		textDisplay.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textDisplay);
        GradientViewport viewport = new GradientViewport(BURNT_ORANGE, DRK_ORANGE_2);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(700,450));
        
        textDisplay.setOpaque(false);
        viewport.setView(textDisplay);
        scrollPane.setViewport(viewport);
        add(scrollPane);        
        
        // buttons
        JButton genListBtn = new JButton("Assign Judges");
        genListBtn.addActionListener(this);
        JButton helpBtn = new JButton("Help");
        helpBtn.addActionListener(this);
        JButton saveBtn = new JButton("Save File");
        saveBtn.addActionListener(this);
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(this);
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(this);
        
        // button tooltips
        genListBtn.setToolTipText("Import file into program");
        helpBtn.setToolTipText("Help for errors");
        saveBtn.setToolTipText("Save file");
        clearBtn.setToolTipText("Clear fields");
        exitBtn.setToolTipText("Exit Program");
        
        // text field - input/output
        this.projectList = new JTextField(18);
        projectList.setCaretPosition(0);
        projectList.setCaretColor(LT_ORANGE);
		projectList.getCaret().setBlinkRate(50);
        JLabel proLabel = new JLabel("Project List");
        pListPanel.add(proLabel);
        pListPanel.add(projectList);
        
        this.judgeList = new JTextField(18);
        judgeList.setCaretPosition(0);
        judgeList.setCaretColor(LT_ORANGE);
		judgeList.getCaret().setBlinkRate(50);
        JLabel judgeLabel = new JLabel("Judge List");
        jListPanel.add(judgeLabel);
        jListPanel.add(judgeList);
        
        // borders
        Border thinBorder = BorderFactory.createLineBorder(Color.WHITE, 1);
        projectList.setBorder(thinBorder);
        judgeList.setBorder(thinBorder);
        textDisplay.setBorder(thinBorder);
		scrollPane.setBorder(thinBorder);
        
        // background colors
        projectList.setBackground(DRK_ORANGE_2);
        judgeList.setBackground(DRK_ORANGE_2);
        genListBtn.setBackground(LT_ORANGE);
        helpBtn.setBackground(LT_ORANGE);
        saveBtn.setBackground(LT_ORANGE);
        clearBtn.setBackground(LT_ORANGE);
        exitBtn.setBackground(LT_ORANGE);
		listPanel.setBackground(BURNT_ORANGE);
		displayPanel.setBackground(BURNT_ORANGE);
		buttonPanel.setBackground(BURNT_ORANGE);
		pListPanel.setBackground(BURNT_ORANGE);
		jListPanel.setBackground(BURNT_ORANGE);
        
        // foreground colors
        textDisplay.setForeground(Color.LIGHT_GRAY);
        judgeList.setForeground(Color.WHITE);
        projectList.setForeground(Color.WHITE);
        proLabel.setForeground(Color.WHITE);
        judgeLabel.setForeground(Color.WHITE);
        genListBtn.setForeground(Color.BLACK);
        helpBtn.setForeground(Color.BLACK);
        saveBtn.setForeground(Color.BLACK);
        clearBtn.setForeground(Color.BLACK);
        exitBtn.setForeground(Color.BLACK);
        
        // text styles
        judgeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        proLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        projectList.setFont(new Font("Verdana", Font.PLAIN, 16));
        judgeList.setFont(new Font("Verdana", Font.PLAIN, 16));
        textDisplay.setFont(new Font("Verdana", Font.PLAIN, 16));
        genListBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        helpBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        saveBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        clearBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        exitBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        
        // adding the components together
        buttonPanel.add(genListBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(helpBtn);
        buttonPanel.add(exitBtn);
        displayPanel.add(scrollPane);
        listPanel.add(pListPanel, BorderLayout.WEST);
        listPanel.add(jListPanel, BorderLayout.EAST);
        
        // putting it all together
        main.add(contentPanel);
        functionPanel.add(listPanel, BorderLayout.NORTH);
        functionPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(functionPanel, BorderLayout.NORTH);
        contentPanel.add(displayPanel, BorderLayout.CENTER);
        main.pack();
        main.setVisible(true);
        projectList.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if(actionCommand.equals("Assign Judges")){generateList();}
        else if(actionCommand.equals("Save File")){saveFile(projectArrLi, judgeArrLi);}
        else if(actionCommand.equals("Clear")){clearFields();}
        else if(actionCommand.equals("Help")){help();}
        else if(actionCommand.equals("Exit")){System.exit(0);}
        else{System.out.println("Error in button interface, program ending.");
            System.exit(0);
        }
    }
    
    // Assign Judges
    private void generateList(){
        projectListIn = projectList.getText();
        judgeListIn = judgeList.getText();
        
        try{
            projectInputStream = new BufferedReader(new FileReader(projectListIn));
            judgeInputStream = new BufferedReader(new FileReader(judgeListIn));
			
            while((lineP=projectInputStream.readLine()) != null){projectArrLi.add(lineP);}
            for(String s : projectArrLi){textDisplay.append(s+"\n");}
			
			while((lineJ=judgeInputStream.readLine()) != null){judgeArrLi.add(lineJ);}
            for(String s : judgeArrLi){textDisplay.append(s+"\n");}
            projectInputStream.close(); judgeInputStream.close();
        }catch(FileNotFoundException e){
            System.out.println("File "+projectList+" not found.\n"+"Program ending.");
            System.exit(0);
        }catch(IOException e){
            System.out.println("Error reading from file " +projectList+ ".\n"
                +"Program ending."); System.exit(0);
        }
    }
	
	// help button
	private void help(){
		textDisplay.setText("In the Project List, type the name of the file"
		+"that has the list of projects.\n\nIn the Judge List, type the name of"
		+"the file that has the list of judges names and what subjects they can"
		+" judge in\n\nExample:\nProject List: project.txt\nJudge List: judges.txt"
		+"\n\n The files need to be in the same directory as this program.\n\n"
		+"Click \"Assign Judges\" to assign judges to projects.\n\nThe generated"
		+" assignment file will be in the same directory as this program under "
		+"the name Judge_Assignment.txt");
	}
	
    // clear fields
    private void clearFields(){projectList.setText(""); judgeList.setText(""); textDisplay.setText("");}
    
    // save file and exit
    private void saveFile(ArrayList<String> projectArrLi, ArrayList<String> judgeArrLi){
        try{
            PrintWriter outputStream = new PrintWriter("Judge_Assignment.txt");
            String stringToOutputAsFile = null;
           
            for (int i = 0; i <= (projectArrLi.size() - 1); i++){
                if (stringToOutputAsFile != null) stringToOutputAsFile += projectArrLi.get(i) + "\n";
                else stringToOutputAsFile = projectArrLi.get(i) + "\n";
            }
            outputStream.println(stringToOutputAsFile); System.out.println("\n");
            System.out.println(stringToOutputAsFile); outputStream.close();
        }
        catch (IOException e){System.out.println("Your file could not be created.\n"); System.exit(0);}          
    }
     
    // MAIN METHOD
    public static void main(String[] args){JudgeListGenerator JudgeListGenerator = new JudgeListGenerator();}
}