public class Actor
{
   /*  Attributes  */
   private int rank;   
   private int money;   
   private int fame;   
   private int rehearsalCounter;   
   private int score;  
   private String name;  
   private Location loc; 
   public boolean working = false;
   
   /* Constructos */
   public Actor(int player,String name){ 
      this.rank=1;
      this.money=0;
      this.fame=0;
      this.rehearsalCounter=0;
      this.score=0;
      this.name=name;
   }
   public Actor(){}
   
   public Actor(int player, int m, int f){
      this.rank=1;
      this.money=m;
      this.fame=f;
      this.rehearsalCounter=0;
      this.score = 0;
   }
   public Actor(int player, int m, int f,String name,Location loc){
      this.rank=1;
      this.money=m;
      this.fame=f;
      this.rehearsalCounter=0;
      this.score = 0;      
      this.name=name;
      this.loc = loc;
   }
      
   
   //get rank of actor
   public int getRank(){           
      return this.rank;
   }
   public Location getLocation(){
      return this.loc;
   }
   public void setLocation(Location loc){
      this.loc = loc;
   }
   
   //get amount of money an actor has
   public int getMoney(){
   return this.money;
   }
   
   //get actor fame value
   public int getFame(){
      return this.fame;
   }
   
   //get actor number of rehearsal Counters 
   public int getRehearsalCounter(){
      return this.rehearsalCounter;
   }
   
   //get actor scores
   public int getScore(){
      setScore();
      return this.score;
   }
   
   public String getName(){
      return this.name;
   }
   // set next player
   public void setRank(int r){  
      this.rank = r;
   }
   
   //set actor value
   public void setMoney(int m){
      this.money = m;
   }
   
   //set an actor's fame value
   public void setFame(int f){
      this.fame = f;
   }
   
   //set an actor's rehearsal counter value
   public void setRehearsalCounter(int rc){
      this.rehearsalCounter = rc;
   }
   //set an actor's score value
   public void setScore(){   // remove int s
      this.score = this.money + this.fame + (5*this.rank);
   }

}