package BoardGameEnvironment;

 public class MemoryGame extends Game {
   private int playerOneScore;
   private int playerTwoScore;

   public MemoryGame(User playerOne, User playerTwo){
     super(new User[] {playerOne, playerTwo});

     this.playerOneScore = 0;
     this.playerTwoScore = 0;

     this.board = new MemoryGameBoard(5,6);
   }

	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void end() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	User checkIfEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	void playMove(int x, int y, User player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	User changeTurn() {
		// TODO Auto-generated method stub
		return null;
	}
 }