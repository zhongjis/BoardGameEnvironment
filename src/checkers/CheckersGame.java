package checkers;
import BoardGameEnvironment.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckersGame extends Game {
	public int playerTurn = 1;
	public CheckersBoard board;
	private CheckersUser playerOne;
	private CheckersUser playerTwo;
		
	CheckersGame(CheckersBoard board, User playerOne, User playerTwo){
		super(new User[] {playerOne, playerTwo});
		this.playerOne = new CheckersUser(playerOne.getName(), 1);
		this.playerTwo = new CheckersUser(playerTwo.getName(), 2);
		this.board = board;		
	}
	
	public void renderBoard()
	{
		System.out.println("Current Player's Turn: " + playerTurn);
		System.out.println("   0 1 2 3 4 5 6 7");
		System.out.println("   ---------------");
		for (int i = 0; i < board.getBoard().length; i++) {
			System.out.print( i + "| ");
			for(int j = 0; j < board.getBoard()[i].length; j++) {
				System.out.print(board.getBoard()[i][j].image + " ");
			}
			System.out.println();
		}
	}
	
	public void run() {
		Scanner input = new Scanner(System.in);
		while(true) {
			renderBoard();
			ArrayList<CheckersLocation> availablePieces = startOfTurn();
			System.out.println("Availiable Pieces to click:");
			for(int i=0;i<availablePieces.size();i++)
			{
				System.out.println(i + ". " + availablePieces.get(i).getX() + "," + availablePieces.get(i).getY());
			}
			int choice = input.nextInt();
			while(!(choice >= 0 && choice < availablePieces.size())) {
				System.out.println("Please enter a valid piece.");
				input.reset();
				choice = input.nextInt();
			}
			CheckersLocation selectedCoord = availablePieces.get(choice);
			if(checkValidSelection(availablePieces.get(choice))){
				if(board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type.equals("regular")) {
					ArrayList<CheckersLocation> validMoves = checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
					ArrayList<CheckersLocation> checkMoves = listOfValidMoves();
					ArrayList<CheckersLocation> temp = new ArrayList<CheckersLocation>();
					for(CheckersLocation element: validMoves) {
						if(checkMoves.contains(element))
							temp.add(element);
					}
					System.out.println("Available moves:");
					for(int i=0;i<temp.size();i++)
					{
						System.out.println(i + ". " + temp.get(i).getX() + "," + temp.get(i).getY());
					}
					input.reset();
					choice = input.nextInt();
					while(!(choice >= 0 && choice < temp.size())) {
						System.out.println("Please enter a valid location.");
						input.reset();
						choice = input.nextInt();
					}
					CheckersLocation endCoord = temp.get(choice);
					CheckersLocation middle = getMiddlePiece(selectedCoord, endCoord);
					if(middle != null) {
	//					selectedCoord = endCoord;
	//					gamestate.capture(middle, endCoord);
	//					while(true) {
	//						ArrayList<CheckersLocation> availableReCaptures = gamestate.checkReCapturable(endCoord);
	//						if(availableReCaptures.size() != 0) {
	//							endCoord = availableReCaptures.get(0);	// This is where the user inputs
	//							middle = gamestate.getMiddlePiece(selectedCoord, endCoord);
	//							gamestate.capture(middle, endCoord);
	//							selectedCoord = endCoord;
	//						}else
	//							break;
						}
					movePiece(selectedCoord, endCoord);
				}else {
					
				}
				changeTurn();
			}
		}
	}
	
	public void end() {
		;
	}
	
	public User checkIfEnd() {
		if(playerOne.getPieces() == 0)
			return playerTwo;
		else if(playerTwo.getPieces() == 0)
			return playerOne;
		return null;
	}
	
	public User changeTurn() {
		if (playerTurn == 1) {
			playerTurn = 2;
			return playerTwo;
		}
		else {
			playerTurn = 1;
			return playerOne;
		}
	}
	
	public void playMove(int x, int y, User player) {
		//Only one set of coordinates, what/where do we move???
		;
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
		
		if(playerTurn == 1 && coordEnd.getX() == 0){
			board.getBoard()[x][y].convertToKing();
		}
		if(playerTurn == 2 && coordEnd.getX() == 7) {
			board.getBoard()[x][y].convertToKing();
		}
	}
	
	public ArrayList<CheckersLocation> startOfTurn(){
		// Returns list of pieces
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		for (int i = 0; i < board.getBoard().length; i++) {
			for(int j = 0; j < board.getBoard()[i].length; j++) {
				if(board.getBoard()[i][j].getType().equals("king")) {
					ArrayList<CheckersLocation> temp = listOfKingMoves(new CheckersLocation(i, j));
					for(int k = 0; k < temp.size();k++) {
						if(temp.get(k).getCapturable())
							allCapturableMoves.add(new CheckersLocation(i, j));
						else
							allNonCapturableMoves.add(new CheckersLocation(i, j));
						break;
					}
				}else {
					if(playerTurn == 1) {
						if(board.getBoard()[i][j].player == 1) {
							ArrayList<CheckersLocation> temp = checkUpMoves(new CheckersLocation(i, j));
							for(int k = 0; k < temp.size();k++) {
								if(temp.get(k).getCapturable())
									allCapturableMoves.add(new CheckersLocation(i, j));
								else
									allNonCapturableMoves.add(new CheckersLocation(i, j));
								break;
							}
						}
					}else{
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
		}
		if(allCapturableMoves.size() == 0) {
			return allNonCapturableMoves;
		}
		else {
			return allCapturableMoves;
		}
	}
	
	public ArrayList<CheckersLocation> listOfValidMoves(){
		// List of moves for player
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		for (int i = 0; i < board.getBoard().length; i++) {
			for(int j = 0; j < board.getBoard()[i].length; j++) {
				if(playerTurn == 1) {
					if(board.getBoard()[i][j].player == 1) {
						ArrayList<CheckersLocation> temp = checkUpMoves(new CheckersLocation(i, j));
						for(int k = 0; k < temp.size();k++) {
							if(temp.get(k).getCapturable())
								allCapturableMoves.add(temp.get(k));
							else
								allNonCapturableMoves.add(temp.get(k));
							break;
						}
					}
				}else{
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
			return listOfKingMoves(coord);
		else
			return checkRegularMoves(coord);
	}
	
	public ArrayList<CheckersLocation> listOfKingMoves(CheckersLocation coord){
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> temp = checkKingMoves(coord);
		for(int k = 0; k < temp.size();k++) {
			if(temp.get(k).getCapturable())
				allCapturableMoves.add(temp.get(k));
			else
				allNonCapturableMoves.add(temp.get(k));
		}
		if(allCapturableMoves.size() == 0) {
			return allNonCapturableMoves;
		}
		else {
			return allCapturableMoves;
		}
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
						leftCapture.setCapturable(true);
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
						rightCapture.setCapturable(true);
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
	
	private ArrayList<CheckersLocation> checkKingMoves(CheckersLocation coord){
		ArrayList<CheckersLocation> availableMoves = new ArrayList<CheckersLocation>();
		CheckersLocation left = new CheckersLocation(coord.getX() - 1, coord.getY() - 1), right = new CheckersLocation(coord.getX() - 1, coord.getY() + 1);
		// Up moves
		if(checkInbounds(left)){
			int tileType = checkTile(left);
			if(tileType == 0)
				availableMoves.add(left);
			else if(playerTurn != tileType) {
				CheckersLocation leftCapture = checkCapturable(left, -1, -1);
				if(leftCapture != null) {
					leftCapture.setCapturable(true);
					availableMoves.add(leftCapture);
				}
			}
		}
		if(checkInbounds(right)){
			int tileType = checkTile(right);
			if(tileType == 0)
				availableMoves.add(right);
			else if(playerTurn != tileType) {
				CheckersLocation rightCapture = checkCapturable(right, -1, 1);
				if(rightCapture != null) {
					rightCapture.setCapturable(true);
					availableMoves.add(rightCapture);
				}
			}	
		}
		// Down moves
		left = new CheckersLocation(coord.getX() + 1, coord.getY() - 1);
		right = new CheckersLocation(coord.getX() + 1, coord.getY() + 1);
		if(checkInbounds(left)){
			int tileType = checkTile(left);
			if(tileType == 0)
				availableMoves.add(left);
			else if(playerTurn != tileType) {
				CheckersLocation leftCapture = checkCapturable(left, 1, -1);
				if(leftCapture != null) {
					leftCapture.setCapturable(true);
					availableMoves.add(leftCapture);
				}
			}
		}
		if(checkInbounds(right)){
			int tileType = checkTile(right);
			if(tileType == 0)
				availableMoves.add(right);
			else if(playerTurn != tileType) {
				CheckersLocation rightCapture = checkCapturable(right, 1, 1);
				if(rightCapture != null) {
					rightCapture.setCapturable(true);
					availableMoves.add(rightCapture);
				}
			}
		}
		return availableMoves;
	}
	
	public void capture(CheckersLocation middle, CheckersLocation end) {
		int x = middle.getX(), y = middle.getY();
		board.getBoard()[x][y] = new CheckersPiece();
		if(playerTurn == 1) {
			playerTwo.decrementPieces();
		}else {
			playerOne.decrementPieces();
		}
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
	

	
}
