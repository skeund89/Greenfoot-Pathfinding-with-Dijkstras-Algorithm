import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gestein here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gestein extends Actor
{
    private int wassergehalt;
    
    public Gestein()
    {
        if (Greenfoot.getRandomNumber(2)==0)
        {
            setImage("images/gesteinRot.png");
        }
        else
        {
            setImage("images/gesteinBlau.png");
        }
    }

    /**
     * Die Anweisungen der Methode act werden ausgeführt, wenn der Act-Button
     * im Hauptfenster geklickt wird.
     */
    public void act() 
    {

    } 
    
    public int getWassergehalt()
    {
        return wassergehalt;
    }
}
