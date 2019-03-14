

import javafx.util.Pair; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;
import java.awt.Color;
public class Battleship {
    private  Pair <User, User> players; 
    private GameBoard battleBoard;
    private int turn;

    private int[] ships;
    private int[][] board; //10 X 10
    private int shipsHit;
    private int[] playerScore;

    private static int AIRCRAFTCARRIER = 5;
    private static int BATTLESHIP = 4;
    private static int CRUISER = 3;
    private static int SUBMARINE = 3;
    private static int DESTROYER = 2;
    private static int MISS = 3;
    private static int HIT = 2;
    private static int SHIP = 1;
    private static int WATER = 0;


    Battleship()
    {
        players =  new Pair <User, User>(new User(), new User()); 
        battleBoard = new GameBoard(10, 10);
        turn = 0;

        ships = new int[]{AIRCRAFTCARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER};  
        board = new int[10][10]; //inits to zeros
        shipsHit = 0;
        playerScore = new int[]{0, 0};
        
    }
    
    public void printboard() {
    	for(int[] x : this.board) {
    		System.out.print("\n");
    		for(int y : x) {
    			System.out.print(y + " ");
    		}
    	}
    }
    
    private BufferedImage getBlueBox()
    {
        BufferedImage image = new BufferedImage(25, 25, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 25, 25);
        g.dispose();
        return image;
  
    }
    private BufferedImage getRedBox()
    {
        BufferedImage image = new BufferedImage(25, 25, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 25, 25);
        g.dispose();
        return image;
  
    }
    private BufferedImage getXBox()
    {
            BufferedImage image = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(Color.BLUE);
            g2d.fillRect(0, 0, 25, 25);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 34)); 
            g2d.drawString("X", 0, 24); 
            g2d.dispose();
            return image;
  
    }
    public void init()
    {
        
        int shipIndex = 0;
        while(shipIndex < 5)
        {
            int row = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            int col = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            int dir = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            //find a way to check that the ship is valid vert or whore
            //zero is virt
            if(dir == 0)
            {
                //random generated point is left most part of ship
                int validspots = 0;
                if(row + ships[shipIndex] > 9) {}
                else { 
                    for(int i = row; i < 9; i++) {
                        if(board[i][col] == 0) {
                            validspots++;
                            System.out.println(i + "  " + col);
                        }
                    }

                    if(validspots == ships[shipIndex]) {
                        for(int x = row; x < 9; x++) {
                            board[x][col] = 1;

                        }
                        shipIndex++;
                 
                       
                    }
                }
            }
            //1 is hor
            else if(dir == 1)
            {
                //random generated point is left most part of ship
                int validspots = 0;
                if(col + ships[shipIndex] > 9) {}
                else { 
                    for(int i = col; i < 9; i++) {
                        if(board[row][i] == 0) {
                            validspots++;
                            System.out.println(i + "  " + row);
                        }
                    }

                    if(validspots == ships[shipIndex]) {
                        for(int x = col; x < 9; x++) {
                            board[row][x] = 1;

                        }
                        shipIndex++;
                 
                       
                    }
                }
            }
                //check if out of bounds 
                //check if any ships there already
                //if passes place ship
                //increment ships placed 
            }
            //if valid increment shipIndex
            
            //else run it again
            //fill board with blue ;)

            for(int i = 0; i < 10; ++i)
            {
                for(int j = 0; j < 10; ++j)
                {
                 //  battleBoard.setMove(i, j, new Piece(25, 25, getBlueBox()));
                }
            }
            


        }

   
    public void end()
    {
    }

    public User checkIfWin()
    {
        if(shipsHit >= 17)
        {
            if(playerScore[0] > playerScore[1])
            {
                return players.getKey(); 
            }
            else
            {
                return players.getValue();
            }
        }
        return null;
    }

    public void playMove(int x, int y, User currentPlayer)
    {
    	if(currentPlayer == players.getValue()) //player 1
    	{
    		if(checkValidMove(x, y))//if true
    		{
    			if(board[x][y] == SHIP)// HIT
    			{
    				board[x][y] = HIT;
    				shipsHit++;
    				playerScore[0]++; 
    				//set gui element to hit here ;)
    			}
    			else //miss
    			{
    				board[x][y] = MISS;
    				//set gui element to miss here
    			}
    		}
    	}
    	else //player 2
    	{
    		if(checkValidMove(x, y))//if true
    		{
    			if(board[x][y] == SHIP)// HIT
    			{
    				board[x][y] = HIT;
    				shipsHit++;
    				playerScore[1]++; 
    				//set gui element to hit here ;)
    			}
    			else //miss
    			{
    				board[x][y] = MISS;
    				//set gui element to miss here
    			}
    		}
    	}
    	
    	++turn;
    }

    public boolean checkValidMove(int x, int y)
    {
    	if(((x < 10) && (x > -1) && (y < 10) && (y > -1) ) && board[x][y] == WATER)
    	{
    		return true;
    	}
    	
    	return false;
    }

    public User changeTurn()
    {
        if(turn % 2 == 1) //odd turn will always be player 1 , even turn will always be player 2
        {
            return players.getValue();
        }
        else
        {
            return players.getKey();
        }
    }


}


