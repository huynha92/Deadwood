public class board{
   /*  Attributes  */  
   private static Location[] locations; 
   private static board board = null;
   /* Constructors */
   private void board(){
      assemble(locations);
   }
   
   //assemble the board creating the array of locations 
   private void assemble(Location[] locs){
      //read in locations from xml and call placeScene
      for (int j = 0; j < 12; j++){
         locations[j]= locs[j];
        //  set location directions based off board
      }
   }
   public Location getLocation(String locName){
      if(locations!=null){
         for( Location l : locations){
            if(locName.equals(l.getName())){        
               return l;         
            }
         }
      }     
      return null;
   }
   
   public static board getInstance(){
      return board;
   }
   public static board getInstance(Location[] locs){
      if(board == null){
         locations=locs;
         for(Location l : locations){
      }
         board = new board();
      }
      return board;
   }
   
   }