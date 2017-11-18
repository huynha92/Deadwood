import java.util.Scanner;;
import java.lang.StringBuilder;
// still need to implement move functions
public class controller{
   private Actor actor;
   private board board;
   private int endturn = 0;
   private boolean moveturn = true;

   public void playerTurn(Actor actor,board b){
          endturn = 0;
          moveturn = true;
          
          this.actor = actor;
          this.board = b;
          Scanner scanner = new Scanner(System.in);
          String args = scanner.nextLine() ;
          String[] arr = args.split(" ");
          while(!args.equals("end")){
              if (endturn == 1) {
              ;
              }
              
              else if(arr[0].equals("who")){
                  System.out.println(who(this.actor));
              }
              else if(arr[0].equals("where")){
                  System.out.println(where(this.actor));
              }
              else if(arr[0].equals("move")){
                  if (moveturn == false) {
                  System.out.println("You have already moved");
                  }
                  else {
                  if(arr.length == 2){
                        move(arr[1]);
                     }
                     else if(arr.length>2){
                        move(arr[1],arr[2]);
                     }
                     else{
                        move();
                     }
                  }
              }
      
              else if(arr[0].equals("neighbors")){
                  move();
              }
              else if(arr[0].equals("work")&&arr.length==1){
                  work();
              }
              else if(arr[0].equals("work")&&arr.length>1){
                  java.lang.StringBuilder role = new java.lang.StringBuilder();
                  for(String s : arr){
                     if(!arr[0].equals(s)&&!arr[arr.length-1].equals(s)){
                        role.append(s);
                        role.append(" ");
                     }
                     else if(arr[arr.length-1].equals(s)){
                        role.append(s);
                     }
                  }
                  work(role.toString());
              }
              else if(arr[0].equals("upgrade") && arr.length > 1 && arr.length < 4){
                 if (moveturn == true) {
                  try{
                     upgrade(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                     }
                     catch(NumberFormatException e){
                        System.err.println("upgrade command takes two int arguments(int credits , int rank)");
                     }
                 }
                 else {
                  System.out.println("You have already moved");
                 }
              }              
              else if(arr[0].equals("rehearse")){
                  if (moveturn == true) {
                     rehearse();
                  }
                  else {
                     System.out.println("You have already moved");
                  }
              }
              else if(arr[0].equals("act") && moveturn == true){
                  if (moveturn == true) {
                     act();
                  }
                  else {
                     System.out.println("You have already moved");
                  }
              }
              else if (arr[0].equals("help")){
                  actions();
              }              
              else
              {
                  System.out.println("Unrecognized argument! Call help for a list of commands.");
              }
              if (endturn == 1) {
              break;
              }
              else {
                 args=scanner.nextLine();
                 arr = args.split(" ");
              }
           }
           end(actor.getName());
   }
   public void actions(){
      System.out.println(" Acceptable commands are:");
      System.out.println("       who");
      System.out.println("       where");
      System.out.println("       move  room");
      System.out.println("       work part");
      System.out.println("       upgrade $ level");
      System.out.println("       upgrade cr level");
      System.out.println("       rehearse");
      System.out.println("       act");
      System.out.println("       help");
      System.out.println("       neighbors");
      System.out.println("       end");
    }
   public String who(Actor name){
      java.lang.StringBuilder who = new java.lang.StringBuilder();
      who.append(name.getName());
      who.append( "($");
      who.append(name.getMoney());
      who.append(",");
      who.append(name.getFame());
      who.append("cr)");
      return who.toString();
     
   }
     public String where(Actor name){
        java.lang.StringBuilder where = new java.lang.StringBuilder();
        where.append(name.getLocation().getName());
        return where.toString();
     }
     
    public void move(String loc){
      //call move(loc) method from turn
      for(String s : actor.getLocation().getNeighbors()){
         if(loc.toLowerCase().equals(s.toLowerCase())){
            Location L = actor.getLocation();
           
           
             //remove from scenes of offcard/oncard if move   
               if (L.getName().equals("office") || L.getName().equals("trailer")) {
                  ;
               }
               else {
                   for (Role r : L.getOffCardRoles()) {
                      if (r.getActor() != null) {
                          r.setActor(null);
                      }
                   }
                  // L.getOffCardRoles().remove(actor);
                   for (Role r : L.getScene().getoncardroles()) {
                      if (r.getActor() != null) {
                          r.setActor(null);
                      }
                   }
               }        
            actor.setLocation(board.getLocation(s.toLowerCase()));
            L.removePlayers(actor);
            board.getLocation(loc).addPlayers(actor);
            System.out.println(actor.getLocation().getName());
            moveturn = false;
         }
      }
      actor.setRehearsalCounter(0);
    }
    public void move(String loc, String loc2){
    
      //call move(loc) method from turn
      String[] arr;
      for(String s : actor.getLocation().getNeighbors()){
         arr=s.toLowerCase().split(" ");
         if(loc.toLowerCase().equals(arr[0])){
            if(loc2.toLowerCase().equals(arr[1])){
               Location L = actor.getLocation();
               
               
              //remove from scenes of offcard/oncard if move   
               if (L.getName().equals("office") || L.getName().equals("trailer")) {
                  ;
               }
               else {
                   for (Role r : L.getOffCardRoles()) {
                      if (r.getActor() != null) {
                          r.setActor(null);
                      }
                   }
                  // L.getOffCardRoles().remove(actor);
                   for (Role r : L.getScene().getoncardroles()) {
                      if (r.getActor() != null) {
                          r.setActor(null);
                      }
                   }
               }  
               
               
               
               
               actor.setLocation(board.getLocation(s.toLowerCase()));
               L.removePlayers(actor);
               board.getLocation(s.toLowerCase()).addPlayers(actor);
               System.out.println(actor.getLocation().getName());
               moveturn = false;
            }
         }
      }
      actor.setRehearsalCounter(0);
    }
    public void move(){
      
      System.out.println("Possible Areas To Move To.");
      for(String s: actor.getLocation().getNeighbors()){
         System.out.println("      "+s);
      }
      
    }
    
    public void work(String part){
      if(actor.getLocation().getName().equals("trailer") || actor.getLocation().getName().equals("office")) {
         System.out.println("You can't work here.");   
      } 
     
      else {
      boolean roleFound = false;
      for(Role role :actor.getLocation().getScene().getoncardroles()){
         if(part.toLowerCase().equals(role.getRole().toLowerCase())){
            roleFound = true;
            if(actor.getRank()>=role.getRank() && role.slotAvailability() == true){
               role.setActor(actor);
               System.out.println(role.getActor().getName() + " is now on the role.");
               endturn = 1;
               return;
               }
            else if (role.slotAvailability() == false) {
               System.out.println(role.getActor().getName() + " has already taken this role.");
               roleFound=false;
            }
            else{
               roleFound=false;
               System.out.println(actor.getName() + " isn't high enough rank to take " + role.getRole() + ".");
            }
           
            break;
         }
      }
      for(Role role :actor.getLocation().getOffCardRoles()){
         if(part.toLowerCase().equals(role.getRole().toLowerCase())){
            roleFound=true;
            if(actor.getRank()>=role.getRank() && role.slotAvailability() == true){
               role.setActor(actor);
               System.out.println(role.getActor().getName() + " is now on the role.");
               endturn = 1;
               return;
               }
            else if (role.slotAvailability() == false) {
               System.out.println(role.getActor().getName() + " has already taken this role.");
               roleFound=false;
            }
            else{
               roleFound=false;
               System.out.println(actor.getName() + " isn't high enough rank to take " + role.getRole() + ".");
            }
            
            break;
         }
      }
      if(!roleFound){
         System.out.println(part+" Role Not Found.");
      }
      }
    }
    
    public void work(){  
      if(actor.getLocation().getName().equals("trailer") || actor.getLocation().getName().equals("office")) {
         System.out.println("You can't work here.");   
      } 
     
      else {
      System.out.println("On Card (Starring) Roles:");
      for(Role role :actor.getLocation().getScene().getoncardroles()){
      
         if (role.slotAvailability() == false) {
            ;
         }
         else {
            System.out.print(role.getRole());
            System.out.print(" Requires rank ");
            System.out.print(role.getRank());
            System.out.print(".");
         }
         System.out.println("");
      } 
      System.out.println("Off Card (Extra) Roles:");
      for(Role role :actor.getLocation().getOffCardRoles()){
         if (role.slotAvailability() == false) {
            ;
         }
         else {
            System.out.print(role.getRole());
            System.out.print(" Requires rank ");
            System.out.print(role.getRank());
            System.out.print(".");
         }
         System.out.println("");
      }      
      }
    }   
    public void upgrade(int credit, int rank){
         System.out.println("Upgrade with fame or money?");
         Scanner scan = new Scanner(System.in);
         String arg = scan.next(); 
         System.out.println(arg);
            if( arg.toLowerCase().equals("fame")){
               fameUpgrade(credit,rank);
            }
            else if (arg.equals("money")){
               dollarUpgrade(credit,rank);
            }
            else{
               System.out.println("unrecognized argument in upgrade. enter fame or money");
               upgrade(credit,rank);
            }
         
    }
    
    public void dollarUpgrade(int money, int lvl){
      //call upgrade from turn class
      if(this.actor.getLocation().getName().equals("office")){
         if(lvl==6&&money>=40){
            this.actor.setRank(lvl);
            this.actor.setMoney(this.actor.getMoney()-40);
            endturn = 1;
            return;
         }
         else if(lvl==5&&money>=28){
            this.actor.setRank(lvl);
            this.actor.setMoney(this.actor.getMoney()-28);
             endturn = 1;
             return;
         }
          else if(lvl==4&&money>=18){
            this.actor.setRank(lvl);
            this.actor.setMoney(this.actor.getMoney()-18);
            endturn = 1;
            return;
         }
          else if(lvl==3&&money>=10){
            this.actor.setRank(lvl);
            this.actor.setMoney(this.actor.getMoney()-10);
            endturn = 1;
            return;
         }
          else if(lvl==2&&money>=3){
            this.actor.setRank(lvl);
            this.actor.setMoney(this.actor.getMoney()-3);
            endturn = 1;
            return;
         }
         else{
               System.out.println("Insufficient funds for upgrade.");
         }
      }
      else{
        System.out.println("Must be in casting office to upgrade rank.");
      }      
    }    
    public void fameUpgrade(int fame, int lvl){
      //call upgrade from turn class
      if(this.actor.getLocation().getName().equals("office")){
         if(lvl==6&&fame>=25){
            this.actor.setRank(lvl);
            this.actor.setFame(this.actor.getFame()-25);
            endturn = 1;
            return;
         }
         else if(lvl==5&&fame>=20){
            this.actor.setRank(lvl);
            this.actor.setFame(this.actor.getFame()-20);
            endturn = 1;
            return;
         }
          else if(lvl==3&&fame>=15){
            this.actor.setRank(lvl);
            this.actor.setFame(this.actor.getFame()-15);
            endturn = 1;
            return;
         }
          else if(lvl==3&&fame>=10){
            this.actor.setRank(lvl);
            this.actor.setFame(this.actor.getFame()-10);
            endturn = 1;
            return;
         }
          else if(lvl==2&&fame>=5){
            this.actor.setRank(lvl);
            this.actor.setFame(this.actor.getFame()-5);  
            endturn = 1;
            return;       
         }
         else{
            System.out.println("Insufficient fame for upgrade.");
         }
      }
      else{
         System.out.println("Must be in casting office to upgrade rank.");
      }
    }
    
    public void rehearse(){
      //call rehearse from turn class
      int onacard = 0;
      for (Role r : actor.getLocation().getOffCardRoles()) {
         if (r.getActor() == actor) {
            onacard++;
         }
      } 
      for (Role r : actor.getLocation().getScene().getoncardroles()) {
         if (r.getActor() == actor) {
            onacard++;
         }
      } 
      if (onacard > 0) {
         this.actor.setRehearsalCounter(this.actor.getRehearsalCounter()+1);
         System.out.println(this.actor.getName()+" has " +this.actor.getRehearsalCounter() + " rehearsal counter(s) for current Scene.");
         endturn = 1;
         return; 
     }   
      else {
         System.out.println("You cannot rehearse without a role");
      }
    }
    
    public void act(){
      //call act from turn class
      int onacard = 0;
      for (Role r : actor.getLocation().getOffCardRoles()) {
         if (r.getActor() == actor) {
            onacard++;
         }
      } 
      for (Role r : actor.getLocation().getScene().getoncardroles()) {
         if (r.getActor() == actor) {
            onacard++;
         }
      } 
      if (onacard > 0) {
         int roll = actRoll(actor);
         PaymentManager pm = PaymentManager.getInstance();
         if(actor.getLocation().getScene().getBudget() > roll){
            System.out.println("Unsuccessful take." + actor.getName()+ " rolled a " + roll+".");
            for(Role r : actor.getLocation().getScene().getoncardroles()){
               if(r.getActor()!=null){
                  if(r.getActor().equals(actor)){
                     pm.updateCurrency(actor,1,0);
                  }
               }
            
                else{
                  pm.updateCurrency(actor,0,0);
                } 
             } 
         }
         else{
            actor.getLocation().setShotCounter();
            System.out.println("Succesful take!"+ actor.getName()+ " rolled a " + roll+".");
            //check if scene is done
            //if (actor.getLocation().
            for(Role r : actor.getLocation().getScene().getoncardroles()){
               if(r.getActor()!=null){
                  if(r.getActor().equals(actor)){
                     pm.updateCurrency(actor,1,1);
                  }
               }
            
                else{
                  pm.updateCurrency(actor,0,1);
                }
            }
            if(actor.getLocation().getShotCounter() == actor.getLocation().getMaxShots()) {
               pm.wrapbonus(actor.getLocation());
               actor.getLocation().getScene().wrapscene();
            for (Role r : actor.getLocation().getOffCardRoles()) {
               r.setActor(null);
            }
            }
         }
      endturn = 1;
      return;   
      }
      else {
         System.out.println("You cannot act without a role");
      }
   }
    
    public void end(String Actor){
      System.out.println(Actor + "'s turn has ended.");
    }


    public int calcPlayers(){
        int players = 0;
        Scanner scanner;
        boolean x = true;
        System.out.println("Enter number of players");
        while (x) {
           scanner = new Scanner(System.in);
          if (scanner.hasNextInt()) {
              players = scanner.nextInt(); 
              if (players <= 8) {
               x = false;
               return players;
              }
              else {
               System.out.println("Max players is 8");
              }
         }
           else {
            System.out.println("Enter a number.");
           }  
       }   
    return players;
  }
  
  private int actRoll(Actor a) {
	   int dice = (int)(Math.random() * 6) + 1+a.getRehearsalCounter();
	   return dice;
   }
}