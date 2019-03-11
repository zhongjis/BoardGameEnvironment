
public class CheckersRender {
	public static void renderBoard(CheckersGamestate gamestate)
	{
		CheckersPiece[][] board = gamestate.board.getBoard();
		
		System.out.println("Current Player's Turn: " + gamestate.playerTurn);

		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].image + " ");
			}
			System.out.println();
		}
	}
}
