import java.util.Scanner;
import java.util.Random;

public class gameManager{
   /* Attributes */
   private int numPlayers;
   private int dayCount;
   private int firstPlayer;
   private boolean game;
   
    public gameManager(int numPlayers){
      this.game = true;
      this.numPlayers = numPlayers;
      firstPlayer();
   }
   
   private int firstPlayer() {
     int randnum = 0 + (int)(Math.random() * (((this.numPlayers-1) - 0) + 1));
      this.firstPlayer = randnum;
      return this.firstPlayer;
   }
   public int getfirstPlayer() {
   return this.firstPlayer;
   }
   
   //this method ends the game after dayManager tells the class the fianl day is over
   public void endGame(){
       this.game = false;
   }
   public boolean getGameOver(){
       return this.game;
   } 
   public int getPlayers(){
      return this.numPlayers;
   }

}