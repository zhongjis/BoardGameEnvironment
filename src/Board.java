
public class Board {

	private Piece[][] board;
	
	Board(){
		board = new Piece[8][8];
		for (int i = 0; i < 1; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(i+j % 2 == 1)
					board[i][j] = new Piece(1);
				else
					board[i][j] = new Piece(0);
			}
		}
		for (int i = 1; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i-1][j].player == 1)
					board[i][j] = new Piece();
				else
					board[i][j] = new Piece(1);
			}
		}
		/*
		for (int i = 3; i < 5; i++) {
			for(int j = 0; j < board[i].length; j++) {
					board[i][j] = new Piece();
			}
		}
		for (int i = 5; i < 8; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(i+j % 2 == 0)
					board[i][j] = new Piece(2);
				else
					board[i][j] = new Piece();
			}
		}*/
	}
	
	public Piece[][] getBoard() {
		return board;
	}
}
