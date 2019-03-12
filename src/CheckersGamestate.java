import java.util.ArrayList;

public class CheckersGamestate {
	public int playerTurn = 1;
	public CheckersBoard board;
	
	CheckersGamestate(CheckersBoard board){
		this.board = board;		
	}
	
	public void endTurn() {
		if (playerTurn == 1)
			playerTurn = 2;
		else
			playerTurn = 1;
	}
	
	public ArrayList<CheckersLocation> startOfTurn(){
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		if(playerTurn == 1) {
			
		}
		else {
			for (int i = 0; i < board.getBoard().length; i++) {
				for(int j = 0; j < board.getBoard()[i].length; j++) {
					if(board.getBoard()[i][j].player == 2) {
						ArrayList<CheckersLocation> temp = checkDownMoves(new CheckersLocation(i, j));
						for(int k = 0; k < temp.size();k++) {
							if(temp.get(k).getCapturable())
								allCapturableMoves.add(new CheckersLocation(i, j));
							else
								allNonCapturableMoves.add(new CheckersLocation(i, j));
							break;
						}
					}
				}
			}
		}
		if(allCapturableMoves.size() == 0) {
			return allNonCapturableMoves;
		}
		else {
			return allCapturableMoves;
		}
	}
	
	public ArrayList<CheckersLocation> listOfValidMoves(){
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		if(playerTurn == 1) {
			
		}
		else {
			for (int i = 0; i < board.getBoard().length; i++) {
				for(int j = 0; j < board.getBoard()[i].length; j++) {
					if(board.getBoard()[i][j].player == 2) {
						ArrayList<CheckersLocation> temp = checkDownMoves(new CheckersLocation(i, j));
						for(int k = 0; k < temp.size();k++) {
							if(temp.get(k).getCapturable())
								allCapturableMoves.add(temp.get(k));
							else
								allNonCapturableMoves.add(temp.get(k));
							break;
						}
					}
				}
			}
		}
		if(allCapturableMoves.size() == 0) {
			return allNonCapturableMoves;
		}
		else {
			return allCapturableMoves;
		}
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
			int tileType = checkTile(left);
			switch(tileType) {
				case 0:
					availableMoves.add(left);
					break;
				case 1:
					break;
				case 2:
					CheckersLocation leftCapture = checkCapturable(left, -1, -1);
					if(leftCapture != null) {
						availableMoves.add(leftCapture);
					}
					break;
			}

			
		}
		if(checkInbounds(right)){
			int tileType = checkTile(right);
			switch(tileType) {
				case 0:
					availableMoves.add(right);
					break;
				case 1:
					break;
				case 2:
					CheckersLocation rightCapture = checkCapturable(right, -1, 1);
					if(rightCapture != null) {
						availableMoves.add(rightCapture);
					}
					break;
			}
			
		}
		
		
		return availableMoves;
	}
	
	private ArrayList<CheckersLocation> checkDownMoves (CheckersLocation coord){
		ArrayList<CheckersLocation> availableMoves = new ArrayList<CheckersLocation>();
		CheckersLocation left = new CheckersLocation(coord.getX() + 1, coord.getY() - 1), right = new CheckersLocation(coord.getX() + 1, coord.getY() + 1);
		if(checkInbounds(left)){
			int tileType = checkTile(left);
			switch(tileType) {
				case 0:
					availableMoves.add(left);
					break;
				case 1:
					CheckersLocation leftCapture = checkCapturable(left, 1, -1);
					if(leftCapture != null) {
						leftCapture.setCapturable(true);
						availableMoves.add(leftCapture);
					}
					break;
				case 2:
					break;

			}

			
		}
		if(checkInbounds(right)){
			int tileType = checkTile(right);
			switch(tileType) {
				case 0:
					availableMoves.add(right);
					break;
				case 1:
					CheckersLocation rightCapture = checkCapturable(right, 1, 1);
					if(rightCapture != null) {
						rightCapture.setCapturable(true);
						availableMoves.add(rightCapture);
					}
					break;
				case 2:
					break;

			}
			
		}
		
		
		return availableMoves;
	}
	
	public void capture(CheckersLocation middle, CheckersLocation end) {
		int x = middle.getX(), y = middle.getY();
		
		board.getBoard()[x][y] = new CheckersPiece();
		
		end.setCapturable(false);
	}
	
	private CheckersLocation checkCapturable(CheckersLocation coord, int xShift, int yShift) {
		int x = coord.getX(), y = coord.getY();
		CheckersLocation capturable = new CheckersLocation(coord.getX() + xShift, coord.getY() + yShift);
		if(checkInbounds(capturable)) {
			if(board.getBoard()[capturable.getX()][capturable.getY()].player == 0) {
				return capturable;
			}
		}
		
		return null;	
	}
	
	public ArrayList<CheckersLocation> checkReCapturable(CheckersLocation coord) {
		ArrayList<CheckersLocation> availableCoords = checkAvailableMoves(coord, board.getBoard()[coord.getY()][coord.getX()].type);
		ArrayList<CheckersLocation> validMoves = new ArrayList<CheckersLocation>();

		for(CheckersLocation element: availableCoords) {
			if((coord.getX() + element.getX()) % 2 == 0 && (coord.getY() + element.getY()) % 2 == 0) {
				validMoves.add(element);
			}
		}
		
		
		
		System.out.println("--------------\nVALID MOVES");
		for(CheckersLocation element: validMoves)
		{
			System.out.println(element.getX() + "," + element.getY());
		}
		System.out.println("--------------");
		return validMoves;
		
	}
	
	public CheckersLocation getMiddlePiece(CheckersLocation start, CheckersLocation end) {
		int sX = start.getX(), sY = start.getY(), eX = end.getX(), eY = end.getY();
		
		if((sX + eX) % 2 == 0 && (sY + eY) % 2 == 0)
		{
			return new CheckersLocation((sX + eX)/2, (sY + eY)/2);
		}
		
		return null;
	}
	
	
	private int checkTile(CheckersLocation coord) {
		int x = coord.getX(), y = coord.getY();
		
		if(board.getBoard()[x][y].player == 0)
			return 0;
		else if(board.getBoard()[x][y].player == 1)
			return 1;		
		else
			return 2;
	}
	
	
	private boolean checkInbounds(CheckersLocation coord) {
		int x = coord.getX(), y = coord.getY();
		if(x < 0 || x > board.getBoard().length-1 || y < 0 || y > board.getBoard().length-1) {
			return false;
		}
		return true;
	}
	
	public String checkType(CheckersLocation coord){
		int x = coord.getX(), y = coord.getY();
		return board.getBoard()[x][y].type;
	}
	
	public void movePiece(CheckersLocation coordStart, CheckersLocation coordEnd)
	{
		int x = coordStart.getX(), y = coordStart.getY();
		
		CheckersPiece startTemp = board.getBoard()[x][y];
		CheckersPiece endTemp = board.getBoard()[coordEnd.getX()][coordEnd.getY()];
		
		board.getBoard()[x][y]= endTemp;
		
		x = coordEnd.getX();
		y = coordEnd.getY();
		
		board.getBoard()[x][y] = startTemp;
		
	}
	
}
