import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die einzigen aktiven Akteure in der Roboterwelt sind die Roboter.
 * Die Welt besteht aus 14 * 10 Feldern.
 */

public class Planet extends World
{
    private static int zellenGroesse = 50;

    /**
     * Erschaffe eine Welt mit 15 * 12 Zellen.
     */
    public Planet()
    {
        super(16, 12, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Marke.class, Gestein.class, Huegel.class);
        Greenfoot.setSpeed(20); 
        prepare();
    }

    private void prep8are()
    {
        
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Huegel huegel = new Huegel();
        addObject(huegel,0,11);
        Huegel huegel2 = new Huegel();
        addObject(huegel2,1,11);
        Huegel huegel3 = new Huegel();
        addObject(huegel3,2,11);
        Huegel huegel4 = new Huegel();
        addObject(huegel4,4,11);
        Huegel huegel5 = new Huegel();
        addObject(huegel5,5,11);
        Huegel huegel6 = new Huegel();
        addObject(huegel6,7,11);
        Huegel huegel7 = new Huegel();
        addObject(huegel7,8,11);
        Huegel huegel8 = new Huegel();
        addObject(huegel8,10,11);
        Huegel huegel9 = new Huegel();
        addObject(huegel9,12,11);
        Huegel huegel10 = new Huegel();
        addObject(huegel10,13,11);
        Huegel huegel11 = new Huegel();
        addObject(huegel11,14,11);
        Huegel huegel12 = new Huegel();
        addObject(huegel12,15,11);
        Huegel huegel13 = new Huegel();
        addObject(huegel13,11,11);
        Huegel huegel14 = new Huegel();
        addObject(huegel14,9,11);
        Huegel huegel15 = new Huegel();
        addObject(huegel15,6,11);
        Huegel huegel16 = new Huegel();
        addObject(huegel16,3,11);
        Huegel huegel17 = new Huegel();
        addObject(huegel17,0,1);
        Huegel huegel18 = new Huegel();
        addObject(huegel18,0,3);
        Huegel huegel19 = new Huegel();
        addObject(huegel19,0,4);
        Huegel huegel20 = new Huegel();
        addObject(huegel20,0,5);
        Huegel huegel21 = new Huegel();
        addObject(huegel21,0,6);
        Huegel huegel22 = new Huegel();
        addObject(huegel22,0,7);
        Huegel huegel23 = new Huegel();
        addObject(huegel23,0,8);
        Huegel huegel24 = new Huegel();
        addObject(huegel24,0,9);
        huegel24.setLocation(0,9);
        Huegel huegel25 = new Huegel();
        addObject(huegel25,0,9);
        Huegel huegel26 = new Huegel();
        addObject(huegel26,0,10);
        Huegel huegel27 = new Huegel();
        addObject(huegel27,0,2);
        Huegel huegel28 = new Huegel();
        addObject(huegel28,2,9);
        Huegel huegel29 = new Huegel();
        addObject(huegel29,3,9);
        Huegel huegel30 = new Huegel();
        addObject(huegel30,2,8);
        Huegel huegel31 = new Huegel();
        addObject(huegel31,2,7);
        Huegel huegel32 = new Huegel();
        addObject(huegel32,3,7);
        Huegel huegel33 = new Huegel();
        addObject(huegel33,2,5);
        Huegel huegel34 = new Huegel();
        addObject(huegel34,3,5);
        Huegel huegel35 = new Huegel();
        addObject(huegel35,4,5);
        Huegel huegel36 = new Huegel();
        addObject(huegel36,5,5);
        Huegel huegel37 = new Huegel();
        addObject(huegel37,5,4);
        Huegel huegel38 = new Huegel();
        addObject(huegel38,5,3);
        Huegel huegel39 = new Huegel();
        addObject(huegel39,5,1);
        huegel39.setLocation(5,2);
        huegel38.setLocation(4,4);
        Huegel huegel40 = new Huegel();
        addObject(huegel40,3,4);
        Huegel huegel41 = new Huegel();
        addObject(huegel41,2,4);
        huegel41.setLocation(2,3);
        huegel40.setLocation(3,3);
        addObject(huegel38,4,2);
        Huegel huegel42 = new Huegel();
        addObject(huegel42,2,2);
        Huegel huegel43 = new Huegel();
        addObject(huegel43,0,0);
        Huegel huegel44 = new Huegel();
        addObject(huegel44,1,0);
        Huegel huegel45 = new Huegel();
        addObject(huegel45,2,0);
        Huegel huegel46 = new Huegel();
        addObject(huegel46,3,2);
        Huegel huegel47 = new Huegel();
        addObject(huegel47,3,0);
        Huegel huegel48 = new Huegel();
        addObject(huegel48,4,0);
        addObject(huegel38,5,0);
        Huegel huegel49 = new Huegel();
        addObject(huegel49,6,0);
        Huegel huegel50 = new Huegel();
        addObject(huegel50,7,0);
        Huegel huegel51 = new Huegel();
        addObject(huegel51,3,8);
        Huegel huegel52 = new Huegel();
        addObject(huegel52,4,9);
        Huegel huegel53 = new Huegel();
        addObject(huegel53,5,9);
        Huegel huegel54 = new Huegel();
        addObject(huegel54,6,9);
        Huegel huegel55 = new Huegel();
        addObject(huegel55,5,7);
        Huegel huegel56 = new Huegel();
        addObject(huegel56,6,7);
        Huegel huegel57 = new Huegel();
        addObject(huegel57,8,9);
        Huegel huegel58 = new Huegel();
        addObject(huegel58,8,8);
        Huegel huegel59 = new Huegel();
        addObject(huegel59,8,7);
        Huegel huegel60 = new Huegel();
        addObject(huegel60,7,5);
        Huegel huegel61 = new Huegel();
        addObject(huegel61,8,5);
        Huegel huegel62 = new Huegel();
        addObject(huegel62,8,6);
        Huegel huegel63 = new Huegel();
        addObject(huegel63,7,3);
        Huegel huegel64 = new Huegel();
        addObject(huegel64,8,3);
        Huegel huegel65 = new Huegel();
        addObject(huegel65,9,3);
        Huegel huegel66 = new Huegel();
        addObject(huegel66,9,9);
        Huegel huegel67 = new Huegel();
        addObject(huegel67,15,10);
        Huegel huegel68 = new Huegel();
        addObject(huegel68,15,9);
        Huegel huegel69 = new Huegel();
        addObject(huegel69,15,8);
        Huegel huegel70 = new Huegel();
        addObject(huegel70,15,7);
        Huegel huegel71 = new Huegel();
        addObject(huegel71,15,6);
        Huegel huegel72 = new Huegel();
        addObject(huegel72,15,4);
        Huegel huegel73 = new Huegel();
        addObject(huegel73,15,5);
        Huegel huegel74 = new Huegel();
        addObject(huegel74,15,3);
        Huegel huegel75 = new Huegel();
        addObject(huegel75,15,2);
        Huegel huegel76 = new Huegel();
        addObject(huegel76,15,1);
        Huegel huegel77 = new Huegel();
        addObject(huegel77,15,0);
        Huegel huegel78 = new Huegel();
        addObject(huegel78,14,0);
        Huegel huegel79 = new Huegel();
        addObject(huegel79,12,0);
        Huegel huegel80 = new Huegel();
        addObject(huegel80,10,0);
        Huegel huegel81 = new Huegel();
        addObject(huegel81,9,0);
        Huegel huegel82 = new Huegel();
        addObject(huegel82,9,0);
        Huegel huegel83 = new Huegel();
        addObject(huegel83,8,0);
        Huegel huegel84 = new Huegel();
        addObject(huegel84,11,0);
        Huegel huegel85 = new Huegel();
        addObject(huegel85,13,0);
        Huegel huegel86 = new Huegel();
        addObject(huegel86,10,9);
        Huegel huegel87 = new Huegel();
        addObject(huegel87,11,9);
        Huegel huegel88 = new Huegel();
        addObject(huegel88,9,8);
        Huegel huegel89 = new Huegel();
        addObject(huegel89,9,7);
        Huegel huegel90 = new Huegel();
        addObject(huegel90,10,7);
        Huegel huegel91 = new Huegel();
        addObject(huegel91,11,7);
        huegel87.setLocation(11,8);
        huegel87.setLocation(11,9);
        huegel86.setLocation(12,9);
        Huegel huegel92 = new Huegel();
        addObject(huegel92,13,9);
        Huegel huegel93 = new Huegel();
        addObject(huegel93,12,7);
        Huegel huegel94 = new Huegel();
        addObject(huegel94,13,7);
        huegel86.setLocation(12,8);
        huegel92.setLocation(13,8);
        Huegel huegel95 = new Huegel();
        addObject(huegel95,12,9);
        Huegel huegel96 = new Huegel();
        addObject(huegel96,13,9);
        huegel91.setLocation(12,6);
        Huegel huegel97 = new Huegel();
        addObject(huegel97,11,5);
        Huegel huegel98 = new Huegel();
        addObject(huegel98,10,5);
        Huegel huegel99 = new Huegel();
        addObject(huegel99,12,5);
        Huegel huegel100 = new Huegel();
        addObject(huegel100,10,3);
        Huegel huegel101 = new Huegel();
        addObject(huegel101,11,3);
        Huegel huegel102 = new Huegel();
        addObject(huegel102,12,3);
        Huegel huegel103 = new Huegel();
        addObject(huegel103,12,3);
        Huegel huegel104 = new Huegel();
        addObject(huegel104,13,6);
        Huegel huegel105 = new Huegel();
        addObject(huegel105,13,5);
        Huegel huegel106 = new Huegel();
        addObject(huegel106,13,3);
        Huegel huegel107 = new Huegel();
        addObject(huegel107,13,2);
        Huegel huegel108 = new Huegel();
        addObject(huegel108,12,2);
        Huegel huegel109 = new Huegel();
        addObject(huegel109,12,2);
        Huegel huegel110 = new Huegel();
        addObject(huegel110,11,2);
        Huegel huegel111 = new Huegel();
        addObject(huegel111,9,2);
        Huegel huegel112 = new Huegel();
        addObject(huegel112,10,2);
        Huegel huegel113 = new Huegel();
        addObject(huegel113,8,2);
        Huegel huegel114 = new Huegel();
        addObject(huegel114,7,2);
        Huegel huegel115 = new Huegel();
        addObject(huegel115,5,0);
    }
}