enum Location
{
    SEA,SHIP,HIT,MISS;
}

//Κλάση η οποία αντιπροσοπεύει το κάθε πλακάκι του πίνακα.
public class Tile
{
    //Η μεταβλητή που δηλώνει την κατάσταση του πλακακίου.
    private Location status;
    
   //Ακολουθούν οι ανάλογοι setters και getters.   
    public boolean hasShip()
    {
       if(status == Location.SHIP) return true;
       else return false;
    }
    public boolean isHit()
    {
        if(status == Location.HIT) return true;
        else return false;
    }
    public boolean isMiss()
    {
        if (status == Location.MISS) return true;
        else return false;
    }
    public boolean isSea()
    {
        if ( status == Location.SEA) return true;
        else return false;
    }
    public void setHit()
    {
        status = Location.HIT;
    }
    public void setMiss()
    {
        status = Location.MISS;
    }
    public void setShip()
    {
        status = Location.SHIP;
    }
    public void setSea()
    {
        status = Location.SEA;
    }
    /*Μέθοδος με την οποία το κάθε πλακάκι ζωγραφίζει τον εαυτό του.Η μεταβλητη isHidden
    απαραίτητη για όταν τυπώνουμε τον πίνακα του αντιπάλου*/     
         
    public void draw(boolean hidden)
    {
        
        if(status==Location.SEA) System.out.print(" ~ ");
        if(status==Location.HIT) System.out.print(" X ");
        if(status==Location.MISS) System.out.print(" o ");
        if(!hidden)
        {
           if(status==Location.SHIP) System.out.print(" s "); 
        }
        else
        {
           if(status==Location.SHIP) System.out.print(" ~ ");
        }
        
        
    }
}
        
    
    
    
    
    


