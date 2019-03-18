package ConnectFour;
import BoardGameEnvironment.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConnectFourGame extends Game
{
	public static final int yellow = 1;
	public static final int red = 2;
	static final int FOUR = 4;
	final int[]d1 = {-1,0};
	final int[]d2 = {-1,1};
	final int[]d3 = {1,1};
	final int[]d4 = {1,0};
	final int[]d5 = {1,-1};
	final int[]d6 = {0,1};
	final int[]d7 = {-1,-1};
	//{0,-1} not needed, because the pieces are dropped from top
	
	int width = 8;
	int height = 8;
	public int[]yvalues;
	int piecesOnBoard;
	
	@SuppressWarnings("serial")
	final ArrayList<int[]>directions = new ArrayList<int[]>() {
		{
			add(d1);add(d2);add(d3);add(d4);add(d5);add(d6);add(d7);
		}
	};
	
	boolean isWin;
	
	public ConnectFourGame(User[] players) {
		super(players);
		this.board = new ConnectFourBoard(this.width, this.height, "connectfour");  //default board is 8x8
		this.turn = yellow; //yellow always goes first
		this.isWin = false;
		this.piecesOnBoard = 0;
		
		yvalues = new int[this.width];
		Arrays.fill(yvalues, this.height-1);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run()
	{
	}
	
	public User[] getPlayers()
	{
		return this.players;
	}
	public GameBoard getBoard()
	{
		return this.board;
	}
	public void run(int x)
	{
		int y = this.yvalues[x];
		makeMove(x,y);
		this.checkWin(x, y);
		if(this.isWin || this.isFull())
		{
			return;		 
		}
		
		this.decYValues(x);
		this.changeTurn();
		
	
	}
	
	public void console_run() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int x;
		System.out.println(this.board);
		System.out.println("It is "+ this.getPlayer().getName() + "(" + this.getColor() +")"
				+ "\'s turn. Please make your move.");
		while(!this.isFull()) {
			x = input.nextInt();
			input.nextLine();
			if(this.validX(x))
			{	
				int y = this.yvalues[x];
				makeMove(x,y);
				this.checkWin(x, y);
				System.out.println(this.board);
				if(this.isWin)
				{
					User winner = this.getPlayer();
					System.out.println("Winner is "+ winner.getName() + "(" + this.getColor() +")");
					return;
				}
				
				this.decYValues(x);
				this.changeTurn();
				System.out.println("It is "+ this.getPlayer().getName() + "(" + this.getColor() +")"
						+ "\'s turn. Please make your move.");
			}
			else {
				System.out.println("Please enter a valid column");
			}
		}
		
		System.out.println("tie game");
		
	}
	
	
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	public String getColor()
	{
		return this.turn==yellow ? "yellow" : "red";
	}
	
	public int getTurn()
	{
		return this.turn;
	}
	
	public boolean isFull()
	{
		return this.piecesOnBoard == this.width * this.height;
	}
	
	public boolean validX(int x)
	{
		return x >= 0 && x < this.width && this.yvalues[x]!=-1;
	}

	
	public User getPlayer() {
		// TODO Auto-generated method stub
			return this.getPlayer(this.turn-1);
	}
	
	@Override
	public void playMove(int x, int y, User player) {
		// TODO Auto-generated method stub
		
	}
	
	public void makeMove(int x, int y)
	{
		this.board.setPiece(y, x, new ConnectFourPiece(this.turn));	
		this.piecesOnBoard++;
	} 
	
	@Override
	public void changeTurn() {
		this.turn = (this.turn == yellow) ? red : yellow;
	}
	
	public void decYValues(int x)
	{
		this.yvalues[x]--;
	}
	
	
	public void checkWin(int x, int y)
	{
		int newX = x;
		int newY = y;
		int connected = 1;
		
		for(int i = 0; i < directions.size();i++)
		{
			for(int j = 0; j < FOUR-1; j++)
			{
				newX += directions.get(i)[0];
				newY += directions.get(i)[1];
				Piece tmp = this.board.getPiece(newY,newX);
				if(tmp!=null && tmp.getId()==this.turn)
				{
					connected++;
				}
				else {
					newX = x;
					newY = y;
					break;
				}
			}
			if(connected == FOUR) 
			{	
				isWin=true;
				return;
			}
			connected = 1;
		}
	}

	@Override
	public User checkIfWin() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void restart()
	{
		 new ConnectFourGame(this.players);
	}
	
	public static void main(String[]args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the first player(yellow)");
		String playerA = input.nextLine();
		System.out.println("Enter the second player(red)");
		String playerB = input.nextLine();
		
		User[]players = {new User(playerA),new User(playerB)};
		
		ConnectFourGame g = new ConnectFourGame(players);
		g.run();
	}
	
}