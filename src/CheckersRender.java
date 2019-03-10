
public class CheckersRender {
	public static void renderBoard(CheckersBoard gameBoard)
	{
		CheckersPiece[][] board = gameBoard.getBoard();
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].player + " ");
			}
			System.out.println();
		}
	}
}
