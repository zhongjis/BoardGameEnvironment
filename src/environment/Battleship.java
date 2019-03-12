import javafx.util.Pair; 
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;
import java.awt.Color;
public class Battleship {
    private  Pair <User, User> players; 
    private Gameboard battleBoard;
    private int turn;

    private static final int[] ships;
    private int[][] board; //10 X 10
    private int shipsHit;
    private int[] playerScore;

    private static int AIRCRAFTCARRIER = 5;
    private static int BATTLESHIP = 4;
    private static int CRUISER = 3;
    private static int SUBMARINE = 3;
    private static int DESTROYER = 2;


    Battleship()
    {
        players =  new Pair <User, User>(new User(string p1), new User(string p2)); 
        battleBoard = new Gameboard(10, 10);
        turn = 0;

        ships = new int[5]{AIRCRAFTCARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER};  
        board = new int[10][10]; //inits to zeros
        shipsHit = 0;
        playerScore = new int[2]{0, 0};
        
    }
    private Image getBlueBox()
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 25, 25);
        g.dispose();
        return image;
  
    }
    private Image getRedBox()
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 25, 25);
        g.dispose();
        return image;
  
    }
    private Image getXBox()
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
            //zero is vert
            if(dir == 0)
            {
                //random generated point is left most part of ship
                int validspots = 0;
                if(row + ships[shipIndex] > 9) {}
                else { 
                    for(int i = row; i < ships[shipIndex]; i++) {
                        if(board[i][col] == 0)
                            validspots++;
                    }

                    if(validspots == ships[shipIndex]) {
                        for(int row = 0; x < ships[shipIndex]; x++) {
                            board[col][x] = 1;
                        }
                            shipIndex++;
                    }
                }
            }
            else if(dir == 1)
            {
                //random generated point is left most part of ship
                int validspots = 0;
                if(col + ships[shipIndex] > 9) {}
                else { 
                    for(int i = col; i < ships[shipIndex]; i++) {
                        if(board[row][i] == 0)
                            validspots++;
                    }

                    if(validspots == ships[shipIndex]) {
                        for(int col = 0; y < ships[shipIndex]; y++) {
                            board[row][y] = 1;
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
                    battleBoard.setMove(i, j, new Piece(25, 25, getBlueBox()));
                }
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
    }

    public void playMove(int x, int y, User currentPlayer)
    {


        ++turn;
    }

    public boolean checkValidMove()
    {

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