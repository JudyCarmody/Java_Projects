/*
 Carmody, Judy
 COSC 2430.001
 Project # 3
 Due: March 4, 2015
 Tic-Tac-Toe game.  Two human players; player one is X.
*/

import java.util.Scanner;

public class TicTacToe
{	
	private char[][] board = new char[3][3];
	private char player = 'X';
	private int freeSpaces = 9;
	private char answer;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		TicTacToe game = new TicTacToe();
		game.newGame();
	}

	public void newGame()
	{
		clearBoard(board);
		do
		{
			printBoard(board);
			System.out.println("Player: " + player);
			System.out.print("Enter a row (1-3) and a column (1-3): ");
			int row = input.nextInt() - 1;
			int col = input.nextInt() - 1;
			System.out.println();
				
			if (board[row][col] != ' ')
			{
				System.out.println("Illegal move.  Try again.");
			}

			else
			{
				board[row][col] = player;
				char isWinner = checkWinner(board);
				freeSpaces--;
	
				if (isWinner != ' ')
				{
					printBoard(board);
					System.out.println("We have a winner! " + isWinner);
					System.out.println();
					clearBoard(board);

					System.out.println("Would you like you play again? (y/n)");
					answer = input.next().charAt(0);

					if (answer != 'y' && answer != 'Y')
					{
						System.exit(0);
					}
				}
	
				else if (freeSpaces == 0)
				{
					System.out.println("It is a tie.");
					System.out.println();	
					clearBoard(board);

					System.out.println("Would you like you play again? (y/n)");
					answer = input.next().charAt(0);

					if (answer != 'y' && answer != 'Y')
					{
						System.exit(0);
					}
				}

				else
				{
					if (player == 'X')
					{
						player = 'O';
					}
				
					else
					{
						player = 'X';
					}
				}
			}
		}while(true);
	}

	private static char checkWinner(char[][] checkBoard)
	{
		for (int row = 0; row < checkBoard.length; row++)
		{
			if (checkBoard[row][0] == checkBoard[row][1] && checkBoard[row][1] == checkBoard[row][2])
			{
				return checkBoard[row][0];
			}
		}

		for (int col = 0; col < checkBoard.length; col++)
		{
			if (checkBoard[0][col] == checkBoard[1][col] && checkBoard[1][col] == checkBoard[2][col])
			{
				return checkBoard[0][col];
			}
		}

		if (checkBoard[0][0] == checkBoard[1][1] && checkBoard[1][1] == checkBoard[2][2])
		{
			return checkBoard[0][0];
		}

		if (checkBoard[0][2] == checkBoard[1][1] && checkBoard[1][1] == checkBoard[2][0])
		{
			return checkBoard[0][2];
		}

		return ' ';
	}

	private void clearBoard(char[][] board)
	{
		for (int r = 0; r < 3; r++)
		{
			for (int c = 0; c < 3; c++)
			{
				board[r][c] = ' ';
			}
		}
	}

	private static void printBoard(char[][] board)
	{
		System.out.println("---------------");
		System.out.println("  | 1 | 2 | 3 |");
		System.out.println("---------------");
		System.out.println("|1| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " | ");
		System.out.println("---------------");
		System.out.println("|2| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " | ");
		System.out.println("---------------");
		System.out.println("|3| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " | ");
		System.out.println("---------------");
	}
}
