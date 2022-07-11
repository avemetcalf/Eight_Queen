import java.util.Random;

public class main {


    //value for heuristic
    private static int heuristic = 0;
    //number of random restarts
    private static int Restart = 0;
  //count for number of state changes
    private static int Changes = 0;
    //Value of heuristic2
    private static int betterHeuristics = 0;
    
    
    public static void main(String[] args) {
         //variable for value of current heuristic
        int currentHeuristic;
        
        //System Output Message
        System.out.println("System Output!:");
        
        
        //Creating the initial board
        EightQueen[] currentBoard = newBoard();
        currentHeuristic = findHeuristic(currentBoard);
        
       
        //Loop until the current heuristic is zero to see if its the solution board
        while (currentHeuristic != 0) {
            currentBoard = nextBoard(currentBoard);
            currentHeuristic  = heuristic;
       
        //Board for non-solution + format messages
        System.out.println("Current h: " + currentHeuristic);
        System.out.print("Current State");
        printState(currentBoard);
        System.out.println("Neighbors found with lower h: " + betterHeuristics);
        System.out.println("Setting new current state");
        System.out.println(" ");
        
        }
        
       //Board for solution + format messages
        System.out.print("Current State");
        printState(currentBoard);
        System.out.println("Solution Found!");
        System.out.println("State Changes: " + Changes);
        System.out.println("Restarts: " + Restart);
        
    }
    
     // Setting heuristic value to 0
    public static int findHeuristic (EightQueen[] currrentstate) {
        int heuristic = 0;
        
        
        for (int i = 0; i< 8; i++) {
            for (int j=i+1; j<8; j++ ) {
                if (currrentstate[i].ifConflict(currrentstate[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    
    //Creating board of new queens that is 8 by 8
    public static EightQueen[] newBoard() {
    	EightQueen[] startingBoard = new EightQueen[8];
        
        Random random = new Random();
        
        //Place a queen(1) on random spot on board in random row
        for(int i=0; i<8; i++){
            startingBoard[i] = new EightQueen(random.nextInt(8), i);
        }
        //Starting board display
        return startingBoard;
    }
    
    //Print out of current state board
    private static void printState (EightQueen[] currrentstate) {
    
        //create a temporary board
        int[][] temporaryBoard = new int[8][8];
        
        //Loop through columns and put queens in board
        for (int i=0; i<8; i++) {
            temporaryBoard[currrentstate[i].getRow()][currrentstate[i].getColumn()]=1;
        }
        
        System.out.println();
        for (int i=0; i<8; i++) {
            for (int j= 0; j < 8; j++) {
                System.out.print(temporaryBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    
        
    //Find lowest heuristic
    public static EightQueen[] nextBoard (EightQueen[] currentBoard) {
        //New queens board for next board and new temporary board
    	EightQueen[] nextBoard = new EightQueen[8];
    	EightQueen[] temporaryBoard = new EightQueen[8];
        
        
        int currentHeuristic = findHeuristic(currentBoard);
        int lowerHeuristic = currentHeuristic;
        int temporaryHeuristic;

        //Values from current board are put in next and temporary board
        for (int i=0; i<8; i++) {
            nextBoard[i] = new EightQueen(currentBoard[i].getRow(), currentBoard[i].getColumn());
            temporaryBoard[i] = nextBoard[i];
        }
        //Loop through each column
        for (int i=0; i<8; i++) {
            if (i>0)
                temporaryBoard[i-1] = new EightQueen (currentBoard[i-1].getRow(), currentBoard[i-1].getColumn());
                temporaryBoard[i] = new EightQueen (0, temporaryBoard[i].getColumn());
                
            //Loop through each row
            for (int j=0; j<8; j++) {
                temporaryHeuristic = findHeuristic(temporaryBoard);
                //Is temporary board better or not
                if (temporaryHeuristic < lowerHeuristic) {
                    lowerHeuristic = temporaryHeuristic;
                    betterHeuristics++;
                    //Turn temporary board to next board
                    for (int k=0; k<8; k++) {
                        nextBoard[k] = new EightQueen(temporaryBoard[k].getRow(), temporaryBoard[k].getColumn());
                    }
                }
                //Move Queens around
                if (temporaryBoard[i].getRow()!=8-1)
                    temporaryBoard[i].move();           
            }
        }
        //See if current and lower heuristic are the same and make a new board
        if (lowerHeuristic == currentHeuristic) {
            Restart++;
           
            nextBoard = newBoard();
            heuristic = findHeuristic(nextBoard);
            System.out.println("" );
            System.out.println("RESTART");
            System.out.println("" );

        }
        else
            heuristic = lowerHeuristic;
            Changes++;
            return nextBoard;
    }
	
}
