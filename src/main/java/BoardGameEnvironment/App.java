/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package BoardGameEnvironment;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        GameBoard testingBoard = new GameBoard(4, 5);
        testingBoard.getBoardArray();
        System.out.println(testingBoard);
    }
}
