import java.util.ArrayList;

public class CheckersMain {

	
	public static void main(String[] args)
	{
		CheckersBoard board = new CheckersBoard();
		CheckersGamestate gamestate = new CheckersGamestate(board);
		CheckersRender.renderBoard(board);
		CheckersLocation coord = new CheckersLocation(5,0);
		if(gamestate.checkValidSelection(coord))
		{
			ArrayList<CheckersLocation> coords = gamestate.checkAvailableMoves(coord, board.getBoard()[coord.getY()][coord.getX()].type);
			
			for(CheckersLocation element: coords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			
			board.movePiece(coord, coords.get(0), gamestate.playerTurn);
			CheckersRender.renderBoard(board);
		}
		
		
	}
}
