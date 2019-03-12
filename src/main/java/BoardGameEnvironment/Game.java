package BoardGameEnvironment;

abstract class Game {
  User[] players;
  GameBoard board;
  int turn;

  public Game(User[] players) {
    this.players = players;
  }

  abstract void run();

  abstract void end();

  abstract User checkIfWin();

  abstract void playMove(int x, int y, User player);

  abstract User changeTurn();
}