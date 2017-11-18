import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;



class deadwood {

   public static void main(String[] args) {
      // main managers     
      View v = new View();
      controller c = new controller();
      int playercount = c.calcPlayers();
      gameManager gm = new gameManager(playercount);
      dayManager dm = new dayManager(playercount);
      PaymentManager pm  = PaymentManager.getInstance();  
     
     
       
       
       int turncounter = 0;
       // setup locations and parse in board
       Location[] locations = locationSetup();
       
       board b = board.getInstance(locations);
       
       // actor setup
      ArrayList<Actor> actors = new ArrayList<Actor>();
      for (int i = 0; i < playercount; i++) {
         actors.add(new Actor(i, "Player " + Integer.toString(i+1)));
      }
      
      // save trailer location and set all players to it
      Location trailer = null;
      for (int i = 0; i < 12; i++) {
         if(locations[i].getName().equals("trailer")) {
            trailer = locations[i];
         }
      }
      for (int i = 0; i < playercount; i++) {
         actors.get(i).setLocation(trailer);
      }
       
       
     //  System.out.println(locations[9].getOffCardRoles().get(0).getRole());
       //System.out.println(locations[10].getNeighbors());
     //  System.out.println(locations[10].getName());
       
       
     /*   ArrayList<Scene> sceneDeck = sceneDeck();
        System.out.println("===========================");
        System.out.println(sceneDeck.get(0).getName());
        System.out.println(sceneDeck.get(0).getBudget());
        System.out.println(sceneDeck.get(0).getoncardroles());
        System.out.println(sceneDeck.get(0).getoncardroles().get(0).getRank());
        System.out.println(sceneDeck.get(0).getoncardroles().get(0).getRole());
        System.out.println("==========================="); */
       
       ArrayList<Scene> sceneDeck = sceneDeck();
      
       while (dm.getDayCount() != 0) {
          // parse new scenes everyday
           Collections.shuffle(sceneDeck); // shuffle at beginning to get first 10

          for (int i = 0; i < 10; i++) { // sets everything but trailer and casting
            locations[i].setScene(sceneDeck.get(i));
         }
          
          
          
          
          
          for (Actor A : actors ) {
            A.setLocation(trailer);
         }
          int firstplayer = gm.getfirstPlayer();
          turncounter = firstplayer;
          while (dm.dayComplete(locations) == false) {
          
             v.turnStart();
             c.playerTurn(actors.get(turncounter),b);
                    
             // reset turn counter when has gone through all players
             if (turncounter == playercount-1) {
               turncounter = 0;
             }     
             else {
               turncounter++;
             }             
            // if (dm.dayComplete(locations) == false) {
            // break;
            // }
             dm.dayComplete(locations);         //keeps checking day complete  
                     
          }
          
         for (Location L : locations ) {
            L.setScene(null);
          }
          
           v.endDay();
       }
       
       
       /* end game routine */ 
       v.endGame();
       turncounter = 0;
       for (int i = 0; i < playercount; i++) {
         v.displayScores(actors.get(turncounter));
       }
       gm.endGame(); 
       System.out.println("Game ended -Deadwood.java");
   }
   
  

 public static String getNameString(Node n){
      String s = n.toString();
            String[] sArr = s.split("=");
            String b =sArr[1];
            b=b.substring(1,b.length()-1);
            return b;
   }
   public static ArrayList<Element> boardParser() {
      NodeList[] boardRoomsArray;
	    try {
	    	File xmlFile = new File ("board.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(xmlFile);
	    	doc.getDocumentElement().normalize();
	    	Element boardElement = doc.getDocumentElement();	//BoardElement a location	(Default)
	    	NodeList boardRooms = boardElement.getElementsByTagName("set");
         ArrayList<Element> elementList = new ArrayList<Element>();
         boardRoomsArray = new NodeList[boardRooms.getLength()];
         
	    	for (int i = 0; i < boardRooms.getLength(); i++){
	    		Element rooms = (Element) boardRooms.item(i);
            elementList.add((Element) boardRooms.item(i));
	    		NodeList neighborList = rooms.getElementsByTagName("neighbor");
	    		NodeList takesList = rooms.getElementsByTagName("take");
	    		NodeList partList = rooms.getElementsByTagName("part");
	    	   boardRoomsArray[i] = boardRooms;
	    	}
         return elementList; 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
      return null;  
     }  
     
     
     
     public static Location[] locationSetup() {
      Location[] locs = new Location[12];
      String roleName;
      String neighborName;
      String locName;
      int maxShots;
      ArrayList<Element> boardParser = boardParser();
      ArrayList<String> neighbors = new ArrayList<String>();
      ArrayList<Role> roles = new ArrayList<Role>();
      
      
      ArrayList<String> officeNeighbors = new ArrayList<String>();
      ArrayList<String> trailerNeighbors = new ArrayList<String>();




    for(int j=0; j<boardParser.size();j++){
      neighbors = new ArrayList<String>();
      roles = new ArrayList<Role>();
      locName = getNameString(boardParser.get(j).getAttributes().getNamedItem("name"));
      for(int i =0; i < boardParser.get(j).getElementsByTagName("neighbor").getLength(); i++){
         neighborName= getNameString(boardParser.get(j).getElementsByTagName("neighbor").item(i).getAttributes().getNamedItem("name"));
         neighbors.add(neighborName.toLowerCase());
         if (neighborName.equals("office")) {
         officeNeighbors.add(locName.toLowerCase());
         }
         if (neighborName.equals("trailer")) {
         trailerNeighbors.add(locName.toLowerCase());
         }
         
       }  
       maxShots = Integer.parseInt(getNameString(boardParser.get(j).getElementsByTagName("take").item(0).getAttributes().getNamedItem("number")));
       
       for(int i =0; i < boardParser.get(j).getElementsByTagName("part").getLength(); i++){
           roleName= getNameString(boardParser.get(j).getElementsByTagName("part").item(i).getAttributes().getNamedItem("name"));
           int rank = Integer.parseInt(getNameString(boardParser.get(j).getElementsByTagName("part").item(i).getAttributes().getNamedItem("level")));
           roles.add(new Role(roleName.toLowerCase(),rank));
        }
         locs[j] = new Location(maxShots,locName.toLowerCase(),null,neighbors,roles);
      }   
      /* setup trailer and casting office */  
      locs[10] = new Location(0,"office", null,officeNeighbors,null);
      locs[11] = new Location(0,"trailer", null,trailerNeighbors,null); 
     
   return locs; 
   }
   
	public static NodeList CardParser() {
			try {
				File xmlFile = new File("cards.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				Element root = doc.getDocumentElement();
				root.normalize();
            ArrayList<Element> elementList = new ArrayList<Element>();
				NodeList cardNames = root.getElementsByTagName("card");
				
				for (int i= 0; i<cardNames.getLength(); i++) {
					Element scene = (Element) cardNames.item(i);
               elementList.add(scene);
					NodeList sceneList = scene.getElementsByTagName("scene");
					NodeList roleList = scene.getElementsByTagName("part");
               return cardNames;
				}
			}catch (Exception e) {
		    	e.printStackTrace();
		    }
          
           return null;
	}

      public static ArrayList<Scene> sceneDeck() {
         NodeList elementList = CardParser();
         ArrayList<Scene> sceneDeck  = new ArrayList<Scene>();     
         String scenename;
         String roleName;
         int scenenum;
         int budget;
         int rank;
         ArrayList<Role> oncardroles = new ArrayList<Role>();
         
      	for (int i= 0; i<elementList.getLength(); i++) {
               oncardroles = new ArrayList<Role>();
					scenename = getNameString(elementList.item(i).getAttributes().getNamedItem("name"));
               budget = Integer.parseInt(getNameString(elementList.item(i).getAttributes().getNamedItem("budget")));
					Element scene = (Element) elementList.item(i);
               NodeList sceneList = scene.getElementsByTagName("scene");
					NodeList roleList = scene.getElementsByTagName("part");
               scenenum = Integer.parseInt(getNameString(sceneList.item(0).getAttributes().getNamedItem("number")));
               
               for(int j=0; j<roleList.getLength();j++){
                  roleName=getNameString(roleList.item(j).getAttributes().getNamedItem("name") );
                  rank=Integer.parseInt(getNameString(roleList.item(j).getAttributes().getNamedItem("level")));
                  oncardroles.add(new Role(roleName,rank));
               }
               sceneDeck.add(new Scene(budget,oncardroles,scenename));
		}

      return sceneDeck;
   }
     
     
     
}
