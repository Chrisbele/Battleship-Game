public class Player
{
    //Ο κάθε παίκτης έχει το δικό του Board και τα τις αντίστοιχες μεταβλητές για τα στατιστικά του.
    private String name;
    private int shots = 0;
    private int missedShots = 0;
    private int successShots = 0;
    private int repeatedShots = 0;
    private Board myBoard;
    
    //Καταστευαστής.Η boolean τιμή απαραίτητη στην εκτύπωση του πίνακα.
    public Player(String name,boolean isHidden)
    {
        this.name = name;
        myBoard = new Board(isHidden);
    }
    public Board getBoard()
    {
        return myBoard;
    }
    public void placeAllships()
    {
        myBoard.placeAllShips();
    }
    //Μέθοδος που καλεί την αντίστοιχη placeAllShips του πίνακα για την τυχαία τοποθέτηση των πλοίων.
    public void placeShip(Ship givenShip,int row,int col,orientation or)throws OversizeException,OverlapTilesException,AdjacentTilesException
    {
        givenShip.setRow(row);
        givenShip.setCol(col);
        givenShip.setOrientation(or);
        givenShip.placeShip(myBoard);
        
    }
   /*Τοποθέτηση πλοίου στο board απο τον χρήστη.Καλώντας την μέθοδο placeShip της board και τους αντοίστιχους
       setters και getters της Ship*/
    public void manualPlaceShip(Ship s)
    {
    while(!s.isShipPlaced())
    {
        System.out.println("\nPlacing"+ s.getName());
        int input[] = new int[2];
        input = Game.getUserInput();
        orientation or = Game.getOrientation();
        try{
         this.placeShip(s,input[0],input[1],or);
        }
        catch(OversizeException e){System.out.println(e.getMessage      ());}
        catch(OverlapTilesException e){System.out.println       (e.getMessage());}
        catch(AdjacentTilesException e){System.out.println      (e.getMessage());}
        } 
       this.drawboards();
    }
    /*Μέθοδος η οποία ειναι υπεύθυνη για τον έλεγχω της κατάστησης του πλακιδίου και της αντίστοιχης μεταβολής του
    ανάλογα με το εαν η βολή ηταν επιτυχής ή άστοχη.Εδώ γίνονεται και η κατάλληλη διαχήριση για την 
    καταμέτρηση των στατιστικών*/
    public void fire(Board enemyBoard,int row,int col)
    {
        if(enemyBoard.getStatus(row,col)==Location.SHIP)
        {
            enemyBoard.setHit(row,col);
            System.out.println(name +" :Hit");
            shots++;
            successShots++;
        }
        else if(enemyBoard.getStatus(row,col)==Location.SEA)
        {
            enemyBoard.setMiss(row,col);
            System.out.println(name +" :Miss");
            shots++;
            missedShots++;
        }
        else if(enemyBoard.getStatus(row,col)==Location.HIT)
        {
            System.out.println(name +" :Already hit");
            repeatedShots++;
            shots++;
        }
        else if(enemyBoard.getStatus(row,col)==Location.MISS)
        {
            System.out.println(name +" :Already miss");
            repeatedShots++;
            shots++;
        }
    }
    //Μέθοδος η οποία τυπώνει τα στατιστικά στοιχεία του κάθε παίκτη.
    public void getStats()
    {
        System.out.println("\nStats:\nTotal shots: "+shots+"\nMissed shots: "+missedShots+"\nSuccessfull shots: "
        +successShots+"\nRepeated shots: "+repeatedShots);
    }
    //Μέθοδος η οποία τυπώνει τον πίνακα καλώντας την drawboards() του board.
    public void drawboards()
    {
        myBoard.drawboards();
    }   
    //Μέθοδος που ελέγχει αν όλα τα πλοία του πίνακα είναι βυθισμένα καλώντας την allShipsSunk του board.
    public boolean allShipsSunk()
    {
        return myBoard.allShipsSunk();
    }
    }
    
