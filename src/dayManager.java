public class dayManager {
	/*Attributes*/
	private int numplayers;	//Game manger has initiated constants for numplayers and daycount.
	private int daycount = 3;
 
	
	/*Constructor*/
	public dayManager(int numplayers) {
      this.numplayers = numplayers;
	}
   
   public void setDays() {
      if (this.numplayers > 3) {
         this.daycount = 4;
      }
   }
   
   public int getDayCount() { /// check if 0
      return this.daycount;
   }
   
  
	// this method will end the day based on the last room left on the board.
	private void endDay() {
   this.daycount--;
	}
  
   
   

   public boolean dayComplete(Location[] locations) {  //if day complete returns true, reset all players to trailer
      int j = 0;
      Scene checker;
      for (int i = 0; i < 12; i++) {
         checker = locations[i].getScene();
         if (checker == null) {
         ;
         }
        
         else if (checker.isActive() == false) {
            j++;
         }
      }
      
      if (j >= 9) { // all scenes done
         endDay();
         return true;
      }
      else {
         return false;
      }
   }
	
}
