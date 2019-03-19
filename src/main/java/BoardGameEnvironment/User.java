package main.java.BoardGameEnvironment;

public class User {
	  String name;
	  public int memoryWins;
	  public int memoryLoses;

	  public int connectfourWins;
	  public int connectfourLoses;

	  public int checkersWins;
	  public int checkersLoses;

	  public int battleshipWins;
	  public int battleshipLoses;


	  public User(String name) {
	    this.name = name;
	    this.memoryWins = 0;
	    this.memoryLoses = 0;

	    this.connectfourWins = 0;
	    this.connectfourLoses = 0;

	    this.checkersWins = 0;
	    this.checkersLoses = 0;

	    this.battleshipWins = 0;
	    this.battleshipLoses = 0;
	  }

	  public User(String name, int[] statistics) {
	    this.name = name;

	    this.memoryWins = statistics[0];
	    this.memoryLoses = statistics[1];

	    this.connectfourWins = statistics[2];
	    this.connectfourLoses = statistics[3];

	    this.checkersWins = statistics[4];
	    this.checkersLoses = statistics[5];

	    this.battleshipWins = statistics[6];
	    this.battleshipLoses = statistics[7];
	  }

	  public String getName() {
	    return this.name;
	  }
	}