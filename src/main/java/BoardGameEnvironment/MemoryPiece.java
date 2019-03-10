package BoardGameEnvironment;

public class MemoryPiece extends Piece {
	public MemoryPiece() {
		super(0);
	}
	public MemoryPiece(int id) {
		super(id);
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}