
public class CheckersLocation {
	private int x;
	private int y;
	private boolean capturable = false;
	
	CheckersLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getCapturable() {
		return capturable;
	}
	
	public void setCapturable(boolean captureState) {
		capturable = captureState;
	}
}
