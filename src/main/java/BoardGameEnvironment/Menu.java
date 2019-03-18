package main.java.BoardGameEnvironment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;

// import BoardGameEnvironment.Memory.MemoryGame;

public class Menu {
  private User[] users;
  private Scanner s;
  private String viewState;
  private JFrame frame;
  private JLabel errorLabel;
  private Map<String, User> usersList;
  private DefaultListModel userModel;
  List<User> selectedUsers;

  public Menu() {
    this.s = new Scanner(System.in);
    User userOne = new User("p1");
    User userTwo = new User("p2");
    this.viewState = "home";
    this.frame = new JFrame("BoardGameEnvironment");
    this.errorLabel = new JLabel("");
    this.users = new User[] {userOne, userTwo};
    this.usersList = new HashMap<>();
    this.userModel = new DefaultListModel();

    this.selectedUsers = new ArrayList<>();
  }
  
  private Game createGame(int choice) {
    switch (choice) {
    case 0:
//      return new MemoryGame(this.users[0], this.users[1]);
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

  private JButton generateGameJButton(String name, int x, int y, int width, int height, String type ) {
    JButton gameButton = new JButton(name);
    gameButton.setBounds(x,y,width,height);

    gameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleGameSelect(type);
      }
    });

    return gameButton;
  }

  private void handleGameSelect(String type) {
    if(selectedUsers.size() == 2) {
      switch(type) {
        case "memory":
          System.out.println("memoryyy");
          break;
        case "checkers":
          System.out.println("checkers");
          break;
        case "battleship":
          System.out.println("battleship");
          break;
        case "connectfour":
          System.out.println("connectfour");
          break;
      }
    }else {
      this.errorLabel.setText("Please select two players");
      System.out.println("Select two players");
    }
  }

  private JButton renderFileExplorer() {
    JButton fileButton = new JButton("Load Users");

    fileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==open){
          JFileChooser fc = new JFileChooser();
          int i=fc.showOpenDialog(fileButton);
          if(i==JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();
            String filepath=f.getPath();
            try{
              BufferedReader br=new BufferedReader(new FileReader(filepath));
              String s1="",s2="";
              while((s1=br.readLine())!=null){
                s2+=s1+"\n";
              }
              System.out.println(s2);
              br.close();
            }catch (Exception ex) {ex.printStackTrace();  }
          }
        }
//      }
    });

    return fileButton;
  }
  
  public void run() {
    JFrame frame = this.frame;
    frame.setSize(800, 600);
    frame.setLayout(null);

    JButton memoryButton, checkersButton, battleshipButton, connectFourButton;

    JLabel welcomeLabel = new JLabel("Welcome to BoardGameEnvironment!", SwingConstants.CENTER);
    welcomeLabel.setBounds(0, 0, 800,100);
    welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(32f));
    frame.add(welcomeLabel);

    memoryButton = generateGameJButton("Memory", 50, 120, 150, 30,"memory");
    checkersButton = generateGameJButton("Checkers", 200, 120, 150, 30,"checkers");
    battleshipButton = generateGameJButton("Battleship", 350, 120, 150, 30,"battleship");
    connectFourButton = generateGameJButton("Connect Four", 500, 120, 150, 30,"connectfour");

    frame.add(memoryButton);
    frame.add(checkersButton);
    frame.add(battleshipButton);
    frame.add(connectFourButton);

    this.errorLabel.setBounds(0,160,800,40);

    frame.add(this.errorLabel);

    JList<String> usersList = new JList<>(userModel);
    usersList.setBounds(560, 210, 200, 40);

    frame.add(usersList);


    JTextField usernameField = new JTextField();
    usernameField.setBounds(560,260,200,30);

    frame.add(usernameField);

    JButton createButton = new JButton("Add Player");
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = usernameField.getText();
        if(!name.isEmpty()) {
          if(userModel.size() >= 2){
            System.out.println(selectedUsers.get(0).getName());
            userModel.removeElementAt(0);
            selectedUsers.remove(0);
          }
          userModel.addElement(name);
          selectedUsers.add(new User(name));
          usernameField.setText("");
        }
      }
    });

    createButton.setBounds(560,300,100,20);
    frame.add(createButton);

    frame.setVisible(true);
  }
}
