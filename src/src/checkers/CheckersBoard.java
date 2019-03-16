package checkers;

import BoardGameEnvironment.GameBoard;

public class CheckersBoard extends GameBoard {
	CheckersBoard(){
		super(8, 8, "checkers");
	}
	
	public void initializeGameBoard() {
		for (int i = 0; i < 1; i++) {
			for(int j = 0; j < getBoardArray()[i].length; j++) {
				if(i+j % 2 == 1)
					getBoardArray()[i][j] = new CheckersPiece(2);
				else
					getBoardArray()[i][j] = new CheckersPiece();
			}
		}
		
		for (int i = 1; i < getBoardArray().length; i++) {
			for(int j = 0; j < getBoardArray()[i].length; j++) {
				if(getBoardArray()[i-1][j].getId() == 2)
					getBoardArray()[i][j] = new CheckersPiece();
				else
					getBoardArray()[i][j] = new CheckersPiece(2);
			}
		}
		
		for (int i = 3; i < 5; i++) {
			for(int j = 0; j < getBoardArray()[i].length; j++) {
				getBoardArray()[i][j] = new CheckersPiece();
			}
		}
			
		for (int i = 5; i < 6; i++) {
			for(int j = 0; j < getBoardArray()[i].length; j++) {
				if((i+j) % 2 == 1)
					getBoardArray()[i][j] = new CheckersPiece(1);
				else
					getBoardArray()[i][j] = new CheckersPiece();
			}
		}
		
		for (int i = 6; i < getBoardArray().length; i++) {
			for(int j = 0; j < getBoardArray()[i].length; j++) {
				if(getBoardArray()[i-1][j].getId() == 1)
					getBoardArray()[i][j] = new CheckersPiece();
				else
					getBoardArray()[i][j] = new CheckersPiece(1);
			}
		}
	}
	
	public CheckersPiece getPiece(int x, int y) {
		return (CheckersPiece)this.getBoardArray()[x][y];
	}
}