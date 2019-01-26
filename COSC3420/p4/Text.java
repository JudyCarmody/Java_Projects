/* 
 Carmody, Judy
 COSC 3420.001
 Project # 3
 DUE: March 21, 2018
 
 Description:
	Simple/basic command-line text editor.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class FileEdit {
    private String inputFile, userInput, fileName;
    private int count=0;
    private ArrayList<String> txtFileArrLi = new ArrayList<String>();
    private PrintWriter outputStream;
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        FileEdit fileEdit = new FileEdit();
        fileEdit.textFileIn();
    }

    private void textFileIn(){
        try{
		String line;
		System.out.println("Enter the file name you want to open. ");
		inputFile = keyboard.nextLine();
		fileName = inputFile;
		System.out.println();

		BufferedReader inputStream = new BufferedReader(new FileReader(inputFile));
		while((line=inputStream.readLine()) != null){txtFileArrLi.add(line);}
		for(String s : txtFileArrLi){
			System.out.println(s);
			count++;
		}
		inputStream.close();
		System.out.println("\n\nEnd of File\nTotal lines: "+count
			+"\n\nTo edit:\nI to insert text (0 to stop "
			+"entering text)\n\tExample: I or I 2\nD to delete a line\n\t"
			+"Example: D or D 2 or D 4 7\n"
			+"L to display text file\nA to add a line at the end of the "
			+"file (0 to stop entering text)\nE to save and exit\n");
		editText(txtFileArrLi);
        }
        catch(FileNotFoundException e){
		System.out.println("File "+inputFile+" not found.\n"
			+"Program ending."); System.exit(0);
        }
        catch(IOException e){
		System.out.println("Error reading from file " +inputFile+ ".\n"
			+"Program ending."); System.exit(0);
        }
    }

    private ArrayList<String> editText(ArrayList<String> txtFileArrLi){
        try{
			int nNum, mNum;
			char i, n, m;
			outputStream = new PrintWriter(new FileOutputStream(inputFile));
			String commandIn = keyboard.nextLine();
			String commands = commandIn.replaceAll("\\s+","");
				if(commands.length()==1){
				i = commands.charAt(0);
				if(i=='I' || i=='i'){insert(txtFileArrLi);}
				else if(i=='D' || i=='d'){delete(txtFileArrLi);}
				else if(i=='L' || i=='l'){list(txtFileArrLi);}
				else if(i=='A' || i=='a'){append(txtFileArrLi);}
				else if(i=='E' || i=='e'){save(txtFileArrLi);}
				else{
					System.out.println("\n\nTo edit:\nI to insert text (0 to stop "
						+"entering text)\n\tExample: I or I 2\nD to delete a "
						+"line\n\tExample: D or D 2 or D 4 7\n"
						+"L to display text file\nA to add a line at the end of the "
						+"file (0 to stop entering text)\nE to save and exit\n");
					editText(txtFileArrLi);
				}
			}
			else if(commands.length()==2){
				i = commands.charAt(0);
				n = commands.charAt(1);
				nNum = Character.getNumericValue(n);
				nNum = nNum-1;
				if(i=='I' || i=='i'){insert(txtFileArrLi, nNum);}
				else if(i=='D' || i=='d'){delete(txtFileArrLi, nNum);}
				else{
					System.out.println("\n\nTo edit:\nI to insert text (0 to stop "
						+"entering text)\n\tExample: I or I 2\nD to delete a"
						+"line\n\tExample: D or D 2 or D 4 7\n"
						+"L to display text file\nA to add a line at the end of the "
						+"file (0 to stop entering text)\nE to save and exit\n");
					editText(txtFileArrLi);
				}
			}
			else if(commands.length()==3){
				i = commands.charAt(0);
				n = commands.charAt(1);
				m = commands.charAt(2);
				nNum = Character.getNumericValue(n);
				mNum = Character.getNumericValue(m);
				nNum = nNum-1;
				mNum = mNum-1;
				if(i=='D' || i=='d'){delete(txtFileArrLi, nNum, mNum);}
			}
			else{
				System.out.println("\n\nTo edit:\nI to insert text (0 to stop "
					+"entering text)\n\tExample: I or I 2\nD to delete a"
					+"line\n\tExample: D or D 2 or D 4 7\n"
					+"L to display text file\nA to add a line at the end of the "
					+"file (0 to stop entering text)\nE to save and exit\n");
				editText(txtFileArrLi);
			}
		}
		catch(FileNotFoundException e){
		System.out.println("File "+inputFile+" not found.\n"
			+"Program ending."); System.exit(0);
			}
		return editText(txtFileArrLi);
    }

    private ArrayList<String> insert(ArrayList<String> txtFileArrLi){
		do{
			userInput = keyboard.nextLine();
			txtFileArrLi.add(userInput);
		}while(!userInput.equals("0"));
        if(userInput.equals("0")){
			outputStream.print(txtFileArrLi);
			txtFileArrLi.remove("0");
			if(userInput.equalsIgnoreCase("D")){return delete(txtFileArrLi);}
			return editText(txtFileArrLi);
        }
        else return insert(txtFileArrLi);
    }

    private ArrayList<String> delete(ArrayList<String> txtFileArrLi){
		int arrSize = txtFileArrLi.size();
		txtFileArrLi.remove(arrSize-1);
		return editText(txtFileArrLi);
    }

    private ArrayList<String> list(ArrayList<String> txtFileArrLi){
        for(String s : txtFileArrLi){System.out.println(s);}
        return editText(txtFileArrLi);
    }

    private ArrayList<String> append(ArrayList<String> txtFileArrLi){
        userInput = keyboard.nextLine();
        int arrSize = txtFileArrLi.size();
        txtFileArrLi.add(arrSize, userInput);
        if(userInput.equals("0")){
			outputStream.print(txtFileArrLi);
			txtFileArrLi.remove("0");
			return editText(txtFileArrLi);
        }
        return append(txtFileArrLi);
    }

    private void save(ArrayList<String> txtFileArrLi){
        for(String s : txtFileArrLi) outputStream.print(s + "\n");
        outputStream.close(); keyboard.close();
        System.out.println("\nFile saved to: "+fileName+"\nProgram Ending.");
        System.exit(0);
    }

    private ArrayList<String> insert(ArrayList<String> txtFileArrLi, int n){
		do{
			userInput = keyboard.nextLine();
			txtFileArrLi.add(n, userInput);
			n++;
		}while(!userInput.equals("0"));
		if(userInput.equals("0")){
			txtFileArrLi.remove("0");
			outputStream.print(txtFileArrLi);	
			if(userInput.equalsIgnoreCase("D")){return delete(txtFileArrLi, n);}
			else return editText(txtFileArrLi);
		}
        else return insert(txtFileArrLi, (count+n));
    }

    private ArrayList<String> delete(ArrayList<String> txtFileArrLi, int n){
		txtFileArrLi.remove(n);
        return editText(txtFileArrLi);
    }
    
    private ArrayList<String> delete(ArrayList<String> txtFileArrLi, int n, int m){
		txtFileArrLi.subList(n,m).clear();
		return editText(txtFileArrLi);
	}
}
