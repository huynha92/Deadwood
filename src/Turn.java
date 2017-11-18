
public class Turn extends Actor {
	
	/*Attributes*/
	private static boolean turnOver = false;
	//private Actor[] playerList;
	private Actor currentPlayer;
	//private int playerPointer = 0;
	
	/*Constructor*/
	public Turn(Actor player) {
		this.currentPlayer = player;
		//this.playerPointer = playerPointer;
	}
	
	// This method determines whose turn it is
	public Actor whosTurn() {
		return currentPlayer;
	}
	
	// This method initiates a move.
	public void move(String to) {
	}
	
	// This method will let a player take a specific Role on the scene.
	public void takeRole(Scene Role) {
	}
	
	// This method will let the current player rehearse on a scene.
	public void rehearse() {
      this.currentPlayer.setRehearsalCounter((this.currentPlayer.getRehearsalCounter())+1);
	}
	
	// This method is essentially be the way a player will roll a dice, and get paid, whether or not a shotSucess occurs
	public void Act() {
	}
	
	// This method allows for upgrades if an there is enough credits or fame, and that a player is at the casting office.
	public void upgrade() {
      this.currentPlayer.setRank(this.currentPlayer.getRank()+1);
	}
	
	// This method will end a turn.
	public void endTurn() {
      turnOver=true;
	}
	
   
   public int roll() {
    return (int)(6.0 * Math.random()) + 1;
   }
}
