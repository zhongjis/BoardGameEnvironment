
public class Render {
	public static void renderBoard(Board gameBoard)
	{
		Piece[][] board = gameBoard.getBoard();
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].image + " ");
			}
			System.out.println();
		}
	}
}
