package environment;

public abstract class Game{
	public abstract void run();
	public abstract User checkIfWin();
	public abstract boolean checkValidMove();
	public abstract boolean reset();
	public abstract User changeTurn();
}