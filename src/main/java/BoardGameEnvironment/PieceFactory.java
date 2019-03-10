package BoardGameEnvironment;

public class PieceFactory {
	public static Piece createPiece(String type) {
		if(type.equalsIgnoreCase("memory")) {
			return new MemoryPiece();
		}
		
		return null;
	}
}