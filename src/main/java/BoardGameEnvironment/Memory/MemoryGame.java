package BoardGameEnvironment;

import java.util.Scanner;

/**
 * Memory game controller
 */
public class MemoryGame extends Game {
   private int playerOneScore;
   private int playerTwoScore;

   public MemoryGame(User playerOne, User playerTwo){
     super(new User[] {playerOne, playerTwo});

     this.playerOneScore = 0;
     this.playerTwoScore = 0;

     this.board = new MemoryGameBoard(5,6);
     this.run();
   }


	@Override
	void run() {
		this.startTurn();
	}

	/**
	 * game ending, show the winner
	 */
	@Override
	void end() {
		// FIXME: not sure if Menu will catch the ending game signal and allow user to start another game.
		System.out.println("gaming ending, winner is" + this.checkIfEnd().name);
		
	}
	
	@Override
	User checkIfEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	void playMove(int x, int y, User player) {
		// WARNING: player parameter is never used
		// FIXME: while playing moves, does not use the flipped value of the Piece
		// FIXME: this method is confusing. it suppose to call setMove on GameBoard but has the third parameter as User instead of Piece.
		MemoryPiece playerPiece = new MemoryPiece(this.turn); // the playerPiece shows on the board who did a correct flip on the tile
		this.board.setPiece(x, y, playerPiece);
	}
	
	/**
	 * switch turn, 1(PlayerOne), 2(PlayerTwo)
	 * @return this.turn
	 */
	@Override
	User changeTurn() {
		// FIXME: the turn int will be confusing with Piece ID which also uses int.
		if (this.turn == 1) {
			// switching turn to PlayerTwo
			this.turn = 2;
			return this.players[1];
		} else {
			// switching turn to PlayerOne
			this.turn = 1;
			return this.players[0];
		}
	}

	private void startTurn() {
		// printing board info into console
		System.out.println(this.board);
		System.out.println("Player one score: " + playerOneScore);
		System.out.println("Player one score: " + playerOneScore);
		System.out.println("player " + this.turn + "'s Turn");

		// FIXME: this will cause performance issue cuz program needs to generate a scanner every single time startTurn() is called
		Scanner reader = new Scanner(System.in);

		// reading the first location of the flip
		// FIXME: needs to return error when input is out of bound
		// FIXME: x-axis and y-axis value is opposite
		System.out.println("Enter first x-axis: ");
		int xOne = reader.nextInt();
		System.out.println("Enter first y-axis: ");
		int yOne = reader.nextInt();
		Piece pieceOne = this.board.getPiece(xOne, yOne);
		
		// reading the second location of the flip
		System.out.println("Enter Second x-axis: ");
		int xTwo = reader.nextInt();
		System.out.println("Enter Second y-axis: ");
		int yTwo = reader.nextInt();
		Piece pieceTwo = this.board.getPiece(xTwo, yTwo);

		// FIXME: feeling this step is extra due to the parameter issue with playMove
		User currentPlayer = null;
		if (this.turn == 1) {
			currentPlayer = this.players[0];
		} else {
			currentPlayer = this.players[1];
		}

		// checking the id of two Pieces
		if (pieceOne.id == pieceTwo.id) {
			// if two tiles match. replacing with playerPiece
			System.out.println("Player " + this.turn + "scored!");
			this.playMove(xOne, yOne, currentPlayer);
			this.playMove(xTwo, yTwo, currentPlayer);
			// incrementing score of the current user
			if (this.turn == 1) {
				this.playerOneScore++;
			} else {
				this.playerTwoScore++;
			}
		} // else do nothing

		// ending turn
		this.endTurn();
	}

	private void endTurn() {
		if (this.checkIfEnd() == null) {
			// no winner
			this.changeTurn();	
			this.startTurn();
		} else {
			// someone won the game
			this.end();
		}
	}
 }