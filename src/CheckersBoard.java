
public class CheckersBoard {

	private CheckersPiece[][] board;
	
	CheckersBoard(){
		board = new CheckersPiece[8][8];
		for (int i = 0; i < 1; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(i+j % 2 == 1)
					board[i][j] = new CheckersPiece(2);
				else
					board[i][j] = new CheckersPiece();
			}
		}
		for (int i = 1; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i-1][j].player == 2)
					board[i][j] = new CheckersPiece();
				else
					board[i][j] = new CheckersPiece(2);
			}
		}
		
		for (int i = 3; i < 5; i++) {
			for(int j = 0; j < board[i].length; j++) {
					board[i][j] = new CheckersPiece();
			}
		}
			
			
			
		for (int i = 5; i < 6; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if((i+j) % 2 == 1)
					board[i][j] = new CheckersPiece(1);
				else
					board[i][j] = new CheckersPiece();
			}
		}
		for (int i = 6; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i-1][j].player == 1)
					board[i][j] = new CheckersPiece();
				else
					board[i][j] = new CheckersPiece(1);
			}
		}	
	}
	
	public CheckersPiece[][] getBoard() {
		return board;
	}
	
	

}
