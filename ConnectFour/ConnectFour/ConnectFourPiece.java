/*
 * Piece obejct for ConnectFour game
 */
package ConnectFour;
import BoardGameEnvironment.*;
public class ConnectFourPiece extends Piece {
	public ConnectFourPiece() {
		super(0);
	}
	public ConnectFourPiece(int id) {
		super(id);   // 1=white, 2=black
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}