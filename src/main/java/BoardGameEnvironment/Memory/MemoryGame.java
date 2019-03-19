package BoardGameEnvironment.Memory;


import BoardGameEnvironment.Game;
import BoardGameEnvironment.Memory.MemoryPiece;
import BoardGameEnvironment.Piece;
import BoardGameEnvironment.User;

import java.util.Scanner;

/**
 * Memory game controller
 */
public class MemoryGame extends Game {
   private int playerOneScore;
   private int playerTwoScore;
   private int moveChance;

   // last picked tile info, default are all -1s
   private int lastPickedTileID;
   private int lastPickedTileX;
   private int lastPickedTileY;

   public MemoryGame(User playerOne, User playerTwo){
     super(new User[] {playerOne, playerTwo});

     this.playerOneScore = 0;
     this.playerTwoScore = 0;

     this.moveChance = 2;

     this.lastPickedTileID = -1;
     this.lastPickedTileX = -1;
     this.lastPickedTileY = -1;

     this.turn = 0;

     this.board = new MemoryGameBoard(5,6);
   }

   	/**
   	 * feedback list
   	 * 1: board should keep tiles flipped
   	 * 2: board should flip tiles back
   	 * 3: still have chance to move, board do nothing except record the button clicked
   	 * 4: board should keep the tiles flipped and end the game, show winner
   	 * @param  x row
   	 * @param  y column
   	 * @return   feedback index
   	 */
	public int playMove(int x, int y) {
		this.moveChance--;
		int output = -1;

		// play move
		int currentPickedTileId = this.board.getPiece(x, y).id;
		if (this.moveChance == 0) {
			// no more chances, checking if match
			if (currentPickedTileId == this.lastPickedTileID) {
				// match, buttons should not be avaliable again

				this.playerScoring(this.turn); // increment player score
				this.nextPlayer();

				output = 1; // board should keep tiles flipped

				if (this.checkIfGameEnd() == true)
					output = 4; // board should keep the tiles flipped and end the game, show winner
			} else if (currentPickedTileId != this.lastPickedTileID) {
				// no match
				
				this.nextPlayer();
				output = 2; // board should flip tiles back
			}
		} else if (this.moveChance == 1) {
			// still have changes left, record move
			this.lastPickedTileID = currentPickedTileId;
			this.lastPickedTileX = x;
			this.lastPickedTileY = y;
			output = 3; // still have chance to move, board do nothing
		}
		System.out.println("One: " + this.playerOneScore);
		System.out.println("Two: " + this.playerTwoScore);
		return output;
	}


	private void nextPlayer() {
		// change turn to next player
		this.changeTurn();
		// refill the chance
		this.moveChance = 2;
		// rest lastPickedTile
		this.lastPickedTileID = -1;
		this.lastPickedTileX = -1;
     	this.lastPickedTileY = -1;
	} 

	private void playerScoring(int playerIndex) {
		// incrementing score of the current user
		if (playerIndex == 0) {
			this.playerOneScore++;
		} else {
			this.playerTwoScore++;
		}
	}

	private boolean checkIfGameEnd() {
		int totalScore = playerOneScore + playerTwoScore;
		if (totalScore == 15 && playerOneScore > playerTwoScore) {
			return true;
		} else {
			return false;
		}
	}

	



















	public User checkIfEnd() {
		int totalScore = playerOneScore + playerTwoScore;
		if (totalScore == 15 && playerOneScore > playerTwoScore) {
			return this.players[0];
		} else if (totalScore == 15 && playerTwoScore > playerOneScore) {
			return this.players[1];
		} else {
			return null;
		}
	}

	/**
	 * game ending, show the winner
	 */
	@Override
	public void end() {
		// FIXME: not sure if Menu will catch the ending game signal and allow user to start another game.
		if (playerOneScore > playerTwoScore) {
			System.out.println("Game finished, Player One is the winner!");
		} else if (playerOneScore < playerTwoScore) {
			System.out.println("Game finished, Player Two is the winner!");
		} else {
			System.out.println ("Game is tied!");
		}
	}

	// private void startTurn() {
	// 	// // printing board info into console
	// 	// System.out.println(this.board);
	// 	// System.out.println("Player one score: " + playerOneScore);
	// 	// System.out.println("Player two score: " + playerTwoScore);

	// 	// if (this.turn == 0) {
	// 	// 	System.out.println("Player One's turn...");
	// 	// } else {
	// 	// 	System.out.println("Player Two's turn...");
	// 	// }

	// 	// int[] firstXY = askPieceWhere(1);
	// 	// int xOne = firstXY[0];
	// 	// int yOne = firstXY[1];
	// 	// Piece pieceOne = this.board.getPiece(xOne, yOne);

	// 	// int[] secondXY = askPieceWhere(2);
	// 	// int xTwo = secondXY[0];
	// 	// int yTwo = secondXY[1];
	// 	// Piece pieceTwo = this.board.getPiece(xTwo, yTwo);

	// 	// // FIXME: feeling this step is extra due to the parameter issue with playMove
	// 	// User currentPlayer = null;
	// 	// if (this.turn == 0) {
	// 	// 	currentPlayer = this.players[0];
	// 	// } else {
	// 	// 	currentPlayer = this.players[1];
	// 	// }

	// 	// comparing the id of two Pieces
	// 	if (pieceOne.id == pieceTwo.id && pieceOne.id != -1) {
	// 		// if two tiles match. replacing with playerPiece
	// 		System.out.println("Scored!");

	// 		this.playMove(xOne, yOne, currentPlayer);
	// 		this.playMove(xTwo, yTwo, currentPlayer);
	// 		// // incrementing score of the current user
	// 		// if (this.turn == 0) {
	// 		// 	this.playerOneScore++;
	// 		// } else {
	// 		// 	this.playerTwoScore++;
	// 		// }
	// 	} else {
	// 		System.out.println("Ops, they are not matched");
	// 	}
	// }
	
	@Override
	public void run() {
		int totalScore = playerOneScore + playerTwoScore;
		while (totalScore < 15) {
//			this.startTurn();
//			this.endTurn();
			// refresh total score
			totalScore = playerOneScore + playerTwoScore;
		} 
	}

	@Override
	public void playMove(int x, int y, User player) {
		// WARNING: player parameter is never used
		// FIXME: while playing moves, does not use the flipped value of the Piece
		// FIXME: this method is confusing. it suppose to call setMove on GameBoard but has the third parameter as User instead of Piece.
		MemoryPiece playerPiece = new MemoryPiece(-1); // the playerPiece shows on the board who did a correct flip on the tile
		this.board.setPiece(x, y, playerPiece);
	}
	
	/**
	 * switch turn, 1(PlayerOne), 2(PlayerTwo)
	 * @return this.turn
	 */
	@Override
	public User changeTurn() {
		// FIXME: the turn int will be confusing with Piece ID which also uses int.
		if (this.turn == 0) {
			// switching turn to PlayerTwo
			this.turn = 1;
			return this.players[1];
		} else {
			// switching turn to PlayerOne
			this.turn = 0;
			return this.players[0];
		}
	}

	int[] askPieceWhere(int index) {
		// FIXME: this will cause performance issue cuz program needs to generate a scanner every single time startTurn() is called
		Scanner reader = new Scanner(System.in);

		if (index == 1) {
			System.out.println("Choosing first tile...");
		} else {
			System.out.println("Choosing second tile...");
		}

		// reading the location of the tile that user wants to flip
		// FIXME: needs to return error when input is out of bound
		// FIXME: x-axis and y-axis value is opposite
		System.out.println("Enter row number: ");
		int x = reader.nextInt();
		System.out.println("Enter column number: ");
		int y = reader.nextInt();
		return new int[]{x, y};
	}
 }