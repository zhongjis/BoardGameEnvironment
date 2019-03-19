package BoardGameEnvironment;
 
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
public class BattleShip extends GameBoard {
    public  User[] players; 
    public int turn;

    private int[] ships;
    private int shipsHit;
    public int[] playerScore;
    public int[][] board;
    
    
    private boolean GAME_OVER;

    private static int AIRCRAFTCARRIER = 5;
    private static int BattleShip = 4;
    private static int CRUISER = 3;
    private static int SUBMARINE = 3;
    private static int DESTROYER = 2;
    private static int MISS = 3;
    private static int HIT = 2;
    private static int SHIP = 1;
    private static int WATER = 0;


    BattleShip(User userOne, User userTwo)
    {
    	super(10, 10, "battleship");
        players =  new User[2];
        players[0] = userOne;
        players[1] = userTwo;
        board = new int [10][10];
        
        GAME_OVER = false;
        
        
        turn = 0;

        ships = new int[]{AIRCRAFTCARRIER, BattleShip, CRUISER, SUBMARINE, DESTROYER};  
        boardArray = new Piece[10][10]; //inits to zeros
        shipsHit = 0;
        playerScore = new int[]{0, 0};
        
        for(int i = 0; i < 10; ++i)
        {
            for(int j = 0; j < 10; ++j)
            {
            	boardArray[i][j] = new BattleShipPiece();
            	((BattleShipPiece) boardArray[i][j]).setState(WATER);
            }
        }
        
        
    }
    
    public void printboard() {
        for(int[] x : this.board) {
            System.out.print("\n");
            for(int y : x) {
                System.out.print(y + " ");
            }
        }
    }
    
    
	@Override
	void initializeGameBoard() {
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
                            ((BattleShipPiece) boardArray[x][col]).setState(SHIP);
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
                            ((BattleShipPiece) boardArray[row][x]).setState(SHIP);

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

            
            


        }

   

    public User checkIfWin()
    {
        if(shipsHit >= 17)
        {
            if(playerScore[0] > playerScore[1])
            {
            	GAME_OVER = true;
            	players[1].battleshipWins++;
            	players[0].battleshipLoses--;
                return players[1]; 
            }
            else
            {
            	GAME_OVER = true;
            	players[0].battleshipWins;
            	players[1].battleshipLoses--;
                return players[0];
            }
            
        }
        return null;
    }

    public boolean playMove(int x, int y, User currentPlayer)
    {
        if(currentPlayer == players[0]) //player 1
        {
            if(checkValidMove(x, y))//if true
            {
                if(((BattleShipPiece) boardArray[x][y]).getState() == SHIP)// HIT
                {
                    ((BattleShipPiece) boardArray[x][y]).setState(HIT);
                    shipsHit++;
                    playerScore[0]++; 
                    ++turn;
                    //set gui element to hit here ;)
                }
                else if(((BattleShipPiece) boardArray[x][y]).getState() == WATER) //miss
                {
                	++turn;
                    ((BattleShipPiece) boardArray[x][y]).setState(MISS);
                    //set gui element to miss here
                }
              
                return true;
            }
            return false;
        }
        else //player 2
        {
            if(checkValidMove(x, y))//if true
            {
            	 if(checkValidMove(x, y))//if true
                 {
                     if(((BattleShipPiece) boardArray[x][y]).getState() == SHIP)// HIT
                     {
                         ((BattleShipPiece) boardArray[x][y]).setState(HIT);
                         shipsHit++;
                         playerScore[1]++; 
                         ++turn;   
                     }
                     else if(((BattleShipPiece) boardArray[x][y]).getState() == WATER)//miss
                     {
                         ((BattleShipPiece) boardArray[x][y]).setState(MISS);
                         ++turn;
                     }
                     
                     return true;
                 }
            }
            return false;
        }
        
    }

    public boolean checkValidMove(int x, int y)
    {
        if(((x < 10) && (x > -1) && (y < 10) && (y > -1) ) && (board[x][y] == WATER || board[x][y] == SHIP))
        {
            return true;
        }
        
        return false;
    }

    public User getPlayer()
    {
        if(turn % 2 == 1) //odd turn will always be player 1 , even turn will always be player 2
        {
            return players[0];
        }
        else
        {
            return players[1];
        }
    }
    
    public Boolean end()
    {
    	return GAME_OVER;
    }




}