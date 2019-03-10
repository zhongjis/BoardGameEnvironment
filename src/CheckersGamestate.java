import java.util.ArrayList;

public class CheckersGamestate {
	public int playerTurn = 1;
	private CheckersBoard board;
	
	CheckersGamestate(CheckersBoard board){
		this.board = board;		
	}
	
	private void switchTurn() {
		if (playerTurn == 1)
			playerTurn = 2;
		else
			playerTurn = 1;
	}
	
	public boolean checkValidSelection(CheckersLocation coord) {
		int x = coord.getX(), y = coord.getY();
		if(!checkInbounds(coord)) {
			return false;
		}
		
		return board.getBoard()[x][y].player == playerTurn;
	}
	
	public ArrayList<CheckersLocation> checkAvailableMoves(CheckersLocation coord, String type){
		if(type.equals("king"))
			return checkKingMoves(coord);
		else
			return checkRegularMoves(coord);
				
	}
	
	public ArrayList<CheckersLocation> checkKingMoves(CheckersLocation coord){
		ArrayList<CheckersLocation> availableMoves = new ArrayList<CheckersLocation>();
		return availableMoves;
	}
	
	public ArrayList<CheckersLocation> checkRegularMoves(CheckersLocation coord){
		if(playerTurn == 1)
			return checkUpMoves(coord);
		else
			return checkDownMoves(coord);

	}
	
	private ArrayList<CheckersLocation> checkUpMoves (CheckersLocation coord){
		ArrayList<CheckersLocation> availableMoves = new ArrayList<CheckersLocation>();
		CheckersLocation left = new CheckersLocation(coord.getX() - 1, coord.getY() - 1), right = new CheckersLocation(coord.getX() - 1, coord.getY() + 1);
		if(checkInbounds(left)){
			availableMoves.add(left);
		}
		if(checkInbounds(right)){
			availableMoves.add(right);
		}
		
		return availableMoves;
	}
	
	private ArrayList<CheckersLocation> checkDownMoves (CheckersLocation coord){
		ArrayList<CheckersLocation> availableMoves = new ArrayList<CheckersLocation>();
		CheckersLocation left = new CheckersLocation(coord.getX() + 1, coord.getY() + 1), right = new CheckersLocation(coord.getX() + 1, coord.getY() - 1);
		
		availableMoves.add(left);
		availableMoves.add(right);
		
		return availableMoves;
	}
	
	private boolean checkInbounds(CheckersLocation coord) {
		int x = coord.getX(), y = coord.getY();
		if(x < 0 || x > board.getBoard().length || y < 0 || y > board.getBoard().length) {
			return false;
		}
		return true;
	}
	
	public String checkType(CheckersLocation coord){
		int x = coord.getX(), y = coord.getY();
		return board.getBoard()[x][y].type;
	}
}
