
import java.util.Random;

public class Board
{
    //Το board αποτελείται απο πλακάκια τύπου tile.
    private Tile[][] mainBoard = new Tile[7][7];
    //Απαραίτητο για την εκτύπωση του πίνακα του αντιπάλου
    private boolean isHidden;
    
    //Αρχικοποίηση του board σε sea για όλα τα πλακάκια.
    public Board(boolean isHidden)
    {
        for(int i=0; i<7; i++)
        {
            for(int j=0; j<7; j++)
            {
                mainBoard[i][j] = new Tile();
                mainBoard[i][j].setSea();
            }
        }
        
        this.isHidden = isHidden;
       
    }
    //Μέθοδος που επιστρέφει την κάτασταση του κάθε σημείου
    public Location getStatus(int row,int col)
    {
        if (mainBoard[row][col].isSea()) return Location.SEA;
        else if (mainBoard[row][col].hasShip()) return Location.SHIP;
        else if (mainBoard[row][col].isHit()) return Location.HIT;  
        else return Location.MISS;
    }
    //Οι ανάλογοι setters που καλούν τις μεθόδους τις Tile.
    public void setShip(int row,int col)
    {
        mainBoard[row][col].setShip();
    }
    
    public void setHit(int row,int col)
    {
        mainBoard[row][col].setHit();
    }
    public void setMiss(int row,int col)
    {
        mainBoard[row][col].setMiss();
    }
     //Μεθοδος η οποία τυπώνει τους πίνακες κάνοντας χρήση της αντιστοιχης μεθόδου στην Tile.
    public void drawboards()
    {
        if(isHidden) System.out.println("- - O P P O N E N T - - ");
        else System.out.println("  -  - Y  O  U  -  -");
        
        System.out.print("  0  1  2  3  4  5  6");
        System.out.println();
        for(int i=0 ; i<7; i++)
        {
            System.out.print(i);
            for(int j=0 ;j<7; j++)
            {
              mainBoard[i][j].draw(isHidden);
            }
            System.out.println();
      
                    
        }
    }
    /*Μέθοδος η οποία ελέγχει αν εκει που πρόκειτε να τοποθετηθεί το πλοίο υπάρχουν άλλα
    πλοία σε ακτίνα ενος πλακιδίου */
    public boolean getAdjacentTiles(int row,int col,orientation or,int size) 

    {
        boolean result = false;
        if(or == orientation.RIGHT)
        {
            if(col == 0)
            {
                if(mainBoard[row][col + size].hasShip())
                {
                    result = true;
                }
            }
            else if(col + size <= 6)
            {
                if(mainBoard[row][col + size].hasShip() || mainBoard[row][col - 1].hasShip())
                {
                    result = true;
                }
            }
            if (row == 0)
            {
                for(int i = col; i<col + size; i++)
                {
                    if(mainBoard[row + 1][i].hasShip())
                    {
                        result =  true;
                    }
                }
            }
            else if(row == 6)
            {
                for(int i = col; i<col + size; i++)
                {
                    if(mainBoard[row - 1][i].hasShip())
                    {
                        result =  true;
                    }
                }
            }
            else
            {
                for(int i = col; i<col + size; i++)
                {
                    if(mainBoard[row - 1][i].hasShip())
                    {
                        result = true;
                    }
                }
                for(int i = col; i<col + size; i++)
                {
                    if(mainBoard[row + 1][i].hasShip())
                    {
                        result = true;
                    }
                }
            }
        }
        if(or == orientation.DOWN)
        {
            if(row == 0)
            {
                if(mainBoard[row + size][col].hasShip())
                {
                    result = true;
                }
            }
            else if(row + size <= 6)
            {
                if(mainBoard[row + size][col].hasShip() || mainBoard[row - 1][col].hasShip())
                {
                    result = true;
                }
            }
            if (col == 0)
            {
                for(int i = row; i<row + size; i++)
                {
                    if(mainBoard[i][1].hasShip())
                    {
                        result =  true;
                    }
                }
            }
            else if(col == 6)
            {
                for(int i = row; i<row + size; i++)
                {
                    if(mainBoard[i][5].hasShip())
                    {
                        result =  true;
                    }
                }
            }
            else
            {
                for(int i = row; i<row + size; i++)
                {
                    if(mainBoard[i][col -1].hasShip())
                    {
                        result = true;
                    }
                }
                for(int i = row; i<row + size; i++)
                {
                    if(mainBoard[i][col + 1].hasShip())
                    {
                        result = true;
                    }
                }
            }
        }
       return result;
        
    
        
    
    }
    
    /*Μέθοδος η οποία τοποθετεί τα πλοια σε τυχαίες θέσεις κάνοντας χρήση της RandomPlaceShip. */

        public void  placeAllShips()
     {
         
        Battleship b1 = new Battleship(); 
        Carrier c1 = new Carrier();
        Submarine s1 = new Submarine();
        Cruiser cu1 = new Cruiser();
        Destroyer d1 = new Destroyer();
        
        b1.RandomPlaceShip(this);
        c1.RandomPlaceShip(this);
        s1.RandomPlaceShip(this);
        cu1.RandomPlaceShip(this);
        d1.RandomPlaceShip(this);
        
        
     
}
   /*Μέθοδος που 'σκανάρει΄ όλον τον πίνακα και ελέγχει αν έχουν μείνει πλοία που δεν έχουν 
   βυθιστεί. */
    public boolean  allShipsSunk()
    {
        boolean isGameFinished = true;;
        
        for (int i=0; i<7; i++)
        {
            for(int j=0;j<7; j++)
            {
                if(mainBoard[i][j].hasShip())
                {
                    isGameFinished = false;
                }
            }
        }
        
        return isGameFinished;
    }
    }
   

        
        