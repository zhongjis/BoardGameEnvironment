package BoardGameEnvironment.Memory;

import BoardGameEnvironment.GameBoard;
import BoardGameEnvironment.Piece;

import java.util.*;
import java.util.Collections;

public class MemoryGameBoard extends GameBoard {
  public MemoryGameBoard(int width, int height) {
	    super(width, height, "memory");
	    
	    this.initializeGameBoard();
  }


  public void initializeGameBoard() {
    int pairs = (this.width * this.height)/2;
    int[] randomPairs = generateRandomPairs(pairs);

    // System.out.println(Arrays.toString(randomPairs));

    for(int i=0; i < this.width; i++){
			for(int j = 0; j < this.height; j++) {
				this.boardArray[j][i] = new Piece(randomPairs[i*(this.width+1)+j]);
			}
		}
  }

  private int[] generateRandomPairs(int pairs) {
    Integer[] randomPairs = new Integer[pairs*2];
    
    for(int i=0, j=0; i < pairs*2; i+=2, j++) {
      // System.out.println(j + " " + i);
      randomPairs[i] = j;
      randomPairs[i+1] = j;
    }

    List<Integer> randomizeArray = Arrays.asList(randomPairs);
    Collections.shuffle(randomizeArray);
    int[] output = new int[pairs*2];

    for(int i=0; i < randomizeArray.size(); i++) {
        output[i] = randomizeArray.get(i);
    }

    return output;
  }
}