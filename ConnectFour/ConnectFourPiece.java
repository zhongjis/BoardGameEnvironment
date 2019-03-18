/*
 * Piece obejct for ConnectFour game
 */
package ConnectFour;
import BoardGameEnvironment.*;
public class ConnectFourPiece extends Piece {
	public String name;
	public ConnectFourPiece() {
		super(0);
	}
	public ConnectFourPiece(int id) {
		super(id);   // 1=yellow, 2=red
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}
}
