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


         Menu start = new Menu();
         start.run();
        
        // testing board creation and presentation
//        GameBoard memoryBoard = new MemoryGameBoard(4,5);
//        System.out.println(memoryBoard);
    }
}