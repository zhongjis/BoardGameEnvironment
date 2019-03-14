package BoardGameEnvironment;
import checkers.CheckersPiece;

public class PieceFactory {
	public static Piece createPiece(String type) {
		if(type.equalsIgnoreCase("memory")) {
			//return new MemoryPiece();
		}
		
		else if(type.equalsIgnoreCase("checkers")) {
			return new CheckersPiece();
		}
		
		return null;
	}
}