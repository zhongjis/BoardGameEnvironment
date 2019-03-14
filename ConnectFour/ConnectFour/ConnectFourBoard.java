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
	
	/*public boolean isFull()
	{
		for(int i = 0; i < this.boardArray.length;i++) {
			for(int j = 0; j < this.boardArray[j].length;j++) {
				if(this.boardArray[i][j].getId() == 0)
				{
					return false;
				}
			}
		}
		return true;
	}*/
	
	
	
}
