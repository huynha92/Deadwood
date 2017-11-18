import java.util.Arrays;
import java.util.ArrayList;

public class PaymentManager {
   private static PaymentManager pm = null;
   /* takes in an actor and updates their values */
   private void PaymentManager() {
   }
   public static PaymentManager getInstance(){
      if (pm == null){
         pm = new PaymentManager();
      }
      return pm;
   
   }
   public void upgradeRank(Actor actor,int rank){
      // change actors value to new value
	   actor.setRank(rank);
      System.out.println(actor.getRank());
   }

   public void updateCurrency(Actor actor, int state, int success){  // on/offcard and if it was success/failure
      int playermoney = actor.getMoney();
      int playerfame = actor.getFame();
  
      if (state == 1) { // oncard
         if (success == 1){ // succeed
         playerfame += 2;
         actor.setFame(playerfame);
         }
      
      }
      else {            //offcard
         if (success == 1){ // succeed
         playerfame++;
         playermoney++;
         actor.setFame(playerfame);
         actor.setMoney(playermoney);
         }
         else { // failure
         playermoney++;
         actor.setMoney(playermoney);
         }
      }  
   }
   
   
   
   
   public void windfallbonus(Actor actor, Role role){                    //--------changed to role
      // get roles rank
      // calculate windfall bonus based off rank
      // change actors value to new value
      actor.setMoney(role.getRank() + actor.getMoney());
   }
   
   
   public void wrapbonus(Location location){			
   int check = 0;
   for (Role r : location.getScene().getoncardroles()) {
      if (r.getActor() != null) {
      check++;
      }
   }
   if (check > 0) {
            /* pay people offcard*/
     ArrayList<Role> roles = location.getOffCardRoles();
     int rolecount = roles.size()-1;
     int playermoney;
     Actor actor;
     while (rolecount >= 0) {
      actor = roles.get(rolecount).getActor();
      if (actor != null) { // theres a player on the role, get there money, add rank value to it, set new value
         playermoney = actor.getMoney();
         /*test */
         System.out.println(playermoney);
         playermoney = playermoney + roles.get(rolecount).getRank();
         actor.setMoney(playermoney);
         /*test */
         System.out.println(playermoney);
         System.out.println("Role : " + roles.get(rolecount).getRole());
         System.out.println("------");
      }
      rolecount--;
     }
     
     
     roles = location.getScene().getoncardroles();
     rolecount = roles.size();
     
   
      System.out.println("oncard start");
   
            /* pay people oncard*/
     int budget = location.getScene().getBudget();
     int[] dicevalues = new int[budget]; // keeps track of adding values for dice roles.
     budget--;
     while (budget >= 0) {
        dicevalues[budget] = rollDice();
        budget--;
     }
     Arrays.sort(dicevalues);
     
     /* test */
     System.out.println(Arrays.toString(dicevalues));
     
      
     int[] rolebonuses = new int[rolecount];
     budget = location.getScene().getBudget();            ////// must add the roles to scene in order for on card roles !!!!!!!!
     int rolecount_cpy = rolecount;
     while (budget != 0) {
     if (rolecount_cpy == 0) {    // set values again starting from highest role
      rolecount_cpy = rolecount;
     }
     
     rolebonuses[rolecount_cpy-1] = rolebonuses[rolecount_cpy-1] + dicevalues[budget-1]; 
     
     
     budget--;
     rolecount_cpy--;
     }
     
     /* test */
     System.out.println(Arrays.toString(rolebonuses));
     
     rolecount_cpy = rolecount - 1;
     
     while (rolecount_cpy >= 0) {
      actor = roles.get(rolecount_cpy).getActor();
      if (actor != null) { // theres a player on the role, get there money, add rank value to it, set new value
         playermoney = actor.getMoney();
         /* test */
         System.out.println(playermoney);
         
         playermoney = playermoney + rolebonuses[rolecount_cpy];
         actor.setMoney(playermoney);
         /* test */
         System.out.println(playermoney);
         System.out.println("Role : " + roles.get(rolecount_cpy).getRole());
         System.out.println("------");
         
      }
      rolecount_cpy--;
     }
     } 
   } 
   
   private int rollDice() {
	   int dice = (int)(Math.random() * 6) + 1;
	   return dice;
   }
   
   
}
