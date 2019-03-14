package BoardGameEnvironment;

import java.util.Scanner;

//import BoardGameEnvironment.Memory.MemoryGame;

public class Menu {
  private User[] users;
  private Scanner s;
  public Menu() {
    this.s = new Scanner(System.in);
    User userOne = new User("p1");
    User userTwo = new User("p2");
    
    this.users = new User[] {userOne, userTwo};
  }
  
  private Game createGame(int choice) {
    switch (choice) {
    case 0:
      //return new MemoryGame(this.users[0], this.users[1]);
    	return null;
    default: 
      return null;
    }
  }
  
  private String generateWelcomeMessage() {
    String welcomeMessage = "Welcome to BoardGameEnvironment! Please type a number to select the game you wish to play.";
    welcomeMessage += "\n\t0 Memory";
    welcomeMessage += "\n\t-1 Exit application";
    
    return welcomeMessage;
  }
  
  public void run() {
    int command = 0;
    
    String welcomeMessage = generateWelcomeMessage();
    System.out.println(welcomeMessage);
    
    while(command != -1) {
      System.out.print("Command: ");
      command = this.s.nextInt();
      s.nextLine();
      
      Game g = createGame(command);
      
      if(g != null) {
        g.run();
      }
    }
  }
}
