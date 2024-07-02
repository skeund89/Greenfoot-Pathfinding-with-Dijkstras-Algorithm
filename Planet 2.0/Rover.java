import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Rover extends Actor
{

    private Display anzeige;
    private String karte[][] = new String[12][16]; // y K�stchen: 12, x k�stche: 16; start von 0, da minus eins

    /**
     * Die Anweisungen der Methode act werden ausgef�hrt, wenn der Act-Button
     * im Hauptfenster geklickt wird.
     */ 

    public void test()
    {
        List<Node> unvisited = initialisieren(14,4);
    }

    public void act()
    {
        int start_posX = getX();
        int start_posY = getY();

        List<Node> unvisited = initialisieren(14,4);
        fahreZuKoordinaten(start_posX, start_posY);
        dijkstrasAlgorithm(unvisited);
        List<Node> path = getShortestPath(unvisited);
        System.out.println("Fastest Path: " + path);
        driveFastestWay(path);
    } 

    public void Projekt(int end_posX, int end_posY)
    {
        // sichern der start postion
        int start_posX = getX();
        int start_posY = getY();
        int start_rotation = getRotation();
        int gameSpeed = 20;

        // Greenfoot.setSpeed() sezt die schnelligkeit des spieles, indem fall auf die schnelligkeit Gamespeed
        Greenfoot.setSpeed(gameSpeed);

        // zu 0,0 fahren
        Greenfoot.setSpeed(50);
        fahreZuKoordinaten(0,0);
        setRotation(0);

        // kartieren
        System.out.println("\nKarte:");
        kartieren();
        Greenfoot.setSpeed(gameSpeed);

        // Zur�ck zum Startpunkt fahren
        Greenfoot.setSpeed(50);
        fahreZuKoordinaten(start_posX, start_posY);
        setRotation(start_rotation);

        // platzieren und initialisieren der Nodes
        Greenfoot.setSpeed(98);
        List<Node> unvisited = initialisieren(end_posX, end_posY);
        Greenfoot.setSpeed(gameSpeed);

        // zum startg positionieren + k�rzerer Weg berechnen
        Greenfoot.setSpeed(50);
        fahreZuKoordinaten(start_posX, start_posY);
        dijkstrasAlgorithm(unvisited);
        Greenfoot.setSpeed(gameSpeed);

        // weg fahrbar machen + fahren
        List<Node> path = getShortestPath(unvisited);
        String[][] karteSchnellsterWeg = driveFastestWay(path);
        
        // karte mit dem schnellsten Weg ausgeben
        System.out.println("\nKarte mit dem schnellsten Weg:");
        arrayAusgeben(karteSchnellsterWeg);
    }

    private List<Node> initialisieren(int endpointX, int endpointY)
    {
        List<Node> visited = new ArrayList<Node>();
        List<Node> unvisited = new ArrayList<Node>(); // besser einen arraylist zu nutzen, unbegr�ndet; https://stackoverflow.com/questions/10400373/add-an-object-to-an-array-of-a-custom-class
        Node previousNode; // f�r das angeben der edges 
        int nodeNumber = 0; // wichtig f�r die namen der nodes

        // platzieren der start und end nodes + f�gt sie zu der gesamt liste zu
        Node startNode = placeNode("nodeStart", getX(), getY(), 0);
        unvisited.add(startNode); 
        previousNode = startNode;

        setzeMarkeMitXY(endpointX, endpointY);
        Node endNode = placeNode("nodeEnd", endpointX, endpointY, Integer.MAX_VALUE);
        unvisited.add(endNode);

        for(int i = 0; i < 1000;i++)
        {
            String nodeName = "node" + nodeNumber;
            ArrayList<String> Richtungen = new ArrayList<String>(3);

            // speichern der node der jeweiligen schleifen iteration, start node nur f�r den anfang wird 100% neues node werden
            Node node = startNode;

            // Richtungen hinzuf�gen
            Richtungen.add("links");
            Richtungen.add("vorne");
            Richtungen.add("rechts");

            // bei keinen weg nach Links, Mitte, Rechts == drehen und bis zur n�chsten Kreuzung fahren
            if (huegelVorhanden("links") && huegelVorhanden("vorne") && huegelVorhanden("rechts"))
            {
                setRotation(getRotation() -180);
            }

            for(int y = Richtungen.size() - 1; y >= 0; y--)
            {
                int originalRotation = getRotation(); // dient f�r das richtige drehen des Rovers

                if(huegelVorhanden(Richtungen.get(y))) // falls Huegelvorhandne
                {
                    String richtung = Richtungen.remove(y); // entferne y von Richtungen
                }
                else
                {
                    // drehen in die verlangte richtung:
                    if(Richtungen.get(y) != "vorne")
                    {
                        drehe(Richtungen.get(y));
                    }

                    // fahre bis zur n�chsten kreuzung oder drehung
                    int drivingCost = 0; // nach pr�fende schleife wird verwendet da sonst bei normaler while schleife bei der ersten iteration sofort true ist => die ganze zeit an der gleichen stelle
                    do{
                        fahre();
                        drivingCost++;
                    }
                    while(!huegelVorhanden("vorne") && huegelVorhanden("links") && huegelVorhanden("rechts")); // fahre bis zur n�chsten kreuzung oder drehung

                    // Rover setzt node + verk�nft neue Node mit vorher Node oder Verkn�pft node nur mit vorher node fals node schon besteht
                    if (getWorld().getObjectsAt(getX(),getY(),Node.class).isEmpty()) 
                    {
                        node = placeNode(nodeName, getX(), getY(), Integer.MAX_VALUE); // erstellen der Node

                        // verkn�pfen von den beiden Nodes miteinander
                        node.addEdge(previousNode, calculateDistance(node.x, node.y, previousNode.x, previousNode.y));
                        previousNode.addEdge(node, calculateDistance(node.x, node.y, previousNode.x, previousNode.y));

                        unvisited.add(node);
                        nodeNumber++;
                    }
                    else if (!getWorld().getObjectsAt(getX(),getY(),Node.class).isEmpty())
                    {
                        // bereits erstellten node mit content in der variable node ersetzen 
                        node = (Node) getWorld().getObjectsAt(getX(),getY(),Node.class).get(0);

                        if(node != previousNode)
                        {
                            // verkn�pfen von den beiden Nodes miteinander
                            node.addEdge(previousNode, calculateDistance(node.x, node.y, previousNode.x, previousNode.y));
                            previousNode.addEdge(node, calculateDistance(node.x, node.y, previousNode.x, previousNode.y));
                        }
                    }
                    else
                    {
                        System.out.println("Fehler!");
                    }

                    // Zur�ckfahren
                    setRotation(getRotation() - 180);
                    for (int j = 0; j < drivingCost; j++)
                    {
                        fahre();
                    }
                    setRotation(originalRotation);
                }
            }

            // entscheiden in welche Richtung +  weiter fahren in Richtung 
            drehe(Richtungen.get(new Random().nextInt(Richtungen.size())));
            do{
                fahre();
            }
            while(!huegelVorhanden("vorne") && huegelVorhanden("links") && huegelVorhanden("rechts")); // fahre bis zur n�chsten kreuzung oder drehung

            // speichern der Node f�r die n�chsten neuen node +  zum verkn�pfen
            previousNode = (Node) getWorld().getObjectsAt(getX(),getY(),Node.class).get(0); 

            // Arraylist Richtungen leer setzen + dann alle Richtungen wieder hinzuf�gen 
            Richtungen.clear();
            Richtungen.add("links");
            Richtungen.add("vorne");
            Richtungen.add("rechts");

            /*
             * Notiz:
             * 1. f�r jede Richtung in Liste Richungen:
            1. wenn Huegel in Richtung besteht
            1. aussortieren aus Liste richtungen
            2. Sonst:
            1.in die Richtung fahren bis zur n�chsten Kreuzung durch entweder: immer nur in eine Richtung fahren, oder durch lange if schleife
            2. platzieren +verkn�pfen der nodes oder verkn�pfen von nodes + entfernen aus Richtungs liste
            3 Zur�ckfahren

            2. Richutng so setzen dass es wieder "links", "vorne", "rechts" gibt
             */
        }

        return unvisited;
    }

    public Node placeNode(String name, int x, int y, int distance)
    {
        Node node = new Node(name, distance,x ,y);
        getWorld().addObject(node, x, y);

        return node;
    }

    public void dijkstrasAlgorithm(List<Node> unvisited)
    {
        /* Aufbau: (currentNode) --- (edge) --- (neighborNode)
         * 
         * Durchf�hrung: 
         * s. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
         */

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        //Finden von nodeStart und hinzuf�gen der startNode in die priorityQueue
        Node startNode = getNodeByName(unvisited, "nodeStart");
        queue.add(startNode);

        while(!queue.isEmpty())
        {
            Node currentNode = queue.poll(); // entferne Node mit der kleinsten Distance aus der queue (Datentyp: priorityQueue)
            //System.out.println("\nVerarbeite Node: " + currentNode.name + " mit Abstand: " + currentNode.distance);

            // durch iterieren f�r jede edge (kante) der jetztigen Node
            for (Edge edge : currentNode.edges) 
            {
                Node neighborNode = edge.target; 
                int newDistance = currentNode.distance + edge.distance; // neue distanz: distanz der currentnode + distanz der edge (verbindung
                 //System.out.println("�berpr�fe Nachbar: " + neighborNode.name + " aktuelle Entfernung: " + neighborNode.distance + " neue Entfernung: " + newDistance);

                if(newDistance < neighborNode.distance) // wenn die neue Entfernung kleiner ist als die Entfernung der Nachbars node
                {
                    //System.out.println("Aktualisiere Node: " + neighborNode.name + " neue Entfernung: " + newDistance);
                    neighborNode.distance = newDistance;
                    neighborNode.previousNode = currentNode;
                    queue.add(neighborNode);
                }
            }
        }
    }

    private Node getNodeByName (List<Node> nodeList, String searchedNodeName)
    {
        for (Node node : nodeList)
        {
            if(node.name == searchedNodeName)
            {
                return node;
            }
        }
        System.out.println("Keine Node gefunden.");
        return null;
    }

    public List<Node> getShortestPath(List<Node> unvisited)
    {
        List<Node> path = new ArrayList<Node>(); 

        // suchen f�r die endNode (abgespeichert mit name = "endNode"), falls gefunden for-loop stoppt, falls nicht stoppen der Methode
        Node currentNode = getNodeByName(unvisited, "nodeEnd");
        path.add(currentNode);

        // von hinten starten und immer die jetzige Node in die Liste path sezten und anschlie�end i auf die previousNode setzen bis die letze node als previous node null hat  
        while (currentNode.previousNode != null) 
        {
            currentNode = currentNode.previousNode; // problem: out of memory
            path.add(currentNode);
        }

        // reversen (drehen) der Liste damit man vom start zum ende fahren kann und nicht anders herum
        Collections.reverse(path);
        return path;
    }

    public String[][] driveFastestWay(List<Node> path) 
    {
        String[][] schnellsterWegKarte = karte.clone();

        for (int i = 0; i < path.size(); i++)
        {            
            // definieren von der currentNode und der neighborNode
            Node node = path.get(i);
            Node neighborNode = path.get(i);
            if (i != (path.size()-1)) // -1 da arraylist immer mit 0 anf�ngt
            {
                neighborNode = path.get(i+1);
            }
            
            // drehen und fahren zu neighbornode 
            dreheZurKoordinaten(neighborNode.x, neighborNode.y);
            schnellsterWegKarte[getY()][getX()] = "*";
            
            while(!(getX() == neighborNode.getX() && getY() == neighborNode.getY()))
            {
                fahre();
                schnellsterWegKarte[getY()][getX()] = "*";
            }
        }
        
        nachricht("Beim Ziel angekommnen.");
        return schnellsterWegKarte;
    }

    public int calculateDistance(int firstX, int firstY, int secondX, int secondY)
    {
        int distance = 0;

        if (firstX == secondX)
        {
            distance = Math.abs(firstY - secondY); // https://stackoverflow.com/questions/15388919/i-want-to-compare-to-variables-and-subtract-the-smallest-from-the-biggest
        }
        else if (firstY == secondY)
        {
            distance = Math.abs(firstX - secondX);
        }
        else
        {
            System.out.println("Du kannst nur gerade Distanzen berechnen bei den entweder die X Koordinate beider Punkte gleich ist oder die Y Koordinate.");
        }

        return distance;
    }

    public void kartieren()
    {
        // kartieren
        for (int y = 0; y < 6; y++)
        {
            reiheBewegen();
            drehe("rechts");
            fahre();
            drehe("rechts");

            reiheBewegen();
            drehe("links");
            fahre();
            drehe("links");
        }
        arrayAusgeben(karte);
    }

    private void reiheBewegen()
    {
        for (int x = 0; x < 16; x++) // 15 k�stchen fahren f�r die l�nge
        {
            if(huegelVorhanden("unten"))
            {
                karte[getY()][getX()] = "X";
            }
            else if (markeVorhanden())
            {
                karte[getY()][getX()] = "o";
            }
            else
            {
                karte[getY()][getX()] = "-";
            }
            fahre();
        }
    }

    public void arrayAusgeben(String array[][])
    {
        for (int y = 0; y < 12; y++)
        {
            for (int x = 0; x < 16; x++)
            {
                System.out.print(karte[y][x]);    
            }
            System.out.println();
        }
    }

    public void dreheZurKoordinaten(int reqX, int reqY)
    {
        // Y
        if(reqY<getY())
        {
            dreheRichtung("oben");
        }
        else if(reqY>getY())
        {
            dreheRichtung("unten");
        }
        else
        {
            nachricht("Ich befinde ich bereits auf der Y Koordinate.");
        }

        // X 
        if(reqX<getX())
        {
            dreheRichtung("links");
        }
        else if(reqX>getX())
        {
            dreheRichtung("rechts");
        }
        else
        {
            nachricht("Ich befinde ich bereits auf der X Koordinate.");
        }
    }

    public void dreheRichtung(String richtung)
    {
        if(richtung == "links")
        {
            setRotation(180);
        }
        else if(richtung == "rechts")
        {
            setRotation(0);
        }
        else if(richtung == "oben")
        {
            setRotation(270);
        }
        else if (richtung == "unten")
        {
            setRotation(90);
        }
        else
        {
            nachricht("Eingabe entspricht nicht links, recht, oben oder unten.");
        }
    }

    public void fahreZuKoordinaten(int reqX, int reqY)
    {
        dreheZurKoordinaten(reqX, reqY);
        while(getX() != reqX)
        {
            fahre();
        }
        
        dreheZurKoordinaten(reqX, reqY);
        while(getY() != reqY)
        {
            fahre();
        }
    }

    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder er sich an der Grenze der Welt befinden,
     * dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void fahre()
    {
        int posX = getX();
        int posY = getY();

        /* if(huegelVorhanden("vorne"))
        {
        nachricht("Zu steil!");
        }
        else if(getRotation()==270 && getY()==1)
        {
        nachricht("Ich kann mich nicht bewegen");
        }
        else
        {*/
        move(1);
        Greenfoot.delay(1);

        /* if(posX==getX()&&posY==getY()&&!huegelVorhanden("vorne"))
        {
        nachricht("Ich kann mich nicht bewegen");
        } */
    }

    /**
     * Der Rover dreht sich um 90 Grad in die Richtung, die mit richtung (�links� oder �rechts�) �bergeben wurde.
     * Sollte ein anderer Text (String) als "rechts" oder "links" �bergeben werden, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void drehe(String richtung)
    {
        if(richtung=="rechts")
        {
            turn(90);
            //setRotation(getRotation()+90);
        }
        else if (richtung=="links")
        {
            turn(-90);
            // setRotation(getRotation()-90);
        }
        else
        {
            nachricht("Befehl nicht korrekt!");
        }
    }

    /**
     * Der Rover gibt durch einen Wahrheitswert (true oder false )zur�ck, ob sich auf seiner 
     * Position ein Objekt der Klasse Gestein befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean gesteinVorhanden()
    {
        if(getOneIntersectingObject(Gestein.class)!=null)
        {
            nachricht("Gestein gefunden!");
            return true;

        }

        nachricht("Kein Gestein vorhanden!");
        return false;
    }

    public void analysiereGestein()
    {
        if(gesteinVorhanden())
        {
            nachricht("Gestein untersucht! Wassergehalt ist " +
                ((Gestein)getOneIntersectingObject(Gestein.class)).getWassergehalt()+"%.");
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        }
        else
        {
            nachricht("Hier ist kein Gestein");
        }
    }

    /**
     * Der Rover �berpr�ft, ob sich in richtung ("rechts", "links", oder "vorne") ein Objekt der Klasse Huegel befindet.
     * Das Ergebnis wird auf dem Display angezeigt.
     * Sollte ein anderer Text (String) als "rechts", "links" oder "vorne" �bergeben werden, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public boolean huegelVorhanden(String richtung)
    {
        int rot = getRotation();

        if (richtung=="vorne" && rot==0 || richtung=="rechts" && rot==270 || richtung=="links" && rot==90)
        {
            if(getOneObjectAtOffset(1,0,Huegel.class)!=null)
            {
                return true;
            }
        }

        if (richtung=="vorne" && rot==180 || richtung=="rechts" && rot==90 || richtung=="links" && rot==270)
        {
            if(getOneObjectAtOffset(-1,0,Huegel.class)!=null)
            {
                return true;
            }
        }

        if (richtung=="vorne" && rot==90 || richtung=="rechts" && rot==0 || richtung=="links" && rot==180)
        {
            if(getOneObjectAtOffset(0,1,Huegel.class)!=null)
            {
                return true;
            }

        }

        if (richtung=="vorne" && rot==270 || richtung=="rechts" && rot==180 || richtung=="links" && rot==0)
        {
            if(getOneObjectAtOffset(0,-1,Huegel.class)!=null)
            {
                return true;
            }

        }

        if (richtung=="unten")
        {
            if(getOneIntersectingObject(Huegel.class)!=null)
            {
                nachricht("Huegel gefunden!");
                return true;
            }

            nachricht("Kein Huegel vorhanden!");
            return false;
        }

        if(richtung!="vorne" && richtung!="links" && richtung!="rechts")
        {
            nachricht("Befehl nicht korrekt!");
        }

        return false;
    }

    /**
     * Der Rover nimmt das Gestein auf seiner Position auf.
     * Sollte kein Objekt der Klasse Gestein vorhanden sein, dann erscheint
     * eine entsprechende Meldung auf dem Display.
     */
    public void nimmGestein()
    {
        if(gesteinVorhanden())
        {
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        }
        else 
        {
            nachricht("Hier ist kein Gestein");
        }
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse �Marke� auf seiner Position.
     */
    public void setzeMarke()
    {
        getWorld().addObject(new Marke(), getX(), getY());
    }

    public void setzeMarkeMitXY(int setX, int setY)
    {
        getWorld().addObject(new Marke(), setX, setY);
    }

    /**
     * *Der Rover gibt durch einen Wahrheitswert (true oder false )zur�ck,
     * *ob sich auf seiner Position ein Objekt der Marke befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean markeVorhanden()
    {
        if(getOneIntersectingObject(Marke.class)!=null)
        {   nachricht("Marke gefunden!");
            return true;
        }

        nachricht("Keine Marke vorhanden!");
        return false;
    }

    public void entferneMarke()
    {
        if(markeVorhanden())
        {
            removeTouching(Marke.class);
        }
    }

    private void nachricht(String pText)
    {
        if(anzeige!=null)
        {
            anzeige.anzeigen(pText);
            Greenfoot.delay(1);
            anzeige.loeschen();
        }
    }

    private void displayAusschalten()
    {
        getWorld().removeObject(anzeige);

    }

    protected void addedToWorld(World world)
    {

        setImage("images/rover.png");
        world = getWorld();
        anzeige = new Display();
        anzeige.setImage("images/nachricht.png");
        world.addObject(anzeige, 7, 0);
        if(getY()==0)
        {
            setLocation(getX(),1);
        }
        anzeige.anzeigen("Ich bin bereit");

    }

    class Display extends Actor
    {
        GreenfootImage bild; 

        public Display()
        {
            bild = getImage();
        }

        public void act() 
        {

        }  

        public void anzeigen(String pText)
        {
            loeschen();
            getImage().drawImage(new GreenfootImage(pText, 25, Color.BLACK, new Color(0, 0, 0, 0)),10,10);

        }

        public void loeschen()
        {
            getImage().clear();
            setImage("images/nachricht.png");
        }

    }
}

