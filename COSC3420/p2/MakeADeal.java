/* 
 Carmody, Judy
 COSC 3420.001
 Project # 2
 DUE: February 21, 2018
 
 Description:
	Test to see if the player would win by switching.
*/

import java.util.Random;
import java.util.Scanner;

public class MakeADeal{
	private int playerTries, guess, view, prize, newGuess;
	private Scanner input = new Scanner(System.in);
	private Random diceRoll = new Random();
	
	private void begin(){
		System.out.println("Enter number of guesses: ");
		playerTries = input.nextInt();
		playGame(playerTries);
	}
	
	private void playGame(int playerTries){
		int matchCount=0;
		System.out.println("\tPrize\tGuess\tView\tNew Guess");

		for(int i=0; i<=playerTries; i++){
			prize = diceRoll.nextInt(4);	
			guess = diceRoll.nextInt(4);
			view = diceRoll.nextInt(4);
			newGuess = diceRoll.nextInt(4);
			
			// this could be done nicer. :/
			if(view==0) view++; //view = diceRoll.nextInt(4);
			if(prize==view) prize = diceRoll.nextInt(4);
			else if(guess==view) guess = diceRoll.nextInt(4);
			else if(newGuess==view) newGuess = diceRoll.nextInt(4);
					
			if(prize==0 && view!=1) prize++;
			else if(prize==0 && view==1) prize=prize+2;
			
			if(guess==0 && view!=1) guess++;
			else if(guess==0 && view==1) guess=guess+2;
			
			if(newGuess==0 && view!=1) newGuess++;
			else if(newGuess==0 && view==1) newGuess=newGuess+2;
			
			if(newGuess==prize) matchCount++;
			
			System.out.println("\t "+prize+"\t "+guess+"\t "+view+"\t"+newGuess);
		}

		double probSw = (double)matchCount/(double)playerTries;
		double probSt = 1-probSw;
		
		System.out.println();
		System.out.format("Probability of winning by switching: %.2f",probSw);
		System.out.println();
		System.out.format("Probability of winning by not switching: %.2f",probSt);
		System.out.println();
	}
	
	public static void main(String[] args){
		MakeADeal start = new MakeADeal();
		start.begin();
	}
}