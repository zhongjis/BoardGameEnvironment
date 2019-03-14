package BoardGameEnvironment;
import ConnectFour.ConnectFourPiece;
public class PieceFactory {
	public static Piece createPiece(String type) {
		if(type.equalsIgnoreCase("connectfour")) {
			return new ConnectFourPiece();
		}
		return null;
	}
}