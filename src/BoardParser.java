import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/*Change Log:
	11/12/17	Not Completed, created a separate project so I could test parsing the xml file	11/12/18
	11/14/17	somewhat have the "take area" and "room area" parsed (commented section) 
	11/15/17	Parses the each location, their neighboring location, and the number of takes on a room.
				Added part names and corresponding levels to each part(role).
*/




public class BoardParser {

	public static NodeList[] main(String argv[]) {
	    try {
	    	File xmlFile = new File ("board.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(xmlFile);
	    	doc.getDocumentElement().normalize();
	    	Element boardElement = doc.getDocumentElement();	//BoardElement a location	(Default)
	    	
	    	System.out.println("Root Element: " + boardElement.getAttribute("name"));
	    	NodeList boardRooms = boardElement.getElementsByTagName("set");
         
         NodeList[] boardRoomsArray = new NodeList[boardRooms.getLength()];
         
	    	for (int i = 0; i < boardRooms.getLength(); i++){
	    		System.out.println("set " + boardRooms.item(i).getAttributes().getNamedItem("name"));
	    		Element rooms = (Element) boardRooms.item(i);
	    		//System.out.println(rooms.getAttributes().getNamedItem("name"));
	    		NodeList neighborList = rooms.getElementsByTagName("neighbor");
	    		System.out.println("neighbor1: " + neighborList.item(0).getAttributes().getNamedItem("name"));
	    		System.out.println("neighbor2: " + neighborList.item(1).getAttributes().getNamedItem("name"));
	    		System.out.println("neighbor3: " + neighborList.item(2).getAttributes().getNamedItem("name"));
	    		
	    		NodeList takesList = rooms.getElementsByTagName("take");
	    		NodeList partList = rooms.getElementsByTagName("part");
	    		
		        System.out.println("  take "+ takesList.item(0).getAttributes().getNamedItem("number"));
		        if(takesList.getLength() > 1){
		        	System.out.println("  take "+ takesList.item(1).getAttributes().getNamedItem("number"));
		        }
		        if(takesList.getLength() > 2){
          			System.out.println("  take "+ takesList.item(2).getAttributes().getNamedItem("number"));
       			}
	    		
	    		System.out.println("    part " + partList.item(0).getAttributes().getNamedItem("name") + "   " + partList.item(0).getAttributes().getNamedItem("level") );
	    		if(partList.getLength() > 1){
	    			System.out.println("    part " + partList.item(1).getAttributes().getNamedItem("name") + "   " + partList.item(1).getAttributes().getNamedItem("level"));
	    		}
	    		if(partList.getLength() > 2){
	    			System.out.println("    part " + partList.item(2).getAttributes().getNamedItem("name") + "   " + partList.item(2).getAttributes().getNamedItem("level"));
	    		}
	    		if(partList.getLength() > 3){
	    			System.out.println("    part " + partList.item(3).getAttributes().getNamedItem("name") + "   " + partList.item(3).getAttributes().getNamedItem("level"));
	    		}
	    		if(partList.getLength() > 4){
	    			System.out.println("    part " + partList.item(4).getAttributes().getNamedItem("name")+ "   " + partList.item(4).getAttributes().getNamedItem("level"));
	    		}
	    	   boardRoomsArray[i] = boardRooms;
	    	}
	    	
	    	/*  This block grabs the room areas and take areas but not the neighbors
	    	 System.out.println("\nCurrent Element :" + boardElement.getAttribute("name"));	
	    	//System.out.println("\nCurrent Element :" + boardElement.getNodeName());
	    	NodeList boardRooms = boardElement.getChildNodes();				// NeighborsList of a location on board. 
	    	
	    	for (int k = 0; k < boardRooms.getLength(); k++) {
	    		Node roomX = boardRooms.item(k);
    			
	    		System.out.println("\nSet Name :" + boardRooms.item(k).getAttributes());
    			
    			// Parses the Area of the Room
    			if (roomX.getNodeType() == Node.ELEMENT_NODE) {
	    			NodeList roomList = roomX.getChildNodes();
	    			Element setName = (Element) roomX;
	   
	    			// Parses the Area of the Room
	    			Node areaNode = roomList.item(3);
	    			Element areaElement = (Element) areaNode;
	    			int w = Integer.parseInt(areaElement.getAttribute("w"));
	    			int h = Integer.parseInt(areaElement.getAttribute("h"));
	    			int y = Integer.parseInt(areaElement.getAttribute("y"));
	    			int x = Integer.parseInt(areaElement.getAttribute("x"));
					System.out.println("Room Areas: w=" + w + " h=" + h + " y="+ y + " x="+ x);
    			
					if( roomX.getNodeName().equals("trailer")) {
						//set trailer area
					}else if (roomX.getNodeName().equals("office")) {
						//set office area
					}else {
						//set area of the other rooms
					}
					
					// Parses the Area of the Takes
					if (roomX.getNodeName().equals("set")) {
		    			Node takesNode = roomList.item(5);
		    			Element takesElement = (Element) takesNode;
		    			NodeList takesList = takesElement.getChildNodes();
		    			for (int p = 0; p< takesList.getLength(); p++) {
		    				Node shotNode = takesList.item(p);
		    				if (shotNode.getNodeType() == Node.ELEMENT_NODE) {
		    					NodeList shotList = shotNode.getChildNodes();
		    					Node shotAreaNode = shotList.item(0);
		    					Element shotAreaElement = (Element) shotAreaNode;
		    					int shotW = Integer.parseInt(shotAreaElement.getAttribute("w"));
		    					int shotH = Integer.parseInt(shotAreaElement.getAttribute("h"));
		    					int shotY = Integer.parseInt(shotAreaElement.getAttribute("y"));
		    					int shotX = Integer.parseInt(shotAreaElement.getAttribute("x"));
		    					//System.out.println("Take Areas: w=" + w + " h=" + h + " y="+ y + " x="+ x);
		    				}
		    			}
		    		}
    			}
	    	}*/
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}