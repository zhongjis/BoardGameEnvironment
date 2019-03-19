package BoardGameEnvironment;

import java.io.*;
import javax.sound.sampled.*;


// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class BattleShipBoom{

   // Constructor
   public BattleShipBoom() { 
      try {
         // Open an audio input stream.           
          File soundFile = new File("Blast.wav"); //you could also get the sound file with an URL
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

}