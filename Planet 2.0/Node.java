import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node extends Actor
{
    public int x;
    public int y;
    public final String name; // durch final k�nnen nach dem initialisieren die variablen nicht mehr ge�ndert werden
    public int distance; // k�rzester Weg vom start node
    List<Edge> edges = new ArrayList<Edge>(); // arraylist f�r kontakte mit anderen nodes
    public Node previousNode; // node mit dem k�rzesten weg davor
    
    public Node(String name, int distance, int x, int y)
    {
        this.name = name;
        this.distance = distance;
        this.x = x;
        this.y = y;
    }
    
    public void addEdge(Node target, int distance)
    {
        // �berpr�fen ob edge mit den verlangten parametern in der node besteht 
        for (Edge edge : edges) {
            if (edge.target == target && edge.distance == distance) {
                return; // nicht returnen einfach beenden
            }
        }
        
        // F�ge edge hinzu falls besteht 
        edges.add(new Edge(target, distance));
    }
    
    public void setXY()
    {
        x = getX();
        y = getY();
    }
    
}

class Edge
{
    public final Node target;
    public final int distance;
    
    public Edge(Node target, int distance)
    {
        this.target = target;
        this.distance = distance;
    }
}