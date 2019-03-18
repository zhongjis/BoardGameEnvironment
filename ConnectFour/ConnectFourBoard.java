package ConnectFour;
import BoardGameEnvironment.*;

public class ConnectFourBoard extends GameBoard{

	public ConnectFourBoard(int width, int height, String gameType) {
		super(width, height, gameType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeGameBoard() {
		// TODO Auto-generated method stub
		
	}
	
/*	@Override
	public Piece getPiece(int x,int  y)
	{
		if(x>=0 && x < this.boardArray.length && y>=0 && y < this.boardArray[x].length) {
			return (ConnectFourPiece) this.boardArray[y][x];
		}else {
			return null;
		}
		
	}*/
	
	
	
}
