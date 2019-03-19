package BoardGameEnvironment;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;

import BoardGameEnvironment.BattleShip.BattleShip;
import BoardGameEnvironment.BattleShip.BattleShipMain;
import BoardGameEnvironment.Checkers.CheckersGame;
import BoardGameEnvironment.Checkers.CheckersMain;
import BoardGameEnvironment.ConnectFour.ConnectFourMain;
import BoardGameEnvironment.Memory.*; 
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

// import BoardGameEnvironment.Memory.MemoryGame;

public class Menu {
  private User[] users;
  private Scanner s;
  private String viewState;
  private JFrame frame;
  private JLabel errorLabel;
  private Map<String, User> usersList;
  private DefaultListModel userModel;
  private DefaultListModel importedUsersModel;
  List<User> selectedUsers;

  private JSONObject usersObj;

  private File userFile;

  public Menu() {
    this.s = new Scanner(System.in);
    User userOne = new User("p1");
    User userTwo = new User("p2");
    this.viewState = "home";
    this.frame = new JFrame("BoardGameEnvironment");
    this.errorLabel = new JLabel("", SwingConstants.CENTER);
    this.users = new User[] {userOne, userTwo};
    this.usersList = new HashMap<String, User>();
    this.userModel = new DefaultListModel();
    this.importedUsersModel = new DefaultListModel();

    this.selectedUsers = new ArrayList<>();

    this.usersObj = new JSONObject();
    this.userFile = null;
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
      User userOne = selectedUsers.get(0);
      User userTwo = selectedUsers.get(1);

      switch(type) {
        case "memory":
          System.out.println("memoryyy");
          MemoryGame newMemoryGame = new MemoryGame(users[0], users[1]);
          MemoryBoardView view = new MemoryBoardView(newMemoryGame);
          view.showBoard();
          break;
        case "checkers":
          System.out.println("checkers");
          CheckersMain checkersGame = new CheckersMain(userOne, userTwo);
          break;
        case "battleship":
          System.out.println("battleship");
          BattleShipMain battleShipGame = new BattleShipMain(userOne, userTwo);
          break;
        case "connectfour":
          System.out.println("connectfour");
          ConnectFourMain connectFourGame = new ConnectFourMain(selectedUsers.toArray(new User[2]));
          connectFourGame.run();
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
            userFile = f;
            String filepath=f.getPath();

            if(f.getName().endsWith(".json")) {
              try{
                Object obj = new JSONParser().parse(new FileReader(filepath));

                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject);

                usersList.clear();

                for(Object key : jsonObject.keySet()) {
                  importedUsersModel.addElement(key);
                  JSONObject statistics = (JSONObject) jsonObject.get(key);
                  JSONObject a = (JSONObject) statistics.get("statistics");
                  int[] statisticsArray = new int[a.values().size()];

                  int count = 0;

                  statisticsArray[0] = Integer.parseInt(String.valueOf(a.get("memoryWins")));
                  statisticsArray[1] = Integer.parseInt(String.valueOf(a.get("memoryLoses")));
                  statisticsArray[2] = Integer.parseInt(String.valueOf(a.get("connectfourWins")));
                  statisticsArray[3] = Integer.parseInt(String.valueOf(a.get("connectfourLoses")));
                  statisticsArray[4] = Integer.parseInt(String.valueOf(a.get("checkersWins")));
                  statisticsArray[5] = Integer.parseInt(String.valueOf(a.get("checkersLoses")));
                  statisticsArray[6] = Integer.parseInt(String.valueOf(a.get("battleshipWins")));
                  statisticsArray[7] = Integer.parseInt(String.valueOf(a.get("battleshipLoses")));

//                  for(Object k : a.keySet()) {
//                    System.out.println(k + " " + a.get(k));
//                    statisticsArray[count] = Integer.parseInt(String.valueOf(a.get(k)));
//                    count++;
//                  }

                  User importedUser = new User((String) key, statisticsArray);
                  usersList.put((String) key, importedUser);
                }

              }catch (Exception ex) {
                ex.printStackTrace();

                if(!f.canRead()) {
                  userFile = null;
                } else {
                  usersList.clear();
                }

                importedUsersModel.clear();
                usersList.clear();
              }
            }else {
              errorLabel.setText("File is not a JSON file");
              userFile = null;
            }
          }
        }
//      }
    });

    return fileButton;
  }

  private String generateUserStatistics(User user) {
    String output = user.name + "\n";

    output += "Memory wins: " + user.memoryWins + "\t" + "Memory loses: " + user.memoryLoses + "\n";
    output += "Checkers wins: " + user.checkersWins + "\t" + "Checkers loses: " + user.checkersLoses + "\n";
    output += "Battleship wins: " + user.battleshipWins + "\t" + "Battleship loses: " + user.battleshipLoses + "\n";
    output += "Connect Four wins: " + user.connectfourWins + "\t" + "Connect Four loses: " + user.connectfourLoses;

    return output;
  }

  public void saveUserData() {
    try {
      File saveFile = userFile;

      if(saveFile != null) {
        JSONObject jsonObject = new JSONObject();

        for(User user: usersList.values()) {
          String name = user.getName();

          JSONObject userObject = new JSONObject();
          userObject.put("name", name);

          JSONObject statistics = new JSONObject();

          statistics.put("memoryWins", user.memoryWins);
          statistics.put("memoryLoses", user.memoryLoses);

          statistics.put("connectfourWins", user.connectfourWins);
          statistics.put("connectfourLoses", user.connectfourLoses);

          statistics.put("checkersWins", user.checkersWins);
          statistics.put("checkersLoses", user.checkersLoses);

          statistics.put("battleshipWins", user.battleshipWins);
          statistics.put("battleshipLoses", user.battleshipLoses);

          userObject.put("statistics", statistics);

          jsonObject.put(name, userObject);
        }
        FileWriter fileWriter = new FileWriter(userFile);

        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
      }


    } catch (Exception ex) {ex.printStackTrace();  }
  }
  
  public void run() {
    JFrame frame = this.frame;
    frame.setSize(800, 600);
    frame.setLayout(null);

    JLabel welcomeLabel = new JLabel("Welcome to BoardGameEnvironment!", SwingConstants.CENTER);
    welcomeLabel.setBounds(0, 0, 800,100);
    welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(32f));
    frame.add(welcomeLabel);

    this.errorLabel.setBounds(0,80,800,40);

    this.errorLabel.setForeground(Color.RED);
    frame.add(this.errorLabel);

    //Game buttons and label
    JLabel stepThree = new JLabel("3. Select Game");
    stepThree.setBounds(600,120,150,30);
    stepThree.setFont(stepThree.getFont().deriveFont(16f));
    frame.add(stepThree);

    JButton memoryButton, checkersButton, battleshipButton, connectFourButton;

    memoryButton = generateGameJButton("Memory", 600, 180, 150, 50,"memory");
    checkersButton = generateGameJButton("Checkers", 600, 250, 150, 50,"checkers");
    battleshipButton = generateGameJButton("Battleship", 600, 320, 150, 50,"battleship");
    connectFourButton = generateGameJButton("Connect Four", 600, 390, 150, 50,"connectfour");

    frame.add(memoryButton);
    frame.add(checkersButton);
    frame.add(battleshipButton);
    frame.add(connectFourButton);

    //Select two players
    JLabel stepTwo = new JLabel("2. Add two users to play");
    JLabel stepTwoB = new JLabel("(from step 1's list)");
    stepTwo.setBounds(300,120,200,30);
    stepTwoB.setBounds(300,150,200,30);
    stepTwo.setFont(stepTwo.getFont().deriveFont(16f));
    frame.add(stepTwo);
    frame.add(stepTwoB);

    JList<String> selectedUsersList = new JList<>(userModel);
    selectedUsersList.setBounds(300, 180, 200, 40);

    frame.add(selectedUsersList);


    JTextField usernameField = new JTextField();
    usernameField.setBounds(300,220,200,30);

    frame.add(usernameField);

    JButton addButton = new JButton("Add User");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = usernameField.getText();
        if(!name.isEmpty() && usersList.containsKey(name)) {
          if(userModel.size() >= 2){
            System.out.println(selectedUsers.get(0).getName());
            userModel.removeElementAt(0);
            selectedUsers.remove(0);
          }
          userModel.addElement(name);
          selectedUsers.add(usersList.get(name));
          usernameField.setText("");
        }
      }
    });

    addButton.setBounds(300,270,100,30);
    frame.add(addButton);

    //LOAD USERS
    JLabel stepOne = new JLabel("1. Load JSON file with users");
    JLabel stepOneB = new JLabel("(or an empty JSON file)");
    stepOne.setBounds(20,120,240,30);
    stepOneB.setBounds(20,150,240,30);
    stepOne.setFont(stepOne.getFont().deriveFont(16f));
    frame.add(stepOne);
    frame.add(stepOneB);

    JTextArea userStatistics = new JTextArea("Click on a user's name above to see its profile statistics");
    userStatistics.setSize(200, 20);
    userStatistics.setEditable(false);

    JScrollPane userStatisticsPane = new JScrollPane(userStatistics);

    userStatisticsPane.setBounds(20, 420, 400, 100);
    userStatisticsPane.setBackground(Color.WHITE);
    userStatistics.setLocation(0,0);

    frame.add(userStatisticsPane);

    JButton fileExplorer = renderFileExplorer();

    fileExplorer.setBounds(20, 180, 100, 30);
    frame.add(fileExplorer);

    JList<String> importedUsersList = new JList<>(importedUsersModel);
    importedUsersList.setBounds(20, 230, 240, 100);
    importedUsersList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
          User selectedUser = usersList.get(importedUsersList.getSelectedValue());
          userStatistics.setText(generateUserStatistics(selectedUser));
        }
        System.out.println(importedUsersList.getSelectedValue());
      }
    });

    frame.add(importedUsersList);

    JTextField newUsernameField = new JTextField();
    newUsernameField.setBounds(20,330,240,30);

    frame.add(newUsernameField);

    JButton createButton = new JButton("Create User");
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(userFile != null) {
          String name = newUsernameField.getText();
          if(!name.isEmpty() && !usersList.containsKey(name)) {
            importedUsersModel.addElement(name);
            usersList.put(name, new User(name));
            newUsernameField.setText("");
          }else {
            errorLabel.setText("Please create a valid and not already existing user");
          }
        }else {
          errorLabel.setText("Please load JSON before creating new users");
        }
      }
    });

    createButton.setBounds(20,380,120,30);
    frame.add(createButton);

    //CLOSING WINDOW
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);

        System.out.println("Closing");
        saveUserData();
      }
    });

    frame.setVisible(true);
  }
}
