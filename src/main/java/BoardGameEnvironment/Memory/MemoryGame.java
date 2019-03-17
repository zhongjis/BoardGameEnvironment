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

     this.turn = 0;

     this.board = new MemoryGameBoard(5,6);
     this.run();
     this.end();
   }


	@Override
	void run() {
		int totalScore = playerOneScore + playerTwoScore;
		while (totalScore < 15) {
			this.startTurn();
			this.endTurn();
			// refresh total score
			totalScore = playerOneScore + playerTwoScore;
		} 
	}

	/**
	 * game ending, show the winner
	 */
	@Override
	void end() {
		// FIXME: not sure if Menu will catch the ending game signal and allow user to start another game.
		if (playerOneScore > playerTwoScore) {
			System.out.println("Game finished, Player One is the winner!");
		} else if (playerOneScore < playerTwoScore) {
			System.out.println("Game finished, Player Two is the winner!");
		} else {
			System.out.println ("Game is tied!");
		}
	}

	private void startTurn() {
		// printing board info into console
		System.out.println(this.board);
		System.out.println("Player one score: " + playerOneScore);
		System.out.println("Player two score: " + playerTwoScore);

		if (this.turn == 0) {
			System.out.println("Player One's turn...");
		} else {
			System.out.println("Player Two's turn...");
		}

		int[] firstXY = askPieceWhere(1);
		int xOne = firstXY[0];
		int yOne = firstXY[1];
		Piece pieceOne = this.board.getPiece(xOne, yOne);

		int[] secondXY = askPieceWhere(2);
		int xTwo = secondXY[0];
		int yTwo = secondXY[1];
		Piece pieceTwo = this.board.getPiece(xTwo, yTwo);

		// FIXME: feeling this step is extra due to the parameter issue with playMove
		User currentPlayer = null;
		if (this.turn == 0) {
			currentPlayer = this.players[0];
		} else {
			currentPlayer = this.players[1];
		}

		// comparing the id of two Pieces
		if (pieceOne.id == pieceTwo.id && pieceOne.id != -1) {
			// if two tiles match. replacing with playerPiece
			System.out.println("Scored!");

			this.playMove(xOne, yOne, currentPlayer);
			this.playMove(xTwo, yTwo, currentPlayer);
			// incrementing score of the current user
			if (this.turn == 0) {
				this.playerOneScore++;
			} else {
				this.playerTwoScore++;
			}
		} else {
			System.out.println("Ops, they are not matched");
		}
	}

	private void endTurn() {
		int totalScore = playerOneScore + playerTwoScore;

		if (totalScore < 15) {
			this.changeTurn();
		}
	} 
	
	@Override
	User checkIfEnd() {
		int totalScore = playerOneScore + playerTwoScore;
		if (totalScore == 15 && playerOneScore > playerTwoScore) {
			return this.players[0];
		} else if (totalScore == 15 && playerTwoScore > playerOneScore) {
			return this.players[1];
		} else {
			return null;
		}
	}
	
	@Override
	void playMove(int x, int y, User player) {
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
	User changeTurn() {
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