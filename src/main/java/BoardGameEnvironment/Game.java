package BoardGameEnvironment;

abstract class Game {
  User[] players;
  GameBoard board;
  int turn;

  public Game(User[] players) {
    this.players = players;
  }

  public User getPlayer(int i) {
    if(0 <= i && i < this.players.length) {
      return this.players[i];
    }else {
      return null;
    }
  }

  public abstract void run();

  public abstract void end();

  public abstract User checkIfEnd();

  public abstract void playMove(int x, int y, User player);

  public abstract User changeTurn();
}