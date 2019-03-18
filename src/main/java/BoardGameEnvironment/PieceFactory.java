package BoardGameEnvironment;

import BoardGameEnvironment.Checkers.CheckersPiece;
import BoardGameEnvironment.ConnectFour.ConnectFourPiece;
import BoardGameEnvironment.Memory.MemoryPiece;

public class PieceFactory {
	public static Piece createPiece(String type) {
		if(type.equalsIgnoreCase("memory")) {
			return new MemoryPiece();
		}
		else if(type.equalsIgnoreCase("checkers")) {
			return new CheckersPiece();
		}
		else if (type.equalsIgnoreCase("connectfour")) {
			return new ConnectFourPiece();
		}
		return null;
	}
}