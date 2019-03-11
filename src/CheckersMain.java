import java.util.ArrayList;

public class CheckersMain {

	
	public static void main(String[] args)
	{
		CheckersBoard board = new CheckersBoard();
		CheckersGamestate gamestate = new CheckersGamestate(board);
		CheckersRender.renderBoard(gamestate);
		
		//player one's movements : Turn 1
		CheckersLocation selectedCoord = new CheckersLocation(5,4);
		if(gamestate.checkValidSelection(selectedCoord)) {
			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
			for(CheckersLocation element: availableCoords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			gamestate.movePiece(selectedCoord, availableCoords.get(0));
			gamestate.endTurn();
			CheckersRender.renderBoard(gamestate);
		
		}
		
		//player two's movements : Turn 2
		selectedCoord = new CheckersLocation(2,3);
		if(gamestate.checkValidSelection(selectedCoord)) {
			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
			for(CheckersLocation element: availableCoords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			gamestate.movePiece(selectedCoord, availableCoords.get(0));
			gamestate.endTurn();
			CheckersRender.renderBoard(gamestate);
		
		}
		
		//player one's movements : Turn 3
		selectedCoord = new CheckersLocation(4,3);
		if(gamestate.checkValidSelection(selectedCoord)) {
			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
			for(CheckersLocation element: availableCoords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			gamestate.movePiece(selectedCoord, availableCoords.get(0));
			gamestate.endTurn();
			CheckersRender.renderBoard(gamestate);
		
		}
		
		//player two's movements : Turn 4
		selectedCoord = new CheckersLocation(2,1);
		if(gamestate.checkValidSelection(selectedCoord)) {
			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
			for(CheckersLocation element: availableCoords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			gamestate.movePiece(selectedCoord, availableCoords.get(0));
			gamestate.endTurn();
			CheckersRender.renderBoard(gamestate);
		
		}
		
		//player one's movements : Turn 5
		selectedCoord = new CheckersLocation(6,5);
		if(gamestate.checkValidSelection(selectedCoord)) {
			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
			for(CheckersLocation element: availableCoords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			gamestate.movePiece(selectedCoord, availableCoords.get(0));
			gamestate.endTurn();
			CheckersRender.renderBoard(gamestate);
		
		}
		
		//player two's movements : Turn 6
		board.getBoard()[2][5] = new CheckersPiece();
		ArrayList<CheckersLocation> startCoords = gamestate.startOfTurn();
		System.out.println("START COORDS");
		for(CheckersLocation element: startCoords)
		{
			System.out.println(element.getX() + "," + element.getY());
		}
		
//		selectedCoord = new CheckersLocation(2,5);
//		if(gamestate.checkValidSelection(selectedCoord)) {
//			ArrayList<CheckersLocation> availableCoords = gamestate.checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
//			for(CheckersLocation element: availableCoords)
//			{
//				System.out.println(element.getX() + "," + element.getY());
//			}
//			gamestate.movePiece(selectedCoord, availableCoords.get(0));
//			gamestate.endTurn();
//			CheckersRender.renderBoard(gamestate);
//		
//		}
		
		
/*		CheckersLocation coord = new CheckersLocation(5,6);
		if(gamestate.checkValidSelection(coord))
		{
			ArrayList<CheckersLocation> coords = gamestate.checkAvailableMoves(coord, board.getBoard()[coord.getY()][coord.getX()].type);
			
			for(CheckersLocation element: coords)
			{
				System.out.println(element.getX() + "," + element.getY());
			}
			
			CheckersLocation loc = new CheckersLocation(3,4);
			gamestate.movePiece(coord, loc, gamestate.playerTurn);
			board.getBoard()[1][2] = new CheckersPiece();
			board.getBoard()[1][6] = new CheckersPiece();
			CheckersRender.renderBoard(board);

			ArrayList<CheckersLocation> coords2 = gamestate.checkAvailableMoves(loc, board.getBoard()[loc.getY()][loc.getX()].type);
			for(CheckersLocation element: coords2)
			{
				System.out.println(element.getX() + "," + element.getY());
			}

		}*/
		
		
		
		
	}
}
