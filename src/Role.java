public class Role {

   /* attributes */
   private Actor actor;
   private String role; // changed to string
   private int requiredrank;


   
   /* constructors */
   public Role(String role) { // added to take in a string role
      this.actor = null;
      this.role = role;
      this.requiredrank = 1;
   }
   
   public Role(String role,int requiredRank) { // added to take in a string role
      this.actor = null;
      this.role = role;
      this.requiredrank = requiredRank;
   }
   
   public Role(Actor actor) {
      this.actor = actor;
      this.role = role;
   }
   
   public Actor getActor() {  // used to check offcard roles if there is actor on it
      return this.actor;
   }
   
   public void setActor(Actor actor) {  // ------added
      this.actor = actor;
   }
   
   /* Role methods */
   public int getRank(){
      return this.requiredrank;
   }
   
   public String getRole(){
      return this.role;
   }
   public boolean slotAvailability(){
      if(this.actor == null){
         return true;
      }
      return false;
   }

   }