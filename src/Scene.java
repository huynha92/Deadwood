import java.util.ArrayList;
public class Scene {

   /* attributes */
   private int budget;
   private ArrayList<Role> oncardroles; // roles avail
   private boolean isRevealed = false; 
   private boolean isActive; 
   private String name;
   
   

   /* constructors */
   public Scene(int budget, ArrayList<Role> oncardroles){//,String name) {
      this.budget = budget;
      this.oncardroles = oncardroles;
      this.isActive = true;
      this.name = name;
   }
   
   public Scene(int budget, ArrayList<Role> oncardroles,String name) {
      this.budget = budget;
      this.oncardroles = oncardroles;
      this.isActive = true;
      this.name = name;
   }

   /* Scene card methods */
   public int getBudget(){
      return this.budget;
   }
   public ArrayList<Role> getoncardroles(){
      return this.oncardroles;
   }
   public void setoncardroles(ArrayList<Role> oncardroles){    // added
      this.oncardroles = oncardroles;
   }
   
   public int getRoleCount(){           //// added
      return this.oncardroles.size();
   }
   
   public void wrapscene() {
      for (Role r : oncardroles) {
         r.setActor(null);
      }
      this.isActive = false;
   }
   public boolean isRevealed() {
      return this.isRevealed;
   }
   public boolean isActive() {
      return this.isActive;
   }
   public void revealScene() {
      this.isRevealed = true;
   }
   public String getName(){
      return this.name;
   }
   }