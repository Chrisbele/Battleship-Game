import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Game
{
public static void main(String[] args)
{
 
    
    //Δημιουργία των δύο παικτών με false για τον πίνακα του παίκτη και true για τον πίνακα τους computer έτσι ώστε να μην είναι εμφανή τα πλοία.
    Player bele = new Player("Bele",false);
    Player computer = new Player("Computer",true);
    
    
    //Τοποθετούμε τα πλοια του computer τυχαία.
    computer.placeAllships();
    
    //Ζητάμε από τον χρήστη εαν θέλει να τοποτηθούν τα πλοια του τυχαία. 
    if(Game.randomPlace())
    {
        
        bele.placeAllships();
       
    }
    else
    {
        //Σε περίπτωση που ζητήσει να τα τοποθετήση μόνος του καλούμε την manualPlaseShip για κάθε πλοίο.
        Carrier c1 = new Carrier();
        Battleship b1 = new Battleship();
        Cruiser cu1 = new Cruiser();
        Destroyer d1 = new Destroyer();
        Submarine s1 = new Submarine();
        
        bele.manualPlaceShip(c1);
        bele.manualPlaceShip(b1);
        bele.manualPlaceShip(cu1);
        bele.manualPlaceShip(d1);
        bele.manualPlaceShip(s1);
        System.out.println("\n~You placed all ships~");
    }
        
        
       
        int counter = 1;
        //Το πρόγραμμα ελέγχει σε κάθε γύρο εαν έχουν βυθιστεί όλα τα πλοια του παίκτη ή του computer έτσι ώστε να τεραμτίσει το παιχνιδί.
        while(!bele.allShipsSunk() && !computer.allShipsSunk())
        {
            System.out.print("\nRound: "+counter++ +"\n");
            int input[] = new int[2];
            boolean flag = true;
            
            System.out.println("Player shot");
            /*Ζητάμε απο τον χρήστη τις συντεταγμένες για την πραγματοποίηση της βόλης.Σε περίπτωση που αυτές οδηγούν σε σημείο
            εκτός του πίνακα εμφανιζουμε κατάλληλο μύνημα και ξαναζητάμε είδοσο.Ο παραπάνω μηχανισμός υλοποιείται με την βοήθεια
            της μεταβλητής flag.*/
           
            while(flag)
            {
                input = Game.getUserInput();
                if(input[0]>6 || input[1]>6)
                {
                    System.out.println("Your coordinates leed out of the board");
                }
                else
                {
                    flag = false;
                }
            }
            bele.fire(computer.getBoard(),input[0],input[1]);
            computer.drawboards();
            
            /*Η ίδια διαδικασία πραγματοποιείται και για τον υπολογιστή μόνο που αυτή την φορα γίνεται χρήση της συνάρτηση getrandInput και ο αμυντικός
            προγραμματισμός δεν είναι αναγκαίος καθώς η μέθοδος έχει υλοποιηθεί έτσι ώστε να δίνει έγκυρες συντεταγμένες */
            
            System.out.println();
           
            System.out.println("Computer shot");
            input = Game.getRandInput();
            computer.fire(bele.getBoard(),input[0],input[1]);
            bele.drawboards();
            
        }
        //Αναλόγος με του παίκτη του οποίου θα βυθιστούν τα πλοία γρηγορότερα τυπώνεται κατάλληλο μύνημα.
        if(!bele.allShipsSunk())
        {
            System.out.println("\nCongratulations you won!");
        }
        else if(!computer.allShipsSunk())
        {
            System.out.println("\nYou lost.");
        }
        //Εμφανίζονται τα στατιστικά.
        bele.getStats();
        computer.getStats();
           
}

/*Μέθοδος η οποία ζητά απο τον χρήστη συντεγμένες εντός των ορίων του πίνακα κάνοντας χρήση αμυντικού προγραμματισμου
και τις επιστρέφει σε δυσδιάστατο πίνακα.*/
public static int[] getUserInput()
{
    Scanner userinput = new Scanner(System.in);
    int location[] = new int[2];
    boolean flag = true;
    
    do
    {
        System.out.print("Give coordinates: ");
        try{
            
            location[0]=userinput.nextInt();
            location[1]=userinput.nextInt();
            flag = false;
        }
        catch(InputMismatchException e)
       {
           System.out.println("You have given wrong format input.You must enter an integer: ");
           userinput.nextLine();
           flag = true;
          
        }
    }while(flag);
    
    
    return location;
}

//Μέθοδος η οποία επιστρέφει τυχαίες συντεταγμένες στα όρια του πίνακα κάνοντας χρήση της κλάσης Random.
public static int[] getRandInput()
{
    Random rand = new Random();
    
    int location[] = new int[2];
    location[0] = rand.nextInt(7);
    location[1]=  rand.nextInt(7);
    
    return location;
    
}

//Μέθοδος η οποία ζητά να απο τον χρήστη ένα αλφαρηθμιτικό το οποίο θα καθορίσει τον προσανατολισμό του πλοίου στην συνέχεια.ίνεται χρήση αμυντικού προγραμματισμού.
public static orientation getOrientation()
{
    orientation or = orientation.DOWN;
    int flag = 0;
    
    System.out.print("Give orientation D for down and R for right:");
    
    Scanner userInput = new Scanner(System.in);
    char c = userInput.next().charAt(0);
    
    while(flag == 0)
    {
        
        if(c =='R')
        {
            or = orientation.RIGHT;
            flag = 1;
        }
        else if(c=='D')
        {
            or = orientation.DOWN;
            flag = 1; 
        }
        else
        {
            System.out.println("You gave wrong input.Give D for down and R for right.");
            c = userInput.next().charAt(0);
        }
    }
     return or;
    }
    //Μέθοδος η οποία ζητά να απο τον χρήστη ένα αλφαρηθμητικό το οποίο μετατρέπεται σε boolean ανάλογα με το αν αυτο είναι Y(true) ή F(false).Γίνεται χρήση αμυντικού προγραμματισμού.
    public static boolean randomPlace()
    {
        System.out.println("Do you want to random place ships;(Y/N)");
        Scanner userInput = new Scanner(System.in);
        char c = userInput.next().charAt(0);
        boolean flag = true;
        boolean isRandom = false;
        
         while(flag)
    {
        
        if(c =='Y')
        {
            isRandom = true;
            flag = false;
        }
        else if(c=='N')
        {
            isRandom = false;
            flag = false; 
        }
        else
        {
            System.out.println("You gave wrong input.Give Y for random ship placement and N manual ship placement");
            c = userInput.next().charAt(0);
        }
    }
    
    return isRandom;
        
    }
}
    
    
    
    


