package BoardGameEnvironment;

public class PieceFactory {
	public static Piece createPiece(String type) {
		if(type.equalsIgnoreCase("memorypiece")) {
			return new MemoryPiece();
		}
		
		return null;
	}
}