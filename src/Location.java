import java.util.ArrayList;


public class Location {
	/*Attributes*/
	private int shotCounter = 0;
	private ArrayList<Role> offcardroles = new ArrayList<Role>(); // returns players acting but not on card //-- offcard roles available change
   private ArrayList<Actor> allplayers = new ArrayList<Actor>();
   private ArrayList<String> neighbors = new ArrayList<String>();
   
	private int MaxShots;
   private Scene scene;
   private String name;          // tell if its the casting office or trailer and such
   
	
	/*Constructors*/
   public Location(int MaxShots) {
		this.MaxShots = MaxShots;
      this.name = null;
      this.scene = null;	
   }
   public Location(int MaxShots,String name) {
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = null;
	}
   
   public Location(int MaxShots,String name,Scene scene) {
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = scene;
	}
   
   public Location(int MaxShots,String name,Scene scene,ArrayList<Role> offcardroles) {    
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = scene;
      this.offcardroles=offcardroles;
	}
   
   public Location(String name,Scene scene,ArrayList<Role> offcardroles) {
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = scene;
      this.offcardroles=offcardroles;
	}
   public Location(String name,ArrayList<String> neighbors,ArrayList<Role> offcardroles) {
		this.MaxShots = MaxShots;
      this.name = name;
      this.neighbors = neighbors;
      this.scene = scene;
      this.offcardroles=offcardroles;
	}
   public Location(String name,Scene scene, ArrayList<String> neighbors,ArrayList<Role> offcardroles) {  /// important
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = scene;
      this.neighbors = neighbors;
      this.offcardroles=offcardroles;
	}
   public Location(int MaxShots, String name,Scene scene, ArrayList<String> neighbors,ArrayList<Role> offcardroles) {  /// important
		this.MaxShots = MaxShots;
      this.name = name;
      this.scene = scene;
      this.neighbors = neighbors;
      this.offcardroles=offcardroles;
	}
   
   public ArrayList<String> getNeighbors(){   
      return this.neighbors;
   }
   public String getName() {
		return this.name;
	}
   public void setName(String name) {
		this.name = name;
	}
   public ArrayList<Actor> getAllPlayers() {
		return this.allplayers;
	}
   public void addPlayers(Actor actor) {
		this.allplayers.add(actor);
	}
   public void removePlayers(Actor actor) {
		this.allplayers.remove(actor);
	}
   public ArrayList<Role> getOffCardRoles() {
		return this.offcardroles;
	}
   public void setOffCardRoles(ArrayList<Role> offcardroles) {
		this.offcardroles = offcardroles;
	}
   public void addOffCardRoles(Role role) {
		this.offcardroles.add(role);
	}
 //  public void removeOffCardRoles(Actor actor) {
//	}
   public Scene getScene() {
		return this.scene;
	}
   public void setScene(Scene scene) {
      this.scene = scene;
	}
   
   public int getMaxShots() {
		return this.MaxShots;
	}
   public int getShotCounter() {
		return this.shotCounter;
	}
   public void setShotCounter() {
		this.shotCounter++;
	}
}
