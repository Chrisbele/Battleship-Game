
import java.util.Random;
import java.lang.String;


enum orientation
    {
        RIGHT,DOWN
    }
    
public abstract class Ship
{
    
    private String name;
    private int row;
    private int col;
    private orientation whereToGo;
    private int size;
    private boolean hasShip = false;
    private boolean hasShipNear = false;
    private boolean shipPlaced = false;
    
    //Κατασκευαστής της Ship που καλείτε μόνο απο τις διάφορες υποκλάση καθώς η Ship ειναι abstract.    
    public Ship(int size,String name)
    {
        this.name = name;
        this.size = size;
 
    }
     //Οι αντίστιχοι setters και getters. 
    public void setRow(int row)
    {
        this.row = row;
    }
    public void setCol(int col)
    {
        this.col = col;
    }
    public void setOrientation(orientation whereToGo)
    {
        this.whereToGo = whereToGo;
    }
    public int getRow(){return row;}
    public int getCol(){return col;}
    public orientation getOrientation(){return whereToGo;}
    public String getName()
    {
        return name;
    }
    
    /*Μέθοδος η οποία παίρνει τοποθετεί το πλοίο στον πίνακα τον οποίο δέχεται σαν όρισμα 
       με σεβασμό πάντα στους όποιος περιορισμούς.*/
    
    public void placeShip(Board myBoard)throws OversizeException,OverlapTilesException,AdjacentTilesException
    {
       
       if( whereToGo == orientation.RIGHT)
       {
           hasShip = false;
           hasShipNear = false;
           if( col+size <= 6)
           {
               for(int i=col;i<col+size; i++)
               {
                   if(myBoard.getStatus(row,i)==Location.SHIP)
                   {
                      hasShip = true;
                   }
               }
               
               if(myBoard.getAdjacentTiles(row,col,whereToGo,size))
               {
                       hasShipNear = true;
               }
               /*Για να γίνει τελικά η τοποθέτηση του πλοίου θα πρέπει η μεθόδοι η μεταβλητές 
                  hasShip και hasShipNear να είναι έχουν παραμείνει false μετά τους 
                  όποιος ελέγχους έχουν προηγηθεί μέσω των αντίστοιχων for loop.*/
               if(!hasShip && !hasShipNear)
               {
                       for(int i=col; i<col+size; i++)
                       {
                           myBoard.setShip(row,i);
                           
                       }
                       shipPlaced = true;
               }
               /*Ανάλογα με το ποιες μεταβλητές έχουν μεταβεί στο true κάνουμε trow
                  και τα απαραίτητα exceptions.*/
               if(hasShip)
               {
                   throw new OverlapTilesException("There is already a ship placed at this path");
               }
               if(hasShipNear)
               {
                   throw new AdjacentTilesException("Every ship must have at least one block distance from every other ship");
               }
           }
           else
           {
               throw new OversizeException("The coordinates leed the ship out of bonus");
             
           }
           
        }
       //Παρόμοια λογική με την παραπάνω μονο αυτή την φορά για κάθετη τοποθέτηση.     
       else if( whereToGo == orientation.DOWN)
       {
           hasShip = false;
           hasShipNear = false;
           if( row+size <= 6)
           {
               for(int i=row;i<row+size; i++)
               {
                   if(myBoard.getStatus(i,col)==Location.SHIP)
                   {
                      hasShip = true;
                   }
                }
                  
                   if(myBoard.getAdjacentTiles(row,col,whereToGo,size))
                   {
                       hasShipNear = true;
                   }
               
               if(!hasShip && !hasShipNear)
               {
                   for(int i=row; i<row+size; i++)
                   {
                       myBoard.setShip(i,col);
                      
                   } 
                   shipPlaced = true;
               }
                if(hasShip)
               {
                   throw new OverlapTilesException("There is already a ship placed at this path");
               }
               if(hasShipNear)
               {
                   throw new AdjacentTilesException("Every ship must have at least one block distance from every other ship");
               }
           }
           else
           {
               throw new OversizeException("The coordinates leed the ship out of bonus");
             
           }
           
        }
       
     
    
    } 
    //Mέθοδος η οποία τοποθετεί το πλοιο σε τυχαίο σημείο κάνοντας χρήση της Random κλάσης.
   public void RandomPlaceShip(Board myBoard)
   {
        while(!shipPlaced)
        {
        Random rand = new Random();
        orientation or;
        int select = rand.nextInt(2);
        if(select == 0) or = orientation.RIGHT;
        else or = orientation.DOWN;
        
        int row1 = rand.nextInt(7);
        int col1 = rand.nextInt(7);
        
        
        
       
        row = row1;
        col = col1;
        whereToGo = or;
       
        try{
        this.placeShip(myBoard);
        }
        catch(OversizeException e){}
        catch(OverlapTilesException e){}
        catch(AdjacentTilesException e){}
        
        
         }
    }
    /*Μέθοδος που ανάλογα με το τη έχει συμβεί στην μέθοδο PlaceShip() η οποία είναι 
         υπεύθυνη για να μεταβάλει την τιμή της shipPlaced εφόσον τοποθετηθεί το πλοιο επ
         επιστρέφει την τιμή της*/
    public boolean isShipPlaced()
    {
        return shipPlaced;
    }
    
        

 }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

     
     
     
     
     