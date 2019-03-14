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
		System.out.println("   0 1 2 3 4 5 6 7");
		System.out.println("   ---------------");
		for (int i = 0; i < board.getBoard().length; i++) {
			System.out.print( i + "| ");
			for(int j = 0; j < board.getBoard()[i].length; j++) {
				System.out.print(board.getBoard()[i][j].image + " ");
			}
			System.out.println();
		}
		System.out.println("Current Player's Turn: " + playerTurn);
	}
	
	public void run() {
		Scanner input = new Scanner(System.in);
		boolean end = false;
		while(true) {
			renderBoard();
			ArrayList<CheckersLocation> availablePieces = startOfTurn();
			availablePieces = makeListUnique(availablePieces);
			System.out.println("Availiable pieces:");
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
			CheckersLocation endCoord = null;
			if(checkValidSelection(availablePieces.get(choice))){
				ArrayList<CheckersLocation> checkMoves = checkAvailableMoves(selectedCoord, board.getBoard()[selectedCoord.getY()][selectedCoord.getX()].type);
				System.out.println("Available moves:");
				for(int i=0;i<checkMoves.size();i++)
				{
					System.out.println(i + ". " + checkMoves.get(i).getX() + "," + checkMoves.get(i).getY());
				}
				input.reset();
				choice = input.nextInt();
				while(!(choice >= 0 && choice < checkMoves.size())) {
					System.out.println("Please enter a valid location.");
					input.reset();
					choice = input.nextInt();
				}
				endCoord = checkMoves.get(choice);
				CheckersLocation middle = getMiddlePiece(selectedCoord, endCoord);
				CheckersLocation tempCoord = null;
				if(middle != null) {
					tempCoord = endCoord;
					if(capture(middle, endCoord) != null) {
						end = true;
						break;
					}
					movePiece(selectedCoord, endCoord);
					while(true) {
						ArrayList<CheckersLocation> availableReCaptures = checkReCapturable(endCoord);
						if(availableReCaptures.size() != 0) {
							renderBoard();
							System.out.println("Available moves:");
							for(int i=0;i<availableReCaptures.size();i++)
							{
								System.out.println(i + ". " + availableReCaptures.get(i).getX() + "," + availableReCaptures.get(i).getY());
							}
							input.reset();
							choice = input.nextInt();
							while(!(choice >= 0 && choice < availableReCaptures.size())) {
								System.out.println("Please enter a valid location.");
								input.reset();
								choice = input.nextInt();
							}
							endCoord = availableReCaptures.get(choice);
							middle = getMiddlePiece(tempCoord, endCoord);
							if(capture(middle, endCoord) != null) {
								end = true;
								break;
							}
							movePiece(tempCoord, endCoord);
							tempCoord = endCoord;
						}else
							break;
					}
				}else
					movePiece(selectedCoord, endCoord);
				if(end) {
					end();
					break;
				}
				changeTurn();
			}
		}
	}
	
	public void end() {
		if(playerTurn == 1)
			System.out.println(playerOne.getName() + " has won!");
		else
			System.out.println(playerTwo.getName() + " has won!");
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
			return listOfRegularMoves(coord);
	}
	
	public ArrayList<CheckersLocation> listOfRegularMoves(CheckersLocation coord){
		// List of moves for player
		ArrayList<CheckersLocation> allNonCapturableMoves = new ArrayList<CheckersLocation>();
		ArrayList<CheckersLocation> allCapturableMoves = new ArrayList<CheckersLocation>();
		if(playerTurn == 1) {
			if(playerTurn == 1) {
				ArrayList<CheckersLocation> temp = checkUpMoves(coord);
				for(int k = 0; k < temp.size();k++) {
					if(temp.get(k).getCapturable())
						allCapturableMoves.add(temp.get(k));
					else
						allNonCapturableMoves.add(temp.get(k));
				}
			}
		}else{
			if(playerTurn == 2) {
				ArrayList<CheckersLocation> temp = checkDownMoves(coord);
				for(int k = 0; k < temp.size();k++) {
					if(temp.get(k).getCapturable())
						allCapturableMoves.add(temp.get(k));
					else
						allNonCapturableMoves.add(temp.get(k));
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
	
	public User capture(CheckersLocation middle, CheckersLocation end) {
		int x = middle.getX(), y = middle.getY();
		board.getBoard()[x][y] = new CheckersPiece();
		if(playerTurn == 1) {
			playerTwo.decrementPieces();
		}else {
			playerOne.decrementPieces();
		}
		end.setCapturable(false);
		return checkIfEnd();
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
		ArrayList<CheckersLocation> availableCoords = null;
		if(board.getBoard()[coord.getX()][coord.getY()].type.equals("regular"))
			availableCoords = checkAvailableMoves(coord, board.getBoard()[coord.getY()][coord.getX()].type);
		else
			availableCoords = listOfKingMoves(coord);
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
	
	public ArrayList<CheckersLocation> makeListUnique(ArrayList<CheckersLocation> list){
		ArrayList<CheckersLocation> temp = new ArrayList<CheckersLocation>();
		if(list.size() != 0) {
			temp.add(list.get(0));
			for(int i=1;i<list.size();i++) {
				boolean in = false;
				CheckersLocation coord = list.get(i-1);
				for(int j=i;j<list.size();j++) {
					int x = list.get(j).getX(), y = list.get(j).getY();
					if(coord.getX() == x && coord.getY() == y)
						in = true;
					if(in)
						break;
				}
				if(!in)
					temp.add(list.get(i));
			}
		}
		return temp;
	}
}
