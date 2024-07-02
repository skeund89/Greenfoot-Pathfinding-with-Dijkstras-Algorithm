import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Marke extends Actor

{
    public String name;
    
     public Marke()
    {
        setImage("images/marke.png");
    }
    
    /**
     * Die Anweisungen der Methode act werden ausgeführt, wenn der Act-Button
     * im Hauptfenster geklickt wird.
     */
    public void act() 
    {
       
    }
    
    public void setName(String x)
    {
        name = x;
    }
    
    public String getName()
    {
        return name;
    }
}
